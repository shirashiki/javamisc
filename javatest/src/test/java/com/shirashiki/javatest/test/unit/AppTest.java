package com.shirashiki.javatest.test.unit;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.Test;
import com.shirashiki.javatest.bo.*;




/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test
	public void testAddOperation() throws Exception{
		Operation op = OperationFactory.createOperation("opAdd");
		String expected = "15.12";
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("opAdd");
		argList.add("chocolate");
		argList.add("0.1");
		argList.add("13");
		argList.add("2.02");
		argList.add("banana");
		argList.add("apple");

		String received = op.getResult(argList);
		
		assertEquals(expected, received);
	}
}
