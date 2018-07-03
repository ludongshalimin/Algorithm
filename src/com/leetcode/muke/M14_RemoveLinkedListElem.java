package com.leetcode.muke;

/**
 * 在链表中删除值为val的所有节点
如 1->2->6->3->4->5->6->NULL，要求删除值为6的节点
返回 1->2->3->4->5->NULL
 * @author weifeng
 *遍历到待删除元素的前一个节点
 *cur:要删除节点的前一个节点
 *delNode = cur->next
 *cur->next = delNode->next
 *但是第一个元素没有前驱节点，需要做特殊处理
 */
class Solution_removelistElem{
	class Node{
		int val;
		Node next;
		public Node(int val){
			this.val = val;
		}
	}
	public void removeListElem(Node head,int val){
		Node dummyNode = new Node(0);
		dummyNode.next = head;
		Node cur = dummyNode;
		while(cur.next != null){
			if(cur.next.val == val){
				cur.next = cur.next.next;
			}else{
				cur = cur.next;
			}
		}
	}
}
public class M14_RemoveLinkedListElem {
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
	public ListNode removeEle(ListNode head,int val){
		//需要对头结点进行特殊处理
		while(head !=null && head.val == val){
			ListNode node = head;
			head = head.next;
		}
		if(head == null)return null;
		ListNode cur = head;
		while(cur.next != null){
			if(cur.next.val == val){
				ListNode delNode = cur.next;
				cur.next = delNode.next;
			}else{
				cur = cur.next;
			}
				
		}
		return head;
	}
	
	//使用虚拟头结点的方式进行
	public ListNode removeEleII(ListNode head,int val){
		//创建虚拟头结点
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode cur = dummyHead;  //cur从虚拟头节点开始，
		while(cur.next != null){   //cur代表的是要判断数值节点的前一个节点
			if(cur.next.val == val){
				ListNode delNode = cur.next;
				cur.next = delNode.next;
			}else{
				cur = cur.next;
			}
		}
		return dummyHead.next;   //最后返回链表的根节点
	}
	public static void main(String[] args){
		int[] arr = {1,1,6,3,4,6};
		int val = 1;
		M14_RemoveLinkedListElem solution = new M14_RemoveLinkedListElem();
		ListNode head = solution.new ListNode(arr);
		System.out.println(head);
		//head = solution.removeEle(head,val);
		head = solution.removeEleII(head,val);
		System.out.println(head);
	}
}
