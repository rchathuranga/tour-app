package lk.proj.tourapp.adapter;

public class Advisor {
    String id,name,email, contact,img;
    int hiredCount,badReviews,goodReviews;

    public Advisor() {
    }

    public Advisor(String id, String name, String email, String contact, String img, int hiredCount, int badReviews, int goodReviews) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.img = img;
        this.hiredCount = hiredCount;
        this.badReviews = badReviews;
        this.goodReviews = goodReviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getHiredCount() {
        return hiredCount;
    }

    public void setHiredCount(int hiredCount) {
        this.hiredCount = hiredCount;
    }

    public int getBadReviews() {
        return badReviews;
    }

    public void setBadReviews(int badReviews) {
        this.badReviews = badReviews;
    }

    public int getGoodReviews() {
        return goodReviews;
    }

    public void setGoodReviews(int goodReviews) {
        this.goodReviews = goodReviews;
    }
}

