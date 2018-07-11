package com.openxu.ds.lib.link;

/**
 * autour : openXu
 * date : 2018/7/11 15:48
 * className : LinkList
 * version : 1.0
 * description : 单链表基本实现
 */
public class LinkList {

    public LNode head;   //单链表头结点

    /**
     * 创建单链表（头插法：倒序）
     * 时间复杂度O(n)
     * @param objs
     * @return
     */
    public static LinkList createListF(Object[] objs){
        LinkList llist = new LinkList();
        if(objs!=null && objs.length>0) {
            for (Object obj : objs) {
                LNode node = new LNode();
                node.data = obj;
                node.next = llist.head;
                llist.head = node;
            }
        }
        return llist;
    }
    /**
     * 创建单链表（尾插法：顺序）
     * 时间复杂度O(n)
     * @param objs
     * @return
     */
    public static LinkList createListR(Object[] objs){
        LinkList llist = new LinkList();
        if(objs!=null && objs.length>0){
            llist.head = new LNode();
            llist.head.data = objs[0];
            LNode temp = llist.head;
            for(int i = 1; i<objs.length; i++){
                LNode node = new LNode();
                node.data = objs[i];
                temp.next = node;
                temp = node;
            }
        }
        return llist;
    }

    /**
     * 将单链表合并为一个单链表
     * 时间复杂度O(n)
     * @param list1
     * @param list2
     * @return
     */
    public static LinkList mergeList(LinkList list1, LinkList list2){
        LNode node = list1.head;
        if(node == null)
            return list2;
        while(node.next!=null)   //找到list1尾结点
            node = node.next;
        node.next = list2.head;  //将list1尾结点指向list2头结点
        return list1;
    }

    /**
     * 判断单链表是否为空表
     * 时间复杂度O(1)
     * @return
     */
    public boolean listEmpty(){
        return head==null;
    }

    /**
     * 获取单链表长度
     * 时间复杂度O(n)
     * @return
     */
    public int listLength(){
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


    /**
     * 输出单链表
     * 时间复杂度O(n)
     */
    public String display(){
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


    /**
     * 循环的方式将单链表反转
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
    }

    /**
     * 递归的方式将单链表反转
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
     * 获取指定索引的结点
     * 时间复杂度O(n)
     * @param index
     * @return
     */
    public LNode getNode(int index){
        LNode node = head;
        int j = 0;
        while(j<index && node!=null){
            j++;
            node = node.next;
        }
        return node;
    }

    /**
     * 获取结点索引
     * 时间复杂度O(n)
     * @param obj
     * @return
     */
    public int locateNode(Object obj){
        if(head==null)
            return -1;   //没有此结点
        LNode node = head;
        int j = 0;
        while(node!=null){
            if(node.data.equals(obj))
                return j;
            j++;
            node = node.next;
        }
        return -1;
    }

    /**
     * 插入结点
     * 时间复杂度O(n)
     * @param obj
     * @param index
     */
    public boolean insertNode(Object obj, int index){
        if(index==0){  //插入为头结点
            LNode temp = new LNode();
            temp.next = head;
            return true;
        }

        int j = 0;
        LNode node = head;
        while(j<index-1 && node!=null){   //找到序列号为index-1的结点
            j++;
            node = node.next;
        }
        if(node==null)
            return false;
        LNode temp = new LNode();  //创建新结点
        temp.data = obj;
        temp.next = node.next;     //新结点插入到Index-1结点之后
        node.next = temp;
        return true;
    }

    /**
     * 删除结点
     * 时间复杂度O(n)
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
        while(j<index-1 && node!=null){   //找到序列号为index-1的结点
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

//
//
//            * 3. 查找单链表中的倒数第K个结点（k > 0）: reGetKthNode
// * 4. 查找单链表的中间结点: getMiddleNode
// * 5. 从尾到头打印单链表: reversePrintListStack，reversePrintListRec（递归）
//            * 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序: mergeSortedList, mergeSortedListRec
// * 7. 对单链表进行排序,listSort（归并）,insertionSortList（插入）
//            * 8. 判断一个单链表中是否有环: hasCycle
// * 9. 判断两个单链表是否相交: isIntersect
// * 10. 已知一个单链表中存在环，求进入环中的第一个节点: getFirstNodeInCycle, getFirstNodeInCycleHashMap
// * 11. 给出一单链表头指针head和一节点指针delete，O(1)时间复杂度删除节点delete: deleteNode

}
