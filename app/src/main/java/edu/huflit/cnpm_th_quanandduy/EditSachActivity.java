package edu.huflit.cnpm_th_quanandduy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class EditSachActivity extends AppCompatActivity {

    ImageView imgHinh;
    FirebaseFirestore db;
    ImageView imagChonSach;
    EditText edtTenSach,edtTenTacGia,edtTheLoai,edtMoTa,edtGiaSach,btnTaiSach;
    Button btnCapNhat;
    Sach sach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sach);

        imagChonSach=findViewById(R.id.imagChonSachE);
        edtTenSach=findViewById(R.id.edtTenSachE);
        edtTenTacGia=findViewById(R.id.edtTenTacGiaE);
        edtTheLoai=findViewById(R.id.edtTheLoaiE);
        edtMoTa=findViewById(R.id.edtMoTaE);
        edtGiaSach=findViewById(R.id.edtGiaSachE);
        btnCapNhat=findViewById(R.id.btnSua);

        sach = (Sach) getIntent().getSerializableExtra("EditSach");

        String IdSach= sach.getIdSach();
        String TenSach = sach.getTenSach();
        String LoaiSach = sach.getLoaiSach();
        String TacGia = sach.getTacGia();
        String GiaSach = sach.getGiaSach();
        String MotTa = sach.getMota();
        String HinhSach = sach.getHinhSach();
        String imageUri= sach.getHinhSach();

        Glide.with(EditSachActivity.this).load(imageUri).into(imagChonSach);
        edtTenSach.setText(TenSach);
        edtTenTacGia.setText(TacGia);
        edtTheLoai.setText(LoaiSach);
        edtMoTa.setText(MotTa);
        edtGiaSach.setText(GiaSach);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IdSachU= IdSach;
                String TenSachU = String.valueOf(edtTenSach.getText());
                String LoaiSachU = String.valueOf(edtTheLoai.getText());
                String TacGiaU = String.valueOf(edtTenTacGia.getText());
                String GiaSachU = String.valueOf(edtGiaSach.getText());
                String MotTaU = String.valueOf(edtMoTa.getText());
                String HinhSachU = imageUri;
                update(IdSachU,TenSachU,LoaiSachU,TacGiaU,GiaSachU,MotTaU,HinhSachU);
            }
            private void update(String idSachU, String tenSachU, String loaiSachU, String tacGiaU, String giaSachU, String motTaU, String hinhSachU) {

                Map<String,Object> sach= new HashMap<>();
                sach.put("TenSach",tenSachU);
                sach.put("LoaiSach",loaiSachU);
                sach.put("TacGia",tacGiaU);
                sach.put("GiaSach",giaSachU);
                sach.put("MoTa",motTaU);
                sach.put("HinhSach",hinhSachU);
                db = FirebaseFirestore.getInstance();
                db.collection("sach").document(idSachU).update(sach)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditSachActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditSachActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }


    }