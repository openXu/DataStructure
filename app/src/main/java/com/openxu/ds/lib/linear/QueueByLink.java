package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/7/26 19:06
 * className : QueueByLink
 * version : 1.0
 * description : 队列的链式存储结构的实现（链式队列）
 *
 *
 *                           ——————————
 *     front（队首出队）<<<                       <<<   (队尾入队)rear
 *                           ——————————
 *
 */
public class QueueByLink<T>{

    private LNode<T> front; //队首指针
    private LNode<T> rear;  //队尾指针

    private int size;      //元素个数

    /**获取队中元素个数*/
    public int getSize() {
        return size;
    }

    /**队列是否为空*/
    public boolean isEmpty() {
        return front==null && rear==null;
    }

    /**入队*/
    public synchronized boolean enQueue(T item) {
        if(item==null)
            throw new NullPointerException("item data is null");
        LNode<T> newNode = new LNode();
        newNode.data = item;
        if (front == null) {
            //向空队中插入，需要将front指针指向第一个结点
            front = newNode;
        } else {
            //非空队列，队尾结点的后继指针指向新结点
            rear.next = newNode;
        }
        rear = newNode;   //队尾指针指向新结点
        size ++;
        return true;
    }

    /**出队*/
    public synchronized T deQueue() {
        if(isEmpty())
            return null;
        T t = front.data;
        front = front.next;
        if (front == null)   //如果出队后队列为空，重置rear
            rear = null;
        size--;
        return t;
    }

    /**返回队首元素*/
    public T peek() {
        return isEmpty()? null:front.data;
    }

    /**清空队列*/
    public synchronized void clear() {
        while(!isEmpty()){
            deQueue();
        }
        front = rear = null;
        size = 0;
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        LNode mFront = front;
        while(mFront!=null){
            buffer.append(mFront.data+", ");
            mFront = mFront.next;
        }
        return buffer.subSequence(0, buffer.lastIndexOf(", "))+"]";
    }

}
