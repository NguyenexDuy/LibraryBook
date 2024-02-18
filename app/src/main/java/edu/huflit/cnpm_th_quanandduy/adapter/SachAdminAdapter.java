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

public class SachAdminAdapter extends RecyclerView.Adapter<SachAdminAdapter.SachAdminViewHolder>{

    ArrayList<Sach> saches;
    Context context;

    public SachAdminAdapter(ArrayList<Sach> saches, Context context) {
        this.saches = saches;
        this.context = context;
    }

    @NonNull
    @Override
    public SachAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sachadmin,parent,false);
        SachAdminViewHolder sachAdminViewHolder=new SachAdminViewHolder(view);
        return sachAdminViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdminViewHolder holder, int position) {
        Sach sach=saches.get(position);
        holder.tv_tacphamAdmin.setText(sach.getTacGia());
        holder.tv_priceAdmin.setText(sach.getGiaSach());
        holder.tv_theloaiAdmin.setText(sach.getLoaiSach());
        String imageUri= sach.getHinhSach();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.img_sachAdminn);

    }

    @Override
    public int getItemCount() {
        return saches.size();
    }

    class SachAdminViewHolder extends RecyclerView.ViewHolder {

        ImageView img_sachAdminn;
        TextView tv_tacphamAdmin,tv_theloaiAdmin,tv_priceAdmin;
        public SachAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sachAdminn=itemView.findViewById(R.id.img_sachAdminn);
            tv_tacphamAdmin=itemView.findViewById(R.id.tv_tacphamAdmin);
            tv_theloaiAdmin=itemView.findViewById(R.id.tv_theloaiAdmin);
            tv_priceAdmin=itemView.findViewById(R.id.tv_priceAdmin);
        }
    }
}
