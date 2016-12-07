package com.cba.workitem.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cba.workitem.WorkItem;
import com.cba.workitem.WorkItemCompletionCallback;
import com.cba.workitem.WorkItemExecutor;

/**
 * This is the work item executor implementation.
 * 
 * @author Kristoffer
 *
 */
public class WorkItemExecutorImpl implements WorkItemExecutor {
	// An Executor provides methods to manage termination and methods that can
	// produce a Future for tracking progress of one or more asynchronous tasks.
	private ExecutorService executorService;
	private static final Logger logger = Logger
			.getLogger(WorkItemExecutorImpl.class.getName());

	@Override
	public void executeWorkItem(final WorkItem w, final int parallelism) {
		executorService = Executors.newFixedThreadPool(parallelism);
		List<Future<?>> futureList = new ArrayList<Future<?>>(parallelism);
		final int[] count = { 0 };
		final WorkItemCompletionCallback callback = new WorkItemCompletionCallback() {
			@Override
			public void complete() {
				logger.log(Level.INFO, Thread.currentThread().getName()
						+ " completed.");

				count[0] += 1;
				logger.log(Level.INFO, "count: " + count[0]);
				if (count[0] >= parallelism) {
					// Shut down once all threads finished
					executorService.shutdown();
				}
			}
		};

		for (int i = 0; i < parallelism; i++) {
			Future<?> future = executorService.submit((new Runnable() {
				@Override
				public void run() {
					w.execute(callback);
				}
			}));
			futureList.add(future);
		}

		while (!executorService.isShutdown()) {
			for (Future<?> future : futureList) {
				try {
					if (future.isDone()) {
						future.get();
					}
				} catch (RuntimeException | ExecutionException e) {
					// If execution fails for a thread, then retry again
					Future<?> future1 = executorService.submit((new Runnable() {
						@Override
						public void run() {
							w.execute(callback);
						}
					}));
					int index = futureList.indexOf(future);
					futureList.set(index, future1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
