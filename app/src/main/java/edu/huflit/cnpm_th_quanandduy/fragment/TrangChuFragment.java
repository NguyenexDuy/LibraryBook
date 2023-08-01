package edu.huflit.cnpm_th_quanandduy.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.TimKiemActivity;
import edu.huflit.cnpm_th_quanandduy.model.Sach;
import edu.huflit.cnpm_th_quanandduy.SachAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }


    ArrayList<Sach> sachesKinhDi;
    ArrayList<Sach> sachesTinhYeu;
    ArrayList<Sach> sachesTreem;
    RecyclerView rcv_SachKinhDi,rcv_SachTinhYeu,rcv_SachTreEm;
    SachAdapter sachAdapter1;
    SachAdapter sachAdapter2;
    SachAdapter sachAdapter3;
    FirebaseFirestore db;
    TextView img_search;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_SachKinhDi=view.findViewById(R.id.rcv_SachKinhDi);
        rcv_SachTinhYeu=view.findViewById(R.id.rcv_SachTinhYeu);
        rcv_SachTreEm=view.findViewById(R.id.rcv_SachTreEm);
        img_search=view.findViewById(R.id.img_search);
        sachesKinhDi =new ArrayList<>();
        sachesTinhYeu=new ArrayList<>();
        sachesTreem=new ArrayList<>();
        sachesTinhYeu();
        sachesKinhDi();
        sachesTreEm();
        sachAdapter1=new SachAdapter(getContext(), sachesKinhDi);
        sachAdapter2=new SachAdapter(getContext(),sachesTinhYeu);
        sachAdapter3=new SachAdapter(getContext(),sachesTreem);
        rcv_SachKinhDi.setAdapter(sachAdapter1);
        rcv_SachTinhYeu.setAdapter(sachAdapter2);
        rcv_SachTreEm.setAdapter(sachAdapter3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_SachKinhDi.setLayoutManager(layoutManager);
        rcv_SachTinhYeu.setLayoutManager(layoutManager2);
        rcv_SachTreEm.setLayoutManager(layoutManager3);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TimKiemActivity.class);
                startActivity(intent);
            }
        });
    }
    private void sachesKinhDi() {

        db=FirebaseFirestore.getInstance();
        db.collection("sach").whereEqualTo("LoaiSach","kinh dị").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String IdSach=documentSnapshot.getId();
                    String TenSach=(String)documentSnapshot.get("TenSach");
                    String GiaSach = (String) documentSnapshot.get("GiaSach");
                    String LoaiSach = (String) documentSnapshot.get("LoaiSach");
                    String TacGia = (String) documentSnapshot.get("TacGia");
                    String MoTa = (String) documentSnapshot.get("MoTa");
                    String HinhSach = (String) documentSnapshot.get("HinhSach");

                    Sach sach=new Sach(IdSach,TenSach,GiaSach,LoaiSach,TacGia,MoTa,HinhSach);
                    sachesKinhDi.add(sach);
                }
                sachAdapter1.notifyDataSetChanged();

            }
        });
    }
    private void sachesTinhYeu() {

        db=FirebaseFirestore.getInstance();
        db.collection("sach").whereEqualTo("LoaiSach","tình yêu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String IdSach=documentSnapshot.getId();
                    String TenSach=(String)documentSnapshot.get("TenSach");
                    String GiaSach = (String) documentSnapshot.get("GiaSach");
                    String LoaiSach = (String) documentSnapshot.get("LoaiSach");
                    String TacGia = (String) documentSnapshot.get("TacGia");
                    String MoTa = (String) documentSnapshot.get("MoTa");
                    String HinhSach = (String) documentSnapshot.get("HinhSach");

                    Sach sach=new Sach(IdSach,TenSach,GiaSach,LoaiSach,TacGia,MoTa,HinhSach);
                    sachesTinhYeu.add(sach);
                }
                sachAdapter2.notifyDataSetChanged();

            }
        });
    }
    private void sachesTreEm() {

        db=FirebaseFirestore.getInstance();
        db.collection("sach").whereEqualTo("LoaiSach","trẻ em").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String IdSach=documentSnapshot.getId();
                    String TenSach=(String)documentSnapshot.get("TenSach");
                    String GiaSach = (String) documentSnapshot.get("GiaSach");
                    String LoaiSach = (String) documentSnapshot.get("LoaiSach");
                    String TacGia = (String) documentSnapshot.get("TacGia");
                    String MoTa = (String) documentSnapshot.get("MoTa");
                    String HinhSach = (String) documentSnapshot.get("HinhSach");

                    Sach sach=new Sach(IdSach,TenSach,GiaSach,LoaiSach,TacGia,MoTa,HinhSach);
                    sachesTreem.add(sach);
                }
                sachAdapter3.notifyDataSetChanged();

            }
        });
    }

}