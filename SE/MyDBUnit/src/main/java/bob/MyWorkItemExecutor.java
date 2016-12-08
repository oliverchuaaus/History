package bob;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class MyWorkItemExecutor implements WorkItemExecutor,
		WorkItemCompletionCallback {
	ExecutorService executorService = null;

	public MyWorkItemExecutor() {
	}

	@Override
	public void complete() {
		executorService.shutdown();
	}

	@Override
	public void executeWorkItem(WorkItem w, int parallelism) {
		System.out.println("Start working, threads : " + parallelism);
		executorService = Executors.newFixedThreadPool(parallelism);
		Future<?>[] f = new Future<?>[parallelism];
		for (int i = 0; i < parallelism; i++) {
			f[i] = executorService.submit(new WorkItemCallable(w, this));
		}

		while (!executorService.isShutdown()) {
			try {
				for (int i = 0; i < parallelism; i++) {
					if (!executorService.isShutdown() && f[i].isDone())
						f[i] = executorService.submit(new WorkItemCallable(w,
								this));
					else
						break;
				}
			} catch (RejectedExecutionException reject) {
				// reject submit when tasks have already been finished
			}
		}

	}

}
