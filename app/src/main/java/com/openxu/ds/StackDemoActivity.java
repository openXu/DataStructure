package com.openxu.ds;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.demo.Maze;
import com.openxu.ds.demo.MazeElem;
import com.openxu.ds.lib.linear.StackByArray;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.view.TitleLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : StackDemoActivity
 * version : 1.0
 * description : 栈应用
 */
public class StackDemoActivity extends BaseActivity {

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
        titleLayout.setTextcenter("栈应用").setTextRight("代码").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.顺序栈破解迷宫");
        itemList.add("2.入栈（顺序栈）");

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
                        result = "迷宫：\n";
                        int[][] maze = new int[][]{
                                {1,1,1,1,1,1},
                                {1,0,0,0,1,1},
                                {1,1,0,1,0,1},
                                {1,0,0,0,1,1},
                                {1,0,1,0,0,1},
                                {1,1,1,1,1,1},
                         };
                        result += printMaze(maze);
                        StackByArray<MazeElem> stack = Maze.getMazePath(maze, 1,1,4,4);
                        result += ("\n出路为："+ stack);
                        MazeElem e;
                        while(!stack.empty()){
                            e = stack.pop();
                            maze[e.x][e.y] = 6;
                        }
                        result += "结果：\n";
                        result += printMaze(maze);
                        break;
                    case 1:
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

    private String printMaze(int[][] maze){
        StringBuffer buffer = new StringBuffer();
        for(int x = 0; x<maze.length; x++){
            for(int y = 0; y<maze.length; y++){
                buffer.append(maze[x][y]+"  ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
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
