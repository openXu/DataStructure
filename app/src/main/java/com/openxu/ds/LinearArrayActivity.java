package com.openxu.ds;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.openxu.ds.lib.LinearArray;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LinearArrayActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    private List<String> itemList;

    private LinearArray array;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        titleLayout.setTextcenter("顺序表（数组）").show();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("建立顺序表");
        itemList.add("获取线性表中第1个元素");
        itemList.add("尾部插入元素");
        itemList.add("删除索引为2的元素");

        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>(this,
                R.layout.item_recycler, itemList){
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_name, str);
            }

            @Override
            public void onItemClick(String data, int position) {
                String result = "";
                switch (position){
                    case 0:
                        array = LinearArray.createArray(new Object[]{"悟空", "八戒", "沙增", "唐僧"});
                        tv_result.setText("");
                        result = array.toString();
                        break;
                    case 1:
                        result = array.getElem(0).toString();
                        break;
                    case 2:
                        array.insertElement("蜘蛛精",array.getLength());
                        result = array.toString();
                        break;
                    case 3:
                        array.removeElement(2);
                        result = array.toString();
                        break;
                }
                tv_result.setText(tv_result.getText().toString().trim()+"\n"+result);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setData(itemList);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
    }



}
