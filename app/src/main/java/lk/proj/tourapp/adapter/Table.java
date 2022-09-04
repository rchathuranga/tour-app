package lk.proj.tourapp.adapter;

public class Table {
    String name, tableNo, hotelName;
    int noOfSeats;
    double bookingCharge;

    public Table(String name, String tableNo, String hotelName, int noOfSeats, double bookingCharge) {
        this.name = name;
        this.tableNo = tableNo;
        this.hotelName = hotelName;
        this.noOfSeats = noOfSeats;
        this.bookingCharge = bookingCharge;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public double getBookingCharge() {
        return bookingCharge;
    }

    public void setBookingCharge(double bookingCharge) {
        this.bookingCharge = bookingCharge;
    }
}
