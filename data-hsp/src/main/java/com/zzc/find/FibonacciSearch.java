package com.zzc.find;

import com.zzc.stack.ArrayStack;

import java.util.Arrays;

/**
 * 斐波那契查找
 *  使用斐波那契數列中的值來管理索引
 *  f(k) = f(k-1) + f(k-2) => f(k)-1 = f(k-1)-1 + f(k-2)-1 + 1
 *                            arr.length = mid左边 + mid右边 + mid
 *       mid = left + f(k-1)-1
 * */
public class FibonacciSearch {

    static int[] arr = new int[]{2, 4, 6, 8 ,10, 11,25,26};

    static int maxSize = 10;

    public static void main(String[] args) {
        System.out.println(search(5));
    }

    public static int[] f(){
        int[] f=new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i=2;i<maxSize; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    private static int search(int findVal) {
        int[] f = f();
        int k=0; // 数列的分割值的下标
        int high = arr.length - 1;
        int low = 0;

        while (arr.length > f[k]-1){
            k++;
        }

        int[] temp =  Arrays.copyOf(arr, f[k]-1);
        for(int i=arr.length; i<temp.length; i++){
            temp[i] = arr[high];
        }

        int mid;
        while (high>=low){
            mid = low + f[k-1] -1;
            if(temp[mid]>findVal){
                k--;
                high = mid - 1;
            }else if(temp[mid]>findVal){
                k -= 2;
                low = mid+1;
            }else {
                return  mid>(arr.length-1) ? arr.length : mid;
            }
        }

        return -1;

    }

}
