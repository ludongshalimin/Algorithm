package com.leetcode.muke;

/**
 * 给定一个链表，对于每两个相邻的节点，交换其位置
 * 如：链表为 1->2->3->4->NULL
返回：2->1->4->3->NULL
只能对节点进行操作，不能修改节点的值
 * @author weifeng
 *
 */
class Solution_swapPairNode{
	class Node{
		int val;
		Node next;
		public Node(int val){
			this.val = val;
		}
	}
	public Node swapPairNode(Node head){
		Node dummyHead = new Node(0);
		dummyHead.next = head;
		Node cur = dummyHead;
		while(cur.next!=null && cur.next.next != null){
			Node node1 = cur.next;
			Node node2 = cur.next.next;
			
			node1.next = node2.next;
			node2.next = node1;
			cur.next = node2;
			cur = node1;			
		}
		return dummyHead.next;
	}
}
class Solution_SwapNode{
	public class Node{
		int val;
		Node next;
		Node(int val){
			this.val = val;
		}
		public Node(int[] arr){
			if(arr == null || arr.length ==0){
				throw new IllegalArgumentException("no result");
			}
			this.val = arr[0];
			Node curNode = this;
			for(int i = 1;i<arr.length;i++){
				curNode.next = new Node(arr[i]);
				curNode = curNode.next;
			}
		}
	    @Override
	    public String toString(){

	        StringBuilder s = new StringBuilder("");
	        Node curNode = this;
	        while(curNode != null){
	            s.append(Integer.toString(curNode.val));
	            s.append(" -> ");
	            curNode = curNode.next;
	        }
	        s.append("NULL");
	        return s.toString();
	    }
	}
	public Node swapNode(Node head){
		Node dummyNode = new Node(0);
		dummyNode.next = head;
		
		Node cur = dummyNode;
		while(cur.next != null && cur.next.next != null){
			Node node1 = cur.next;
			Node node2 = cur.next.next;
			
//			node1.next = node2.next;  //这个位置也不能换
//			node2.next = node1;
//			cur.next = node2;   //这个位置不能换，当前的cur还是前一个cur
//			cur = node1;	
			
			cur.next = node2;
			node1.next = node2.next;
			node2.next = node1;
			cur = node1;
			
		}
		return dummyNode.next;
	}
}
public class M15_SwapNodeInPairs {
	public class ListNode{
		public int val;
		public ListNode next;
		public ListNode(int x){val = x;}
		//后面追加的ListNode的相关信息，是为了方便进行测试
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
	
	public ListNode swapPairs(ListNode head){
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode p = dummyHead;
		while(p.next!=null && p.next.next !=null){   //需要保证要交换的node1,node2不为空
			ListNode node1 = p.next;
			ListNode node2 = node1.next;
			ListNode next = node2.next;
			node2.next = node1;
			node1.next = next;
			p.next = node2;
			p = node1;
		}
		return dummyHead.next;
	}
	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6,7,8,9};
//		M15_SwapNodeInPairs solution = new M15_SwapNodeInPairs();
//		ListNode head = solution.new ListNode(arr);
//		System.out.println(head);
//		head = solution.swapPairs(head);
//		System.out.println(head);
		
		System.out.println("hahah");
		Solution_SwapNode ss = new Solution_SwapNode();
		
		com.leetcode.muke.Solution_SwapNode.Node head = ss.new Node(arr);
		System.out.println(head);
		head = ss.swapNode(head);
		System.out.println(head);
	}
	
	
	
	
}
