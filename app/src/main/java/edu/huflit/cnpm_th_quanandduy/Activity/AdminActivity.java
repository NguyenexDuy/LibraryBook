
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
import edu.huflit.cnpm_th_quanandduy.fragment.DSSachFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.ThongBaoFragment;

public class AdminActivity extends AppCompatActivity {
    BottomNavigationView bnvAmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bnvAmin = findViewById(R.id.bnv_admin);
        bnvAmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                display(item.getItemId());
                return true;
            }
        });
        display(R.id.mnu_sach);
    }

    void display(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.mnu_sach:
                fragment = new DSSachFragment();
                break;
            case R.id.mnu_thongbao:
                fragment = new ThongBaoFragment();
                break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fAdmin, fragment);
        ft.commit();
    }
}