package com.ot.devicecheck;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pranjal Gupta on 10-11-2017.
 */

public class DbListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;

    Context context;
    List<SQLElement>  listvalue;
    List<SQLElement> listname;



    public DbListAdapter (Context context, List<SQLElement> listname){
        this.context=context;
        this.listname=listname;
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
            view = inflater.inflate(R.layout.dblist_adapter, null);

        TextView tv1 = (TextView)view.findViewById(R.id.element_name);
        ImageView iv2 = (ImageView) view.findViewById(R.id.imageWorking);
        TextView tv2 = (TextView) view.findViewById(R.id.time);

        SQLElement data = listname.get(i);
        tv1.setText(data.getElement());
        if (data.getVerdict().contentEquals("Working"))
        {
            iv2.setImageResource(R.drawable.checkmark);
        }
        else
        {
            iv2.setImageResource(R.drawable.redcross);
        }

        // Converting timestamp into x ago format
        final CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(data.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        tv2.setText(timeAgo);

        return view;
    }
}
