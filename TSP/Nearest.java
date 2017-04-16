import java.util.Scanner;

public class Nearest {
	public static void main(String [] args) {
		Scanner read = new Scanner(System.in);
		
		
		
		System.out.println("Enter the amount of points: ");
		int vertices = read.nextInt();
		int B, A;
		int []path = new int[vertices];
		point[] points = new point[vertices];
		boolean []visited = new boolean[vertices];
		
		//all visited is false initially
		for (int i = 0; i < vertices; i++) {
			visited[i] = false;
		}
		
		for (int i = 0; i < vertices; i++) {
			int x, y;
			System.out.print("x = ");
			x = read.nextInt();
			System.out.print("y = ");
			y = read.nextInt();
			points[i] = new point(x, y);
		}
		
		//set the starting vertex A as true.
		int i = 0;
		double startTime = System.nanoTime(); //start recording time
		System.out.println("Starting point: ");
		A = farthest(points); //get vertex A
		System.out.println(A);
		visited[A] = true; 
		path[0] = A;
		
		for (i = 1; i < vertices; i++) {
			B = nearest(points, A, visited); //B is the next nearest point
			A = B; //shift to B as the new A
			path[i] = A; //add the index to the path
			visited[A] = true; //Vertex B, or A now becomes the new visited
		}
		double endTime   = System.nanoTime();
		//just print out the path
		for (int j = 0; j < vertices; j++) {
			points[path[j]].print();
		}
		points[path[0]].print();
		System.out.println();
		 // calculate the length of the Hamiltonian cycle
		int dist = 0;
		for (i = 0; i < points.length - 1; i++)  {
			dist += Math.abs(points[path[i]].x - points[path[i+1]].x) + Math.abs(points[path[i]].y - points[path[i+1]].y);
		}
		dist += Math.abs(points[path[0]].x - points[path[points.length - 1]].x) + Math.abs(points[path[0]].y - points[path[points.length - 1]].y);
		  
		System.out.println("The Hamiltonian Weight is: " + dist);
		double totalTime = (endTime - startTime)/1000000/1000; //convert time to seconds
		System.out.println("Elapsed Time: " + totalTime + " seconds");
		read.close();
	}
	
	public static void printCycle(point[] v) {
		for (int i = 0 ; i < v.length; i++) {
			v[i].print();
		}
		v[0].print();
		System.out.println();
	}
	
	public static int farthest(point [] v) {
		int max_dist = 0;
		int index = 0;
		int dist;
		  
		for(int i=0; i < v.length-1; i++)
			for(int j=0; j < v.length;j++) {
				dist = Math.abs(v[i].x - v[j].x) + Math.abs(v[i].y - v[j].y);
				if (max_dist < dist){
					max_dist = dist;
					index = i;
				}
		    }
		  return index;
		
	}
	
	public static int nearest(point [] v, int A, boolean[] visited) {
		int min_dist = 99999999;
		int index = 0;
		int dist;
		
		for(int j = 0; j < v.length;j++) {
			if (visited[j] == true) { //if the vertex is already visited skip the iteration of the loop
				continue;
			}
			dist = Math.abs(v[A].x - v[j].x) + Math.abs(v[A].y - v[j].y); //vertex of everything except A, and all visited values.
			if (dist < min_dist){
				min_dist = dist;
				index = j;
			}
		}
		
		visited[index] = true;
		return index;
	}
}
