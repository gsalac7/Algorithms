/*Angelo Salac
 * Programming Assignment #2
 * 3/17/17
 * CPSC 335
 */

import java.util.Scanner;

public class Project2 {
	public static void main(String[] args) {
		
		Scanner read = new Scanner(System.in);
		System.out.print("Enter the amount of vertices: ");
		int v = read.nextInt();
		int[][] matrix = new int[v][v]; //graph represented in 2d adjacency matrix array
		//initialize the weights of graph 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = read.nextInt();
			}
		}
		//just display the weights to confirm the graph is properly implemented
		System.out.println("Here are the weights of the graphs: ");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		long startTime = System.currentTimeMillis();
		//check topology pattern
		System.out.print("The topology is: ");
		if (isRing(matrix, v)) {
			System.out.println("Ring");
		} else if (isComplete(matrix, v)) {
			System.out.println("Fully Connected Mesh");
		} else if (isStar(matrix, v)) {
			System.out.println("Star");
		} else {
			System.out.println("Neither");
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		read.close();
	}
	public static boolean isRing(int [][]m, int v) {
		int []counter = new int[v]; //counts the number of "valid" edge weights in the graph
		for (int i = 0; i < counter.length; i++) { 
			counter[i] = 0; //initialize the row counters for the matrix to 0;
		}
		for (int i = 0; i < m.length; i++) { //check every number in the matrix to make sure
			for (int j = 0; j < m[i].length; j++) { //that it's not 0 or 100.
				if (m[i][j] != 0 && m[i][j] != 100) { //if it is not 0 or 100
					counter[i]++; //then increment the counter for that row.
				}//else skip counter and continue.
			}
			if (counter[i] != 2) { //an edge is only incident on every vertex, therefore the counter
				return false; //should only have values of 2, otherwise its not a ring.
			}
		}
		return true; 
	}
	
	public static boolean isComplete(int [][]m, int v) {	
		int [] counter = new int[v]; //counts the number of "valid" edge weights in the graph.
		for (int i = 0; i < counter.length; i++) {
			counter[i] = 0; //initialize the row counters for the matrix to 0;
		}
		for (int i = 0; i < m.length; i++) { //count the number of weights not equal to 0 or 100
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] != 0 && m[i][j] != 100) {
					counter[i]++; //add the values to the counter in each row
				} //else continue
			}
			if (counter[i] != v - 1) { //for a complete graph, every vertex is connected to
				return false; //every other vertex (not including itself) so v - 1
			}	
		}
		return true;
	}
	
	public static boolean isStar(int [][]m, int v) {
		int [] counter = new int[v]; //counts the number of "valid" edge weights in the graph.
		for (int i = 0; i < counter.length; i++) {
			counter[i] = 0; //initialize the row counters for the matrix to 0;
		}
		for (int i = 0; i < m.length; i++) { //count the number of weights not equal to 0 or 100
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] != 0 && m[i][j] != 100) {
					counter[i]++; //add those values to the counter for each row.
				}
			}
			if (counter[i] == v - 1 || counter[i] == 1) { //every other vertex only has one edge incident to it.
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}
