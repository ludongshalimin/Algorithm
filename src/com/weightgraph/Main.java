package com.weightgraph;

public class Main {
    // 测试通过文件读取图的信息
    public static void main(String[] args) {

        // 使用两种图的存储方式读取testG1.txt文件
//        String filename = "testG1.txt";
//        SpraseWeightGraph<Double> g1 = new SpraseWeightGraph<Double>(8, false);
//        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename);
//        System.out.println("CircleMin G1 in Sparse Weighted Graph:");
//        g1.show();
//
//        System.out.println();
//
//        DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<Double>(8, false);
//        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2 , filename );
//        System.out.println("CircleMin G1 in Dense Graph:");
//        g2.show();
//
//        System.out.println();
    	
    	
        String filename = "testGG1.txt";
        int V = 5;

        SpraseWeightGraph<Integer> g = new SpraseWeightGraph<Integer>(V, true);
        // Dijkstra最短路径算法同样适用于有向图
        //SparseGraph<int> g = SparseGraph<int>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Dijkstra:\n");
        Dijkstra<Integer> dij = new Dijkstra<Integer>(g,0);
        for( int i = 1 ; i < V ; i ++ ){
            if(dij.hasPathTo(i)) {
                System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
                dij.showPath(i);
            }
            else
                System.out.println("No Path to " + i );

            System.out.println("----------");
        }
    }
}
