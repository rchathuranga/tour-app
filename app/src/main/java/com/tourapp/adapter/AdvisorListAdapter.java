package com.tourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tourapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdvisorListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Advisor> ongoingslist;

    public AdvisorListAdapter(Context context, int layout, ArrayList<Advisor> ongoingslist) {
        this.context = context;
        this.layout = layout;
        this.ongoingslist = ongoingslist;
    }


    private class ViewHolder{
        TextView name,email;
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

            holder.name = (TextView) row.findViewById(R.id.advisorName);
            holder.email= (TextView) row.findViewById(R.id.advisorsEmail);
            holder.imageView = (CircleImageView) row.findViewById(R.id.advisorProPic);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Advisor ongoing = ongoingslist.get(position);

        holder.name.setText(ongoing.name);
        holder.email.setText(ongoing.contact);
        holder.imageView.setImageResource(R.drawable.test_advisor);
        return row;
    }
}
