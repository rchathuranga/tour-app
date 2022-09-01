package lk.proj.tourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import lk.proj.tourapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HotelListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Hotel> ongoingslist;

    public HotelListAdapter(Context context, int layout, ArrayList<Hotel> ongoingslist) {
        this.context = context;
        this.layout = layout;
        this.ongoingslist = ongoingslist;
    }


    private class ViewHolder{
        TextView hotelName, location;
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


        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.hotelName = (TextView) row.findViewById(R.id.hotelName);
            holder.location = (TextView) row.findViewById(R.id.location);
            holder.imageView = (CircleImageView) row.findViewById(R.id.hotelProPic);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Hotel ongoing = ongoingslist.get(position);

        holder.hotelName.setText(ongoing.hotelName);
        holder.location.setText(ongoing.location);
        holder.imageView.setImageResource(ongoing.hotelimage);
        return row;
    }
}
