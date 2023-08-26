package bgu.spl.net.impl.echo.Messages;


import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

public class Block implements Message{

  private String message;
  private String toBlockuserName="";
  private final short opcode=12;
    private DataBase dataBase = DataBase.getInstance();



  public Block(String message){
  this.message = message;
  processMessage();
 }
  public String getMessage(){
  return this.message;
 }
  public void processMessage(){
        int i=0;
           while(i<message.length()){
                toBlockuserName = toBlockuserName + message.charAt(i);
                i++;
            }
        }
 public String getUserName(){
      return toBlockuserName;
 }
    public String getStringOpCode(){
        return "12";
    }

    @Override
    public short getOpCode() {
        return opcode;
    }
    public Message blockUsers(User blockingUser){
      if(blockingUser!=null) {
          if (dataBase.blockUsers(blockingUser.getUsername(), toBlockuserName)) {
              return new Ack(this);
          }
      }
      return new Error(this);
    }

}
