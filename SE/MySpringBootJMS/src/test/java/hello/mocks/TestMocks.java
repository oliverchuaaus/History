package hello.mocks;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

import hello.mocks.MyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMocks {

	@Autowired
	MyService service;
	
	@MockBean
	MySubService subService;
	
	@Before
    public void setup() {
    }

	@Test
	public void testMock() {
        when(subService.getSum(Integer.valueOf(1),Integer.valueOf(2))).thenReturn(Integer.valueOf(5));
        when(subService.getSum(Integer.valueOf(2),Integer.valueOf(3))).thenReturn(Integer.valueOf(10));
		assertEquals(10, service.getSum(2,3));
		assertEquals(5, service.getSum(1,2));

	}
}
