package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.huflit.cnpm_th_quanandduy.R;

public class MainActivity extends AppCompatActivity {

    Button btn_dangKy, btn_dangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_dangKy=findViewById(R.id.btn_dangKy);
        btn_dangNhap=findViewById(R.id.btn_dangNhap);
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        btn_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

    }
}