package edu.huflit.cnpm_th_quanandduy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class ChiTietSachActivity extends AppCompatActivity {

    Sach sach;
    ImageView img_thongTinSachImg;
    TextView tv_thongTinTenSach,tv_thongTinSachTenTacGia,tv_thongTinSachMoTa,tv_tacGiaChiTiet,tv_theLoaiChiTiet;
    RecyclerView rcvSachLienQuan;
    Button btn_thongTinSachThueSach;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sach);
        sach = (Sach) getIntent().getSerializableExtra("ThongTinSach");
        tv_thongTinTenSach=findViewById(R.id.tv_thongTinTenSach);
        tv_thongTinSachTenTacGia=findViewById(R.id.tv_thongTinSachTenTacGia);
        tv_thongTinSachMoTa=findViewById(R.id.tv_thongTinSachMoTa);
        tv_tacGiaChiTiet=findViewById(R.id.tv_tacGiaChiTiet);
        rcvSachLienQuan=findViewById(R.id.rcvSachLienQuan);
        img_thongTinSachImg=findViewById(R.id.img_thongTinSachImg);
        btn_thongTinSachThueSach=findViewById(R.id.btn_thongTinSachThueSach);
        tv_theLoaiChiTiet=findViewById(R.id.tv_theLoaiChiTiet);
        Glide.with(ChiTietSachActivity.this).load(sach.getHinhSach()).into(img_thongTinSachImg);
        db=FirebaseFirestore.getInstance();

        String tenSach=sach.getTenSach();
        String tenTacGia=sach.getTacGia();
        String moTa=sach.getMota();
        String theLoaiChiTiet=sach.getLoaiSach();
        String tenTacGiaChiTiet=sach.getTacGia();



        tv_thongTinTenSach.setText(tenSach);
        tv_thongTinSachTenTacGia.setText(tenTacGia);
        tv_thongTinSachMoTa.setText(moTa);
        tv_tacGiaChiTiet.setText(tenTacGiaChiTiet);
        tv_theLoaiChiTiet.setText(theLoaiChiTiet);


    }
}