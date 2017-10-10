package internship.wadimakkah.com.dpObjects;

/**
 * Created by mac on 5/29/17.
 */
public class Announcement {

    private String date;
    private String department;
    private String text;

    public Announcement( String date, String department ,String text) {
        this.text = text;
        this.date = date;
        this.department = department;
    }

    public Announcement() {
    }

    public String getannounce() {
        return text;
    }

    public void setannounce(String addannounce) {
        this.text = text;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
