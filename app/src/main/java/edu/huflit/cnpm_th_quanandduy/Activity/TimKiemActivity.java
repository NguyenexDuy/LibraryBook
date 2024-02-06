package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.adapter.TimKiemSachAdapter;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class TimKiemActivity extends AppCompatActivity {

    RecyclerView rvSach;
    ArrayList<Sach> saches;
    TimKiemSachAdapter timKiemSachAdapter;
    FirebaseFirestore db;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        rvSach = findViewById(R.id.rvtimkiemsach);
        saches = new ArrayList<>();

        timKiemSachAdapter = new TimKiemSachAdapter(TimKiemActivity.this,saches);
        rvSach.setAdapter(timKiemSachAdapter);
        rvSach.setLayoutManager(new LinearLayoutManager(TimKiemActivity.this,LinearLayoutManager.VERTICAL,false));
        rvSach.addItemDecoration(new DividerItemDecoration(TimKiemActivity.this,DividerItemDecoration.VERTICAL));
        db=FirebaseFirestore.getInstance();
        db.collection("sach").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
//                    String IdSach=documentSnapshot.getId();
//                    String TenSach=(String)documentSnapshot.get("TenSach");
//                    String GiaSach = (String) documentSnapshot.get("GiaSach");
//                    String LoaiSach = (String) documentSnapshot.get("LoaiSach");
//                    String TacGia = (String) documentSnapshot.get("TacGia");
//                    String MoTa = (String) documentSnapshot.get("MoTa");
//                    String HinhSach = (String) documentSnapshot.get("HinhSach");
//                    Sach sach=new Sach(IdSach,TenSach,GiaSach,LoaiSach,TacGia,MoTa,HinhSach);
//                    saches.add(sach);
                }
                timKiemSachAdapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

//        setup vai thuoc tinh
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                timKiemSachAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                timKiemSachAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }
}