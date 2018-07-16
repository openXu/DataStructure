package com.openxu.ds.lib.linear;


/**
 * autour : openXu
 * date : 2018/7/11 15:35
 * className : LNode
 * version : 1.0
 * description : 双链表结点类
 */
public class DNode {

    protected DNode prior; //指针域，指向直接前驱结点
    protected DNode next;  //指针域，指向直接后继结点
    protected Object data;    //数据域

    @Override
    public String toString() {
        return "LNode{" + data +'}';
    }
}
