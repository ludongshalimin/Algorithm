package com.leetcode.muke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历：非递归的实现
 * 用栈模拟递归
 * @author weifeng
 * 
 *            count 1
 *go 1        go1_L
 *			  go1_R
 */
class Solution_preOrderNoRecrussion0608{
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	class Command{
		String cmd;
		TreeNode node;
		Command(String s,TreeNode node){
			this.cmd = s;
			this.node.val = node.val;
			this.node.left = node.left;
			this.node.right = node.right;
		}
	}
	public void preOrderNoRecru(TreeNode root){
		if(root == null) return ;
		Stack<Command> ss = new Stack<Command>();
		LinkedList<Integer> res = new LinkedList<Integer>();
		ss.push(new Command("go",root));
		while(ss.size() != 0){
			Command cmd = ss.pop();
			if(cmd.cmd == "print"){
				res.add(cmd.node.val);
			}else{
				if(cmd.node.right != null){
					ss.push(new Command("go",cmd.node.right));
				}
				if(cmd.node.left != null){
					ss.push(new Command("go",cmd.node.left));
				}
				ss.push(new Command("print",cmd.node));
			}
		}
	}
}
public class M22_preorderTraversal_NoRecrussion {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int x){val = x;}
	}
	private class Command{
		String s;  //go print
		TreeNode node;
		Command(String s,TreeNode node){
			this.s = s;
			this.node = node;
		}
	}
	public List<Integer> preorderTraversal(TreeNode root){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		Stack<Command> stack = new Stack<Command>();
		stack.push(new Command("go",root));
		while(!stack.empty()){
			Command command = stack.pop();
			if(command.s.equals("print")){   //这里面print和 go是作为标记来做的，什么时候推入和退出
				res.add(command.node.val);
			}
			else{
				assert command.s.equals("go");  //栈的推入顺序，后入先出
				if(command.node.right != null){
					stack.push(new Command("go",command.node.right));
				}
				if(command.node.left != null){
					stack.push(new Command("go",command.node.left));
				}
				stack.push(new Command("print",command.node));
			}	
		}
		return res;
			
	}
}
