package Structure;

import java.util.ArrayList;

public class Subscription {
    private String name;
    private String address;
    private ArrayList<Profile> profiles;

    public Subscription(String name, String address){
        this.name = name;
        this.address = address;
        this.profiles = new ArrayList<Profile>();
    }

    public String getName(){
        return this.name;
    }

    public String getAaddress(){
        return this.address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
