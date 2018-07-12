package com.leetcode.muke;

import java.util.Arrays;

/**
 * 01背包问题
 * 有一个背包，它的容量为C (Capacity)，。
 * 现在有n种不同的物品，编号为0...n-1，
 * 其中每一件物品的重量为w(i)，价值为v(i)。
 * 问可以向这个背包中盛放哪些物品，
 * 使得在不超过背包容量的基础上，物品的总价值最大。

 * @author weifeng
 *状态定义： F( n , C ) 考虑将n个物品放进容量为C的背包，使得价值最大
 *F ( i , c )  =   F( i-1 , c )                    第i个物品放不下了
               =    v(i) + F( i-1 , c - w(i) )     第i个物品可以放下

状态转移：F ( i , c )  =   
	max( F( i-1 , c ) , v(i) + F( i-1 , c - w(i) )



 *举个例子： 背包ID：0   1   2
 *	   weight: 1   2   3
 *      value: 6   10  12
 */
class Solution_BagProblem0712{
	//状态转移：F(C,n)表示将[0..n]为索引的物品填充容量为C的背包所获得最大的价值
	//这里有两种情况：1，如果包含该索引n，这里的条件是C>=w[n],F(C-w[n],n-1) + v[n]
	//             2, 如果不包含索引n,F(C,n-1)
	//             3,这两者取最大的一个
    //终止条件：C==0 return 0;
	//        n == -1 return 0;
	private int getMaxValue(int n,int c,int[]w,int[]v){
		int maxValue=0;
		if(c == 0)return 0;
		if(n == -1)return 0;
		if(c>=w[n]){
			maxValue = getMaxValue(n-1,c-w[n],w,v)+v[n];
		}
		maxValue = Math.max(maxValue,getMaxValue(n-1,c,w,v));
		return maxValue;
	}
	public int getMaxValue(int c,int[]w,int[] v){
		int n = w.length;
		return getMaxValue(n-1,c,w,v);
	}
	public int getMaxValueDy(int c,int[]w,int[]v){  //memo[j][i]表示[0..i-1]填充容量为j的背包所获得的最大的价值
		int n = w.length;
		int[][]memo = new int[c+1][n];
		for(int i = 0;i<n;i++){
			memo[0][i] = 0;
		}
		for(int j= 0;j<=c;j++){
			if(j>= w[0]){
				memo[j][0] =  v[0];     //将索引为0的物品填到
			}
		}
		for(int i = 1;i<n;i++){
			for(int j = 1;j<=c;j++){
				int maxValue = memo[j][i-1];
				if(j>=w[i]){
					maxValue = Math.max(maxValue,memo[j-w[i]][i-1]+v[i]);
				}
				memo[j][i] = maxValue;
			}
		}
		return memo[c][n-1];
	}
	public int getMaxValueDy2(int C,int[] w,int[] v){
		int n = w.length;
		int[] memo = new int[C+1]; //memo[i]表示的填充容量填充容量为i所获得的最大的价值
			//memo[i] memo[i-1],什么关系？
		for(int i = 1;i<=C;i++){    //先对memo[i]赋初值
			memo[i] = i>w[0]?v[0]:0;
		}
		for(int j = 1;j<n;j++){    //n个箱子
			for(int k = 1;k<=C;k++){  //容量为k
				if(k >= w[j])          //当前的权重能够放的下物品j
					memo[k] = Math.max(memo[k],v[j] + memo[k-w[j]]);
			}
		}
		return memo[C];
	}
}
class Solution_BagProblem0618{
	//背包问题：权重数组是w,价值数组是v,容量为C，求获取的最大的价值，尽量填满容量C
	//递归解决问题
	//f(n):表示[0-n]所获得的最大的价值
	//f(n) = f(n-1),
	//     = if(剩余容量-w[n]>0)表示还要剩余的容量，则，f(n-1) + v[n]
	//     = 
	public int getMaxValue(int[] w,int[] v,int C){
		int n = w.length;
		return getMaxValueR(w,v,n-1,C);
		
	}
	private int getMaxValueR(int[] w,int[] v,int n,int c){ 
		if(n == 0 ||c <= 0)return 0;
		int res = getMaxValueR(w,v,n-1,c);  //表示的是不填入n
		if(c-w[n] >= 0){                     //如果有容量可以放下n
			res = Math.max(res,v[n] + getMaxValueR(w,v,n-1,c-w[n]));
		}
		return res;
	}
	//动态规划
	public int getMaxValueDy(int[] w,int[] v,int C){
		int n = w.length;
		int[] memo = new int[C+1]; //memo[i]表示的填充容量填充容量为i所获得的最大的价值
									//memo[i] memo[i-1],什么关系？
		for(int i = 1;i<=C;i++){    //先对memo[i]赋初值
			memo[i] = i>w[0]?v[0]:0;
		}
		for(int j = 1;j<n;j++){    //n个箱子
			for(int k = 1;k<=C;k++){  //容量为k
				if(k >= w[j])          //当前的权重能够放的下物品j
				memo[k] = Math.max(memo[k],v[j] + memo[k-w[j]]);
			}
		}
		return memo[C];
	}
}
class Solution_BagProblem0518{
	//F(N,C):【0..n】个物品填容量为C的背包，所获得价值最大
	//=max(F(n-1,C),v(n) + F(n-1,C-w(i))
	public int getMaxValueDepth(int[] w ,int[] v,int c){
		int n = w.length;
		return getMaxDepth(w,v,c,n-1);//将索引为【0...n-1】n个物品放在容量为C的背包中
	}
	private int getMaxDepth(int[] w,int[] v,int c,int index){
		if(c<=0||index<0) return 0;
		int resValue = getMaxDepth(w,v,c,index-1);
		if(c>=w[index]){   //还有剩余的容量，c表示的就是容量
			resValue = Math.max(resValue,v[index] +getMaxDepth(w,v,c-w[index],index -1));
		}
		return resValue;
	}
	//使用动态规划的方式解决问题，
	//从底向上memo[j]：表示的是填容量为j所获得最大的价值
	public int getMaxValue(int[] weight,int[] value,int capacity){
		int[] memo = new int[capacity+1];   //memo[i]表示填入的背包的容量<=i,所获得的最大的价值
		
		int n = weight.length;
		for(int i = 1;i<capacity+1;i++){
			memo[i] = weight[0]<i?value[0]:0;   //初始化，如果能装下，就装下一号箱子，如果装不下，就装不下为0
		}
		for(int i = 1;i<n;i++){  //n个箱子
			for(int j = 1;j<capacity+1;j++){  //容量为capacity,容量j所获得的最大的权重
				if(j-weight[i]>=0){   //当前的容量能够放下物品i
					memo[j] = Math.max(memo[j],value[i]+memo[j-weight[i]]);
				}			
			}
		}
		return memo[capacity];
	}
}
public class M42_01knapsack {
	/**
	 * 使用记忆化搜索的方式进行
	 * @author weifeng
	 *
	 */
	public class Solution1{
		private int[][] memo;
		public int knapsack(int[] w,int[] v,int C){
			int n = w.length;
			memo = new int[n][C+1];
			Arrays.fill(memo,-1);
			return bestValue(w,v,n-1,C);
		}
		// 用 [0...index]的物品,填充容积为c的背包的最大价值
		//用[0...index]的物品，填充容积为C-w[index]的背包的最大的价值。
		private int bestValue(int[] w,int[] v,int index,int c){
			if( c<=0 || index <0)return 0;
			if(memo[index][c] != -1) return memo[index][c];
			int res = bestValue(w,v,index-1,c);  //放不下了，考略将【0.。index-1]个物品放进容量为C的
			if(c>= w[index]) //还有剩余的容量盛下当前的
				res = Math.max(res,v[index]+bestValue(w,v,index-1,c-w[index]));
			memo[index][c] =res;
			return res;
		}
	}
	/**
	 * 使用动态规划
	 */
	//memo[i]表示的是以容量i,所获得最大的价值
	public class Solution{
		public int knapsack(int[]w,int[]v,int c){
			int n = w.length;
			if(n == 0 || c==0) return 0;
			int [] memo = new int[c+1];  //容量C的值，是背包所能背的最大值
			for(int j = 0;j<=c;j++){    //初始化，背了第一个物品
				memo[j] = (j>=w[0]?v[0]:0);
			}
			for(int i = 1;i<n;i++){  //i代表了是物品，物品是一个一个的放
				for(int j = c;j>=w[i];j--){//j代表了是容量，memo[j] 代表了容量的最大值，
					memo[j] = Math.max(memo[j],v[i]+memo[j-w[i]]);
				}
			}
			return memo[c];
		}
	}
	public static void main(String[] args){
		int[] w = {1,2,3};
		int[] v = {6,10,12};
		M42_01knapsack ss = new M42_01knapsack();
		Solution s1 = ss.new Solution();
		System.out.println(s1.knapsack(w,v,5));
		Solution_BagProblem0518 s2 = new Solution_BagProblem0518();
		System.out.println(s2.getMaxValue(w,v,5));
		System.out.println(s2.getMaxValueDepth(w,v,5));
		System.out.println("sasa0618");
		
		Solution_BagProblem0618 sss = new Solution_BagProblem0618();
		System.out.println(sss.getMaxValue(w,v,5));
		System.out.println(sss.getMaxValue(w,v,5));
		
		System.out.println("sasa0712");
		Solution_BagProblem0712 ss2 = new Solution_BagProblem0712();
		System.out.println(ss2.getMaxValue(5,w,v));
		System.out.println(ss2.getMaxValueDy(5,w,v));
		System.out.println(ss2.getMaxValueDy2(5,w,v));
		
		
	}
}
