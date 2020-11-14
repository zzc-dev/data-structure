package com.zzc.array;

/**
 * 稀疏数组
 **/
public class SparseArray {

    public static void main(String[] args) {
        int[][] originArr = originArr();
        int[][] sparseArr = produceSparseArr(originArr);
        returnOriginArr(sparseArr);

    }

    public static int[][] originArr(){
        int[][] originArr = new int[4][4];
        originArr[0][1] = 1;
        originArr[1][2] = -1;
        System.out.println("原始数组：");
        printArr(originArr);
        return originArr;
    }

    public static int[][] produceSparseArr(int[][] originArr){
        int count = 0;
        for(int i = 0; i < originArr.length; i++){
            for(int j = 0; j < originArr[i].length; j++){
               if(originArr[i][j] != 0){
                   count++;
               }
            }
        }
        int[][] sparseArr = new int[count+1][3];
        sparseArr[0][0] = originArr.length;
        sparseArr[0][1] = originArr[0].length;
        sparseArr[0][2] = count;

        int no = 1;

        for(int i = 0; i < originArr.length; i++){
            for(int j = 0; j < originArr[i].length; j++){
                if(originArr[i][j] != 0){
                    sparseArr[no][0] = i;
                    sparseArr[no][1] = j;
                    sparseArr[no][2] = originArr[i][j];
                    no++;
                }
            }
        }
        System.out.println("稀疏数组：");
        printArr(sparseArr);
        return sparseArr;
    }

    public static int[][] returnOriginArr(int[][] sparseArr){
        int[][] originArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1; i < sparseArr.length; i++){
            int row = sparseArr[i][0];
            int cell = sparseArr[i][1];
            int value = sparseArr[i][2];
            originArr[row][cell] = value;
        }
        System.out.println("还原数组");
        printArr(originArr);
        return originArr;
    }

    public static void printArr(int [][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
