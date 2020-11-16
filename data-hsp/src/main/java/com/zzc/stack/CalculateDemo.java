package com.zzc.stack;

import java.util.Stack;

public class CalculateDemo {
    public static void main(String[] args) {

        String express = "1+6/3*4-1";
        Stack<Integer> cal = new Stack<Integer>();
        Stack<String> oper = new Stack<String>();

        int index = 0;
        while (true){
            char c = express.substring(index, index + 1).charAt(0);
            if()
            if (!isChar(c)){
                cal.push(c - 48);
            }else {
                if(oper.isEmpty()){
                    oper.push(c);
                }else if(highPri(oper.peek(), c)){

                }
            }

        }
    }

    public static boolean isChar(char ch){
        switch (ch){
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
                default:
                    return false;
        }
    }

    public static boolean highPri(char a, char b){
        if()

    }
}
