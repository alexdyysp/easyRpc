package framework;

public class Weight {
    private String ip;
    private Integer weight;
    private Integer currentWeight;

    public Weight(String ip, Integer weight, Integer currentWeight){
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }

    public String getIp(){
        return ip;
    }

    public Integer getWeight(){
        return weight;
    }

    public Integer getCurrentWeight(){
        return currentWeight;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public void setWeight(Integer weight){
        this.weight = weight;
    }

    public void setCurrentWeight(Integer currentWeight){
        this.currentWeight = currentWeight;
    }
}
