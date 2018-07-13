package com.leetcode.muke;
/**
 * 给定一个二维平面的字母和一个单词，
 * 看是否可以在这个二维平面上找到该单词。
 * 其中找到这个单词的规则是，从一个字母出发，
 * 可以横向或者纵向连接二维平面上的其他字母。同一个位置的字母只能使用一次。

 * @author weifeng
 *
 */
/*
board = {
		{'A','B','C','E'},
		{'S','F','C','S'},
		{'A','D','E','E'}
}
"ABCCED",return true
"SEE",return true;
"ABCB",return false;
*/
class Solution_wordSearch0713{
	
}
class Solution_WordSearch{
	boolean[][] visit;
	private boolean isArea(char[][]word,int i,int j){
		int n = word.length;
		int m = word[0].length;
		return i>=0&&i<n&&j>=0&&j<m;
	}
	//对于字符串的pos位置，从词组的[i,j]位置是否可以找到
	private boolean worddfs(char[][]word,String target,int i ,int j,int pos){
		int n = word.length;
		int m = word[0].length;
		if(pos == target.length()-1){   //表示的是已经全部遍历完单词了，可以进行返回了
			return word[i][j] == target.charAt(pos);
		}
		if(word[i][j] == target.charAt(pos)){
			visit[i][j] = true;
			int[][]dir = {{0,1},{0,-1},{1,0},{-1,0}};   //这个时候字符串相等，然后进行递归操作
			for(int k = 0;k<4;k++){                 //四个方向
				int new_x = i+dir[k][0];
				int new_y = j+dir[k][1];
				if(isArea(word,new_x,new_y)&&!visit[new_x][new_y]&& //[new_x,new_y]
						worddfs(word,target,new_x,new_y,pos+1)){
					return true;
				}
				
			}
			visit[i][j] = false;
		}
		return false;
	}
	public boolean iswordExit(char[][]word,String target){
		int n = word.length;
		int m = word[0].length;
		visit = new boolean[n][m];
		for(int i = 0;i<n;i++){
			for(int j = 0;j<m;j++){
				if(worddfs(word,target,i,j,0)){
					return true;
				}
			}
		}
		return false;
	}
}
public class M36_WordSearch {
	private int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	private int m,n;
	private boolean[][] visited;
	public boolean exist(char[][] board,String word){
		m = board.length;   //m行
		n = board[0].length; //n列
		visited = new boolean[m][n];  //作为辅助数组，判断是否被访问
		for(int i =0;i<m;i++){
			for(int j = 0;j<n;j++){
				if(searchWord(board,word,0,i,j)){  //这里的0是单词的index
					return true;
				}
			}
		}
		return false;
	}
	private boolean inArea(int x,int y){
		return x>=0 && x<m&&y>=0&&y<n;
	}
	// 从board[startx][starty]开始, 寻找word[index...word.size())
	private boolean searchWord(char[][]board,String word,int index,int startx,int starty){
		if(index == word.length() -1){  //单词的最后一个索引了
			return board[startx][starty] == word.charAt(index);
		}
		if(board[startx][starty] == word.charAt(index)){
			visited[startx][starty] = true;
			// 从startx,starty出发，向四个方向寻找
			for(int i = 0;i<4;i++){
				int newx = startx+d[i][0];
				int newy = starty +d[i][1];
				if(inArea(newx,newy)&& !visited[newx][newy] &&
						searchWord(board, word, index + 1, newx, newy)){  //四个方向都没有找到，进行回退
							return true;
				}
			}
			visited[startx][starty] = false;
		}
		return false;	
		
	}
	public static void main(String args[]){

        char[][] b1 = { {'A','B','C','E'},
                        {'S','F','C','S'},
                        {'A','D','E','E'}};

        String words[] = {"ABCCED", "SEE", "ABCB" };
        for(int i = 0 ; i < words.length ; i ++)
            if((new M36_WordSearch()).exist(b1, words[i]))
                System.out.println("found " + words[i]);
            else
                System.out.println("can not found " + words[i]);

        // ---

        char[][] b2 = {{'A'}};
        if((new M36_WordSearch()).exist(b2,"AB"))
            System.out.println("found AB");
        else
            System.out.println("can not found AB");
        
        
        System.out.println("0711");
        Solution_WordSearch ss = new Solution_WordSearch();
        for(int i = 0 ; i < words.length ; i ++)
            if(ss.iswordExit(b1, words[i]))
                System.out.println("found " + words[i]);
            else
                System.out.println("can not found " + words[i]);
        if(ss.iswordExit(b2,"AB"))
            System.out.println("found AB");
        else
            System.out.println("can not found AB");
    }
}
