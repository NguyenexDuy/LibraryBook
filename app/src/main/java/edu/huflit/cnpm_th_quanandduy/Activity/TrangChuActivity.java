package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.fragment.CaNhanFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.HoaDonFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.TrangChuFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.TimKiemFragment;

public class TrangChuActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                display(item.getItemId());
                return true;
            }
        });
        display(R.id.home);
    }
    void display(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.home:
                fragment = new TrangChuFragment();
                break;
            case R.id.search_view_root:
                fragment = new TimKiemFragment();
                break;
            case R.id.setting:
                fragment = new CaNhanFragment();
                break;
            case R.id.favorite_book:
                fragment = new HoaDonFragment();
                break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}