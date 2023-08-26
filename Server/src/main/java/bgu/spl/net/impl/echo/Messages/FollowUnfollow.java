package bgu.spl.net.impl.echo.Messages;


import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

public class FollowUnfollow implements Message {

  private String message;
  private boolean follow=false;
  private String Username;
  private String userToFollowName="";
  private final short opcode=4;
    private DataBase dataBase = DataBase.getInstance();
  public FollowUnfollow(String message){
      this.message = message;
      processMessage();
 }

  public String getMessage(){
  return this.message;
 }
  public void processMessage(){
   if(message.charAt(0)=='0')
    follow=true;
    int i=1;
    while (i<message.length()){
        userToFollowName=userToFollowName+message.charAt(i);
     i++;
     }
   }
   public String getUsertoFollowName(){
    return userToFollowName;
   }

   public String getFollowCodeTypeString(){
      if (follow)
          return "0";
      return "1";
   }
   public boolean isFollow(){
    return follow;
   }

    public short getOpCode(){
        return opcode;
    }
    public String getStringOpCode(){
        return "04";
    }

    public Message FollowunFollow(User user) {
        boolean result = false;
        if (user != null) {
            if (follow) {
                result = dataBase.followUser(user.getUsername(), userToFollowName);
            } else result = dataBase.unfollowUser(user.getUsername(), userToFollowName);
            if (result) {
                return new Ack(this);
            }
        }
            return new Error(this);
    }
}
