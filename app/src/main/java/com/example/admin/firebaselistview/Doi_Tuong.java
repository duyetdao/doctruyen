package com.example.admin.firebaselistview;

/**
 * Created by Admin on 5/26/2017.
 */

public class Doi_Tuong {
    private String key;


    public Doi_Tuong(String key, String tenTruyen, String tacGia, String noiDung, String url) {
        this.key = key;
        TenTruyen = tenTruyen;
        TacGia = tacGia;
        NoiDung = noiDung;
        this.url = url;
    }

    public Doi_Tuong(String tenTruyen, String tacGia, String noiDung, String url) {
        TenTruyen = tenTruyen;
        TacGia = tacGia;
        NoiDung = noiDung;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    private  String TenTruyen;

    public String getTenTruyen() {
        return TenTruyen;
    }
    public void setTenTruyen(String tenTruyen) {
        this.TenTruyen = tenTruyen;
    }


    private  String TacGia;

    public String getTacGia() {
        return TacGia;
    }
    public void setTacGia(String tacGia) {
        this.TacGia = tacGia;
    }

    private String NoiDung;

    public String getNoiDung() {
        return NoiDung;
    }
    public void setNoiDung(String noiDung) {
        this.NoiDung = noiDung;
    }
    private  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Doi_Tuong() {
    }



}
