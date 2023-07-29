package edu.huflit.cnpm_th_quanandduy.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.Sach;
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

    ArrayList<Sach> saches;
    RecyclerView rcv_Sach;
    SachAdapter sachAdapter;
    FirebaseFirestore db;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_Sach=view.findViewById(R.id.rcv_SachKinhDi);
        saches=new ArrayList<>();
        sachAdapter=new SachAdapter(getContext(), saches);
        rcv_Sach.setAdapter(sachAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_Sach.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        saches.clear();
        LayDuLieu();
    }

    private void LayDuLieu() {

        db=FirebaseFirestore.getInstance();
        db.collection("sach").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                    saches.add(sach);
                }
                sachAdapter.notifyDataSetChanged();

            }
        });
    }

}