package bgu.spl.net.impl.echo.Messages;


import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

import java.awt.*;
import java.util.Vector;

public class Stats implements Message {
 private final short opcode=8;
 private String message;
 private Vector<String> listofUsernames;
 private DataBase dataBase=DataBase.getInstance();

 public Stats(String message) {
  this.message = message;
  listofUsernames = new Vector<String>();
  processMessage();
 }

 public String getMessage() {
  return this.message;
 }

 public void processMessage() {
  int index = 0;
  String user = "";
  while (index < message.length()) {
   while (index < message.length()&&message.charAt(index) != '|') {
    user = user + message.charAt(index);
    index++;
   }
   listofUsernames.add(user);
   user="";
   index++;
  }
 }
 public boolean isListValid(User user){
   for(int i=0;i<listofUsernames.size();i++) {
    User userForInfo=dataBase.getUser(listofUsernames.get(i));
     if(userForInfo==null)
       return false;
     if(user.isUserBlocked(userForInfo))
      return false;
   }
   return true;
 }
 public Vector<Ack> getStats(){
  Vector<Ack> stats=new Vector<Ack>();
  for(int i=0;i<listofUsernames.size();i++){
    if(dataBase.getUser(listofUsernames.get(i))!=null)
     stats.add(makeStatAck(dataBase.getUser(listofUsernames.get(i))));
  }
  return stats;
 }

 public short getOpCode(){
  return opcode;
 }
 public String getStringOpCode(){
  return "08";
 }
 public Ack makeStatAck(User user){
  return new Ack("10"+"08"+user.toStringAge()+" "+user.toStringNumOfPosts()+" "+user.toStringNumOfFollowers()+" "+user.toStringNumOfFollowing());

 }
}
