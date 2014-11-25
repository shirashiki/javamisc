package com.shirashiki.javatest.bo;

import java.util.List;

/**
 * Operation Interface: represents a console operation.
 * When creating new Operations, please implement this
 * @author silviohirashiki
 *
 */
public interface Operation {

	public String getResult(List<String> argList);
}
