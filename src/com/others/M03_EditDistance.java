package com.others;

import java.util.Arrays;

/*
 * 编辑距离，
 * 在考略计算两个字符串的相似度的时候，有这么一个编辑距离
 * 正巧这个题是leetcode_72的题，所以在这也就刷刷
 * 
 * 题目
 * 给定一个源串和一个目标串
 * 能够进行如下的操作
 * 1:在任意的位置插入一个字符，
 * 2:替换一个字符
 * 3:删除任意的字符
 * 写一个程序，实现返回最小操作次数，使得源串进行上述操作后等于目标串
 */
//这里面报错，timelimit的错误，如何进行优化，记忆化搜索或者动态规划
/*
 * 解题思路：
 * F(n,m)表示[0..n]的字符串s1,[0...m]的字符串s2,s1->s2所需要的最小操作次数
 * 这里有两种情况：
 * 	1：s1[n] == s2[m]
 * 	   F(n-1,m-1)
 *  2:s1[n] != s2[m]
 *     F(n-1,m)+1    //这里相当于删除
 *     F(n,m-1)+1    //这里相当于插入
 *     F(n-1,m-1)+1  //这里相当于替换
 *     这三种方向取最小的操作数
 * 边界条件
 * 	if(n==-1 && m!=-1)return m+1;表示的是s1字符串已经操作完毕，但是s2字符串还有剩余m+1个字符串没有操作，这时候只能进行m+1次插入操作
 *  if(n!=-1 && m==-1)return n+1;表示的s1字符串还没有操作完毕，但是s2字符串已经操作完毕，s1剩余n+1个字符只能进行删除
 *  if(n==-1 && m==-1)return 0;表示的两个字符串均进行操作完毕
 */
public class M03_EditDistance {
	//表示的是以[0...n]s1,[0...n]s2
	int[][] memo;
	private int getMinStep(String s1,String s2,int n,int m){  //这里面假设n，m是索引
		int minStep = Integer.MAX_VALUE;
		if(n == -1 && m!=-1)return m+1; //第一个字符串操作完了，s2还有剩余的字符串没有匹配，这时候只能，全部增加操作
		if(n!=-1 && m==-1)return n+1;   //第一个字符串还没有操作完，第二个字符串已经结束，这时候，只能是删除操作
		if(n==-1&& m== -1)return 0;     //表示的这两个字符串都进行完毕，全部匹配成功
		if(memo[n][m] != -1)return memo[n][m];  
		if(s1.charAt(n)==s2.charAt(m)){
			minStep = getMinStep(s1,s2,n-1,m-1);
		}else{
			minStep = Math.min(minStep,getMinStep(s1,s2,n-1,m) + 1);  //相当于删除操作
			minStep = Math.min(minStep,getMinStep(s1,s2,n-1,m-1)+1);  //相当于替换操作
			minStep = Math.min(minStep,getMinStep(s1,s2,n,m-1) + 1);  //相当于插入操作
		}
		memo[n][m] = minStep;
		return minStep;
	}
	public int getMinStep(String s1,String s2){
		int n = s1.length();
		int m = s2.length() ;
		memo = new int[n][m];
		for(int i = 0;i<n;i++){
			Arrays.fill(memo[i],-1);  //填充二维数组，这里的二维数组并不能提高效率，因为也是用for循环来实现的
		}
		return getMinStep(s1,s2,n-1,m-1);
	}
	public int getMinStepDy(String s1,String s2){
		int n = s1.length();
		int m = s2.length();           
		if(m==0) return n;
		if(n==0) return m;             //这里申请的内存要求是n>0&&m>0
		int[][] memo = new int[n][m];  //memo[i][j]表示[0...i]s1,[0...j]s2,s1->s2所需的最小的操作
		for(int i = 0;i<n;i++){  //要看[0..i]中是否有s2[0]这个字符，如果有就是i-1,如果没有就是i
			boolean hasChar = false;
			for(int j = 0;j<=i;j++){
				if(s1.charAt(j) == s2.charAt(0)){
					hasChar = true;
					break;
				}
			}
			if(hasChar){
				memo[i][0] = i;  //s1[0..i]中有一个字符和s2[0]相等
			}else{
				memo[i][0] = i+1; //s1[0..i]中没有一个字符和s2[0]相等
			}
			hasChar = false;
		}
		for(int j = 0;j<m;j++){
			boolean hasChar= false;
			for(int i = 0;i<=j;i++){
				if(s1.charAt(0) == s2.charAt(i)){
					hasChar = true;
					break;
				}
			}
			if(hasChar){
				memo[0][j] = j;
			}else{
				memo[0][j] = j+1;
			}
			hasChar = false;
		}
		for(int i = 1;i<n;i++){
			for(int j = 1;j<m;j++){
				if(s1.charAt(i) == s2.charAt(j)){
					memo[i][j] = memo[i-1][j-1];
				}else{
					int minStep = Integer.MAX_VALUE;
					minStep = Math.min(minStep,memo[i-1][j]+1);
					minStep = Math.min(minStep,memo[i-1][j-1]+1);
					minStep = Math.min(minStep,memo[i][j-1]+1);
					memo[i][j] = minStep;
				}
				
			}
		}
		return memo[n-1][m-1];
	}
	public static void main(String[] args){
		M03_EditDistance ss = new M03_EditDistance();
		String s1 = "horse";
		String s2 = "ros";   //应该返回3
		System.out.println(ss.getMinStep(s1,s2));
		System.out.println(ss.getMinStepDy(s1,s2));
		System.out.println(ss.getMinStep(s2,s1));
		System.out.println(ss.getMinStepDy(s2,s1));
		String s3 = "intention";
		String s4 = "execution";   //应该返回5
		System.out.println(ss.getMinStep(s3,s4));
		System.out.println(ss.getMinStepDy(s3,s4));
		System.out.println(ss.getMinStep(s4,s3));
		System.out.println(ss.getMinStepDy(s4,s3));
	}
}
