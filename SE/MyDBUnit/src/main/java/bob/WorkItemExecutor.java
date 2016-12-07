package bob;

public interface WorkItemExecutor {
	void executeWorkItem(WorkItem w, int parallelism);
}
