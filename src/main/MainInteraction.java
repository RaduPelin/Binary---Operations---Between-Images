package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import image.MyBitmap;
import interaction.Interactions;
import interaction.MyFile;

public class MainInteraction {
	private static Scanner scan;

	public static void main(String [] args) {
		
		String path;
		long startTime, stopTime;
		
		scan = new Scanner(System.in);
		Interactions interaction = new Interactions();
		
		System.out.println("Give the path for the first image: ");
		path = scan.next();
		
		startTime = System.nanoTime();
		MyFile file1 = new MyFile("bmp", path);
		MyBitmap image1 = new MyBitmap(file1);
		stopTime = System.nanoTime();
		System.out.println("Time to read the first image:  "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		System.out.println("Give the path for the second image: ");
		path = scan.next();
		
		startTime = System.nanoTime();
		MyFile file2 = new MyFile("bmp", path); 
		MyBitmap image2 = new MyBitmap(file2);
		stopTime = System.nanoTime();
		System.out.println("Time to read the second image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		System.out.println("Give the path of the result directory: ");
		path = scan.next();
		
		startTime = System.nanoTime();
		try {
			Files.createDirectories(Paths.get(path));
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		stopTime = System.nanoTime();
		System.out.println("Time to create the directory: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		startTime = System.nanoTime();
		interaction.myBitmapToFile(image1, path, "simpleFirst.bmp");
		stopTime = System.nanoTime();
		System.out.println("Time to save the first simple image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		startTime = System.nanoTime();
		interaction.myBitmapToFile(image2, path, "simpleSecond.bmp");
		stopTime = System.nanoTime();
		System.out.println("Time to save the second simple image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		simpleInteraction("SimpleAnd.bmp", path, file1, file2, "and");
		simpleInteraction("SimpleNand.bmp", path, file1, file2, "nand");
		simpleInteraction("SimpleOr.bmp", path, file1, file2, "or");
		simpleInteraction("SimpleNor.bmp", path, file1, file2, "nor");
		simpleInteraction("SimpleXor.bmp", path, file1, file2, "xor");
		simpleInteraction("SimpleXnor.bmp", path, file1, file2, "xnor");
		
		startTime = System.nanoTime();
		interaction.negate(image1);
		stopTime = System.nanoTime();
		System.out.println("Time to generate the negative form of the first image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		startTime = System.nanoTime();
		interaction.myBitmapToFile(image1, path, "NegativeFirst.bmp");
		stopTime = System.nanoTime();
		System.out.println("Time to save the negative form of the first image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");	
		
		startTime = System.nanoTime();
		interaction.negate(image2);
		stopTime = System.nanoTime();
		System.out.println("Time to generate the negative form of the second image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		startTime = System.nanoTime();
		interaction.myBitmapToFile(image2, path, "NegativeSecond.bmp");
		stopTime = System.nanoTime();
		System.out.println("Time to save the negative form of the second image: "+ (double)(stopTime - startTime)/1000000000 + " seconds");		
	
		complexInteraction("ComplexAnd.bmp", path, file1, file2, "and");
		complexInteraction("ComplexNand.bmp", path, file1, file2, "nand");
		complexInteraction("ComplexOr.bmp", path, file1, file2, "or");
		complexInteraction("ComplexNor.bmp", path, file1, file2, "nor");

		shift(">>", path, file1, file2);
		shift("<<", path, file1, file2);
		shift("red", ">>", path, file1, file2);
		shift("blue", ">>", path, file1, file2);
		shift("green", ">>", path, file1, file2);
		shift("red", "<<", path, file1, file2);
		shift("blue", "<<", path, file1, file2);
		shift("green", "<<", path, file1, file2);
	}
	
	public static void simpleInteraction(String fileName, String path, MyFile file1, MyFile file2, String operation) {
		MyBitmap image1 = new MyBitmap(file1);
		MyBitmap image2 = new MyBitmap(file2);
		long startTime, stopTime;
		
		Interactions interaction = new Interactions();
		
		startTime = System.nanoTime();
		interaction.addOperation(operation, image1, image2);
		stopTime = System.nanoTime();
		System.out.println("Time for simple " + operation.toUpperCase() + " interaction: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		startTime = System.nanoTime();
		interaction.myBitmapToFile(image1, path, fileName);
		stopTime = System.nanoTime();
		System.out.println("Time to save the " + operation.toUpperCase() +  " result: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
	}
	
	public static void complexInteraction(String fileName, String path, MyFile file1, MyFile file2, String operation) {
		MyBitmap image1 = new MyBitmap(file1);
		MyBitmap image2 = new MyBitmap(file2);
		long startTime, stopTime;
		
		Interactions interaction = new Interactions();
		
		startTime = System.nanoTime();
		interaction.negate(image1);
		interaction.addOperation(operation, image1, image2);
		stopTime = System.nanoTime();
		System.out.println("Time for complex " + operation.toUpperCase() + " interaction: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		
		startTime = System.nanoTime();
		interaction.myBitmapToFile(image1, path, fileName);
		stopTime = System.nanoTime();
		System.out.println("Time to save the complex " + operation.toUpperCase() +  " result: "+ (double)(stopTime - startTime)/1000000000 + " seconds");
	}
	
	public static void shift(String operator,String path, MyFile file1, MyFile file2) {
		MyBitmap image1 = new MyBitmap(file1);
		MyBitmap image2 = new MyBitmap(file2);
		long startTime, stopTime;
		
		Interactions interaction = new Interactions();
		for (int i = 1; i < 3; i++) {
			startTime = System.nanoTime();
			interaction.addOperation(operator, image1, i);
			stopTime = System.nanoTime();
			System.out.println("Time to shift the first image with " + i + ": "+ (double)(stopTime - startTime)/1000000000 + " seconds");
			
			startTime = System.nanoTime();
			if (operator.equals("<<")) {
				interaction.myBitmapToFile(image1, path, "ShiftLeftWith" + i + "First.bmp");
			} else {
				interaction.myBitmapToFile(image1, path, "ShiftRightWith" + i + "First.bmp");
			}
			stopTime = System.nanoTime();
			System.out.println("Time to save the first shifted image with " + i + ": "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		}		
		for (int i = 1; i < 3; i++) {
			startTime = System.nanoTime();
			interaction.addOperation(operator, image2, i);
			stopTime = System.nanoTime();
			System.out.println("Time to shift the second image with " + i + ": " + (double)(stopTime - startTime)/1000000000 + " seconds");
			
			startTime = System.nanoTime();
			if (operator.equals("<<")) {
				interaction.myBitmapToFile(image2, path, "ShiftLeftWith" + i + "Second.bmp");
			} else {
				interaction.myBitmapToFile(image2, path, "ShiftRightWith" + i + "Second.bmp");
			}
			stopTime = System.nanoTime();
			System.out.println("Time to save the second shifted image with " + i + ": " + (double)(stopTime - startTime)/1000000000 + " seconds");
		}		
	}
	
	public static void shift(String color, String operator,String path, MyFile file1, MyFile file2) {
		MyBitmap image1 = new MyBitmap(file1);
		MyBitmap image2 = new MyBitmap(file2);
		long startTime, stopTime;
		
		Interactions interaction = new Interactions();
		for (int i = 1; i < 5; i++) {
			if (operator.equals(">>")&& i > 2) {
				continue;
			}
			
			startTime = System.nanoTime();
			interaction.addOperation(color, operator, image1, i);
			stopTime = System.nanoTime();
			System.out.println("Time to shift the first image, color - " + color + " with " + i + ": "+ (double)(stopTime - startTime)/1000000000 + " seconds");
			
			startTime = System.nanoTime();
			if (operator.equals("<<")) {
				interaction.myBitmapToFile(image1, path, color.substring(0,1).toUpperCase() + color.substring(1,color.length()) + "ShiftLeftWith" + i + "First.bmp");
			} else {
				interaction.myBitmapToFile(image1, path, color.substring(0,1).toUpperCase() + color.substring(1,color.length()) +  "ShiftRightWith" + i + "First.bmp");
			}
			stopTime = System.nanoTime();
			System.out.println("Time to save the first image, color - " + color + " shifted with " + i + ": "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		}		
		for (int i = 1; i < 5; i++) {
			if (operator.equals(">>")&& i > 2) {
				continue;
			}
			
			startTime = System.nanoTime();
			interaction.addOperation(color, operator, image2, i);
			stopTime = System.nanoTime();
			System.out.println("Time to shift the second image, color - " + color + " with " + i + ": "+ (double)(stopTime - startTime)/1000000000 + " seconds");
			
			startTime = System.nanoTime();
			if (operator.equals("<<")) {
				interaction.myBitmapToFile(image2, path, color.substring(0,1).toUpperCase() + color.substring(1,color.length()) + "ShiftLeftWith" + i + "Second.bmp");
			} else {
				interaction.myBitmapToFile(image2, path, color.substring(0,1).toUpperCase() + color.substring(1,color.length()) +  "ShiftRightWith" + i + "Second.bmp");
			}
			stopTime = System.nanoTime();
			System.out.println("Time to save the second image, color - " + color + " shifted with " + i + ": "+ (double)(stopTime - startTime)/1000000000 + " seconds");
		}		
	}
}


