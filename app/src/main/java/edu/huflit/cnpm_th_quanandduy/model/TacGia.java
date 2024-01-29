package edu.huflit.cnpm_th_quanandduy.model;

import android.widget.ImageView;

public class TacGia {
    private  String tenTacGia;
    private int img_TacGia;

    public TacGia(String tenTacGia, int img_TacGia) {
        this.tenTacGia = tenTacGia;
        this.img_TacGia = img_TacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getImg_TacGia() {
        return img_TacGia;
    }

    public void setImg_TacGia(int img_TacGia) {
        this.img_TacGia = img_TacGia;
    }
}
