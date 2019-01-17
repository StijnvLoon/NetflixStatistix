package Structure;

// Deze class is er om gegevens van subscripties op te slaan en op te vragen
public class Subscription {
    private String name;
    private String address;

    // Hier wordt het object gemaakt
    public Subscription(String name, String address){
        this.name = name;
        this.address = address;
    }

    // Hier wordt de naam teruggegeven
    public String getName(){
        return this.name;
    }

    // Hier wordt het adres teruggegeven
    public String getAddress(){
        return this.address;
    }
}
