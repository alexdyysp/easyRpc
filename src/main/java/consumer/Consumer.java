package consumer;

import framework.ProxyFactory;
import provider.api.HelloService;

public class Consumer {
    // 调用服务
    public static void main(String[] args){

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("dyy");

        System.out.println(result);

    }
}
