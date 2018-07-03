package com.unionfind;
//采用基于路径压缩的优化
//rank[i]表示以i为根的集合所表示的树的层数
public class UnionFindI {
	private int[] rank;
	private int[] parent;
	private int count;
	
	//构造函数
	public UnionFindI(int count){
		rank = new int[count];
		parent = new int[count];
		this.count = count;
		//初始化，每一个parent[i]指向自己，表示每一个元素自己自成一个集合
		for(int i = 0;i<count;i++){
			parent[i] = i;
			rank[i] = 1;
		}
	}
	//查找过程，查找元素P所对应的集合编号
	private int find(int p){
		assert(p >= 0 && p < count);
		//路径压缩1
//		while(p != parent[p]){
//			parent[p] = parent[parent[p]];
//			p = parent[p];
//		}
//		return p;
		//路径压缩2
		if(p != parent[p]){
			parent[p] = find(parent[p]);
		}
		return parent[p];
	}
	
	public boolean isConnected(int p,int q){
		return find(p) == find(q);
	}
	
	public void unionElement(int p,int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot){
			return ;
		}
		//根据两个元素所在树的个数不同判断合并的方向
		//将元素个数少的合并到元素个数多的集合中
		if(rank[pRoot] < rank[qRoot]){
			parent[pRoot] = qRoot;
		}else if(rank[qRoot] < rank[pRoot]){
			parent[qRoot] = pRoot;
		}else{
			parent[pRoot]=qRoot;
			rank[qRoot] += 1;
		}
	}
	
}
