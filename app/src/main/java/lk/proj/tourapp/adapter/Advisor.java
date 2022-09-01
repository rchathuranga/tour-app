package lk.proj.tourapp.adapter;

public class Advisor {
    String id,name, contact,hired_status,img;

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

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHired_status() {
        return hired_status;
    }
    public void setHired_status(String hired_status) {
        this.hired_status = hired_status;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Advisor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", hired_status='" + hired_status + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

