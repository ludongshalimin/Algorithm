package com.bishi;
import java.util.Scanner;

//是以enter作为分割符号
//next:对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
//只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
//next() 不能得到带有空格的字符串

//nextLine:以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符


//next()是以 空格作为终结字符串


//hasNextInt()，while(hasNextInt())

//nextInt()
//nextFloat()
//nextDouble()
//读入一行字符串，然后按照空格进行切分，然后转换成整数
//String arr[] = tmp.split("\\s+");//使用正则表达式将字符串分割 “\\s+”表示多个空格  
//int sum = 0;  
//for(String a:arr)//遍历所有的字符串并转换成整数求和  
//{  
//    sum+=Integer.parseInt(a);  
//}  
//System.out.println(sum);  

	public class iqiyi
	{
	  public static int gcd(int m, int n){  
	    while(m>0)  
	    {  
	        int c = n % m;  
	        n = m;  
	        m = c;  
	    }  
	    return n;  
	} 
	 // public static int gcd(int m, int n){  
	  //    return (m==0)?n:gcd(n%m, m);  
	 // }
	  public static void main(String args[])
	  {
	      Scanner sc = new Scanner(System.in);
	      int n = sc.nextInt();
	      int i = sc.nextInt();
	      int j = sc.nextInt();
	      int p = sc.nextInt();
	      int [] A = new int[n+1];
	      Integer a =  Integer.MAX_VALUE;
	      A[1]=p;
	      for(int k = 2;k<n+1;k++){    //构造矩阵
	          A[k]=(A[k-1]+153)%p;
	      }
	      for(int s = 1;s<A.length;s++){
	    	  System.out.println(A[s]);
	      }
	      System.out.println(gcd(1,2));
	      System.out.println(i);
	      System.out.println(j);
	      int res = 0;
		  for(int m = 1;m<=i;m++){
	          for(int l =1;l<=j;l++){
	          	res += A[gcd(m,l)];
	          }
	      }
	      System.out.println(res);
	  }
	}



