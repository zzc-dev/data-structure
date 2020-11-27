package com.zzc.binaryTree;

import java.util.Stack;

/**
 * 顺序储存二叉树
 *   二叉树必须是完全二叉树
 *   数组转二叉树：
 *      curNode.leftIndex = curNode.index * 2 + 1
 *      curNode.rightIndex = curNode.index * 2 + 2
 *      curNode.parentIndex = (curNode.index - 1) / 2
 *
 *
 * @author zzc
 * @since 2020-11-27
 */
public class ArrayBinaryTree {
    public static Node root;
    static int[] arr = new int[]{1,2,3,4,5,6,7,8};
    public static void main(String[] args) {

//        convert(arr);
        preList();
        System.out.println();
        preListNR();
    }

    // 12485367
    public static void preList(){
        if(arr.length == 0){
            return;
        }
        preList(0);
    }

    public static void preListNR(){
        Stack<Integer> nodes = new Stack<>();
        nodes.push(0);
        while (!nodes.isEmpty()){
            Integer data = nodes.pop();
            System.out.print(arr[data]);
            if(data*2+2 < arr.length){
                nodes.push(data*2+2);
            }
            if(data*2+1 < arr.length){
                nodes.push(data*2+1);
            }

        }
    }

    private static void preList(int index) {
        System.out.print(arr[index]+"");
        if(index < arr.length && (index*2+1) < arr.length){
            preList(index*2+1);
        }
        if(index < arr.length && (index*2+2) < arr.length){
            preList(index*2+2);
        }
    }

    public static void convert(int[] arr){
        if(arr.length == 0){
            System.out.println("no data");
            return;
        }
        root = new Node(arr[0]);
        for(int i=1;i<arr.length;i++){
            Node node = new Node(arr[i]);
        }
    }









    static class Node{
        public int data;
        public Node left;
        public Node right;

        public Node(int data){
            this.data = data;
        }
    }
}
