package com.zzc.list;

/**
 * 环形链表
 * <p>
 * 约瑟夫问题：
 * n个人报成环， 从k个位置从1开始报数，报到m出列，从出列的下一个有从1开始报数，知道所有人出列为止
 */
public class CircleLinkedListDemo {

    public static void main(String[] args) {
        CircleLinked linked = new CircleLinked(5);
        linked.poll(1, 2);
    }

}

class CircleLinked {

    int size;

    Node head;

    public CircleLinked(int size) {
        if (size <= 0) {
            System.out.println("size 必须大于1");
            return;
        }
        Node node = new Node(1);
        head = node;
        if(size == 1){
            head.next = node;
            return;
        }


        for (int i = 2; i <= size; i++) {
            Node node1 = new Node(i);
            node.next = node1;
            node = node1;
            if(i == 5){
                node.next = head;
            }
        }

        this.size = size;


    }

    // 默认k和m都是正整数
    public int poll(int k, int m) {
        final int k0 = k;
        final int m0 = m;
        if (size == 0) {
            return -1;
        }

        if (size == 1) {
            int val = head.data;
            head = null;
            size--;
            System.out.println(val);
            return val;
        }

        // 当前节点
        Node curNode = head;
        // 当前节点的上一个节点
        Node preNode = curNode;
        while (--k != 0) {
            curNode = curNode.next;
        }

        while (--m != 0) {
            curNode = curNode.next;
        }

        while (true) {
            if (preNode.next == curNode) {
                break;
            }
            preNode = preNode.next;
        }

        int value = curNode.data;
        preNode.next = curNode.next;
        curNode.next = null;

        head = preNode.next;

        size--;
        System.out.println(value);
        poll(k0,m0);
        return value;
    }




    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
