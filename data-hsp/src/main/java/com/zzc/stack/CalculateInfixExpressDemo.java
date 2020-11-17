package com.zzc.stack;

import java.util.Stack;

/**
 *  中缀表达式 计算
 * */
public class CalculateInfixExpressDemo {
    public static void main(String[] args) {

        String express = "1+6/3*4-1+4/2";   //  10
        Stack<Integer> cal = new Stack<Integer>();
        Stack<String> oper = new Stack<String>();

        int index = 0;
        while (index < express.length()) {
            char c = express.substring(index, index + 1).charAt(0);
            if (!isChar(c)) {
                cal.push(c - 48);
            } else {
                if (oper.isEmpty()) {
                    oper.push(c + "");
                } else if (highPri(oper.peek(), c)) {
                    oper.push(c + "");
                } else {
                    int num2 = cal.pop();
                    int num1 = cal.pop();
                    char ch = oper.pop().charAt(0);
                    int result = cal(ch, num1, num2);
                    System.out.println(result);
                    cal.push(result);
                    continue;

                }
            }
            index++;
        }
        int size = oper.size();
        for (int i = 0; i < size; i++) {
            String op = oper.pop();
            int num2 = cal.pop();
            int num1 = cal.pop();
            char ch = op.charAt(0);
            int result = cal(ch, num1, num2);
            System.out.println(result);
            cal.push(result);
        }
        System.out.println(cal.peek());

    }

    public static boolean isChar(char ch) {
        switch (ch) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    public static int cal(char ch, int num1, int num2) {
        switch (ch) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return -1;
        }
    }

    // b的优先级比a高
    public static boolean highPri(String a, char b) {
        if (pri(b) - pri(a.charAt(0)) > 0) {
            return true;
        }
        return false;
    }

    public static int pri(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                return -1;
        }
    }
}
