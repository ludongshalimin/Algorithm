package com.binary;

import java.util.LinkedList;

//二分搜索树，首先是一棵二叉树
//二分搜索树,由于key需要进行比较，所以需要extends Comparable<key>
//二分搜索树的优势，查找表的经典的实现--字典数据结构
//不仅可查找数据，还可以高效的插入数据，删除数据，动态维护数据
//可以回答很多数据之间的关系问题：min,max,floor,ceil,rank,select

//二分搜索树的结构
//每个节点的键值大于左孩子，每个节点的键值小于右孩子，以左右为根的子树，仍然是二分搜索树
//二分搜索树不一定是完全二叉树
public class BinarySearchTree <Key extends Comparable<Key> ,Value>{
	//树中的节点为私有的类，外界不需要了解二分搜索树节点的具体实现
	private class Node{
		private Key key;
		private Value value;
		private Node left,right;
		public Node(Key key,Value value){   //构造函数，传递KEY和value，这个节点是一个子节点
			this.key = key;
			this.value = value;
			left=null;
			right = null;
		}
		public Node(Node node){    //构造函数,传递的是一个节点，这个节点是一个父节点
			this.key = node.key;
			this.value = node.value;
			this.left = node.left;
			this.right = node.right;
		}
	}
	
	private Node root; //根节点
	private int count; //树中的节点个数
	
	//构造函数
	public BinarySearchTree(){
		root = null;
		count = 0;
	}
	//返回二分搜索树的节点个数
	public int size(){
		return count;
	}
	//返回二分搜索树是否为空
	public boolean isEmpty(){
		return count == 0;
	}
	//查看二分搜索树种是否存在键key
	public boolean contain(Key key){
		return contain(root,key);
	}
	//在二分搜索树种搜索键key所对应的值，如果这个值不存在，则返回null
	public Value search(Key key){
		return search(root,key);
	}
	public void insert(Key key,Value value){
		root = insert(root,key,value);
	}
	//二分查找树的遍历，
	//深度优先搜索：前序遍历，中序遍历，后序遍历
	//广度优先搜索：层序遍历
	//前序遍历
	public void preOrder(){
		preOrder(root);
	}
	//中序遍历
	public void inOrder(){
		inOrder(root);
	}
	//后序遍历
	public void postOrder(){
		postOrder(root);
	}
	//二分搜索树的层序遍历
	public void levelOrder(){
		//使用linkList作为队列
		LinkedList<Node> q = new LinkedList<Node>();  //这个例子说明要熟练掌握容器类
		q.add(root);
		while(!q.isEmpty()){
			Node node = q.remove();
			System.out.println(node.key);
			if(node.left != null){
				q.add(node.left);
			}
			if(node.right != null){
				q.add(node.right);
			}
		}
	}
	//寻找二分查找树的最小键值
	public Key minNum(){
		assert count != 0;
		Node minNode = minNum(root);
		return minNode.key;
	}
	//寻找二分查找树中的最大键值
	public Key maxNum(){
		assert count!=0;
		Node maxNode = maxNum(root);
		return maxNode.key;
	}
	//从二分查找树种删除最小值所在的节点
	public void removeMin(){
		if(root != null){
			root =  removeMin(root);
		}
	}
	//从二分查找树种删除最大值所在的节点
	public void removeMax(){
		if(root != null){
			root = removeMax(root);
		}
	}
	public void remove(Key key){
		root = remove(root,key);
	}
	
	//二分搜索树的辅助函数
	//向以node为根的二分搜索树种，插入节点（key,value)，使用递归算法
	//返回插入新节点的二分搜索树的根
	private Node insert(Node node,Key key,Value value){
		if(node == null){  //如果没有找到相同节点的值，遍历了所有的节点，所以添加新的节点
			count++;
			return new Node(key,value);
		}
		if(key.compareTo(node.key) == 0){  //如果找到了含有相同key的节点值，则更新value值
			node.value = value;
		}else if(key.compareTo(node.key) < 0){
			node.left = insert(node.left,key,value);
		}else{
			node.right = insert(node.right,key,value);
		}
		return node;
	}
	//查看以node为根的二分搜索树种是否包含键值为key的节点,使用递归的算法
	private boolean contain(Node node,Key key){
		if(node == null)return false;
		if(key.compareTo(node.key) == 0){
			return true;
		}else if(key.compareTo(node.key) < 0){
			return contain(node.left,key);
		}else{
			return contain(node.right,key);
		}
	}
	
	//以node为根的二分搜索树种查找key所对应的 value，递归算法
	//若value不存在，则返回null
	private Value search(Node node,Key key){
		if(node == null){
			return null;
		}
		if(key.compareTo(node.key) == 0){
			return node.value;
		}else if(key.compareTo(node.key) < 0){
			return search(node.left,key);
		}else{
			return search(node.right,key);
		}
	}
	
