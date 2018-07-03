package com.leetcode.muke;
/**
 * 二分搜索树是有顺序的
 * 给定一棵二分搜索树和两个节点，寻找这两个节点的最近公共祖先。
如右图所示二分搜索树
2和8的最近公共祖先为6
2和4的最近公共祖先为2
       _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5

 * @author weifeng
 *利用了二分搜索树的顺序性
 */
class Solution_LCA{
	public class Node{
		int val;
		Node left;
		Node right;
	}
	public Node LCA(Node root,int p,int q){   //p和q的最小公共祖先
		if(root == null)return null;
		if(p<root.val&& q<root.val){
			return LCA(root.left,p,q);
		}
		if(p>root.val && q>root.val){
			return LCA(root.right,p,q);
		}
		return root;
	}
}
public class M32_LowestCommonAncestor {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	public TreeNode loweatCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
		if(p == null || q == null){
			throw new IllegalArgumentException("no result");
		}
		if(root == null){
			return null;
		}
		if(p.val<root.val && q.val<root.val){
			return loweatCommonAncestor(root.left,p,q);
		}
		if(p.val>root.val && q.val > root.val){
			return loweatCommonAncestor(root.right,p,q);
		}
		return root;    //一个数大于等于root的值，一个小于等于root的值
	}
}
