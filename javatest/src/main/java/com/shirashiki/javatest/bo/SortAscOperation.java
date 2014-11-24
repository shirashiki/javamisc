package com.shirashiki.javatest.bo;

import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.shirashiki.javatest.model.*;

/**
 * 
 * @author silvio hirashiki
 *
 */
public class SortAscOperation implements Operation {

	public String getResult(List<String> argList) {

		// ### consider refactoring
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
					empList.add(new Employee(s));
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
		// Merges list containing ints and doubles
		while (i < strList.size() && j < empList.size()) {
			
			if ( strList.get(i).compareTo(empList.get(j).getLastName()) == -1 ) {
				finalList.add(strList.get(i));
				i++;
			} else if (strList.get(i).compareTo(empList.get(j).getLastName()) == 1) {
				finalList.add(empList.get(j).toCSV());
				j++;
			} else {
				finalList.add(strList.get(i));
				finalList.add(empList.get(j).toCSV());
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
				finalList.add(empList.get(j).toCSV());
				j++;
			}
		}
		
		
		// Generates a string representation of the sorted list
		StringBuilder strBuild = new StringBuilder("");
		for (String s : finalList) {

			strBuild.append("\"");
			strBuild.append(StringEscapeUtils.escapeJava(s));
			strBuild.append("\" ");
		}

		return strBuild.toString();
	}

}
