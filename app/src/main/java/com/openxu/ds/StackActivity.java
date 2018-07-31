package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.linear.StackByArray;
import com.openxu.ds.lib.linear.StackByLink;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.utils.ToastAlone;
import com.openxu.oxlib.view.TitleLayout;

import java.util.ArrayList;
import java.util.List;

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

    StackByArray<String> stackByArray;
    StackByLink<String> stackByLink;

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
                        stackByArray = new StackByArray<>(5);
                        stackByArray.push("1");
                        stackByArray.push("2");
                        stackByArray.push("3");
                        stackByArray.push("4");
                        stackByArray.push("5");
                        result += ("\n栈："+ stackByArray);
                        break;
                    case 1:
                        if(stackByArray ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "入栈：\n";
                        stackByArray.push("6");
                        stackByArray.push("7");
                        result += ("\n入栈后："+ stackByArray);
                        break;
                    case 2:
                        if(stackByArray ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "查询栈顶元素："+ stackByArray.peek();
                        break;
                    case 3:
                        if(stackByArray ==null)
                            ToastAlone.show("请先创建栈");
                        result = "元素出现位置：\n";
                        result += ("5距栈顶距离："+ stackByArray.search("5"));
                        break;
                    case 4:
                        if(stackByArray ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "出栈：\n";
                        stackByArray.pop();
                        result += ("出栈后："+ stackByArray);
                        break;
                    case 5:
                        if(stackByArray ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        stackByArray.clear();
                        result = "清空栈："+ stackByArray;
                        break;

                    case 6:
                        result = "创建栈：";
                        stackByLink = new StackByLink<>();
                        stackByLink.push("1");
                        stackByLink.push("2");
                        stackByLink.push("3");
                        stackByLink.push("4");
                        stackByLink.push("5");
                        result += ("\n栈："+ stackByLink);
                        break;
                    case 7:
                        if(stackByLink ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "入栈：\n";
                        stackByLink.push("6");
                        stackByLink.push("7");
                        result += ("\n入栈后："+ stackByLink);
                        break;
                    case 8:
                        if(stackByLink ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "查询栈顶元素："+ stackByLink.peek();
                        break;
                    case 9:
                        if(stackByLink ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "元素出现位置：\n";
                        result += ("5距栈顶距离："+ stackByLink.search("5"));
                        break;
                    case 10:
                        if(stackByLink ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        result = "出栈：\n";
                        stackByLink.pop();
                        result += ("出栈后："+ stackByLink);
                        break;
                    case 11:
                        if(stackByLink ==null) {
                            ToastAlone.show("请先创建栈");
                            return;
                        }
                        stackByLink.clear();
                        result = "清空栈："+ stackByLink;
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
