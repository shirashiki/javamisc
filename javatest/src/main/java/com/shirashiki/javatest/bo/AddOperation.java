package com.shirashiki.javatest.bo;

import java.util.*;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Implements the Add operation: 
 * add numbers. Adds any argument that is numeric. Ignores non numeric arguments.
 * @see com.shirashiki.javatest.bo.OperationFactory
 * @author silviohirashiki
 *
 */
public class AddOperation implements Operation {

	public String getResult(List<String> argList) {
		
		double result = 0d;
	
	    for (String s : argList) {
	    	
	    	// when element is numeric, accumulates values
	        if (NumberUtils.isNumber(s)) {
	        	result = result + Double.parseDouble(s);
	        }
	    }
				
		return String.valueOf(result);
	}

}
