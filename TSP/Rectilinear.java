import java.util.Scanner;

public class Rectilinear {
	public static void main(String [] args) {
		Scanner read = new Scanner(System.in);
		
		System.out.println("Enter the number of vertices (>2): ");
		int vertices = read.nextInt();
		
		point[] points = new point[vertices]; 
		point[] bestA = new point[vertices];
		System.out.println("Enter the points; make sure they are distinct: ");
		for (int i = 0; i < vertices; i++) {
			int x, y;
			System.out.print("x = ");
			x = read.nextInt();
			System.out.print("y = ");
			y = read.nextInt();
			points[i] = new point(x, y);
		}
		
		//Assign first permutation's weight as the best weight
		for (int i = 0; i < points.length; i++) {
    		points[i].print();
    	}
    	System.out.println();
		int weight = 0;
		for (int i = 0; i < points.length - 1; i++) {
			int d = Math.abs(points[i].x - points[i+1].x) + Math.abs(points[i].y - points[i+1].y);
			weight += d;
		}
		int best = weight;
		
		for (int i = 0 ; i < points.length; i++) {
			bestA[i] = points[i];
		}
		System.out.println();
		
		permutation(points, vertices, best, bestA);
		printCycle(bestA);
		System.out.println("The best distance: " + best);
		read.close();
	}
	
	public static void printCycle(point[] v) {
		for (int i = 0 ; i < v.length; i++) {
			v[i].print();
		}
		v[0].print();
		System.out.println();
	}
	
	public static void permutation(point[] v, int n, int best, point[] bestA) {		
        if (n == 1) {
        	int dist = 0;
        	for (int i = 0; i < v.length - 1; i++) {
        		
        		int d = Math.abs(v[i].x - v[i+1].x) + Math.abs(v[i].y - v[i+1].y);
        		dist += d;
        	}
        	 //find the least distance
        	if (dist < best) {
        		best = dist;
        		for (int k = 0; k < v.length; k++) {
                	bestA[k] = v[k];
                }
        	}        	
        } 
        else {
        	for (int i = 0; i < n - 1; i++) {
        		permutation(v, n - 1, best, bestA);
        		if (n%2 == 0) {
        			point temp = v[i];
        			v[i] = v[n - 1];
        			v[n - 1] = temp;
        		}
        		else {
        			point temp = v[0];
        			v[0] = v[n - 1];
        			v[n - 1] = temp;
        		}
        	}
        	permutation(v, n - 1, best, bestA);
        }
    }
}