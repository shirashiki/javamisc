package com.shirashiki.javatest.test.unit;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.Test;
import com.shirashiki.javatest.bo.*;




/**
 * Unit test for simple App.
 */
public class AppTest {
	
	/**
	 * Tests Add Operation
	 * @throws Exception
	 */
	@Test
	public void testAddOperation() throws Exception{
		Operation op = OperationFactory.createOperation("opAdd");
		String expected = "15.12";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("chocolate");
		argList.add("0.1");
		argList.add("13");
		argList.add("2.02");
		argList.add("employee(James,Brown,60000");
		argList.add("banana");
		argList.add("apple");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
		
	}
	/**
	 * Tests Add Operation, only numbers in argument
	 * @throws Exception
	 */
	@Test
	public void testAddOperationOnlyNumbers() throws Exception{
		Operation op = OperationFactory.createOperation("opAdd");
		String expected = "45.12001";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("10.1");
		argList.add("13");
		argList.add("22.02001");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
		
	}
	
	/**
	 * Tests Add Operation, only non numbers in arguments
	 * @throws Exception
	 */
	@Test
	public void testAddOperationNonNumbers() throws Exception{
		Operation op = OperationFactory.createOperation("opAdd");
		String expected = "0.0";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("Banana");
		argList.add("Carrot");
		argList.add("employee(James,Brown,60000");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
		
	}
	
	/**
	 * Tests Concatenate Operation
	 * @throws Exception
	 */
	@Test
	public void testConcatenateOperation() throws Exception{
		Operation op = OperationFactory.createOperation("opConcatenate");
		String expected = "chocolate0.1132.02bananaapple";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("chocolate");
		argList.add("0.1");
		argList.add("13");
		argList.add("employee(James,Brown,60000");
		argList.add("2.02");
		argList.add("banana");
		argList.add("employee(Ray,Charles,61000");
		argList.add("apple");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
	
	/**
	 * Tests Concatenate Operation: only numbers
	 * @throws Exception
	 */
	@Test
	public void testConcatenateOperationOnlyNumbers() throws Exception{
		Operation op = OperationFactory.createOperation("opConcatenate");
		String expected = "123";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("1");
		argList.add("2");
		argList.add("3");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
	
	/**
	 * Tests Sort Operation: without employee
	 * @throws Exception
	 */
	@Test
	public void testSortWithoutEmployee() throws Exception{
		Operation op = OperationFactory.createOperation("opSortAsc");
		String expected = "\"1\" \"2\" \"3\" \"11\" ";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("3");
		argList.add("2");
		argList.add("1");
		argList.add("11");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
	
}
