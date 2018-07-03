package com.list;
//反转一个链表
public class InverseList {
	class Node{
		int val;
		Node next;
		public Node(int val){
			this.val =  val;
			this.next = null;
		}
	}
	public Node inverse(Node head){
		Node pre = null;
		Node cur = head;
		while(cur != null){
			Node next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}
	public static void main(String[] args){
		InverseList ll = new InverseList();
		Node node1 = ll.new Node(1);
		Node node2 = ll. new Node(2);
		Node node3 = ll.new Node(3);
		Node node4 = ll.new Node(4);
		Node node5 = ll.new Node(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		Node cur = node1;
		while(cur !=null){
			System.out.println(cur.val);
			cur = cur.next;
		}
		System.out.println("sasa");
		cur = ll.inverse(node1);
		while(cur != null){
			System.out.println(cur.val);
			cur = cur.next;
		}
	}
}
