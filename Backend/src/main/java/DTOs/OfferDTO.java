package DTOs;

public class OfferDTO {

    private String title;
    private String state;
    private String description;
    private String date;
    private String email;
    private String relatedTitle;

    public OfferDTO() {

    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getRelatedTitle() {
        return relatedTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRelatedTitle(String relatedTitle) {
        this.relatedTitle = relatedTitle;
    }

    

}
