package com.weightgraph;

import java.util.Vector;

//稠密图==邻接矩阵
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {
	private int n ;  //节点数
	private int m;    //边数
	private boolean directed;  //是否为有向图
	private Edge<Weight>[][] g;  //图的具体数据
	
	public DenseWeightedGraph(int n ,boolean directed){
		assert n >= 0;
		this.n = n;
		this.m = 0;
		this.directed = directed;
		g = new Edge[n][n];
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				g[i][j] = null;
			}
		}
	}
	//节点数
	@Override
	public int V() {
		// TODO Auto-generated method stub
		return n;
	}
	//边数
	@Override
	public int E() {
		// TODO Auto-generated method stub
		return m;
	}
	//向图中添加一个边
	@Override
	public void addEdge(Edge e) {
		// TODO Auto-generated method stub
		assert e.V() >= 0 && e.V() < n ;
		assert e.W() >= 0 && e.W() < n ;
		if(hasEdge(e.V(),e.W()))return ;
		g[e.V()][e.W()]= new Edge(e);
		if(e.V() != e.W() && !directed){
			g[e.W()][e.V()] = new Edge(e.W(),e.V(),e.wt());
		}
		m++;
	}
	//验证图中是否有从v到w的边
	@Override
	public boolean hasEdge(int v, int w) {
		// TODO Auto-generated method stub
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;
		return g[v][w] != null;
	}
	//显示图的信息
	@Override
	public void show() {
		// TODO Auto-generated method stub
		for(int i = 0 ;i < n;i++){
			for(int j = 0 ;j<n;j++){
				if(g[i][j] != null){
					System.out.print(g[i][j].wt() + "\t");
				}else{
					System.out.print("NULL\t");
				}
			}
			System.out.println();
		}
	}
	//返回图中一个顶点的所有的边
	@Override
	public Iterable adj(int v) {
		// TODO Auto-generated method stub
		assert v >= 0 &&v < n;
		Vector<Edge<Weight>> adjv = new Vector<Edge<Weight>>();
		for(int i = 0;i<n;i++){
			if(g[v][i] != null){
				adjv.add(g[v][i]);
			}
		}
		return adjv;
	}
}
