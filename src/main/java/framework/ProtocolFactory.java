package framework;

import protocol.netty.NettyProtocol;
import protocol.http.HttpProtocol;

public class ProtocolFactory {
    // 工厂模式
    public static Protocol getProtocol(){
        String name = System.getProperty("protocolName");
        if(name == null || name.equals("")) {
            name = "http";
        }
        switch(name){
            case "http": return new HttpProtocol();
            case "netty": return new NettyProtocol();
            default: break;
        }
        return new HttpProtocol();
    }
}
