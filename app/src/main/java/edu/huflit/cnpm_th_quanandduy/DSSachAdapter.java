package edu.huflit.cnpm_th_quanandduy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class DSSachAdapter extends  RecyclerView.Adapter<DSSachAdapter.DSSachViewHolder> {

    Context context;
    ArrayList<Sach> sachesAdmin;

    public DSSachAdapter(Context context, ArrayList<Sach> sachesAdmin) {
        this.context = context;
        this.sachesAdmin = sachesAdmin;
    }

    @NonNull
    @Override
    public DSSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dssach,parent,false);
        DSSachViewHolder dsSachViewHolder=new DSSachViewHolder(view);
        return dsSachViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DSSachViewHolder holder, int position) {
        Sach sach=sachesAdmin.get(position);
        holder.tv_tenSachAdmin.setText(sach.getTenSach());
        String imageUri= sach.getHinhSach();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.img_sachAdmin);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSach(sach,position);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditSachActivity.class);
                intent.putExtra("EditSach",sach);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sachesAdmin.size();
    }

    class DSSachViewHolder extends RecyclerView.ViewHolder{
        ImageView img_sachAdmin;
        TextView tv_tenSachAdmin;
        ImageButton btnDelete,btnEdit;

        public DSSachViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sachAdmin=itemView.findViewById(R.id.img_sachAdmin);
            tv_tenSachAdmin=itemView.findViewById(R.id.tv_tenSachAdmin);
            btnDelete = itemView.findViewById(R.id.btndelete);
            btnEdit = itemView.findViewById(R.id.btnedit);


        }
    }
    public void deleteSach(Sach sach, int position) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docref = db.collection("sach").document(sach.getIdSach());
        docref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                sachesAdmin.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
