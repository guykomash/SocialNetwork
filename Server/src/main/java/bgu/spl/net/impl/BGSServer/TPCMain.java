package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocolImpl;
import bgu.spl.net.api.bidi.MessageEncoderDecoderImpl;
import bgu.spl.net.srv.Server;
import bgu.spl.net.srv.TPCServer;


import java.util.ArrayList;
import java.util.function.Supplier;

public class TPCMain {
    public static void main(String []args){
        ArrayList<String> filteredWord = new ArrayList<>();
        filteredWord.add("Hi");
        filteredWord.add("Hello");
        filteredWord.add("Bye");
        filteredWord.add("Yes");
        filteredWord.add("No");
        String port=args[0];
        int portInt= Integer.parseInt(port);


        TPCServer server=new TPCServer(portInt, (Supplier<BidiMessagingProtocol>) () -> new BidiMessagingProtocolImpl(), (Supplier<MessageEncoderDecoder>) () -> new MessageEncoderDecoderImpl(filteredWord));
        server.serve();
    }
}
