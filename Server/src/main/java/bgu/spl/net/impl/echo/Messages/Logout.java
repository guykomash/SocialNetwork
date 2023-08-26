package bgu.spl.net.impl.echo.Messages;


import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

public class Logout implements Message {
  private final short opcode=3;
 private String message;
 private DataBase dataBase = DataBase.getInstance();

 public Logout() {
  this.message="";
 }

 public String getMessage() {
  return this.message;
 }
 public short getOpCode() {
  return opcode;
 }
 public String getStringOpCode() {
  return "03";
 }


 public Message logoutUser(User user,int connectionId) {
    if(user!=null){
     if(dataBase.logoutUser(user.getUsername(),connectionId)){
        return  new Ack(this);}
    }
     return new Error(this);
  }
}
