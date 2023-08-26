package bgu.spl.net.impl.echo.Messages;


public class Error implements Message {
 private final short opcode=11;
 private String message;
 private short userOpCode;

 public Error(Message msg){
  userOpCode = msg.getOpCode();
  processMessage(userOpCode,msg);
 // this.message=opcode+message.getStringOpCode();
 }

 public void processMessage(short userOpCode,Message msg){
  //Change it Later
  if (userOpCode == 1) {
   message = opcode +msg.getStringOpCode()+";";
  } else if (userOpCode == 2) {
   message = opcode + msg.getStringOpCode()+";";
  } else if (userOpCode == 3) {
   message = opcode + msg.getStringOpCode()+";";
  }
  else if(userOpCode==4){
   message = opcode + msg.getStringOpCode()+";";
  }
  else if (userOpCode == 5) {
   message = opcode + msg.getStringOpCode()+";";
  } else if (userOpCode == 6) {
   message = opcode + msg.getStringOpCode()+";";
  } else if (userOpCode == 7) {
   message = opcode + msg.getStringOpCode()+";";
  }
  else if(userOpCode==8){
   message = opcode + msg.getStringOpCode()+";";
  }
  else if(userOpCode==12)
   message=opcode+msg.getStringOpCode()+";";
 }

  public String getMessage(){  ///SIM LEV
    return this.message;}
 public String getStringOpCode(){
  return "11";
 }
 @Override
 public short getOpCode() {
  return opcode;
 }
}
