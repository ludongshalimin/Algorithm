package com.leetcode.muke;

import java.util.Stack;

/**
 * 给定一个字符串，只包含 ( , [ , { , ) , ] , } ，
 * 判定字符串中的括号匹配是否合法。
如 ”()” , “()[]{}” 是合法的
如 “(]”，”([)]” 是非法的 

 * @author weifeng
 *
 */
public class M18_ValidParenthess {
	public boolean isValid(String s){
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0;i<s.length();i++){
			if(s.charAt(i) == '(' || s.charAt(i) =='[' || s.charAt(i)=='{'){
				stack.push(s.charAt(i));
			}
			else{ //当前的符号不是上述的符号
				if(stack.size()==0){   //当前有数据，但是当前的这个数据为空了，所以也
					return false;
				}
				Character c = stack.pop();
				Character match;
				if(s.charAt(i) == ')')match = '(';
				else if(s.charAt(i) ==']') match = '[';
				else{
					assert s.charAt(i) == '}';
					match='{';
				}
				if (c!=match) return false;
			}
		}
		if(stack.size() != 0){   //所有的字符都遍历完了，但是栈中还是有数据则这时候是出错的
			return false;
		}
		return true;
	}
	private static void printBool(boolean b){
		System.out.println(b?"true":"false");
	}
	public static void main(String[] args){
	    printBool((new M18_ValidParenthess()).isValid("()"));
        printBool((new M18_ValidParenthess()).isValid("()[]{}"));
        printBool((new M18_ValidParenthess()).isValid("(]"));
        printBool((new M18_ValidParenthess()).isValid("([)]"));
	}
}
