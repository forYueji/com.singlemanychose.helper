package com.singlemanychose.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by huangyaping on 16/4/9.
 */
public class ManyRecyclerViewHodler extends RecyclerView.ViewHolder {
    public TextView textView_item_many_title_view;
    public CheckBox checkBox_item_many_multiselect_view_show;
    public RelativeLayout relativeLayour_item_view_show;

    public ManyRecyclerViewHodler (View view){
        super(view);
        textView_item_many_title_view = (TextView)view.findViewById(R.id.textView_item_many_title_view);
        checkBox_item_many_multiselect_view_show = (CheckBox)view.findViewById(R.id.checkBox_item_many_multiselect_view_show);
        relativeLayour_item_view_show = (RelativeLayout)view.findViewById(R.id.relativeLayour_item_view_show);
    }
}
