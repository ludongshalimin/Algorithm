package com.leetcode.muke;
/**
 * 给出一棵二叉树以及一个数字sum，
 * 判断在这棵二叉树上是否存在一条从根到叶子的路径，其路径上的所有节点和为sum。
 * @author weifeng
 *
 */
class Solution_PathSumI_0715{
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	//是否存在一条从根到叶子的路径
	public boolean hasPath(TreeNode root,int sum){
		if(root == null)return false;
		if(root.left == null && root.right == null){  //表示到达了叶子节点了
			return root.val == sum;
		}
		if(hasPath(root.left,sum - root.val) || hasPath(root.right,sum-root.val)){
			return true;
		}
		return false;
	}
}
public class M29_PathSumI {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	public boolean hasPathSum(TreeNode root,int sum){
		if(root == null){
			return false;
		}
		if(root.left == null&& root.right == null){  //以叶子节点为节点的终点大的一条路径
			return sum == root.val;
		}
		return hasPathSum(root.left,sum-root.val)||
				hasPathSum(root.right,sum-root.val);
	}
}
