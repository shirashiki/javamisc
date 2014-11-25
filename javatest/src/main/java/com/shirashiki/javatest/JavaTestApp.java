package com.shirashiki.javatest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shirashiki.javatest.bo.Operation;
import com.shirashiki.javatest.bo.OperationFactory;

/**
 * 
 * Write a Java command line program that takes a list of arguments as follows:
 * operator arg1 arg2 arg3 ... argx argN can be of the form: 1. abcd (a String)
 * 2. 123 (an int) 3. 123.33 (a double) 4. employee(John,Smith,33000) (an
 * Employee)
 * 
 * Employee can be defined by a class with firstName (String), lastName
 * (String), and salary (double) properties.
 * 
 * Operator is one of the following: opAdd, opConcatenate, opSortAsc - opAdd:
 * add numbers. Adds any argument that is numeric. Ignores non numeric
 * arguments.- - opConcatenate: concatenate strings. Concatenates any argument
 * that is a string or numeric. Ignores employee arguments. - opSortAsc: sort
 * numbers and strings in ascending order. If an employee is encountered, use
 * its lastName.
 * 
 * Examples of how to use it:
 * 
 * java -cp package.jar com.shirashiki.javatest.JavaTestApp opAdd 1 2 3
 * java -cp package.jar com.shirashiki.javatest.JavaTestApp opConcatenate
 * banana apple 1 2 3 employee(John,Smith,33000)
 * java -cp package.jar com.shirashiki.javatest.JavaTestApp opSortAsc 
 * 99 11 1 9
 *
 * @author Silvio Hirashiki - silvio.hirashiki@gmail.com
 */
public class JavaTestApp {
	/**
	 * Receives the arguments and sends the response in the console
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// cannot have empty argument
		if (args.length < 1) {
			throw new IllegalArgumentException("Empty argument, need to inform operation and ars, example: JavaTestApp opAdd 3 2 1");
		}

		List<String> argList = new ArrayList<String>(Arrays.asList(args));
		String operationName = argList.get(0);

		// removes first element which contains operation
		argList.remove(0);

		// Creates the object which will perform the operation
		Operation op = OperationFactory.createOperation(operationName);

		// Executes the operation and prints result in the console
		String opResult = "";
		opResult = op.getResult(argList);

		System.out.println(opResult);

	}
}
