package com.shirashiki.javatest.test.integration;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shirashiki.javatest.*;

public class IntegrationTest {

	private static final String EOL = System.getProperty("line.separator");
	private PrintStream console;
	private ByteArrayOutputStream bytes;

	@Before
	public void setUp() {
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	@After
	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void testAddOperation() throws Exception {

		JavaTestApp.main(new String[] { "opAdd", "1", "2" });
		assertEquals("3.0" + EOL, bytes.toString());
	}
}
