package edu.huflit.cnpm_th_quanandduy;

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

public class DangNhapActivity extends AppCompatActivity {

    EditText edtGmaillogin, edtPassLogin;
    Button btnLogin;
    TextView registerNow;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtGmaillogin=findViewById(R.id.edtGmaillogin);
        edtPassLogin=findViewById(R.id.edtPassLogin);
        btnLogin=findViewById(R.id.btnLogin);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
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
                progressBar.setVisibility(View.GONE);
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

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(DangNhapActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                        else{
                            if (email.equals("admin@gmail.com") && pass.equals("123456")) {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập Admin thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(DangNhapActivity.this, AdminActivity.class);
                            startActivity(i);
                            }
                            else {
                                Toast.makeText(DangNhapActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
