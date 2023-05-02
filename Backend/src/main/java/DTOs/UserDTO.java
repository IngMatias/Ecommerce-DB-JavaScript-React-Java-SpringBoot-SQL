package DTOs;

public class UserDTO {

    private String email;
    private String password;
    private String name;
    private String phone;
    private String ubication;

    public UserDTO() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUbication() {
        return ubication;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

}
