import java.net.MalformedURLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class MyTest {
	
	@Scheduled(cron="/15,45 0 * * ? *")
	public static void test(){
		System.out.println(new Date());
	}
	
	public static void login(){
		WebDriver driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.navigate().to("http://gstatic.com/generate_204");
		driver.findElement(By.id("hsp")).click();
		System.out.println(driver.getPageSource());
		driver.findElement(By.id("username")).sendKeys(
				"kris.chua.aus@gmail.com");
		driver.findElement(By.id("password")).sendKeys("kins4ley");
		driver.findElement(By.id("submitbtn")).click();
	}
	public static void main(String... args) throws MalformedURLException {
        SpringApplication.run(MyTest.class);
	}

}
