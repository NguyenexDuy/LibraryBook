
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
import edu.huflit.cnpm_th_quanandduy.fragment.HomeAdminFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.MoneyAdminFragment;
import edu.huflit.cnpm_th_quanandduy.fragment.ThongBaoAdminFragment;

public class AuthorActivity extends AppCompatActivity {
    BottomNavigationView bnvAmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

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
                fragment = new HomeAdminFragment();
                break;
            case R.id.mnu_money:
                fragment=new MoneyAdminFragment();
            case R.id.mnu_setting:
                fragment = new ThongBaoAdminFragment();
                break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fAdmin, fragment);
        ft.commit();
    }
}