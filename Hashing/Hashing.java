/*Angelo Salac
 * CPSC 335
 * Programming Assignment 4
 */

import java.io.*;
import java.util.Scanner;

public class Hashing {
	public static void main(String [] args) {
		Scanner read = new Scanner(System.in);
		
		int tableSize = 17;
		String [] T1 = new String[tableSize];
		String [] T2 = new String[tableSize];
		
		System.out.print("Enter the name of the file: ");
		String fileName = read.nextLine();
		
		try {
			hashFile("C:\\Users\\gsalac7\\Desktop\\" + fileName + ".txt", T1, T2, tableSize);
		} catch (IOException ex) {
			System.out.println("Error reading the file");
		}
		
		System.out.println();
		
		System.out.println("Here is the hash table 1 using hash function f1: ");
		displayTable(T1, T2);
		
		read.close();
	}
	
	public static void hashFile(String fname, String [] T1, String[] T2, int tableSize) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fname));
		try {	
			String line;
			while((line = in.readLine())!=null) {
				hash(T1, T2, hashFunction1(line, tableSize), hashFunction2(line, tableSize), line);
			}
		} catch(Exception Ex){
			System.out.println("File not found");
		}
		
		in.close();
	} 
	
	public static void hash(String [] T1, String [] T2, int key1, int key2, String line) {
		if (T1[key1] == null) {
			T1[key1] = line;
			System.out.println("String <" + line + "> will be placed at Table 1[" + key1 + "]");
		} else {
			T2[key2] = line;
			System.out.println("String <" + line + "> will be placed at Table 2[" + key2 + "]");
		}
	}
	
	public static int hashFunction1(String line, int tableSize) {
		int value = 0;
		
		for (int i = 0; i < line.length(); i++) {
			value = line.charAt(i) * (int)Math.pow(31, i);
			
		}
		value = value % tableSize;
		if (value < 0) {
			value += tableSize;
		}
		return value;
	}
	
	public static int hashFunction2(String line, int tableSize) {
		int value = 0;
		
		for (int i = 0; i < line.length(); i++) {
			value = line.charAt(line.length() - i - 1) * (int)Math.pow(31, i);
		}
		value = value % tableSize;
		
		return value;
	}
	
	public static void displayTable(String [] T1, String []T2) {
            System.out.println();
            System.out.println("Table 1 Hash Table: ");	
            for (int i = 0; i < T1.length; i++) {
		System.out.println(T1[i]);
            }
                
            System.out.println();
            System.out.println("Table 2 Hash Table: ");
            for (int i = 0; i < T1.length; i++) {
		System.out.println(T2[i]);
            }
	}
}
