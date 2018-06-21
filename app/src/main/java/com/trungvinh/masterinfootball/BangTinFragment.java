package com.trungvinh.masterinfootball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BangTinFragment extends Fragment {

    public BangTinFragment() {
        // Required empty public constructor
    }

    public static BangTinFragment newInstance(String param1, String param2) {
        BangTinFragment fragment = new BangTinFragment();
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
        return inflater.inflate(R.layout.fragment_bang_tin, container, false);
    }
}
