package com.zzc.queue;

import java.util.Arrays;

public class CircleQueueDemo {

    public static void main(String[] args) throws Exception {
        CircleQueue queue = new CircleQueue(4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove();
        queue.add(4);
        queue.remove();
        queue.add(5);
        queue.remove();
        queue.remove();
        queue.remove();
        queue.printAll();
    }
}


/**
 * 使用数组实现循环队列
 *
 * head 头元素
 * tail 并不是尾元素，而是尾元素的后一个元素
 *      因此数组能存储的最大容量是maxSize - 1
 *
 * */
class CircleQueue{
    private int tail;
    private int head;
    private transient int[] elements;
    private int maxSize;

    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        this.elements = new int[maxSize];
    }

    public boolean isFull(){
        return ((tail + 1) % maxSize) == head;
    }

    public boolean isEmpty(){
        return tail == head;
    }

    public int size(){
        return (tail + maxSize - head) % maxSize;
    }

    public boolean add(int value){
        if(isFull()){
            System.out.println(value + "man");
            return false;
        }
        elements[tail] = value;
        tail = (++tail) % maxSize;
        return true;
    }

    public int remove() throws Exception {
        if(isEmpty()){
            throw new Exception("empty");
        }
        int value = elements[head];
        elements[head] = 0;
        head = ++head % maxSize;
        return value;
    }

    public void printAll(){
        System.out.println(String.format("head=%s;tail=%s;size=%s",head,tail,size()));
        System.out.println(Arrays.toString(elements));
    }

}