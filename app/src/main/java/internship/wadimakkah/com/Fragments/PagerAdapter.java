package internship.wadimakkah.com.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by mac on 5/25/17.
 */

public class PagerAdapter extends FragmentPagerAdapter {
   //TODO : for custom indicator icon title
    //private Context context;
    private static int NUM_ITEMS;


    public PagerAdapter(FragmentManager fm ,int NUM_ITEMS ) {
        super(fm);
        this.NUM_ITEMS= NUM_ITEMS;
        //TODO :for custom indicator icon title

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                AttendanceFragment attFragment = new AttendanceFragment();
                return attFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                AttendanceFragment atFragment = new AttendanceFragment();
                return atFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                AttendanceFragment atrFragment = new AttendanceFragment();
                return atrFragment.newInstance(2, "Page # 3");
            case 3: // Fragment # 0 - This will show FirstFragment
                AttendanceFragment tFragment = new AttendanceFragment();
                return tFragment.newInstance(3, "Page # 4");
            case 4: // Fragment # 0 - This will show FirstFragment different title
                AttendanceFragment ttFragment = new AttendanceFragment();
                return ttFragment.newInstance(4, "Page # 5");
            case 5: // Fragment # 1 - This will show SecondFragment
                AttendanceFragment rFragment = new AttendanceFragment();
                return rFragment.newInstance(5, "Page # 6");
            case 6: // Fragment # 0 - This will show FirstFragment
                AttendanceFragment tFagment = new AttendanceFragment();
                return tFagment.newInstance(6, "Page # 7");

            default:
                AttendanceFragment atrFragmet = new AttendanceFragment();
                return atrFragmet.newInstance(2, "Page # 3");
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
         switch (position){
             case 0 :
                 return "1";
             case 1:
                 return "2";

             case 2:
                 return "3";
             case 3:
                 return "4";

             case 4:
                 return "5";

             case 5:
                 return "6";

             case 6:
                 return "7";


             default:
                 return "Error!";
         }

    }

//TODO : i will contact the developer to change the settings
   /* private int[] imageResId = {
            R.drawable.common_google_signin_btn_icon_dark_normal_background,
            R.drawable.common_full_open_on_phone,
            R.drawable.common_google_signin_btn_icon_dark_normal_background
    };
    
    @Override
    public CharSequence getPageTitle(int position) {

        Drawable image = context.getResources().getDrawable(imageResId[position]);

        image.setBounds(5, 5, image.getIntrinsicWidth(), image.getIntrinsicHeight());

        SpannableString sb = new SpannableString("");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }
*/


}
