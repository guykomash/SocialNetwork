package bgu.spl.net.impl.echo.Messages;


public class Ack implements Message {
 private String message="";
 private final short opcode=10;
 private short msgOpCode;

 public Ack(Message msg) {
  msgOpCode = msg.getOpCode();
  processMessage(msgOpCode,msg);
 }
 public Ack(String message){
  this.message=message+";";
 }
 public String getMessage(){
  return this.message;
 }

 public void processMessage(short msgOpCode,Message msg){
  switch (msgOpCode){
   case 1:
     message = "10" +msg.getStringOpCode()+";";
    break;
   case 2:
    message = "10" + msg.getStringOpCode()+";";
    break;
   case 3:
    message = "10" + msg.getStringOpCode()+";";
    break;
   case 4:
    message = "10" + msg.getStringOpCode()+((FollowUnfollow) msg).getFollowCodeTypeString()+((FollowUnfollow) msg).getUsertoFollowName()+";";
    break;
   case 5:
    message = "10" + msg.getStringOpCode()+";";
    break;
   case 6 :
    message = opcode + msg.getStringOpCode()+";";
    break;
   case 7:
    message = opcode + msg.getStringOpCode()+";";
    break;// this case should never happen because Logstat only uses String Constructor
   case 8:
    message = opcode + msg.getStringOpCode();
    break;// this case should never happen because Stat only uses String Constructor
   case 12:
    message = opcode+msg.getStringOpCode()+";";
    break;
   }
 }
 public short getOpcode(){
  return opcode;
 }
 public short getUserOpCode() {
  return msgOpCode;
 }
 public short getOpCode() {
  return opcode;
 }
 public String getStringOpCode(){
  return "10";
 }
 }

