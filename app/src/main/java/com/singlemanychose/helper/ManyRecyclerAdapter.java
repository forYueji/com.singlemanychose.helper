package com.singlemanychose.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by huangyaping on 16/4/9.
 */
public class ManyRecyclerAdapter extends RecyclerView.Adapter<ManyRecyclerViewHodler>{
    private List<String> list;
    private Context context;
    public SparseArray<Boolean> ischeck;
    public SparseArray<CheckBox> listbox;
    private boolean selectedState = false;

    public ManyRecyclerAdapter (List<String> list,Context context){
        this.context = context;
        this.list = list;
        ischeck = new SparseArray<Boolean>();
        listbox = new SparseArray<CheckBox>();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public ManyRecyclerViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_many_title,parent,false);
        return new ManyRecyclerViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ManyRecyclerViewHodler holder, int position) {
        holder.textView_item_many_title_view.setText(list.get(position).toString());
        holder.relativeLayour_item_view_show.setId(position);
        SetSelectedState(holder.checkBox_item_many_multiselect_view_show, ischeck.get(position, false));
        listbox.put(position, holder.checkBox_item_many_multiselect_view_show);
        holder.relativeLayour_item_view_show.setOnClickListener(onclick);
    }

    public View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = v.getId();
            setIsSelectedState(true);
            if (ischeck == null) {
                ischeck.put(position, true);
            } else {
                if (!ischeck.get(position, false)) {
                    ischeck.put(position, true);
                } else {
                    ischeck.put(position, false);
                }
            }
            SetSelectedState(listbox.get(position), ischeck.get(position));
            ((ManyActivity)context).setResponseState();
        }
    };

    public void setIsSelectedState(boolean state) {
        selectedState = state;
    }

    public boolean getIsSelectedState() {
        return selectedState;
    }

    public void SetSelectedState(CheckBox checkBox, boolean type) {
        if (getIsSelectedState()) {
            checkBox.setChecked(type);
            if (type) {
                checkBox.setBackgroundResource(R.mipmap.checkbox_check);
            } else {
                checkBox.setBackgroundResource(R.mipmap.checkbox_empty);
            }
        }
    }

    public void getAllSelect() {
        try {
            setIsSelectedState(true);
            if (getIsSelectedState()) {
                int length = list.size();
                ischeck.clear();
                for (int i = 0; i < length; i++) {
                    ischeck.put(i, true);
                    ((ManyActivity)context).setResponseState();
                    if (i < listbox.size()) {
                        SetSelectedState(listbox.get(i), ischeck.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAllSelect() {
        try {
            setIsSelectedState(true);
            if (getIsSelectedState()) {
                ischeck.clear();
                int length = list.size();
                for (int i = 0; i < length; i++) {
                    ischeck.put(i, false);
                    if (i < listbox.size()) {
                        SetSelectedState(listbox.get(i), ischeck.get(i));
                    }
                }
                ((ManyActivity)context).setResponseState();
            }
        } catch (Exception e) {
            e.printStackTrace();
            /** 为避免index下标越界 */
        }
    }

}
