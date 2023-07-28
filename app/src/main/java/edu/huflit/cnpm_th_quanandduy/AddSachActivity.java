package edu.huflit.cnpm_th_quanandduy;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class AddSachActivity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST =1 ;
    ImageView imagChonSach;
    EditText edtTenSach;
    EditText edtTenTacGia;
    EditText edtTheLoai;
    EditText edtMoTa;
    EditText edtGiaSach;
    Button btnTaiSach;
    Uri imageUri;
    StorageReference storageReference;
    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);

        imagChonSach=findViewById(R.id.imagChonSach);
        edtTenSach=findViewById(R.id.edtTenSach);
        edtTenTacGia=findViewById(R.id.edtTenTacGia);
        edtTheLoai=findViewById(R.id.edtTheLoai);
        edtMoTa=findViewById(R.id.edtMoTa);
        edtGiaSach=findViewById(R.id.edtGiaSach);
        btnTaiSach=findViewById(R.id.btnTaiSach);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("images/");
        firestore = FirebaseFirestore.getInstance();

        btnTaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null) {
                    // Tải hình ảnh lên Firebase Storage
                    uploadImageToFirebaseStorage(imageUri);
                } else {
                    Toast.makeText(AddSachActivity.this, "Hãy chọn một hình ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imagChonSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            Log.d("check", imageUri.toString());
            imagChonSach.setImageURI(imageUri);
        }
        else {
            Log.d("ngu", "fsdkjfs");
        }
    }

    private void openFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }
    private void uploadImageToFirebaseStorage(Uri imageUri) {

        // Tạo thư mục "images" trong Firebase Storage để lưu trữ hình ảnh
        StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");
        // Tải hình ảnh lên Firebase Storage
        fileReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Lấy URL tải xuống của hình ảnh
                    fileReference.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                Toast.makeText(this, "tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                saveDataToFirestore(uri.toString());
                            })
                            .addOnFailureListener(e -> {
                                // Xử lý lỗi nếu không lấy được URL tải xuống
                                Toast.makeText(this, "Không lấy được URL tải xuống", Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi nếu việc tải hình ảnh thất bại
                    Toast.makeText(this, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show();
                });
    }

    private void saveDataToFirestore(String imageUri) {
        String tenSach = edtTenSach.getText().toString();
        String tenTacGia = edtTenTacGia.getText().toString();
        String theLoai = edtTheLoai.getText().toString();
        String moTa = edtMoTa.getText().toString();
        String giaSach = edtGiaSach.getText().toString();


        Map<String, Object> sach = new HashMap<>();
        sach.put("TenSach",tenSach);
        sach.put("TacGia",tenTacGia);
        sach.put("LoaiSach",theLoai);
        sach.put("GiaSach",giaSach);
        sach.put("MoTa",moTa);
        sach.put("HinhSach",imageUri);

        // Thêm dữ liệu vào Firestore
        firestore.collection("sach")
                .add(sach)
                .addOnSuccessListener(documentReference -> {
                    // Dữ liệu đã được lưu trữ thành công vào Firestore
                    Toast.makeText(this, "Tải lên thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi nếu việc lưu dữ liệu vào Firestore thất bại
                    Toast.makeText(this, "Tải lên thất bại", Toast.LENGTH_SHORT).show();
                });
    }
}