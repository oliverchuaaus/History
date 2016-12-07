package timer;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;

@Stateless
public class MyTimerServiceImpl implements MyTimerService {
	@Resource
	private SessionContext ctx;

	public void doSomething(long ms) {
		TimerService service = ctx.getTimerService();
		Timer timer = service.createTimer(new Date(System.currentTimeMillis()
				+ ms), "Hello World");
		TimerHandle handle = timer.getHandle();
		System.out.println(handle.toString());

		timer = service.createTimer(new Date(System.currentTimeMillis() + ms),
				10000, "Hello Mars");
		handle = timer.getHandle();
		System.out.println(handle.toString());
	}

	public void stopAll() {
		TimerService service = ctx.getTimerService();
		Collection<Timer> coll = service.getTimers();
		for (Timer timer : coll) {
			timer.cancel();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see timer.MyTimerService#timeout(javax.ejb.Timer)
	 */
	@Timeout
	public void timeout(Timer timer) {
		System.out.println(timer.getTimeRemaining());
		System.out.println(timer.getInfo());
	}
}
