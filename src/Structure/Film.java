package Structure;

public class Film extends Program{

    private String category;
    private String language;
    private int ageRestriction;

    public Film(String category, String language, int ageRestriction) {
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
