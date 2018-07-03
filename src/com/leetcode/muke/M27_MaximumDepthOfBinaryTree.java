package com.leetcode.muke;
//求一个二叉树的深度
//求一个二叉树的最大深度
public class M27_MaximumDepthOfBinaryTree {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	public int maxDepth(TreeNode root){
		if(root == null){
			return 0;
		}
		return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
	}
}
