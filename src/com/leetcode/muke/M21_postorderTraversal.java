package com.leetcode.muke;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树后序遍历
 * @author weifeng
 *
 */
public class M21_postorderTraversal {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	private void postorderTraversal(TreeNode node,List res){
		if(node != null){
			postorderTraversal(node.left,res);
			postorderTraversal(node.right,res);
			res.add(node.val);
		}
	}
	public List<Integer>postorderTraversal(TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		postorderTraversal(root,res);
		return res;
	}
}
