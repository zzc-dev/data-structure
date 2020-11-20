package com.zzc.recursion;

/**
 *
 *  迷宫问题
 * @author zzc
 * @since 2020-11-18
 */
public class MazeDemo {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.go(1,1);
//        maze.print();
    }
}

/**
 * 规定：1代表墙 0：还未走过 2：此路可通 3：此路不通（回溯）
 * 必须自定义走路策略
 * */
class Maze{
    private int[][] arr = new int[6][6];

    public Maze(){
        for(int i =0; i< arr.length; i++){
            arr[0][i] = 1;
            arr[arr.length -1][i] = 1;
            arr[i][arr.length -1] = 1;
            arr[i][0] = 1;
        }
        arr[2][1] = 1;
        arr[3][1] = 1;
    }

    // 策略  → ↓← ↑
    public boolean go(int i, int j){
        if(i ==  4 && j==4 ){
            print();
            return true;
        }
        if(arr[i][j] == 0){
            arr[i][j] = 2;
            if(go(i, j+1)){
                return true;
            }else if(go(i+1, j)){
                return true;
            }else if(go(i,j-1)){
                return true;
            }else if(go(i-1, j)){
                return true;
            }else {
                arr[i][j] = 3;
                return false;
            }
        }else {
            return false;
        }
    }


    public void print(){
        for(int i=0;i<arr.length; i++){
            for(int j=0;j<arr.length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
