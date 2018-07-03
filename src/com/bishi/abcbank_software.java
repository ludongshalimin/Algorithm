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
	public boolean isComponent(String s1,String s2,String s3,int n,int m){ //这里的n,m代表的是具体的索引
		//如何处理极端的情况。一个字符串已经完全完了，另一个字符串还有内容
		if(n==-1 || m==-1)return true;   //这是递归后的解决方法
		if((s1.charAt(n) == s3.charAt(n+m+1)&& isComponent(s1,s2,s3,n-1,m)) ||
			(s2.charAt(m) == s3.charAt(n+m+1)&& isComponent(s1,s2,s3,n,m-1))){
				return true;
		}
		return false;
	}
	public boolean isComponent(String s1,String s2,String s3){
		int n = s1.length();
		int m = s2.length();
		if(n+m != s3.length())return false;
		return isComponent(s1,s2,s3,n-1,m-1);
	}
	boolean[][] matched ;

	//这里我以后要用，动态规划解决问题，首先想到的是先进行初始化
	public boolean isComponentDy(String s1,String s2,String s3){
		if(s1.length()+s2.length() != s3.length())return false;
		matched = new boolean[s1.length()+1][s2.length()+1];
		for(int i = 1;i<=s1.length();i++){
			if(s1.charAt(i-1) == s3.charAt(i-1)){
				matched[i][0] = true;
			}
		}
		for(int j = 1;j<=s2.length();j++){
			if(s2.charAt(j-1) == s3.charAt(j-1)){
				matched[0][j] = true;
			}
		}
		for(int i = 1;i<= s1.length();i++){
			for(int j = 1;j<=s2.length();j++){
//				if(i==0&&j==0){
//					matched[i][j] = true;
//				}else if(i == 0){
//					matched[i][j] =(matched[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1));
//				}else if(j ==0){
//					matched[i][j] = (matched[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1));
//				}else{
					matched[i][j] = (matched[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1) ||
							matched[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1));  //就是这么一句话的事，考场上倒是想到了，但是就是没有答上来
//				}
			}
		}
		return matched[s1.length()][s2.length()];
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
	}
}
