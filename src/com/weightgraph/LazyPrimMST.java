package com.weightgraph;

import java.util.Vector;

import com.heap.MinHeap;

//使用prim算法求图中的最小生成树
public class LazyPrimMST <Weight extends Number & Comparable> {
	private WeightedGraph<Weight> G;   //图的引用
	private MinHeap<Edge<Weight>> pq;   //最小堆，算法辅助数据结构
	private boolean[] marked;       //标记数组，在算法运行中标记节点 i是否被访问
	private Vector<Edge<Weight>> mst;   //最小生成树所包含的边
	private Number mstWeight;       //最小生成树的权值
	
	private void visit(int v){
		assert !marked[v];
		marked[v] = true;
		//将和节点v相连接的所有未访问的边放入最小堆中
		for(Edge<Weight> e:G.adj(v)){     //返回图中一个顶点的所有的边
			if( !marked[e.other(v)]){    //e.other(v)给定一个顶点返回另一个顶点
				pq.insert(e);
			}
		}
	}
	public LazyPrimMST(WeightedGraph<Weight> graph){
		G = graph;
		pq = new MinHeap<Edge<Weight>>(G.E());   //这里的G.E()是边数
		marked = new boolean[G.V()];            //这里的G.V()是节点数
		mst = new Vector<Edge<Weight>>();     //这里存放的是挑选出来的边数
		
		visit(0);
		while( !pq.isEmpty()){
			//使用最小堆找出已经访问的边中权值最小的边
			Edge<Weight> e = pq.extractMin();
			//如果这条边的两端都已经访问过了，则扔掉这个边
			if(marked[e.V()] == marked[e.W()])continue;   //e.v()是边的一个顶点，g.v()是图的顶点总数
			//否则这条边应该放在最小生成树中
			mst.add(e);
			//访问和这条边连接的还没有被访问过的节点
			if( !marked[e.V()]){
				visit(e.V());
			}else{
				visit(e.W());
			}
		}
		//计算最小生成树的权值
		mstWeight = mst.elementAt(0).wt();
		for(int i = 1 ;i<mst.size();i++){
			mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
		}
		
	}
	//返回最下生成树的所有的边
	Vector<Edge<Weight>> mstEdges(){
		return mst;
	}
	//返回最小生成树的权值
	Number result(){
		return mstWeight;
	}
	
	//测试lazy prim
	public static void main(String[] args){
        String filename = "testG1.txt";
        int V = 8;

        SpraseWeightGraph<Double> g = new SpraseWeightGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
	}
	
}
