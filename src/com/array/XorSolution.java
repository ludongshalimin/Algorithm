package com.array;

import java.util.HashMap;

import com.bishi.kuaishou;

/**
 * 异或问题：这里面有三个问题
 * 0：给定一个数组，数组里面只有一个数是出现了一次，其他的数都出现了两次，
 * 1：给定一个数组，这里面有两个数是只出现了一次，其他的数据是出现了两次，求这两个数（如何用o(n)的时间复杂度和o(1)的空间复杂度）
 * 2：给定一个数组，这个数组里面有一些数是出现了一次或者两次，求只出现了一次的数（这会是一个数组）
 * @author weifeng
 *
 */
class xor_solution0517{
	public int getSingle(int[] arr){
		int n = arr.length;
		int res = arr[0];
		for(int i = 1;i<n;i++){
			res = res^arr[i];
		}
		return res;
	}
	//针对的是第二个问题
	private int getFirstOneIndex(int n){
		int i = 0;
		while((n&1) != 1&&i<=32){
			n=n>>1;
			i++;
		}
		return i;
	}
	private boolean isOne(int num,int index){
		int n = 0;
		while(n<index){
			num = num>>1;
			n++;
		}
		return ((num&1)==1);
	}
	public int[] getSinglePair(int[] arr){   //一堆数中只有两个数是重复的，求这两个数
		int [] res = new int[2];
		int n = arr.length;
		int temp = arr[0];
		for(int i = 1;i<n;i++){
			temp = temp ^arr[i];
		}
		//然后找到temp中的1的位置，根据temp中的1进行切分数据
		int firstOneIndex = getFirstOneIndex(temp);
		for(int i = 0;i<n;i++){
			if(isOne(arr[i],firstOneIndex)){
				res[0] = res[0]^arr[i];
			}else{
				res[1] = res[1]^arr[i];
			}
		}
		return res;
	}
	//如果是针对的是第三个问题，即如果有一个数组，这个数组里面的数字，要么出现了一次，要么出现了两次
	public int[] getSingleArr(int[] arr){
		int n = arr.length;
		HashMap<Integer,Integer> ss = new HashMap<Integer,Integer>();
		for(int i = 0;i<n;i++){
			if(ss.containsKey(arr[i])){
				ss.remove(arr[i]);
				continue;
			}
			ss.put(arr[i],i);
		}
		int i =0 ;
		int[] res = new int[ss.size()];
		for(int num:ss.keySet()){
			res[i] = num;
			i++;
		}
		return res;
		
	}
}
class kuaishou_Solution{
	private int findFirstBit1(int num){   //找到num表示的二进制数中的从左到右，出现的第一个1
		int n = 0;
		while(((num&1)== 0) && n<32){
			num = num >> 1;
			n++;
		}
		return n;
	}
	private boolean isBit1(int num,int index){
		num =  num >> index;
		return ((num & 1)==1);
	}
	public int[] getSingle(int[] arr){  //先求出来全部的异或的和，必然重复的数组的异或是为0，然后找到异或结果的第一个不为0的bit 索引
		int n = arr.length;             //按照索引，然后将数据分为两部分进行异或，异或后的结果就是最终的结果
		int xorres = 0;                //我们知道在一个数组中，如果仅有一个数是只出现一次，这个很好找
	    for (int i = 0; i< n; ++ i){
	    	xorres ^= arr[i];
	    }
	    int intindexOf1 = findFirstBit1(xorres);
	    int[] res = new int[2];
	    for (int j = 0; j< n; ++ j){
	      if(isBit1(arr[j], intindexOf1)){
	           res[0] ^= arr[j];
	      }else{
	           res[1] ^= arr[j];
	      }
	    }
	    return res;
	}
}
public class XorSolution {

	public int[] getSingle(int[] arr){
		int n = arr.length;
		
//			LinkedList<Integer> ll = new LinkedList<Integer>();
		HashMap<Integer,Integer> ll = new HashMap<Integer,Integer>();
		for(int i = 0 ;i<n;i++){
			if(ll.containsKey(arr[i])){
				ll.remove(arr[i]);
				continue;
			}
			ll.put(arr[i],i);
		}
		int j = 0;
		int nn = ll.size();
		int[] res = new int[nn];
		for( int resultint:ll.keySet()){
			res[j] = resultint;
			j++;
		}
		return res;
	}
	public static void main(String[] args){
		
		int[] arr = {3,8,8,4,3,6,6,5,5,4,1,2};
		kuaishou ss = new kuaishou();
		int []res = ss.getSingle(arr);
		for(int i:res){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
		System.out.println("sasa");
		xor_solution0517 sss = new xor_solution0517();
		res = sss.getSinglePair(arr);
		for(int i:res){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
		System.out.println("sasa");
		int[] arrr = {3,8,8,4,3,6,6,5,5,4,1,2,9};
		res = sss.getSingleArr(arrr);
		for(int i:res){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println("end");
	}
}
