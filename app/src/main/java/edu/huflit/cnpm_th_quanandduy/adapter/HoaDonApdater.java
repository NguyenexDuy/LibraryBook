package edu.huflit.cnpm_th_quanandduy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.HoaDon;

public class HoaDonApdater extends RecyclerView.Adapter<HoaDonApdater.HoaDonVH> {

    ArrayList<HoaDon> hoaDons;

    public HoaDonApdater(ArrayList<HoaDon> hoaDons, Context context) {
        this.hoaDons = hoaDons;
        this.context = context;
    }

    Context context;


    @NonNull
    @Override
    public HoaDonVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon,parent,false);
        HoaDonVH hoaDonVH= new HoaDonVH(view);
        return hoaDonVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonVH holder, int position) {
        HoaDon hoaDon = hoaDons.get(position);
        holder.tvTenHoaDon.setText(hoaDon.getTenSach());

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        String NgayTra = hoaDon.getNgayTra();
//
        String[] parts2 = formattedDate.split("/");
        int day2 = Integer.parseInt(parts2[0]);
        int month2 = Integer.parseInt(parts2[1]);
        int year2 = Integer.parseInt(parts2[2]);

        String[] parts = NgayTra.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        LocalDate ngay1 = LocalDate.of(year, month, day); // Ngày thứ nhất
        LocalDate dayay = LocalDate.of(year2, month2, day2); // Ngày thứ hai
        int ngayconlai = (int) ChronoUnit.DAYS.between(dayay,ngay1); // Số tháng chênh lệch

        holder.tvNgayConLai.setText(ngayconlai+" ngày còn lại");
    }

    @Override
    public int getItemCount() {
        return hoaDons.size();
    }


    class HoaDonVH extends RecyclerView.ViewHolder {
        TextView tvTenHoaDon,tvNgayConLai;
        public HoaDonVH(@NonNull View itemView) {
            super(itemView);
            tvTenHoaDon = itemView.findViewById(R.id.tvTenSachHD);
            tvNgayConLai = itemView.findViewById(R.id.tv_ngayconlai);
        }
    }
}
