package bgu.spl.net.srv;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.BidiMessagingProtocolImpl;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.ConnectionsClass;

import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class TPCServer<T> extends BaseServer<T> {
    private AtomicInteger connectionId=new AtomicInteger();
    public TPCServer(int port, Supplier<BidiMessagingProtocol<T>> protocolFactory, Supplier<MessageEncoderDecoder<T>> encdecFactory) {
        super(port, protocolFactory, encdecFactory);
        connectionId.set(0);
    }
    protected void execute(BlockingConnectionHandler<T> handler) {
        BidiMessagingProtocol protocol=handler.getProtocol();
        connectionId.incrementAndGet();
        protocol.start(connectionId.get(), ConnectionsClass.getInstance());
        ConnectionsClass.getInstance().registerClient(connectionId.get(),handler);
        new Thread(handler).start();
    }
}