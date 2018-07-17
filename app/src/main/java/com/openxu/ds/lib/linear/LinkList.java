package com.openxu.ds.lib.linear;

import java.util.HashMap;
import java.util.Stack;

/**
 * autour : openXu
 * date : 2018/7/11 15:48
 * className : LinkList
 * version : 1.0
 * description : 单链表基本实现
 */
public class LinkList<T> implements IList<T>{


    public LNode<T> head;   //单链表开始结点

    /**
     * 1.1 创建单链表（头插法：倒序）
     * 解：遍历数组，创建新结点，新结点的指针域指向头结点，让新结点作为头结点
     * 时间复杂度O(n)
     * @param array
     * @return
     */
    public static <T> LinkList<T> createListF(T[] array){
        LinkList llist = new LinkList();
        if(array!=null && array.length>0) {
            for (T obj : array) {
                LNode<T> node = new LNode();
                node.data = obj;
                node.next = llist.head;
                llist.head = node;
            }
        }
        return llist;
    }
    /**
     * 1.2 创建单链表（尾插法：顺序）
     * 解：
     * 时间复杂度O(n)
     * @param array
     * @return
     */
    public static <T> LinkList<T>  createListR(T[] array){
        LinkList llist = new LinkList();
        if(array!=null && array.length>0){
            llist.head = new LNode();
            llist.head.data = array[0];
            LNode<T> temp = llist.head;
            for(int i = 1; i < array.length; i++){
                LNode node = new LNode();
                node.data = array[i];
                temp.next = node;
                temp = node;
            }
        }
        return llist;
    }

