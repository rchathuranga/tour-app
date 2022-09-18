package lk.proj.tourapp.dto;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String name;
    private String contactNo;
    private String email;
    private String advisorId;
    private String hotelId;
    private String cabId;
    private String checkIn;
    private String checkOut;
    private int noOfPeople;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdvisorId() {
        return advisorId;
    }
    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getHotelId() {
        return hotelId;
    }
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getCabId() {
        return cabId;
    }
    public void setCabId(String cabId) {
        this.cabId = cabId;
    }

    public String getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }
    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + getUserId() + '\'' +
                ", name='" + getName() + '\'' +
                ", contactNo='" + getContactNo() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", advisorId='" + getAdvisorId() + '\'' +
                ", hotelId='" + getHotelId() + '\'' +
                ", cabId='" + getCabId() + '\'' +
                '}';
    }
}
