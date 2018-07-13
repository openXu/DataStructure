package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/7/11 10:45
 * className : LinearArray
 * version : 1.0
 * description : 线性表的顺序存储结构（顺序表），是由数组来实现的
 */
public class LinearArray<T> implements IList<T>{

    private Object[] datas;

    /**
     * 通过给定的数组 建立顺序表
     * @param objs
     * @return
     */
    public static <T> LinearArray<T> createArray(T[] objs){
        LinearArray<T> array = new LinearArray();
        array.datas = new Object[objs.length];
        for(int i = 0; i<objs.length; i++)
            array.datas[i] = objs[i];
        return array;
    }
    private LinearArray(){
    }

    @Override
    public boolean isEmpty() {
        return datas.length == 0;
    }

    @Override
    public int length() {
        return datas.length;
    }

    @Override
    public T set(int index, T data) {
        return null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public int indexOf(T data) {
        return 0;
    }

    @Override
    public int lastIndexOf(T data) {
        return 0;
    }

    @Override
    public T getElem(int index) {
        return null;
    }


    @Override
    public boolean add(int index, T data) {
        return false;
    }

    @Override
    public boolean add(T data) {
        return false;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean remove(T data) {
        return false;
    }

    @Override
    public boolean removeAll(T data) {
        return false;
    }

    @Override
    public void clear() {

    }


}
