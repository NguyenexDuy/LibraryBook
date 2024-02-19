package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Set;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.TacGia;

public class ChiTietAuthorActivity extends AppCompatActivity {
    ImageButton img_backChiTietTacGia;
    ImageView img_thongTinTacGia;
    TextView tv_tenTacGiaChiTiet,tv_age,tv_experience,tv_favorite,tv_quantity;
    Button btn_theoDoi;
    TacGia tacGia;

    RecyclerView rcv_sachLienQuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_author);
        AnhXa();
        SetData();
        img_backChiTietTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void AnhXa()
    {
        img_backChiTietTacGia=findViewById(R.id.img_backChiTietTacGia);
        img_thongTinTacGia=findViewById(R.id.img_thongTinTacGia);
        tv_tenTacGiaChiTiet=findViewById(R.id.tv_tenTacGiaChiTiet);
        tv_age=findViewById(R.id.tv_age);
        tv_experience=findViewById(R.id.tv_experience);
        tv_favorite=findViewById(R.id.tv_favorite);
        tv_quantity=findViewById(R.id.tv_quantity);
    }

    private void SetData()
    {
        tacGia=(TacGia) getIntent().getSerializableExtra("ThongTinTacGia");
        String tenTacGia=tacGia.getTenTacGia();
        String age=tacGia.getTuoi();
        String ex=tacGia.getKinh_Nghiem();
        tv_tenTacGiaChiTiet.setText(tenTacGia);
        tv_age.setText(age);
        tv_experience.setText(ex);
    }

}