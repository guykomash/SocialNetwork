package bgu.spl.net.impl.echo.Messages;


public class Notification implements Message{

    private final short opcode=9;
 private String message;
 private boolean notficationType;
 private String postingUser;
 private String content;

 public Notification(String message){
  this.message ="09"+ message;
  this.postingUser="";
  content="";
  processMessage();
 }
 public String getMessage(){
  return this.message;
 }
 public void processMessage(){
      int index=0;
      if(message.charAt(index)=='1')
       notficationType=true;
      else if(message.charAt(index)=='0')
       notficationType=false;
      else System.out.println("NotficationType not valid"); //Delete it later!
      index++;
      while(index<message.length()){
       postingUser=postingUser+message.charAt(index);
       index++;
      }
      index++;
  while(index<message.length()){
   content=content+message.charAt(index);
   index++;
  }
 }
    @Override
    public short getOpCode() {
        return opcode;
    }

    public String getStringOpCode() {
        return "09";
    }

}
