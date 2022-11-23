package com.example.firebasauthmanagement;

import java.io.Serializable;

public class Nam implements Serializable {
    private String tenKhoaHoc,tenThuongGoi,CongDung;
    private int HinhAnh;

    public Nam(String tenKhoaHoc, String tenThuongGoi, String congDung, int hinhAnh) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.tenThuongGoi = tenThuongGoi;
        CongDung = congDung;
        HinhAnh = hinhAnh;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getTenThuongGoi() {
        return tenThuongGoi;
    }

    public void setTenThuongGoi(String tenThuongGoi) {
        this.tenThuongGoi = tenThuongGoi;
    }

    public String getCongDung() {
        return CongDung;
    }

    public void setCongDung(String congDung) {
        CongDung = congDung;
    }

    public int getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
