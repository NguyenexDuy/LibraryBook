package edu.huflit.cnpm_th_quanandduy.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import edu.huflit.cnpm_th_quanandduy.adapter.SachYeuThichAdapter;
import edu.huflit.cnpm_th_quanandduy.model.SachYeuThich;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YeuThichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YeuThichFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YeuThichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YeuThichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YeuThichFragment newInstance(String param1, String param2) {
        YeuThichFragment fragment = new YeuThichFragment();
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
        return inflater.inflate(R.layout.fragment_yeu_thich, container, false);
    }

    ArrayList<SachYeuThich> sachYeuThiches;
    RecyclerView rcv_SachYeuThich;
    FirebaseFirestore db;
    SachYeuThichAdapter sachYeuThichAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_SachYeuThich=view.findViewById(R.id.rcv_SachYeuThich);
        sachYeuThiches=new ArrayList<>();
        sachYeuThich();
        sachYeuThichAdapter=new SachYeuThichAdapter(getContext(),sachYeuThiches);
        rcv_SachYeuThich.setAdapter(sachYeuThichAdapter);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rcv_SachYeuThich.setLayoutManager(layoutManager);


    }
    private void sachYeuThich()
    {
        db=FirebaseFirestore.getInstance();
        db.collection("sachYeuThich").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String IdSachYeuThich=documentSnapshot.getId();
                    String IdSachGoc=(String) documentSnapshot.get("idSach");
                    String tenSach=(String) documentSnapshot.get("tenSach");
                    String loaiSach=(String) documentSnapshot.get("loaiSach");
                    String tacGia=(String) documentSnapshot.get("tacGia");
                    String giaSach=(String) documentSnapshot.get("giaSach");
                    String moTa=(String) documentSnapshot.get("mota");
                    String hinhSach=(String) documentSnapshot.get("hinhSach");
                    SachYeuThich sachYeuThich=new SachYeuThich(IdSachYeuThich,IdSachGoc,tenSach,loaiSach,tacGia,giaSach,moTa,hinhSach);
                    sachYeuThiches.add(sachYeuThich);
                }
                sachYeuThichAdapter.notifyDataSetChanged();
            }
        });
    }

}