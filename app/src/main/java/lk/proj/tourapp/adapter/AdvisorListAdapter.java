package lk.proj.tourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lk.proj.tourapp.Advisor_Details;
import lk.proj.tourapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import lk.proj.tourapp.databinding.ActivityMainBinding;

public class AdvisorListAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<Advisor> ongoingslist;
    ActivityMainBinding binding;

    public AdvisorListAdapter(Context context, int layout, ArrayList<Advisor> ongoingslist) {
        this.context = context;
        this.layout = layout;
        this.ongoingslist = ongoingslist;
    }


    private class ViewHolder{
        TextView name,email, experience;
        CircleImageView imageView;
    }

    @Override
    public int getCount() {
        return ongoingslist.size();
    }

    @Override
    public Object getItem(int i) {
        return ongoingslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        LayoutInflater inflater;

        if(row == null){
           inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.name = (TextView) row.findViewById(R.id.advisorName);
            holder.email= (TextView) row.findViewById(R.id.advisorsEmail);
            holder.experience= (TextView) row.findViewById(R.id.advisorExperience);
            holder.imageView = (CircleImageView) row.findViewById(R.id.advisorProPic);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Advisor ongoing = ongoingslist.get(position);

        holder.name.setText(ongoing.name);
        holder.email.setText(ongoing.contact);
        holder.experience.setText(ongoing.getExperience());
        Picasso.get().load(ongoing.img).into(holder.imageView);

        return row;
    }
}
