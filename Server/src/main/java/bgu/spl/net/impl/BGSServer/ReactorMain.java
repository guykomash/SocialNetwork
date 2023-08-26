package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocolImpl;
import bgu.spl.net.api.bidi.MessageEncoderDecoderImpl;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Reactor;
import bgu.spl.net.srv.TPCServer;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ReactorMain {
    public static void main(String[]args){
    ArrayList<String> filteredWord=new ArrayList<>();
        filteredWord.add("Hi");
        filteredWord.add("Hello");
        filteredWord.add("Bye");
        filteredWord.add("Yes");
        filteredWord.add("No");
    String port_arg=args[0];
    String numThreads_arg = args[1];
    int portInt= Integer.parseInt(port_arg);
    int numThreads = Integer.parseInt(numThreads_arg);
    Reactor reactor=new Reactor(numThreads,portInt, (Supplier<BidiMessagingProtocol>) () -> new BidiMessagingProtocolImpl(), (Supplier<MessageEncoderDecoder>) () -> new MessageEncoderDecoderImpl(filteredWord));
        reactor.serve();
        }
    }

