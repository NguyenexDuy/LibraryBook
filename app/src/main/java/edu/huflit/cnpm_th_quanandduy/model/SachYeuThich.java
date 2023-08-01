package edu.huflit.cnpm_th_quanandduy.model;

import java.io.Serializable;

public class SachYeuThich implements Serializable {


    private String IdSachYeuThich;
    private String IdSachGoc;
    private String TenSach;
    private String LoaiSach;
    private String TacGia;
    private String GiaSach;
    private String Mota;
    private String HinhSach;

    public SachYeuThich(String idSachYeuThich ,String idSachGoc, String tenSach, String loaiSach, String tacGia, String giaSach, String mota, String hinhSach) {
        IdSachYeuThich=idSachYeuThich;
        IdSachGoc = idSachGoc;
        TenSach = tenSach;
        LoaiSach = loaiSach;
        TacGia = tacGia;
        GiaSach = giaSach;
        Mota = mota;
        HinhSach = hinhSach;
    }

    public String getIdSachYeuThich() {
        return IdSachYeuThich;
    }

    public void setIdSachYeuThich(String idSachYeuThich) {
        IdSachYeuThich = idSachYeuThich;
    }

    public String getIdSachGoc() {
        return IdSachGoc;
    }

    public void setIdSachGoc(String idSachGoc) {
        IdSachGoc = idSachGoc;
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
