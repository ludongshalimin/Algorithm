package com.others;

import java.util.LinkedList;

/**
 * leetcode 103题
 * 之字形答应二叉树
 * 给一个二叉树，以之字形的顺序访问节点的值
 * 
 * ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 * @author weifeng
 * 这道题是腾讯面试的师兄分享的面经
 * 中等难度吧
 */
public class M08_BinaryTreePathWithZigzag {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	LinkedList<LinkedList<Integer>> res;
	private void zigzagLevelOrder(TreeNode node,int n){   //这里的n代表了度，从根节点的0开始
		if(node == null) return ;
		if(res.size() <=n){     //获得index为n处的LinkedList<Integet>,还没有创建
			res.add(new LinkedList<Integer>());
			res.get(n).add(node.val);
			if(node.left != null){
				zigzagLevelOrder(node.left,n+1);
			}
			if(node.right != null){
				zigzagLevelOrder(node.right,n+1);
			}
		}else{        //当前的index为n的地方已经创建好了
			if(n%2==0){     //偶数时顺序
				res.get(n).add(node.val);
				if(node.left != null){
					zigzagLevelOrder(node.left,n+1);
				}
				if(node.right != null){
					zigzagLevelOrder(node.right,n+1);
				}
			}else{          //奇数是逆序
				res.get(n).addFirst(node.val);
				if(node.left!=null){
					zigzagLevelOrder(node.left,n+1);
				}
				if(node.right != null){
					zigzagLevelOrder(node.right,n+1);
				}
			}
		}
		
	}
	public LinkedList<LinkedList<Integer>> zigzagLevelOrder(TreeNode root){
		res = new LinkedList<LinkedList<Integer>>();
		zigzagLevelOrder(root,0);
		return res;
	}
	public static void main(String[] args){
		M08_BinaryTreePathWithZigzag ss = new M08_BinaryTreePathWithZigzag();
		TreeNode node1 = ss.new TreeNode(3);
		
		TreeNode node2 = ss.new TreeNode(9);
		TreeNode node3 = ss.new TreeNode(20);
		
		TreeNode node4 = ss.new TreeNode(15);
		TreeNode node5 = ss.new TreeNode(7);
		
		TreeNode node6 = ss.new TreeNode(2);
		TreeNode node7 = ss.new TreeNode(8);
		node1.left = node2;
		node1.right = node3;
		node3.left = node4;
		node3.right = node5;
		
		node2.left = node6;
		node2.right = node7;
		
		TreeNode node11 = ss.new TreeNode(11);
		TreeNode node22 = ss.new TreeNode(22);
		TreeNode node33 = ss.new TreeNode(33);
		TreeNode node44 = ss.new TreeNode(44);
		TreeNode node55 = ss.new TreeNode(55);
		TreeNode node66 = ss.new TreeNode(66);
		TreeNode node77 = ss.new TreeNode(77);
		TreeNode node88 = ss.new TreeNode(88);
		node6.left = node11;
		node6.right = node22;
		node7.left = node33;
		node7.right = node44;
		node4.left = node55;
		node4.right = node66;
		node5.left = node77;
		node5.right = node88;
		
		LinkedList<LinkedList<Integer>> result;
		result=ss.zigzagLevelOrder(node1);
		for(LinkedList<Integer> aa:result){
			for( Integer i:aa){
				System.out.print(i);
				System.out.print("  ");
			}
			System.out.println();
		}
		
	}
}
