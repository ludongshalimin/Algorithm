package com.graph;
//求图中的联通分量
public class Components {
	Graph G;   //图的引用
	private boolean[] visited;  //记录dfs过程中节点是否被访问
	private int ccount;   //记录联通分量个数
	private int[] id;     //每个节点所对应的联通分量标记
	//图的深度优先遍历
	void dfs(int v){
		visited[v] = true;
		id[v] = ccount;
		for(int i:G.adj(v)){
			if(!visited[i]){
				dfs(i);
			}
		}
	}
	//构造函数，求出图的联通分量
	public Components(Graph graph){
		//初始化
		G = graph;
		visited = new boolean[G.V()];   //G.V()返回的是顶点的个数
		id = new int[G.V()];
		ccount = 0;
		for(int i = 0;i<G.V();i++){
			visited[i] = false;
			id[i] = -1;
		}
		//求图的联通分量
		for(int i = 0;i<G.V();i++){
			if(!visited[i]){
				dfs(i);
				ccount++;
			}
		}
	}
	int count(){
		return ccount;
	}
	boolean isConnected(int v,int w){
		assert v >= 0 && v < G.V();
		assert w >= 0 && w < G.V();
		return id[v] == id[w];
	}
}
