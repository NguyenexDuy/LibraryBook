package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.adapter.SachAdapter;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class ChiTietSachActivity extends AppCompatActivity {

    Sach sach;
    ImageView img_plur,img_thongTinSachImg;
    TextView tv_TenSachChiTiet,tv_thongTinSachTenTacGia,tv_ThoiGianAudio,tv_LuotXem,tv_TheLoai,tv_GioiThieu,tv_danhGia;
    RecyclerView rcv_tacPhamCoLienQuan;
    ArrayList<Sach> sachLienQuan;
    Button btn_Nghe;
    FirebaseFirestore db;
    SachAdapter sachLienQuanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sach);

        AnhXa();
        SetData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            img_plur.setRenderEffect(RenderEffect.createBlurEffect(50,50, Shader.TileMode.MIRROR));
        }
        ActionListen();


    }
    private void SetData()
    {
        sach=(Sach) getIntent().getSerializableExtra("ThongTinSach");
        Glide.with(ChiTietSachActivity.this).load(sach.getHinhSach()).into(img_thongTinSachImg);
        Glide.with(ChiTietSachActivity.this).load(sach.getHinhSach()).into(img_plur);
        String tenTacGia=sach.getTacGia();
        String tenSach=sach.getTenSach();
        String theloai=sach.getLoaiSach();
        tv_TenSachChiTiet.setText(tenSach);
        tv_thongTinSachTenTacGia.setText(tenTacGia);
        tv_TheLoai.setText(theloai);

    }
    private void ActionListen()
    {
        btn_Nghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChiTietSachActivity.this,PlayAudioBookActivity.class);
                startActivity(intent);
            }
        });

    }
    private void AnhXa()
    {
        img_plur=findViewById(R.id.img_plur);
        img_thongTinSachImg=findViewById(R.id.img_thongTinSachImg);
        tv_TenSachChiTiet=findViewById(R.id.tv_TenSachChiTiet);
        tv_thongTinSachTenTacGia=findViewById(R.id.tv_thongTinSachTenTacGia);
        tv_ThoiGianAudio=findViewById(R.id.tv_ThoiGianAudio);
        tv_LuotXem=findViewById(R.id.tv_LuotXem);
        tv_TheLoai=findViewById(R.id.tv_TheLoai);
        tv_GioiThieu=findViewById(R.id.tv_GioiThieu);
        tv_danhGia=findViewById(R.id.tv_danhGia);
        rcv_tacPhamCoLienQuan=findViewById(R.id.rcv_tacPhamCoLienQuan);
        btn_Nghe=findViewById(R.id.btn_Nghe);
    }

}