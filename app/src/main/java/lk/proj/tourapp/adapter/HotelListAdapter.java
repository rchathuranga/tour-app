package lk.proj.tourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lk.proj.tourapp.R;

import java.util.ArrayList;

public class HotelListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Hotel> hotelList;

    public HotelListAdapter(Context context, int layout, ArrayList<Hotel> hotelList) {
        this.context = context;
        this.layout = layout;
        this.hotelList = hotelList;
    }


    private class ViewHolder{
        TextView lblHotelName, lblLocation, lblPrice;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return hotelList.size();
    }

    @Override
    public Object getItem(int i) {
        return hotelList.get(i);
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

            holder.lblHotelName = (TextView) row.findViewById(R.id.hotelName);
            holder.lblLocation = (TextView) row.findViewById(R.id.location);
            holder.lblPrice = (TextView) row.findViewById(R.id.hotelPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.hotelProPic);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Hotel hotel = hotelList.get(position);

        holder.lblHotelName.setText(hotel.getHotelName());
        holder.lblLocation.setText(hotel.getLocation());
        holder.lblPrice.setText(hotel.getPrice().toString());
        Picasso.get().load(hotel.getImageUrl()).into(holder.imageView);
        return row;
    }
}
