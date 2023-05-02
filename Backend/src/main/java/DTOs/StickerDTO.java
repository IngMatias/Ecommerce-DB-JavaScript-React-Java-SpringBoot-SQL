package DTOs;

public class StickerDTO {

    private String player;
    private String country;
    private String number;
    private String photo;

    public StickerDTO() {

    }

    public String getPlayer() {
        return player;
    }

    public String getCountry() {
        return country;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
