package register;

import framework.URL;

import java.io.*;
import java.util.*;

public class RemoteMapRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    // 注册一个服务器地址
    public static void regist(String interfaceName, URL url) {
        //List<URL> urls = Collections.singletonList(url);
        List<URL> urls = REGISTER.get(interfaceName);
        if(urls == null){
            urls = new ArrayList<>();
        }
        urls.add(url);
        REGISTER.put(interfaceName, urls);
        System.out.println(REGISTER);
        saveFile();
    }

    public static List<URL> get(String interfaceName){
        Map<String, List<URL>> tmp =  getFile();
        //System.out.println("REGister");
        //System.out.println(tmp);
        return tmp.get(interfaceName);
    }

    public static URL getRandom(String interfaceName){
        List<URL> urls = RemoteMapRegister.get(interfaceName);
        int i = new Random().nextInt(urls.size());
        return urls.get(i);
    }

    private static void saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream("/temp.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(REGISTER);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile(){
        try {
            FileInputStream fis = new FileInputStream("/temp.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, List<URL>> map = (Map<String, List<URL>>) ois.readObject();
            ois.close();
            fis.close();
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
