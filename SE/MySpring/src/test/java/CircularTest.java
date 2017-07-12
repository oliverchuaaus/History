import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import util.SpringUtil;

public class CircularTest {
	@BeforeClass
	public static void beforeClass() {
		SpringUtil.init("SpringContext.xml");
	}

	@Test
	public void testBadCircular() {
		try {
			SpringUtil.getBean("beanA");
			fail("Expected exception not thrown.");
		} catch (NoSuchBeanDefinitionException e) {
			assertTrue(e.getMessage().contains(
					"No bean named 'beanA' available"));
		}
	}

	@Test
	public void testCircular() {
		SpringUtil.getBean("safeBeanA");
		SpringUtil.getBean("safeBeanB");
	}
}
