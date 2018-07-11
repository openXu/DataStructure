package com.openxu.ds;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.openxu.ds.lib.link.LNode;
import com.openxu.ds.lib.link.LinkList;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : LinkListActivity
 * version : 1.0
 * description : 单链表
 */
public class LinkListActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    private List<String> itemList;

    private LinkList list;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        titleLayout.setTextcenter("单链表").show();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("创建单链表（头插法：倒序）");
        itemList.add("创建单链表（尾插法：顺序）");
        itemList.add("反转（循环）");
        itemList.add("反转（递归）");
        itemList.add("将单链表合并为一个单链表");
        itemList.add("获取索引为2的结点");
        itemList.add("获取值为“悟空”的索引");
        itemList.add("插入结点");
        itemList.add("删除结点");

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
                        list = LinkList.createListF(new Object[]{"悟空", "八戒", "沙增", "唐僧"});
                        tv_result.setText("");
                        result = list.display();
                        break;
                    case 1:
                        list = LinkList.createListR(new Object[]{"悟空", "八戒", "沙增", "唐僧"});
                        tv_result.setText("");
                        result = list.display();
                        break;
                    case 2:
                        list.reverseListByLoop();
                        result = "循环反转："+list.display();
                        break;
                    case 3:
                        LNode head = list.reverseListByRec(list.head);
                        result = "递归反转："+list.display();
                        break;
                    case 4:
                        LinkList list1 = LinkList.createListF(new Object[]{"悟空", "八戒", "沙增", "唐僧"});
                        LinkList list2 = LinkList.createListR(new Object[]{"悟空", "八戒", "沙增", "唐僧"});
                        list = LinkList.mergeList(list1, list2);
                        tv_result.setText("");
                        result = list.display();
                        break;
                    case 5:
                        LNode node = list.getNode(2);
                        result = "获取第2个结点:"+node;
                        break;
                    case 6:
                        int index = list.locateNode("悟空");
                        result = "获取值为“悟空”的结点索引:"+index;
                        break;
                    case 7:
                        boolean res = list.insertNode("插入", 2);
                        result = "插入结点:"+list.display();
                        break;
                    case 8:
                        LNode delete = list.deleteNode(1);
                        result = "删除结点:"+list.display();
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
