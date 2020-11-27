package com.zzc.binaryTree;

import java.util.Stack;

/**
 * @author zzc
 * @since 2020-11-26
 *  二叉树的前中后序遍历
 *    递归与非递归
 */
public class CommonBinaryTree {

    public static Node root;

    public static void main(String[] args) {
        create();
//        list();
//        System.out.println();
//        preListUseNotRecursion();
//        System.out.println();
//        midListUseNotRecursion();
//        System.out.println();
//        afterListUseNotRecusion();
        remove(2);
        preListUseNotRecursion();
    }


    public static void list(){
        if(root == null){
            System.out.println("无节点");
            return;
        }
        System.out.println("前序：");
        preList(root);
        System.out.println();
        System.out.println("中序：");
        midList(root);
        System.out.println();
        System.out.println("后序：");
        afterList(root);
    }

    /**
     * 前序遍历  1 2 4 6 7 5 8 3
     *  1：先输出当前节点
     *  2：输出左节点
     *  3：输出右节点
     * */
    public static void preList(Node node){
       if(node != null){
           System.out.print(node.data + "  ");
       }
       if(node.left != null){
           preList(node.left);
       }
       if(node.right != null){
           preList(node.right);
       }
    }

    /**
     * 借助栈，对于根节点，先将当前节点压入栈中，然后遍历的时候弹出栈中的一个元素，输出，当该节点的右节点不为空时，将节点压入栈，当左节点不为空时，将左节点压入栈
     * [前序遍历是根左右但是栈的数据结构时先入后出，先访问到左节点，需要将右节点先压入栈中]。
     * 继续循环，弹出栈顶元素，输出，将右节点和左节点压入栈中...
     * */
    public static void preListUseNotRecursion(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node curNode = stack.pop();
            System.out.print(curNode.data + "  ");
            if(curNode.right != null){
                stack.push(curNode.right);
            }
            if(curNode.left != null){
                stack.push(curNode.left);
            }
        }
    }

    /**
     * 中序遍历  6  4  7  2  8  5  1  3
     *  1：输出左节点
     *  2：先输出当前节点
     *  3：输出右节点
     * */
    public static void midList(Node node){
        if(node.left != null){
            midList(node.left);
        }
        if(node != null){
            System.out.print(node.data + "  ");
        }

        if(node.right != null){
            midList(node.right);
        }
    }

    /**
     * 1. 初始化栈，令curNode = root
     * 2. 先将curNode压入栈中，然后令curNode=curNode.left,一次压入栈中，直到curNode为空
     * 3. 弹出栈顶元素并打印，在使curNode=栈顶元素.right，一直重复2，直到栈空为止
     * */
    public static void midListUseNotRecursion(){
        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        stack.push(root);
        while (!stack.isEmpty()){
           while(curNode !=null && curNode.left != null){
               stack.push(curNode.left);
               curNode = curNode.left;
           }
           curNode = stack.pop();
            System.out.print(curNode.data + "  ");
            curNode = curNode.right;
            if(curNode != null){
                stack.push(curNode);
            }
        }
    }

    /**
     * 后序遍历  6 7 4 8 5 2 3 1
     *  1：输出左节点
     *  2：输出右节点
     *  3：先输出当前节点
     * */
    public static void afterList(Node node){
        if(node.left != null){
            afterList(node.left);
        }

        if(node.right != null){
            afterList(node.right);
        }
        if(node != null){
            System.out.print(node.data + "  ");
        }
    }

    /**
     * 1.定义两个栈s1,s2,首先将root压入s1
     * 2. 从s1弹出一个元素，压入s2,并将s1的左右节点依次压入s1
     * 3. 重复2,直到s1为空
     * */
    public static void afterListUseNotRecusion(){
        Stack<Node> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()){
            Node node = s1.pop();
            s2.push(node.data);
            if(node.left != null){
                s1.push(node.left);
            }
            if(node.right != null){
                s1.push(node.right);
            }
        }
        for(int i=s2.size(); i>0;i--){
            System.out.print(s2.pop()+"  ");
        }

    }


    public static void remove(int data){
        if(root.data == data){
            root = null;
            return;
        }
        remove(root, data);
    }


    static boolean flag = false;
    /**
     * 删除节点
     * 1. 判断当前节点的左子节点是否符合条件，如果符合curNode.left = null
     * 2. 判断当前节点的右子节点是否符合条件，如果符合curNode.right = null
     * 3. 如果12都没有删除，令curNode = curNode.left,继续12
     * 4. 如果3没有删除，令curNode = curNode.right,继续12
     * */
    public static void remove(Node node, int data){
       if(node.left!=null && node.left.data == data){
           node.left = null;
           return;
       }
       if(node.right!=null&& node.right.data == data){
           node.right = null;
           return;
       }

       if(node.left !=null){
           remove(node.left, data);
       }

       if(node.right != null){
           remove(node.right, data);
       }

    }

    public static void create(){
        root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        node5.left = node8;
    }



    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }


}
