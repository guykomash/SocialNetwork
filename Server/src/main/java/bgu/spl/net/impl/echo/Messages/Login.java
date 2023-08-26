package bgu.spl.net.impl.echo.Messages;

import bgu.spl.net.srv.DataBase;

public class Login implements Message {
    private int connectionId;
    private String message;
    private String Username = "";
    private String Password = "";
    private int captcha;
    private final short opcode = 2;
    private DataBase dataBase = DataBase.getInstance();
    public Login(String message) {
        this.message = message;
        processMessage();
    }
    public String getMessage() {
        return this.message;
    }

    public void processMessage() {
        boolean end = false;
        int i = 0;
        int stage = 0;
        while (!end) {
            if (message.charAt(i) != ' ') {
                if (stage == 0)
                    Username = Username + message.charAt(i);
                if (stage == 1)
                    Password = Password + message.charAt(i);
                if (stage == 2) {
                    if (message.charAt(i) == '1')
                        captcha = 1;
                    else
                        captcha = 0;
                    end = true;
                }
                i++;
                } else {
                    i++;
                    stage++;
                }
            }
        }

    public int getCaptcha() {
        return captcha;
    }
    public String getUserName() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
    @Override
    public short getOpCode() {
        return opcode;
    }
    public String getStringOpCode() {
        return "02";
    }
    public Message loginUser(int connectionId) {
        if (captcha == 1) {
            if (dataBase.loginUser(Username, Password, connectionId))
                return new Ack(this);
        }
       return new Error(this);
    }
}
