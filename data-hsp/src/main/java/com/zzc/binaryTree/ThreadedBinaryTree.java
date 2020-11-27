package com.zzc.binaryTree;

/**
 * 线索化二叉树
 *
 * @author zzc
 * @since 2020-11-27
 */
public class ThreadedBinaryTree {
    static Node root;

    public static void main(String[] args) {
        create();
//        midList(root);  //8  4  2  5  1  6  3  7
        midBuildThreaded(root);
        listThreaded();
    }

    static void create() {
        root = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        root.leftNode = node2;
        root.rightNode = node3;
        node2.leftNode = node4;
        node2.rightNode = node5;
        node3.leftNode = node6;
        node3.rightNode = node7;
        node4.leftNode = node8;
    }

    static Node preNode = null;

    /**
     * 中序构建线索二叉树
     * */
    static void midBuildThreaded(Node node) {
        if (node == null) {
            return;
        }

        // （一）先线索左子树
        midBuildThreaded(node.leftNode);

        // （二）线索化当前节点
        // preNode -> node  =>  preNode.right = node; node.left = preNode;
        // 2.1 当前节点的左子树为空，则将当前节点的左子树（前驱节点）指向preNode
        if(preNode!=null && node.leftNode == null){
            node.leftNode = preNode;
            node.isPreNode = true;
        }
        // 2.2 当前节点的右子树为空，则将preNode的右子树（后继节点）指向node
        if(preNode!=null && preNode.rightNode == null){
            preNode.rightNode = node;
            preNode.isAfterNode = true;
        }

        // (三) 将preNode执行node
        preNode = node;

        // （四）最后线索化右子树
        midBuildThreaded(node.rightNode);

    }

    /**
     * 遍历线索化二叉树
     * */
    static void listThreaded(){
        Node node = root;
        while (node != null){
            // 找到二叉树的有效左子节点
            while (node.leftNode != null && !node.isPreNode){
                node = node.leftNode;
            }
            // 输出当前节点（中序遍历时最先打印的节点）
            System.out.print(node.data+"  ");
            // 输出后继节点
            while (node.rightNode != null && node.isAfterNode){
                node = node.rightNode;
                System.out.print(node.data+"  ");
            }
            // 此时后继节点是有效左节点 用当前右子树替换遍历节点，重新去找有效左子节点
            node = node.rightNode;
        }
    }
    
    /**
     * 中序遍历二叉树
     * */
    static void midList(Node node) {
        if (node == null) {
            return;
        }
        if (node.leftNode != null) {
            midList(node.leftNode);
        }
        System.out.print(node.data + "  ");
        if (node.rightNode != null) {
            midList(node.rightNode);
        }

    }
    


    static class Node {
        int data;
        Node leftNode;
        boolean isPreNode; // 前驱节点
        Node rightNode;
        boolean isAfterNode; //后继节点

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return leftNode.data + "==" + data + rightNode.data;
        }
    }
}
