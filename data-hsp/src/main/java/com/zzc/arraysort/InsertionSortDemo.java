package com.zzc.arraysort;

import java.util.Arrays;

public class InsertionSortDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,1,8,3};
        InsertionSort insertionSort = new InsertionSort(arr);
        insertionSort.print();
        System.out.println();
        insertionSort.sort();
    }
}

class InsertionSort{
    private int maxSize;
    private int[] arr;

    public InsertionSort(int[] arr){
        this.maxSize = arr.length;
        this.arr = arr;
    }

    public void sort(){
        int temp;
        int insertIndex;
        for(int i=1;i<maxSize;i++){
            temp = arr[i];
            insertIndex = i-1;
            while (insertIndex >=0  && (temp <= arr[insertIndex])){
                arr[insertIndex+1] = arr[insertIndex];  // 后移一位
                insertIndex--;
            }

            arr[insertIndex+1] = temp;
            print();
        }
    }

    public void print(){
        System.out.println(Arrays.toString(arr));
    }
}
