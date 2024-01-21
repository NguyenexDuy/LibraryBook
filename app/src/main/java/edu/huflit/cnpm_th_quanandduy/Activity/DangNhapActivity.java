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

public class DangNhapActivity extends AppCompatActivity {

    EditText edtGmaillogin, edtPassLogin;
    Button btnLogin;
    TextView registerNow;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtGmaillogin=findViewById(R.id.edtGmaillogin);
        edtPassLogin=findViewById(R.id.edtPassLogin);
        btnLogin=findViewById(R.id.btnLogin);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        registerNow=findViewById(R.id.registerNow);
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DangKyActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass, passAgain;
                email=edtGmaillogin.getText().toString();
                pass=edtPassLogin.getText().toString();

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

                mAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        checkRoleUser(authResult.getUser().getUid());
                    }
                });
            }
        });
    }

    private void checkRoleUser(String uid) {
        DocumentReference dr=firestore.collection("user").document(uid);
        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("Tag","onSucess"+documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin")!=null)
                {
                    Toast.makeText(DangNhapActivity.this, "Đã vào trang admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    finish();
                } else if (documentSnapshot.getString("IsUser")!=null) {

                    Intent intent=new Intent(DangNhapActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }
//                if (documentSnapshot.getString("isUser")!=null)
//                {
//                    Intent intent=new Intent(DangNhapActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
            }
        });


    }
}
