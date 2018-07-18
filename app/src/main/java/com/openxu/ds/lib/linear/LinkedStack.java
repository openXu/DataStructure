package com.openxu.ds.lib.linear;

import java.util.EmptyStackException;

/**
 * autour : openXu
 * date : 2018/7/18 11:40
 * className : ArrayStack
 * version : 1.0
 * description : 栈的链式存储结构的实现
 */
public class LinkedStack<T>{

    public LNode<T> top;   //栈顶指针,指向栈顶结点
    private int size = 0;

    /**进栈，将元素添加到栈顶*/
    public synchronized void push(T item) {
        LNode temp = new LNode();
        temp.next = top;   //添加的结点的指针域指向当前栈顶结点
        top = temp;        //更新栈顶结点
        size ++;
    }

    /**出栈，将栈顶的元素移除并返回该元素*/
    public synchronized T pop() {
        if(top==null)
            new EmptyStackException();
        LNode < T> next = top.next;
        T data = top.data;
        top.next = null;
        top.data=null;
        top = next;
        size--;
        return data;
    }
    /**清空栈*/
    public synchronized void clear() {
        LNode<T> next;
        while(top!=null){
            next = top.next;
            top.data = null;
            top.next = null;
            top = next;
        }
        size = 0;
    }
    /**返回栈顶元素*/
    public T peek() {
        if(top==null)
            new EmptyStackException();
        return top.data;
    }
    /**获取栈中元素个数*/
    public int getLength() {
        return size;
    }
    /**栈是否为空栈*/
    public boolean empty() {
        return top==null;
    }

    /**获取元素到栈顶的距离 */
    public int search(T data) {
        int dis = -1;
        if(empty())
            return dis;
        LNode<T> node = top;
        if (data == null) {
            while(node!=null){
                dis ++;
                if (node.data == null)
                    return dis;
            }
        } else {
            while(node!=null){
                dis ++;
                if (node.data.equals(data))
                    return dis;
            }
        }
        return dis;
    }

    @Override
    public String toString() {
        if(empty())
            return "[]";
        LNode node = top;
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        while(node != null){
            buffer.append(node.data+", ");
            node = node.next;
        }
        return buffer.subSequence(0, buffer.lastIndexOf(", "))+"]";
    }
}
