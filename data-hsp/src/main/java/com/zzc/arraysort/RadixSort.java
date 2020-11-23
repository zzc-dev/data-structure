package com.zzc.arraysort;

import java.util.Arrays;

/**
 * 基数排序
 *    特殊的桶排序
 *    使用空间换时间
 *    按位数排序
 *      先按个位数排序（将个位数相同的放入同一个桶【0-9】中），依次放入数组中
 *      再按十位数排序，直到最高位排序完成
 * */
public class RadixSort {
    private static int[] arr;
    private static int maxSize;

    public static void main(String[] args) {
        arr = new int[]{53, 3, 542, 748, 14, 214};
        maxSize = 6;
        sort();
    }

    public static void sort(){
        int maxLength = getMaxBitLength();
        int[][] buckets = new int[10][maxSize];
        int[] bucketTotal = new int[10];

        int bit = 1;
        for(int i=0; i<maxLength; i++){
            bit *= 10;
            int bucketIndex;
            for(int j=0; j<maxSize; j++){
                bucketIndex = arr[j] / (bit/10) % 10;
                buckets[bucketIndex][bucketTotal[bucketIndex]] = arr[j];
                bucketTotal[bucketIndex]++;
            }

            bucketIndex = 0;
            for(int j=0; j<bucketTotal.length; j++){
                if(bucketTotal[j] != 0){
                    for(int k=0; k<bucketTotal[j]; k++){
                        arr[bucketIndex++] = buckets[j][k];
                    }
                }
                bucketTotal[j] = 0;
            }
            System.out.println(Arrays.toString(arr));
        }





    }

    public static int getMaxBitLength(){
        int max = arr[0];
        for(int i=1;i<arr.length;i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return (max+"").length();
    }

}
