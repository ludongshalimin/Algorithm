package com.leetcode.muke;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个数字字符串，返回这个数字字符串能表示的所有字母组合
树形结构的字符串调用
 * @author weifeng
 * digits 是数字字符串
s(digits) 是digits所能代表的字母字符串
s(digits[0…n-1]) 
    = letter(digits[0]) + s(digits[1…n-1])
    = letter(digits[0]) + letter(digits[1]) + s(digits[2…n-1])
    = ……

 *
 *o(2^n)的复杂度，是一种暴力的方法
 *回溯法，暴力法的一种重要实现
 *多重循环，暴力法。
 */
public class M33_LetterCombination {
    private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };
    private ArrayList<String> res;
    public List<String> letterCombination(String digits){
    	res = new ArrayList<String>();
    	if(digits.equals(""))return res;  //表示已经到0了,,这个res是作为全局的一个变量来使用的
    	findCombination(digits,0,"");
    	return res;
    }
 // s中保存了此时从digits[0...index-1]翻译得到的一个字母字符串
    // 寻找和digits[index]匹配的字母, 获得digits[0...index]翻译得到的解
    private void findCombination(String digits,int index,String s){  //作为一个树形问题
    	if(index == digits.length()){   //表示已经遍历到头了，深度优先搜索？？？
    		res.add(s); //把整个累加后的字符串，添加到结果中
    		return;
    	}
    	Character c = digits.charAt(index);
    	String letters = letterMap[c - '0'];  //数字得到对应的字符串
    	for(int i =0;i<letters.length();i++){  //得到对应的数字相应的字符串
    		findCombination(digits,index+1,s+letters.charAt(i));
    	}
    }
    private static void printList(List<String> list){
    	for(String s:list){
    		System.out.println(s);
    	}
    }
    public static void main(String[] args){
    	printList(new M33_LetterCombination().letterCombination("234"));
    }
}
