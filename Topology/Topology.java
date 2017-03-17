/*Angelo Salac
 * Programming Assignment #2
 * 3/17/17
 * CPSC 335
 */

import java.util.Scanner;

public class Topology {
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		System.out.println("Topology Recognition Algorithm");
		System.out.print("Enter the amount of vertices: ");
		int v = read.nextInt();
		int[][] matrix = new int[v][v]; //graph represented in 2d adjacency matrix array
		//initialize the weights of graph 
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = read.nextInt();
			}
		}
		
		read.close();
		//just display the weights to confirm the graph is properly implemented
		System.out.println("Here are the weights of the graphs: ");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		double startTime = System.nanoTime(); //start recording time
		//check topology pattern
		System.out.print("The topology is: ");
		if (isRing(matrix, v)) {
			double endTime   = System.nanoTime();
			System.out.println("Ring");
			double totalTime = (endTime - startTime)/1000000/1000; //convert time to seconds
			System.out.println("Elapsed Time: " + totalTime + " seconds");
			System.exit(0); //exit
		}
		double startTime1 = System.nanoTime(); //start recording time
		if (isComplete(matrix, v)) {
			double endTime = System.nanoTime();
			System.out.println("Fully Connected Mesh");
			double totalTime = (endTime - startTime1)/1000000/1000; //convert time to seconds
			System.out.println("Elapsed Time: " + totalTime + " seconds");
			System.exit(0); //exit
		} 
		double startTime2 = System.nanoTime(); //start recording time
		if (isStar(matrix, v)) {
			double endTime = System.nanoTime();
			System.out.println("Star");
			double totalTime = (endTime - startTime2)/1000000/1000; //convert time to seconds
			System.out.println("Elapsed Time: " + totalTime + " seconds");
			System.exit(0); //exit 
		}
		double endTime   = System.nanoTime();
		System.out.println("Neither");
		double totalTime = (endTime - startTime)/1000000/1000; //convert time to seconds
		System.out.println("Elapsed Time: " + totalTime + " seconds");
	
	}
	public static boolean isRing(int [][]m, int v) {		
		for (int i = 0; i < m.length; i++) { //check every number in the matrix to make sure
			int counter = 0;
			for (int j = 0; j < m[i].length; j++) { //that it's not 0 or 100.
				if (m[i][j] != 0 && m[i][j] != 100) { //if it is not 0 or 100
					counter++; //then increment the counter for that row.
				}//else skip counter and continue.
			}
			if (counter != 2) { //an edge is only incident on every vertex, therefore the counter
				return false; //should only have values of 2, otherwise its not a ring.
			}
		}
		return true; 
	}
	
	public static boolean isComplete(int [][]m, int v) {	
		for (int i = 0; i < m.length; i++) { //count the number of weights not equal to 0 or 100
			int counter = 0;
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] != 0 && m[i][j] != 100) {
					counter++; //add the values to the counter in each row
				} //else continue
			}
			if (counter != v - 1) { //for a complete graph, every vertex is connected to
				return false; //every other vertex (not including itself) so v - 1
			}	
		}
		return true;
	}
	
	public static boolean isStar(int [][]m, int v) {
		int center = 0;
		for (int i = 0; i < m.length; i++) { //count the number of weights not equal to 0 or 100
			int counter = 0;
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] != 0 && m[i][j] != 100) {
					counter++; //add those values to the counter for each row.
				}
			}
			if (counter == v - 1)  { //make sure theres only one center which as all vertices incident on it
				center++; 
			} else if (center == 1 && counter == 1) { //continue the loop as long as there is 1 center and all other vertices have 1 edge incident on it
				continue;
			} else { //if theres more than 1 center or another vertex has more than 1 or less than 1 edge incident, then it's not a star
				return false;
			}
		}
		return true;
	}
}
