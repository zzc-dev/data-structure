package com.zzc.stack;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  后缀表达式
 * @author zzc
 * @since 2020-11-17
 */
public class SuffixExpressDemo {

    public static void main(String[] args) {
        //(3+4)×5-6=29  [3, 4, +, 5, *, 6, -]
        // 1+((2+3)×4)-5=16 [1, 2, 3, +, 4, *, +, 5, -]

        List<String> list = inFixToSuffix("1+((2+3)*4)-5");
        compute(list);
    }

    // 中缀转后缀
    public static List<String> inFixToSuffix(String inFix){
        Stack<String> result = new Stack<>();
        Stack<String> opeStack = new Stack<>();
        int index = 0;
        while (index < inFix.length()){
            String ch = inFix.substring(index,index+1);
            // 如果是数字 将其压
            if(ch.matches("\\d+")){
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(inFix.substring(index));
                if(matcher.find()){
                    index += matcher.group(0).length() - 1;
                    result.push(matcher.group(0));
                }
            //  如果是左括号“(”，则直接压入s1
            //  如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
            //  若优先级比栈顶运算符的高，也将运算符压入s1
            }else if("(".equals(ch) || opeStack.isEmpty() || highPri(opeStack.peek(), ch.charAt(0))) {
                opeStack.push(ch);
            }else if(")".equals(ch)) {  // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                String v;
                while (!(v = opeStack.pop()).equals("(")) {
                    result.push(v);
                }
                // 将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较
            } else if(!highPri(opeStack.peek(), ch.charAt(0))){
                result.push(opeStack.pop());
                continue;
            }
            index++;
        }
        for(int i=0;i<opeStack.size();i++){
            result.push(opeStack.pop());
        }
        List<String> list = new ArrayList<>();
        for(int i=result.size();i>0;i--){
            list.add(result.pop());
        }
        Collections.reverse(list);
        System.out.println(list);
        return list;
    }

    public static void compute(List<String> list){
        Stack<Integer> result = new Stack<>();
        list.forEach(val ->{
            if(val.matches("\\d+")){
                result.push(Integer.valueOf(val));
            }else {
                int num2 = result.pop();
                int num1 = result.pop();
                int temp = cal(val, num1, num2);
                result.push(temp);
            }
        });
        System.out.println(result.peek());
    }

    public static int cal(String ch, int num1, int num2) {
        switch (ch) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
               throw new RuntimeException("不存在的运算符："+ ch);
        }
    }

    // b的优先级比a高
    public static boolean highPri(String a, char b) {
        if("(".equals(a)){
            return true;
        }
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
