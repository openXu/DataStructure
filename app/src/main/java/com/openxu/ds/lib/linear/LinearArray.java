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
    /**
     * 获取指定位置的元素
     * 分析：时间复杂度O(1)
     *       从顺序表中检索值是简单高效的，因为顺序表内部采用数组作为容器，数组可直接通过索引值访问元素
     */
    @Override
    public T get(int index) {
        if (index<0 || index >= datas.length)
            throw new IndexOutOfBoundsException();
        return (T) datas[index];
    }
    /**
     * 为指定索引的结点设置值
     * 分析：时间复杂度O(1)
     */
    @Override
    public T set(int index, T data) {
        if (index<0 || index >= datas.length)
            throw new IndexOutOfBoundsException();

        T oldValue = (T) datas[index];
        datas[index] = data;
        return oldValue;
    }

    /**
     * 判断是否包含某值只需要判断该值有没有出现过
     * 分析：时间复杂度O(n)
     */
    @Override
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }
    /**
     * 获取某值第一次出现的索引
     * 分析：时间复杂度O(n)
     */
    @Override
    public int indexOf(T data) {
        if (data == null) {
            for (int i = 0; i < datas.length; i++)
                if (datas[i]==null)
                    return i;
        } else {
            for (int i = 0; i < datas.length; i++)
                if (data.equals(datas[i]))
                    return i;
        }
        return -1;
    }
    /**
     * 获取某值最后一次出现的索引
     * 分析：时间复杂度O(n)
     */
    @Override
    public int lastIndexOf(T data) {
        if (data == null) {
            for (int i = datas.length-1; i >= 0; i--)
                if (datas[i]==null)
                    return i;
        } else {
            for (int i = datas.length-1; i >= 0; i--)
                if (data.equals(datas[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 指定位置插入元素
     * 分析：时间复杂度O(n)
     *       在数组中插入元素时，需要创建一个比原数组容量大1的新数组，
     *       将原数组中(0,index-1)位置的元素拷贝到新数组，指定新数组index位置元素值为新值，
     *       继续讲原数组(index, length-1)的元素拷贝到新数组
     * @param index
     * @param data
     * @return
     */
    @Override
    public boolean add(int index, T data) {
        if (index > datas.length || index < 0)
            throw new IndexOutOfBoundsException();
        Object destination[] = new Object[datas.length + 1];
        System.arraycopy(data, 0, destination, 0, index);
        destination[index] = data;
        System.arraycopy(data, index, destination, index
                + 1, datas.length - index);
        datas = destination;
        return true;
    }
    /**
     * 在顺序表末尾处插入元素
     * 分析：时间复杂度O(n)
     *       同上面一样，也需要
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        Object destination[] = new Object[datas.length + 1];
        System.arraycopy(data, 0, destination, 0, datas.length);
        destination[datas.length-1] = data;
        datas = destination;
        return true;
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
        datas = new Object[]{};
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "";
        String str = "[";
        for(int i = 0; i<datas.length; i++){
            str += (datas[i]+", ");
        }
        str = str.substring(0, str.lastIndexOf(", "));
        return str+"]";
    }
}
