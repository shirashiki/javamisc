package com.shirashiki.javatest.model;

import java.util.Comparator;

public class EmployeeLastNameComparator implements Comparator<Employee>{

	public int compare(Employee emp1, Employee emp2) {
		return emp1.getLastName().compareTo(emp2.getLastName());
	}

}
