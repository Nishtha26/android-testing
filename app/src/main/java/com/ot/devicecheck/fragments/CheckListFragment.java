package com.ot.devicecheck.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.devicecheck.R;
import com.ot.devicecheck.adapters.CheckListAdapter;
import com.ot.devicecheck.adapters.CheckListAdapter2;
import com.ot.devicecheck.model.CheckListItem;

import java.util.ArrayList;

/**
 * Created by Pranjal Gupta on 29-01-2018.
 */

public class CheckListFragment extends Fragment{
    private static final String TAG = CheckListFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private RelativeLayout rlSL;
    boolean editing_enable = false;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CheckListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CheckListFragment newInstance(int columnCount) {
        CheckListFragment fragment = new CheckListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private View view;
    private EditText etUserList;
    private LinearLayout topBar;
    private ImageButton btnEdit, btnRemove;
    // Array of strings...
    private ListView simpleList;
    private ArrayList<CheckListItem> sList = new ArrayList<CheckListItem>();
    private CheckListAdapter2 slAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_checklist, container, false);
            setHasOptionsMenu(true);
            simpleList = (ListView) view.findViewById(R.id.lvSL);
            LayoutInflater inf = getActivity().getLayoutInflater();
            ViewGroup header = (ViewGroup) inf.inflate(R.layout.row_add_item, simpleList, false);
            simpleList.addHeaderView(header, null, false);
            slAdapter = new CheckListAdapter2(getActivity(), sList);
            simpleList.setAdapter(slAdapter);
            final EditText etText = (EditText) header.findViewById(R.id.editText);
            etText.setFilters(new InputFilter[]{filter});
            etText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == 6 && !TextUtils.isEmpty(v.getText().toString())) {
                        addItem(v.getText().toString());
                        etText.getText().clear();
                        return true;
                    }
                    return false;
                }
            });
            simpleList.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    //Log.d(TAG, "Scrolled chnaged1 :: "+scrollState);
                    View mview = getActivity().getCurrentFocus();
                    int viewPos = view.getPositionForView(mview);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                }
            });

        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void addItem(String itemName) {
        sList.add(0,new CheckListItem(itemName));
        slAdapter.notifyDataSetChanged();
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

    private void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
