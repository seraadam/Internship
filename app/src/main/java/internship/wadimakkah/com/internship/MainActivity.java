package internship.wadimakkah.com.internship;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import github.chenupt.springindicator.SpringIndicator;
import internship.wadimakkah.com.Fragments.PagerAdapter;

public class MainActivity extends AppCompatActivity  {
    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ViewPager myviewPager = (ViewPager) findViewById(R.id.pager);

        SpringIndicator springIndicator = (SpringIndicator)findViewById(R.id.indicator);

        myToolbar.setTitleTextColor(0xFFFFFFFF);

        PagerAdapter adapterpager = new PagerAdapter(getSupportFragmentManager(), 5 );
        myviewPager.setAdapter(adapterpager);

        springIndicator.setViewPager(myviewPager);
        myviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                myToolbar.setTitle(titles[position]);
            }

            @Override
            public void onPageSelected(int position) {
                myToolbar.setTitle(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    private String[] titles = {
            "Attendance",
            "Rules",
            "Announcments",
            "Help",
            "Profile",
            "Students",
            "Add",
            "Error!"
    };



    //-----------signout method if signout method in the Setting clicked :)
    public void signoutMethod(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, SigninActivity.class));
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.menu_main_signout);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
       // s.setSpan(new ForegroundColorSpan(getResources("")), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.menu_main_signout:
                try {
                    signoutMethod();

                } catch (android.content.ActivityNotFoundException anfe) {
                   Toast.makeText(this,"There is an error try again" ,Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
