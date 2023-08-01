package edu.huflit.cnpm_th_quanandduy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_th_quanandduy.model.Sach;
import edu.huflit.cnpm_th_quanandduy.model.SachYeuThich;

public class ChiTietSachActivity extends AppCompatActivity {

    Sach sach;
    ImageView img_thongTinSachImg,img_yeuThich;
    TextView tv_thongTinTenSach,tv_thongTinSachTenTacGia,tv_thongTinSachMoTa,tv_tacGiaChiTiet,tv_theLoaiChiTiet;
    RecyclerView rcvSachLienQuan;
    ArrayList<Sach> sachLienQuan;
    Button btn_thongTinSachThueSach;
    FirebaseFirestore db;
    SachAdapter sachLienQuanAdapter;

    Boolean danhdau = false;
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
        img_yeuThich=findViewById(R.id.img_yeuThich);
        btn_thongTinSachThueSach=findViewById(R.id.btn_thongTinSachThueSach);
        rcvSachLienQuan=findViewById(R.id.rcvSachLienQuan);

        sachLienQuan=new ArrayList<>();
        sachesLienQuan();
        sachLienQuanAdapter=new SachAdapter(ChiTietSachActivity.this,sachLienQuan);
        rcvSachLienQuan.setAdapter(sachLienQuanAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChiTietSachActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcvSachLienQuan.setLayoutManager(layoutManager);


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

        btn_thongTinSachThueSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChiTietSachActivity.this,PhieuMuongSachActivity.class);
                i.putExtra("MuonSach",sach);
                startActivity(i);
            }
        });



        db.collection("sachYeuThich").whereEqualTo("idSach",sach.getIdSach()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                danhdau = true;
            }
        });
        if(danhdau){
            img_yeuThich.setEnabled(false);
            img_yeuThich.setBackgroundResource(R.drawable.liked);
        }
        img_yeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach=sach.getTenSach();
                String idSachGoc=sach.getIdSach();
                String tenTacGia=sach.getTacGia();
                String theLoai=sach.getLoaiSach();
                String moTa=sach.getMota();
                String gia=sach.getGiaSach();
                String hinh=sach.getHinhSach();
                Map<String, Object> sachYeuThich = new HashMap<>();
                sachYeuThich.put("idSachGoc",idSachGoc);
                sachYeuThich.put("TenSach",tensach);
                sachYeuThich.put("TacGia",tenTacGia);
                sachYeuThich.put("LoaiSach",theLoai);
                sachYeuThich.put("GiaSach",gia);
                sachYeuThich.put("MoTa",moTa);
                sachYeuThich.put("HinhSach",hinh);
                db.collection("sachYeuThich")
                        .add(sach)
                        .addOnSuccessListener(documentReference -> {
                            // Dữ liệu đã được lưu trữ thành công vào Firestore
                            img_yeuThich.setEnabled(false);
                            img_yeuThich.setBackgroundResource(R.drawable.liked);
                            Toast.makeText(ChiTietSachActivity.this, "Tải lên thành công", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            // Xử lý lỗi nếu việc lưu dữ liệu vào Firestore thất bại
                            Toast.makeText(ChiTietSachActivity.this, "Tải lên thất bại", Toast.LENGTH_SHORT).show();
                        });

            }
        });

    }
    private void sachesLienQuan()
    {
        db=FirebaseFirestore.getInstance();
        db.collection("sach").whereEqualTo("LoaiSach",sach.getLoaiSach()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String IdSach=documentSnapshot.getId();
                    String TenSach=(String)documentSnapshot.get("TenSach");
                    String GiaSach = (String) documentSnapshot.get("GiaSach");
                    String LoaiSach = (String) documentSnapshot.get("LoaiSach");
                    String TacGia = (String) documentSnapshot.get("TacGia");
                    String MoTa = (String) documentSnapshot.get("MoTa");
                    String HinhSach = (String) documentSnapshot.get("HinhSach");

                    Sach sach=new Sach(IdSach,TenSach,GiaSach,LoaiSach,TacGia,MoTa,HinhSach);
                    sachLienQuan.add(sach);
                }

            }
        });
    }
}