package com.ot.devicecheck.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ot.devicecheck.R;
import com.ot.devicecheck.model.CheckListItem;

import java.util.ArrayList;

/**
 * Created by Anurag on 26-02-2018.
 */

public class CheckListAdapter2 extends BaseAdapter {

    private ArrayList<CheckListItem> items;
    private Context context;

    public CheckListAdapter2(Context context, ArrayList<CheckListItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_cl_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CheckListItem currentItem = (CheckListItem) getItem(position);
        viewHolder.itemName.setText(currentItem.getText());
        viewHolder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.itemName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE
                Log.d("Keypressed ", "event ::" + keyCode);
                if (keyCode == KeyEvent.KEYCODE_DEL && viewHolder.itemName.getText().length() == 0 && position < items.size()) {
                    //this is for backspace
                    items.remove(position);
                    notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewHolder.itemName.setPaintFlags(viewHolder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    items.get(position).setChecked(true);
                } else {
                    viewHolder.itemName.setPaintFlags(viewHolder.itemName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    items.get(position).setChecked(false);
                }
            }
        });
        viewHolder.checkBox.setChecked(currentItem.isChecked());


        return convertView;

    }

    private class ViewHolder {
        EditText itemName;
        ImageButton ivRemove;
        CheckBox checkBox;

        public ViewHolder(View view) {
            itemName = (EditText) view.findViewById(R.id.editText);
            itemName.setFilters(new InputFilter[]{filter});
            ivRemove = (ImageButton) view.findViewById(R.id.ivClose);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            //itemDescription = (TextView) view.findViewById(R.id.text_view_item_description);
        }
    }


    private String blockCharacterSet = "~#^|$%&*!,";

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };


}
