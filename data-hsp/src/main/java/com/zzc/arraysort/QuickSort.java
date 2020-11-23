package com.zzc.arraysort;

import java.util.Arrays;

/**
 * 快速排序
 *  1．先从数列中取出一个数作为基准数。
 *  2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 *  3．再对左右区间重复第二步，直到各区间只有一个数。
 *
 *  填数+分治法
 *
 * */
public class QuickSort {
    private static int[] arr;
    private static int maxSize;

    public static void main(String[] args) {
//        arr = new int[]{4, 2, 1, 8, 5, 3, 21, 9};
        arr =new int[8];
        for (int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*8) +1;
        }
        System.out.println(Arrays.toString(arr));
        sort(0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int left, int right){
        int temp = arr[left];  // A.将left挖坑
        int i = left;
        int j = right;

        while (i != j){
            // 1.从右边开始找到比temp小的数
            // B. arr[j] 填上 arr[i]的坑， 挖arr[j]坑
            while (i != j){
                if(arr[j] < temp){
                    arr[i] = arr[j];
                    break;
                }
                j--;
            }

            // 2.从左边开始找到比temp大的数
            // C. arr[i] 填上 arr[j]的坑， 挖arr[i]坑
            while (i != j){
                if(arr[i] > temp){
                    arr[j] = arr[i];
                    break;
                }
                i++;
            }
        }
        // D. B和C互相填坑，最后还是有一个坑剩余，此时用temp填上
         arr[i] = temp;
        if((i-1) > left){
            sort(left, i-1);
        }

        if((i+1)<right){
            sort(i+1, right);
        }

    }
}
