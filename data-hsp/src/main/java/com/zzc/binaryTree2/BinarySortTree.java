package com.zzc.binaryTree2;

/**
 *  二叉排序树
 * @author zzc
 * @since 2020-12-01
 */
public class BinarySortTree {
    static Node root;

    public static void main(String[] args) {
        int[] str = new int[] {5, 4, 3,8,7,9};
        createdBST(str);
        System.out.println("中序遍历：");
        midPrint(root);
        remove(5);
        System.out.println("中序遍历：");
        midPrint(root);
    }

    private static void createdBST(int[] str) {
        if(str == null || str.length == 0){
            return;
        }

        for (int d : str) {
            Node node = new Node(d);
            if(root == null){
                root = node;
            }else {
               Node tmp = root;
               while (true){
                   if(d < tmp.data){
                       if(tmp.left == null){
                           tmp.left = node;
                           break;
                       }else {
                           tmp = tmp.left;
                       }
                   }else {
                       if(tmp.right == null){
                           tmp.right = node;
                           break;
                       }else {
                           tmp = tmp.right;
                       }
                   }
               }

            }
        }
    }

    private static void remove(int val){
        if(root == null){
            return;
        }
        //1. 找到该节点和其父节点
        Node[] nodes = findNodeAndParentNode(val);
        if(nodes == null){
            return;
        }
        Node curNode = nodes[0];
        Node parentNode = nodes[1];

        //2.删除节点
        //2.1 该节点的父节点为null,删除根节点
        if(parentNode == null){
            removeRoot();
        }else if(curNode.left == null && curNode.right == null){ // 2.2 删除叶子节点
            if(parentNode.left != null && parentNode.left.data == curNode.data){
                parentNode.left = null;
            }else {
                parentNode.right = null;
            }
        }
        //2.3 删除非叶子节点：该节点有一个子节点，该节点有两个节点

    }

    private static void removeRoot(){
        if(root.left == null && root.right == null){
            root = null;
        }else if(root.right != null){ // 找到右子树最小的
            Node temp = root.right;
            Node tempParent = root;
            while (temp.left != null){
                tempParent = temp;
                temp = temp.left;
            }
            if(tempParent == root){
                root.right = temp.right;
            }else {
                tempParent.left = null;
            }
            root.data = temp.data;
        }else {  //找到左子树最大的
            Node temp = root.left;
            Node tempParent = root;
            while (temp.right != null){
                tempParent = temp;
                temp = temp.right;
            }
            if(tempParent == root){
                root.left = temp.left;
            }else {
                tempParent.right = null;
            }
            root.data = temp.data;
        }
    }

    /**
     * 找到该节点和其父节点
     * */
    private static Node[] findNodeAndParentNode(int val){
        Node tmp = root;
        Node parent = null;
        while (true){
            if(tmp == null){
                System.out.println("没有找到该节点");
                return null;
            }

            if(tmp.data == val){
                break;
            }
            parent = tmp;
            if(val < tmp.data){
                tmp = tmp.left;
            }else {
                tmp = tmp.right;
            }
        }
        return new Node[]{tmp, parent};
    }

    private static void midPrint(Node node){
        if(node == null){
            return;
        }
        if(node.left !=null){
            midPrint(node.left);
        }
        System.out.print(node.data+"  ");
        if(node.right != null){
            midPrint(node.right);
        }
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


