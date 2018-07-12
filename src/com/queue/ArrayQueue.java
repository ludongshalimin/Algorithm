package com.queue;
//利用链表实现队列
//队列的思想是：先进先出
//记住这种队列的实现
class Node<E>{
	Node<E> next = null;
	E data;
	public Node(E data){
		this.data = data;
	}
}
//先进先出，使用两个指针来表示，否则，在pop的时候,需要遍历，增加程序的复杂度
public class ArrayQueue<E> {
	private Node<E> head = null;
	private Node<E> tail = null;
	
	public boolean isEmpty(){
		return head == tail;
	}
	public void put(E data){  //入队的时候，尾巴增长
		Node<E> newNode = new Node<E> (data);
		if(head == null && tail == null){ //队列为空
			head = tail = newNode;
		}else{
			tail.next = newNode;
			tail = newNode;
		}
	}
	public E pop(){       //出队的时候，从头开始出队
		if(this.isEmpty()){
			return null;
		}
		E data = head.data;
		head = head.next;
		return data;
	}
	public int size(){
		Node<E> tmp = head;
		int n = 0;
		while(tmp != null){
			n++;
			tmp = tmp.next;
		}
		return n;
	}
	
	public static void main(String[] args){
		ArrayQueue<Integer> q = new ArrayQueue<Integer>();
		q.put(1);
		q.put(2);
		q.put(3);
		System.out.println("队列的长度" + q.size());
		System.out.println("队列首元素" + q.pop());
		System.out.println("队列的长度" + q.size());
		System.out.println("队列的首元素" + q.pop());
	}	
}
 