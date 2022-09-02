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

public class CabListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Cab> ongoingslist;

    public CabListAdapter(Context context, int layout, ArrayList<Cab> ongoingslist) {
        this.context = context;
        this.layout = layout;
        this.ongoingslist = ongoingslist;
    }


    private class ViewHolder{
        TextView driverName, contactNo;
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

            holder.driverName = (TextView) row.findViewById(R.id.driverName);
            holder.contactNo = (TextView) row.findViewById(R.id.contactNo);
            holder.imageView = (CircleImageView) row.findViewById(R.id.cabProPic);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Cab ongoing = ongoingslist.get(position);

        holder.driverName.setText(ongoing.driverName);
        holder.contactNo.setText(ongoing.contactNo);
        holder.imageView.setImageResource(ongoing.image);
        return row;
    }
}
