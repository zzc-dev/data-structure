package com.zzc.list;

public class DoubleLinkedListDemo {
    public static void main(String[] args) throws Exception {
        DoubleLinkedList linkedList = new DoubleLinkedList();

        linkedList.addOrder(1, "z");
        linkedList.addOrder(12, "z");
        linkedList.remove();
        linkedList.elements(true);
        System.out.println();
        linkedList.addOrder(10, "z");
        linkedList.elements(true);
        System.out.println();

    }
}

/**
 * 双向链表，可排序，有头节点和尾节点
 *
 * */

class DoubleLinkedList{
    private Node head;
    private Node tail;

    /**
     * 添加尾节点
     * 1. 判断链表是否存在节点，不存在初始化head和tail，否则2
     * 2. 将数据添加到尾节点，并将当前节点置为尾节点
     * */
    public void add(int no, String name){
        Node newNode = new Node(no,name);
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.pre = tail;
        tail = newNode;
    }

    /**
     * 有序添加：
     *   1. 判断链表是否存在节点，不存在初始化head和tail，否则2
     *   2. 找到插入节点的位置
     *   3. 插入节点：
     *     3.1 如果当前节点为尾节点， 执行add的第二步操作
     *     3.2 如果当前节点为头节点，将newNode添加到头节点，并将newNode置为head
     *     3.3 以上都不满足，即中间节点， 10  <->  12  --> 11
     *       curNode.pre.next = newNode;  10 -> 11
     *       newNode.pre = curNode.pre;   11 -> 10
     *       curNode.pre = newNode;       12 -> 11
     *       newNode.next = curNode;      11 -> 12
     *       最终 : null <-10->  <-11->  <-12-> null
     *
     * */
    public void addOrder(int no, String name){
        Node newNode = new Node(no, name);

        if(head == null){
            head = tail = newNode;
            return;
        }

        Node temp = head;

        while (temp != null){
            if(temp.no - no > 0) {
                break;
            }
            temp = temp.next;
        }

        // 加在最后一位
        if(temp == null){
            add(no, name);
            return;
        }

        // 加在头部
        if(temp.pre == null){
            newNode.next = temp;
            temp.pre = newNode;
            head = newNode;
            return;
        }

        temp.pre.next = newNode;
        newNode.pre = temp.pre;
        newNode.next = temp;
        temp.pre = newNode;
    }

    public int remove() throws Exception {
        if(tail == null){
            throw new Exception("no data");
        }



        int value = tail.no;

        if(head == tail){
            head = tail = null;
            return value;
        }

        tail = tail.pre;
        tail.next = null;

        System.out.println(value);
        return value;
    }

    public void elements(boolean reserve){
        if(!reserve){
            Node temp = head;
            while (temp != null){
                System.out.println(temp);
                temp = temp.next;
            }
        }else {
            Node temp = tail;
            while (temp != null){
                System.out.println(temp);
                temp = temp.pre;
            }
        }

    }


    class Node{
        private int no;
        private String name;
        private Node pre;
        private Node next;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
