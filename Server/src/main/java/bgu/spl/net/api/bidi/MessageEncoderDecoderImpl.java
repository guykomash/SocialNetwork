package bgu.spl.net.api.bidi;


import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.impl.echo.Messages.*;
import bgu.spl.net.impl.echo.Messages.Error;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;


public class MessageEncoderDecoderImpl implements MessageEncoderDecoder<Message> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private boolean flag = false;
    private ArrayList<String> filteredWords;
    public MessageEncoderDecoderImpl(ArrayList<String> filteredWords){
        this.filteredWords=filteredWords;
    }

    public Message decodeNextByte(byte nextByte) {
        if (nextByte == ';' && !flag) {
            flag=true;
            String msg= popString();
            return decodeToMessage(msg);
        }
        if(flag){
            pushByte(nextByte);
            popString();
            flag=false;
        }
        else
        pushByte(nextByte); //not a line yet
        return null;
        }
    public byte[] encode(Message message) {
            return (message.getMessage()).getBytes(StandardCharsets.UTF_8);

    }
    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
        }
    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
        bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
        }
        private Message decodeToMessage(String msg) {
            String opcode = "";
            if(msg!=null && msg!=";" &&msg!="\0"){
                opcode = opcode + msg.charAt(0);
                opcode = opcode + msg.charAt(1);}
                switch (opcode) {
                    case "01":
                        return new Register(msg.substring(2, msg.length() - 1));
                    case "02":
                        return new Login(msg.substring(2, msg.length()));
                    case "03":
                        return new Logout();
                    case "04":
                        return new FollowUnfollow(msg.substring(2, msg.length()));
                    case "05":
                        return new Post(msg.substring(2, msg.length()));
                    case "06":
                        return new PM(msg.substring(2, msg.length()),filteredWords);
                    case "07":
                        return new Logstat();
                    case "08":
                        return new Stats(msg.substring(2, msg.length()));
                    case "09":
                        return new Notification(msg.substring(2, msg.length() - 1));
                    case "12":
                        return new Block(msg.substring(2, msg.length()));
                }
            return null;
        }
//        public void cleanMessage(String message){
//            int i=0;
//            i<
//        }
}
