package Structure;

import java.util.ArrayList;

public class Profile {
    private String profileName;
    private int age;
    private ArrayList<Program> watchedPrograms;

    public Profile(String profileName, int age){
        this.profileName = profileName;
        this.age = age;
        this.watchedPrograms = new ArrayList<Program>();
    }

    public String getProfileName(){
        return this.profileName;
    }

    public int getAge(){
        return this.age;
    }

    public ArrayList<Program> getWatchedPrograms(){
        return this.watchedPrograms;
    }
}
