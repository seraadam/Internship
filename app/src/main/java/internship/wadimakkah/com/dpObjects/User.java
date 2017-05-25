package internship.wadimakkah.com.dpObjects;

/**
 * Created by mac on 5/24/17.
 */

public class User {
    private String username;
    private String password;
    private String authority;


    public User() {

    }
    public User(String username, String password , String authority) {
        this.password=password;
        this.username = username;
        this.authority = authority;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        if (authority=="sera.adam@gmail.com"){
            this.authority  = "admin";
        }else {
            this.authority  = "student";
        }

    }
}
