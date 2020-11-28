package com.zzc.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 赫夫曼树：
 *   wpl：所有叶子的权值 * 深度之和最小
 *
 * 赫夫曼编码：
 *  赫夫曼树：向左路径为0，向右路径为1，符合前缀编码
 *
 * */
public class HefumanTree {


    public static List<Node> list = new ArrayList();
    static {
        list.add(new Node(13));
        list.add(new Node(7));
        list.add(new Node(8));
        list.add(new Node(3));
        list.add(new Node(29));
        list.add(new Node(6));
        list.add(new Node(1));
    }

    /**
     * 生成赫夫曼树
     * */
    public static Node createHefuManTree(){
        Node temp;
        while (list.size() > 1){
            // 1. 从小到大排序
            list.sort(Node::compareTo);
            // 2. 弹出两个元素组成二叉树
            temp = new Node(list.get(0).data + list.get(1).data);
            temp.leftNode = list.get(0);
            temp.rightNode = list.get(1);
            // 3. 删除弹出的两个头节点，并将父节点temp加入list
            list.remove(0);
            list.remove(0);
            list.add(temp);
        }
        return list.get(0);
    }




    static class Node implements Comparable<Node>{
        int data;
        Node leftNode;
        Node rightNode;

        public Node(int data){
            this.data = data;
        }

        @Override
        public int compareTo(Node o) {
            return this.data - o.data;
        }
    }
}
