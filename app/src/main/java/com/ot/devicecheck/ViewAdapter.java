package com.ot.devicecheck;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pranjal Gupta on 15-10-2017.
 */

public class ViewAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;

    Context context;
    List<String>  listvalue;
    List<String> listname;



    public ViewAdapter (Context context, List<String> listname, List<String> listvalue){
        this.context=context;
        this.listname=listname;
        this.listvalue=listvalue;
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
            view = inflater.inflate(R.layout.view_adapter, null);

        TextView tv1 = (TextView)view.findViewById(R.id.text_name);
        TextView tv2 = (TextView)view.findViewById(R.id.text_value);


        String name = listname.get(i);
        String value = listvalue.get(i);

        tv1.setText(name);
        tv2.setText(value);
        return view;
    }
    }
