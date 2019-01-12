package Structure;

import java.util.ArrayList;

public class Program {
    private int id;
    private String title;
    private int duration;
    private ArrayList<Profile> watchedByProfiles;

    public Program(int id, String title, int duration){
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public int getDuration(){
        return this.duration;
    }

    public ArrayList<Profile> getWatchedByProfiles(){
        return this.watchedByProfiles;
    }
}
