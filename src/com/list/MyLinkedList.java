package com.list;

//实现一个链表
class Node <E>{
	Node next = null;
	E data ;
	public Node(E data){
		this.data = data;
	}
}
//一个.java文件中只有一个public class文件
public class MyLinkedList <E> {
	Node head = null;    //整个链表的头结点
	public void addNode(E d){
		Node newNode = new Node(d);
		if(head == null){
			head = newNode;
			return;
		}
		//遍历链表的头结点
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = newNode;
	}
	//删除链表的第几个节点
	public boolean deleteNode(int index){
		if(index < 1 || index >length()){
			return false;
		}
		//删除链表第一个元素
		if(index == 1){
			head = head.next;
			return true;
		}
		int i = 2;
		Node preNode = head;
		Node curNode = preNode.next;
		while(curNode != null){
			if(i == index){
				preNode.next = curNode.next;   //删除节点
				return true;
			}
			preNode = curNode;               //遍历节点
			curNode = curNode.next;
			i++;
		}
		return true;			
	}
	public int length(){
		int length = 0;
		Node temp = head;
		while( temp != null){
			length++;
			temp = temp.next;
		}
		return length;
	}
	public void printList(){
		Node<E> temp = head;
		while( temp != null){
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	public static void  main(String[] args){
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.addNode(1);
		list.addNode(2);
		list.addNode(3);
		list.printList();
		
	}
}
