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
		
		int i = 0, j = 1, weight = 0;
		while(i < points.length - 1 && j < points.length) {
			weight += distance(points[i], points[j]);
			i++;
			j++;
		}
		int best = weight;
		
		System.out.println();
		permutation(points, vertices, best);
		read.close();
	}
	
	
	public static void permutation(point[] v, int n, int best)
    {		
        if (n == 1) {
        	int i = 0, j = 1, weight = 0;
            for (int k = 0; k < v.length; k++) {
            	v[k].print();
            }
            System.out.println();
            
    		
    		while(i < v.length - 1 && j < v.length) {
    			weight += distance(v[i], v[j]);
    			i++;
    			j++;
    		}
    		
    		if (weight < best) {
    			best = weight;
    		}
    		
    		System.out.println("Total weight: " + weight);
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
	
	 private static void swap(point[] v, int i, int j)
	 {
	     point t = v[i];
	     v[i] = v[j];
	     v[j] = t;
	 }
	
	public static double distance(point a, point b) {
		double d;
		double xPoint = Math.abs(a.getX() - b.getX());
		double yPoint = Math.abs(a.getY() - b.getY());
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
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	void print() {
		System.out.print("(" + this.x + ", " + this.y + ")   ");
	}
}
