package bgu.spl.net.impl.echo.Messages;

import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Post implements Message {

 private DataBase dataBase=DataBase.getInstance();

 private final short opcode=5;
 private String message="";
 private String content="";
 private Vector<String> taggedNames;

 public Post(String message){
  this.message = message;
  taggedNames=new Vector<String>();
  processMessage();
 }

 public String getMessage(){
  return this.message;
 }

 public void processMessage() {
  int index = 0;
  while (index<message.length()) {
   content = content + message.charAt(index);
   index++;
  }
  processTagged();

 }
 public void processTagged(){
  String name="";
  int i=0;
  while(i<content.length()){
    if(content.charAt(i)=='@'){
      i++;
      while (i<content.length()&& content.charAt(i)!=' ' && content.charAt(i)!='@' ){
        name=name+content.charAt(i);
        i++;
     }
      if(!taggedNames.contains(name))
        taggedNames.add(name);
      name="";
    }
    else
     i++;
  }
 }
 public String getStringOpCode() {
  return "05";
 }
 public short getOpCode(){
    return opcode;
 }

 public Vector<Integer> newPost(User postingUser) {
        if(postingUser!=null) {
            if (dataBase.canUserPost(postingUser.getUsername())) {
                postingUser.addNewPost(this);
                String msg = makeNotificationMessage(postingUser);
                Vector<User> followers = postingUser.getFollowersVector();
                ConcurrentHashMap<User, Integer> onlineUsers = dataBase.getOnlineUsers();
                Vector<Integer> messagesForUserById = new Vector<>(); //
                for (int i = 0; i < followers.size(); i++) {//adding message for the followers
                    User followUser = followers.get(i);
                    if (taggedNames.contains(followUser.getUsername()))
                        taggedNames.remove(followUser.getUsername());
                    if (onlineUsers.containsKey(followUser)) {
                        messagesForUserById.add(onlineUsers.get(followUser));
                    } else followUser.addWaitingNotfication(new Notification(msg));
                }
                for (int i = 0; i < taggedNames.size(); i++) {//adding message for the tagged users who not followers
                    User taggedUser = dataBase.getUser(taggedNames.get(i));
                    if (taggedUser != null) {
                        if (!postingUser.getBlocked().contains(taggedUser)) {
                            if (onlineUsers.containsKey(taggedUser)) {
                                messagesForUserById.add(onlineUsers.get(taggedUser));
                            } else taggedUser.addWaitingNotfication(new Notification(msg));
                        }
                    }
                }

                return messagesForUserById;
            }
        }
    return null;
 }
public String makeNotificationMessage(User postingUser){
   if(postingUser!=null) {
       String msg = "1" + postingUser.getUsername() + ' ' + content + ";";
       return msg;
   }
   return null;
}
}