    /**
     * 判断单链表是否为空表
     * 时间复杂度O(1)
     * @return
     */
    @Override
    public boolean isEmpty() {
        return head==null;
    }
    /**
     * 4 获取单链表长度
     * 时间复杂度O(n)
     * @return
     */
    @Override
    public int length() {
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

    @Override
    public void clear() {
        head = null;
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
    public T get(int index) {
        return getNode(index).data;
    }
    /**
     * 6.1 获取指定索引的结点
     * 时间复杂度O(n)
     * @param index
     * @return
     */
    public LNode<T> getNode(int index){
        LNode node = head;
        int j = 0;
        while(j  <  index && node!=null){
            j++;
            node = node.next;
        }
        return node;
    }

    /**
     * 6.2 获取指定数据值结点的索引
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param data
     * @return
     */
    @Override
    public int indexOf(T data) {
        if(head==null)
            return -1;   //没有此结点
        LNode node = head;
        int j = 0;
        while(node!=null){
            if(node.data.equals(data))
                return j;
            j++;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T data) {
        if(head==null)
            return -1;
        int index = -1;
        LNode node = head;
        int j = 0;
        while(node!=null){
            if(node.data.equals(data)) {
                index = j;
            }
            j++;
            node = node.next;
        }
        return index;
    }

    /**
     * 6.3 单链表中的倒数第k个结点（k > 0）
     * 解：先找到顺数第k个结点，然后使用前后指针移动到结尾即可
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param k
     * @return
     */
    public LNode<T> getReNode(int k){
        if(head==null)
            return null;
        int len = length();
        if(k > len)
            return null;
        LNode target = head;
        LNode next = head;
        for(int i=0;i < k;i++)
            next = next.next;
        while(next!=null){
            target = target.next;
            next = next.next;
        }
        return target;
    }
    /**
     * 6.4 查找单链表的中间结点
     * 时间复杂度O(n) 空间复杂度O(1)
     * @return
     */
    public LNode getMiddleNode(){
        if(head == null|| head.next == null)
            return head;
        LNode target = head;
        LNode temp = head;
        while(temp != null && temp.next != null){
            target = target.next;
            temp = temp.next.next;
        }
        return target;
    }

    /**
     * 2.1 将单链表合并为一个单链表
     * 解：遍历第一个表，用其尾结点指向第二个表头结点
     *     时间复杂度O(n)
     * @return
     */
    public static LNode mergeList(LNode head1, LNode head2){
        if(head1==null) return head2;
        if(head2==null) return head1;
        LNode loop = head1;
        while(loop.next!=null)   //找到list1尾结点
            loop = loop.next;
        loop.next = head2;  //将list1尾结点指向list2头结点
        return head1;
    }

    /**
     * 2.1 通过递归，合并两个有序的单链表head1和head2
     *
     * 解：两个指针分别指向两个头结点，比较两个结点大小，
     *     小的结点指向下一次比较结果（两者中较小），最终返回第一次递归的最小结点
     * @param head1
     * @param head2
     * @return
     */
   public static LNode mergeSortedListRec(LNode head1, LNode head2){
        if(head1==null)return head2;
        if(head2==null)return head1;
       if (((int)head1.data)>((int)head2.data)) {
           head2.next = mergeSortedListRec(head2.next, head1);
           return head2;
       } else {
           head1.next = mergeSortedListRec(head1.next, head2);
           return head1;
       }
    }

    /**
     * 3.1 循环的方式将单链表反转
     * 时间复杂度O(n)   空间复杂度O(1)
     */
    public void reverseListByLoop() {
        if (head == null || head.next == null)
            return;
        LNode pre = null;
        LNode nex = null;
        while (head != null) {
            nex = head.next;
            head.next = pre;
            pre = head;
            head = nex;
        }
        head = pre;
    }

    /**
     * 3.2 递归的方式将单链表反转,返回反转后的链表头结点
     * 时间复杂度O(n)  空间复杂度O(n)
     */
    public LNode reverseListByRec(LNode head) {
        if(head==null||head.next==null)
            return head;
        LNode reHead = reverseListByRec(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;
    }
    /**
     * 5.1 获取单链表字符串表示
     * 时间复杂度O(n)
     */
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
    public static String display(LNode head){
        if(head == null)
            return "";
        LNode node = head;
        StringBuffer buffer = new StringBuffer();
        while(node != null){
            buffer.append(" -> "+node.data);
            node = node.next;
        }
        return buffer.toString();
    }

    /**
     * 5.2 用栈的方式获取单链表从尾到头倒叙字符串表示
     * 解：由于栈具有先进后出的特性，现将表中的元素放入栈中，然后取出就倒序了
     * 时间复杂度O(n)  空间复杂度O(1)
     * @return
     */
    public String displayReverseStack(){
        if(head == null)
            return "";
        Stack <LNode> stack = new Stack < >();   //堆栈 先进先出
        LNode head = this.head;
        while(head!=null){
            stack.push(head);
            head=head.next;
        }
        StringBuffer buffer = new StringBuffer();
        while(!stack.isEmpty()){
            //pop()移除堆栈顶部的对象，并将该对象作为该函数的值返回。
            buffer.append(" -> "+stack.pop().data);
        }
        return buffer.toString();
    }

    /**
     * 5.3 用递归的方式获取单链表从尾到头倒叙字符串表示
     * @return
     */
    public void displayReverseRec(StringBuffer buffer, LNode head){
        if(head==null)
            return;
        displayReverseRec(buffer, head.next);
        buffer.append(" -> ");
        buffer.append(head.data);
    }

    /**
     * 7.1 插入结点
     * 解：先找到第i-1个结点，让创建的新结点的指针域指向第i-1结点指针域指向的结点，
     *     然后将i-1结点的指针域指向新结点
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param data
     * @param index
     */
    @Override
    public boolean add(int index, T data) {
        if(index==0){  //插入为头结点
            LNode temp = new LNode();
            temp.next = head;
            return true;
        }
        int j = 0;
        LNode node = head;
        while(j < index-1 && node!=null){   //找到序列号为index-1的结点
            j++;
            node = node.next;
        }
        if(node==null)
            return false;
        LNode temp = new LNode();  //创建新结点
        temp.data = data;
        temp.next = node.next;     //新结点插入到Index-1结点之后
        node.next = temp;
        return true;
    }

    @Override
    public boolean add(T data) {
        LNode node = head;
        while(node!=null && node.next!=null)   //找到尾结点
            node = node.next;
        LNode temp = new LNode();  //创建新结点
        temp.data = data;
        node.next = temp;
        return false;
    }
    @Override
    public T remove(int index) {
        LNode<T> node = deleteNode(index);
        return node==null?null:node.data;
    }
    /**
     * 7.2 删除结点
     * 解：让被删除的结点前一个结点的指针域指向后一个结点指针域
     * 时间复杂度O(n) 空间复杂度O(1)
     * @return
     */
    public LNode deleteNode(int index){
        LNode node = head;
        if(index==0){           //删除头结点
            if(node==null)
                return null;
            head = node.next;
            return node;
        }
        //非头结点
        int j = 0;
        while(j < index-1 && node!=null){   //找到序列号为index-1的结点
            j++;
            node = node.next;
        }
        if(node==null)
            return null;
        LNode delete = node.next;
        if(delete==null)
            return null;            //不存在第index个结点
        node.next = delete.next;
        return delete;
    }

    @Override
    public boolean remove(T data) {
        return false;
    }

    @Override
    public boolean removeAll(T data) {
        return false;
    }

    /**
     * 7.3 给出一单链表头指针head和一节点指针delete，要求O(1)时间复杂度删除节点delete
     * 解：将delete节点value值与它下个节点的值互换的方法，
     *     但是如果delete是最后一个节点，需要特殊处理，但是总得复杂度还是O(1)
     * @return
     */
    public static void deleteNode(LNode head, LNode delete){
        if(delete==null)
            return;
        //首先处理delete节点为最后一个节点的情况
        if(delete.next==null){
            if(head==delete)       //只有一个结点
                head = null;
            else{
                //删除尾结点
                LNode temp = head;
                while(temp.next!=delete)
                    temp = temp.next;
                temp.next=null;
            }
        } else{
            delete.data = delete.next.data;
            delete.next = delete.next.next;
        }
        return;
    }

    /**
     * 8.1 判断一个单链表中是否有环
     * 解：使用快慢指针方法，如果存在环，两个指针必定指向同一结点
     * 时间复杂度O(n)  空间复杂度O(1)
     * @return
     */
    public static boolean hasCycle(LNode head){
        LNode p1 = head;
        LNode p2 = head;
        while(p1!=null && p2!=null){
            p1 = p1.next;       //一次跳一步
            p2 = p2.next.next;  //一次跳两步
            if(p2 == p1)
                return true;
        }
        return false;
    }

    /**
     * 8.2、已知一个单链表中存在环，求进入环中的第一个节点
     *  利用hashmap，不要用ArrayList，因为判断ArrayList是否包含某个元素的效率不高
     * @param head
     * @return
     */
    public static LNode getFirstNodeInCycleHashMap(LNode head){
        LNode target = null;
        HashMap<LNode,Boolean > map=new HashMap< >();
        while(head != null){
            if(map.containsKey(head)) {
                target = head;
                break;
            } else {
                map.put(head, true);
                head = head.next;
            }
        }
        return target;
    }

    /**
     *  8.3、已知一个单链表中存在环，求进入环中的第一个节点,不用hashmap
     *   用快慢指针，与判断一个单链表中是否有环一样，找到快慢指针第一次相交的节点，
     *   此时这个节点距离环开始节点的长度和链表头距离环开始的节点的长度相等
     *   参考 https://www.cnblogs.com/fankongkong/p/7007869.html
     * @param head
     * @return
     */
    public static LNode getFirstNodeInCycle(LNode head){
        LNode fast = head;
        LNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        if(fast == null||fast.next == null)
            return null;//判断是否包含环
        //相遇节点距离环开始节点的长度和链表投距离环开始的节点的长度相等
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }//同步走
        return slow;
    }

    /**
     * 9、判断两个单链表是否相交,如果相交返回第一个节点，否则返回null
     * ①、暴力遍历两个表，是否有相同的结点(时间复杂度O(n²))
     * ②、第一个表的尾结点指向第二个表头结点，然后判断第二个表是否存在环，但不容易找出交点（时间复杂度O(n)）
     * ③、两个链表相交，必然会经过同一个结点，这个结点的后继结点也是相同的（链表结点只有一个指针域，后继结点只能有一个），
     *     所以他们的尾结点必然相同。两个链表相交，只能是前面的结点不同，所以，砍掉较长链表的差值后同步遍历，判断结点是否相同，相同的就是交点了。
     *     时间复杂度（时间复杂度O(n)）
     * @param list1
     * @param list2
     * @return 交点
     */
    public static LNode isIntersect(LinkList list1, LinkList list2){
        LNode head1 = list1.head;
        LNode head2 = list2.head;
        if(head1==null || head2==null)return null;
        int len1 = list1.length();
        int len2 = list2.length();
        //砍掉较长链表的差值
        if(len1 >= len2){
            for(int i=0;i < len1-len2;i++){
                head1=head1.next;
            }
        }else{
            for(int i=0;i < len2-len1;i++){
                head2=head2.next;
            }
        }
        //同步遍历
        while(head1 != null&&head2 != null){
            if(head1 == head2)
                return head1;
            head1=head1.next;
            head2=head2.next;
        }
        return null;
    }

}
