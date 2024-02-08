package edu.huflit.cnpm_th_quanandduy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.TacGia;

public class TacGiaAdapter extends RecyclerView.Adapter<TacGiaAdapter.TacGiaViewHolder>{

    Context context;
    ArrayList<TacGia> tacGias;

    public TacGiaAdapter(Context context, ArrayList<TacGia> tacGias) {
        this.context = context;
        this.tacGias = tacGias;
    }

    @NonNull
    @Override
    public TacGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tacgia,parent,false);
        TacGiaViewHolder tacGiaViewHolder=new TacGiaViewHolder(view);
        return tacGiaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TacGiaViewHolder holder, int position) {
        TacGia tacGia=tacGias.get(position);
        String imageUri= tacGia.getImg_TacGia();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.img_tacGia);
    }

    @Override
    public int getItemCount() {
        return tacGias.size();
    }

    class TacGiaViewHolder extends RecyclerView.ViewHolder {
        ImageView img_tacGia;
        public TacGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            img_tacGia=itemView.findViewById(R.id.img_tacGia);
        }
    }
}
