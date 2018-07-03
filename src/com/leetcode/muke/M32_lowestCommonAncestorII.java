package com.leetcode.muke;

/**
 * 最小公共父节点，如果不是一棵二叉搜索树，是一棵普通的二叉树
 * @author weifeng
 *
 */
public class M32_lowestCommonAncestorII {
	 public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	 }
	 //深度优先遍历
	 //从根节点开始遍历，如果node1和node2中的任一个和root匹配，那么root就是最低公共祖先。
	 //如果都不匹配，则分别递归左、右子树，如果有一个 节点出现在左子树，并且另一个节点出现在右子树，则root就是最低公共祖先.  
	 //如果两个节点都出现在左子树，则说明最低公共祖先在左子树中，否则在右子树。
	 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	     if(root == null) return null;
	     if(root == p) return p;     //出现节点就返回
	     if(root == q) return q;
	
	     TreeNode left = lowestCommonAncestor(root.left, p, q);
	     TreeNode right = lowestCommonAncestor(root.right, p, q);
	
	     if(left != null && right != null) return root;   //找到left和right所对应的位置，如果他们不为空，则找到最小的父节点
	     return left != null ? left : right;
	 }
	 public static void main(String[] args){
		 M32_lowestCommonAncestorII myTree = new M32_lowestCommonAncestorII();
		 TreeNode node1  = myTree.new TreeNode(1);
		 TreeNode node2  = myTree.new TreeNode(2);
		 TreeNode node3  = myTree.new TreeNode(3);
		 TreeNode node4  = myTree.new TreeNode(4);
		 TreeNode node5  = myTree.new TreeNode(5);
		 TreeNode node6  = myTree.new TreeNode(6);
		 TreeNode node7  = myTree.new TreeNode(7);
		 TreeNode node8  = myTree.new TreeNode(8);
		 TreeNode node9  = myTree.new TreeNode(9);
		 
		 node1.left = node2;
		 node1.right = node3;
		 node2.left = node4;
		 node2.right = node5;
		 node4.left = node8;
		 node4.right = node9;
		 node3.left = node6;
		 node3.right = node7;
		 
		 TreeNode ancestor = myTree.lowestCommonAncestor(node1,node8,node9);
		 System.out.println(ancestor.val);
		 
	 }
}
