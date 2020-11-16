package com.zzc.stack;


import java.util.Arrays;
import java.util.Stack;

public class ArrayStack {
    private int maxSize;
    private int[] elements;
    public int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        elements = new int[maxSize];
    }


    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("full");
            return;
        }
        elements[++top] = data;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty");
        }

        int value = elements[top];
        elements[top] = 0;
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("empty");
        }
        return elements[top];
    }

    public void print(){
        System.out.println(Arrays.toString(elements));
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);

    }
}
