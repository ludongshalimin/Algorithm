package com.leetcode.muke;
/**
 * 给出一棵二叉树以及一个数字sum，判断在这棵二叉树上存在多少条路径，
 * 其路径上的所有节点和为sum。
其中路径不一定要起始于根节点；终止于叶子节点。
路径可以从任意节点开始，但是只能是向下走的。
 * @author weifeng
 *
 */
class Solution_PathSumII_0715{
	//这颗二叉树只是普通的二叉树
	//有多少条这样的路径
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	private int findPath(TreeNode root,int sum){
		if(root == null)return 0;
		int count = 0;
//		if(root.left == null && root.right == null){
//			if( root.val == sum){
//				count ++;
//			}
//		}
		if(root.val == sum){  //这里的叶子节点也是普通的节点
			count ++;
		}
		count += findPath(root.left,sum-root.val);
		count += findPath(root.right,sum-root.val);
		return count;
	}
	public int pathSum(TreeNode root,int sum){
		if(root == null)return 0;
		return findPath(root,sum) +
				pathSum(root.left,sum)+
				pathSum(root.right,sum);
	}
}
public class M31_PathSumIII {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	//在以node为根节点的二叉树中，寻找包含node的路径，和为sum
	//返回这样的路径的个数
	private int findPath(TreeNode node,int num){
		if(node == null)return 0;   //这里没有说是到叶子还是到其他的节点就可以，所以可以一直的递归下去
		int res = 0;
		if(node.val == num){
			res += 1;          //不能直接返回num，因为，后面的数字和当前的数字相加如果也为 num?如果有负数
		}              
		res += findPath(node.left,num-node.val);
		res += findPath(node.right,num-node.val);
		return res;
	}
	//在以root为根节点的二叉树中，寻找和为sum的路径的个数
	public int pathSum(TreeNode root,int sum){
		if(root == null)return 0;
		return findPath(root,sum)        //包含root
				+ pathSum(root.left,sum) //不包含root的左子树
				+ pathSum(root.right,sum);//不包含root的右子树
		
	}
}
