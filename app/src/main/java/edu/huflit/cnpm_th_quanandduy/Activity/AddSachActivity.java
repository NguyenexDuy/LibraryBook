package edu.huflit.cnpm_th_quanandduy.Activity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.databinding.ActivityAddSachBinding;

public class AddSachActivity extends AppCompatActivity {

    private ActivityAddSachBinding activityAddSachBinding;
    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imagChonSach, img_sound;
    EditText edtTenSach;
    EditText edtTheLoai;
    EditText edtMoTa;
    EditText edtGiaSach;
    Button btnTaiSach;
    Uri imageUri;
    Uri mp3;
    ImageButton btn_backAddSach;
    StorageReference storageReference;
    FirebaseFirestore firestore;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressBar progess;
    String namAuthor="";
    String pictureBook="";
    private static final int PICK_AUDIO_REQUEST = 1;
    private static final int    NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddSachBinding = ActivityAddSachBinding.inflate(getLayoutInflater());
        setContentView(activityAddSachBinding.getRoot());


        imagChonSach = findViewById(R.id.imagChonSach);
        edtTenSach = findViewById(R.id.edtTenSach);
        edtTheLoai = findViewById(R.id.edtTheLoai);
        edtMoTa = findViewById(R.id.edtMoTa);
        edtGiaSach = findViewById(R.id.edtGiaSach);
        img_sound = findViewById(R.id.img_sound);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("images/");
        firestore = FirebaseFirestore.getInstance();
        progess = findViewById(R.id.progess);
        progess.setVisibility(View.GONE);
        activityAddSachBinding.progressImg.setVisibility(View.GONE);
        activityAddSachBinding.tvProgressImg.setVisibility(View.GONE);
        activityAddSachBinding.tvProgressAudio.setVisibility(View.GONE);
        btn_backAddSach = findViewById(R.id.btn_backAddSach);


