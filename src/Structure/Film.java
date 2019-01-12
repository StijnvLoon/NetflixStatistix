package Structure;

public class Film extends Program{

    private String category;
    private String language;
    private int ageRestriction;

    public Film(int id, String title, int duration, String category, String language, int ageRestriction) {
        super(id, title, duration);
        this.category = category;
        this.language = language;
        this.ageRestriction = ageRestriction;
    }

    public String getCategory() {
        return this.category;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
