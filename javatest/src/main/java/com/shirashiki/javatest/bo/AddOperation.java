package com.shirashiki.javatest.bo;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Implements the Add operation: add numbers. Adds any argument that is numeric.
 * Ignores non numeric arguments.
 * 
 * @see com.shirashiki.javatest.bo.OperationFactory
 * @author silviohirashiki
 *
 */
public class AddOperation implements Operation {

	public String getResult(List<String> argList) {

		BigDecimal result = new BigDecimal(0);

		for (String s : argList) {

			// when element is numeric, accumulates values
			if (NumberUtils.isNumber(s)) {
				result = result.add(new BigDecimal(s));
			}
		}

		return String.valueOf(result);
	}

}
