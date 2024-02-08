package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import edu.huflit.cnpm_th_quanandduy.R;

public class ChiTietAuthorActivity extends AppCompatActivity {
    ImageButton img_backChiTietTacGia;
    ImageView img_thongTinTacGia;
    TextView tv_tenTacGiaChiTiet,tv_age,tv_experience,tv_favorite,tv_quantity;
    Button btn_theoDoi;

    RecyclerView rcv_sachLienQuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_author);
        AnhXa();
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

}