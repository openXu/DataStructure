package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.CodeViewActivity;
import com.openxu.ds.R;
import com.openxu.ds.lib.linear.StringByArray;
import com.openxu.ds.lib.linear.StringByLink;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.utils.ToastAlone;
import com.openxu.oxlib.view.TitleLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : StringActivity
 * version : 1.0
 * description : 哈希表
 */
public class HashActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    ScrollView scrollView;
    private List<String> itemList;

    private StringByArray stringByArray;
    private StringByLink stringByLink;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }
    protected void onMenuClick(TitleLayout.MENU_NAME menu, View view) {
        if(menu== TitleLayout.MENU_NAME.MENU_RIGHT_TEXT){
            Intent intent = new Intent(this, CodeViewActivity.class);
//            intent.putExtra("file","file:///android_asset/LinkList.html");
            intent.putExtra("file","https://github.com/openXu/DataStructure/blob/master/app/src/main/java/com/openxu/ds/lib/link/LinkList.java");
            startActivity(intent);
        }
    }
    @Override
    protected void initView() {

        String a = "";
        Map<String, String> map = new HashMap<>();







        titleLayout.setTextcenter("哈希表").setTextRight("代码").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.创建（顺序串）");
        itemList.add("2.获取长度（顺序串）");
        itemList.add("3.获取指定位置字符（顺序串）");

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
                        stringByArray = new StringByArray(new char[]{'a','b','c','d','e'});
                        result = "创建顺序串：\n"+stringByArray;
                        break;
                    case 1:
                        if(stringByArray==null){
                            ToastAlone.show("请先创建顺序串");
                            return;
                        }
                        result += "获取长度："+stringByArray.length();
                        break;
                    case 2:
                        if(stringByArray==null){
                            ToastAlone.show("请先创建顺序串");
                            return;
                        }
                        result += "获取索引为1的字符："+stringByArray.charAt(1);
                        break;
                }
                tv_result.setText(tv_result.getText().toString()+"\n"+result+"\n");
                //设置默认滚动到底部
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
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
