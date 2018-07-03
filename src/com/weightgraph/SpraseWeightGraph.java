package com.weightgraph;

import java.util.Vector;

//稀疏图。邻接表
public class SpraseWeightGraph <Weight extends Number & Comparable> implements WeightedGraph {
	private int n; //节点数
	private int m; //边数
	private boolean directed;
	private Vector<Edge<Weight>>[] g;
	public SpraseWeightGraph(int n,boolean directed){
		assert n >= 0;
		this.n = n;
		this.m = 0;  //初始化没有边
		this.directed = directed;
		
		g = new Vector[n];
		for(int i = 0;i<n;i++){
			g[i] = new Vector<Edge<Weight>>();
		}
	}
	//返回节点个数
	@Override
	public int V() {
		// TODO Auto-generated method stub
		return n;
	}
	//返回图中边的总个数
	@Override
	public int E() {
		// TODO Auto-generated method stub
		return m;
	}
	@Override
	public void addEdge(Edge e) {
		// TODO Auto-generated method stub
		assert e.V() >= 0 && e.V() < n;
		assert e.W() >= 0 && e.W() < n;
		
		g[e.V()].add(new Edge(e));
		
		if(e.V() != e.W() && !directed){
			g[e.W()].add(new Edge(e.W(),e.V(),e.wt()));
		}
		m++;
	}
	@Override
	public boolean hasEdge(int v, int w) {
		// TODO Auto-generated method stub
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;
		for(int i = 0 ;i<g[v].size();i++){
			if(g[v].elementAt(i).other(v) == w){
				return true;
			}
		}
		return false;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		for(int i = 0;i<n;i++){
			System.out.print("vertex" + i + ":\t");
			for(int j = 0;j<g[i].size();j++){
				Edge e = g[i].elementAt(j);
				System.out.print("(to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
			}
			System.out.println();
		}
	}
	//返回图中一个顶点的所有的邻边
	@Override
	public Iterable <Edge<Weight>> adj(int v) {
		// TODO Auto-generated method stub
		assert v >= 0 && v < n;
		return g[v];
	}
}
