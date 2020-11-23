package com.zzc.arraysort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    private static int[] arr;
    private static int maxSize;

    public static void main(String[] args) {
        arr = new int[]{8, 4, 5, 7, 1, 3, 6, 2};
        sort(0, arr.length - 1, new int[arr.length]);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 先分后合
     */
    public static void sort(int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 左分
            sort(left, mid, temp);
            // 右分
            sort(mid + 1, right, temp);
            merge(left, mid, right, temp); // 0-1最先到，
        }
    }

    public static void merge(int left, int mid, int right, int[] temp) {
        System.out.println(left + "-" + right);
        // 1. 在左右两个分片中依次找到最小的入temp
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[index++] = arr[i];
                i++;
            } else {
                temp[index++] = arr[j];
                j++;
            }
        }

        // 2.将比较后剩余的数放入temp中
        while (i <= mid) {
            temp[index++] = arr[i++];
        }

        while (j <= right){
            temp[index++] = arr[j++];
        }

        // 3.将temp拷贝到arr中
        for(int k=0; k<index; k++){
            arr[left++] = temp[k];
        }


    }
}
