import java.util.Scanner;

public class Nearest {
	public static void main(String [] args) {
		Scanner read = new Scanner(System.in);
		
		System.out.println("Enter the points; make sure they are distinct: ");
		int vertices = read.nextInt();
		point[] points = new point[vertices];
		boolean []visited = new boolean[vertices];
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
		
		read.close();
	}
	
	public static void printCycle(point[] v) {
		for (int i = 0 ; i < v.length; i++) {
			v[i].print();
		}
		v[0].print();
		System.out.println();
	}
	
	public static void farthest(point [] v) {
		
		
	}
	
	public static void nearest(point [] v) {
		
	}
}
