package com.zzc.recursion;

import java.util.Arrays;

public class EightQueenDemo {
    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.begin(0);
        System.out.println(queen.count);
    }
}

class EightQueen{
    public int[] arr = new int[] {-1, -1, -1, -1, -1, -1, -1, -1};

    public int count;

    public void begin(int n){
        // n=8 代表已经全部摆放完成
        if(n == 8){
            count++;
            print();
            return;
        }

        // 每一层都需要把所有位置全摆放一遍，用于得到所有结果
        for(int i = 0; i<8; i++){
            arr[n] = i; // 当前行摆放的列
            if(judge(n)){
                begin(n+1);  // 如果不冲突，接着摆下一行， 直到最后一行输出结果
                                // 返回后，将当前行往后挪一列接着判断此行的所有可能结果
            }
            // 如果冲突，将当前行往后挪一列
        }

    }

    public void print(){
        System.out.println(Arrays.toString(arr));
    }

    public boolean judge(int n){
        for(int i=n-1; i>=0;i--){
            // 同一列
            if(arr[i] == arr[n]){
                return false;
            }
            // 同一对角线
            if(Math.abs(i-n) == Math.abs(arr[i] -arr[n])){
                return false;
            }
        }
        return true;
    }
}
