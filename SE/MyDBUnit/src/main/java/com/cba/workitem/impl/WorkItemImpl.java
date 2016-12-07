package com.cba.workitem.impl;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cba.workitem.WorkItem;
import com.cba.workitem.WorkItemCompletionCallback;

/**
 * This is work item implementation
 * @author Kristoffer
 *
 */
public class WorkItemImpl implements WorkItem {
	private static final Logger logger = Logger.getLogger(WorkItemImpl.class
			.getName());

	@Override
	public void execute(WorkItemCompletionCallback callback) {
		Random r = new Random();
		long sleepTime = Math.abs(r.nextInt(5000));

		// Sleep random times
		logger.log(Level.INFO, Thread.currentThread().getName()
				+ " sleeping for " + sleepTime + " ms.");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
		}
		logger.log(Level.INFO, Thread.currentThread().getName() + " waking");

		// Throw exception randomly to simulate failing threads
		int throwError = Math.abs(r.nextInt(10));
		if (throwError > 5) {
			logger.log(Level.SEVERE, Thread.currentThread().getName() + " throwing Exception");
			throw new RuntimeException("Simulated exception");
		}
		else{
			callback.complete();
		}
	}
}
