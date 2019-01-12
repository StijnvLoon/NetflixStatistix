package Structure;

public class Episode extends Program{

    private int episodeNumber;
    private Serie serie;

    public Episode(int id, String title, int duration, int episodeNumber, Serie serie) {
        super(id, title, duration);
        this.episodeNumber = episodeNumber;
        this.serie = serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public Serie getSerie() {
        return serie;
    }
}
