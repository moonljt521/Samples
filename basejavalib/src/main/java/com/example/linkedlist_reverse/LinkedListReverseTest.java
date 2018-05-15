package com.example.linkedlist_reverse;

/**
 * author: moon
 * created on: 17/10/11 下午12:33
 * description:  反转一个单链表 ！！
 */
public class LinkedListReverseTest {

    public static void main(String[] args) {
        // 设置三个节点
        Node head = new Node();
        head.setData(1);
        Node h2 = new Node();
        h2.setData(2);
        Node h3 = new Node();
        h3.setData(3);
        Node h4 = new Node();
        h4.setData(9);
        Node h5 = new Node();
        h5.setData(0);

        // 设置指针域,构成一个链表
        head.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);
        h4.setNext(h5);

        Node h = head;
        while (null != h){

            System.out.println(">>"+h.getData());

            h = h.getNext();
        }

//        head = reverseNode(head);
        head = reverseNode3(head);


        while (null != head){

            System.out.println("<<"+head.getData());

            head = head.getNext();
        }

    }

    /**
     * 方式1 ： 递归
     * @param node
     * @return  消耗内存太多
     */
    private static Node reverseNode(Node node){
        if (node == null || node.getNext() == null){
            return node;
        }

        Node n1 = reverseNode(node.getNext());

        node.getNext().setNext(node);
        node.setNext(null);
        return n1;
    }

    /**
     * 方式2 ： 赋值临时变量，遍历
     * @param node
     * @return
     */
    private static Node reverseNode2(Node node){
        if (node == null|| node.getNext() == null){
            return node;
        }

        Node pre = node;
        Node cur = pre.getNext();
        Node tmp;

        while (cur!=null){
            tmp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tmp;
        }

        node.setNext(null);


        return pre;
    }

    /**
     * 方式3 ：最易懂 ！！！！
     *  只需要遍历一遍链表，在遍历过程中，把遍历的节点依次插入到头部。
     * @param node
     * @return
     */
    private static Node reverseNode3(Node node){

        Node pre = null;

        while (node !=null){
            Node tmp = node.next;

            node.next = pre ;
            pre = node;
            node = tmp;
        }
        return pre;
    }



    private static class Node{

        private int data;

        private Node next;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


}


