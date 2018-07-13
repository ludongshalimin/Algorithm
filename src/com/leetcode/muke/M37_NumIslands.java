package com.leetcode.muke;

import java.util.Arrays;

/**
 * 给定一个二维数组，只含有0和1两个字符。
 * 其中1代表陆地，0代表水域。横向和纵向的陆地连接成岛屿，被水域分隔开。
 * 问给出的地图中有多少岛屿？

 * @author weifeng
 * flood fill问题
 *
 */
//深度优先遍历的问题还是有些问题
//屏蔽的是我的错误的答案
//class Solution_NumIsland_0713II{
//	private boolean[][] visit;
//	private boolean inArea(char[][] arr,int i,int j){
//		int n = arr.length;
//		int m = arr[0].length;
//		return i>=0&& i<n&&j>=0&&j<m;
//	}
//	private boolean dfs(char[][] arr,int i,int j){  //从arr[i][j]
//		int[][] pos = {{1,0},{0,1},{0,-1},{-1,0}};  //两个前向的方向
//		if(visit[i][j]) return true;
//		visit[i][j] = true;  //表示访问过了arr[i][j]的位置,这里要设置三种状态，-1表示没有访问过，0表示是海洋，1表示是陆地
//		for(int k = 0;k<4;k++){
//			int new_x = i+pos[k][0];
//			int new_y = j+pos[k][1];
//			if(inArea(arr,new_x,new_y) && arr[new_x][new_y] == '1'&&!visit[new_x][new_y]){ //新位置还是一个岛屿，且与arr[i][j]相连接
//				return dfs(arr,new_x,new_y);
//			}
//		}
//		return false;
//	}
//	public int islandCount(char[][] arr){
//		int n = arr.length;
//		int m = arr[0].length;
//		visit = new boolean[n][m];
//		int count = 0;   //孤岛的数量
//		for(int i = 0;i<n;i++){
//			for(int j = 0;j<m;j++){
//				if(arr[i][j]== '1'&&!dfs(arr,i,j)){  //当前是陆地，并且dfs去尽量的标记陆地，如果最后实在没有陆地可以标记了，就返回falsess
//					count ++;
//				}
//			}
//		}
//		return count;
//	}
//}
class Solution_NumIsland_0713{
	private boolean[][] visit;
	private boolean inArea(char[][] arr,int i,int j){
		int n = arr.length;
		int m = arr[0].length;
		return i>=0&& i<n&&j>=0&&j<m;
	}
	private void dfs(char[][] arr,int i,int j){  //从arr[i][j]
		int[][] pos = {{1,0},{0,1},{0,-1},{-1,0}};  //两个前向的方向
		visit[i][j] = true;  //表示访问过了arr[i][j]的位置,这里要设置三种状态，-1表示没有访问过，0表示是海洋，1表示是陆地
		for(int k = 0;k<4;k++){
			int new_x = i+pos[k][0];
			int new_y = j+pos[k][1];
			if(inArea(arr,new_x,new_y) && arr[new_x][new_y] == '1'&&!visit[new_x][new_y]){ //新位置还是一个岛屿，且与arr[i][j]相连接
				dfs(arr,new_x,new_y);
			}
		}
	}
	public int islandCount(char[][] arr){
		int n = arr.length;
		int m = arr[0].length;
		visit = new boolean[n][m];
		int count = 0;   //孤岛的数量
		for(int i = 0;i<n;i++){
			for(int j = 0;j<m;j++){
				if(arr[i][j]== '1'&&!visit[i][j]){  //当前是陆地，并且dfs去尽量的标记陆地，如果最后实在没有陆地可以标记了，就返回false
					dfs(arr,i,j);   
					count ++;
				}
			}
		}
		return count;
	}
}
class Solution_NumIslands{
	boolean[][]visit;
	private boolean isArea(char[][]arr,int i,int j){
		int n = arr.length;
		int m = arr[0].length;
		if(i>=0&&i<n&&j>=0&&j<m){  //在界内表示没有越界
			return true;
		}
		return false;
	}                                         //深度优先的目的是什么
	private void dfs(char[][]arr,int i,int j){ //表示的以[i,j]为索引深度优先遍历
		int[][] pos = {{0,1},{1,0},{0,-1},{-1,0}};  //设置4个方向
		visit[i][j] = true;     //标志已经被访问过
		for(int k = 0;k<4;k++){
			int new_x = i+pos[k][0];
			int new_y = j+pos[k][1];
			if(isArea(arr,new_x,new_y)&&arr[new_x][new_y]=='1'&& visit[new_x][new_y]==false){   //如果再界内
				dfs(arr,new_x,new_y);
			}
		}
	}
	public int getNumIslands(char[][]arr){
		int n = arr.length;
		int m = arr[0].length; 
		int num = 0;     //有多少个岛
		visit = new boolean[n][m];
		for(int i = 0;i<n;i++){
			Arrays.fill(visit[i],false);
		}
		for(int i = 0;i<n;i++){
			for(int j = 0;j<m;j++){
				if(arr[i][j] == '1' &&visit[i][j] == false){
					dfs(arr,i,j);  //如果当前是陆地，并且还没有被访问过
					num++;
				}
			}
		}
		return num;
	}
}
public class M37_NumIslands {
	private int d[][] = {{0,1},{1,0},{0,-1},{-1,0}}; //定义四个方向
	private int m,n;
	private boolean visited[][];
	public int numIslands(char[][] grid){
		m = grid.length;   //m行
		n = grid[0].length;    //n列
		visited = new boolean[m][n];
		int res = 0;
		for(int i = 0;i<m;i++){  //m行
			for (int j= 0;j<n;j++){  //n列
				if(grid[i][j] =='1'&&!visited[i][j]){   //如果当前是陆地，并且还没有被访问则继续遍历
					dfs(grid,i,j);   //相连接的陆地进行标记，flood fill
					res++;
				}
			}
		}
		return res;
	}
	 // 从grid[x][y]的位置开始,进行floodfill
    // 保证(x,y)合法,且grid[x][y]是没有被访问过的陆地
    private void dfs(char[][] grid, int x, int y){
    	visited[x][y] = true;
    	for(int i = 0;i<4;i++){   //向四个方向
    		int newx = x+d[i][0];
    		int newy = y+d[i][1];
            if(inArea(newx, newy) && !visited[newx][newy] && grid[newx][newy] == '1')
                dfs(grid, newx, newy);    //找到了新的，和x,y相连接的区域，接着进行标记
    	}
    	return;
    }
    private boolean inArea(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {

        char grid1[][] = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println((new M37_NumIslands()).numIslands(grid1));
        // 1

        // ---

        char grid2[][] = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println((new M37_NumIslands()).numIslands(grid2));
        
        // 3
        System.out.println("0701");
        Solution_NumIslands ss = new Solution_NumIslands();
        System.out.println(ss.getNumIslands(grid1));
        System.out.println(ss.getNumIslands(grid2));
        
        char grid3[][] = {
        		{'1'},
        		{'1'}
        };
        char grid4[][] ={
        		{'1','1','1'},
        		{'0','1','0'},
        		{'1','1','1'}
        };
        System.out.println("0713");
        Solution_NumIsland_0713 sss = new Solution_NumIsland_0713();
        System.out.println(sss.islandCount(grid1));
        System.out.println(sss.islandCount(grid2));
        System.out.println(sss.islandCount(grid3));
        System.out.println(sss.islandCount(grid4));
//        Solution_NumIsland_0713II ssss = new Solution_NumIsland_0713II();
//        System.out.println(ssss.islandCount(grid1));
//        System.out.println(ssss.islandCount(grid2));
//        System.out.println(ssss.islandCount(grid3));
//        System.out.println(ssss.islandCount(grid4));
        
    }
}
