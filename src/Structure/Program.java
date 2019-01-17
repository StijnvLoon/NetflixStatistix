package Structure;
// Deze class is er om gegevens van programs op te slaan en op te halen
public class Program {
    private int id;
    private String title;
    private int duration;

    // Hier wordt het object aangemaakt
    public Program(int id, String title, int duration){
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    // Hier wordt de titel teruggegeven
    public String getTitle(){
        return this.title;
    }

    // Hier wordt de duration teruggegeven
    public int getDuration(){
        return this.duration;
    }
}
