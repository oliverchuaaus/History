package com.cba.workitem;

public interface WorkItem {
	void execute(WorkItemCompletionCallback callback);
}
