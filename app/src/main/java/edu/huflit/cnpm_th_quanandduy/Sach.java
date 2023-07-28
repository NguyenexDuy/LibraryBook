package edu.huflit.cnpm_th_quanandduy;

public class Sach {

    private String IdSach;
    private String TenSach;
    private String LoaiSach;
    private String TacGia;
    private String GiaSach;
    private String Mota;
    private String HinhSach;

    public Sach(String IDSach,String tenSach, String giaSach, String loaiSach, String tacGia, String mota, String hinhSach) {
        IdSach=IDSach;
        TenSach = tenSach;
        LoaiSach = loaiSach;
        TacGia = tacGia;
        GiaSach = giaSach;
        Mota = mota;
        HinhSach = hinhSach;
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
