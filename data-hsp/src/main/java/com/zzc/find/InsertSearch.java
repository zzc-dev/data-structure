package com.zzc.find;

/**
 * 插值查找
 *  mid = left + (findVal - arr[left]) / (arr[right] - arr[left]) *  (right - left)
 * */
public class InsertSearch {
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
            mid = left + (num - arr[left])  * (right - left) / (arr[right] - arr[left]) ;
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
