package edu.huflit.cnpm_th_quanandduy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class TimKiemSachAdapter extends RecyclerView.Adapter<TimKiemSachAdapter.TimKiemSachVH> implements Filterable {

    Context context;
    ArrayList<Sach> saches;
    ArrayList<Sach> sachesold;

    public TimKiemSachAdapter(Context context, ArrayList<Sach> saches) {
        this.context = context;
        this.saches = saches;
        this.sachesold = saches;
    }

    @NonNull
    @Override
    public TimKiemSachVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mnutimkiem,parent,false);
        TimKiemSachVH timKiemSachVH =new TimKiemSachVH(view);
        return timKiemSachVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemSachVH holder, int position) {
        Sach sach=saches.get(position);
        holder.tvTenSach.setText(sach.getTenSach());
        holder.tvGiaSach.setText(sach.getGiaSach());
        holder.tvLoaiSach.setText(sach.getLoaiSach());

        String imageUri= sach.getHinhSach();
        Glide.with(holder.itemView.getContext()).load(imageUri).into(holder.mHinhSach);
    }

    @Override
    public int getItemCount() {
        return saches.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    saches = sachesold;
                }
                else{
                    ArrayList<Sach> list = new ArrayList<>();
                    for(Sach sach : sachesold){
                        //laytheo ten
                        if(sach.getTenSach().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(sach);
                        }
                    }
                    saches = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = saches;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                saches = (ArrayList<Sach>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class TimKiemSachVH extends RecyclerView.ViewHolder {
        ImageView mHinhSach;
        TextView tvTenSach,tvGiaSach,tvLoaiSach;
        public TimKiemSachVH(@NonNull View itemView) {
            super(itemView);

            mHinhSach = itemView.findViewById(R.id.imgsachTK);
            tvTenSach = itemView.findViewById(R.id.tvTenSachTimKiem);
            tvGiaSach = itemView.findViewById(R.id.tvGiaSachTimKiem);
            tvLoaiSach = itemView.findViewById(R.id.tvLoaiSachTimKiem);
        }
    }
}
