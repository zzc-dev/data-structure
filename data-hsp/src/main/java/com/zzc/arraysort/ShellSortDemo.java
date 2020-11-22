package com.zzc.arraysort;

import java.util.Arrays;

/**
 * 希尔排序
 *   是一种特殊的插入排序，它先将数据按步长分组，分组插入排序后，递减步长，知道分组数为1后在进行一次插入排序即可
 *   8*1000*10000 30545
 *   8*100*10000  2174
 *   8*10*10000   196
 *   80000        17
 *   8000         3
 * */
public class ShellSortDemo {
    public static void main(String[] args) {
//        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr =new int[8];
        for (int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*80000) +1;
        }
        ShellSort shellSort = new ShellSort(arr);
        long l = System.currentTimeMillis();
        shellSort.sort();
        System.out.println(System.currentTimeMillis() - l);
    }
}

class ShellSort{
    private int maxSize;
    private int[] arr;

    public ShellSort(int[] arr){
        this.maxSize = arr.length;
        this.arr = arr;
    }

    public void sort(){
        int temp;
        int insertIndex;
        int gap = maxSize / 2;
        while (gap>0){
            // arr分成gap组，每组之间使用插入排序
            for(int i=gap; i<maxSize; i++){
                temp = arr[i];
                insertIndex = i;
                while ((insertIndex-gap)>=0 && temp < arr[insertIndex-gap]){
                    arr[insertIndex] = arr[insertIndex-gap];
                    insertIndex -= gap;
                }

                if(insertIndex != i){
                    arr[insertIndex] = temp;
                }
            }
//            print();
            gap /= 2;
        }



    }

    public void print(){
        System.out.println(Arrays.toString(arr));
    }
}