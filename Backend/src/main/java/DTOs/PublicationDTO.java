package DTOs;

public class PublicationDTO {

    private String title;
    private String description;
    private String date;
    private String photo;
    private String condition;
    private String email;

    public PublicationDTO() {

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCondition() {
        return condition;
    }

    public String getEmail() {
        return email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
