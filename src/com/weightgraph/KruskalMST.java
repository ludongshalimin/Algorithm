package com.weightgraph;

import java.util.Vector;

import com.heap.MinHeap;
import com.unionfind.UnionFind;

//krustal算法求最小生成树
public class KruskalMST <Weight extends Number & Comparable> {
	private Vector<Edge<Weight>> mst;  //最小生成树所包含的边
	private Number mstWeight;   //最小生成树的权值
	
	//构造函数，使用kruskal算法计算graph的最小生成树
	public KruskalMST(WeightedGraph graph){
		mst = new Vector<Edge<Weight>>();
		//将图中的所有的边放入到最小堆中
		MinHeap<Edge<Weight>> pq = new MinHeap<Edge<Weight>>(graph.E());
		for(int i = 0;i < graph.V();i++){
			for(Object item:graph.adj(i)){
				Edge<Weight> e = (Edge<Weight>)item;
				if(e.V() <= e.W()){    //为什么要写小于呢？？？最小生成树是针对无向图来做的，为了防止重复添加
					pq.insert(e);
				}
			}
		}
		//创建并查集，来查看已经访问的节点的联通的情况
		UnionFind uf = new UnionFind(graph.V());
		while(!pq.isEmpty() && mst.size() < graph.V()-1){
			//从最小堆中依次从小到大取出所有的边
			Edge<Weight> e = pq.extractMin();
			//如果该边的两个端点是联通的，说明加入这条边将产生环，扔掉这个边
			if( uf.isConnected(e.V(),e.W()))continue;
			//否则将这两条边添加进最小生成树，同时标记边的两个端点联通
			mst.add(e);
			uf.unionElement(e.V(),e.W());
		}
		//计算最小生成树的权值
		mstWeight = mst.elementAt(0).wt();
		for(int i = 1;i < mst.size();i++){
			mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
		}
	}
	//返回最小生成树的所有边
	Vector<Edge<Weight>> mstEdges(){
		return mst;
	}
	//返回最小生成树的权值
	Number result(){
		return mstWeight;
	}
	
	//测试Kruskal
	public static void main(String[] args){

        String filename = "testG1.txt";
        int V = 8;

        SpraseWeightGraph<Double> g = new SpraseWeightGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
	}
	
	
	
	
	
	
	
	
}
