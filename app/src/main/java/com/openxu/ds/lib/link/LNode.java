package com.openxu.ds.lib.link;


/**
 * autour : openXu
 * date : 2018/7/11 15:35
 * className : LNode
 * version : 1.0
 * description : 单链表结点类
 */
public class LNode {

    protected LNode next;  //指针域，指向直接后继结点
    protected Object data;    //数据域

    @Override
    public String toString() {
        return "LNode{" + data +'}';
    }
}
