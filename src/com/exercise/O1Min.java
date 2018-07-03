package com.exercise;

import com.stack.ArrayStack;

//如何使用o(1)的时间复杂度实现，求栈中最小的元素
//使用两个栈，一个栈用来存储数据，另一个栈用来存储最小的数据
//如果当前入栈的元素比原来栈中的元素最小值还要小，则把这个值保存到最小元素的栈中
//在出栈时，如果当前的元素恰好为当前栈中的最小值，保存最小值得栈顶元素也出栈
public class O1Min {
	ArrayStack<Integer> elemstack;
	ArrayStack<Integer> minstack;
	
	public O1Min(){
		elemstack = new ArrayStack<Integer>();
		minstack = new ArrayStack<Integer>();
	}
	public void push(int data){
		elemstack.push(data);
		if(minstack.isEmpty()){
			minstack.push(data);
		}else{
			if(data < minstack.peek()){
				minstack.push(data);
			}
		}
	}
	public int pop(){
		int topData = elemstack.peek();
		elemstack.pop();
		if(topData == this.min()){
			minstack.pop();
		}
		return topData;
	}
	public int min(){
		if(minstack.isEmpty()){
			return Integer.MAX_VALUE;
		}else{
			return minstack.peek();
		}
	}
	public static void main(String[] args){
		O1Min minnum = new O1Min();
		minnum.push(4);
		minnum.push(2);
		minnum.push(22);
		minnum.push(55);
		System.out.println(minnum.min());
	}
}
