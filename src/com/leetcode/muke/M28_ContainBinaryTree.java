package com.leetcode.muke;
/**
 * 判断二叉树是否包含某一个KEY值
 * @author weifeng
 * 将递归看成是解决子问题，假设这个子问题求解出了答案
 */
public class M28_ContainBinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	public boolean contain(TreeNode root,int key){
		if(root == null)return false;
		if(key == root.val){
			return true;
		}
		if(contain(root.left,key) || contain(root.right,key)){
			return true;
		}
		return false;
	}
}
