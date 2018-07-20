package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.linear.ArrayStack;
import com.openxu.ds.lib.linear.LinkedStack;
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
 * className : StackActivity
 * version : 1.0
 * description : 栈
 */
public class StackActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    ScrollView scrollView;
    private List<String> itemList;

    ArrayStack<String> arrayStack;
    LinkedStack<String> linkedStack;

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
        itemList.add("1.创建栈（顺序栈）");
        itemList.add("2.入栈（顺序栈）");
        itemList.add("3.查询栈顶元素（顺序栈）");
        itemList.add("4.元素出现位置（顺序栈）");
        itemList.add("5.出栈（顺序栈）");
        itemList.add("6.清空栈（顺序栈）");

        itemList.add("1.创建栈（链栈）");
        itemList.add("2.入栈（链栈）");
        itemList.add("3.查询栈顶元素（链栈）");
        itemList.add("4.元素出现位置（链栈）");
        itemList.add("5.出栈（链栈）");
        itemList.add("6.清空栈（链栈）");

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
                        result = "创建栈：";
                        arrayStack = new ArrayStack<>(5);
                        arrayStack.push("1");
                        arrayStack.push("2");
                        arrayStack.push("3");
                        arrayStack.push("4");
                        arrayStack.push("5");
                        result += ("\n栈："+arrayStack);
                        break;
                    case 1:
                        if(arrayStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "入栈：\n";
                        arrayStack.push("6");
                        arrayStack.push("7");
                        result += ("\n入栈后："+arrayStack);
                        break;
                    case 2:
                        if(arrayStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "查询栈顶元素："+arrayStack.peek();
                        break;
                    case 3:
                        if(arrayStack==null)
                            ToastAlone.show("请先创建栈");
                        result = "元素出现位置：\n";
                        result += ("5距栈顶距离："+arrayStack.search("5"));
                        break;
                    case 4:
                        if(arrayStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "出栈：\n";
                        arrayStack.pop();
                        result += ("出栈后："+arrayStack);
                        break;
                    case 5:
                        if(arrayStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        arrayStack.clear();
                        result = "清空栈："+arrayStack;
                        break;

                    case 6:
                        result = "创建栈：";
                        linkedStack = new LinkedStack<>();
                        linkedStack.push("1");
                        linkedStack.push("2");
                        linkedStack.push("3");
                        linkedStack.push("4");
                        linkedStack.push("5");
                        result += ("\n栈："+linkedStack);
                        break;
                    case 7:
                        if(linkedStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "入栈：\n";
                        linkedStack.push("6");
                        linkedStack.push("7");
                        result += ("\n入栈后："+linkedStack);
                        break;
                    case 8:
                        if(linkedStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "查询栈顶元素："+linkedStack.peek();
                        break;
                    case 9:
                        if(linkedStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "元素出现位置：\n";
                        result += ("5距栈顶距离："+linkedStack.search("5"));
                        break;
                    case 10:
                        if(linkedStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "出栈：\n";
                        linkedStack.pop();
                        result += ("出栈后："+linkedStack);
                        break;
                    case 11:
                        if(linkedStack==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        linkedStack.clear();
                        result = "清空栈："+linkedStack;
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
