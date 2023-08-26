package bgu.spl.net.api.bidi;

import bgu.spl.net.impl.echo.Messages.Message;
import bgu.spl.net.srv.ConnectionHandler;

import java.util.HashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionsClass<T> implements Connections<T>{

    private ConcurrentHashMap<Integer,ConnectionHandler<T>> clientsMap; //Concurrent??

    private static class  SingletonHolder {
        private static ConnectionsClass instance = new ConnectionsClass() ;
    }

    private ConnectionsClass(){
        clientsMap=new ConcurrentHashMap<>();
    }

    public static ConnectionsClass getInstance(){
        return SingletonHolder.instance;
    }

    public  boolean send(int connectionId, T msg){
        if(clientsMap.containsKey(connectionId)) {
            clientsMap.get(connectionId).send(msg);
            return true;
        }
        return false;
    }

    public void broadcast(T msg){
            for(Integer id : clientsMap.keySet()){
            ConnectionHandler<T> currentConnectionHandler = clientsMap.get(id);
            currentConnectionHandler.send(msg);

        }
    }

    public void disconnect(int connectionId){
        if(clientsMap.containsValue(connectionId))
            clientsMap.remove(connectionId);
    }

    public void registerClient(Integer connectionId,ConnectionHandler<T> handler){
        if(!clientsMap.containsKey(connectionId)){
            clientsMap.put(connectionId,handler);
        }
    }
}
