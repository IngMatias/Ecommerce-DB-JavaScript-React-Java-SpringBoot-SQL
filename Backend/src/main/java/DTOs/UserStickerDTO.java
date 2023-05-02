package DTOs;

public class UserStickerDTO {

    private int quantity;
    private String email;
    private String country;
    private String number;

    public UserStickerDTO() {

    }

    public int getQuantity() {
        return quantity;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getNumber() {
        return number;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    

}
