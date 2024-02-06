package edu.huflit.cnpm_th_quanandduy.model;

import android.net.Uri;

import java.io.Serializable;

public class Sach implements Serializable {

    private String IdSach;
    private String IdTacGia;
    private String TenSach;
    private String LoaiSach;
    private String TacGia;
    private String GiaSach;
    private String Mota;
    private String HinhSach;
    private String Mp3;

    public Sach(String IDSach,String tenSach,String idTacGia, String giaSach, String loaiSach, String tacGia, String mota, String hinhSach,String Mp3) {
        IdSach=IDSach;
        TenSach = tenSach;
        this.IdTacGia=idTacGia;
        LoaiSach = loaiSach;
        TacGia = tacGia;
        GiaSach = giaSach;
        Mota = mota;
        HinhSach = hinhSach;
        this.Mp3=Mp3;
    }

    public String getIdSach() {
        return IdSach;
    }

    public void setIdSach(String idSach) {
        IdSach = idSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getLoaiSach() {
        return LoaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        LoaiSach = loaiSach;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getGiaSach() {
        return GiaSach;
    }

    public void setGiaSach(String giaSach) {
        GiaSach = giaSach;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getHinhSach() {
        return HinhSach;
    }

    public void setHinhSach(String hinhSach) {
        HinhSach = hinhSach;
    }
}
