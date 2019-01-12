package Structure;

import java.util.ArrayList;
import java.util.List;

public class Serie {

    private String title;
    private String category;
    private String language;
    private int ageRestriction;
    private ArrayList<Episode> episodes;

    public Serie(String title, String category, String language, int ageRestriction) {
        this.title = title;
        this.category = category;
        this.language = language;
        this.ageRestriction = ageRestriction;
        this.episodes = new ArrayList<Episode>();
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    public ArrayList<Episode> getEpisodes() {
        return this.episodes;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getAgeRestriction() {
        return this.ageRestriction;
    }
}
