package com.shirashiki.javatest;

import java.util.*;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Implements the Add operation: 
 * add numbers. Adds any argument that is numeric. Ignores non numeric arguments.
 * @author silviohirashiki
 *
 */
public class AddOperation implements Operation {

	public String getResult(String[] args) {
		
		double result = 0d;
		
		List<String> argList = new ArrayList<String>(Arrays.asList(args));
		
		// removes first element which contains operation
		argList.remove(0);
		
	    for (String s : argList) {
	    	
	    	// when element is numeric, accumulates values
	        if (NumberUtils.isNumber(s)) {
	        	result = result + Double.parseDouble(s);
	        }
	    }
		
		
		return String.valueOf(result);
	}

}
