import java.util.Arrays;
import java.util.Scanner;

public class Rectilinear {
	public static void main(String [] args) {
		Scanner read = new Scanner(System.in);
		
		System.out.println("Enter the number of vertices (>2): ");
		int vertices = read.nextInt();
		
		point[] points = new point[vertices]; 
		
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
		int weight = 0;
		for (int i = 0; i < points.length - 1; i++) {
			weight += distance(points[i], points[i+1]);
		}
		
		int best = weight;
		System.out.println();
		permutation(points, vertices, best);
		read.close();
	}
	
	
	public static void permutation(point[] v, int n, int best)
    {		
		
        if (n == 1) {
        	int weight = 0;
    		for (int i = 0; i < v.length - 1; i++) {
    			int d = Math.abs(v[i].x - v[i+1].x) + Math.abs(v[i].y - v[i+1].y);
    			weight += d;
    		}
    		
    		//System.out.println("Total weight: " + weight);
    		
    		if (weight < best) {
    			best = weight;
    			for (int k = 0; k < v.length; k++) {
                	v[k].print();
                }
                v[0].print();
                System.out.println();
    			
    		}
    		
    		System.out.println("Current best weight: " + best);
        }
        else {
            for (int f= 0; f < n; f++) {
                permutation(v, n - 1, best);
                if (n % 2 == 1) {
                    swap(v, 0, n - 1);
                }
                else {
                    swap(v, f, n - 1);
                }
            }
        }
    }
	
	private static void swap(point[] v, int i, int j) {
	    point t = v[i];
	    v[i] = v[j];
	    v[j] = t;
	}
	
	public static void nearest(point [] v, int n) {
		
	}
	
	public static int distance(point a, point b) {
		int d;
		int xPoint = Math.abs(a.x - b.x);
		int yPoint = Math.abs(a.y - b.y);
		d = xPoint + yPoint;
		
		return d;
	}
}

class point {
	int x, y;
	point(int x1, int y1) {
		this.x = x1;
		this.y = y1;
	}
	void print() {
		System.out.print("(" + this.x + ", " + this.y + ")   ");
	}
}
