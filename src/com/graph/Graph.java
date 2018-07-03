package com.graph;
//图的接口
public interface Graph {
	public int V();//返回顶点的个数
	public int E();//返回边的个数
	public void addEdge(int v,int w); //添加边
	public boolean hasEdge(int v,int w); //判断两个顶点是否有边
	void show();
	public Iterable<Integer> adj(int v);  //返回相邻的节点
}
