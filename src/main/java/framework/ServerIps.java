package framework;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerIps {
    public static final List<String> LIST = Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5",
            "192.168.0.6",
            "192.168.0.7",
            "192.168.0.8",
            "192.168.0.9",
            "192.168.0.10"
    );

    public static final Map<String, Integer> WEIGHT_MAP = new LinkedHashMap<String, Integer>();

    static{
        WEIGHT_MAP.put("192.168.0.1", 4);
        WEIGHT_MAP.put("192.168.0.2", 3);
        WEIGHT_MAP.put("192.168.0.3", 2);
        WEIGHT_MAP.put("192.168.0.4", 6);
        WEIGHT_MAP.put("192.168.0.5", 9);
        WEIGHT_MAP.put("192.168.0.6", 8);
        WEIGHT_MAP.put("192.168.0.7", 1);
    }
}
