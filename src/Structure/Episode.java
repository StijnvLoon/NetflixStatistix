package Structure;

import java.util.ArrayList;

// Deze class is er om gegevens van afleveringen op te slaan en op te vragen
public class Episode extends Program{

    private int episodeNumber;
    private ArrayList<Integer> watchedDurations = new ArrayList<Integer>();

    // Hier wordt het object gemaakt
    public Episode(int id, String title, int duration, int episodeNumber) {
        super(id, title, duration);
        this.episodeNumber = episodeNumber;
    }

    // Hier wordt een duration doorgegeven aan de lijst met durations
    public void addWatchedDuration(int duration){
        this.watchedDurations.add(duration);
    }

    // Hier wordt de lijst met durations opgevraagd, vervolgens wordt de totale duration berekend, daarna de gemiddelde duration, en uiteindelijk
    // wordt berekend hoeveel procent er gemiddeld bekeken werd en dat wordt teruggestuurd
    public double getAverageWatchedDurationPercentage(){
        if (watchedDurations.isEmpty()){
            return 0;
        }
        double totalDuration = 0;
        for (int duration : this.watchedDurations){
            totalDuration += duration;
        }
        return ((totalDuration / this.watchedDurations.size()) / super.getDuration()) * 100;
    }

    // Hier wordt het volgnummer teruggestuurd
    public int getEpisodeNumber() {
        return episodeNumber;
    }

}
