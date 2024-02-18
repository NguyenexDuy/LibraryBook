package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.databinding.ActivityDangNhapBinding;

public class DangNhapActivity extends AppCompatActivity {


    TextView registerNow;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;


    private ActivityDangNhapBinding activityDangNhapBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //upgrade code, user viewbinding to AnhXa
        activityDangNhapBinding=ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(activityDangNhapBinding.getRoot());


//        edtGmaillogin=findViewById(R.id.edtGmaillogin);
//        edtPassLogin=findViewById(R.id.edtPassLogin);
//        pb_dangnhap=findViewById(R.id.pb_dangnhap);
//        btnLogin=findViewById(R.id.btnLogin);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        registerNow=findViewById(R.id.registerNow);
        registerNow.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), DangKyActivity.class);
            startActivity(intent);
            finish();
        });
        activityDangNhapBinding.btnLogin.setOnClickListener(view -> {
            activityDangNhapBinding.pbDangnhap.setVisibility(View.VISIBLE);
            String email, pass, passAgain;
            email=activityDangNhapBinding.edtGmaillogin.getText().toString();
            pass=activityDangNhapBinding.edtPassLogin.getText().toString();
            email="duy129345@gmail.com";
            pass="duy123";



            if(TextUtils.isEmpty(email))
            {
                Toast.makeText(DangNhapActivity.this, "Vui long nhap email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(pass))
            {
                Toast.makeText(DangNhapActivity.this, "Vui long nhap password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(authResult -> {
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                checkRoleUser(authResult.getUser().getUid());
            });
        });
    }

    private void checkRoleUser(String uid) {
        DocumentReference dr=firestore.collection("User").document(uid);
        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                activityDangNhapBinding.pbDangnhap.setVisibility(View.GONE);

                Log.d("Tag","onSucess"+documentSnapshot.getData());

                if(documentSnapshot.getString("Status").equals("A"))
                {
                    Toast.makeText(DangNhapActivity.this, "Đã vào trang admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AuthorActivity.class));
                } else if (documentSnapshot.getString("Status").equals("U")) {

                    Intent intent=new Intent(DangNhapActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }


            }
        });


    }
}
