import java.util.Scanner;

public class Rectilinear {
	static int bestDist;
	public static void main(String [] args) {
		Scanner read = new Scanner(System.in);
		
		System.out.println("Enter the number of vertices (>2): ");
		int vertices = read.nextInt();
		int dist;
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
		
		
		for (int i = 0; i < points.length; i++) {
    		points[i].print();
    	}
    	System.out.println();
    	double startTime = System.nanoTime();
		dist = farthest(points);
		bestDist = vertices*dist;
		
    	
		for (int i = 0 ; i < points.length; i++) {
			bestA[i] = points[i];
		}
		System.out.println();
		
		permutation(points, vertices, bestA);
		printCycle(bestA);
		System.out.println("The best distance: " + bestDist);
		double endTime   = System.nanoTime();
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
		int max = 0, dist;
		for (int i = 0; i < v.length - 1; i++) {
			for (int j = 0; j < v.length; j++) {
				dist = Math.abs(v[i].x - v[j].x) + Math.abs(v[i].y - v[j].y);
				if (max < dist) {
					max = dist;
				}
			}
		}
		return max;
	}
	
	public static void permutation(point[] v, int n, point[] bestA) {		
        if (n == 1) {
        	int dist = 0;
        	for (int i = 0; i < v.length - 1; i++) {
        		int d = Math.abs(v[i].x - v[i+1].x) + Math.abs(v[i].y - v[i+1].y);
        		dist += d;
        	}
        	dist += Math.abs(v[v.length - 1].x - v[0].x) + Math.abs(v[v.length - 1].y - v[0].y);
        	//find the least distance
        	if (dist < bestDist) {
        		bestDist = dist;
        		for (int k = 0; k < v.length; k++) {
                	bestA[k] = v[k]; //find the best array
                }
        	}        	
        } 
        else {
        	for (int i = 0; i < n - 1; i++) {
        		permutation(v, n - 1, bestA);
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
        	permutation(v, n - 1, bestA);
        }
    }
}
