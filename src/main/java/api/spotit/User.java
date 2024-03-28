package api.spotit;

public class User {
    private String email;
    private String first_name;

    private String last_name;
    private String password;
    private String re_password;

    public User(String email, String first_name, String last_name, String password, String re_password) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.re_password = re_password;
    }

    public User() {
    }
    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getRe_password() {
        return re_password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }
}