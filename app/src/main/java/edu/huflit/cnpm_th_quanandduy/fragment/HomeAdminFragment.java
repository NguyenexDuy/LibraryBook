package edu.huflit.cnpm_th_quanandduy.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.Activity.AddSachActivity;
import edu.huflit.cnpm_th_quanandduy.ItemTouchHelperListener;
import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.RecycleViewTouchHelper;
import edu.huflit.cnpm_th_quanandduy.adapter.SachAdminAdapter;
import edu.huflit.cnpm_th_quanandduy.model.Firebase;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAdminFragment extends Fragment implements ItemTouchHelperListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DSSachFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeAdminFragment newInstance(String param1, String param2) {
        HomeAdminFragment fragment = new HomeAdminFragment();
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
        return inflater.inflate(R.layout.fragment_home_admin, container, false);
    }

    ArrayList<Sach> saches;
    RecyclerView rcv_sach;
    ImageButton btnAddSach,img_avtAdmin,img_notifi;
    TextView tv_tenTacGiaAdmin;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Firebase firebase;
    String author;
    SachAdminAdapter sachAdminAdapter;
    ProgressBar progess;
    LinearLayout root_View;
    int count=0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);
        if(firebaseUser!=null)
        {
             author=firebaseUser.getUid();


                DocumentReference admin=db.collection("User").document(author);
                admin.get().addOnSuccessListener(documentSnapshot -> {
                    if(documentSnapshot.exists())
                    {

                        tv_tenTacGiaAdmin.setText(documentSnapshot.getString("Ten"));
                    }
                    else {
                        tv_tenTacGiaAdmin.setText("User");
                    }
                });

                SetData();

            btnAddSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), AddSachActivity.class));
                }
            });

        }

    }
    private void SetData()
    {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcv_sach.setLayoutManager(layoutManager1);
        firebase.GetAllSachOfAdmin(author,new Firebase.FirebaseCallback<Sach>() {
            @Override
            public void onCallback(ArrayList<Sach> list) {
                saches=list;
                sachAdminAdapter=new SachAdminAdapter(saches,getContext());
                rcv_sach.setAdapter(sachAdminAdapter);
            }
        });

    }

    private void AnhXa(View view)
    {
        img_avtAdmin=view.findViewById(R.id.img_avtAdmin);
        img_notifi=view.findViewById(R.id.img_notifi);
        tv_tenTacGiaAdmin=view.findViewById(R.id.tv_tenTacGiaAdmin);
        btnAddSach = view.findViewById(R.id.btnThemSachAdmin);
        db=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        rcv_sach=view.findViewById(R.id.rvDSSach);
        firebase= new Firebase(getContext());
        root_View=view.findViewById(R.id.root_View);
        ItemTouchHelper.SimpleCallback simpleCallback=new RecycleViewTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcv_sach);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof SachAdminAdapter.SachAdminViewHolder){
            String nameDeleted=saches.get(viewHolder.getAbsoluteAdapterPosition()).getTenSach();

            final Sach sach=saches.get(viewHolder.getAbsoluteAdapterPosition());
            final int indexDelete=viewHolder.getAdapterPosition();

            //remove
            sachAdminAdapter.remove(indexDelete);
            Snackbar snackbar=Snackbar.make(root_View,nameDeleted+" removed!",Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachAdminAdapter.undoItem(sach,indexDelete);
                    if(indexDelete==0||indexDelete==saches.size()-1){
                        rcv_sach.scrollToPosition(indexDelete);
                    }
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}