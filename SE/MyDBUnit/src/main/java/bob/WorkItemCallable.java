package bob;

import java.util.concurrent.Callable;

public class WorkItemCallable implements Callable<String> {

        WorkItem wi = null;
        WorkItemCompletionCallback cb = null;
        
        public WorkItemCallable(WorkItem w, WorkItemCompletionCallback c){
                wi = w;
                cb = c;
        }
        
        @Override
        public String call() throws Exception {
                wi.execute(cb);
                return "OK";
        }

}

