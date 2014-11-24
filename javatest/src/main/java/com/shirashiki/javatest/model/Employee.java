package com.shirashiki.javatest.model;

import java.util.*;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Class that represents an Employee
 * 
 * @author silviohirashiki
 *
 */
public class Employee {
	private String firstName;
	private String lastName;
	private double salary;

	public Employee() {
	}

	/**
	 * 
	 * @param csvString
	 *            Comma separated value string containing first name, last name
	 *            and salary, in the format: employee(John,Smith,33000)
	 */
	public Employee(String employeeString) {

		String csvString = employeeString;
		csvString = csvString.replace("employee(", "");
		csvString = csvString.substring(0, csvString.length() - 1);

		List<String> empInfo = Arrays.asList(csvString.split("\\s*,\\s*"));

		if (empInfo.size() < 3) {
			throw new IllegalArgumentException(
					"employee needs to have firtname, lastname and salary. received:"
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

		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;

	}

	/**
	 * Comparator to sort employees by last name, ascending
	 */
	public static Comparator<Employee> LastNameComparator = new Comparator<Employee>() {

		public int compare(Employee emp1, Employee emp2) {
			return emp1.getLastName().compareTo(emp2.getLastName());
		}

	};
	
	/**
	 * Generates a string similar to the one used to create the employee. Example:
	 * employee(John,Smith,33000.00)
	 * @return
	 */
	public String toCSV() {
		
		StringBuilder strOut = new StringBuilder("");
		strOut.append("employee(");
		strOut.append(firstName + ",");
		strOut.append(lastName + ",");
		strOut.append(Double.toString(salary));
		strOut.append(")");
		
		return strOut.toString();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (Double.doubleToLongBits(salary) != Double
				.doubleToLongBits(other.salary))
			return false;
		
		return true;
	}

	/*
	 * public int compareTo(Employee emp) { return
	 * lastName.compareTo(emp.getLastName()); }
	 */

}
