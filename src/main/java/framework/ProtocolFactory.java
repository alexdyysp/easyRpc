package framework;

import protocol.netty.NettyProtocol;
import protocol.http.HttpProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ProtocolFactory {

    public static Protocol getProtocol(){
        // Java spi
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();
    }


// 工厂模式
//    public static Protocol getProtocol(){
//        String name = System.getProperty("protocolName");
//        if(name == null || name.equals("")) {
//            name = "http";
//        }
//        switch(name){
//            case "http": return new HttpProtocol();
//            case "netty": return new NettyProtocol();
//            default: break;
//        }
//        return new HttpProtocol();
//    }
}
