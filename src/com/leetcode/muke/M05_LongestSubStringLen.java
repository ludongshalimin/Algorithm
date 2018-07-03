package com.leetcode.muke;

import java.util.HashMap;
import java.util.LinkedList;

/*
 * 在一个字符串中寻找没有重复字母的连续最长子串，返回其长度。
如”abcabcbb”，则结果为”abc”，长度为3
如”bbbbb”，则结果为”b”，长度为1
如”pwwkew”，则结果为”wke”，长度为3
如何求出最长的子串呢？

没有重复的字符串的长度
 */
/**
 * 最长的子串没有重复的字母,滑动窗口
 * 如何求出最长的子串
 * 是否可以用个容器类进行判断
 */
class Solution_subString0623{
	public int getMaxLen(String s){  //给出一个字符串
		int maxLen = 0;
		char[] arr = s.toCharArray();
		int n = arr.length;
		LinkedList<Character> hs = new LinkedList<Character>();
		int i = 0;
		int j = -1;
		while(i<n){
			if(j+1<n&&!hs.contains(arr[j+1])){
				j++;
				hs.add(arr[j]);
			}else{
				i++;
				hs.removeFirst();
			}
			maxLen = Math.max(maxLen,hs.size());
			
		}
		return maxLen ;
	}
}
class Solution_subString0608{
	public int getSub(String s){
		int n = s.length();
		LinkedList<Character> hm = new LinkedList<Character>();
		int maxLen = 0;
		int i = 0;
		int j = 0;
		while(i<n){
			if(j<n && !hm.contains(s.charAt(j))){  //不包含
				hm.addLast(s.charAt(j));
				j++;
			}else{
				hm.removeFirst();  //删除第一个，然后接着删除
				i++;
			}
			maxLen = Math.max(maxLen,hm.size());
//			if(maxLen>hm.size()){
//				maxLen = hm.size();
//				maxR = j;
//				maxL = i;
//			}

		}
		return maxLen;
	}
	//通过记录最大值的索引的形式记录
}
class Solution_subStringMax0517{
	public int subStringMax(String str){
		int n = str.length();
		int maxLen = 0;
		LinkedList<Character> ss  = new LinkedList<Character>();  //这里面存储的字符串应该是独一无二的
		int l = 0;
		int r = 0;
		while(l<n){
			if(r< n &&!ss.contains(str.charAt(r))){
				ss.add(str.charAt(r));
				r++;
			}else{  //出现了重复的字符，但是这个字符目前还没有添加
				maxLen= Math.max(maxLen,ss.size());
				ss.removeFirst();  //删除第一个
				l++;
			}
		}
		return maxLen;
	}
}
//这里面包含了两个问题，
//没有重复的连续的字符串，
//求解连续的没有重复的子串
class Solution{
	public String getSubString(String s){
		int rIndex=-1;
		int lIndex=0;
		HashMap<Character,Integer> ss = new HashMap<Character,Integer>();
		int n = s.length();
		int l = 0;
		int r = -1;
		while(l < n){
			if(r + 1<n && !ss.containsKey(s.charAt(r+1))){//没有包含重复的元素
				r++;
				ss.put(s.charAt(r),r);
				if(r-l>rIndex-lIndex){  //要找到在s中的最大长度的索引的位置
					rIndex = r;
					lIndex = l;
				}	
				
			}else{   //已经包含了重复的元素,要删除最左边的元素
				ss.remove(s.charAt(l));  
				l++;
			}
	
		}
		StringBuilder res = new StringBuilder();  //获取没有重复的连续字符串
		for (int i = lIndex;i<=rIndex;i++){
			res.append(s.charAt(i));
		}
		return res.toString();
	}
}

public class M05_LongestSubStringLen {
	public int lengthOfLongestSubstring(String s){
		int[] freq = new int[256];   //用一个数组表示字符的频率，因为字符的范围就是0-255,所以用此表示范围
		int l = 0,r = -1 ;// 滑动窗口s[l..r]
		int res = 0;
		//整个循环从l = 0;r = -1这个空窗口开始
		//到l == s.size()；r = s.size()-1这个空窗口结束
		//在每次循环中逐渐改变窗口，维护freq，并记录当前窗口的值
		while(l < s.length()){
			if (r+1 < s.length() && freq[s.charAt(r+1)]==0){  //当前的字符还没有被包含
				r++;
				freq[s.charAt(r)]++;  //字符频率++
			}
			else{ //r已经到头 || freq[s[r+1]] !=0，窗口已经到头了或则出现了重复的数字
				
				freq[s.charAt(l)]--;
				l++;
			}
			res = Math.max(res,r-l+1);				
		}
		return res;
	}
	public int lengthOfLongestSubstringMine(String s){
		HashMap<Character,Integer> myhashmap = new HashMap<Character,Integer>();
		int n = s.length();
		int l = 0;  //索引
		int r = -1;
		int res = 0;  //存放的是最后的结果
		while(l < n){
			if(r+1<n && !myhashmap.containsKey(s.charAt(r+1))){   //没有超过字符串的长度，并且hashmap里面不存在
				myhashmap.put(s.charAt(r+1),r+1);
				r ++;
			}else{  //如果当前字符串，在myhashMap中有了
				myhashmap.remove(s.charAt(l));   //删除l代表的字符,只有arraylist,LinkedList有remove(index)
				l ++;
			}
			res = Math.max(res,myhashmap.size());
		}
		return res;
	}
    public static void main(String[] args) {

        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstring( "abcbabcde" ));
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstring( "bbbbb" ));
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstring( "pwwkew" ));
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstring( "" ));
        System.out.println("sasa");
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstringMine( "abcbabcde" ));
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstringMine( "bbbbb" ));
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstringMine( "pwwkew" ));
        System.out.println((new M05_LongestSubStringLen()).lengthOfLongestSubstringMine( "" ));
        System.out.println("hahah");
        System.out.println((new Solution().getSubString( "abcbabcde" )));
        System.out.println((new Solution().getSubString( "bbbbb" )));
        System.out.println((new Solution().getSubString( "pwwkew" )));
        System.out.println((new Solution().getSubString( "" )));

        System.out.println(new Solution_subStringMax0517().subStringMax("abcbabcde" ));
        System.out.println((new Solution_subStringMax0517().subStringMax( "bbbbb" )));
        System.out.println((new Solution_subStringMax0517().subStringMax( "pwwkew" )));
        System.out.println((new Solution_subStringMax0517().subStringMax( "" )));
        
        
        
        System.out.println("0608");
        System.out.println(new Solution_subString0608().getSub("abcbabcde" ));
        System.out.println((new Solution_subString0608().getSub( "bbbbb" )));
        System.out.println((new Solution_subString0608().getSub( "pwwkew" )));
        System.out.println((new Solution_subString0608().getSub( "" )));
        
        System.out.println("0623");
        System.out.println(new Solution_subString0623().getMaxLen("abcbabcde" ));
        System.out.println((new Solution_subString0623().getMaxLen( "bbbbb" )));
        System.out.println((new Solution_subString0623().getMaxLen( "pwwkew" )));
        System.out.println((new Solution_subString0623().getMaxLen( "" )));
    }
}
