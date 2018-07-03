package com.queue;

import java.util.LinkedList;

//链表实现队列，线程安全
public class LinkedQueue<E> {
	private LinkedList<E> list = new LinkedList<E>();
	private int size = 0;
	public synchronized void put(E e){
		list.addLast(e);
		size ++;
	}
	public synchronized E pop(){
		size --;
		return list.removeFirst();
	}
	public synchronized boolean isEmpty(){
		return size == 0;
	}
	public synchronized int size(){
		return size;
	}
	public static void mai(String[] args){
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		q.put(1);
		q.put(2);
		q.put(3);
		System.out.println("队列的长度" + q.size());
		System.out.println("队列首元素" + q.pop());
		System.out.println("队列的长度" + q.size());
		System.out.println("队列的首元素" + q.pop());
	}
}
