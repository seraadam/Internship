package internship.wadimakkah.com.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.datatype.Duration;

import internship.wadimakkah.com.dpObjects.Attendance;
import internship.wadimakkah.com.internship.R;


public class AttendanceFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;
    //----------shared prefrences variables

    public static final String MY_PREFS_NAME = "MyAttendanceFile";
    SharedPreferences sharedpreferences;

    //---------Declair firebase instance----------------

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseUser user;
    private DatabaseReference mDatabase;

    //--------- newInstance constructor for creating fragment with arguments
    public static AttendanceFragment newInstance(int page, String title) {
        AttendanceFragment fragmentFirst = new AttendanceFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        final Button checkin = (Button) view.findViewById(R.id.checkin);
        final Button checkout = (Button) view.findViewById(R.id.checkout);

        sharedpreferences = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        //-------------------get the date and time-----------
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addattendanceMethod(getarrivaltime(),getDate());
               checkin.setEnabled(false);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("day",getDate());
                editor.commit();

            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddepartureMethod(getDeparturetime());
                checkout.setEnabled(false);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("day",getDate());
                editor.putInt("flag",1);
                editor.commit();
            }
        });


        return view;
    }

    //---------------record arrival time------------------
    public void addattendanceMethod(String time, String Date) {

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String today = prefs.getString("day", null);
        if (today!= null && today.equals(getDate())){
            Toast.makeText(getActivity(), "you already checked in ", Toast.LENGTH_SHORT).show();
        }
        else{
            Firebase.setAndroidContext(getActivity().getApplicationContext());
            auth = FirebaseAuth.getInstance();

            final String attendtime = time;
            final String userID = auth.getCurrentUser().getUid();
            final String daydate = Date;
            final String Departuretime = "null";

            final Attendance atten = new Attendance(attendtime, daydate, Departuretime, userID, "7");

            //1- Declare the DB refrence :)
            mDatabase = FirebaseDatabase.getInstance().getReference();

            //2- generate a random ID :)
            String AttendanceID = mDatabase.child("Attendance").push().getKey();
            // save attendance key in shared prefrence

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("Attendance key",AttendanceID);
            editor.commit();

            // 3- set the Atendance object in the DB under the generated ID :)
            mDatabase.child("Attendance").child(AttendanceID).setValue(atten);

            //4- Checks if the addition is done successfully
            if (AttendanceID.isEmpty()) {
                Toast.makeText(getActivity(), "Sorry :( .. Please Try again", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Addition is Done Successfully :) 1", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //---------------record departure time------------------

    public void adddepartureMethod( String Dtime ){

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String today = prefs.getString("day", null);
        int flag = prefs.getInt("flag",0);
        if (today.equals(getDate()) && flag==1 ){
            Toast.makeText(getActivity(), "you already checked out ", Toast.LENGTH_SHORT).show();
        }else {

            Firebase.setAndroidContext(getActivity().getApplicationContext());
            mDatabase = FirebaseDatabase.getInstance().getReference();

            //------get attendance id from shared prefrence
            String restoredID = prefs.getString("Attendance key", null);
            if (restoredID != null) {
                Toast.makeText(getActivity(), "restoredID : " + restoredID, Toast.LENGTH_LONG).show();
            }
            //--------------update the departure data for the day

            final String Attendanceid = restoredID;
            final String departuretime = Dtime;
            mDatabase.child("Attendance").child(Attendanceid).child("departuretime").setValue(departuretime);
        }
    }


        String getarrivaltime(){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            String time=format.format(c.getTime());
            return time;
        }
        String getDeparturetime(){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            String time=format.format(c.getTime());
            return time;
        }

        String getDate(){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
            String date=format.format(c.getTime());
            return date;
        }


    String calculateWorkinghours(){
        //TODO:calculate working hours ber day and add them to the total working hours field in database
        return "";
    }

}
