package internship.wadimakkah.com.Fragments;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import internship.wadimakkah.com.dpObjects.Announcement;
import internship.wadimakkah.com.internship.R;

public class AnnouncementadminFragment extends Fragment {

    //-----Store instance variables
    private String title;
    private int page;
    private RecyclerView recyclerView;
    private AnnounceCardAdapter adapter;
    private List<Announcement> announcelist;
    private OnFragmentInteractionListener mListener;
    private DatabaseReference mDatabase;

    public AnnouncementadminFragment() {
        // Required empty public constructor
    }

    //--------- newInstance constructor for creating fragment with arguments
    public static AnnouncementadminFragment newInstance(int page, String title) {
        AnnouncementadminFragment fragmentFirst = new AnnouncementadminFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //----------------Inflate the layout for this fragment--------------
        View rootview = inflater.inflate(R.layout.fragment_announcement, container, false);

        //---------------------define recyclerview---------------------------
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view);

        //-----------------create list for the announcements-----------------------
        announcelist = new ArrayList<>();

        //--------------define the customed adapter--------------------------
        adapter = new AnnounceCardAdapter(getActivity().getApplicationContext(), announcelist);

        //---------------the cardview design in the layout---------------------

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //--------setting the used data (i can replace it with the asynchronous method
        getAnnouncements();

        return rootview;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void getAnnouncements() {
        Firebase.setAndroidContext(getActivity().getApplicationContext());
        //Firebase ref1=new Firebase("https://startups-map-45b3d.firebaseio.com//Pin");

        for (int i = 0; i <= 20; i++) {
            String text = "greate announcement welcome to wadi makkah internship";
            Announcement a = new Announcement(getDate(), text, "programming");
            announcelist.add(a);
        }
    }

    private void addannouncementmethod(View view) {
        //-----------spinner--------------
        final Spinner spinner = (Spinner) view.findViewById(R.id.announce_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.department, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //------------start dialog----------------
        final Dialog dialog = new Dialog(getActivity(), R.style.CustomDialogTheme);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //-----------connecting with the right xml file--------
        dialog.setContentView(R.layout.announcementdialoglist);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.show();
        dialog.getWindow().setAttributes(lp);

        final EditText announcementmsg = (EditText) view.findViewById(R.id.announcementmsg);
        final Button sendbutton = (Button) view.findViewById(R.id.sendannouncement);

        //-----------send the data to the firebase -------------
        final String addannounce = announcementmsg.getText().toString();
        final String department = spinner.getSelectedItem().toString();
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase.setAndroidContext(getActivity().getApplicationContext());
                final Announcement ann = new Announcement(getDate(),addannounce,department);

                mDatabase = FirebaseDatabase.getInstance().getReference();
                String AnnounceID = mDatabase.child("Announcement").push().getKey();
                mDatabase.child("Announcement").child(AnnounceID).setValue(ann);

                if (AnnounceID.isEmpty()) {
                    Toast.makeText(getActivity(), "Sorry :( .. Please Try again", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.hide();
                }
            }
        });
    }

    String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        String date = format.format(c.getTime());
        return date;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
