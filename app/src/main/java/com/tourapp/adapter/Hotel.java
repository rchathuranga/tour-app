package com.tourapp.adapter;

public class Hotel {
    String hotelName, location;
    int hotelimage;
    public Hotel(String hotelName, String hotelContact, int hotelimage) {
        this.hotelName = hotelName;
        this.location = hotelContact;
        this.hotelimage = hotelimage;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHotelimage() {
        return hotelimage;
    }

    public void setHotelimage(int hotelimage) {
        this.hotelimage = hotelimage;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", location='" + location + '\'' +
                ", hotelimage=" + hotelimage +
                '}';
    }
    
}
