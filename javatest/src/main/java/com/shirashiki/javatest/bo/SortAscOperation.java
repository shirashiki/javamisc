package com.shirashiki.javatest.bo;

import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.shirashiki.javatest.model.*;

/**
 * Sort numbers and strings in ascending order. If an employee is encountered,
 * use its lastName.
 * 
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
	 * 
	 * @param argList
	 *            List to be sorted
	 * @return Sorted List
	 */
	public List<String> getSortedAsc(List<String> argList) {

		List<Integer> intList = new ArrayList<Integer>();
		List<Double> doubList = new ArrayList<Double>();
		List<String> strList = new ArrayList<String>();
		List<Employee> empList = new ArrayList<Employee>();

		/*
		 * Separates the list of strings in lists with more specific types, so
		 * we can sort and process
		 */
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

		List<String> strResultList;
		
		// merges the numeric lists
		strResultList = mergeNumericLists(intList, doubList);
		
		// merges the list of strings and employees and appends
		strResultList.addAll(mergeStringBasedLists(strList, empList));
		
		return strResultList;
		
	}

	/**
	 * Merges numeric lists into a list of strings
	 * 
	 * @param intList
	 *            numeric list of integer values
	 * @param doubList
	 *            numeric list of double values
	 * @return merged list
	 */
	private List<String> mergeNumericLists(List<Integer> intList,
			List<Double> doubList) {

		// Sorts each collection
		Collections.sort(intList);
		Collections.sort(doubList);

		// merges sorted arrays
		int i = 0;
		int j = 0;

		List<String> strResList = new ArrayList<String>();

		// Merges list containing ints and doubles
		while (i < intList.size() && j < doubList.size()) {
			if (intList.get(i) < doubList.get(j)) {
				strResList.add(Integer.toString(intList.get(i)));
				i++;
			} else if (intList.get(i) > doubList.get(j)) {
				strResList.add(Double.toString(doubList.get(j)));
				j++;
			} else {
				strResList.add(Integer.toString(intList.get(i)));
				strResList.add(Double.toString(doubList.get(j)));
				i++;
				j++;
			}
		}

		// transport remaining items
		if (i < intList.size()) {
			while (i < intList.size()) {
				strResList.add(Integer.toString(intList.get(i)));
				i++;
			}
		}

		if (j < doubList.size()) {
			while (j < doubList.size()) {
				strResList.add(Double.toString(doubList.get(j)));
				j++;
			}
		}

		return strResList;
	}
	
	
	/**
	 * Merges lists which can be compared in a string comparation
	 * @param strList
	 * @param empList
	 * @return
	 */
	private List<String> mergeStringBasedLists(List<String> strList, List<Employee>empList) {
		
		Collections.sort(strList);
		Collections.sort(empList, Employee.LastNameComparator);
		
		int i = 0;
		int j = 0;
		
		List<String> strResList = new ArrayList<String>();
		
		// Adds now the elements of type string and Employee
		while (i < strList.size() && j < empList.size()) {
			String strTemp1 = strList.get(i);
			String strTemp2 = empList.get(j).getLastName();

			if (strTemp1.compareTo(strTemp2) < 0) {
				strResList.add(strList.get(i));
				i++;
			} else if (strTemp1.compareTo(strTemp2) > 0) {
				strResList.add(employeeAsCSV(empList.get(j)));
				j++;
			} else {
				strResList.add(strList.get(i));
				strResList.add(employeeAsCSV(empList.get(j)));
				i++;
				j++;
			}
		}

		// transport remaining items
		if (i < strList.size()) {
			while (i < strList.size()) {
				strResList.add(strList.get(i));
				i++;
			}
		}

		if (j < empList.size()) {
			while (j < empList.size()) {
				strResList.add(employeeAsCSV(empList.get(j)));
				j++;
			}
		}
		
		return strResList;
	}

	
	/**
	 * Generates a string similar to the one used to create the employee.
	 * Example: employee(John,Smith,33000.00)
	 * @param emp	an employee object
	 * @return a string to generate an employee
	 */
	private String employeeAsCSV(Employee emp) {

		StringBuilder strOut = new StringBuilder("");
		strOut.append("employee(");
		strOut.append(emp.getFirstName() + ",");
		strOut.append(emp.getLastName() + ",");
		strOut.append(Double.toString(emp.getSalary()));
		strOut.append(")");

		return strOut.toString();
	}

	/**
	 * Creates an employee with a string parameter in the format
	 * employee(John,Doe,2200)
	 * @param employeeString
	 * @return an Employee
	 */
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
