package com.leetcode.muke;

/**
 * 给定链表中的节点，删除该节点
 * @author weifeng
 * 
 * 正常情况下删除节点需要知道节点的前驱节点
 * 但是这个节点没有前驱节点，怎么办
 * 给的节点不能是尾节点，因为如果给了尾节点无法知道尾节点的前驱节点是谁
 *
 */
public class M16_DeleteNodeInLinkedList {
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
	public void deleteNode(ListNode node){
		//这个方法对于尾节点不能用
		if(node == null || node.next == null){
			throw new IllegalArgumentException("no res");
		}
		node.val = node.next.val;
		node.next = node.next.next;
	}
	public static void main(String[] args){
		int[] arr = {1,2,3,4};
		M16_DeleteNodeInLinkedList solution = new M16_DeleteNodeInLinkedList();
		ListNode head = solution.new ListNode(arr);
		System.out.println(head);
		solution.deleteNode(head);
		System.out.println(head);
	}
	
	
	
}
