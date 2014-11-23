package com.shirashiki.javatest.bo;

/**
 * Factory to create supported operations
 * @author silviohirashiki
 *
 */
public class OperationFactory {

	/**
	 * Creates operation according to the name
	 * @param operationName
	 * @return The desired operation
	 */
	public static Operation createOperation(String operationName) {
		
		Operation myOperation;
		
		if (operationName.equalsIgnoreCase("opAdd")) {
			myOperation = new AddOperation();
			return myOperation;
		} 
		else if (operationName.equalsIgnoreCase("opConcatenate")) {
			myOperation = new ConcatenateOperation();
			return myOperation;
		}
		else if (operationName.equalsIgnoreCase("opSortAsc")) {
			myOperation = new SortAscOperation();
			return myOperation;
		} 
		else {
			throw new IllegalArgumentException("Operation " + operationName + " not supported");
		}

		
	}
}
