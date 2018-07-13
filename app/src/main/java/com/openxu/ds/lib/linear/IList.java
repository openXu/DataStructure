package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/7/13 15:41
 * className : IList
 * version : 1.0
 * description : 线性表的抽象数据类型
 */
public interface IList<T> {

    /**
     * 判断线性表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取长度
     * @return
     */
    int length();

    /**
     * 将结点添加到指定序列的位置
     * @param index
     * @param data
     * @return
     */
    boolean add(int index, T data);

    /**
     * 将指定的元素追加到列表的末尾
     * @param data
     * @return
     */
    boolean add(T data);

    /**
     * 根据index移除元素
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 移除值为data的第一个结点
     * @param data
     * @return
     */
    boolean remove(T data);

    /**
     * 移除所有值为data的结点
     * @param data
     * @return
     */
    boolean removeAll(T data);
    /**
     * 清空表
     */
    void clear();

    /**
     * 设置指定序列元素的值
     * @param index
     * @param data
     * @return
     */
    T set(int index, T data);

    /**
     * 是否包含值为data的结点
     * @param data
     * @return
     */
    boolean contains(T data);

    /**
     * 根据值查询索引
     * @param data
     * @return
     */
    int indexOf(T data);

    /**
     * 根据data值查询最后一次出现在表中的索引
     * @param data
     * @return
     */
    int lastIndexOf(T data);
    /**
     * 获取指定序列的元素
     * @param index
     * @return
     */
    T getElem(int index);
    /**
     * 输出格式
     * @return
     */
    String toString();

}
