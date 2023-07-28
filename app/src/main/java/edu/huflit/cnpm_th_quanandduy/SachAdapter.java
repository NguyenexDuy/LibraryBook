package edu.huflit.cnpm_th_quanandduy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Reader;
import java.util.ArrayList;
import java.util.BitSet;

public class SachAdapter extends  RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    Context context;
    ArrayList<Sach> saches;
    FirebaseStorage storage=FirebaseStorage.getInstance();

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
        StorageReference storageReference=storage.getReference();
        StorageReference imgSach=storageReference.child(String.valueOf(sach.getHinhSach()));
        imgSach.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap=BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                holder.HinhSach.setImageBitmap(bitmap);
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
            HinhSach=itemView.findViewById(R.id.img_sach);
            TenSach=itemView.findViewById(R.id.tv_tenSach);
        }
    }
}
