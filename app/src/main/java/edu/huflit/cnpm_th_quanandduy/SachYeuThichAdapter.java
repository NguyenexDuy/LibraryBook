package edu.huflit.cnpm_th_quanandduy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.model.SachYeuThich;

public class SachYeuThichAdapter extends RecyclerView.Adapter<SachYeuThichAdapter.SachYeuThichViewHolder> {

    Context context;
    ArrayList<SachYeuThich> sachYeuThiches;

    public SachYeuThichAdapter(Context context, ArrayList<SachYeuThich> sachYeuThiches) {
        this.context = context;
        this.sachYeuThiches = sachYeuThiches;
    }

    @NonNull
    @Override
    public SachYeuThichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sachyeuthich,parent,false);
        SachYeuThichViewHolder sachYeuThichViewHolder=new SachYeuThichViewHolder(view);
        return sachYeuThichViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachYeuThichViewHolder holder, int position) {
        SachYeuThich sachYeuThich=sachYeuThiches.get(position);
        holder.tv_tenSachYeuThich.setText(sachYeuThich.getTenSach());
        String imageUri= sachYeuThich.getHinhSach();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.img_sachYeuThich);
        holder.img_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.img_liked.setBackgroundResource(R.drawable.like);
                deleteSachYeuThich(sachYeuThich,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ChiTietSachActivity.class);
                intent.putExtra("ThongTinSach",sachYeuThich);
                context.startActivity(intent);
            }
        });

    }
    public void deleteSachYeuThich(SachYeuThich sachYeuThich,int position)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docref = db.collection("sachYeuThich").document(sachYeuThich.getIdSachYeuThich());
        docref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                sachYeuThiches.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Đã xóa khỏi danh sách phát", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sachYeuThiches.size();
    }

    class SachYeuThichViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sachYeuThich;
        TextView tv_tenSachYeuThich;
        ImageView img_liked;
        public SachYeuThichViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sachYeuThich=itemView.findViewById(R.id.img_sachYeuThich);
            tv_tenSachYeuThich=itemView.findViewById(R.id.tv_tenSachYeuThich);
            img_liked=itemView.findViewById(R.id.img_liked);

        }
    }
}
