package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.linear.DLinkList;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.view.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : DLinkListActivity
 * version : 1.0
 * description : 双链表
 */
public class DLinkListActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    ScrollView scrollView;
    private List<String> itemList;

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
        titleLayout.setTextcenter("双链表").setTextRight("代码").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.创建双链表");
        itemList.add("2.添加结点");
        itemList.add("3.删除结点");
        itemList.add("4.设置结点值");
        itemList.add("5.查找");
        itemList.add("6.反转");

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
                        result = "创建双链表：";
                        DLinkList list = DLinkList.createListF(new Object[]{"1", "2", "3", "4"});
                        result += ("\n头插法（倒序）：\n"+list.toString());
                        Log.w(TAG, "头插法（倒序）："+list.toString()+"         "+list.toString().contains("\n"));
                        list = DLinkList.createListR(new Object[]{"1", "2", "3", "4"});
                        result += ("\n尾插法（顺序）：\n"+list.toString());
                        break;
                    case 1:
                        result = "插入结点：\n";
                        list = DLinkList.createListR(new Object[]{"1", "2", "3", "4"});
                        result += ("双链表\n"+list.toString());
                        list.add("尾部插入");
                        list.add(2, "中间插入");
                        result += ("\n插入后：\n"+list.toString());
                        break;
                    case 2:
                        result = "删除结点：\n";
                        list = DLinkList.createListR(new Object[]{"1", "1", "2", "2", "3","3","4","4"});
                        result += ("双链表\n"+list.toString());
                        list.remove(0);
                        list.remove("4");
                        list.removeAll("3");
                        result += ("\n删除后：\n"+list.toString());
                        break;
                    case 3:
                        result = "修改结点值：\n";
                        list = DLinkList.createListR(new Object[]{"1", "2", "3", "4"});
                        result += ("双链表\n"+list.toString());
                        list.set(0, "修改值");
                        result += ("\n修改后：\n"+list.toString());
                        break;
                    case 4:
                        result = "查找：\n";
                        list = DLinkList.createListF(new Object[]{"0", "1", "1", "2", "2", "3"});
                        result += ("双链表\n"+list.toString());
                        result += ("\n值为1的结点出现位置："+list.indexOf("1"));
                        result += ("\n值为2的结点最后一次出现位置："+list.lastIndexOf("1"));
                        result += ("\n获取索引为2的结点值："+list.get(2));
                        break;
                    case 5:
                        result = "反转：\n";
                        list = DLinkList.createListF(new Object[]{"1", "2", "3", "4"});
                        result += ("双链表\n"+list.toString());
                        list.reverse();
                        result += ("\n反转为：\n"+list.toString());
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
