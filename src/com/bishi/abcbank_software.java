package com.bishi;
/**
 * 农行软件中心，总共出了三个题
 * 1：三个字符串，s1,s2,s3,s3是否可以由s1,s2交叉构成
 * 2：数独问题，将1填入4n*4n的数组，使得，每一行，每一列，每一个对角线的这三个值相等
 * 3：搭积木，问题，如果一个小孩，搭积木，共计n个积木，现在要拆解k个积木，要求顺序不变，同时，拆解后的组成的数值是最小的
 * 	 比如：29134，5个积木，现在要拆解掉3个，则最小的数是13
 *   可以抽象为，一个数组，然后随机的在数组中删掉k，顺序组成的数组
 * @author weifeng
 *
 */
/*
 * leetcode 97题目
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	Example 1:
	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
	Output: true
	Example 2:
	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
	Output: false
 */
class Solution_ConsitsString0707{
	//这里的F(n,m)代表s1[0..n],s2[0..m]是否可以构成s3[0..n+m+1];因为需要全部的进行匹配n+1的长度m+1的长度
	//终止条件n==-1,表示s1字符串已经没有了，变成了s2[0..m]是否可以构成s3[0..m]
	//终止条件m==-1,表示s2字符串已经没有了，变成了s1[0..n]是否可以构成s3[0..n]
	private boolean isConsist(String s1,String s2,String s3,int n,int m){
		if(n == -1){
			return s2.substring(0,m+1).equals(s3.substring(0,m+1));
		}
		if(m == -1){
			return s1.substring(0,n+1).equals(s3.substring(0,n+1));
		}
		if(s1.charAt(n)== s3.charAt(m+n+1)&& isConsist(s1,s2,s3,n-1,m)||
				s2.charAt(m) == s3.charAt(m+n+1)&& isConsist(s1,s2,s3,n,m-1)){
			return true;
		}
		return false;
	}
	public boolean isConsist(String s1,String s2,String s3){
        int n = s1.length();
        int m = s2.length();
        if(n+m != s3.length())return false;
        if(n==0&&m==0)return s3.length()==0;
        if(n==0)return s2.equals(s3);
        if(m==0)return s1.equals(s3);
        return isConsist(s1,s2,s3,n-1,m-1);
	}
	//这里memo[i][j]表示，s1[0..i-1],s2[0..j-1]是否可以构成s3[0..i+j-1]
	//memo[0][j]表示,s1[0..-1]也就是不存在了，s2[0..j]是否可以构成s3[0..j]
	//memo[i][0]表示s1[0..i],s2[0..-1]也就是不存在了，是否可以构成s3[0..i]
	//最后返回memo[n][m]表示s1[0..n-1],s2[0..m-1]是否可以构成s3[0..n+m-1]
	//因为没有办法表示[-1][i]所以只能拓展[n][m]
	public boolean isConsistDy(String s1,String s2,String s3){
		int n = s1.length();
		int m = s2.length();
		if(s1.length()+s2.length() != s3.length())return false;
        if(n==0&&m==0)return s3.length()==0;
        if(n==0)return s2.equals(s3);
        if(m==0)return s1.equals(s3);   //也就是说要执行下面两个字符串必须有字符
		boolean[][] memo = new boolean[n+1][m+1];  //代表的是索引
		for(int i = 1;i<=n;i++){
			if(s1.substring(0,i).equals(s3.substring(0,i))){
				memo[i][0] = true;
			}
		}
		for(int j = 1;j<=m;j++){
			if(s2.substring(0,j).equals(s3.substring(0,j))){
				memo[0][j] = true;
			}
		}
		for(int i = 1;i<=n;i++){
			for(int j=1;j<=m;j++){
				memo[i][j] = ((s1.charAt(i-1)==s3.charAt(i+j-1)&&memo[i-1][j]) ||
						(s2.charAt(j-1)==s3.charAt(i+j-1)&&memo[i][j-1]));
			}
		}
		return memo[n][m];		
	}
}
class Solution_jimu0627{
	//求解过程，F(n,k)表示的是[0..n]按照顺序选出k个数后得到的结果最小
	//F(n,k) = F(n-1,k)                 //表示的是不包括arr[n]
	//       = F(n-1,k-1)*10 + arr[n]   //表示的包括arr[n]
	private int getMin(int[]arr,int n,int k){ //这里的n代表的是索引,
		if(k==0) return 0;  //表示的是不选任何数了
		if(n == k-1){         //这里要组成一个数，现在就剩下，arr[0..n].length = k,直接组装成一个整数
			int value=0;
			for(int i = 0;i<k;i++){
				value = (value + arr[i])*10;
			}
			value = value /10;
			return value;
		}
		int minValue = getMin(arr,n-1,k);   //这里不包含，arr[n],
		minValue = Math.min(minValue,getMin(arr,n-1,k-1)*10 + arr[n]); //包含arr[n]
		return minValue;
	}
	public  int getMinValue(int[] arr,int k){
		int n = arr.length;
		if(k>n)return 0;
		return getMin(arr,n-1,k);
		
	}
	/**
	  采用动态规划的方式进行
	memo[n][k],表示[0..n]中选择k个数，使得组成的数值最小
	memo[n][k] = Math.min(memo[n-1][k],memo[n-1][k-1]*10+arr[n][k])
	 */
	public int getMinValueDy(int[]arr,int k){
		int n = arr.length;
		int[][] memo = new int[n][k+1];
		for(int i = 0;i<n;i++){
			int tempMin = arr[0];
			for(int j = 0;j<=i;j++){  //进行初始化赋值
				if(tempMin>arr[j]){
					tempMin = arr[j];
				}
			}
			memo[i][1]=tempMin;
		}
		for(int i = 0;i<n;i++){
			for(int j =2;j<=k;j++){
				if(i+1<j)continue;
				if(i+1==j){
					int value = 0;
					for(int m = 0;m<j;m++){
						value = (value+arr[m])*10; 
					}
					value = value /10;
					memo[i][j] = value;
				}else{
					memo[i][j] = Math.min(memo[i-1][j],memo[i-1][j-1]*10+arr[i]);
				}
			}
		}
		return memo[n-1][k];
	}
}
public class abcbank_software {
	//f(n,m)表示的是s1字符串的索引n位置，s
	private boolean isConsist(String s1,String s2,String s3,int n,int m){
		if(n == -1){
			return s2.substring(0,m+1).equals(s3.substring(0,m+1));
		}
		if(m == -1){
			return s1.substring(0,n+1).equals(s3.substring(0,n+1));
		}
		if(s1.charAt(n)== s3.charAt(m+n+1)&& isConsist(s1,s2,s3,n-1,m)||
				s2.charAt(m) == s3.charAt(m+n+1)&& isConsist(s1,s2,s3,n,m-1)){
			return true;
		}
		return false;
	}
	public boolean isComponent(String s1,String s2,String s3){
        int n = s1.length();
        int m = s2.length();
        if(n+m != s3.length())return false;
        if(n==0&&m==0)return s3.length()==0;
        if(n==0)return s2.equals(s3);
        if(m==0)return s1.equals(s3);
        return isConsist(s1,s2,s3,n-1,m-1);
	}

