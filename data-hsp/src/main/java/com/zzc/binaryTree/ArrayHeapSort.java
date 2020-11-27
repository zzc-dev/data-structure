package com.zzc.binaryTree;

import java.util.Arrays;

/**
 * @author zzc
 * @since 2020-11-27
 * 堆排序
 *   1.先将数组整理为大顶堆
 *   2.将数组末尾和顶部交换，弹出末尾元素（末尾元素不在参与后续过程）
 *   3.重复1操作
 * 大顶堆：
 *   父节点的权值 >= 左右子节点
 *   左右节点的值不做比较
 */
public class ArrayHeapSort{

    static int[] arr = new int[]{1,2,3,4,5,6,7,8};

    public static void main(String[] args) {
        // 一、先将数组整理为大顶堆
        // n的父级索引 = (n-1)/2
        int lastNoLeafIndex = ((arr.length-1) - 1) / 2;
        for(; lastNoLeafIndex >= 0; lastNoLeafIndex--){
            createHeap(lastNoLeafIndex, arr.length);
        }
        System.out.println(Arrays.toString(arr));
        // 二、 将数组末尾和顶部交换，弹出末尾元素（末尾元素不在参与后续过程）
        int len = arr.length;
        int temp;
        while (len > 1){
            temp = arr[0];
            arr[0] = arr[len-1];
            arr[len-1] = temp;
            len--;
            createHeap(0, len);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param i 非叶子节点的值
     * @param length 会越来越少，后续节点不参与过程
     * */
    static void createHeap(int i, int length){
        int temp = arr[i];

        for(int k = i * 2 + 1;k < length;  k+= i*2+1){
            // 得到左右节点的最大值
            if((k+1) < length && arr[k] < arr[k+1]){
                k = k+1;
            }
            if(arr[i] < arr[k]){
                arr[i] = arr[k];
                arr[k] = temp;
                createHeap(k, length);
            }
        }


    }
}
