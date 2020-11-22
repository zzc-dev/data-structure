package com.zzc.arraysort;

import java.util.Arrays;

/**
 * 冒泡排序算法（顺序排序）
 * <p>
 * 比较数组的第一个和第二个元素，将较大的数往后移，直到比较数组的最后一个元素
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort(5);
        bubbleSort.sort();

    }
}

class BubbleSort {
    public int[] arr;
    private int maxSize;

    public BubbleSort(int maxSize) {
        this.maxSize = maxSize;
//        arr = new int[maxSize];
//        for (int i = 0; i < maxSize; i++) {
//            arr[i] = (int) (Math.random() * 10) + 2;
//        }
        arr = new int[]{3, 4, 9, 10, 7};
        print();
    }

    public void sort() {
        int temp;
        boolean flag = false;
        for (int i = 0; i < maxSize-1; i++) {
            for (int j = 0; j < maxSize - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            print();
            // 如果本次排序没有发生交换，则该数组已经是顺序的
            if(!flag){
                return;
            }else {
                flag = false;
            }
        }
    }

    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}
