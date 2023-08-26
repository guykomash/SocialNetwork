package bgu.spl.net.api.bidi;

import bgu.spl.net.impl.echo.Messages.*;
import bgu.spl.net.impl.echo.Messages.Error;
import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class BidiMessagingProtocolImpl implements BidiMessagingProtocol<Message> {

    private ConnectionsClass connections;
    private boolean shouldTerminate = false;
    private int connectionId;
    private DataBase dataBase;
    private User loggedUser = null;

    ;

    public BidiMessagingProtocolImpl() {
        connections = ConnectionsClass.getInstance();
        dataBase = DataBase.getInstance();

    }

    @Override
    public void start(int connectionId, Connections connections) {
        this.connections = (ConnectionsClass) connections;
        this.connectionId = connectionId;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    @Override
    public void process(Message message) {
        Message msg = null;
        int opcode = message.getOpCode();
        switch (opcode) {
            case 1:
                msg = ((Register) (message)).createUser();
                break;
            case 2:
                msg = ((Login) (message)).loginUser(connectionId);
                if (msg instanceof Ack) {
                    setUserforProtocol(dataBase.getUser(((Login) (message)).getUserName()));
                    LinkedList<Message> waiting = loggedUser.getWaitingNotfications();
                    for (int i = 0; i < waiting.size(); i++) {
                        connections.send(connectionId, waiting.get(i));
                    }
                }
                break;
            case 3:
                msg = ((Logout) (message)).logoutUser(loggedUser, connectionId);
                if (msg instanceof Ack)
                    setUserforProtocol(null);
                break;
            case 4:
                msg = ((FollowUnfollow) (message)).FollowunFollow(loggedUser);
                break;
            case 5:
                Vector<Integer> idForPost = ((Post) (message)).newPost(loggedUser);
                String notificationMsg = ((Post) (message)).makeNotificationMessage(loggedUser);
                if (idForPost != null) {
                    for (int i = 0; i < idForPost.size(); i++) {
                        connections.send(idForPost.get(i), new Notification(notificationMsg));
                    }
                    connections.send(connectionId, new Ack(message));
                } else
                    connections.send(connectionId, new Error(message));
                break;
            case 6:
                Integer recipientId = ((PM) (message)).sendPM(loggedUser);
                if (recipientId != null) {
                    if (recipientId != -1) {
                        connections.send(recipientId, new Notification(((PM) (message)).makeNotificationMessage(loggedUser)));
                    }
                    connections.send(connectionId, new Ack(message));
                } else connections.send(connectionId, new Error(message));
                break;
            case 7:
                Vector<Ack> acksToSend = ((Logstat) (message)).getLogstat(loggedUser);
                if (acksToSend != null) {
                    for (int i = 0; i < acksToSend.size(); i++) {
                        connections.send(connectionId, acksToSend.get(i));
                    }
                } else connections.send(connectionId, new Error(message));

                break;
            case 8:
                if (loggedUser != null && ((Stats) (message)).isListValid(loggedUser)) {
                    Vector<Ack> ackStats = ((Stats) (message)).getStats();
                    for (int i = 0; i < ackStats.size(); i++) {
                        connections.send(connectionId, ackStats.get(i));
                    }
                } else
                    connections.send(connectionId, new Error(message));
                break;
            case 12:
                msg = ((Block) (message)).blockUsers(loggedUser);
                break;
        }
        if (msg != null)
            connections.send(connectionId, msg);
    }

    public void setUserforProtocol(User user) {
        loggedUser = user;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

}
