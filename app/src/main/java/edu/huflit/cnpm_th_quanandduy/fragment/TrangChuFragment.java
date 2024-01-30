package edu.huflit.cnpm_th_quanandduy.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.huflit.cnpm_th_quanandduy.Activity.NotificationActivity;
import edu.huflit.cnpm_th_quanandduy.Activity.TrangChuActivity;
import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.Activity.TimKiemActivity;
import edu.huflit.cnpm_th_quanandduy.adapter.PhotoAdapter;
import edu.huflit.cnpm_th_quanandduy.adapter.TacGiaAdapter;
import edu.huflit.cnpm_th_quanandduy.model.Firebase;
import edu.huflit.cnpm_th_quanandduy.model.Photo;
import edu.huflit.cnpm_th_quanandduy.model.Sach;
import edu.huflit.cnpm_th_quanandduy.adapter.SachAdapter;
import edu.huflit.cnpm_th_quanandduy.model.TacGia;
import me.relex.circleindicator.CircleIndicator;

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
        View view=inflater.inflate(R.layout.fragment_trang_chu, container, false);
        AnhXa(view);
        return view;
    }

    Firebase firebase;
    ArrayList<Sach> sachesTreem;
    ArrayList<TacGia> tacGias;
    ImageButton img_notifi;
    RecyclerView rcv_SachKinhDi,rcv_SachTinhYeu,rcv_SachTreEm,rcv_tacGia;
    RecyclerView rcv_sachPhoBien;
    ArrayList<Sach> saches;
    TacGiaAdapter tacGiaAdapter;
    SachAdapter sachAdapter;
    SachAdapter sachAdapter3;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    PhotoAdapter photoAdapter;
    FirebaseFirestore db;
//    TextView img_search;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoAdapter =new PhotoAdapter(getContext(), getListPhoto());
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        SetData();

        tacGias=new ArrayList<>();
        GetAllAuthor();
        tacGiaAdapter=new TacGiaAdapter(getContext(),tacGias);
        rcv_tacGia.setAdapter(tacGiaAdapter);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_tacGia.setLayoutManager(layoutManager1);



    }
    private void SetData(){
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_sachPhoBien.setLayoutManager(layoutManager1);
        firebase.GetAllSach(new Firebase.FirebaseCallback<Sach>() {
            @Override
            public void onCallback(ArrayList<Sach> list) {
                saches=list;
                sachAdapter=new SachAdapter(getContext(),saches);
                rcv_sachPhoBien.setAdapter(sachAdapter);
                sachAdapter.notifyDataSetChanged();
            }
        });
    }
    private void ActionNotifi()
    {
        img_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa(View view)
    {
        img_notifi=view.findViewById(R.id.img_notifi);
        viewPager=view.findViewById(R.id.view_pager);
        circleIndicator=view.findViewById(R.id.circle_center);
        rcv_sachPhoBien=view.findViewById(R.id.rcv_sachPhoBien);
        rcv_tacGia=view.findViewById(R.id.rcv_tacGia);
        firebase=new Firebase(getContext());

    }
    private List<Photo> getListPhoto(){
        List<Photo> list=new ArrayList<>();
        list.add(new Photo(R.drawable.picture));
        list.add(new Photo(R.drawable.picture1));
        list.add(new Photo(R.drawable.picture2));
        return list;
    }
    private void GetAllAuthor(){

        tacGias.add(new TacGia("a",R.drawable.tacgia));
        tacGias.add(new TacGia("a",R.drawable.tacgia));
        tacGias.add(new TacGia("a",R.drawable.tacgia));
       tacGias.add(new TacGia("a",R.drawable.tacgia));
    }



}