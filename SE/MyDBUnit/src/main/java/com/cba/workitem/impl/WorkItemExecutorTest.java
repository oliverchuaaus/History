package com.cba.workitem.impl;

import com.cba.workitem.WorkItem;
import com.cba.workitem.WorkItemExecutor;

/**
 * This is the test class.
 * 
 * @author Kristoffer
 *
 */
public class WorkItemExecutorTest {

	public static void main(String[] args) {
		WorkItemExecutor exec = new WorkItemExecutorImpl();
		WorkItem wi = new WorkItemImpl();
		int parallelism = 5;
		exec.executeWorkItem(wi, parallelism);
	}
}
