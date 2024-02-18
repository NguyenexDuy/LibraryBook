package edu.huflit.cnpm_th_quanandduy.fragment;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
import edu.huflit.cnpm_th_quanandduy.adapter.SachAdapter;
import edu.huflit.cnpm_th_quanandduy.adapter.SachTimKiemAdapter;
import edu.huflit.cnpm_th_quanandduy.adapter.SachYeuThichAdapter;
import edu.huflit.cnpm_th_quanandduy.model.Firebase;
import edu.huflit.cnpm_th_quanandduy.model.Sach;
import edu.huflit.cnpm_th_quanandduy.model.SachYeuThich;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimKiemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimKiemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimKiemFragment() {
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
    public static TimKiemFragment newInstance(String param1, String param2) {
        TimKiemFragment fragment = new TimKiemFragment();
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
        View view=inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        Anhxa(view);
        return view;
    }

    SearchView searchView;
    RecyclerView rcv_sachTimKiem;
    SachTimKiemAdapter sachAdapter;
    ArrayList<Sach> saches;
    ArrayList<Sach> sachSearch;

    Sach sach;
    Firebase firebase;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView.setIconified(false);
        searchView.clearFocus();
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcv_sachTimKiem.setLayoutManager(layoutManager1);
        firebase.GetAllSach(new Firebase.FirebaseCallback<Sach>() {
            @Override
            public void onCallback(ArrayList<Sach> list) {
                saches=list;
                sachAdapter= new SachTimKiemAdapter(getContext(),saches);
                rcv_sachTimKiem.setAdapter(sachAdapter);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sachSearch=new ArrayList<>();
                if (query.length()>0)
                {
                    for (int i=0;i<saches.size();i++)
                    {
                        if(saches.get(i).getTenSach().toUpperCase().contains(query.toUpperCase())||saches.get(i).getTacGia().toUpperCase().contains(query.toUpperCase()))
                        {
                             Sach sach=new Sach();
                            sach.setTenSach(saches.get(i).getTenSach());
                            sach.setTacGia(saches.get(i).getTacGia());
                            sach.setHinhSach(saches.get(i).getHinhSach());
                            sachSearch.add(sach);
                        }
                    }
                    firebase.GetAllSach(new Firebase.FirebaseCallback<Sach>() {
                        @Override
                        public void onCallback(ArrayList<Sach> list) {
                            saches=list;
                            sachAdapter= new SachTimKiemAdapter(getContext(),sachSearch);
                            rcv_sachTimKiem.setAdapter(sachAdapter);
                        }
                    });

                }
                else {
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rcv_sachTimKiem.setLayoutManager(layoutManager1);
                    firebase.GetAllSach(new Firebase.FirebaseCallback<Sach>() {
                        @Override
                        public void onCallback(ArrayList<Sach> list) {
                            saches=list;
                            sachAdapter= new SachTimKiemAdapter(getContext(),saches);
                            rcv_sachTimKiem.setAdapter(sachAdapter);
                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sachSearch=new ArrayList<>();
                if (newText.length()>0)
                {
                    for (int i=0;i<saches.size();i++)
                    {
                        if(saches.get(i).getTenSach().toUpperCase().contains(newText.toUpperCase())||saches.get(i).getTacGia().toUpperCase().contains(newText.toUpperCase()))
                        {
                            Sach sach=new Sach();
                            sach.setTenSach(saches.get(i).getTenSach());
                            sach.setTacGia(saches.get(i).getTacGia());
                            sach.setHinhSach(saches.get(i).getHinhSach());
                            sachSearch.add(sach);
                        }
                    }
                    firebase.GetAllSach(new Firebase.FirebaseCallback<Sach>() {
                        @Override
                        public void onCallback(ArrayList<Sach> list) {
                            saches=list;
                            sachAdapter= new SachTimKiemAdapter(getContext(),sachSearch);
                            rcv_sachTimKiem.setAdapter(sachAdapter);
                        }
                    });

                }
                else {
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rcv_sachTimKiem.setLayoutManager(layoutManager1);
                    firebase.GetAllSach(new Firebase.FirebaseCallback<Sach>() {
                        @Override
                        public void onCallback(ArrayList<Sach> list) {
                            saches=list;
                            sachAdapter= new SachTimKiemAdapter(getContext(),saches);
                            rcv_sachTimKiem.setAdapter(sachAdapter);
                        }
                    });
                }
                return false;
            }
        });

    }

    private void Anhxa(View view)
    {
        searchView=view.findViewById(R.id.searchView);
        rcv_sachTimKiem=view.findViewById(R.id.rcv_sachTimKiem);
        firebase=new Firebase(getContext());

    }


}