package com.leetcode.muke;
/**
 * 反转一个链表
 * @author weifeng
 *
 */

//反转一个链表

class Solution_ReverseList0506{
	class Node{
		int val;
		Node next;
		public Node(int val){
			this.val = val;
		}
	}
	public void reverseList(Node head){
		Node pre = null;
		Node cur = head;
		while(cur != null){
			Node next = cur.next;
			
			cur.next = pre;
			pre = cur;
			cur = next;
		}
	}
}

//反转一个链表，实现
class Node{
	int val;
	Node next;
	public Node(int val){
		this.val = val;
	}
}
class Solution_InverseList{
	public void inverseList(Node root){
		
	}
}

public class M13_ReverseLinkedList {
	public class ListNode{
		public int val;
		public ListNode next;
		public ListNode(int x){val = x;}
		//根据N个元素的数组arr创建一个链表
		public ListNode(int[] arr){
			if(arr == null || arr.length ==0){
				throw new IllegalArgumentException("no result");
			}
			this.val = arr[0];
			ListNode curNode = this;
			for(int i = 1;i<arr.length;i++){
				curNode.next = new ListNode(arr[i]);
				curNode = curNode.next;
			}
		}
	    // 返回以当前ListNode为头结点的链表信息字符串
	    @Override
	    public String toString(){

	        StringBuilder s = new StringBuilder("");
	        ListNode curNode = this;
	        while(curNode != null){
	            s.append(Integer.toString(curNode.val));
	            s.append(" -> ");
	            curNode = curNode.next;
	        }
	        s.append("NULL");
	        return s.toString();
	    }
	}
	//反转一个链表
	public ListNode reverseList(ListNode head){
		ListNode pre = null;
		ListNode cur = head;
		while(cur != null){
			ListNode next = cur.next;
			
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}
	public static void main(String[] args){
		int[] nums = {1,2,3,4,5};
		M13_ReverseLinkedList solution = new M13_ReverseLinkedList();
		ListNode head = solution.new ListNode(nums);
		System.out.println(head);
		ListNode head2 = solution.reverseList(head);
		System.out.println(head2);
		
	}
}
