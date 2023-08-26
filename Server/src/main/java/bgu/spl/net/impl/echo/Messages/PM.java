package bgu.spl.net.impl.echo.Messages;


import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

import java.util.ArrayList;

public class PM implements Message{

 private final short opcode=6;
 private String message;
 private String recipientUsername="";
 private String content="";
 private String Sendingdateandtime="";
 private DataBase dataBase=DataBase.getInstance();
 private ArrayList<String> filteredWords;

 public PM(String message,ArrayList<String> filter){
  this.filteredWords=filter;
  this.message = message;
  processMessage();
  filterWords();

 }
 public String getMessage(){
  return this.message;
 }

 public void processMessage(){
  int index=0;
  while(index<message.length() &&message.charAt(index)!=' ' ){
   recipientUsername=recipientUsername+message.charAt(index);
   index++;
  }
  index++;
  while(index<message.length()&&message.charAt(index)!='\0'){
   content=content+message.charAt(index);
   index++;
  }
  index++;
  while(index<message.length()){
   Sendingdateandtime=Sendingdateandtime+message.charAt(index);
   index++;
  }
 }
 public short getOpCode() {
  return opcode;
 }
 public String getStringOpCode() {
  return "06";
 }
 public Integer sendPM(User PMUser){
  if(PMUser!=null) {
   if (dataBase.canUserPM(PMUser.getUsername(), recipientUsername)) {
    PMUser.addNewPM(this);
    User recipient = dataBase.getUser(recipientUsername);
    if (recipient != null) {
     String msg = makeNotificationMessage(PMUser);
     if (dataBase.getOnlineUsers().containsKey(recipient))
      return dataBase.getConnectionId(recipient);
     recipient.addWaitingNotfication(new Notification(msg));
     return -1;
    }
   }
  }
 return null;
 }
  public String makeNotificationMessage(User PMUser){
   String msg="0"+PMUser.getUsername() +' '+ content + '\0'+Sendingdateandtime+";";
   return msg;
  }
  public void filterWords(){
    for(int i=0;i<filteredWords.size();i++){
      content=content.replaceAll(filteredWords.get(i),"filtered");
    }
  }

}