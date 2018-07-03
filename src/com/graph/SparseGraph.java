package com.graph;

import java.util.Vector;

//稀疏图--邻接表的实现
public class SparseGraph implements Graph{
	private int n; //节点个数
	private int m; //边的个数
	private boolean directed; //是否是有向图
	private Vector<Integer>[] g;  //图的具体数据
	
	//构造函数
	public SparseGraph(int n,boolean directed){
		assert n >= 0;
		this.n = n;
		this.m = 0;
		this.directed = directed;
		g = new Vector[n];
		
		for(int i = 0;i<n;i++){
			g[i] = new Vector<Integer>();
		}
	}
	@Override
	public int V(){
		return n;  //返回节点的个数
	}
	@Override
	public int E(){
		return m;  //返回边的个数
	}
	//向图中添加一个边，使得v和w相连
	@Override
	public void addEdge(int v,int w){
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;
		
		g[v].add(w);
		if(v != w && !directed){   //不是有向图
			g[w].add(v);
		}
		m ++;
	}
	//验证途中是否有从V到W的边
	@Override
	public boolean hasEdge(int v,int w){
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;
		for(int i = 0;i<g[v].size();i++){
			if(g[v].elementAt(i) == w){
				return true;
			}
		}
		return false;
	}
	
	//返回图中一个顶点的所有邻边
	@Override
	public Iterable<Integer> adj(int v){
		assert v >= 0 && v < n;
		return g[v];
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		for(int i = 0;i<n;i++){
			System.out.println("vertex" + i + ":\t");
			for(int j = 0;j<g[i].size();j++){
				System.out.print(g[i].elementAt(j) + "\t");
			}
			System.out.println();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
