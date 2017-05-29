package internship.wadimakkah.com.dpObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 5/25/17.
 */

public class Attendance {

    private String userId;
    private String workingHours;
    public String arrivaltime;
    public String departuretime;
    public String day;

    public Attendance() {

    }

    public Attendance(String arrivaltime, String day, String departuretime, String userId, String workingHoours) {
        this.arrivaltime = arrivaltime;
        this.departuretime = departuretime;
        this.userId = userId;
        this.day=day;
        this.workingHours = workingHoours;
    }


    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHoours) {
        this.workingHours = workingHoours;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




}
