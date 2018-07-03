package com.unionfind;

//第一版,普通的并查集，采用数组的形式
public class UnionFind {
	private int[] id;   //第一版的个数本质是一个数组
	private int count;  //数据的个数
	
	public UnionFind(int n){
		count = n;
		id = new int[n];
		//初始化，每一个ID指向自己，没有合并的元素
		for(int i = 0;i<n;i++){
			id[i] = i;
		}
	}
	//查找过程，查找元素p所对应的集合编号
	private int find(int p){
		assert p >= 0&&p < count;
		return id[p];
	}
	//查看元素p和q是否是一个集合，o(1)
	public boolean isConnected(int p,int q){
		return find(p) == find(q);
	}
	//合并元素p和q所属的集合o(n)
	public void unionElement(int p,int q){
		int pID = find(p);
		int qID = find(q);
		if(pID == qID){
			return;
		}
		//合并过程中需要遍历所有的元素，将两个元素所属的集合编号合并
		for(int i = 0;i<count;i++){
			if(id[i] == pID){
				id[i] = qID;
			}
		}
	}
	
}
















