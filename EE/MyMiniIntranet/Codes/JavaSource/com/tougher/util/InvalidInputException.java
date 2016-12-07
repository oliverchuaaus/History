package com.tougher.util;

import java.util.*;

/**
 * Encapsulates a collection of exceptions.
 * <P>
 * Exceptions added must be referenced by a key(property). Only one exception
 * per key is allowed.
 * 
 * @author Kristoffer Chua
 * @version 0.1 August 26, 2003
 * @version 0.1 September 24, 2004
 */
public class InvalidInputException extends RuntimeException {
	public static class ErrorDetail {
		private String key;

		private String[] values = new String[4];

		public ErrorDetail(String key) {
			this.key = key;
		}

		/*
		 * public ErrorDetail(String key, String value0) { this.key = key;
		 * values[0] = value0; }
		 * 
		 * public ErrorDetail(String key, String value0, String value1) {
		 * this.key = key; values[0] = value0; values[1] = value1; }
		 * 
		 * public ErrorDetail(String key, String value0, String value1, String
		 * value2) { this.key = key; values[0] = value0; values[1] = value1;
		 * values[2] = value2; }
		 * 
		 * public ErrorDetail(String key, String value0, String value1, String
		 * value2, String value3) { this.key = key; values[0] = value0;
		 * values[1] = value1; values[2] = value2; values[3] = value3; }
		 */
		public ErrorDetail(String key, String[] values) {
			this.key = key;
			this.values = values;
		}

		public String getKey() {
			return key;
		}

		public String[] getValues() {
			return values;
		}

	}

	private ArrayList errorKeyList = new ArrayList();

	private ArrayList errorList = new ArrayList();

	private HashMap errorHM = new HashMap();

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String key, String value, String[] params) {
		super();
		addException(key, value, params);
	}

	/**
	 * Add all exception in iie to this object
	 * 
	 * @param iie
	 */
	public void addException(InvalidInputException iie) {
		this.errorKeyList.addAll(iie.errorKeyList);
		this.errorList.addAll(iie.errorList);
		this.errorHM.putAll(iie.errorHM);
	}

	public void addException(String key, String value, String params[]) {
		if (!this.errorHM.containsKey(key)) {
			ArrayList errorDetailList = new ArrayList();
			InvalidInputException.ErrorDetail ed = new InvalidInputException.ErrorDetail(
					value, params);
			errorDetailList.add(ed);
			this.errorHM.put(key, errorDetailList);
			this.errorKeyList.add(key);
			this.errorList.add(errorDetailList);
		} else {
			ArrayList errorDetailList = (ArrayList) this.errorHM.get(key);
			InvalidInputException.ErrorDetail ed = new InvalidInputException.ErrorDetail(
					value, params);
			errorDetailList.add(ed);
			//errorDetailList is shared, so no need to update errorList
		}
	}

	public ArrayList getExceptionList() {
		return (ArrayList) this.errorList.clone();
	}

	public ArrayList getExceptionKeyList() {
		return (ArrayList) this.errorKeyList.clone();
	}

	public HashMap getExceptionHashMap() {
		return (HashMap) this.errorHM.clone();
	}

	public ArrayList getExceptionList(String key) {
		ArrayList valueList = (ArrayList) this.errorHM.get(key);
		return (ArrayList) valueList.clone();
	}

	public boolean isEmpty() {
		return this.errorList.isEmpty();
	}

}