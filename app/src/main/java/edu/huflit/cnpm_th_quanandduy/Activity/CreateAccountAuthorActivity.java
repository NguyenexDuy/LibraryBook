package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import edu.huflit.cnpm_th_quanandduy.model.Firebase;

public class CreateAccountAuthorActivity extends AppCompatActivity {

    Button btn_tao;
    EditText edt_gmail,edt_matkhau,edt_ten,edt_tuoi,edt_kinhngiem;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_author);
        AnhXa();
        btn_tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadData();
            }
        });
    }
    private void AnhXa()
    {
        btn_tao=findViewById(R.id.btn_tao);
        edt_gmail=findViewById(R.id.edt_gmail);
        edt_matkhau=findViewById(R.id.edt_matkhau);
        edt_ten=findViewById(R.id.edt_ten);
        edt_tuoi=findViewById(R.id.edt_tuoi);
        edt_kinhngiem=findViewById(R.id.edt_kinhngiem);
        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();


    }

    private void UploadData()
    {
        String gmail=edt_gmail.getText().toString();
        String maukhau=edt_matkhau.getText().toString();
        String ten=edt_ten.getText().toString();
        String tuoi=edt_tuoi.getText().toString();
        String kinh_nghiem=edt_kinhngiem.getText().toString();
        String picture="";
        String status="A";

        mAuth.createUserWithEmailAndPassword(gmail,maukhau)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            firebaseUser=mAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();
                            Toast.makeText(CreateAccountAuthorActivity.this, "Tao thanh cong", Toast.LENGTH_SHORT).show();
                            Map<String, Object> tacgia=new HashMap<>();
                            tacgia.put("Gmail",gmail);
                            tacgia.put("PassWord",maukhau);
                            tacgia.put("Ten",ten);
                            tacgia.put("Age",tuoi);
                            tacgia.put("Experience", kinh_nghiem);
                            tacgia.put("Avatar",picture);
                            tacgia.put("Status",status);

                            firestore.collection("User")
                                    .document(userId)
                                    .set(tacgia)
                                    .addOnSuccessListener(documentReference -> {
                                        Toast.makeText(CreateAccountAuthorActivity.this, "Tải lên thành công", Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(CreateAccountAuthorActivity.this, "Tải lên thất bại", Toast.LENGTH_SHORT).show();
                                    });
                        }
                        else
                        {
                            Toast.makeText(CreateAccountAuthorActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}