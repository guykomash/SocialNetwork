package bgu.spl.net.impl.echo.Messages;


import bgu.spl.net.srv.DataBase;

public class Register implements Message {

    private final short opcode=1;
    private String message;
    private String Username;
    private String Password;
    private String Birthday;
    private DataBase dataBase=DataBase.getInstance();
    private int connectionId;

    public Register(String message){

     this.message = message;
     Username="";
     Password="";
     Birthday="";
     processMessage();
    }
    public String getMessage(){
     return this.message;
    }
    public void processMessage() {
        int index = 0;
        while (index < message.length()&& message.charAt(index) != ' ' ) {
            Username = Username + message.charAt(index);
            index++;
        }
        index++;
        while ( index < message.length() &&message.charAt(index) != ' ' ) {
            Password = Password + message.charAt(index);
            index++;
        }
        index++;
        while (index < message.length() && message.charAt(index) != ' '  ) {
            Birthday = Birthday + message.charAt(index);
            index++;
        }
    }
    public Message createUser(){
      if(dataBase.addUser(Username,Password,Birthday))
          return new Ack(this);
      else return new Error(this);
    }
    @Override
    public short getOpCode() {
       return opcode;
    }
    public String getStringOpCode() {
        return "01";
    }
    public String getUserName(){
        return Username;
    }
}
