package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/7/31 10:03
 * className : PriorityQueue
 * version : 1.0
 * description : 优先级队列
 *
 *                         ———————————————
 *     front（队首）<<<        高    （优先级）    低          <<<   (队尾)rear
 *                         ———————————————
 *
 */
public class PriorityQueue<T extends Comparable<T>> extends DLinkList<T> {

    private ODER_TYPE oderType = ODER_TYPE.desc;   //默认降序优先队列

    public enum ODER_TYPE{
        desc,     //降序
        asc,      //升序
    }

    public PriorityQueue(ODER_TYPE oderType) {
        this.oderType = oderType;
    }

    /**入队*/
    public synchronized boolean enQueue(T item) {
        if(item==null)
            throw new NullPointerException("item data is null");

        DNode<T> newNode = new DNode<>();
        newNode.data = item;

        //如果队列为空，或者添加的元素优先级比队尾元素还低，则直接添加到队尾
        if(isEmpty() || item.compareTo(last.data) <= 0) {
            if (last == null)            //空表
                first = newNode;
            else {
                last.next = newNode;
                newNode.prior = last;
            }
            last = newNode;
            size++;
            return true;
        }

        DNode<T> p = first;
        //查找插入点 , p为从队首开始第一个小于插入值的元素，需要插入到p前面
        while (p != null && item.compareTo(p.data) <= 0)
            p = p.next;

        newNode.next = p;
        newNode.prior = p.prior;
        if(p.prior==null){   //p为队首结点
            first = newNode;
        }else{
            p.prior.next = newNode;
        }
        p.prior = newNode;

        size++;
        return true;
    }

    /**出队*/
    public synchronized T deQueue() {
        if(isEmpty())
            return null;
        return oderType == ODER_TYPE.desc ? remove(0):remove(length()-1);
    }

    /**返回队首元素，不执行删除操作*/
    public T peek() {
        return isEmpty()? null:
                oderType == ODER_TYPE.desc ? get(0):get(length()-1);
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        DNode mFirst = first;
        while(mFirst!=null){
            buffer.append(mFirst.data+", ");
            mFirst = mFirst.next;
        }
        return buffer.subSequence(0, buffer.lastIndexOf(", "))+"]";
    }
}
