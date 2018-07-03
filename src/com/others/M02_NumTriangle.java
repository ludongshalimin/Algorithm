package com.others;

/***
 * 数字三角形问题
 * @author weifeng
 *问题：
　　给定一个由n行数字组成的数字三角形，如下图所示：
        　　    7 
      　　   3   8 
      　　 8   1   0 
   　　 2   7   4   4 
　　 4   5   2   6   5 
　　试设计一个算法，计算出从三角形的顶至底的一条路径，使该路径经过的数字总和最大（每一步只能从一个数走到下一层上和它最近的左边的数或者右边的数）。
输入：
　　第一行是数字三角形的行数，接下来 n 行是数字三角形中的数字。　　
　　比如：
　　5
　　7
　　3  8
　　8 1 0
　　2 7 4 4
　　4 5 2 6 5　　
输出：
　　输出这个最大值。
 */
/*
 * 假设F(i,j)表示：以arr[i][j]为结尾的最大的和最大的路径
 * 充分利用了三角形的索引关系
 * F(i,j) = Math.max(F(i-1,j)+arr[i][j],F(i-1,j-1)+arr[i][j])
 * 这里j<i+1,
 */
public class M02_NumTriangle {
	private int getSum(int[][]arr,int i,int j){ //表示的是要获取以索引i,j为arr[i][j]为结尾的最大的和
		if(i==0&&j==0)return arr[0][0];
		if(j>=i+1)return 0;
		return Math.max(i>0?getSum(arr,i-1,j)+arr[i][j]:0+arr[i][j],
				i>0&&j>0?getSum(arr,i-1,j-1)+arr[i][j]:0+arr[i][j]);
	}
	public int getMaxValue(int[][]arr){
		int n = arr.length;
		int maxValue = Integer.MIN_VALUE;
		for(int i=0;i<n;i++){
			maxValue = Math.max(maxValue,getSum(arr,n-1,i));
		}
		return maxValue;
	}
	//考略用动态规划做
	//memo[i][j] 表示索引i行，j列为结尾的最大的和
	//memo[i][j] = memo[i-1][j] + arr[i][j],
	//           = memo[i-1][j-1] + arr[i][j]
	//
	public int getMaxValueDy(int[][]arr){
		int n = arr.length;
		int[][] memo = new int[n][n];
		for(int i = 0;i<n;i++){        //将数值进行初始化，其实相当于复制了一个数组
			for(int j = 0;j<=i;j++){
				memo[i][j] = arr[i][j];
			}
		}
		for(int i = 0;i<n;i++){      //状态转移
			for(int j =0;j<=i;j++){
				memo[i][j] = Math.max(i>0?memo[i-1][j]+arr[i][j]:0+arr[i][j],
						i>0&&j>0?memo[i-1][j-1]+arr[i][j]:0+arr[i][j]);
			}
		}
		int maxValue = Integer.MIN_VALUE;  //在最后一行中求得最大的值
		for(int i =0;i<n;i++){
			maxValue = Math.max(maxValue,memo[n-1][i]);
		}
		return maxValue;
	}

	public static void main(String[] args){
		int[][] arr = {
				{7,0,0,0,0},
				{3,8,0,0,0},
				{8,1,0,0,0},
				{2,7,4,4,0},
				{4,5,2,6,5}};
		M02_NumTriangle ss = new M02_NumTriangle();
		System.out.println(ss.getMaxValue(arr));
		System.out.println(ss.getMaxValueDy(arr));
	}
}
