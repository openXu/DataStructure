package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.linear.QueueByArray;
import com.openxu.ds.lib.linear.QueueByLink;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.utils.ToastAlone;
import com.openxu.oxlib.view.TitleLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : QueueActivity
 * version : 1.0
 * description : 队列
 */
public class QueueActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    ScrollView scrollView;
    private List<String> itemList;

    QueueByArray<String> queByArray;
    QueueByLink<String> queByLink;

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
        titleLayout.setTextcenter("栈").setTextRight("代码").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.创建队列（顺序队）");
        itemList.add("2.入队（顺序队）");
        itemList.add("3.查询队首元素（顺序队）");
        itemList.add("4.队扩容（顺序队）");
        itemList.add("5.出队（顺序队）");
        itemList.add("6.清空队（顺序队）");

        itemList.add("1.创建队列（链式队）");
        itemList.add("2.入队（链式队）");
        itemList.add("3.查询队首元素（链式队）");
        itemList.add("4.队扩容（链式队）");
        itemList.add("5.出队（链式队）");
        itemList.add("6.清空队（链式队）");

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
                        result = "创建队：";
                        queByArray = new QueueByArray<>(5);
                        result += ("\n队："+ queByArray);
                        break;
                    case 1:
                        if(queByArray ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        result = "入队：";
                        queByArray.enQueue("1");
                        queByArray.enQueue("2");
                        queByArray.enQueue("3");
                        result += ("\n入队后："+ queByArray);
                        break;
                    case 2:
                        if(queByArray ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        result = "队首元素："+ queByArray.peek();
                        break;
                    case 3:
                        if(queByArray ==null)
                            ToastAlone.show("请先创建队");
                        result = "队扩容：\n";
                        queByArray.enQueue("4");
                        queByArray.enQueue("5");
                        queByArray.enQueue("6");
                        queByArray.enQueue("7");
                        queByArray.enQueue("8");
                        queByArray.enQueue("9");
                        result += ("扩容后："+ queByArray);
                        break;
                    case 4:
                        if(queByArray ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        result = "出队：\n";
                        queByArray.deQueue();
                        result += ("出栈后："+ queByArray);
                        break;
                    case 5:
                        if(queByArray ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        queByArray.clear();
                        result = "清空队："+ queByArray;
                        break;

                    case 6:
                        result = "创建队：";
                        queByLink = new QueueByLink<>();
                        result += ("\n队："+ queByLink);
                        break;
                    case 7:
                        if(queByLink ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        result = "入队：";
                        queByLink.enQueue("1");
                        queByLink.enQueue("2");
                        queByLink.enQueue("3");
                        result += ("\n入队后："+ queByLink);
                        break;
                    case 8:
                        if(queByLink ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        result = "队首元素："+ queByLink.peek();
                        break;
                    case 9:
                        if(queByLink ==null)
                            ToastAlone.show("请先创建队");
                        result = "队扩容：\n";
                        queByLink.enQueue("4");
                        queByLink.enQueue("5");
                        queByLink.enQueue("6");
                        queByLink.enQueue("7");
                        queByLink.enQueue("8");
                        queByLink.enQueue("9");
                        result += ("扩容后："+ queByLink);
                        break;
                    case 10:
                        if(queByLink ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        result = "出队：\n";
                        queByLink.deQueue();
                        result += ("出栈后："+ queByLink);
                        break;
                    case 11:
                        if(queByLink ==null) {
                            ToastAlone.show("请先创建队");
                            return;
                        }
                        queByLink.clear();
                        result = "清空队："+ queByLink;
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

        Stack<String> stack = new Stack<>();
        stack.push("第1个");
        stack.push("第2个");
        stack.push("第3个");
        stack.push("第4个");
        stack.add("第5个");
        stack.add(0, "第6个");
        stack.insertElementAt("第7个", 3);
        Log.w(TAG, "栈内元素："+stack);
//        List list = new ArrayList();
//        HashSet set = new TreeSet<>()Set();
//        TreeSet sett = new TreeSet();
//
//        HashMap map = new HashMap();
    }



}
