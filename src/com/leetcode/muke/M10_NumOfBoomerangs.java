package com.leetcode.muke;

import java.util.HashMap;

/**
 * 给出一个平面上的n个点，
 * 寻找存在多少个由这些点构成的三元组（i, j, k）,这个三元组是有顺序的么(i,j,k)和(i,k,j)是否是同一个
 * 使得i,  j两点的距离等于i, k两点的距离。
 * 其中n最多为500，且所有的点坐标的范围在[-10000, 10000]之间。

 * @author weifeng
 * 观察到 i 是一个“枢纽”，对于每个点i，遍历其余点到i的距离O(n^2)

 *
 */
public class M10_NumOfBoomerangs {
	public int numberOfBoomerangs(int[][] points){
		int res = 0;
		for(int i=0;i<points.length;i++){   //两重循环，点是不一样的
			//record中存储点i到所有其他店的距离出现的频次
			HashMap<Integer,Integer> record = new HashMap<Integer,Integer>();
			for(int j = 0;j<points.length;j++){
				if(j != i ){
					//计算距离时不进行开根运算，以保证精度
					int dis = dis(points[i],points[j]);
					if(record.containsKey(dis)){
						record.put(dis,record.get(dis) + 1);
					}else{
						record.put(dis,1);
					}
				}
			}
			for(Integer dis:record.keySet()){  //对于当前点的每一个距离，个数乘以个数-1
				res += record.get(dis) * (record.get(dis)-1);  //比如距离为3，的有3个数对，C(3,2)表示的是，3*2=6
			}

		}
		return res;
	}
	private int dis(int[]pa,int[]pb){
		return(pa[0]-pb[0])*(pa[0]-pb[0])+
				(pa[1]-pb[1])*(pa[1]-pb[1]);
	}
	public static void main(String[] args){
		int [][] points = {{0,0},{1,0},{2,0}};
		System.out.println((new M10_NumOfBoomerangs()).numberOfBoomerangs(points));
	}
}
