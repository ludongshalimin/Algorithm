package com.leetcode.muke;

import java.util.Arrays;

/**
 * 
 * 最长公共子序列LCS问题
 * Longest common squence
 * 两个字符串对应的Index相同的部分一致，才算是相似的公共子序列
 * 给出两个字符串S1和S2，求这两个字符串的最长公共子序列的长度
S1 = AAACCGTGAGTTATTCGTTCTAGAA
S2 = CACCCCTAAGGTACCTTTGGTTC


S1 = ABCD
S2 = AEBD

 * @author weifeng
 * 
 *自顶向下的解决问题
 * 定义：LCS(m,n) = s1[0...m] 和s2[0...n]的最长公共子序列的长度
 * 1，s1[m] == s2[n]
 * 	  LCS(m,n) = 1 + LCS(m-1,n-1)
 * 2,s1[m] != s2[n]
 * 	LCS(m,n) = max(LCS(m-1,n),LCS(m,n-1))
 * 
 * 其中的意思表示的是如果缩一缩的话，如果还是相等的就可以算作一个相等的东西
 */
//采用递归的方法进行求解最长公共子序列，然后求出最长公共子序列的长度,
//同时获取最长的公共子序列
//目前0713这个是非常正确的
class Solution_LongestCommonSequence0713{
	private int[][] memo;  //递归+采用记忆化搜索
	private int getLength(String s1,String s2,int n,int m){  //这里的n,m代表了索引
		if(n == -1 || m == -1)return 0;
		if(memo[n][m] != -1) return memo[n][m];
		int maxLength = 0;
		if(s1.charAt(n) == s2.charAt(m)){
			maxLength = getLength(s1,s2,n-1,m-1) + 1;
		}else{
//			maxLength = Math.max(maxLength,getLength(s1,s2,n-1,m));
			maxLength = Math.max(getLength(s1,s2,n-1,m),getLength(s1,s2,n,m-1));
		}
		memo[n][m] = maxLength;
		return maxLength;
		
	}
	public int getMaxLength(String s1,String s2){
		int n = s1.length();
		int m = s2.length();
		memo = new int[n][m];
		for(int i = 0;i<n;i++){
			Arrays.fill(memo[i],-1);
		}
		return getLength(s1,s2,n-1,m-1);	
	}
	public int getmaxLengthDy(String s1,String s2){
		int n = s1.length();
		int m = s2.length();
		if(n == 0||m == 0)return 0;
		int[][] memo = new int[n][m]; //memo[i][j]表示，[0..i]s1,[0..j]s2的最大的长度
		for(int i = 0;i<n;i++){
			if(s1.charAt(i) == s2.charAt(0)||(i>0&&memo[i][0] ==1)){
				memo[i][0] = 1;
			}
		}
		for(int j = 0;j<m;j++){
			if(s2.charAt(0) == s1.charAt(0) || (j>0&&memo[0][j] == 1)){
				memo[0][j] = 1;
			}
		}
		for(int i = 1;i<n;i++){
			for(int j = 1;j<m;j++){
				int maxlength = 0;
				if(s1.charAt(i) == s2.charAt(j)){
					maxlength = memo[i-1][j-1] + 1;
				}else{
//					maxlength = Math.max(maxlength,memo[i-1][j]);  //这里如果不相等的话，只能是小
					maxlength = Math.max(memo[i-1][j],memo[i][j-1]);
				}
				memo[i][j] = maxlength;
			}
		}
		return memo[n-1][m-1];
	}
	//这里要打印最大的长度字符串
	public String printLCS(String s1,String s2){
		int n = s1.length()-1;
		int m = s2.length()-1;
		int maxlength = getMaxLength(s1,s2);  //得到最大的
		StringBuilder ss = new StringBuilder();
		while(n>=0&&m>=0){
			if(s1.charAt(n) == s2.charAt(m)){
//				ss.append(s1.charAt(n));
				ss.insert(0,s1.charAt(n));
				n--;
				m--;
			}else if(n==0){
				m--;
			}else if(m==0){
				n--;
			}else{
				if(memo[n-1][m]>memo[n][m-1]){
					n--;
				}else{
					m--;
				}
			}
		}
		return ss.toString();
	}
}
class Solution_LongestCommonSquence0623{
	private int[][]memo;
	private int getMaxLen(String s1,String s2,int n,int m){//s1[0..n],s2[0...m]，所获得最长的长度
		if(n <0||m<0)return 0;
		int maxlen =0;
		if(memo[n][m]!=-1)return memo[n][m];
		if(s1.charAt(n) == s2.charAt(m)){
			maxlen = getMaxLen(s1,s2,n-1,m-1)+1;
		}else{
			maxlen = Math.max(getMaxLen(s1,s2,n-1,m),getMaxLen(s1,s2,n,m-1));
		}
		memo[n][m] = maxlen;
		return maxlen;
	}
	public int getMaxLen(String s1,String s2){
		int n = s1.length();
		int m = s2.length();
		if(n==0||m==0)return 0;
		memo = new int[n][m];
		for(int i = 0;i<n;i++){
			Arrays.fill(memo[i],-1);
		}
		return getMaxLen(s1,s2,n-1,m-1);
		
	}//通过执行获得了公共的子长度,因为我是自上而下解决的
	public String getCommonString(String s1,String s2){
		int maxlen = memo[s1.length()-1][s2.length()-1];
		StringBuilder sb = new StringBuilder("");
		int i = s1.length()-1;
		int j = s2.length()-1;
		while(i>=0&&j>=0){
			if(s1.charAt(i)==s2.charAt(j)){
//				sb.append(s1.charAt(i));//append是追加在后面
				sb.insert(0,s1.charAt(i)); //insert是插入到具体的位置
				i--;
				j--;
			}else if(i==0){
				j--;
			}else if(j==0){
				i--;
			}else{
				if(memo[i-1][j]>memo[i][j-1]){//现在要去掉一个字符，是去s1还是去掉s2
					i--;
				}else{
					j--;
				}
			}
		}
		return sb.toString();
	}
	public int getMaxLenDy(String s1,String s2){
		int n = s1.length();
		int m = s2.length();
		int[][] memo1 = new int[n][m];  //这里的memo[n][m]表示的s1[0..n]s2[0..m]所获得最大的长度
		for(int i =0;i<n;i++){
			if(s1.charAt(i) == s2.charAt(0)){
				memo1[i][0]=1;
			}
		}
		for(int j =0;j<m;j++){
			if(s1.charAt(0)==s2.charAt(j)){
				memo1[0][j] = 1;
			}
		}
		for(int i =1;i<n;i++){
			for(int j =1;j<m;j++){
				if(s1.charAt(i)==s2.charAt(j)){
					memo1[i][j] = memo1[i-1][j-1] + 1;
				}else{
					memo1[i][j] = Math.max(memo1[i][j-1],memo1[i-1][j]);
				}
			}
		}
		return memo[n-1][m-1];
	}
}
public class M45_LongestCommonSquence {
	public class Solution1{        //递归来做
		private int[][] memo;
		public int getMaxlength(){
			int m = memo.length;
			int n = memo[0].length;
			return memo[m-1][n-1];
		}
		public String lcs(String s1,String s2){
			if(s1.length()==0||s2.length()==0){
				return "";
			}
			int m= s1.length();
			int n = s2.length();
			memo = new int[m][n];  //m行,n列
			for(int i = 0;i<m;i++){
				Arrays.fill(memo[i],-1);  //针对的是一行数据，fill的是一维的数据
			}
			lcs(s1,s2,m-1,n-1);
			return getLCS(s1,s2);
		}
		//求s1[0..m] 和s2[0..n]的最长公共子序列的长度值
		private int lcs(String s1,String s2,int m,int n){
			if(m<0 || n<0){
				return 0;   
			}
			if(memo[m][n] != -1)return memo[m][n];  //已经计算过了，则直接返回计算过的值
			int res = 0;
			if(s1.charAt(m) == s2.charAt(n)){ //当前索引的字符相等，则相减一个进行递归的
				res = 1+lcs(s1,s2,m-1,n-1);
			}else{                            //当前的字符不相等，则一个字符串减掉一个，另一个字符串不变进行遍历
				res = Math.max(lcs(s1,s2,m-1,n),
						lcs(s1,s2,m,n-1));
			}
			memo[m][n] = res;
			return res;
		}
		//通过memo反向求解s1和s2的最长公共子序列
		private String getLCS(String s1,String s2){
			int m = s1.length() -1;
			int n = s2.length() -1;
			StringBuilder res = new StringBuilder("");
			while(m >=0 && n>= 0){
				if(s1.charAt(m) == s2.charAt(n)){
					res = res.insert(0,s1.charAt(m));
					m--;
					n--;
				}else if(m == 0){
					n--;
				}else if(n == 0){
					m--;
				}else{
					if(memo[m-1][n] > memo[m][n-1]){ //m-1长度的字符串，n长度的字符串的
						m--;
					}else{
						n--;
					}
				}
			}
			return res.toString();
		}
	}
	public class Solution2{   //动态规划来做
		public String lcs(String s1,String s2){
			int m = s1.length();
			int n = s2.length();
			//对memo的第0行第0列进行初始化
			int [][] memo = new int[m][n];
			for(int i = 0;i<m;i++){             //对于矩阵进行初始化，m
				if(s1.charAt(i) == s2.charAt(0)){
					memo[i][0] = 1;
				}
			}
			for(int j = 0;j<n;j++){     //对于矩阵进行初始化，n
				if(s1.charAt(0) == s2.charAt(j)){
					memo[0][j] = 1;
				}
			}

			//动态规划的过程，m[i,j]表示s1[0,i],s2[0,j]上的字符相同的最大长度
			for(int i = 1;i<m;i++){
				for(int j = 1;j<n;j++){
					if(s1.charAt(i) == s2.charAt(j)){
						memo[i][j] = 1+memo[i-1][j-1];
					}else{
						memo[i][j] = Math.max(memo[i-1][j],memo[i][j-1]);
					}
				}
			}
			//获取字符串
			//通过memo反向求解s1和s2的最长公共子序列
			 m = s1.length() -1;
			 n = s2.length() -1;
			 StringBuilder res = new StringBuilder();
			 while(m >= 0 && n >= 0){
				 if(s1.charAt(m) == s2.charAt(n)){
					 res.insert(0,s1.charAt(m));
					 m--;
					 n--;
				 }
				 else if(m ==0){
					 n--;
				 }else if(n == 0){
					 m --;
				 }else{
					 if(memo[m-1][n] > memo[m][n-1]){
						 m--;
					 }else{
						 n--;
					 }
				 }
			 }
			 return res.toString();
		}
	}
    public static void main(String[] args) {

        String s1 = "ABCDGH";
        String s2 = "AEDFHR";
        M45_LongestCommonSquence instance = new M45_LongestCommonSquence();
        Solution1 solution1= instance.new Solution1();
        System.out.println(solution1.lcs(s1, s2) +"   "+ solution1.getMaxlength());       
        s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
        s2 = "CACCCCTAAGGTACCTTTGGTTC";
        System.out.println(solution1.lcs(s1, s2)+"    " + solution1.getMaxlength());   
        
        
        s1 = "ABCDGH";
        s2 = "AEDFHR";
        M45_LongestCommonSquence instance2 = new M45_LongestCommonSquence();
        Solution2 solution2= instance2.new Solution2();
        System.out.println(solution2.lcs(s1, s2) +"   ");
        
        s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
        s2 = "CACCCCTAAGGTACCTTTGGTTC";
        System.out.println(solution2.lcs(s1, s2)+"    " );
        
        
        System.out.println("0623");
        Solution_LongestCommonSquence0623 ss = new Solution_LongestCommonSquence0623();
        s1 = "ABCDGH";
        s2 = "AEDFHR";
        System.out.println(ss.getMaxLen(s1,s2));
        System.out.println(ss.getMaxLenDy(s1,s2));
        System.out.println("common string " + ss.getCommonString(s1,s2));
        s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
        s2 = "CACCCCTAAGGTACCTTTGGTTC";
        System.out.println(ss.getMaxLen(s1,s2));
        System.out.println(ss.getMaxLenDy(s1,s2));
        System.out.println("common string " +ss.getCommonString(s1,s2));
        
        System.out.println("0713");
        Solution_LongestCommonSequence0713 sss = new Solution_LongestCommonSequence0713();
        s1 = "ABCDGH";
        s2 = "AEDFHR";
        System.out.println(sss.getMaxLength(s1,s2));
        System.out.println(sss.getmaxLengthDy(s1,s2));
        System.out.println("common string: " + sss.printLCS(s1,s2));
        s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
        s2 = "CACCCCTAAGGTACCTTTGGTTC";
        System.out.println(sss.getMaxLength(s1,s2));
        System.out.println(sss.getmaxLengthDy(s1,s2));
        System.out.println("common string: " +sss.printLCS(s1,s2));
   
    }
}
