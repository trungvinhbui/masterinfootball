package com.trungvinh.masterinfootball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CauThuFragment extends Fragment {

    public CauThuFragment() {
        // Required empty public constructor
    }

    public static CauThuFragment newInstance(String param1, String param2) {
        CauThuFragment fragment = new CauThuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cau_thu, container, false);
    }
}
