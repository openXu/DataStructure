package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.lib.linear.StringByArray;
import com.openxu.ds.lib.linear.StringByLink;
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
 * className : StringActivity
 * version : 1.0
 * description : 串
 */
public class StringActivity extends BaseActivity {

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
        titleLayout.setTextcenter("串的实现").setTextRight("代码").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.创建（顺序串）");
        itemList.add("2.获取长度（顺序串）");
        itemList.add("3.获取指定位置字符（顺序串）");
        itemList.add("4.获取子串（顺序串）");
        itemList.add("5.是否包含（顺序串）");
        itemList.add("6.顺序串是否包含子串（BF匹配算法）");
        itemList.add("7.顺序串是否包含子串（KMP匹配算法）");
        itemList.add("1.创建（链串）");
        itemList.add("2.获取长度（链串）");
        itemList.add("3.获取指定位置字符（链串）");
        itemList.add("4.获取子串（链串）");
        itemList.add("5.是否包含（链串）");

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
                    case 3:
                        if(stringByArray==null){
                            ToastAlone.show("请先创建顺序串");
                            return;
                        }
                        result += "获取子串(1,3)："+stringByArray.subString(1,3);
                        break;
                    case 4:
                        if(stringByArray==null){
                            ToastAlone.show("请先创建顺序串");
                            return;
                        }
                        result += "是否包含'b'："+stringByArray.contains('b');
                        break;
                    case 5:
                        if(stringByArray==null){
                            ToastAlone.show("请先创建顺序串");
                            return;
                        }
                        StringByArray t = new StringByArray(new char[]{'d','e', 'f'});
                        result += "是否包含\"def\"："+stringByArray.indexOf(t);
                        t = new StringByArray(new char[]{'c','d'});
                        result += "是否包含\"cd\"："+stringByArray.indexOf(t);
                        break;
                    case 6:
                        if(stringByArray==null){
                            ToastAlone.show("请先创建顺序串");
                            return;
                        }
                        t = new StringByArray(new char[]{'d','e', 'f'});
                        result += "是否包含\"def\"："+stringByArray.indexOf(t);
                        t = new StringByArray(new char[]{'c','d'});
                        result += "是否包含\"cd\"："+stringByArray.indexOf(t);
                        break;
                    case 7:
                        stringByLink = new StringByLink(new char[]{'a','b','c','d','e'});
                        result = "创建链串：\n"+stringByLink;
                        break;
                    case 8:
                        if(stringByLink==null){
                            ToastAlone.show("请先创建链串");
                            return;
                        }
                        result += "获取长度："+stringByLink.length();
                        break;
                    case 9:
                        if(stringByLink==null){
                            ToastAlone.show("请先创建链串");
                            return;
                        }
                        result += "获取索引为1的字符："+stringByLink.charAt(1);
                        break;
                    case 10:
                        if(stringByLink==null){
                            ToastAlone.show("请先创建链串");
                            return;
                        }
                        result += "获取子串(1,3)："+stringByLink.subString(1,3);
                        break;
                    case 11:
                        if(stringByLink==null){
                            ToastAlone.show("请先创建链串");
                            return;
                        }
                        result += "是否包含'b'："+stringByLink.contains('b');
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
