package internship.wadimakkah.com.dpObjects;

/**
 * Created by mac on 5/24/17.
 */

public class User {
    private String username;
    private String password;
    private String authority;
    private String phone;
    private String mail;
    private String groupNo;

    public User(String groupNo)
    {
        this.groupNo= groupNo;
    }

    public User(String username, String password  ,String mail , String phone ) {
        this.password=password;
        this.username = username;
        this.authority = mail;
        this.mail = mail;
        this.phone= phone;

    }
    public String getUsername() {
        return username;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        if (authority == "sera.adam@gmail.com"){
            this.authority  = "admin";
        }else {
            this.authority  = "student";
        }

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
