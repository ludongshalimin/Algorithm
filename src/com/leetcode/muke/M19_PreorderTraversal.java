package com.leetcode.muke;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * @author weifeng
 *
 */
public class M19_PreorderTraversal {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;	
		}
	}
	private void preOrderTraversal(TreeNode node,List<Integer>list){
		if(node != null){
			list.add(node.val);
			preOrderTraversal(node.left,list);
			preOrderTraversal(node.right,list);
		}
	}
	public List<Integer> preOrderTraversal(TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		preOrderTraversal(root,res);
		return res;
	}
}
