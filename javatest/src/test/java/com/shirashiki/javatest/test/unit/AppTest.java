package com.shirashiki.javatest.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import com.shirashiki.javatest.bo.*;
import com.shirashiki.javatest.model.Employee;




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
	 * Tests Sort Operation: test with numbers, strings and employees
	 * @throws Exception
	 */
	@Test
	public void testSort() throws Exception{
		Operation op = OperationFactory.createOperation("opSortAsc");
		
		List<String> expectedList = new ArrayList<String>();

		expectedList.add("1");
		expectedList.add("1.12");
		expectedList.add("2.1");
		expectedList.add("2.13");
		expectedList.add("3");
		expectedList.add("7");
		expectedList.add("11");
		expectedList.add("20");
		expectedList.add("Apple");
		expectedList.add("employee(Susan,Ames,22001.23)");
		expectedList.add("employee(Melvin,Barnes,11000.12)");
		expectedList.add("Cardamon");
		expectedList.add("employee(John,Smith,22000.01)");
		expectedList.add("Watermelon");
		expectedList.add("employee(Walter,White,33000.02)");
		
		// Generates a string representation of the sorted list
		StringBuilder strBuild = new StringBuilder("");
		for (String s : expectedList) {
			strBuild.append("\"");
			strBuild.append(StringEscapeUtils.escapeJava(s));
			strBuild.append("\" ");
		}
		String expected = strBuild.toString();
		
		
		ArrayList<String> argList = new ArrayList<String>();
		
		argList.add("Watermelon");
		argList.add("Cardamon");
		argList.add("Apple");
		argList.add("employee(John,Smith,22000.01)");
		argList.add("employee(Susan,Ames,22001.23)");
		argList.add("employee(Walter,White,33000.02)");
		argList.add("7");
		argList.add("2.1");
		argList.add("1");
		argList.add("employee(Melvin,Barnes,11000.12)");
		argList.add("1.12");
		argList.add("11");
		argList.add("2.13");
		argList.add("3");
		argList.add("20");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
	
	
	/**
	 * Tests Sort Operation: only numbers
	 * @throws Exception
	 */
	@Test
	public void testSortOnlyNumbers() throws Exception{
		Operation op = OperationFactory.createOperation("opSortAsc");
		String expected = "\"1\" \"1.12\" \"2.1\" \"2.13\" \"3\" \"7\" \"11\" \"20\" ";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("7");
		argList.add("2.1");
		argList.add("1");
		argList.add("1.12");
		argList.add("11");
		argList.add("2.13");
		argList.add("3");
		argList.add("20");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
	
	/**
	 * Tests Sort Operation: only int numbers
	 * @throws Exception
	 */
	@Test
	public void testSortInt() throws Exception{
		Operation op = OperationFactory.createOperation("opSortAsc");
		String expected = "\"1\" \"2\" \"2\" \"4\" \"7\" \"13\" ";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("7");
		argList.add("2");
		argList.add("2");
		argList.add("13");
		argList.add("1");
		argList.add("4");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
		
		expected = "\"7\" ";
		argList = new ArrayList<String>();
		argList.add("7");
		received = op.getResult(argList);
		
		assertEquals(expected, received);

	}
	
	/**
	 * Tests Sort Operation: only int numbers
	 * @throws Exception
	 */
	@Test
	public void testSortDouble() throws Exception{
		Operation op = OperationFactory.createOperation("opSortAsc");
		String expected = "\"1.12\" \"1.13\" \"2.1\" \"2.1\" \"2.12\" \"13.2\" ";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("1.13");
		argList.add("13.2");
		argList.add("2.12");
		argList.add("2.1");
		argList.add("2.1");
		argList.add("1.12");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
		
		expected = "\"7\" ";
		argList = new ArrayList<String>();
		argList.add("7");
		received = op.getResult(argList);
		
		assertEquals(expected, received);

	}
	
	/**
	 * Tests Sort Operation: without employee
	 * @throws Exception
	 */
	@Test
	public void testSortWithoutEmployee() throws Exception{
		Operation op = OperationFactory.createOperation("opSortAsc");
		String expected = "\"1\" \"2\" \"3\" \"11\" \"a\" \"b\" \"z\" ";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("3");
		argList.add("2");
		argList.add("1");
		argList.add("a");
		argList.add("z");
		argList.add("b");
		argList.add("11");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
	
	
	
	/**
	 * Tests employee creation
	 * @throws Exception
	 */
	@Test
	public void testcreateEmployee() throws Exception{

		String empString = "employee(John,Smith,33000.0)"; 
		Employee emp = new Employee(empString);
		
		assertEquals("John", emp.getFirstName());
		assertEquals("Smith", emp.getLastName());
		assertEquals(33000.0, emp.getSalary(),0.0);
		assertEquals(empString, emp.toCSV());
		
		
		empString = "employee(,,123.456)";
		emp = new Employee("employee(,,123.456)");
		
		assertEquals("", emp.getFirstName());
		assertEquals("", emp.getLastName());
		assertEquals(123.456, emp.getSalary(),0.0);
		assertEquals(empString, emp.toCSV());
		
	}
	
}
