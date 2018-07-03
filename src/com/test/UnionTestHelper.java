package com.test;

import com.unionfind.UnionFind;
import com.unionfind.UnionFindI;

//测试并查集
public class UnionTestHelper {
	//测试第一版本的并查集，测试元素个数为n
	public static void testUF(int n){
		UnionFind uf = new UnionFind(n);
		long startTime = System.currentTimeMillis();
		
		//进行N次操作，每次随机选择两个元素进行合并操作
		for(int i = 0;i < n;i++){
			int a = (int)(Math.random() * n);
			int b = (int)(Math.random() * n);
			uf.unionElement(a,b);
		}
		//再进行n次操作，每次随机选择两个元素，查询他们是够属于同一个集合
		for(int i = 0;i<n;i++){
			
			int a = (int)(Math.random() * n);
			int b = (int)(Math.random() * n);
			uf.isConnected(a,b);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("UF," + 2*n + " ops ," + (endTime-startTime) + "ms");
			
	}
	//测试第一版本的并查集，测试元素个数为n
	public static void testUFI(int n){
		UnionFindI uf = new UnionFindI(n);
		long startTime = System.currentTimeMillis();
		
		//进行N次操作，每次随机选择两个元素进行合并操作
		for(int i = 0;i < n;i++){
			int a = (int)(Math.random() * n);
			int b = (int)(Math.random() * n);
			uf.unionElement(a,b);
		}
		//再进行n次操作，每次随机选择两个元素，查询他们是够属于同一个集合
		for(int i = 0;i<n;i++){
			
			int a = (int)(Math.random() * n);
			int b = (int)(Math.random() * n);
			uf.isConnected(a,b);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("UFI," + 2*n + " ops ," + (endTime-startTime) + "ms");
			
	}
	public static void main(String[] args){
		int n = 10000;
		testUF(n);
		testUFI(n);
	}
}
