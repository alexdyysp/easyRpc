package framework;

import protocol.http.HttpClient;
import register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    // 生成代理对象
    public static <T> T getProxy(Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Protocol protocol = ProtocolFactory.getProtocol();
                // 构造Invocation
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                URL url = LoadBalance.getServerByRandom(urlList);

                String result = protocol.send(url, invocation);
                return result;
            }
        });
    }
}
