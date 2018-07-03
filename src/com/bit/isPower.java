package com.bit;
/**
 * 判断一个数是否是2的n次方
 * @author weifeng
 *
 */
class SoulutionIsPower{
	public boolean isPower(int n){
		return (n&(n-1)) == 0;
	}
}
public class isPower {
	public static class Solution1{
		public static boolean isPower(int n){
			if(n<1) return false;
			int i = 1;
			while(i<=n){
				if(i==n)return true;
				i <<=1;
			}
			return false;
		}
	}
	public class Solution2{
		public boolean isPower(int n){
			if(n<1) return false;
			int m = n&(n-1);
			return m==0;   //因为最后一位是1
		}
	}
	public static void main(String args[]){
		isPower instance = new isPower();
		SoulutionIsPower ss = new SoulutionIsPower();
		System.out.println(ss.isPower(6));
	}
}
