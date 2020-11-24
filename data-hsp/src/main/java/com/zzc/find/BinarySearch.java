package com.zzc.find;

/**
 * 二分查找算法：必須有序
 * */
public class BinarySearch {
    static int[] arr = new int[]{1,2,3,4,5,8};

    public static void main(String[] args) {
        search(5);
    }

    private static void search(int num) {

        int left = 0;
        int right = arr.length - 1;
        int mid;
        if(num == arr[0]){
            System.out.println(0);
            return;
        }else if(num == arr[arr.length -1 ]){
            System.out.println(arr.length-1);
            return;
        }else if(num>arr[arr.length-1] || num<arr[0]){
            System.out.println(-1);
            return;
        }
        while (left != right){
            mid = (left + right) / 2;
            if(arr[mid] == num){
                System.out.println(mid);
                return;
            }
            if(arr[mid] > num){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        System.out.println(-1);
    }
}
