package edu.huflit.cnpm_th_quanandduy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.HoaDonApdater;
import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.HoaDon;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HoaDonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoaDonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HoaDonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HoaDonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoaDonFragment newInstance(String param1, String param2) {
        HoaDonFragment fragment = new HoaDonFragment();
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
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }

    RecyclerView rvHoaDon;
    ArrayList<HoaDon>   hoaDons;
    HoaDonApdater hoaDonApdater;
    FirebaseFirestore db;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvHoaDon = view.findViewById(R.id.rvHoaDon);
        hoaDons = new ArrayList<>();

        hoaDonApdater = new HoaDonApdater(hoaDons,getContext());
        rvHoaDon.setAdapter(hoaDonApdater);
        rvHoaDon.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rvHoaDon.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        db = FirebaseFirestore.getInstance();
        db.collection("HoaDon").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String IDHoaDon = documentSnapshot.getId();
                    String IDSach = (String) documentSnapshot.get("IDSach");
                    String NgayMuon = (String) documentSnapshot.get("NgayMuon");
                    String NgayTra = (String) documentSnapshot.get("NgayTra");
                    String SoNgayMuon = (String) documentSnapshot.get("SoNgayMuon");
                    String TenSach = (String) documentSnapshot.get("TenSach");
                    String TongTien = (String) documentSnapshot.get("TongTien");
                    HoaDon hoaDon = new HoaDon(IDHoaDon,IDSach,TenSach,NgayMuon,NgayTra,SoNgayMuon,TongTien);
                    hoaDons.add(hoaDon);
                }
                hoaDonApdater.notifyDataSetChanged();
            }

        });
    }
}