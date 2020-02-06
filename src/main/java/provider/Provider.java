package provider;

import framework.Protocol;
import framework.ProtocolFactory;
import framework.URL;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.RemoteMapRegister;

public class Provider {
    public static void main(String[] args){
        /* 1. remote register
         * {接口名：[Provider URL]}
        */
        RemoteMapRegister.regist(HelloService.class.getName(), new URL("localhost", 8080));
        /* 2. local register
         * {接口名：实现类}
        */
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        // 3. start Tomcat / Netty
        //HttpServer httpServer = new HttpServer();
        //httpServer.start("localhost", 8080);
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(new URL("localhost", 8080));
    }
}
