package hello.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	@Autowired
	private MySubService subService;

	public MySubService getSubService() {
		return subService;
	}

	public void setSubService(MySubService subService) {
		this.subService = subService;
	}

	public int getSum(int a, int b) {
		int sum = subService.getSum(a, b);
		return sum;
	}
}
