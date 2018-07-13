package com.openxu.ds.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * autour : openXu
 * date : 2018/7/11 10:45
 * className : LinearArray
 * version : 1.0
 * description : 线性表的顺序存储结构（顺序表），是由数组来实现的
 */
public class LinearArray {

    private Object[] data;
    private int length;

    /**
     * 通过给定的数组 建立顺序表
     * @param objs
     * @return
     */
    public static LinearArray createArray(Object[] objs){
        LinearArray array = new LinearArray(objs.length);
        for(int i = 0; i<objs.length; i++)
            array.data[i] = objs[i];
        array.length = array.data.length;

        List list = new ArrayList();
        return array;
    }

    private LinearArray(int length){
        data = new Object[length];
        this.length = data.length;
    }

    /**
     * 判断线性表是否为空表
     * @return
     */
    public boolean isEmpty(){
        return data==null||data.length<=0;
    }

    /**
     * 获取线性表长度
     * @return
     */
    public int getLength(){
        return length;
//        return data.length;
    }

    /**
     * 输出线性表
     */
    @Override
    public String toString() {
        if(isEmpty())
            return "";
        String str = "[";
        for(int i = 0; i<data.length; i++){
            str += (data[i]+", ");
        }
        str = str.substring(0, str.lastIndexOf(", "));
        return str+"]";
    }

    /**
     * 获取线性表中某个元素
     * @param i
     * @return
     */
    public Object getElem(int i){
        if(isEmpty() || i<0 || i >= data.length)
            return null;
        return data[i];
    }

    /**
     * 查找元素obj第一次出现的索引
     * @param obj
     * @return
     */
    public int locateElem(Object obj){
        if(isEmpty())
            return -1;
        int loc = 0;
        while(loc<data.length && !isEqul(data[loc], obj))
            loc++;
        if(loc>=data.length)
            return -1;
        return loc;
    }

    private boolean isEqul(Object obj1, Object obj2){
        if(obj1 instanceof Integer)
            return (int)obj1 == (int)obj2;
        if(obj1 instanceof Long)
            return (long)obj1 == (long)obj2;
        if(obj1 instanceof Float)
            return (float)obj1 == (float)obj2;
        if(obj1 instanceof Double)
            return (double)obj1 == (double)obj2;
        else
            return obj1.equals(obj2);
    }

    /**
     * 向index位置插入元素elem
     * @param element
     * @param index
     * @return
     */
    public void insertElement(Object element, int index) {
        int length = data.length;
        if(isEmpty() || index>length)
            return;
        Object destination[] = new Object[length + 1];
        System.arraycopy(data, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(data, index, destination, index
                + 1, length - index);
        data = destination;
        this.length = data.length;
    }

    /**
     * 删除元素
     * @param index
     * @return
     */
    public void removeElement(int index) {
        int length = data.length;
        if(isEmpty() || index>=length)
            return;
        Object destination[] = new Object[length - 1];
        System.arraycopy(data, 0, destination, 0, index);
        System.arraycopy(data, index+1, destination, index,
                destination.length - index);
        data = destination;
        this.length = data.length;
    }

}
