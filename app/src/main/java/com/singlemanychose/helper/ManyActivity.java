package com.singlemanychose.helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyaping on 16/4/9.
 */
public class ManyActivity extends AppCompatActivity {
    private RecyclerViewItemDecoration recyclerViewItemDecoration;
    private ManyRecyclerAdapter manyRecyclerAdapter;
    private List<String> list;
    private RecyclerView recyclerView_Many_view_show;
    private TextView textView_Search_Event_Response_view_show;
    private boolean isType = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_view);
        recyclerViewItemDecoration = new RecyclerViewItemDecoration(this,1);
        list = new ArrayList<String>();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        addText();
    }

    public void initView (){
        textView_Search_Event_Response_view_show = (TextView) findViewById(R.id.textView_Search_Event_Response_view_show);
        recyclerView_Many_view_show = (RecyclerView)findViewById(R.id.recyclerView_Many_view_show);
        recyclerView_Many_view_show.addItemDecoration(recyclerViewItemDecoration);
        recyclerView_Many_view_show.setLayoutManager(new LinearLayoutManager(this));

        textView_Search_Event_Response_view_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!list.isEmpty()) {
                        if (isType) {
                            manyRecyclerAdapter.setAllSelect();
                            isType = false;
                        } else {
                            isType = true;
                            manyRecyclerAdapter.getAllSelect();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    String title [] = new String[40];
    public void addText (){
        for (int i = 0; i < title.length; i++){
            title[i] = "测试数据" + i;
            list.add(title[i]);
        }
        manyRecyclerAdapter = new ManyRecyclerAdapter(list,this);
        recyclerView_Many_view_show.setAdapter(manyRecyclerAdapter);
    }

    public void setResponseState() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    int len = 0;
                    int lenght = list.size();
                    if (lenght > 1) {
                        for (int i = 0; i < lenght; i++) {
                            if (manyRecyclerAdapter.ischeck.get(i,false)) {
                                len = len + 1;
                            }
                        }
                        if (len == 0) {
                            isType = false;
                            textView_Search_Event_Response_view_show.setText(getResources().getString(R.string.search));
                            textView_Search_Event_Response_view_show.setTextColor(getResources().getColor(R.color.company_name_color));
                        } else if (len > 0 & len < lenght) {
                            isType = false;
                            textView_Search_Event_Response_view_show.setTextColor(getResources().getColor(R.color.fragment_header));
                            textView_Search_Event_Response_view_show.setText(getResources().getString(R.string.Select));
                        } else if (len == lenght) {
                            isType = true;
                            textView_Search_Event_Response_view_show.setTextColor(getResources().getColor(R.color.fragment_header));
                            textView_Search_Event_Response_view_show.setText(getResources().getString(R.string.cancel));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
