package com.zzc.algorithm;

/**
 * 汉诺塔问题：分治算法
 * @author zzc
 * @since 2020-12-03
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5, 'A','B','C');
    }

    public static void hanoiTower(int num,char a, char b, char c){
        if(num == 1){
            System.out.println("n=1:"+a +"->"+c);
            return;
        }
        hanoiTower(num-1, a, c, b);
        System.out.println("最下面："+a+"->"+c);
        hanoiTower(num-1, b, a, c);
    }
}
