package com.shirashiki.javatest.bo;

import java.util.List;

/**
 * Concatenates any argument that is a string or numeric. Ignores employee
 * arguments.
 * 
 * @author silvio hirashiki
 *
 */
public class ConcatenateOperation implements Operation {

	public String getResult(List<String> argList) {

		StringBuilder strBuild = new StringBuilder("");

		for (String s : argList) {

			// Concatenates all non-employee strings
			if (s.toLowerCase().contains("employee(") == false) {
				strBuild.append(s);
			}
		}

		return strBuild.toString();
	}

}
