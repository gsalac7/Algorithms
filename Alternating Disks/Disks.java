/*Angelo Salac
 * 2/24/17
 * Programming Assignment 1
 * CPSC 335
 */

import java.util.Scanner;

public class Disks {
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		System.out.print("Enter the number of disks: ");
		
		int numDisks;
		numDisks = read.nextInt();
		numDisks *=2;
		
		//disks are implemented as an array of 1's and 0's
		//1 = dark
		//0 = light
		int [] disks = new int[numDisks];
		initializeDisks(disks);
		
		
		System.out.println("This is the initial placement of the disks: ");
		displayDisks(disks); //display initial disks
		System.out.println("Now implementing left-to-right algorithm");
		greedy(disks); //perform the first greedy algorithm.
		
		initializeDisks(disks);
		System.out.println("This is the initial placement of the disks: ");
		displayDisks(disks); //display initial disks
		System.out.println("Now implementing lawnmower algorithm");
		lawnmower(disks); //perform the first lawnmower algorithm.
		
		read.close();
	}
	
	//assign the array values
	public static void initializeDisks(int [] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i%2 == 0) {
				arr[i] = 1;
			}
			else {
				arr[i] = 0;
			}
		}
	}
	public static void greedy(int []arr) {
		int swapCounter = 0;
		for (int x = 1; x <= arr.length; x++) { //loop while the disks aren't sorted
			for (int i = 0; i < arr.length - 1; i++) { //go through loop from left to right, then restart
				if (arr[i] > arr[i+1]) {	//if the next disk is light, then swap with the dark disk.
					swap(i, i+1, arr); //swap
					swapCounter++; //count the swap
				}
			}
		} //go back to the left and loop again
		displayDisks(arr);
		System.out.println("Number of swaps: " + swapCounter);
	}
	
	public static void lawnmower(int []arr) {
		int swapCounter = 0;
		for (int i = 0; i < arr.length; i++) { //repeat until all disks are swapped
			for (int k = 0; k < arr.length - 1; k++) { //go from left to right
				if (arr[k] > arr[k + 1]) {
					swap(k, k + 1, arr);
					swapCounter++;
				}
			}
			for (int j = arr.length - 1; j > 0; j--) { //go from right to left
				if (arr[j] < arr[j - 1]) {
					swap(j, j - 1, arr);
					swapCounter++;
				}
			}
		}
		displayDisks(arr);
		System.out.println("Number of swaps: " + swapCounter);
	}
	
	//swap function
	public static void swap(int i, int j, int []arr) {
		int c = arr[j];
		arr[j] = arr[i];
		arr[i] = c;
	}
	
	//function to display the disks
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
