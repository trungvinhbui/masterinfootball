package com.trungvinh.masterinfootball;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_BangTin:
                    toolbar.setTitle("Bang Tin");
                    fragment = new BangTinFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_KetQua:
                    toolbar.setTitle("Ket Qua");
                    fragment = new KetQuaFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_Clip:
                    toolbar.setTitle("Clip");
                    fragment = new ClipFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_DuDoan:
                    toolbar.setTitle("Du Doan");
                    fragment = new DuDoanFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_CauThu:
                    toolbar.setTitle("Cau Thu");
                    fragment = new CauThuFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Bang Tin");
    }
}
