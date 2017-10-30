package com.ot.devicecheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranjal Gupta on 30-10-2017.
 */

public class ManualTest_Adapter extends BaseAdapter {


    private LayoutInflater inflater;

    Context context;
    List<String> listname;
    Integer[] icon;


    public ManualTest_Adapter(Context context, List<String> listname, Integer[] icon) {
        this.context = context;
        this.listname = listname;
        this.icon = icon;
    }

    @Override
    public int getCount() {
        return listname.size();
    }

    @Override
    public Object getItem(int i) {
        return listname.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.manual_view, null);

        ImageView iv1 = (ImageView) view.findViewById(R.id.icon);
        TextView tv1 = (TextView) view.findViewById(R.id.value);


        String name = listname.get(i);
        int iconvalue = icon[i];

        tv1.setText(name);
        iv1.setImageResource(iconvalue);
        return view;
    }
}
