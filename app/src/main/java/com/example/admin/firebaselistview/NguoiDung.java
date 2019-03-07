package com.example.admin.firebaselistview;

/**
 * Created by DUYET on 11/30/2017.
 */

public class NguoiDung {
    private String email,Pass;

    public NguoiDung() {
    }

    public NguoiDung(String email, String pass) {
        this.email = email;
        Pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
