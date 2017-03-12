package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
            
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        int value = 0;
        int flag = 0;
        
        while (flag == 0){
        	value  = Controller(kb);
        	//this is quit
        	if (value == 1){
        		flag = 1;
        	}
        	//this is show
        	else if (value == 0){
        		flag = 0;
        	}else {
        		flag = 0;
        		System.out.println("error processing: " + kb);
        	}
        
     
        }
        
        // System.out.println("GLHF");
        
        
        
        /* Write your code above */
        
        System.out.flush();

    }
    
    private static int Controller(Scanner kb){
    	System.out.println("critters>");
    	//String user_input = kb.next();
    	
    	int flag = 0;
    	int value = 0;
    	while (flag == 0){
    		String user_input = kb.next();
    		String total = user_input;
    		flag = 1;
    	if (user_input.equals("quit")){
    		return(1);
    	}else if (user_input.equals("show")){
    		Critter.displayWorld();
    		return(0);
    	}else if (user_input.contains("step")){
    		if(kb.hasNextInt()){
    			try{
    				value = kb.nextInt();
    				total = total + " " +String.valueOf(value);
    				for(int i = 0; i < value; i++){
    					Critter.worldTimeStep();
    					
    				}
    			//return (value);
    				return(0);
    			}
    			catch(InputMismatchException e){
    				flag = 0;
    				System.out.println("error processing: " + total);
    			}
    		}else{
    			Critter.worldTimeStep();
    		}
    	}else if (user_input.equals("seed")){
    		int name;
    		if(kb.hasNext()){
    			
    			name = kb.nextInt();
    			total = total + " " +String.valueOf(name);
    			try{
    				Critter.setSeed(name);
    			}
    			catch(InputMismatchException e){
    				flag = 0;
    				System.out.println("error processing: " + total);
    			}
    		}
    		
    		return(0);	
    	}
    	else if (user_input.equals("stats")){
    		String name;
    		if(kb.hasNext()){
    			name = kb.next();
    			total = total + " " +String.valueOf(name);
    			List<Critter> crittor = new ArrayList<Critter>();
    			try {
					crittor = Critter.getInstances(name);
					Critter.runStats(crittor);
				} catch (InvalidCritterException e) {
					// TODO Auto-generated catch block
					flag = 0;
					System.out.println("error processing: " + kb);
				}
    		}
    		return 0;
    	}
    	else if (user_input.equals("make")){
    		String class_name;
    		if(kb.hasNext() && !(kb.hasNextInt())){
    			class_name = kb.next();
    			total = total + class_name;
    		if(kb.hasNextInt()){
    			try{
    				value = kb.nextInt();
    				total = total + " " + String.valueOf(value);
    				for(int i = 0; i < value; i++){
    					try {
							Critter new_Critter = Critter.makeCritter(class_name);
							
							
						} catch (InvalidCritterException e) {
							// TODO Auto-generated catch block
							flag = 0;
							System.out.println("error processing: " + total);
						}
    				}
    				
    				return(0);
    			}
    			catch(InputMismatchException e){
    				flag = 0;
    				System.out.println("error processing: " + total);
    			}
    		}else{
    			try {
					Critter.makeCritter(class_name);
				} catch (InvalidCritterException e) {
					// TODO Auto-generated catch block
					flag = 0;
					System.out.println("error processing: " + total);
				}
    		}
    		}
    	}
    	
    	else {
    		flag = 0;
    		kb.nextLine();
    		System.out.println("error processing: " + total);
    	}
    	
    	}
    	return 0;
    }
}
