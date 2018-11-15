import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import MazeRunners.Node;

public class Maze extends Canvas{
		
	private int rows = 25;
	private int columns = 25;
	
	ArrayList<Node[][]> maps = new ArrayList<Node[][]>(); 
	
	public Maze(int r, int c) {
		rows = r;
		columns = c;
	}
	
	public void generateMaze() {
		//-1: unvisited, 0: empty, 1: wall
		Node[][] maze = new Node[rows][columns];
		Random rnd = new Random();
		Stack<Node> stack = new Stack<Node>();
		for(int i = 0; i<maze.length; i++) {
			for(int j = 0; j<maze[i].length; j++) {
				maze[i][j] = new Node(i,j,false);
			}
		}
		maze[0][0].setVisited(true); //visited
		int curRow = 0;
		int curCol = 0;
		int newRow;
		int newCol;
		int choice;
		while(hasUnvisited(maze)) {
			if(hasUnvisitedNeighbor(maze, curRow, curCol)) {
				newRow = curRow;
				newCol = curCol;
				while(true) {
					choice = rnd.nextInt(2);
					if(choice == 0)
						newRow = curRow + rnd.nextInt(3) - 1;
					else
						newCol = curCol + rnd.nextInt(3) - 1;
					
					//new node is actually a neighbor, and nothing is out of bounds
					if((newRow != curRow || newCol != curCol) && newRow >= 0 && newCol >= 0 && newRow < rows && newCol < columns) {
						if(!maze[newRow][newCol].isVisited()) {
							break;
						}
					}
				}
				stack.push(maze[curRow][curCol]);
				if(newRow > curRow) {
					maze[curRow][curCol].setNorth(false);
					maze[newRow][newCol].setSouth(false);
				}
				else if(newRow < curRow) {
					maze[curRow][curCol].setSouth(false);
					maze[newRow][newCol].setNorth(false);
				}
				else if(newCol > curCol){
					maze[curRow][curCol].setEast(false);
					maze[newRow][newCol].setWest(false);
				}
				else {
					maze[curRow][curCol].setWest(false);
					maze[newRow][newCol].setEast(false);
				}
				curRow = newRow;
				curCol = newCol;
				maze[curRow][curCol].setVisited(true);
			}
			else if(!stack.isEmpty()) {
				curRow = stack.peek().getRow();
				curCol = stack.peek().getCol();
				stack.pop();
			}
		}
		maps.add(maze);
	}
	
	public boolean hasUnvisited(Node[][] maze) {
		for(int i = 0; i<maze.length; i++) {
			for(int j = 0; j<maze[i].length; j++) {
				if(!maze[i][j].isVisited())
					return true;
			}
		}
		return false;
	}
	
	public boolean hasUnvisitedNeighbor(Node[][] maze, int row, int col) {
		try {
			if(!maze[row+1][col+1].isVisited()) {
				return true;
			}
			if(!maze[row][col+1].isVisited()) {
				return true;
			}
			if(!maze[row-1][col+1].isVisited()) {
				return true;
			}
			if(!maze[row+1][col].isVisited()) {
				return true;
			}
			if(!maze[row-1][col].isVisited()) {
				return true;
			}
			if(!maze[row+1][col-1].isVisited()) {
				return true;
			}
			if(!maze[row][col-1].isVisited()) {
				return true;
			}
			if(!maze[row-1][col-1].isVisited()) {
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Node[][] maze = maps.get(0);
		int x=5,y=5;
		for(int i=0; i<rows; i++) {
			x=5;
			for(int j=0; j<columns; j++) {
				if(maze[i][j].isNorth()) {
					g.fillRect(x, y-5, 10, 1);
				}
				if(maze[i][j].isSouth()) {
					g.fillRect(x, y+5, 10, 1);
				}
				if(maze[i][j].isEast()) {
					g.fillRect(x+5, y, 1, 10);
				}
				if(maze[i][j].isWest()) {
					g.fillRect(x-5, y, 1, 10);
				}
				x += 10;
			}
			y += 10;
		}

	}

}
