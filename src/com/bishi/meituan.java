package com.bishi;


import java.util.Scanner;
public class meituan
{
  public static void main(String args[])
  {
      Scanner in = new Scanner(System.in);
      int num = in.nextInt();
      char [] ss = {'L','S','B','Q','W'};
      StringBuilder res = new StringBuilder();
      
      int i = 0;
      while(i<5){    //总共有五个数
          String s = Integer.toString(num%10);  //首先是个位数
          res.insert(0,ss[i]);
          res.insert(0,s);         
//          res.append(s);
//          res.append(ss[i]);
          num = num / 10;    
          i++;
      }
      for(int j = 0;j<res.length();j++){     //删除‘0’
    	  if(res.charAt(j)=='0'){
    		  res.delete(j,j+2);
    		  j--;
    	  }
      }
      //调整顺序
      int n = res.length();
      if(n ==2){
    	  res.delete(1,2);
      }
      if(n >2){
    	  if(res.charAt(n-3)!='S'){   //1W2QL1
        	  char temp = res.charAt(n-2);
        	  res.setCharAt(n-2,res.charAt(n-1));
        	  res.setCharAt(n-1,temp);
    	  }else{    //1B1S1
    		  res.delete(n-1,n); 
    	  }
      }
      System.out.println(res.toString());
  }
}
