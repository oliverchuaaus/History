package bob;
public class ExecutorTestMain {

        public static void main(String[] args) {
                
                
                WorkItemExecutor e = new MyWorkItemExecutor();
                WorkItem wi = new MyWorkItem(100);
                e.executeWorkItem(wi, 10);
                
                
        }

}
