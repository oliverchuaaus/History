package hello.mocks;

import org.springframework.stereotype.Service;

@Service
public class MySubService {
	public int getSum(int a, int b) {
		return a + b;
	}
}