	//这里我以后要用，动态规划解决问题，首先想到的是先进行初始化
	public boolean isComponentDy(String s1,String s2,String s3){
		int n = s1.length();
		int m = s2.length();
		if(s1.length()+s2.length() != s3.length())return false;
        if(n==0&&m==0)return s3.length()==0;
        if(n==0)return s2.equals(s3);
        if(m==0)return s1.equals(s3);   //也就是说要执行下面两个字符串必须有字符
		boolean[][] memo = new boolean[n+1][m+1];  //代表的是索引
		for(int i = 1;i<=n;i++){
			if(s1.substring(0,i).equals(s3.substring(0,i))){
				memo[i][0] = true;
			}
		}
		for(int j = 1;j<=m;j++){
			if(s2.substring(0,j).equals(s3.substring(0,j))){
				memo[0][j] = true;
			}
		}
		for(int i = 1;i<=n;i++){
			for(int j=1;j<=m;j++){
				memo[i][j] = ((s1.charAt(i-1)==s3.charAt(i+j-1)&&memo[i-1][j]) ||
						(s2.charAt(j-1)==s3.charAt(i+j-1)&&memo[i][j-1]));
			}
		}
		return memo[n][m];
	}
	public static void main(String[] args){
		abcbank_software s = new abcbank_software();
		String s1 = "abc";
		String s2 = "da";
		String s3 = "adbac";
//		System.out.println(s.isComponentDy(s1,s2,s3));
		System.out.println(s.isComponent(s1,s2,s3));  //true
		
		String s11 = "aabcc"; 
		String s22 = "dbbca";

        String s33 = "aadbbcbcac";   //return true;
	    String s43 = "aadbbbaccc";  // return false.
//	    System.out.println(s.isComponentDy(s11,s22,s33));
//	    System.out.println(s.isComponentDy(s11,s22,s43));
	    System.out.println(s.isComponent(s11,s22,s33));  //true
	    System.out.println(s.isComponent(s11,s22,s43));  //false
	    
	    
	    System.out.println("测试“搭积木”问题");
	    int[] arr = {2,9,1,4,3};
	    Solution_jimu0627 ss = new Solution_jimu0627();
	    System.out.println(ss.getMinValue(arr,2));
	    System.out.println(ss.getMinValueDy(arr,2));
	    

//	    1432219
	    int[] arr1 = {1,4,3,2,2,1,9};
	    System.out.println("1432219 remove 3:" + ss.getMinValue(arr1,4));
	    int[] arr2 = {1,0,2,0,0};
	    System.out.println("10200 remove 1:" + ss.getMinValue(arr2,4));
	    int[] arr3 = {1,0};
	    System.out.println("10 remove 2:"+ss.getMinValue(arr3,0));
	    
	    
	    System.out.println("0707");
	    Solution_ConsitsString0707 ss1 = new Solution_ConsitsString0707();
	    System.out.println(ss1.isConsist(s11,s22,s33));
	    System.out.println(ss1.isConsist(s11,s22,s43));
	    System.out.println(ss1.isConsist("db","b","cbb"));
	    System.out.println(ss1.isConsist("aa","ab","aaba"));
	    System.out.println(ss1.isConsistDy(s11,s22,s33));
	    System.out.println(ss1.isConsistDy(s11,s22,s43));
	    System.out.println(ss1.isConsistDy("db","b","cbb"));
	    System.out.println(ss1.isConsistDy("aa","ab","aaba"));
	}
}
