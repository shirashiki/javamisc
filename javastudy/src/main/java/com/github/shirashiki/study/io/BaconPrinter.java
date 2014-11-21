package com.github.shirashiki.study.io;

/**
 * Program made for a job application at LightSpeed. Requirements:
 * Write a program that prints the numbers from 1 to 100. But for 
 * multiples of three print "Kevin" instead of the number and for 
 * the multiples of five print "Bacon". 
 * For numbers which are multiples of both three and five 
 * print "Kevin Bacon".
 * 
 * @author silvio.hirashiki@gmail.com
 *
 */
public class BaconPrinter {

	
	public static void main(String[] args) {
		BaconPrinter baconPrint = new BaconPrinter();
		baconPrint.printHundred();

	}
	
	/**
	 * Prints numbers from 1 to 100. See class documentation.
	 * I prefer to be very explicit when coding
	 */
	public void printHundred() {
		
		// loops from numbers from 1 to 100 to print
		for (int i=1; i < 101 ; i++) {
			
			if ( (i % 3 == 0) & (i % 5 == 0) ) {
				// if multiple of 3 and 5
				System.out.println("Kevin Bacon");
			} else if (i % 3 == 0) {
				// if multiple of 3
				System.out.println("Kevin");
			} else if (i % 5 == 0) {
				// if multiple of 5
				System.out.println("Bacon");
			} else {
				// otherwise
				System.out.println(i);
			}
		}
		
	}
	
}
