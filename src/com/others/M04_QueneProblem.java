package com.others;
/*
 * 八皇后问题
 * @author weifeng
 * 有多少种解决方案，然后打印具体的方法
 */

public class M04_QueneProblem {
	private boolean[][] visit;
	private int count;
	private int N;  //几皇后问题
	private boolean inArea(int n,int m,int i,int j){
		return i>=0&&i<n&&j>=0&&j<m;
	}
	private boolean isSafe(int row,int col){
        //判断中上、左上、右上是否安全
        int step=1;
        while(row-step>=0){
            if(visit[row-step][col]==true)                //中上
                return false;
            if(col-step>=0 && visit[row-step][col-step]==true)        //左上
                return false;
            if(col+step<N && visit[row-step][col+step]==true)        //右上
                return false;
            
            step++;
        }
        return true;
	}
	private void putQueenAtRow(int n,int row){   //n*n的棋盘，现在要放置第row行
		if(row == n){
			count ++;
            System.out.println("第 "+ count +" 种解：");
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(visit[i][j]+" ");
                }
                System.out.println();
            }
			return;
		}
		for(int i = 0;i<n;i++){   //表示的是列数
			for(int j = 0;j<n;j++){  //将当前行全部进行初始化，防止污染
				visit[row][j] = false;  
			}
			visit[row][i] = true;  //先放置在visit[row][i]上
			if(isSafe(row,i)){
				putQueenAtRow(n,row+1);
			}
		}
	}
	public void putQueen(int n){
		N= n;
		visit = new boolean[n][n];
		putQueenAtRow(n,0);  //从0行开始放置
	}
	public static void main(String[] args){
		M04_QueneProblem ss = new M04_QueneProblem();
		ss.putQueen(8);
		System.out.println(ss.count);
	}
}