	//对以node 为根的二叉搜索树进行前序遍历
	private void preOrder(Node node){
		if(node != null){
			System.out.println(node.key);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	//对以node为根的二叉搜索树进行中序遍历
	private void inOrder(Node node){
		if(node != null){
			inOrder(node.left);
			System.out.println(node.key);
			inOrder(node.right);
		}
	}
	//对以node为根的二叉排序树进行后序遍历
	private void postOrder(Node node){
		if(node != null){
			postOrder(node.left);
			postOrder(node.right);
			System.out.println(node.key);
		}
	}
	
	//返回以node为根的二分搜索树的最小键值所在的节点
	private Node minNum(Node node){
		if(node.left == null){
			return node;
		}
		return minNum(node.left);
	}
	//返回以node为根的二分搜索树种的最大键值所在的节点
	private Node maxNum(Node node){
		if(node.right == null){
			return node;
		}
		return maxNum(node.right);
	}
	//删除掉以node为根的二分搜索树种的最小节点
	//返回删除节点后的新的二分搜索树种的根
	private Node removeMin(Node node){
		if(node.left == null){
			Node rightNode = node.right;
			node.right = null;
			count--;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}
	// 删除掉以node为根的二分搜索树种的最大节点
	//返回删除节点的新的二分搜索树种的根
	private Node removeMax(Node node){
		if(node.right == null){
			Node leftNode = node.left;
			node.left = null;
			count--;
			return leftNode;
		}
		node.right = removeMax(node.right);
		return node;
	}
	//删除以node为根的二分搜索树种的键值为key的节点，
	//返回删除节点新的二分搜索树的根
	
	Node remove(Node node,Key key){
		if(node == null){
			return null;
		}
		if(key.compareTo(node.key) < 0){
			node.left = remove(node.left,key);
			return node;
		}else if(key.compareTo(node.key) > 0){
			node.right = remove(node.right,key);
			return node;
		}else{   //key == node.key
			//待删除节点左子树为空的情况
			if(node.left == null){
				Node rightNode = node.right;
				node.right = null;
				count--;
				return rightNode;
			}
			//待删除节点右子树为空的情况
			if(node.right == null){
				Node leftNode = node.left;
				node.left = null;
				count --;
				return leftNode;
			}
			//待删除的左右子树均不为空的情况下
			//找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
			//用这个节点代替删除节点的位置
			Node successor = new Node(minNum(node.right)); //右子树种的最小值
			count ++;
			successor.right = removeMin(node.right); //返回node.right
			successor.left = node.left;
			node.left = node.right = null;
			count --;
			return successor;
		}
	}
	
	
	//选择性的记忆
	//floor：什么是floor，最接近KEY的值，但是比KEY要小的值
	//ceil:最接近KEY的值，但是比KEY要大的值
	
	public Key floor(Key key){
		if(count ==0 || key.compareTo(minNum()) <0){
			return null;
		}
		Node floorNode = floor(root,key);
		return floorNode.key;
	}
	//在以node为根的二叉树中，寻找KEY的floor值，所处的节点
	private Node floor(Node node,Key key){
		if(node == null)return null;
		//如果node的key值和要寻找的key值相等
		//则node本身就是key的floor节点
		if(node.key.compareTo(key) == 0){
			return node;
		}
		//如果node的key值比要寻找的key值大
		//则要寻找的key的floor节点一点在node的左子树中
		if(node.key.compareTo(key) > 0){
			return floor(node.left,key);
		}
		//如果node->key < key
		//则node有可能是key的floor节点，也有可能不是
		//需要尝试向node的右子树寻找一下
		Node tempNode = floor(node.right,key);
		if(tempNode != null){
			return tempNode;
		}
		return node;
	}
	
	
    // 寻找key的ceil值, 递归算法
    // 如果不存在key的ceil值(key比BST中的最大值还大), 返回NULL
    Key ceil(Key key){

        if( count == 0 || key.compareTo(maxNum()) > 0 )
            return null;

        Node ceilNode = ceil(root, key);
        return ceilNode.key;
    }
	// 在以node为根的二叉搜索树中, 寻找key的ceil值所处的节点, 递归算法
    private Node ceil(Node node, Key key){

        if( node == null )
            return null;

        // 如果node的key值和要寻找的key值相等
        // 则node本身就是key的ceil节点
        if( node.key.compareTo(key) == 0 )
            return node;

        // 如果node的key值比要寻找的key值小
        // 则要寻找的key的ceil节点一定在node的右子树中
        if( node.key.compareTo(key) < 0 )
            return ceil( node.right , key );

        // 如果node->key > key
        // 则node有可能是key的ceil节点, 也有可能不是(存在比node->key小但是大于key的其余节点)
        // 需要尝试向node的左子树寻找一下
        Node tempNode = ceil( node.left , key );
        if( tempNode != null )
            return tempNode;

        return node;
    }
    public static void main(String[] args){
    int N = 1000000;

    // 创建一个数组，包含[0...N)的所有元素
    Integer[] arr = new Integer[N];
    for(int i = 0 ; i < N ; i ++)
        arr[i] = new Integer(i);

    // 打乱数组顺序
    for(int i = 0 ; i < N ; i ++){
        int pos = (int) (Math.random() * (i+1));
        Integer t = arr[pos];
        arr[pos] = arr[i];
        arr[i] = arr[pos];
    }
    // 由于我们实现的二分搜索树不是平衡二叉树，
    // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
    // 平衡二叉树的实现，我们在这个课程中没有涉及，
    // 有兴趣的同学可以查看资料自学诸如红黑树的实现
    // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）


    // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
    // 键值的对应关系为每个整型对应代表这个整型的字符串
    BinarySearchTree<Integer,String> bst = new BinarySearchTree<Integer,String>();
    for(int i = 0 ; i < N ; i ++)
        bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));

    // 对[0...2*N)的所有整型测试在二分搜索树中查找
    // 若i在[0...N)之间，则能查找到整型所对应的字符串
    // 若i在[N...2*N)之间，则结果为null
    for(int i = 0 ; i < 2*N ; i ++){
        String res = bst.search(new Integer(i));
        if( i < N ){
            assert res == Integer.toString(i);
    		System.out.println(i);
        }
        else
            assert res == null;
    }
}
}
