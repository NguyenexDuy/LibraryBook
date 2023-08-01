package edu.huflit.cnpm_th_quanandduy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.huflit.cnpm_th_quanandduy.fragment.CaNhanFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.HoaDonFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.TrangChuFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.YeuThichFragment;

public class MainActivity extends AppCompatActivity {

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
        display(R.id.mnu_trangchu);
    }
    void display(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.mnu_trangchu:
                fragment = new TrangChuFragment();
                break;
            case R.id.mnu_favorite:
                fragment = new YeuThichFragment();
                break;
            case R.id.mnu_canhan:
                fragment = new CaNhanFragment();
                break;
            case R.id.mnu_hoadon:
                fragment = new HoaDonFragment();
                break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}