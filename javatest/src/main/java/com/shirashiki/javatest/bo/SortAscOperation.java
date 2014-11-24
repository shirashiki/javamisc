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
		
		// fill each list according to type
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
		    		// implement how to create employees
		    	} else {
		    		strList.add(s);
		    	}
	        }
	        	

	    }
		
		
		Collections.sort(argList);
		
		// Generates an 
		StringBuilder strBuild = new StringBuilder("");
	    for (String s : argList) {
	    	
	    	strBuild.append("\"");
	    	strBuild.append(StringEscapeUtils.escapeJava(s));
	    	strBuild.append("\" ");
	    }
		
		return strBuild.toString();
	}

}
