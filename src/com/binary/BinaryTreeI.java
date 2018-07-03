package com.binary;
/**
 * 已经知道二叉树的先序遍历和中序遍历，如何求后续遍历
 * 先序遍历：根左右
 * 中序遍历：左根右
 * 后序遍历：左右根
 * 层序遍历
 * @author weifeng
 *
 */
class Node{
	int data;
	Node left;
	Node right;
	public Node(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
public class BinaryTreeI {
	private Node root;
	public BinaryTreeI(){
		root = null;
	}
	//后序遍历
	public void postOrder(){
		this.postOrder(root);
	}
	//后序遍历方法递归实现
	private void postOrder(Node localRoot){
		if(localRoot != null){
			postOrder(localRoot.left);
			postOrder(localRoot.right);
			System.out.print(localRoot.data +" ");
		}
	}
	//根据先序遍历和中序遍历的结果进行构建一棵树，然后利用后序遍历的方式进行打印输出
	public void initTree(int[] preOrder,int[] inOrder){
		this.root = this.initTree(preOrder,0,preOrder.length-1,inOrder,0,inOrder.length-1);
	}
	//preOrder的从索引start1开始，end1开始
	//inOrder从索引start2开始，end2开始
	private Node initTree(int[]preOrder,int start1,int end1,int[]inOrder,int start2,int end2){
		if(start1>end1||start2>end2) return null;
		int rootData = preOrder[start1];
		Node head = new Node(rootData);
		//找到根节点所在的位置
		int rootIndex = findIndexInArray(inOrder,rootData,start2,end2);//在中序遍历的元素中找到根节点所在的位置
		int offSet = rootIndex -start2 -1;   //为什么会减去start2,start2会进行变化
		//构建左子树
		Node left = initTree(preOrder,start1+1,start1+1+offSet,inOrder,start2,start2+offSet);
		//构建右子树
		Node right = initTree(preOrder,start1+offSet+2,end1,inOrder,rootIndex+1,end2);
		head.left = left;
		head.right = right;
		return head;
	}
	//在arr数组中的，以start开始,end结束，寻找data的元素
	private int findIndexInArray(int[] arr,int data,int start,int end){
		for(int i = start;i<=end;i++){
			if(arr[i] == data){
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args){
		BinaryTreeI biTree = new BinaryTreeI();
		int[] preOrder = {1,2,4,8,9,5,10,3,6,7};
		int[] inOrder = {8,4,9,2,10,5,1,6,3,7};
		biTree.initTree(preOrder,inOrder);   //根据先序遍历和中序遍历，初始化一棵二叉树
		System.out.println("二叉树的后序遍历");
		biTree.postOrder();
	}
}
