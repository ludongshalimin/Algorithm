package com.leetcode.muke;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历，没有递归的实现
 * @author weifeng
 *
 */
public class M23_inorderTraversal_NoRecrussion {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){val = x;}
	}
	private class Command{
		String s;
		TreeNode node;
		Command(String s,TreeNode node){
			this.s = s;
			this.node = node;
		}
	}
	public List<Integer> inorderTraversal(TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		Stack<Command> stack = new Stack<Command>();
		stack.push(new Command("go",root));
		while(!stack.empty()){
			Command command = stack.pop();
			if(command.s.equals("print")){   //pop后以后，首先是print
				res.add(command.node.val);
			}
			else{
				assert command.s.equals("go");
				if(command.node.right != null){
					stack.push(new Command("go",command.node.right));
				}
				stack.push(new Command("print",command.node));
				if(command.node.left != null){
					stack.push(new Command("go",command.node.left));
				}
			}
		}
		return res;
	}
}
