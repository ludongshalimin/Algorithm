package com.graph;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

//广度优先遍历获得无权图的最短路径
public class ShortestPath {
	private Graph G;  //图
	private int  s;  //起始点
	private boolean[] visited;  //记录dfs过程中节点是否被访问过
	private int[] from;   //记录路径，from[i]表示查找路径上的的上一个节点
	private int[] ord;    //表示距离起始点的长度
	
	public ShortestPath(Graph graph,int s){
		//算法初始化
		G = graph;
		assert s >= 0 && s < G.V();
		visited = new boolean[G.V()];
		from = new int[G.V()];
		ord = new int[G.V()];
		
		for(int i = 0;i<G.V();i++){
			visited[i] = false;
			from[i] = -1;
			ord[i] = -1;
		}
		this.s = s;
		
		
		//无向图最短路径从s开始广度优先遍历整个路径
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.push(s);
		visited[s] = true;
		ord[s] = 0;
		while( !q.isEmpty()){
			int v = q.pop();
			for(int i :G.adj(v)){
				if(!visited[i]){
					q.push(i);
					visited[i] = true;
					from[i] = v;
					ord[i] = ord[v] + 1;
				}
			}
		}
	}
	public boolean hasPath(int w){
		assert w >= 0 && w < G.V();
		return visited[w];
	}
	//查询从s点到w点的路径,放在vec中
	public Vector<Integer> path(int w){
		assert hasPath(w);
		Stack<Integer> s = new Stack<Integer>();
		int p = w;
		while(p != -1){
			s.push(p);
			p = from[p];
		}
		Vector<Integer> res = new Vector<Integer>();
		while(!s.isEmpty()){
			res.add(s.pop());
		}
		return res;
	}
	//打印从s点到w点的路径
	public void showPath(int w){
		assert hasPath(w);
		Vector<Integer> vec = path(w);
		for(int i = 0;i<vec.size();i++){
			System.out.print(vec.elementAt(i));
			if(i == vec.size()-1){
				System.out.println();
			}else{
				System.out.print("-->");
			}
		}
	}
	//查看从s点到w点的最短路径的长度
	public int length(int w){
		assert w >= 0 && w < G.V();
		return ord[w];
	}
}
