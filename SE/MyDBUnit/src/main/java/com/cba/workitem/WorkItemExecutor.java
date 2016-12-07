package com.cba.workitem;

public interface WorkItemExecutor {
	void executeWorkItem(WorkItem w, int parallelism);
}
