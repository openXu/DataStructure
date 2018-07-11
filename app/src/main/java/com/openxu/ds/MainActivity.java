package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;
    private List<String> itemList;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        itemList = new ArrayList<>();
        itemList.add("线性表顺序存储（数组）");
        itemList.add("线性表链式存储（单链表）");
        itemList.add("线性表链式存储（双链表）");

        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>(this,
                R.layout.item_recycler, itemList){
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_name, str);
            }

            @Override
            public void onItemClick(String data, int position) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(mContext, LinearArrayActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, LinkListActivity.class);
                        break;
                }
                if(null!=intent)
                    startActivity(intent);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
    }



}
