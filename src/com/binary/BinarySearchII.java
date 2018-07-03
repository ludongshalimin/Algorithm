package com.binary;
//递归的使用二分查找
//
public class BinarySearchII {
	private BinarySearchII(){}
	private static int find(Comparable[] arr,int l,int r,Comparable target){
		if(l > r){     //递归终止的条件，必须有
			return -1;
		}
		//防止极端情况下出现整形的溢出
		int mid = l + (r - l) /2;
		if(arr[mid].compareTo(target) == 0){
			return mid;
		}else if(arr[mid].compareTo(target) > 0){
			return find(arr,l,mid-1,target);
		}else{
			return find(arr,mid+1,r,target);
		}
	}
	//二分查找，如果找到了，返回索引，如果没有找到返回-1
	public static int find(Comparable[] arr,Comparable target){
		return find(arr,0,arr.length-1,target);
	}
    // 测试递归的二分查找算法
    public static void main(String[] args) {

        int N = 1000000;
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for(int i = 0 ; i < 2*N ; i ++) {
            int v = BinarySearchII.find(arr, new Integer(i));
            if (i < N){
                assert v == i;

            }
            else
                assert v == -1;
        }

        return;
    }
	
	
	
	
	
	
	
	
}
