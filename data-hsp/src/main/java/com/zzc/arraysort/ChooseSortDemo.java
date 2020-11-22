package com.zzc.arraysort;

import java.util.Arrays;

/**
 * 选择排序
 *   每次从数组中找出最大的位置，然后放到最后的位置
 *
 * */
public class ChooseSortDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,1,8,3};
        ChooseSort chooseSort = new ChooseSort(arr);
        chooseSort.print();
        System.out.println();
        chooseSort.sort();
    }
}

class ChooseSort{
    private int maxSize;
    private int[] arr;

    public ChooseSort(int[] arr){
        this.maxSize = arr.length;
        this.arr = arr;
    }

    public void sort(){
        int maxIndex;
        int temp;
        for(int i=0; i<arr.length-1; i++){
            maxIndex = 0;
            for(int j=1; j<arr.length-i; j++){
                if(arr[maxIndex]<arr[j]){
                    maxIndex = j;
                }
            }
            if(maxIndex != arr.length-i-1){
                temp = arr[maxIndex];
                arr[maxIndex] = arr[arr.length-i-1];
                arr[arr.length-i-1] = temp;
            }
            print();
        }
    }

    public void print(){
        System.out.println(Arrays.toString(arr));
    }
}
