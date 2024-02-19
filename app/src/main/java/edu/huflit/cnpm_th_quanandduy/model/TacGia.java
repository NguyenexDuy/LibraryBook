package edu.huflit.cnpm_th_quanandduy.model;

import java.io.Serializable;

public class TacGia implements Serializable {

    private  String tenTacGia;
    private  String gmail;
    private  String matkhau;
    private  String tuoi;
    private  String kinh_Nghiem;

    private String img_TacGia;



    public TacGia(String tenTacGia, String gmail, String matkhau, String tuoi, String kinh_Nghiem, String img_TacGia) {
        this.tenTacGia = tenTacGia;
        this.gmail = gmail;
        this.matkhau = matkhau;
        this.tuoi = tuoi;
        this.kinh_Nghiem = kinh_Nghiem;
        this.img_TacGia = img_TacGia;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public String getKinh_Nghiem() {
        return kinh_Nghiem;
    }

    public void setKinh_Nghiem(String kinh_Nghiem) {
        this.kinh_Nghiem = kinh_Nghiem;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getImg_TacGia() {
        return img_TacGia;
    }

    public void setImg_TacGia(String img_TacGia) {
        this.img_TacGia = img_TacGia;
    }
}
