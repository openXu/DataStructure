package com.openxu.ds.lib.linear;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * autour : openXu
 * date : 2018/7/18 11:40
 * className : StackByArray
 * version : 1.0
 * description : 栈的顺序存储结构的实现（顺序栈）
 *
 *
 *
 *                   |   |
 *              top  | d |  栈顶
 *                   | c |
 *                   | b |
 *                   | a |
 *                   ++++   栈底
 *
 */
public class StackByArray<T>{

    private int top = -1; //栈顶指针,-1代表空栈
    private int capacity = 10;  //默认容量
    private int capacityIncrement = 5;  //容量增量
    private T[] datas;    //元素容器

    public StackByArray(int capacity){
        datas = (T[])new Object[capacity];
    }
    public StackByArray(){
        datas = (T[])new Object[capacity];
    }

    /**溢出扩容 */
    private void ensureCapacity() {
        int oldCapacity = datas.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                capacityIncrement : oldCapacity);
        datas = Arrays.copyOf(datas, newCapacity);
    }

    /**进栈，将元素添加到栈顶*/
    public synchronized void push(T item) {
        //容量不足时扩容
        if(top>=datas.length-1)
            ensureCapacity();
        datas[++top] = item;
    }

    /**出栈，将栈顶的元素移除并返回该元素*/
    public synchronized T pop() {
        if(top<0)
            new EmptyStackException();
        T t = datas[top];
        datas[top] = null;
        top--;
        return t;
    }
    /**清空栈*/
    public synchronized void clear() {
        if(top<0)
            return;
        for(int i = top; i>=0; i--)
            datas[i] = null;
        top = -1;
    }
    /**返回栈顶元素*/
    public T peek() {
        if(top<0)
            new EmptyStackException();
        return datas[top];
    }
    /**获取栈中元素个数*/
    public int getLength() {
        return top+1;
    }
    /**栈是否为空栈*/
    public boolean empty() {
        return top<0;
    }

    /**获取元素到栈顶的距离 */
    public int search(T data) {
        int index = -1;
        if(empty())
            return index;
        if (data == null) {
            for (int i = top; i >= 0; i--)
                if (datas[i]==null){
                    index = i;
                    break;
                }
        } else {
            for (int i = top; i >= 0; i--)
                if (data.equals(datas[i])) {
                    index = i;
                    break;
                }
        }
        if (index >= 0) {
            return top - index;
        }
        return index;
    }

    @Override
    public String toString() {
        if(empty())
            return "[]";
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for(int i = 0; i<=top; i++){
            buffer.append(datas[i]+", ");
        }
        return buffer.subSequence(0, buffer.lastIndexOf(", "))+"]";
    }
}
