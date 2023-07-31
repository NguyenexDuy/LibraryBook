package edu.huflit.cnpm_th_quanandduy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class SachAdapter extends  RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    Context context;
    ArrayList<Sach> saches;
    FirebaseStorage storage=FirebaseStorage.getInstance();
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();

    public SachAdapter(Context context, ArrayList<Sach> saches) {
        this.context = context;
        this.saches = saches;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach,parent,false);
        SachViewHolder sachViewHolder=new SachViewHolder(view);


        return sachViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach=saches.get(position);
        holder.TenSach.setText(sach.getTenSach());
        String imageUri= sach.getHinhSach();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.HinhSach);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ChiTietSachActivity.class);
                intent.putExtra("ThongTinSach",sach);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return saches.size();
    }

    class SachViewHolder extends RecyclerView.ViewHolder{

        ImageView HinhSach;
        TextView TenSach;

        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            HinhSach=itemView.findViewById(R.id.img_sachAdmin);
            TenSach=itemView.findViewById(R.id.tv_tenSachAdmin);

        }
    }


}
