package com.others;

import java.util.LinkedList;
class Solution_BinaryPath0703{
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	LinkedList<String> res;
	private void path(TreeNode root,String s){  //要在s后面的字符串中追加字符
		if(root.left == null&&root.right==null){
			String ss = s + "->" + Integer.toString(root.val);
			res.add(ss);
			return;
		}
		if(root.left != null){
			String ss = s + "->" + Integer.toString(root.val);
			path(root.left,ss);
		}
		if(root.right != null){
			String ss = s + "->" +Integer.toString(root.val);
			path(root.right,ss);
		}
		return;
	}
	public LinkedList<String> binaryPaths(TreeNode root){
		res = new LinkedList<String>();
		if(root == null)return res;
        if(root.left == null && root.right == null){
        	res.add(Integer.toString(root.val));
            return res;
        }
		if(root.left != null){
			path(root.left,Integer.toString(root.val));
		}
		if(root.right !=null){
			path(root.right,Integer.toString(root.val));
		}
		return res;	
	}
}
public class M05_BinaryPath {
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
    public LinkedList<String> binaryTreePaths(TreeNode root) {
    	LinkedList<String> ls = new LinkedList<String>();
    	if(root == null) return ls;
        if(root.left==null && root.right == null){  //表示当前节点就是叶子节点
        	ls.add(Integer.toString(root.val));   //或者使用""+root.val
        	return ls;
        }
        if(root.left != null){
        	LinkedList<String> leftls = binaryTreePaths(root.left);
        	for(String ss:leftls){  //代表了历史的字
            	StringBuilder cur = new StringBuilder(Integer.toString(root.val));
        		cur.append("->");
        		cur.append(ss);
        		ls.add(cur.toString());
        	}
        }
        if(root.right != null){
        	LinkedList<String> rightls = binaryTreePaths(root.right);
        	for(String ss:rightls){  //代表了历史的字
        		StringBuilder cur = new StringBuilder(Integer.toString(root.val));
        		cur.append("->");
        		cur.append(ss);
        		ls.add(cur.toString());
        	}
        }
        return ls;
    }
	public static void main(String[] args){
		M05_BinaryPath ss = new M05_BinaryPath();
		M05_BinaryPath.TreeNode node0 = ss.new TreeNode(0);
		M05_BinaryPath.TreeNode node1 = ss.new TreeNode(1);
		M05_BinaryPath.TreeNode node2 = ss.new TreeNode(2);
		M05_BinaryPath.TreeNode node3 = ss.new TreeNode(3);
		M05_BinaryPath.TreeNode node4 = ss.new TreeNode(4);
		M05_BinaryPath.TreeNode node5 = ss.new TreeNode(5);
		node0.left = node1;
		node0.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.right = node5;
		LinkedList<String> ls = ss.binaryTreePaths(node0);
		for(String s:ls){
			System.out.println(s);
		}
		System.out.println("0703");
		Solution_BinaryPath0703 sss = new Solution_BinaryPath0703();
		Solution_BinaryPath0703.TreeNode node00 = sss.new TreeNode(0);
		Solution_BinaryPath0703.TreeNode node11 = sss.new TreeNode(1);
		Solution_BinaryPath0703.TreeNode node22 = sss.new TreeNode(2);
		Solution_BinaryPath0703.TreeNode node33 = sss.new TreeNode(3);
		Solution_BinaryPath0703.TreeNode node44 = sss.new TreeNode(4);
		Solution_BinaryPath0703.TreeNode node55 = sss.new TreeNode(5);
		node00.left = node11;
		node00.right = node22;
		node11.left = node33;
		node11.right = node44;
		node22.right = node55;
		LinkedList<String> ls2 = sss.binaryPaths(node00);
		for(String s:ls2){
			System.out.println(s);
		}
	}
}


