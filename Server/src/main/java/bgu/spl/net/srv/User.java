package bgu.spl.net.srv;


import bgu.spl.net.impl.echo.Messages.Message;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class User {

    private int lastOnline;
    private String Username;
    private String Password;
    private String Birthday;
    private Vector<User> Followers;
    private Vector<User> Following;
    private Vector<User> Blocked;
    private boolean isLoggedin;
    private LinkedList<Message> waitingNotifications;
    private LinkedList<Message> Posts;
    private LinkedList<Message> PMs;
    private Object lockFollowers = new Object();
    private Object lockFollowing = new Object();
    ;
    private Object lockBlocked = new Object();
    ;
    private Object lockWaitingNotifications = new Object();
    ;

    public User(String username, String password, String birthday) {
        this.Username = username;
        this.Password = password;
        this.Birthday = birthday;
        isLoggedin = false;
        waitingNotifications = new LinkedList<Message>();
        Posts = new LinkedList<Message>();
        PMs = new LinkedList<Message>();
        Followers = new Vector<User>();
        Following = new Vector<User>();
        Blocked = new Vector<User>();

    }

    public void setLastOnline(int time) {
        this.lastOnline = time;
    }

    public void login() {
        isLoggedin = true;
    }

    public void logout() {
        isLoggedin = false;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public Vector<User> getFollowersVector() {
        return Followers;
    }

    public Vector<User> getFollowingVector() {
        return Following;
    }

    public void addWaitingNotfication(Message msg) {
        synchronized (lockWaitingNotifications) {
            this.waitingNotifications.add(msg);
        }
    }

    public LinkedList<Message> getWaitingNotfications() {
        return waitingNotifications;
    }

    public void addFollower(User follower) {
        synchronized (lockFollowers) {
            Followers.add(follower);
        }
    }

    public void removeFollower(User follower) {
        synchronized (lockFollowers) {
            Followers.remove(follower);
        }
    }

    public void addFollowing(User following) {
        synchronized (lockFollowing) {
            Following.add(following);
        }
    }

    public void removeFollowing(User following) {
        synchronized (lockFollowing) {
            Following.remove(following);
        }
    }

    public void addBlocked(User toBlock) {
        synchronized (lockBlocked) {
            Blocked.add(toBlock);
        }
    }

    public void addNewPost(Message msg) {
        this.Posts.add(msg);
    }

    public void addNewPM(Message msg) {
        this.PMs.add(msg);
    }

    public Vector<User> getBlocked() {
        return this.Blocked;
    }

    public boolean isUserBlocked(User user) {
        return Blocked.contains(user);
    }

    public int getAge() {
        Integer day = Integer.parseInt(this.Birthday.substring(0, 2));
        Integer month = Integer.parseInt(this.Birthday.substring(3, 5));
        Integer year = Integer.parseInt(this.Birthday.substring(6, 10));

        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }

    public String toStringNumOfPosts() {
        return toStringNums(Posts.size());
    }

    public String toStringNumOfFollowers() {
        return toStringNums(Followers.size());
    }

    public String toStringNumOfFollowing() {
        return toStringNums(Following.size());
    }

    public int getNumofPosts() {
        return Posts.size();
    }

    public int getNumofFollowers() {
        return Followers.size();
    }

    public int getNumofFollowing() {
        return Following.size();
    }

    public String toStringNums(int num) {
        return String.valueOf(num);
    }

    public String toStringAge() {
        return toStringNums(getAge());
    }


}