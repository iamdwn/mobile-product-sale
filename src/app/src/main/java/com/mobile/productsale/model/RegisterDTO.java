package com.mobile.productsale.model;

public class RegisterDTO {

    private String userName;
    private String passwordHash;
    private String email;
    private String phone;
    private String address;

    public RegisterDTO() {
    }

    public RegisterDTO(String userName, String passwordHash, String email, String phone, String address) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
