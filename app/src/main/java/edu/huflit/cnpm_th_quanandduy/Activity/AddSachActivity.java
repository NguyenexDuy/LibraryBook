package edu.huflit.cnpm_th_quanandduy.Activity;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_th_quanandduy.R;

public class AddSachActivity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST =1 ;
    ImageView imagChonSach,img_sound;
    EditText edtTenSach;
    EditText edtTenTacGia;
    EditText edtTheLoai;
    EditText edtMoTa;
    EditText edtGiaSach;
    Button btnTaiSach;
    Uri imageUri;
    Uri mp3;
    StorageReference storageReference;
    FirebaseFirestore firestore;
    private static final int PICK_AUDIO_REQUEST = 1;




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
        img_sound=findViewById(R.id.img_sound);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("images/");
        firestore = FirebaseFirestore.getInstance();

        btnTaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null&&mp3!=null) {
                    // Tải hình ảnh lên Firebase Storage
                    uploadImageToFirebaseStorage(imageUri,mp3);
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
        img_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSoundFileChooser();
            }
        });

    }

    private  void  openSoundFileChooser()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");  // Chỉ hiển thị các tệp âm thanh
        startActivityForResult(intent, PICK_AUDIO_REQUEST);
    }
    private void openFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
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
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            // Lấy URI của tệp âm thanh được chọn
             mp3 = data.getData();

            // Thực hiện xử lý với URI tệp âm thanh đã chọn
            handleSelectedAudio(mp3);
            int changeimg=R.drawable.filemusic_fill;
            Drawable newImageDrawable = ContextCompat.getDrawable(AddSachActivity.this, changeimg);
            img_sound.setImageDrawable(newImageDrawable);
        }
    }
    private void handleSelectedAudio(Uri audioUri) {

        Toast.makeText(this, "Đã chọn âm thanh: " + audioUri.toString(), Toast.LENGTH_SHORT).show();
    }


    private void uploadImageToFirebaseStorage(Uri imageUri,Uri audioUri) {

        StorageReference imageRef  = storageReference.child(System.currentTimeMillis() + ".jpg");
        imageRef .putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Lấy URL tải xuống của hình ảnh
                    imageRef .getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                Toast.makeText(this, "tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
//                                saveDataToFirestore(uri.toString());
                                uploadAudioToFirebaseStorage(audioUri, uri.toString());
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Không lấy được URL tải xuống", Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show();
                });
    }
    private void uploadAudioToFirebaseStorage(Uri audioUri, String imageDownloadUrl) {
        StorageReference audioRef = storageReference.child("audio/" + System.currentTimeMillis() + ".mp3");
        audioRef.putFile(audioUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Lấy URL tải xuống của âm thanh
                    audioRef.getDownloadUrl().addOnSuccessListener(audioDownloadUrl -> {
                        // Tải thông tin sách lên Firestore sau khi tải âm thanh thành công
                        saveDataToFirestore(imageDownloadUrl, audioDownloadUrl.toString());
                    });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Tải âm thanh thất bại", Toast.LENGTH_SHORT).show();
                });
    }

    private void saveDataToFirestore(String imageUri, String audioDownloadUrl) {
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
        sach.put("MP3",audioDownloadUrl);

        // Thêm dữ liệu vào Firestore
        firestore.collection("Book")
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