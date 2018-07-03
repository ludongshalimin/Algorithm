package com.leetcode.muke;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中序遍历
 * @author weifeng
 *
 */
public class M20_InorderTraversal {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	private void inorderTraversal(TreeNode node,List<Integer> list){
		if(node != null){
			inorderTraversal(node.left,list);
			list.add(node.val);
			inorderTraversal(node.right,list);
		}
	}
	public List<Integer> inorderTraversal(TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		inorderTraversal(root,res);
		return res;
	}
}
