import java.util.Scanner;

//1 = dark
//0 = light
public class Disks {
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		System.out.print("Enter the number of disks: ");
		
		int numDisks;
		numDisks = read.nextInt();
		numDisks *=2;
		
		//disks are implemented as an array of 1's and 0's
		int [] disks = new int[numDisks];
		for (int i = 0; i < numDisks; i++) {
			if (i%2 == 0) {
				disks[i] = 1;
			}
			else {
				disks[i] = 0;
			}
		}
		
		displayDisks(disks); //display initial disks
		//greedy(disks); //perform the first greedy algorithm.
		lawnmower(disks); //perform the first lawnmower algorithm.
		
		read.close();
	}
	
	
	public static void greedy(int []arr) {
		boolean compare = true;
		int swapCounter = 0;
		
		while(compare) {
			compare = false;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i+1]) {
					swap(i, i+1, arr);
					swapCounter++;
					compare = true;
				}
			}
			displayDisks(arr);
		}
		System.out.println("Number of swaps: " + swapCounter);
	}
	
	public static void lawnmower(int []arr) {
		int swapCounter = 0;
		boolean compare = true;
		while(compare) {
			int i = 0, j = arr.length - 1;
			compare = false;
			while(i < arr.length - 1) {
				if (arr[i] > arr[i+1]) {
					swap(i, i+1, arr);
					compare = true;
					swapCounter++;
				}
				i++;
			}
			while(j > 0) {
				if (arr[j] < arr[j-1]) {
					swap(j, j - 1, arr);
					compare = true;
					swapCounter++;
				}
				j--;
			}
			displayDisks(arr);
		}
	}
	
	public static void swap(int i, int j, int []arr) {
		int c = arr[j];
		arr[j] = arr[i];
		arr[i] = c;
	}
	
	public static void displayDisks(int [] arr) {
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] == 1)
				System.out.print("d ");
			else 
				System.out.print("l ");
		}
		System.out.println();
	}
}
