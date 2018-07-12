package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.link.LNode;
import com.openxu.ds.lib.link.LinkList;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.view.TitleLayout;

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
        titleLayout.setTextcenter("单链表").setTextRight("代码").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.创建单链表");
        itemList.add("2.合并");
        itemList.add("3.反转");
        itemList.add("4.长度");
        itemList.add("5.字符串表示");
        itemList.add("6.查找");
        itemList.add("7.插入删除结点");
        itemList.add("8.是否存在环");
        itemList.add("9.两个链表是否相交");

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
                        result = "创建单链表：";
                        LinkList list = LinkList.createListF(new Object[]{"1", "2", "3", "4", "5"});
                        result += ("\n头插法（倒序）："+list.display());
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5"});
                        result += ("\n尾插法（顺序）："+list.display());
                        break;
                    case 1:
                        result = "合并：";
                        list = LinkList.createListF(new Object[]{"1", "2", "3", "4", "5"});
                        result += list.display();
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5"});
                        result += ("\n"+list.display());
                        break;
                    case 2:
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5"});
                        result = "反转：\n原链表："+list.display();
                        list.reverseListByLoop();
                        result += ("\n循环反转："+list.display());
                        list.head = list.reverseListByRec(list.head);
                        result += ("\n递归反转："+list.display());
                        break;
                    case 3:
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5"});
                        result = "长度：\n链表："+list.display()+"长度"+list.getListLength();
                        break;
                    case 4:
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5"});
                        result = "\n字符串表示：\n链表："+ list.display();
                        result += ("\n栈倒叙："+list.displayReverseStack());
                        StringBuffer buffer = new StringBuffer();
                        list.displayReverseRec(buffer, list.head);
                        result += ("\n递归倒叙："+buffer.toString());
                        break;
                    case 5:
                        list = LinkList.createListR(new Object[]{"悟空", "唐僧", "八戒", "沙增", "如来"});
                        result = "链表："+ list.display();
                        result += ("\n获取索引为2的结点："+list.getNode(2));
                        result += ("\n获取如来的索引："+list.locateNode("如来"));
                        result += ("\n倒数第4个结点："+list.getReNode(4));
                        result += ("\n获取中间结点："+list.getMiddleNode());
                        break;
                    case 6:
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5"});
                        result = "插入删除结点："+ list.display();
                        list.insertNode("插入", 2);
                        result += ("\n在索引为2处插入："+list.display());
                        list.deleteNode(3);
                        result += ("\n删除索引为3的结点："+list.display()+"\n");
                        result += ("\nO(1)复杂度删除"+list.head.getNext().getNext().getNext().getData()+"：");
                        LinkList.deleteNode(list.head, list.head.getNext().getNext().getNext());
                        result += (list.display());
                        break;
                    case 7:
                        list = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
                        result = "链表："+ list.display();
                        LNode node = list.getReNode(1);
                        node.setNext(list.head.getNext().getNext());   //10指向3
                        result += "\n"+ node.getData()+"指向"+list.head.getNext().getNext().getData();
                        result += "\n是否存在环："+ LinkList.hasCycle(list.head);
                        result += "\n环入口（hashmap）："+ LinkList.getFirstNodeInCycleHashMap(list.head);
                        result += "\n环入口（快慢指针）："+ LinkList.getFirstNodeInCycle(list.head);
                        break;
                    case 8:
                        LinkList list1 = LinkList.createListR(new Object[]{"1", "2", "3", "4", "5", "6", "7", "8"});
                        LinkList list2 = LinkList.createListR(new Object[]{"3", "4"});
                        LNode nodeLast2 = list2.getReNode(1);  //获取list2的尾结点
                        nodeLast2.setNext(list1.getNode(list1.locateNode("5")));  //让lsit2的尾结点指向list1中的5
                        result = "是否相交？找出交点：\n";
                        result += "链表1："+list1.display()+"\n";
                        result += "链表2："+list2.display()+"\n";
                        LNode jiaodian = LinkList.isIntersect(list1, list2);
                        result  += "是否相交："+ (jiaodian!=null)+"交点："+jiaodian;
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
