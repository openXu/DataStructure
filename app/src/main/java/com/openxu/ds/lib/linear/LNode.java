package com.openxu.ds.lib.linear;


/**
 * autour : openXu
 * date : 2018/7/11 15:35
 * className : LNode
 * version : 1.0
 * description : 单链表结点类
 */
public class LNode<T> {

    protected LNode next;  //指针域，指向直接后继结点
    protected T data;    //数据域

    public LNode getNext() {
        return next;
    }

    public void setNext(LNode next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LNode{" + data +'}';
    }
}
