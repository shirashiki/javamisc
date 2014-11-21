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
 * and salary (double) properties.
 * 
 * Operator is one of the following: opAdd, opConcatenate, opSortAsc
 * - opAdd: add numbers. Adds any argument that is numeric. Ignores non numeric arguments.-
 * - opConcatenate: concatenate strings. Concatenates any argument that is a string or numeric. Ignores employee arguments.
 * - opSortAsc: sort numbers and strings in ascending order. If an employee is encountered, use its lastName.
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
    
    	/*
    	for (int i= 0; i < args.length; i++){
    		
    		System.out.println(args[i]);
    	}
    	*/
    	
    	if (args.length < 1){
    		throw new IllegalArgumentException("empty argument");
    	}
    	
    	String operationName = args[0].trim();
    	
    	Operation op;
    	String opResult = "";
    	if (operationName.equals("opAdd")) {
    		op = new AddOperation();
    		opResult = op.getResult(args);
    	}
    	
    	System.out.println(operationName + "=[" + opResult + "]");
    	
    }
}
