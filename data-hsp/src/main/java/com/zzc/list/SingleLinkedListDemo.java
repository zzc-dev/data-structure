package com.zzc.list;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleSortLinkedList linkedList = new SingleSortLinkedList();
        SingleSortLinkedList linkedList1 = new SingleSortLinkedList();

        Hero hero1 = new Hero(1, "zzc");
        Hero hero2 = new Hero(3, "zzc");
        Hero hero3 = new Hero(5, "zzc");
        Hero hero4 = new Hero(4, "zzc");

        Hero hero5 = new Hero(8, "zzc");
        Hero hero6 = new Hero(2, "zzc");



        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero4);
        linkedList.add(hero3);
        linkedList1.add(hero5);
        linkedList1.add(hero6);

        linkedList.elements();
        linkedList1.elements();

        linkedList.add(linkedList1);
        System.out.println("添加");
        linkedList.elements();
    }

    public static void test1(){
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.elements();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.elements();
    }

}

/**
 * 单向链表 --- 不考虑顺序
 * */
class SingleLinkedList{

    private final Node head = new Node(null);

    public void add(int value){
        Node newNode = new Node(value);

        if(head.next == null){
            head.next = newNode;
            return;
        }

        // 找到尾节点
        Node temp = head.next;

        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void elements(){
        if(head.next == null){
            System.out.println("No data");
            return;
        }
        Node temp = head.next;
        while (true){
            if(temp == null){
                return;
            }
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    class Node{
        Integer data;
        Node next;

        public Node(Integer data){
            this.data = data;
        }
    }
}

/**
 * 单向链表 --- 考虑顺序
 * */
class SingleSortLinkedList{

    private final Node head = new Node(null);

    public void add(Hero value){
        if(value == null){
            try {
                throw new Exception("data is null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Node newNode = new Node(value);
        Node temp = head;

        // 找到添加位置的前一个节点
        while (true){
            if(temp.next == null){
                break;
            }

            if(temp.next.data.compareTo(value) >  0){
                break;
            }
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;

    }

    public void update(Hero value){
        if(head.next == null){
            System.out.println("no data can not update");
            return;
        }

        Node temp = head.next;

        while (true){
            if(temp == null){
                System.out.println("五匹配数据");
                return;
            }
            if(temp.data.no == value.no){
                temp.data.name = value.name;
                return;
            }
            temp = temp.next;
        }
    }

    public void remove(){

        if(head.next ==null){
            return;
        }

        // 找到最后一个节点的前一个节点
        Node temp = head;

        while (true){
            if(temp.next.next == null){
                temp.next = null;
                break;
            }
            temp = temp.next;
        }
    }

    public void removeAt(int no){

        Node temp = head;

        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.data.no == no){
               temp.next = temp.next.next;
               return;
            }
            temp = temp.next;
        }

    }

    public void elements(){
        if(head.next == null){
            System.out.println("No data");
            return;
        }
        Node temp = head.next;
        while (true){
            if(temp == null){
                return;
            }
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * 获取链表中有效节点个数
     * */
    public int size(){
        Node temp = head;
        int count = 0;
        while (true){
            if(temp.next == null){
                break;
            }
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 获取链表中倒数第k个元素
     * */
    public Integer getDataByLastIndex(int k){
        if(k > size() || k <= 0){
            return null;
        }
        int index = size() - k;
        Node temp = head.next;
        while (index-- > 0){
            temp = temp.next;
        }
        return temp.data.no;
    }

    /**
     * 将链表反转
     *  1. 先定义一个节点reseverNode用于临时接受反转的链表
     *  2. 从头到尾遍历链表，每遍历一个节点，就将其取出，并放在新的链表reserverNode的最前端
     *     null -> 1
     *     null -> 2 -> 1
     *  3. head.next = reseverNode.next; 完成反转操作
     * */
    public void resever(){
        Node reseverNode = new Node(null);
        Node curNode = head.next;
        Node nextNode;
        while (curNode != null){
            nextNode = curNode.next;

            curNode.next = reseverNode.next;
            reseverNode.next = curNode;

            curNode = nextNode;
        }

        head.next = reseverNode.next;
    }

    /**
     * 反向打印：
     *   1.利用resever，但会破坏原来链表的结构
     *   2. 利用Stack，先进后厨
     * */
    public void reseverPrint(){
        Node temp = head.next;

        Stack<Node> stack = new Stack<Node>();
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        while (true){
            if (stack.size() == 0){
                break;
            }
            System.out.println(stack.pop().data);

        }
    }

    /**
     * 添加链表
     * */
    public void add(SingleSortLinkedList linkedList){
        Node node = linkedList.head.next;
        while (node != null){
            add(node.data);
            node = node.next;
        }
    }

    class Node{
        Hero data;
        Node next;

        public Node(Hero data){
            this.data = data;
        }
    }
}

class Hero implements Comparable<Hero>{
    public int no;
    public String name;

    public Hero(int no, String name) {
        this.no = no;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int compareTo(Hero o) {
        return no - o.no;
    }
}


