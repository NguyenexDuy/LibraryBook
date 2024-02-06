package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.huflit.cnpm_th_quanandduy.R;

public class AdminActivity extends AppCompatActivity {

    Button btn_taoTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        btn_taoTaiKhoan=findViewById(R.id.btn_taoTaiKhoan);
        btn_taoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,CreateAccountAuthorActivity.class);
                startActivity(intent);
            }
        });
    }
}