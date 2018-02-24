package com.ot.devicecheck.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ot.devicecheck.R;

/**
 * Created by Pranjal Gupta on 29-01-2018.
 */

public class CheckListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist,
                container, false);
        return view;
    }

}
