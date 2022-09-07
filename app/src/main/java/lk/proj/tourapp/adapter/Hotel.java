package lk.proj.tourapp.adapter;

import android.view.View;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String hotelId;
    private String hotelName;
    private int availableRooms;
    private String roomTypes;
    private String rating;
    private String description;
    private String email;
    private String contactNo;
    private String imageUrl;
    private Double price;
    private String location;
    private View.OnClickListener btnMoreInfoClickEvent;
    private View.OnClickListener btnBookNowClickEvent;


    public String getHotelId() {
        return hotelId;
    }
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }
    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getRoomTypes() {
        return roomTypes;
    }
    public void setRoomTypes(String roomTypes) {
        this.roomTypes = roomTypes;
    }

    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public View.OnClickListener getBtnMoreInfoClickEvent() {
        return btnMoreInfoClickEvent;
    }
    public void setBtnMoreInfoClickEvent(View.OnClickListener btnMoreInfoClickEvent) {
        this.btnMoreInfoClickEvent = btnMoreInfoClickEvent;
    }

    public View.OnClickListener getBtnBookNowClickEvent() {
        return btnBookNowClickEvent;
    }
    public void setBtnBookNowClickEvent(View.OnClickListener btnBookNowClickEvent) {
        this.btnBookNowClickEvent = btnBookNowClickEvent;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", availableRooms=" + availableRooms +
                ", roomTypes='" + roomTypes + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                '}';
    }
}
