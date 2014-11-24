package com.shirashiki.javatest.bo;

import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.shirashiki.javatest.model.*;

/**
 * Sort numbers and strings in ascending order. If an employee is encountered, 
 * use its lastName.
 * @author silvio hirashiki
 *
 */
public class SortAscOperation implements Operation {

	/**
	 * Returns Sort Operation result as a string
	 */
	public String getResult(List<String> argList) {

		List<String> sortedList = getSortedAsc(argList);
		
		// Generates a string representation of the sorted list
		StringBuilder strBuild = new StringBuilder("");
		for (String currentString : sortedList) {

			strBuild.append("\"");
			strBuild.append(StringEscapeUtils.escapeJava(currentString));
			strBuild.append("\" ");
		}

		return strBuild.toString();
	}

	
	/**
	 * Sorts List in ascending order
	 * @param argList List to be sorted
	 * @return Sorted List
	 */
	public List<String> getSortedAsc(List<String> argList) {

		List<Integer> intList = new ArrayList<Integer>();
		List<Double> doubList = new ArrayList<Double>();
		List<String> strList = new ArrayList<String>();
		List<Employee> empList = new ArrayList<Employee>();

		// fills each list according to type
		for (String s : argList) {

			// when numeric, adds elements in the double or int list
			if (NumberUtils.isNumber(s)) {
				if (s.contains(".")) {
					doubList.add(Double.parseDouble(s));
				} else {
					intList.add(Integer.parseInt(s));
				}
			} else {
				if (s.toLowerCase().contains("employee(")) {
					empList.add(createEmployee(s));
				} else {
					strList.add(s);
				}
			}

		}

		// Sorts each collection
		Collections.sort(intList);
		Collections.sort(doubList);
		Collections.sort(strList);
		Collections.sort(empList, Employee.LastNameComparator);

		/*
		 * Merge 2 sorted arrays
		 */
		// http://stackoverflow.com/questions/5958169/how-to-merge-two-sorted-arrays-into-a-sorted-array

		int i = 0;
		int j = 0;

		List<String> finalList = new ArrayList<String>();

		// Merges list containing ints and doubles
		while (i < intList.size() && j < doubList.size()) {
			if (intList.get(i) < doubList.get(j)) {
				finalList.add(Integer.toString(intList.get(i)));
				i++;
			} else if (intList.get(i) > doubList.get(j)) {
				finalList.add(Double.toString(doubList.get(j)));
				j++;
			} else {
				finalList.add(Integer.toString(intList.get(i)));
				finalList.add(Double.toString(doubList.get(j)));
				i++;
				j++;
			}
		}

		// transport remaining items
		if (i < intList.size()) {
			while (i < intList.size()) {
				finalList.add(Integer.toString(intList.get(i)));
				i++;
			}
		}

		if (j < doubList.size()) {
			while (j < doubList.size()) {
				finalList.add(Double.toString(doubList.get(j)));
				j++;
			}
		}


		i = 0;
		j = 0;
		// Adds now the elements of type string and Employee
		while (i < strList.size() && j < empList.size()) {
			String strTemp1 = strList.get(i);
			String strTemp2 = empList.get(j).getLastName();
			
			if ( strTemp1.compareTo(strTemp2) < 0 ) {
				finalList.add(strList.get(i));
				i++;
			} else if (strTemp1.compareTo(strTemp2) > 0) {
				finalList.add(employeeAsCSV(empList.get(j)));
				j++;
			} else {
				finalList.add(strList.get(i));
				finalList.add(employeeAsCSV(empList.get(j)));
				i++;
				j++;
			}
		}

		// transport remaining items
		if (i < strList.size()) {
			while (i < strList.size()) {
				finalList.add(strList.get(i));
				i++;
			}
		}

		if (j < empList.size()) {
			while (j < empList.size()) {
				finalList.add(employeeAsCSV(empList.get(j)));
				j++;
			}
		}
		
		return finalList;
	}
	
	/**
	 * Generates a string similar to the one used to create the employee. Example:
	 * employee(John,Smith,33000.00)
	 * @return
	 */
	public String employeeAsCSV(Employee emp) {
		
		StringBuilder strOut = new StringBuilder("");
		strOut.append("employee(");
		strOut.append(emp.getFirstName() + ",");
		strOut.append(emp.getLastName() + ",");
		strOut.append(Double.toString(emp.getSalary()));
		strOut.append(")");
		
		return strOut.toString();
	}
	
	private Employee createEmployee(String employeeString) {
		String csvString = employeeString;
		csvString = csvString.replace("employee(", "");
		csvString = csvString.substring(0, csvString.length() - 1);

		List<String> empInfo = Arrays.asList(csvString.split("\\s*,\\s*"));

		if (empInfo.size() < 3) {
			throw new IllegalArgumentException(
					"employee needs to have firstname, lastname and salary. received:"
							+ employeeString);
		}

		String firstName = empInfo.get(0).trim();
		String lastName = empInfo.get(1).trim();

		double salary;

		if (NumberUtils.isNumber(empInfo.get(2).trim())) {
			salary = Double.parseDouble(empInfo.get(2).trim());
		} else {
			throw new IllegalArgumentException("employee salary not numeric:"
					+ empInfo.get(2));
		}

		return new Employee(firstName, lastName, salary);
	}
}
