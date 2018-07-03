package com.leetcode.muke;
/**
 * 反转一棵二叉树
 * @author weifeng
 *
 */
public class M27_InvertBinaryTree {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	public TreeNode invertTree(TreeNode root){
		if(root == null){
			return null;
		}
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;
	}
}
