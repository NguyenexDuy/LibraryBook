package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class PhieuMuongSachActivity extends AppCompatActivity {


    ImageView img_sachphieumuon;

    TextView tvTenSachPhieuMuon,tvGiaSachPhieuMuon,tv_ngayphieumuon,tv_songaymuonPhieuSach,tvTongTienPhieuMuon;
    ImageButton btnCalendar;
    Button btnThueSachPhieuMuon;
    String formattedDate;
    int monthsDiff;
    FirebaseFirestore db;
    Sach sach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muong_sach);
        sach = (Sach) getIntent().getSerializableExtra("MuonSach");
        AnhXa();
        //
        tvTenSachPhieuMuon.setText(sach.getTenSach());
        tvGiaSachPhieuMuon.setText(sach.getGiaSach());
        //số ngày hiện tại
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDate = date.format(formatter);
        tv_ngayphieumuon.setText(formattedDate);
        btnThueSachPhieuMuon.setEnabled(false);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ngày chọn từ lịch
                showdialogrentdate();

            }
        });
        btnThueSachPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDSach = sach.getIdSach();
                String TenSach = (String) tvTenSachPhieuMuon.getText();
                String NgayMuon = formattedDate;
                String NgayTra = (String) tv_ngayphieumuon.getText();
                String SoNgayMuon = String.valueOf(Integer.valueOf((String) tv_songaymuonPhieuSach.getText()));
                String TongTien = String.valueOf(Integer.valueOf((String) tvTongTienPhieuMuon.getText()));

                Map<String,Object> HoaDon = new HashMap<>();
                HoaDon.put("IDSach",IDSach);
                HoaDon.put("TenSach",TenSach);
                HoaDon.put("NgayMuon",NgayMuon);
                HoaDon.put("NgayTra",NgayTra);
                HoaDon.put("SoNgayMuon",SoNgayMuon);
                HoaDon.put("TongTien",TongTien);

                db = FirebaseFirestore.getInstance();
                db.collection("HoaDon").add(HoaDon).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(PhieuMuongSachActivity.this, "Đã Thuê", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });


    }
    public void HamXuLy(){
        String date1 = formattedDate;
        String date2 = (String) tv_ngayphieumuon.getText();

//                String date1 = "29/7/2022";
//                String date2 = "29/08/2023";
        String[] parts2 = date1.split("/");
        int day2 = Integer.parseInt(parts2[0]);
        int month2 = Integer.parseInt(parts2[1]);
        int year2 = Integer.parseInt(parts2[2]);

        String[] parts = date2.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        LocalDate ngay1 = LocalDate.of(year, month, day); // Ngày thứ nhất
        LocalDate dayay = LocalDate.of(year2, month2, day2); // Ngày thứ hai
        monthsDiff = (int) ChronoUnit.DAYS.between(dayay,ngay1); // Số tháng chênh lệch
        tv_songaymuonPhieuSach.setText(monthsDiff+ "");
        btnThueSachPhieuMuon.setEnabled(true);
        if (Integer.valueOf((String) tv_songaymuonPhieuSach.getText()) <= 0){
            Toast.makeText(PhieuMuongSachActivity.this, "Số ngày phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            btnThueSachPhieuMuon.setEnabled(false);
            tv_songaymuonPhieuSach.setText("0");
            return;
        }


    }
    private void AnhXa() {
        img_sachphieumuon = findViewById(R.id.img_sachphieumuon);
        Glide.with(PhieuMuongSachActivity.this).load(sach.getHinhSach()).into(img_sachphieumuon);

        tvTenSachPhieuMuon = findViewById(R.id.tv_TenSachphieumuon);
        tv_ngayphieumuon = findViewById(R.id.tv_ngayphieumuon);
        tvGiaSachPhieuMuon = findViewById(R.id.tvgiasachphieumuon);
        tv_songaymuonPhieuSach = findViewById(R.id.tv_songaymuonPhieuSach);
        btnCalendar = findViewById(R.id.btn_calendar);
        btnThueSachPhieuMuon = findViewById(R.id.btnThueSachPhieuMuon);
        tvTongTienPhieuMuon = findViewById(R.id.tvTongTienPhieuMuon);
    }
    public Integer TinhTongTien(){
        Integer songay = Integer.valueOf((String)tv_songaymuonPhieuSach.getText());
        Integer gia = Integer.valueOf((String) tvGiaSachPhieuMuon.getText()) ;
        return songay*gia;
    }
    private void showdialogrentdate(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog rentDatePickerDialog = new DatePickerDialog(PhieuMuongSachActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String rentDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        tv_ngayphieumuon.setText(rentDate);
                        HamXuLy();
                        tvTongTienPhieuMuon.setText(TinhTongTien().toString());
                    }
                }, mYear, mMonth, mDay);
        rentDatePickerDialog.setTitle("Chọn ngày thuê");
        rentDatePickerDialog.show();
    }
}

