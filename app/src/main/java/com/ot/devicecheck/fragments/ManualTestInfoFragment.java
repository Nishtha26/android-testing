package com.ot.devicecheck.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ot.devicecheck.R;
import com.ot.devicecheck.adapters.DbListAdapter;
import com.ot.devicecheck.dataObject.SQLElement;
import com.ot.devicecheck.database.DatabaseHandler;

import java.util.List;

/**
 * Created by Pranjal Gupta on 27-12-2017.
 */

public class ManualTestInfoFragment extends Fragment {

    View view;
    public ManualTestInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_manualtest_info, container, false);

        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        List<SQLElement> dataList = dbHandler.getAllElements();

        ListView databaseList = (ListView) view.findViewById(R.id.databaseInfoList);
        DbListAdapter dbadapter = new DbListAdapter(getContext(),dataList);
        databaseList.setAdapter(dbadapter);


        return view;
    }
}
