package com.openxu.ds.demo;

import android.os.Handler;
import android.util.Log;

import com.openxu.ds.lib.linear.QueueByArray;
import com.openxu.ds.lib.linear.StackByArray;

/**
 * autour : openXu
 * date : 2018/7/19 16:44
 * className : Maze
 * version : 1.0
 * description : 使用栈 破解迷宫
 */
public class Maze {

    private static String TAG = "Maze";

    Handler handler = new Handler();

    /**
     * 使用栈求解迷宫路径
     * @param maze 迷宫二维数组，元素0代表可走，1代表不可走，(-1代表已入栈)
     * @param startX 入口行号
     * @param startY 入口列号
     * @param endX 出口行号
     * @param endY 出口列号
     * @return
     */
    public static StackByArray<MazeElem> getMazePathByArrayStack(int[][] maze, int startX,
                                                     int startY, int endX, int endY){
        int x = startX, y = startY;       //用于查找下一个可走方位的坐标
        boolean find;   //是否找到栈顶元素下一个可走 的 方块
        StackByArray<MazeElem> stack = new StackByArray();
        //入口元素入栈
        MazeElem elem = new MazeElem(startX, startY, -1);
        stack.push(elem);
        maze[x][y] = -1;  //将入口元素置为-1，避免死循环
        //栈不为空时循环
        while(!stack.empty()){
            elem = stack.peek();   //取栈顶元素
            if(elem.x == endX && elem.y == endY){
                //栈顶元素与出口坐标一样时，说明找到出口了
                Log.e(TAG, "迷宫路径如下：\n"+stack);
                return stack;
            }

            find = false;

            //遍历栈顶元素的四个方向 ，找 栈顶 元素 下一个可走 方块
            while(elem.di < 4 && !find){
                elem.di++;
                switch ((elem.di)){
                    case 0:   //上方
                        x = elem.x-1; y = elem.y;
                        break;
                    case 1:   //右方
                        x = elem.x; y = elem.y+1;
                        break;
                    case 2:   //下方
                        x = elem.x+1; y = elem.y;
                        break;
                    case 3:   //左方
                        x = elem.x; y = elem.y-1;
                        break;
                }
                //避免OutOfIndex
                if(x>=0 && y>=0 && x<maze.length && y<maze[0].length)
                    find = maze[x][y]==0;
            }

            if(find){  //找到了下一个可走方向
                stack.push(new MazeElem(x, y, -1));  //下一个可走方向入栈
                maze[x][y] = -1;      //入栈后置为-1，避免死循环
            }else{
                //栈顶元素没有下一个可走结点，则出栈，并将该方块置为0（可走）
                elem = stack.pop();
                maze[elem.x][elem.y] = 0;
            }
        }
        return null;
    }

    /**
     * 使用队列求解迷宫（最短路径）
     * @param maze
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public static QueueByArray<MazeElem> getMazePathByArrayQueue(int[][] maze, int startX,
                                                                 int startY, int endX, int endY){
        int x, y, di;

        QueueByArray<MazeElem> queue = new QueueByArray();
        //入口元素进队
        MazeElem elem = new MazeElem(startX, startY, 0,-1);
        queue.enQueue(elem);
        maze[startX][startY] = -1;  //将入口元素置为-1，避免回过来重复搜索

        int front = 0;  //记录当前操作的可走方块在队列中的索引
        //队列不为空且未找到路径时循环
        while(front<=queue.getRear()){
            x = queue.getElement(front).x;
            y = queue.getElement(front).y;
            if(x == endX && y == endY){  //找到了出口
                int k = front, j;
                //反向找到最短路径，将该路径上的方块的pre设置为-1
                do{
                    j = k;
                    k = queue.getElement(k).pre;   //上一个可走方块索引
                    queue.getElement(j).pre = -1;  //将路径上的元素的pre值为-1
                }while(k!=0);
                //返回队列，队列中pre为-1的元素，构成最短路径
                return queue;
            }
            for(di = 0; di<4; di++){   //循环扫描每个方位，把每个可走的方块插入队列中
                switch (di){
                    case 0:     //上
                        x = queue.getElement(front).x-1;
                        y = queue.getElement(front).y;
                        break;
                    case 1:     //右
                        x = queue.getElement(front).x;
                        y = queue.getElement(front).y+1;
                        break;
                    case 2:     //下
                        x = queue.getElement(front).x+1;
                        y = queue.getElement(front).y;
                        break;
                    case 3:     //左
                        x = queue.getElement(front).x;
                        y = queue.getElement(front).y-1;
                        break;
                }
                if(x>=0 && y>=0 && x<maze.length && y<maze[0].length){
                    if(maze[x][y] == 0){
                        //将该相邻方块插入队列
                        queue.enQueue(new MazeElem(x, y, 0, front));
                        maze[x][y] = -1;   //赋值为-1，避免回过来重复搜索
                    }
                }
            }
            front ++;
        }
        return null;
    }
}
