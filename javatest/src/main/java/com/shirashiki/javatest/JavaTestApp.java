package com.shirashiki.javatest;

/**
 * 
 * Write a Java command line program that takes a list of arguments as follows:
 * operator arg1 arg2 arg3 ... argx
 * argN can be of the form:
 * 1. abcd (a String)
 * 2. 123 (an int)
 * 3. 123.33 (a double)
 * 4. employee(John,Smith,33000) (an Employee)
 * 
 * Employee can be defined by a class with firstName (String), lastName (String), 
 * and salary (double) properties
 *
 * @author Silvio Hirashiki - silvio.hirashiki@gmail.com
 */
public class JavaTestApp 
{
	/**
	 * Receives the arguments and sends the response in the console
	 * @param args
	 */
    public static void main( String[] args )
    {
        System.out.println(args[0] + args[1]);
    }
}
