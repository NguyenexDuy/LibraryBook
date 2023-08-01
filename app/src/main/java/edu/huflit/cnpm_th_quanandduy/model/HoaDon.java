package edu.huflit.cnpm_th_quanandduy.model;

public class HoaDon {
    public HoaDon(String iDHoaDon, String IDSach, String tenSach, String ngayMuon, String ngayTra, String soNgayMuon, String tongTien) {
        this.IDSach = IDSach;
        TenSach = tenSach;
        NgayMuon = ngayMuon;
        NgayTra = ngayTra;
        SoNgayMuon = soNgayMuon;
        TongTien = tongTien;
        IDHoaDon = iDHoaDon;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    private String IDHoaDon;
    private String IDSach;
    private String TenSach;
    private String NgayMuon;
    private String NgayTra;
    private String SoNgayMuon;
    private String TongTien;

    public String getIDSach() {
        return IDSach;
    }

    public void setIDSach(String IDSach) {
        this.IDSach = IDSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String ngayTra) {
        NgayTra = ngayTra;
    }

    public String getSoNgayMuon() {
        return SoNgayMuon;
    }

    public void setSoNgayMuon(String soNgayMuon) {
        SoNgayMuon = soNgayMuon;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }


}
