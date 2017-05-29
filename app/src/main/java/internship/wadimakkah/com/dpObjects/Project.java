package internship.wadimakkah.com.dpObjects;

/**
 * Created by mac on 5/25/17.
 */

public class Project {
    private String title;
    private String user_ID;
    private String programmingL;
    private String group;
    private String trello;
    private String bitbucket;

    public Project(String bitbucket, String user_ID, String group, String programmingL, String title, String trello) {
        this.bitbucket = bitbucket;
        this.user_ID = user_ID;
        this.group = group;
        this.programmingL = programmingL;
        this.title = title;
        this.trello = trello;
    }

    public String getBitbucket() {
        return bitbucket;
    }

    public void setBitbucket(String bitbucket) {
        this.bitbucket = bitbucket;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProgrammingL() {
        return programmingL;
    }

    public void setProgrammingL(String programmingL) {
        this.programmingL = programmingL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrello() {
        return trello;
    }

    public void setTrello(String trello) {
        this.trello = trello;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }
}
