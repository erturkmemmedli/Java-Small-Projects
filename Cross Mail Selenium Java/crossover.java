import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class crossover {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Erturk Memmedli/Documents/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Enter Gmail
		driver.get("https://mail.google.com");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Enter Username and Next
		driver.findElement(By.id("identifierId")).sendKeys("...");
		driver.findElement(By.cssSelector("#identifierNext span span")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// Enter Password and Next
		driver.findElement(By.name("password")).sendKeys("...");
		driver.findElement(By.cssSelector("#passwordNext span span")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Compose Email
		driver.findElement(By.cssSelector(".aic .z0 div")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Enter Recipient
		driver.findElement(By.cssSelector(".oj div textarea")).sendKeys("...@gmail.com");
		// Enter Subject
		driver.findElement(By.cssSelector(".aoD.az6 input")).sendKeys("Subject of this message");
		// Enter Body
		driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys("Single line body of the message");
		// Label Email
		driver.findElement(By.cssSelector("tr.btC td:nth-of-type(5) div div:nth-of-type(1) div")).click();
		driver.findElement(By.cssSelector(".J-Ph.Gi.J-N div")).click();
		driver.findElement(By.cssSelector(".J-M-Jz.aXjCH div:nth-of-type(1) div")).click();
		// Send Email
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("tr.btC td:nth-of-type(1) div div:nth-of-type(2) div:nth-of-type(1)")).click();
		// Star Email
		driver.findElement(By.cssSelector("tr.aAA.J-KU-Jg.J-KU-Jg-K9 td:nth-of-type(2) div")).click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='Cq aqL']/div/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.xpath("//div[@class='Cq aqL']/div/div/div[8]/div/div[4]/div")).click();
		driver.findElement(By.xpath("//div[@class='Cq aqL']/div/div/div[7]/div/span")).click();
		driver.findElement(By.xpath("//div[@class='J-M aX0 aYO jQjAxd']/div/div[7]/div")).click();
		
		
	}

}
