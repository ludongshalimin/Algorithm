package com.leetcode.muke;

/**
 * 给定一个链表，删除倒数第n个节点
如：1->2->3->4->5->NULL, n = 2
返回：1->2->3->5

n从0计还是从1计
n不合法，负数或者大于链表长度如何处理（保证n合法）

 * @author weifeng
 *双指针技术，齐头并进
 */
public class M17_RemoveNthFromEnd {
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
	//注意，这里是删除，不是找到这个节点算完事。删除的话，就要考虑是否是头结点
	public ListNode removeNthFromEnd(ListNode head,int n){
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode p = dummyHead;
		ListNode q = dummyHead;
		for(int i=0;i<n+1;i++){
			q = q.next;
		}
		while(q!=null){
			p = p.next;
			q = q.next;
		}
		p.next = p.next.next;
		return dummyHead.next;
	}
	public static void main(String[] args){
		int arr[] = {1,2,3,4,5};
		M17_RemoveNthFromEnd solution = new M17_RemoveNthFromEnd();
		ListNode head = solution.new ListNode(arr);
		System.out.println(head);
		head = solution.removeNthFromEnd(head,2);
		System.out.println(head);
	}
	
}
