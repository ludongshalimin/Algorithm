package com.sward.offer;
/**
 *  拷贝一个复杂的链表
 *  复制一个复杂链表。在复杂链表中，每个节点除了有一个next指针外，还有一个random指针指向链表中的任意一个节点
 *  或者null。
 * @author weifeng
 *	这里的复制一个链表指的是，链表的相对位置不能变，链表的指向也不能变
 */
public class M01_Copy_ComplicatedLinkedList {
	class Node{
		int val;
		Node next;
		Node random;
		public Node(int val){
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
	public Node getCopyList(Node head){  //得到一个复杂链表的复制，相对的位置不变
		if(head == null) return null;
		//复制链表
		Node cur = head;
		while(cur != null){
			Node temp = new Node(cur.val);
			temp.next = cur.next;
			cur.next = temp;
			cur  = temp.next;
		}
		//复制random
		cur = head;
		while(cur != null){
			if(cur.random != null){  //因为如果指针也可能指向为空
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		//拆分链表
		Node phead = head.next;
		Node p = phead;
		Node q = head;
		while(p.next != null){
			q.next = p.next;
			q = q.next;
			
			p.next = q.next;
			p=p.next;
		}
		q.next = null;
		return phead;
	}
	public static void main(String[] args){
		M01_Copy_ComplicatedLinkedList ss = new M01_Copy_ComplicatedLinkedList();
		Node node1 = ss.new Node(1);
		Node node2 = ss.new Node(2);
		Node node3 = ss. new Node(3);
		Node node4 = ss.new Node(4);
		Node node5 = ss.new Node(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node1.random = node3;
		node2.random = node4;
		
		Node phead = ss.getCopyList(node1);
		Node cur = phead;
		while(cur != null){
			System.out.println(cur.val);
			cur = cur.next;
		}
		System.out.println("random");
		System.out.println(phead.random.val);
		System.out.println(phead.next.random.val);
	}
}
