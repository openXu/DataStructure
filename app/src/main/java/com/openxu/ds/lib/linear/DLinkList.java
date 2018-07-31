package com.openxu.ds.lib.linear;

import com.openxu.oxlib.utils.LogUtil;

/**
 * autour : openXu
 * date : 2018/7/11 15:48
 * className : LinkList
 * version : 1.0
 * description : 双链表基本实现
 */
public class DLinkList<T> implements IList<T>{


    protected DNode<T> first;  //双链表开始结点
    protected DNode<T> last;   //双链表末端结点
    protected int size;          //结点数

    /**
     * 创建单链表（头插法：倒序）
     * 时间复杂度O(n)
     * @param array
     * @return
     */
    public static <T> DLinkList<T> createListF(T[] array){
        DLinkList dlist = new DLinkList();
        if(array!=null && array.length>0) {
            dlist.size = array.length;
            for (T obj : array) {
                DNode<T> node = new DNode();
                node.data = obj;
                node.next = dlist.first;
                if(dlist.first!=null)
                    dlist.first.prior = node;   //相比单链表多了此步
                else
                    dlist.last = node;
                dlist.first = node;
            }
        }
        return dlist;
    }
    /**
     * 1.2 创建单链表（尾插法：顺序）
     * 时间复杂度O(n)
     * @param array
     * @return
     */
    public static <T> DLinkList<T> createListR(T[] array){
        DLinkList dlist = new DLinkList();
        if(array!=null && array.length>0){
            dlist.size = array.length;
            dlist.first = new DNode<T>();
            dlist.first.data = array[0];
            dlist.last = dlist.first;
            for(int i = 1; i < array.length; i++){
                DNode<T> node = new DNode();
                node.data = array[i];
                dlist.last.next = node;
                node.prior = dlist.last;    //相比单链表多了此步
                dlist.last = node;
            }
        }
        return dlist;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int length() {
        return size;
    }

    /**2 添加结点*/
    @Override
    public boolean add(int index, T data) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        DNode<T> newNode = new DNode();
        newNode.data = data;
        if (index == size) {           //在末尾添加结点不需要遍历
            final DNode<T> l = last;
            if (l == null)            //空表
                first = newNode;
            else {
                l.next = newNode;
                newNode.prior = l;
            }
            last = newNode;
            size++;
        } else {
            //其他位置添加结点需要遍历找到index位置的结点
            DNode<T> indexNode = getNode(index);
            DNode<T> pred = indexNode.prior;
            newNode.prior = pred;
            newNode.next = indexNode;
            indexNode.prior = newNode;
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            size++;
        }
        return false;
    }
    @Override
    public boolean add(T data) {
        return add(size, data);
    }

    /**3 删除结点*/
    @Override
    public T remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return unlink(getNode(index));
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            for (DNode<T> x = first; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (DNode<T> x = first; x != null; x = x.next) {
                if (data.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean removeAll(T data) {
        boolean result = false;
        if (data == null) {
            for (DNode<T> x = first; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    result = true;
                }
            }
        } else {
            for (DNode<T> x = first; x != null; x = x.next) {
                if (data.equals(x.data)) {
                    unlink(x);
                    result = true;
                }
            }
        }
        return result;
    }
    /**
     * 将指定的结点解除链接
     * @param x
     * @return
     */
    private T unlink(DNode<T> x) {
        // assert x != null;
        final T element = x.data;
        final DNode<T> next = x.next;
        final DNode<T> prev = x.prior;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prior = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prior = prev;
            x.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        for (DNode<T> x = first; x != null; ) {
            DNode<T> next = x.next;
            x.data = null;
            x.next = null;
            x.prior = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    /**
     * 设置结点值
     * @param index
     * @param data
     * @return
     */
    @Override
    public T set(int index, T data) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        DNode<T> x = getNode(index);
        T oldVal = x.data;
        x.data = data;
        return oldVal;
    }

    /**
     * 判断是否存在结点值
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    /**
     * 检索结点值
     * @param data
     * @return
     */
    @Override
    public int indexOf(T data) {
        int index = 0;
        if (data == null) {
            for (DNode<T> x = first; x != null; x = x.next) {
                if (x.data == null)
                    return index;
                index++;
            }
        } else {
            for (DNode<T> x = first; x != null; x = x.next) {
                if (data.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T data) {
        int index = size;
        if (data == null) {
            for (DNode<T> x = last; x != null; x = x.prior) {
                index--;
                if (x.data == null)
                    return index;
            }
        } else {
            for (DNode<T> x = last; x != null; x = x.prior) {
                index--;
                if (data.equals(x.data))
                    return index;
            }
        }
        return -1;
    }


    @Override
    public T get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return getNode(index).data;
    }
    /**
     * 获取指定索引的结点
     * 解：由于双链表能双向检索，判断index离开始结点近还是终端结点近，从近的一段开始遍历
     * 时间复杂度O(n)
     * @param index
     * @return
     */
    private DNode<T> getNode(int index) {
        if (index < (size >> 1)) {
            DNode<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            DNode<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prior;
            return x;
        }
    }

    /**
     * 倒序
     * 遍历每个结点，让node.next = node.prior;  node.prior = (node.next此值需要体现保存);
     */
    public void reverse(){
        last = first;           //反转后终端结点=开始结点
        DNode now = first;
        DNode next;
        while(now!=null){
            next = now.next;    //保存当前结点的后继结点
            now.next = now.prior;
            now.prior = next;
            first = now;
            now = next;
        }
    }


    @Override
    public String toString() {
        if(size == 0)
            return "";
        DNode node = first;
        StringBuffer buffer = new StringBuffer();
        buffer.append("         ");
        while(node != null){
            buffer.append(node.data+" -> ");
            node = node.next;
        }
        buffer.append("next\npre");
        node = last;
        int start = buffer.length();
        LogUtil.i(getClass().getSimpleName(), "buffer长度："+buffer.length());
        while(node != null){
            buffer.insert(start ," <- "+node.data);
            node = node.prior;
        }
        return buffer.toString();
    }
}
