package edu.huflit.cnpm_th_quanandduy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class SachTimKiemAdapter extends RecyclerView.Adapter<SachTimKiemAdapter.SachTimKiemViewHolder> {

    Context context;
    ArrayList<Sach> saches;

    public SachTimKiemAdapter(Context context, ArrayList<Sach> saches) {
        this.context = context;
        this.saches = saches;
    }

    @NonNull
    @Override
    public SachTimKiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sachtimkiem,parent,false);
        SachTimKiemViewHolder sachTimKiemViewHolder=new SachTimKiemViewHolder(view);
        return sachTimKiemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachTimKiemViewHolder holder, int position) {
        Sach sach=saches.get(position);
        holder.tv_tenSachTimKiem.setText(sach.getTenSach());
        holder.tv_tacGiaTimKiem.setText(sach.getTacGia());
        String imageUri= sach.getHinhSach();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.img_sachTimKiem);
    }

    @Override
    public int getItemCount() {
        return saches.size();
    }

    class SachTimKiemViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sachTimKiem;
        TextView tv_tenSachTimKiem,tv_tacGiaTimKiem;
        public SachTimKiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tacGiaTimKiem=itemView.findViewById(R.id.tv_tacGiaTimKiem);
            tv_tenSachTimKiem=itemView.findViewById(R.id.tv_tenSachTimKiem);
            img_sachTimKiem=itemView.findViewById(R.id.img_sachTimKiem);
        }
    }
}
