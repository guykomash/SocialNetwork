package bgu.spl.net.impl.echo.Messages;

import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

import java.util.Enumeration;
import java.util.Set;
import java.util.Vector;

public class Logstat implements Message{

    private String message="07";
    private final short opcode=7;
    private DataBase dataBase=DataBase.getInstance();

    public Logstat(){
    }
    public String getMessage(){
        return this.message;
    }
    public String getStringOpCode() {
        return "07";
    }

    @Override
    public short getOpCode() {
        return opcode;
    }

    public Vector<Ack> getLogstat(User user) {
        if (user != null) {
            Vector<Ack> ackForLoggedin = new Vector<Ack>();
            Set<User> keySet = dataBase.getOnlineUsers().keySet();
            for (User userToGetInfo : keySet) {
                //&& user != userToGetInfo
                if (!user.isUserBlocked(userToGetInfo))
                    ackForLoggedin.add(makeLogstatAck(userToGetInfo));
            }
            return ackForLoggedin;
        }
        return null;
    }

    public Ack makeLogstatAck(User user){
    return new Ack("10"+"07"+user.toStringAge()+" "+user.toStringNumOfPosts()+" "+user.toStringNumOfFollowers()+" "+user.toStringNumOfFollowing());

    }
}