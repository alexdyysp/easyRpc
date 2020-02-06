package protocol.http;

import com.alibaba.fastjson.JSONObject;
import framework.Invocation;
import org.apache.commons.io.IOUtils;
import provider.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    // handler处理请求
    public void handler(HttpServletRequest req, HttpServletResponse resp){

        try{
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName(); // 取出接口名
            Class implClass = LocalRegister.get(interfaceName);  // 取出对应接口的实现类
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());

            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
            System.out.println("HttpServerHandler --> " + result );

            IOUtils.write(result, resp.getOutputStream());  // 返回结果

        } catch(IOException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
