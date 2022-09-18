package lk.proj.tourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lk.proj.tourapp.R;
import lk.proj.tourapp.dto.User;

public class TableListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Table> tableList;
    private User user;

    public TableListAdapter(Context context, int layout, ArrayList<Table> tableList, User user) {
        this.context = context;
        this.layout = layout;
        this.tableList = tableList;
        this.user = user;
    }

    private class ViewHolder{
        TextView lblRestaurantName, lblLocation, lblPrice;
        MaterialButton btnBookTable;
        ImageView imgRestaurantImage;
    }

    @Override
    public int getCount() {
        return tableList.size();
    }

    @Override
    public Object getItem(int i) {
        return tableList.get(i);
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

            holder.lblRestaurantName = (TextView) row.findViewById(R.id.lblRestaurantName);
            holder.lblLocation = (TextView) row.findViewById(R.id.lblTLocation);
            holder.lblPrice = (TextView) row.findViewById(R.id.lblPrice);
            holder.imgRestaurantImage = row.findViewById(R.id.imgRestaurantImage);
            holder.btnBookTable = row.findViewById(R.id.btnBookTable);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Table table = tableList.get(position);

        holder.lblRestaurantName.setText(table.getRestaurantName());
        holder.lblLocation.setText(table.getLocation());
        holder.lblPrice.setText(String.valueOf(table.getBookingPrice()));
        Picasso.get().load(table.getImageUrl()).into(holder.imgRestaurantImage);
        holder.btnBookTable.setOnClickListener(table.getBookBtnAction());
        return row;
    }
}
