package timer;

import javax.ejb.Remote;

@Remote
public interface MyTimerService {

    public void doSomething(long ms);

    public void stopAll();
}