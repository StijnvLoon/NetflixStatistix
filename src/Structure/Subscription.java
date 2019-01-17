package Structure;

public class Subscription {
    private String name;
    private String address;

    public Subscription(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }
}
