package com.leetcode.muke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 二叉树的层序遍历
 * @author weifeng
 *
 */
class Solution_levaleOrder0608{
	class Node{
		int val;
		Node left;
		Node right;
		Node(int val){
			this.val = val;
		}
	}
	public LinkedList<Integer>  levelOrder(Node root){
		LinkedList<Node> qq = new LinkedList<Node>();
		LinkedList<Integer> res = new LinkedList<Integer>();
		qq.addLast(root);
		while(qq.size()!=0){
			Node nn = qq.removeFirst();
			res.add(nn.val);
			if(nn.left != null){
				qq.addLast(nn.left);
			}
			if(nn.right != null){
				qq.addLast(nn.right);
			}
		}
		return res;
	}
}
class Solution_levelOrder{
	public class Node{
		int val;
		Node left;
		Node right;
		Node(int val){
			this.val = val;
		}
	}
	public void levelOrder(Node head){
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.addLast(head);
		while(queue.size() != 0){
			Node curNode = queue.removeFirst();
			System.out.println(curNode.val);
			if(curNode.left != null){
				queue.addLast(curNode.left);
			}
			if(curNode.right != null){
				queue.addLast(curNode.right);
			}
		}
	}
}
public class M25_levelorderTraversal {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	private class Pair<TreeNode ,Integer>{
		TreeNode node;
		Integer integer;
		Pair(TreeNode node,Integer i){
			this.node = node;
			this.integer = i;
		}
		TreeNode getKey(){
			return this.node;
		}
		Integer getValue(){
			return this.integer;
		}
	}
	public List<List<Integer>> levelOrder(TreeNode root){
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();  //代表了每一层的结果作为一个List,然后每一层构成一个ArrayList
		if(root == null)return res;
		//使用LinkedList作为我们先入先出的队列
		LinkedList<Pair<TreeNode,Integer>> queue = new LinkedList<Pair<TreeNode,Integer>>();
		queue.addLast(new Pair<TreeNode,Integer>(root,0));
		while(!queue.isEmpty()){
			Pair<TreeNode,Integer> front = queue.removeFirst();  //队首的元素进行出队
			TreeNode node = front.getKey();   //获取节点
			int level = front.getValue();  //获取所在的序列
			if(level == res.size()){    //还没有明白这句话什么意思？？？，说明还不存在这一层
				res.add(new ArrayList<Integer>());
			}
			assert level < res.size();
			res.get(level).add(node.val);   //res.get(level)得到的是 level索引对应的链表
			if(node.left != null){
				queue.addLast(new Pair<TreeNode,Integer>(node.left,level+1));
			}
	       if(node.right != null)
                queue.addLast(new Pair<TreeNode, Integer>(node.right, level + 1));			
		}
		return res;
	}
	public static void main(String[] args){
		Solution_levelOrder ss = new Solution_levelOrder();
		Solution_levelOrder.Node  n0 = ss.new Node(0);
		Solution_levelOrder.Node  n1 =ss.new Node (1);
		Solution_levelOrder.Node  n2 = ss.new Node (2);
		n0.left = n1;
		n0.right = n2;
		Solution_levelOrder.Node  n3 =ss.new Node (3);
		Solution_levelOrder.Node  n4 = ss.new Node (4);
		Solution_levelOrder.Node  n5 =ss.new Node (5);
		Solution_levelOrder.Node  n6 = ss.new Node (6);
		
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		n2.right = n6;
		ss.levelOrder(n0);
	}
}
