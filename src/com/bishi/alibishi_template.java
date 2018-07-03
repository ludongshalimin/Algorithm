package com.bishi;

import java.util.Scanner;
/**
 * 阿里笔试的输入和输出模板
 * @author weifeng
 *
 */
public class alibishi_template {
	static int maxAreaRectWithSumZero(int[][] mat, int row, int col) {
        int sum=0;
        if(row<=0||col<=0)return 0;
        for(int i =0;i<row;i++){
          for(int j =0;j<col;j++){
              sum+=mat[i][j];
          } 
        }
        if((row *col)%2 ==0 &&sum ==(row * col)/2){
            return sum*2;
        }else{
        	return Math.max(maxAreaRectWithSumZero(mat,row-1,col),maxAreaRectWithSumZero(mat,row,col-1));
        }
        

      }
	  public static void main(String args[])
	  {
	        Scanner in = new Scanner(System.in);    //定向键盘
	        int res=0;

	        int _mat_rows = 0;
	        int _mat_cols = 0;
	        _mat_rows = Integer.parseInt(in.nextLine().trim()); //读取行
	        _mat_cols = Integer.parseInt(in.nextLine().trim()); //读取列

	        int[][] _mat = new int[_mat_rows][_mat_cols];
	        for(int _mat_i=0; _mat_i<_mat_rows; _mat_i++) {     //从键盘读取的数字赋值给数组
	            for(int _mat_j=0; _mat_j<_mat_cols; _mat_j++) {
	                _mat[_mat_i][_mat_j] = in.nextInt();

	            }
	        }

	        res = maxAreaRectWithSumZero(_mat, _mat_rows, _mat_cols);   //使用数组
	        System.out.println(String.valueOf(res));
	  }
}
