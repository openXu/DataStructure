package com.openxu.ds.demo;


/**
 * autour : openXu
 * date : 2018/7/19 16:39
 * className : MazeElem
 * version : 1.0
 * description : 迷宫元素
 */
public class MazeElem {

    public int x;    //行号
    public int y;    //列号
    public int di;   //下一个可走的相邻的方位号  0：上方  1：右方   2：下方   3：左方

    public MazeElem() {
    }

    public MazeElem(int x, int y, int di) {
        this.x = x;
        this.y = y;
        this.di = di;
    }

    @Override
    public String toString() {
        return "(" + x +", " + y +')';
    }
}
