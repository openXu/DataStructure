package com.openxu.ds;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.demo.Maze;
import com.openxu.ds.demo.MazeElem;
import com.openxu.ds.lib.linear.PriorityQueue;
import com.openxu.ds.lib.linear.QueueByArray;
import com.openxu.ds.lib.linear.StackByArray;
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
        titleLayout.setTextcenter("栈和队列的应用").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("1.顺序栈破解迷宫");
        itemList.add("2.队列破解迷宫（最短路径）");
        itemList.add("3.优先级队列");

        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>(this,
                R.layout.item_recycler, itemList){
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_name, str);
            }

            @Override
            public void onItemClick(String data, int position) {
                String result = "";
                int[][] maze = getMaze();
                switch (position){
                    case 0:
                        result = "迷宫：\n";
                        result += printMaze(maze);
                        StackByArray<MazeElem> stack = Maze.getMazePathByArrayStack(maze, 1,0,8,9);
                        result += ("\n"+stack.getLength()+"出路为："+ stack);
                        MazeElem e;
                        while(!stack.empty()){
                            e = stack.pop();
                            maze[e.x][e.y] = 6;
                        }
                        result += "\n结果：\n";
                        result += printMaze(maze);
                        break;
                    case 1:
                        result = "迷宫：\n";
                        result += printMaze(maze);
                        QueueByArray<MazeElem> queue = Maze.getMazePathByArrayQueue(maze, 1,0,8,9);
                        result += ("\n出路为(-1)："+ queue);
                        int num = 0;
                        maze = getMaze();
                        while(!queue.isEmpty()){
                            e = queue.deQueue();
                            if(e.pre == -1) {
                                num++;
                                maze[e.x][e.y] = 6;
                            }
                        }
                        result += ("\n"+num+"结果：\n");
                        result += printMaze(maze);
                        break;

                    case 2:
                        result = "降序优先级队列：\n";
                        PriorityQueue<PriorityProcess> pqueue = new PriorityQueue(PriorityQueue.ODER_TYPE.desc);
                        pqueue.enQueue(new PriorityProcess("进程1", 3));
                        pqueue.enQueue(new PriorityProcess("进程2", 5));
                        pqueue.enQueue(new PriorityProcess("进程3", 2));
                        pqueue.enQueue(new PriorityProcess("进程4", 1));
                        pqueue.enQueue(new PriorityProcess("进程5", 6));
                        pqueue.enQueue(new PriorityProcess("进程6", 2));
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

    private int[][] getMaze(){
        return new int[][]{
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,0,1,0,0,0,1,0,1},
                {1,0,0,1,0,0,0,1,0,1},
                {1,0,0,0,0,1,1,0,0,1},
                {1,0,1,1,1,0,0,0,0,1},
                {1,0,0,0,1,0,0,0,0,1},
                {1,0,1,0,0,0,1,0,0,1},
                {1,0,0,1,1,0,1,1,0,1},
                {1,0,0,1,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,1},
        };
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

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }



}
