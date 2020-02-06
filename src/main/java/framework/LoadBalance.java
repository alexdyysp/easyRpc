package framework;

import java.util.List;
import java.util.Random;

public class LoadBalance {
    public static URL getrandom(List<URL> list){
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }
}
