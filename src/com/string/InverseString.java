package com.string;
//反转字符串，“how are you"  --->  ”you are how"
class Solution_SwapString0607{
	private void swapString(char[] s,int start,int end){
		while(start<end){
			char cc = s[start];
			s[start] = s[end];
			s[end] = cc;
			start ++;
			end--;
		}
	}
	public String swapwords(String s){
		char[] cc = s.toCharArray();
		swapString(cc,0,cc.length-1);
		int startIndex = 0;
		int endIndex = -1;
		for(int i=0;i<cc.length;i++){
			if(cc[i]== ' '){
				swapString(cc,startIndex,endIndex);
				startIndex = i+1;
			}
			endIndex++;
		}
		swapString(cc,startIndex,cc.length-1);
		return new String(cc);
	}
}
class Solution_SwapString{
	private void swap(char[] arr,int i ,int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	//从索引i开始到j，反转整个字符串数组
	public void inverseString(char[] arr,int i,int j){
		for(int k = i,l=j;k<j;k++,l--){
			swap(arr,k,l);
		}
	}
	public String inverseString(String s){
		char[] charArr = s.toCharArray();
		int n = charArr.length;
		for(int i =0,j=n-1;i<j;i++,j--){  //字符全部反转
			swap(charArr,i,j);
		}
		int j =0;
		for(int i =0;i<n;i++){
			if(charArr[i]==' '){
				inverseString(charArr,j,i-1);
				if(i+1<n){
					j=i+1;
				}
			}
		}
		inverseString(charArr,j,n-1);
		return new String(charArr);
	}
}

public class InverseString {
	//字符数组互换
	private void swapArray(char[] cArr,int front,int end){
		while( front < end){
			char tmp = cArr[end];
			cArr[end] = cArr[front];
			cArr[front] = tmp;
			front ++;
			end --;
		}
	}
	public String swapWords(String s){
		char[] cArr = s.toCharArray();
		//对整个字符串进行反转操作
		swapArray(cArr,0,cArr.length - 1);
		int begin = 0;
		//对每个单词进行字符反转操作
		for(int i =1 ;i<cArr.length;i++){
			if(cArr[i] == ' '){
				swapArray(cArr,begin,i-1);
				begin = i+1;
			}
		}
		//最后一个单词，因为没有分隔符‘ ’
		swapArray(cArr,begin,cArr.length-1);
		return new String(cArr);    //再将整个字符数组变成字符串
	}
	public static void main(String[] args){
		String str = "how are you";                 
		String result = new InverseString().swapWords(str);
		System.out.println(result);
		Solution_SwapString ss = new Solution_SwapString();
		System.out.print(ss.inverseString(str));
		
		Solution_SwapString0607 sss = new Solution_SwapString0607();
		String s = sss.swapwords("how are you");
		System.out.println(s);
	}
}
 