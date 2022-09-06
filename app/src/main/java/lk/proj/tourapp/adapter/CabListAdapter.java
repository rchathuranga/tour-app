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

import de.hdodenhof.circleimageview.CircleImageView;

public class CabListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Cab> cabArrayList;

    public CabListAdapter(Context context, int layout, ArrayList<Cab> cabArrayList) {
        this.context = context;
        this.layout = layout;
        this.cabArrayList = cabArrayList;
    }


    private class ViewHolder {
        TextView driverName, contactNo;
        CircleImageView imgDriver;
        ImageView imgVehicleType;
    }

    @Override
    public int getCount() {
        return cabArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return cabArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();


        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.driverName = (TextView) row.findViewById(R.id.driverName);
            holder.contactNo = (TextView) row.findViewById(R.id.contactNo);
            holder.imgDriver = (CircleImageView) row.findViewById(R.id.cabProPic);
            holder.imgVehicleType = (ImageView) row.findViewById(R.id.cabVehicleType);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Cab ongoing = cabArrayList.get(position);

        holder.driverName.setText(ongoing.getDriverName());
        holder.contactNo.setText(ongoing.getContactNo());
        Picasso.get().load(ongoing.getImageUrl()).into(holder.imgDriver);
        holder.imgVehicleType.setImageResource(
                (ongoing.getVehicleType().equalsIgnoreCase("CAR"))
                        ? R.drawable.ic_baseline_directions_car_24
                        : (ongoing.getVehicleType().equalsIgnoreCase("VAN"))
                        ? R.drawable.ic_baseline_directions_car_24
                        : R.drawable.ic_baseline_directions_car_24);
        return row;
    }
}
