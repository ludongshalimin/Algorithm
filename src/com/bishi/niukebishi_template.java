package com.bishi;

import java.util.Scanner;

/**
 * 牛客网笔试，读取键盘的输入和输出模板
 * @author weifeng
 *
 */

//nextLine():以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符
//next()是以 空格作为分隔符
//hasNext()
//hasNextLine()

//hasNextInt()，while(hasNextInt())

//nextInt()
//nextFloat()
//nextDouble()
//读入一行字符串，然后按照空格进行切分，然后转换成整数
//String arr[] = tmp.split("\\s+");//使用正则表达式将字符串分割 “\\s+”表示多个空格  
//int sum = 0;  
//for(String a:arr)//遍历所有的字符串并转换成整数求和  
//{  
//  sum+=Integer.parseInt(a);  
//}  
//System.out.println(sum);  
public class niukebishi_template {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
//		while(sc.hasNextLine()){
//			System.out.print(Integer.parseInt(sc.nextLine()));
//		}
		// 如果要是有输入的行数和列数，则按照下面进行
        int _mat_rows = 0;
        int _mat_cols = 0;
        _mat_rows = Integer.parseInt(sc.nextLine().trim()); //读取行
        _mat_cols = Integer.parseInt(sc.nextLine().trim()); //读取列
        int[][] _mat = new int[_mat_rows][_mat_cols];
        for(int _mat_i=0; _mat_i<_mat_rows; _mat_i++) {     //从键盘读取的数字赋值给数组
            for(int _mat_j=0; _mat_j<_mat_cols; _mat_j++) {
                _mat[_mat_i][_mat_j] = sc.nextInt();

            }
        }
        //如果没有给出输入的行数和列数，读取一行数字字符串，然后分别分割转换成数字  
		String temp = sc.nextLine();
		String arr[] = temp.split("\\s+");//使用正则表达式将字符串分割 “\\s+”表示多个空格  
		int[] arrInt = new int[arr.length];
		int sum = 0;  
		for(String a:arr)//遍历所有的字符串并转换成整数求和  
		{  
		  sum+=Integer.parseInt(a);  
		}  
		System.out.println(sum); 
		sc.nextLine();     //隔了一个空行,如何整
		
		temp = sc.nextLine();
		String arrr[] = temp.split("\\s+");//使用正则表达式将字符串分割 “\\s+”表示多个空格  
		sum = 0;  
		for(String a:arrr)//遍历所有的字符串并转换成整数求和  
		{  
		  sum+=Integer.parseInt(a);  
		}  
		System.out.println(sum); 
		
	}
}
