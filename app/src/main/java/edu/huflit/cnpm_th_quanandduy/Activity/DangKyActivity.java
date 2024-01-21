package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_th_quanandduy.R;

public class DangKyActivity extends AppCompatActivity {

    EditText edtGmail, edtPass, edtPassAgain,edtFullName,edtPhone;
    Button btnregister;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    TextView loginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtFullName=findViewById(R.id.edtFullname);
        edtPhone=findViewById(R.id.edtPhone);
        edtGmail=findViewById(R.id.edtGmail);
        edtPass=findViewById(R.id.edtPass);
        edtPassAgain=findViewById(R.id.edtPassAgain);
        btnregister=findViewById(R.id.btnregister);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        loginNow=findViewById(R.id.loginNow);
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, pass, passAgain,fullname,phone;
                fullname=edtFullName.getText().toString();
                phone=edtPhone.getText().toString();
                email=edtGmail.getText().toString();
                pass=edtPass.getText().toString();
                passAgain=edtPassAgain.getText().toString();


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(DangKyActivity.this, "Vui long nhap email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(DangKyActivity.this, "Vui long nhap password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passAgain))
                {
                    Toast.makeText(DangKyActivity.this, "Vui long nhap password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(fullname))
                {
                    Toast.makeText(DangKyActivity.this, "Vui long nhap ho va ten", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    Toast.makeText(DangKyActivity.this, "Vui long nhap so dien thoai", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pass.equals(passAgain))
                {
                    Toast.makeText(DangKyActivity.this, "password không khớp với cái đã cho", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(DangKyActivity.this, "Tao tai khoan thanh cong",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user=mAuth.getCurrentUser();
                                    DocumentReference dr=firestore.collection("user").document(user.getUid());
                                    Map<String,Object> userInfo=new HashMap<>();
                                    userInfo.put("HovaTen",fullname);
                                    userInfo.put("SoDienThoai",phone);
                                    userInfo.put("UserEmail",email);
                                    userInfo.put("IsUser","1");
                                    dr.set(userInfo);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(DangKyActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}