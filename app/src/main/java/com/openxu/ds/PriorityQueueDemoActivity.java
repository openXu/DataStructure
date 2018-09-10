package com.openxu.ds;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.linear.PriorityQueue;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.utils.LogUtil;
import com.openxu.oxlib.view.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : PriorityQueueDemoActivity
 * version : 1.0
 * description : 优先队列
 */
public class PriorityQueueDemoActivity extends BaseActivity {

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
        titleLayout.setTextcenter("优先队列").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("优先级队列示例");

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
                        result = "降序优先级队列：\n";
                        PriorityQueue<PriorityProcess> pqueue = new PriorityQueue<PriorityProcess>(PriorityQueue.ODER_TYPE.desc);
                        pqueue.enQueue(new PriorityProcess("进程1", 3));
                        pqueue.enQueue(new PriorityProcess("进程2", 5));
                        pqueue.enQueue(new PriorityProcess("进程3", 2));
                        pqueue.enQueue(new PriorityProcess("进程4", 1));
                        pqueue.enQueue(new PriorityProcess1("进程5", 6));
                        pqueue.enQueue(new PriorityProcess1("进程6", 2));
                        result += ("\n队列中进程存放顺序："+ pqueue);
                        result += ("\n队列中进程处理顺序：\n");
                        while (!pqueue.isEmpty()){
                            result += (pqueue.deQueue()+"\n");
                        }
                        break;


                }
                LogUtil.w(TAG, result);
                tv_result.setText(tv_result.getText().toString()+"\n"+result+"\n");
                //设置默认滚动到底部
                scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setData(itemList);
    }


    class PriorityProcess implements Comparable<PriorityProcess>{
        public PriorityProcess(String name,int priority) {
            super();
            this.name = name;
            this.priority = priority;
        }
        public int priority;  //优先级
        public String name;   //进程名称

        @Override
        public String toString() {
            return name +
                    ":" + priority;
        }

        @Override
        public int compareTo(@NonNull PriorityProcess o) {
            return priority-o.priority;
        }
    }
    class PriorityProcess1 extends PriorityProcess{
        public PriorityProcess1(String name,int priority) {
            super(name, priority);
        }

        @Override
        public int compareTo(@NonNull PriorityProcess o) {
            return priority-o.priority;
        }
    }
    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }



}
