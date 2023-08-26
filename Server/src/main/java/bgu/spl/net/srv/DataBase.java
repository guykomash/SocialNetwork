package bgu.spl.net.srv;

import bgu.spl.net.api.bidi.ConnectionsClass;
import bgu.spl.net.impl.echo.Messages.Error;
import bgu.spl.net.impl.echo.Messages.Message;
//import org.graalvm.compiler.lir.LIRInstruction;
//import org.graalvm.compiler.nodes.calc.IntegerTestNode;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataBase {
    private ConcurrentHashMap<String, User> UsersDataBase;
    private ConcurrentHashMap<User, Integer> onlineUsers;
    private LinkedList<String> filteredWords;

    private static class SingletonHolder {
        private static DataBase instance = new DataBase();
    }

    private DataBase() {
        filteredWords = new LinkedList<String>();
        UsersDataBase = new ConcurrentHashMap<String, User>();
        onlineUsers = new ConcurrentHashMap<User, Integer>();
    }

    public static DataBase getInstance() {
        return DataBase.SingletonHolder.instance;
    }

    public boolean addUser(String username, String password, String birthday) {
        if (UsersDataBase.containsKey(username))
            return false;
        User newUser = new User(username, password, birthday);
        UsersDataBase.put(username, newUser);
        return true;
    }

    public User getUser(String username) {
        return UsersDataBase.get(username); //null if no such user exist!
    }

    public ConcurrentHashMap<User, Integer> getOnlineUsers() {
        return this.onlineUsers;
    }

    public Integer getConnectionId(User user) {
        return onlineUsers.get(user);
    }

    public boolean loginUser(String username, String password, int connectionId) {
        if (UsersDataBase.containsKey(username)) {
            User user = UsersDataBase.get(username);
            if (onlineUsers.contains(connectionId))
                return false;
            if(user.getPassword().equals(password)){
                user.login();
                onlineUsers.put(user, connectionId);
                return true;
            }
            }
         return false; //user is not registerd
    }
    public boolean logoutUser(String username, int connectionId) {
        if (UsersDataBase.containsKey(username)) {
            User user = UsersDataBase.get(username);
            if (user.isLoggedin()) {
                user.logout();
                onlineUsers.remove(user);
                return true;
            } else return false;
        }
        return false; //user is not registered
    }

    public boolean followUser(String userName, String userToFollow) {
        if (UsersDataBase.containsKey(userName) && UsersDataBase.containsKey(userToFollow)) {
            User user = UsersDataBase.get(userName);
            User userfollow = UsersDataBase.get(userToFollow);
            if (user.isLoggedin() && userfollow!=user) {
                if (user.getBlocked().contains(userfollow))
                    return false;
                if (!user.getFollowingVector().contains(userfollow)) {
                    user.addFollowing(userfollow);
                    userfollow.addFollower(user);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean unfollowUser(String username, String usertounfollow) {
        if (UsersDataBase.containsKey(username) && UsersDataBase.containsKey(usertounfollow)) {
            User user = UsersDataBase.get(username);
            User userunfollow = UsersDataBase.get(usertounfollow);
            if (user.isLoggedin()) {
                if (user.getFollowingVector().contains(userunfollow)) {
                    user.removeFollowing(userunfollow);
                    userunfollow.removeFollower(user);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canUserPost(String username) {
        if (UsersDataBase.containsKey(username)) {
            User user = UsersDataBase.get(username);
            if (user.isLoggedin()) {
                return true;
            }
        }
        return false;
    }


    public boolean canUserPM(String username, String recipientUser) {
        if (UsersDataBase.containsKey(username) && UsersDataBase.containsKey(recipientUser)) {
            User user = UsersDataBase.get(username);
            User recipient = UsersDataBase.get(recipientUser);
            if (user.isLoggedin()) {
                if (user.getFollowingVector().contains(recipient))
                    return true;
            }
        }
        return false;
    }

    public boolean blockUsers(String username, String toBlockusername) {
        if (UsersDataBase.containsKey(username) && UsersDataBase.containsKey(toBlockusername)) {
            User user = UsersDataBase.get(username);
            User toBlockuser = UsersDataBase.get(toBlockusername);
            if (!user.getBlocked().contains(toBlockuser)) {
                user.addBlocked(toBlockuser);
                toBlockuser.addBlocked(user);
                user.removeFollowing(toBlockuser);
                user.removeFollower(toBlockuser);
                toBlockuser.removeFollowing(user);
                toBlockuser.removeFollower(user);
                return true;
            }
        }
        return false;
    }

}
