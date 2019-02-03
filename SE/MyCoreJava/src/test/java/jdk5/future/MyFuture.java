package jdk5.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class MyFuture {

	@Test
	public void testManualCompletion() {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				int sleeping = (int) (Math.random() * 1000);
				Thread.sleep(sleeping);
				return "" + sleeping;
			}
		};

		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			// submit Callable tasks to be executed by thread pool
			Future<String> future = executor.submit(callable);
			// add Future to the list, we can get return value using Future
			list.add(future);
		}

		while (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				try {
					Future<String> fut = list.get(i);
					if (fut != null && fut.isDone()) {
						System.out.println(fut.get());
						list.remove(fut);
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		executor.shutdown();
	}

	@Test
	public void testAutoCompletion() {
		CompletionService<String> compService = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(5));

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				int sleeping = (int) (Math.random() * 10000);
				Thread.sleep(sleeping);
				return "" + sleeping;
			}
		};

		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			// submit Callable tasks to be executed by thread pool
			Future<String> future = compService.submit(callable);
			// add Future to the list, we can get return value using Future
			list.add(future);
		}

		while (!list.isEmpty()) {
			try {
				Future<String> fut = compService.take();
				System.out.println(fut.get());
				list.remove(fut);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		// TODO: This is not right, output should be ascending
	}
}
