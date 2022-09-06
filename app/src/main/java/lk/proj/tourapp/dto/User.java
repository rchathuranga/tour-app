package lk.proj.tourapp.dto;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String name;
    private String contactNo;
    private String email;
    private String advisorId;

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

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
