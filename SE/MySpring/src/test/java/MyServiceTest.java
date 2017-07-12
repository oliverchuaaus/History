import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kristoffer.service.MyOtherService;
import org.kristoffer.service.MyService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import util.SpringUtil;

public class MyServiceTest {
	@BeforeClass
	public static void beforeClass() {
		SpringUtil.init("SpringContext.xml");
	}

	@Test
	public void testGetBean() {
		MyService service = (MyService) SpringUtil.getBean("myServiceImpl");
		assertNotNull(service);
		service.doSomething();
	}

	@Test
	public void testBadGetBean() {
		try {
			SpringUtil.getBean("MyServiceImpl");
			fail();
		} catch (NoSuchBeanDefinitionException e) {
			assertEquals("No bean named 'MyServiceImpl' available",
					e.getMessage());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetBeanByClass() {
		MyService service = (MyService) SpringUtil
				.getBeanByClass(MyService.class);
		assertNotNull(service);
		service.doSomething();
	}

	@Test
	public void testBadGetBeanByClass() {
		try {
			SpringUtil.getBeanByClass(MyOtherService.class);
		} catch (NoSuchBeanDefinitionException e) {
			assertEquals(
					"No qualifying bean of type 'org.kristoffer.service.MyOtherService' available: expected single matching bean but found 2: myDummyServiceImpl,myOtherServiceImpl",
					e.getMessage());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetBeanByClassDual() {
		MyOtherService service = (MyOtherService) SpringUtil.getBeanByClass(
				"myDummyServiceImpl", MyOtherService.class);
		assertNotNull(service);
		assertEquals("something", service.doSomething());
	}

	@Test
	public void testDoSomething() {
		MyService service = (MyService) SpringUtil.getBean("myServiceImpl");
		assertEquals("Something", service.doSomething());
		service.doSomething();
	}
}
