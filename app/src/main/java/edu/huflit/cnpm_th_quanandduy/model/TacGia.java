package edu.huflit.cnpm_th_quanandduy.model;

import android.widget.ImageView;

public class TacGia {
    private  String tenTacGia;
    private  String gmail;
    private  String matkhau;
    private  String tuoi;
    private  int kinh_Nghiem;

    private String img_TacGia;

    public TacGia(String tenTacGia, String img_TacGia) {
        this.tenTacGia = tenTacGia;
        this.img_TacGia = img_TacGia;
    }

    public TacGia(String tenTacGia, String gmail, String matkhau, String tuoi, int kinh_Nghiem, String img_TacGia) {
        this.tenTacGia = tenTacGia;
        this.gmail = gmail;
        this.matkhau = matkhau;
        this.tuoi = tuoi;
        this.kinh_Nghiem = kinh_Nghiem;
        this.img_TacGia = img_TacGia;
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
