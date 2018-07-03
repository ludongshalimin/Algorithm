package com.bit;
/**
 * 二进数中1的个数
 * @author weifeng
 *
 */
class Solution_CountOne{
	public int countOne(int num){
		int count = 0;
		while(num!=0){
			count++;
			num = num&(num-1);
		}
		return count;
	}
}
public class countOne {
	public class Solution1{
		public int countOne(int n){
			int count = 0;
			while(n>0){
				if((n&1) == 1)count ++;
				count >>=1;
			}
			return count;
		}
	}
	public class Solution2{
		public int countOne(int n){
			int count = 0;
			while(n>0){
				n = n&(n-1);
				count ++;
			}
			return count;
		}
	}
	public static void main(String[] args){
		Solution_CountOne ss = new Solution_CountOne();
		System.out.println(ss.countOne(5));
	}
}
