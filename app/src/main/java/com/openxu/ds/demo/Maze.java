package com.openxu.ds.demo;

import android.os.Handler;
import android.util.Log;

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
     * 求解迷宫路径
     * @param maze 迷宫二维数组，元素0代表可走，1代表不可走，(-1代表已入栈)
     * @param startX 入口行号
     * @param startY 入口列号
     * @param endX 出口行号
     * @param endY 出口列号
     * @return
     */
    public static StackByArray<MazeElem> getMazePath(int[][] maze, int startX,
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

}
