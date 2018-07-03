package com.bishi;

import java.util.Scanner;
/**
 * 阿里系笔试调试模板
 * 读取整数，读取数组
 * 先规定一个读取的行数和列数，然后进行
 * @author weifeng
 *
 */
public class ali {
    static int maxAreaRectWithSumZero(int[][] mat, int row, int col) {
        int sum=0;
        if(row<=0||col<=0)return 0;
        for(int i =0;i<row;i++){
          for(int j =0;j<col;j++){
              sum+=mat[i][j];
          } 
        }
//        int sum1 = maxAreaRectWithSumZero(mat,row-1,col); 
//        int sum2 = maxAreaRectWithSumZero(mat,row,col-1);
        if((row *col)%2 ==0 &&sum ==(row * col)/2){
            return sum*2;
        }else{
        	return Math.max(maxAreaRectWithSumZero(mat,row-1,col),maxAreaRectWithSumZero(mat,row,col-1));
        }
        

      }
	  public static void main(String args[])
	  {
	        Scanner in = new Scanner(System.in);
	        int res=0;

	        int _mat_rows = 0;
	        int _mat_cols = 0;
	        _mat_rows = Integer.parseInt(in.nextLine().trim());
	        _mat_cols = Integer.parseInt(in.nextLine().trim());

	        int[][] _mat = new int[_mat_rows][_mat_cols];
	        for(int _mat_i=0; _mat_i<_mat_rows; _mat_i++) {
	            for(int _mat_j=0; _mat_j<_mat_cols; _mat_j++) {
	                _mat[_mat_i][_mat_j] = in.nextInt();

	            }
	        }

	        res = maxAreaRectWithSumZero(_mat, _mat_rows, _mat_cols);
	        System.out.println(String.valueOf(res));
	  }
}
