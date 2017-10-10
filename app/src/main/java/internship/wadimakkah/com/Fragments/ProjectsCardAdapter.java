
package internship.wadimakkah.com.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import internship.wadimakkah.com.dpObjects.Announcement;
import internship.wadimakkah.com.internship.R;

 /*
public class ProjectsCardAdapter extends RecyclerView.Adapter<ProjectsCardAdapter.MyViewHolder>
    {
       /* private Context mContext;
        private List<Announcement> announcelist;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView date, announce_text;
            //public ImageView announcementimage;

            public MyViewHolder(View view) {
                super(view);
                //poster_path = (ImageView) view.findViewById(R.id.thumbnail);
                announce_text = (TextView) view.findViewById(R.id.announcementtext);
                date  = (TextView) view.findViewById(R.id.dateandtimetext);
            }
        }

        public ProjectsCardAdapter(Context mContext, List<Announcement> announcelist) {
            this.mContext = mContext;
            this.announcelist = announcelist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_card,parent,false);
            return new MyViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ProjectsCardAdapter.MyViewHolder holder, int position) {

            final Announcement announce = announcelist.get(position);
            holder.date.setText(announce.getDate());
            holder.announce_text.setText(announce.getText());

            holder.announce_text.setTextColor(Color.parseColor("#607D8B"));
            holder.date.setTextColor(Color.parseColor("#F06292"));

        }

        @Override
        public int getItemCount() {
            return announcelist.size();
        }

    }

*/


