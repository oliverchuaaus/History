package bob;

public interface WorkItem {
	void execute(WorkItemCompletionCallback callback);
}
