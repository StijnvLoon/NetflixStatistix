package Structure;

import java.util.ArrayList;

public class Episode extends Program{

    private int episodeNumber;
    private ArrayList<Integer> watchedDurations = new ArrayList<Integer>();

    public Episode(int id, String title, int duration, int episodeNumber) {
        super(id, title, duration);
        this.episodeNumber = episodeNumber;
    }

    public void addWatchedDuration(int duration){
        this.watchedDurations.add(duration);
    }

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

    public int getEpisodeNumber() {
        return episodeNumber;
    }

}
