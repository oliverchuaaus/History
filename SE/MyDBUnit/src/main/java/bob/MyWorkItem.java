package bob;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyWorkItem implements WorkItem {
	static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	WorkItemCompletionCallback callback = null;

	// set tasks simply by integer
	public MyWorkItem(int total) {
		System.out.println("constructing work items : " + total);
		for (int i = 0; i < total; i++)
			queue.add(i);
	}

	public int getItemCount() {
		return queue.size();
	}

	@Override
	public void execute(WorkItemCompletionCallback callback) {
		synchronized (this) {
			if (!queue.isEmpty())
				System.out.println("WorkItem is working on " + queue.poll());
			else {
				int throwError = Math.abs(new Random().nextInt(10));
				if (throwError > 5) {
					System.out.println(Thread.currentThread().getName()
							+ " throwing Exception");
					throw new RuntimeException("Simulated exception");
				} else {
					callback.complete();
				}
			}
		}

	}
}