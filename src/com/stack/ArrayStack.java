package com.stack;

class Node<E> {
	Node<E> next = null;
	E data;
	public Node(E data){
		this.data = data;
	}
}
//使用链表实现栈，需要使用一个指针就可以了
public class ArrayStack<E> {
	Node<E> top = null;
	public boolean isEmpty(){
		return top == null;
	}
	//入栈
	public void push(E data){
		Node<E> newNode = new Node<E>(data);
		newNode.next = top;  //对于栈来说，后入先出LIFO
		top = newNode;
	}
	//出栈
	public E pop(){
		if(this.isEmpty()){
			return null;
		}
		E data = top.data;
		top = top.next;
		return data;
	}
	//返回最节点上的值
	public E peek(){
		if(isEmpty()){
			return null;
		}
		return top.data;
	}
	public static void main(String[] args){
		ArrayStack<Integer> st = new ArrayStack<Integer>();
		st.push(1);
		st.push(2);
		st.push(3);
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.pop());
	}
}
