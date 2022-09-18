package lk.proj.tourapp.adapter;

import android.view.View;

import java.io.Serializable;

public class Table implements Serializable {
    private String tableId;
    private String restaurantName;
    private double bookingPrice;
    private String imageUrl;
    private int noOfSeats;
    private String location;
    private View.OnClickListener bookBtnAction;

    public Table() {
    }

    public Table(String tableId, String restaurantName, double bookingPrice, String imageUrl, int noOfSeats, String location) {
        this.tableId = tableId;
        this.restaurantName = restaurantName;
        this.bookingPrice = bookingPrice;
        this.imageUrl = imageUrl;
        this.noOfSeats = noOfSeats;
        this.location = location;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public View.OnClickListener getBookBtnAction() {
        return bookBtnAction;
    }

    public void setBookBtnAction(View.OnClickListener bookBtnAction) {
        this.bookBtnAction = bookBtnAction;
    }
}
