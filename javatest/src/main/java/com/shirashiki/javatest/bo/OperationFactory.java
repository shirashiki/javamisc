package com.shirashiki.javatest.bo;

/**
 * Factory to create supported operations
 * @author silvio hirashiki
 *
 */
public class OperationFactory {

	/**
	 * Creates operation according to the name
	 * @param operationName
	 * @return The desired operation
	 */
	public static Operation createOperation(String operationName) {
		
		if (operationName.equalsIgnoreCase("opAdd")) {
			return new AddOperation();
		} 
		else if (operationName.equalsIgnoreCase("opConcatenate")) {
			return new ConcatenateOperation();
		}
		else if (operationName.equalsIgnoreCase("opSortAsc")) {
			return new SortAscOperation();
		} 
		else {
			throw new IllegalArgumentException("Operation " + operationName + " not supported. Supported operations: opAdd, opConcatenate, opSortAsc");
		}

		
	}
}
