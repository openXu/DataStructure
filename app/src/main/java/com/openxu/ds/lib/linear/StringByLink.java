package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/8/8 15:59
 * className : StringByLink
 * version : 1.0
 * description : 串的链式存储结构的实现（顺序串）
 */
public class StringByLink {

    public LNode<Character> head;   //单链表开始结点

    public StringByLink(){}

    /**通过字符数组构建字符串*/
    public StringByLink(char[] chars){
        if(chars==null)
            throw new NullPointerException("chars could not be null");
        head = new LNode();
        head.data = chars[0];
        LNode<Character> temp = head;
        for(int i = 1; i < chars.length; i++){
            LNode node = new LNode();
            node.data = chars[i];
            temp.next = node;
            temp = node;
        }
    }
    /**获取字符串长度*/
    public int length(){
        if(head==null)
            return 0;
        int l = 1;
        LNode node = head;
        while(node.next!=null) {
            l++;
            node = node.next;
        }
        return l;
    }
    /**获取索引为index的字符*/
    public char charAt(int index){
        if(index<0)
            throw new ArrayIndexOutOfBoundsException("index is out of bounds");
        LNode<Character> node = head;
        int j = 0;
        while(j  <  index){
            j++;
            node = node.next;
            if(node==null)
                throw new ArrayIndexOutOfBoundsException("index is out of bounds");
        }
        return node.data;
    }
    /**获取子串（包含头不包含尾）*/
    public StringByLink subString(int start, int end){
        int length = length();
        if(start<0 || start>=length || end<0 || end>length)
            throw new ArrayIndexOutOfBoundsException("index is out of bounds");
        StringByLink newStr = new StringByLink();
        LNode<Character> oldNode = head;
        LNode<Character> newHead = null;
        int j = 0;
        while(oldNode!=null){
            if(j>=start && j<end){
                LNode node = new LNode();
                node.data = oldNode.data;
                if(j==start) {
                    newHead = node;
                    newStr.head = newHead;
                }
                newHead.next = node;
                newHead = node;
            }
            j++;
            oldNode = oldNode.next;
        }
        return newStr;
    }
    /**是否包含某个字符*/
    public boolean contains(char c){
        if(head==null)
            return false;
        int index = -1;
        LNode<Character> node = head;
        while(node!=null){
            if(node.data==c)
                return true;
            node = node.next;
        }
        return false;
    }
    @Override
    public String toString() {
        if(head == null)
            return "";
        LNode node = head;
        StringBuffer buffer = new StringBuffer();
        while(node != null){
            buffer.append(node.data+" -> ");
            node = node.next;
        }
        return buffer.toString();
    }

}
