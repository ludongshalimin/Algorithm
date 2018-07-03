package com.leetcode.muke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树，返回所有表示从根节点到叶子节点路径的字符串。
如右图所示的二叉树
    1
  /    \
2      3
   \
    5

结果为 ["1->2->5", "1->3"]
 * @author weifeng
 * 
 * 1->{左子树的路径字符串}
 * 1->{右子树的路径字符串}
 * 
 * 2->{左子树的路径字符串}        3->{左子树的路径字符串}
 * 2->{右子树的路径字符串}        3->{右子树的路径字符串}
 * 
 * {4}   {5}                {6}   {7}
 * 
 * 
 * 
 *
 *
 */
class Solution_binaryTreePath0520{
	private LinkedList<String> ll;
	public LinkedList<String> binaryTreePath(com.leetcode.muke.Solution_BinaryTreePath.Node n1){
		ll = new LinkedList<String>();
		path(n1,"");
		return ll;
	}
	private void path(com.leetcode.muke.Solution_BinaryTreePath.Node root,String s){
		if(root.left == null&&root.right ==  null){   //已经到达了叶子节点
			StringBuilder sb = new StringBuilder(s);
			sb.append( "-->");
			sb.append(Integer.toString(root.val));
			ll.add(sb.toString());  //最终的结果加入到容器中
			return;
		}
		if(root.left != null){
			StringBuilder sb = new StringBuilder(s);
			sb.append("-->");
			sb.append(Integer.toString(root.val));
			path(root.left,sb.toString());
		}
		if(root.right !=null){
			StringBuilder sb = new StringBuilder(s);
			sb.append("-->");
			sb.append(Integer.toString(root.val));
			path(root.right,sb.toString());
		}
	}
}
class Solution_BinaryTreePath{
	 class Node{
		int val;
		Node left;
		Node right;
		public Node(int val){
			this.val = val;
		}
	}
	public List<String> binaryTreePath(Node root){
		ArrayList<String> res = new ArrayList<String>();
		if(root == null) return res;
		if(root.left == null && root.right == null){
			//到达了叶子节点
			res.add(Integer.toString(root.val));
			return res;
		}
		List<String> leftPath = binaryTreePath(root.left);
		for (String s:leftPath){
			StringBuilder sb = new StringBuilder(Integer.toString(root.val));
			sb.append("->");
			sb.append(s);
			res.add(sb.toString());
		}
		List<String>rightPaths = binaryTreePath(root.right);	
		for(String s:rightPaths){
			StringBuilder sb = new StringBuilder(Integer.toString(root.val));
			sb.append("->");
			sb.append(s);
			res.add(sb.toString());
		}
		return res;
	}
}
public class M30_BinaryTreePaths {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val =x;}
	}
	public List<String> binaryTreePath(TreeNode root){
		ArrayList<String> res = new ArrayList<String>();
		if(root == null) return res;    //返回的是一个整个数组
		if(root.left == null && root.right == null){
			//到达了叶子节点
			res.add(Integer.toString(root.val));
			return res;
		}                                                    //利用递归返回的结果
		List<String> leftPaths = binaryTreePath(root.left);  //递归的返回的是结果字符串
		for(String s:leftPaths){
			StringBuilder sb = new StringBuilder(Integer.toString(root.val));
			sb.append("->");
			sb.append(s);
			res.add(sb.toString());
		}
		List<String>rightPaths = binaryTreePath(root.right);
		for(String s:rightPaths){
			StringBuilder sb = new StringBuilder(Integer.toString(root.val));
			sb.append("->");
			sb.append(s);
			res.add(sb.toString());
		}
		return res;
	}
	public static void main(String[] args){
		Solution_BinaryTreePath ss = new Solution_BinaryTreePath();
		Solution_BinaryTreePath.Node n1 =  ss.new Node(1);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n2 =  ss.new Node(2);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n3 =  ss.new Node(3);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n4 =  ss.new Node(4);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n5 =  ss.new Node(5);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n6 =  ss.new Node(6);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n7 =  ss.new Node(7);   //前面代表了什么类型，后面代表了一个实例的
		Solution_BinaryTreePath.Node n8 =  ss.new Node(8);   //前面代表了什么类型，后面代表了一个实例的
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		ArrayList<String> ls = new ArrayList();
		ls = (ArrayList<String>) ss.binaryTreePath(n1);
		int n = ls.size();
		for(int i = 0;i<n;i++){
			System.out.println(ls.get(i));
		}
		System.out.println("sasa");
		LinkedList<String> ll = new LinkedList<String>();
		Solution_binaryTreePath0520 sss = new Solution_binaryTreePath0520();
		ll = sss.binaryTreePath(n1);
		for(String s:ll){
			System.out.println(s);
		}
		
	}
}
