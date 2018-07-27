package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/7/26 19:06
 * className : QueueByArray
 * version : 1.0
 * description : 队列的顺序存储结构的实现（环形队列） (双端队列)
 *
 *
 *                                      ——————————
 *     front（队首出队deQueue）    <<<                       <<<   (队尾入队enQueue)rear
 *     front（队首入队enQueueFront）>>>                      >>>   (队尾出队dnQueueRear)rear
 *                                      ——————————
 *
 */
public class QueueByArray<T>{

    private int front = 0; //队首指针（出队）
    private int rear = 0;  //队尾指针（入队）
    private int size;      //元素个数

    private int capacity = 10;  //默认容量
    private int capacityIncrement = 5;  //容量增量
    private T[] datas;    //元素容器

    public QueueByArray(int capacity){
        datas = (T[])new Object[capacity];
    }
    public QueueByArray(){
        datas = (T[])new Object[capacity];
    }

    /**获取队中元素个数*/
    public int getSize() {
        return rear - front;
//        return size;
    }

    /**是否为空队*/
    public boolean isEmpty() {
        return rear==front;
    }

    /**入队*/
    public synchronized boolean enQueue(T item) {
        if(item==null)
            return false;
        //判断是否满队
        if((rear+1) % datas.length == front){
            //满队时扩容
            ensureCapacity();
        }
        //添加data
        datas[rear] = item;
        //更新rear指向下一个空元素的位置
        rear = (rear+1) % datas.length;
        size++;
        return true;
    }

    /**双端队列  从队首入队*/
    public synchronized boolean enQueueFront(T item) {
        if(item==null)
            return false;
        //判断是否满队
        if((rear+1) % datas.length == front){
            //满队时扩容
            ensureCapacity();
        }
        //使队首指针指向上一个空位
        front = (front-1+datas.length)%datas.length;
        //添加data
        datas[front] = item;
        size++;
        return true;
    }

    /**溢出扩容 */
    private void ensureCapacity() {
        int oldCapacity = datas.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                capacityIncrement : oldCapacity);
        T[] oldDatas = datas;
        datas = (T[])new Object[newCapacity];
        int j = 0;
        //将原数组中元素拷贝到新数组
        for (int i = front; i!=this.rear ; i = (i+1) % datas.length) {
            datas[j++] = oldDatas[i];
        }
        //front指向新数组0的位置
        front = 0;
        //rear指向新数组最后一个元素位置
        rear = j;
    }

    /**出队*/
    public synchronized T deQueue() {
        if(isEmpty())
            return null;
        T t = datas[front];
        datas[front] = null;
        //front指向新的队首元素
        front = (front+1) % datas.length;
        size --;
        return t;
    }
    /**双端队列  从队尾出队*/
    public synchronized T deQueueRear() {
        if(isEmpty())
            return null;
        //队尾指针指向上一个元素位置
        rear = (rear-1+datas.length)%datas.length;
        T t = datas[rear];
        datas[rear] = null;
        size --;
        return t;
    }

    /**清空队列*/
    public synchronized void clear() {
        while(!isEmpty()){
            deQueue();
        }
        front = rear = 0;
    }

    /**返回队首元素*/
    public T peek() {
        if(isEmpty())
            return null;
        return datas[front];
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        int mFront = front;
        while(mFront!=rear){
            buffer.append(datas[mFront]+", ");
            mFront = (mFront+1) % datas.length;
        }
        return buffer.subSequence(0, buffer.lastIndexOf(", "))+"]";
    }
}
