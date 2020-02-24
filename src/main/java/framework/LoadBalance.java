package framework;

import java.util.*;

public class LoadBalance {

    /*
     Random Select Balancing
     */
    public static URL getServerByRandom(List<URL> list){
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }

    /*
        Smooth Weighted Round-Robin Balancing
     */
    private static Map<String, Weight> weightMap = new LinkedHashMap<String, Weight>();

    public static String getServerBySmoothWeightedRoundRobin(){
        int totalWeight = 0;
        for(Integer weight: ServerIps.WEIGHT_MAP.values()){
            totalWeight += weight;
        }
        // Init
        if(weightMap.isEmpty()){
            for(String ip: ServerIps.WEIGHT_MAP.keySet()){
                Integer weight = ServerIps.WEIGHT_MAP.get(ip);

                weightMap.put(ip, new Weight(ip, weight, 0));
            }
        }

        //
        for(Weight weight: weightMap.values()){
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());

        }

        // max(currentWeight) -= sum(weight)
        Weight maxCurrentWeight = null;
        for(Weight weight: weightMap.values()){
            if(maxCurrentWeight == null || weight.getCurrentWeight() > maxCurrentWeight.getCurrentWeight()){
                maxCurrentWeight = weight;
            }
        }
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight()-totalWeight);

        return maxCurrentWeight.getIp();
    }

    /*
      Consistent Hashing
     */
    private static TreeMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();
    private static final int VIRTUAL_NODES = 160;

    static {
        for (String ip: ServerIps.LIST){
            for(int i=0; i<VIRTUAL_NODES; i++){
                int hash = getHash(ip+i);
                virtualNodes.put(hash, ip);
            }
        }
    }

    private static int getHash(String str){
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for(int i=0; i<str.length(); i++){
            hash = (hash ^ str.charAt(i)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash ^= hash << 5;

        if(hash<0) hash = Math.abs(hash);

        return hash;
    }

    private static String getServerByConsistentHashing(String client){
        int hash = getHash(client);

        SortedMap<Integer, String> sortedMap = virtualNodes.tailMap(hash);

        Integer firstKey = null;
        if(sortedMap == null){
            firstKey = virtualNodes.firstKey();
        }else
            firstKey = sortedMap.firstKey();

        return virtualNodes.get(firstKey);
    }

}