        activityAddSachBinding.btnTaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null && mp3 != null) {
                    // Tải hình ảnh lên Firebase Storage
                    Log.d("Tag", "ảnh và sound ko null");
                    uploadImageToFirebaseStorage(imageUri, mp3);
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
        btn_backAddSach.setOnClickListener(v -> onBackPressed());

    }

    private void openSoundFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");  // Chỉ hiển thị các tệp âm thanh
        startActivityForResult(intent, PICK_AUDIO_REQUEST);
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && data.getData() != null) {
            imageUri = data.getData();
            Log.d("check", imageUri.toString());
            imagChonSach.setImageURI(imageUri);
        } else {
            Log.d("ngu", "fsdkjfs");
        }
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            // Lấy URI của tệp âm thanh được chọn
            mp3 = data.getData();


            // Thực hiện xử lý với URI tệp âm thanh đã chọn
            handleSelectedAudio(mp3);
            int changeimg = R.drawable.filemusic_fill;
            Drawable newImageDrawable = ContextCompat.getDrawable(AddSachActivity.this, changeimg);
            img_sound.setImageDrawable(newImageDrawable);
        }
    }

    private void handleSelectedAudio(Uri audioUri) {

        Toast.makeText(this, "Đã chọn âm thanh: " + audioUri.toString(), Toast.LENGTH_SHORT).show();
    }


    private void uploadImageToFirebaseStorage(Uri imageUri, Uri audioUri) {


        StorageReference imageRef = storageReference.child(System.currentTimeMillis() + ".jpg");
        activityAddSachBinding.progressImg.setVisibility(View.VISIBLE);
        activityAddSachBinding.tvProgressImg.setVisibility(View.VISIBLE);
//        imageRef .putFile(imageUri)
//                .addOnSuccessListener(taskSnapshot -> {
//                    // Lấy URL tải xuống của hình ảnh
//                    imageRef .getDownloadUrl()
//                            .addOnSuccessListener(uri -> {
//                                Toast.makeText(this, "tải hình ảnh  thành công", Toast.LENGTH_SHORT).show();
//
//                                uploadAudioToFirebaseStorage(audioUri, uri.toString());
//                            })
//                            .addOnFailureListener(e -> {
//                                Toast.makeText(this, "Không lấy được URL tải xuống", Toast.LENGTH_SHORT).show();
//                            });
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(this, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show();
//                });
        imageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageRef.getDownloadUrl()
                                .addOnSuccessListener(imageDowloadUrl -> {
                                    uploadAudioToFirebaseStorage(audioUri, imageDowloadUrl.toString());
                                    Toast.makeText(AddSachActivity.this, "Tải hình ảnh thành công", Toast.LENGTH_SHORT).show();
                                    activityAddSachBinding.progressImg.setVisibility(View.GONE);
                                    activityAddSachBinding.tvProgressImg.setText("Đã tải 100 %");
                                });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double pre = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        activityAddSachBinding.progressImg.setProgress((int) pre);
                        activityAddSachBinding.tvProgressImg.setText(pre + " %");
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Tải ảnh thất bại", Toast.LENGTH_SHORT).show();
                });

    }

    private void uploadAudioToFirebaseStorage(Uri audioUri, String imageDownloadUrl) {
        StorageReference audioRef = storageReference.child("audio/" + System.currentTimeMillis() + ".mp3");
//        audioRef.putFile(audioUri)
//                .addOnSuccessListener(taskSnapshot -> {
//                    // Lấy URL tải xuống của âm thanh
//                    audioRef.getDownloadUrl().addOnSuccessListener(audioDownloadUrl -> {
//                        // Tải thông tin sách lên Firestore sau khi tải âm thanh thành công
//                        Toast.makeText(this, "Tải âm thanh thành con công", Toast.LENGTH_SHORT).show();
//                        saveDataToFirestore(imageDownloadUrl, audioDownloadUrl.toString());
//                    });
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(this, "Tải âm thanh thất bại", Toast.LENGTH_SHORT).show();
//                });

        progess.setVisibility(View.VISIBLE);
        activityAddSachBinding.tvProgressAudio.setVisibility(View.VISIBLE);

        audioRef.putFile(audioUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddSachActivity.this, "Tai am thanh thanh cong", Toast.LENGTH_SHORT).show();
                activityAddSachBinding.progess.setVisibility(View.GONE);
                activityAddSachBinding.tvProgressAudio.setText("Đã tải 100 %");
                audioRef.getDownloadUrl().addOnSuccessListener(audioDownloadUrl -> {
                    saveDataToFirestore(imageDownloadUrl, audioDownloadUrl.toString());
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double press = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progess.setProgress((int) press);
                activityAddSachBinding.tvProgressAudio.setText(press + " %");
            }
        });

    }

    private void saveDataToFirestore(String imageUri, String audioDownloadUrl) {
        String id = firebaseUser.getUid();

        DocumentReference nameAuthor = db.collection("User").document(id);
        nameAuthor.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String tenTacgia = documentSnapshot.getString("Ten");
                namAuthor=tenTacgia;
                pictureBook=imageUri;
                String tenSach = edtTenSach.getText().toString();
                String theLoai = edtTheLoai.getText().toString();
                String moTa = edtMoTa.getText().toString();
                String giaSach = edtGiaSach.getText().toString();
                Map<String, Object> sach = new HashMap<>();
                sach.put("IdTacGia", id);
                sach.put("TenSach", tenSach);
                sach.put("TacGia", tenTacgia);
                sach.put("LoaiSach", theLoai);
                sach.put("GiaSach", giaSach);
                sach.put("MoTa", moTa);
                sach.put("HinhSach", imageUri);
                sach.put("MP3", audioDownloadUrl);

                // Thêm dữ liệu vào Firestore
                firestore.collection("Book")
                        .add(sach)
                        .addOnSuccessListener(documentReference -> {
                            // Dữ liệu đã được lưu trữ thành công vào Firestore
                            Toast.makeText(this, "Tải lên thành công", Toast.LENGTH_SHORT).show();
                            senNotification();

                            AlertDialog.Builder myDialog = new AlertDialog.Builder(AddSachActivity.this);
                            myDialog.setTitle("Tải thành công");
                            myDialog.setMessage("Chúc mừng bạn đã tải thành công!");
                            myDialog.setIcon(R.drawable.icon_notice);
                            myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    finish();
                                }
                            });
                            myDialog.create().show();
                        })
                        .addOnFailureListener(e -> {
                            // Xử lý lỗi nếu việc lưu dữ liệu vào Firestore thất bại
                            Toast.makeText(this, "Tải lên thất bại", Toast.LENGTH_SHORT).show();
                        });

            }

        });


    }

        private void senNotification() {
        Toast.makeText(this, "đã vào hàm senNotifi", Toast.LENGTH_SHORT).show();
        String tenSach=edtTenSach.getText().toString();


        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);


            Notification notification= null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                        .setContentTitle(namAuthor+" vừa đăng audioBook mới")
                        .setContentText(namAuthor+" đã đăng tải "+tenSach+". Hãy nhanh chân thưởng thức nào!")
                        .setSmallIcon(R.drawable.book_line)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(Icon.createWithContentUri(imageUri)).bigLargeIcon(null))
                        .setLargeIcon(bitmap)
                        .build();
            }

            NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null)
        {
            notificationManager.notify(NOTIFICATION_ID,notification);
        }
        else {
            Toast.makeText(this, "Rong roi", Toast.LENGTH_SHORT).show();
        }
    }

}