package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class GMailTest extends TestCase {
	
    private WebDriver driver;
    private Properties properties = new Properties();

    public void setUp() throws Exception {
    	
        properties.load(new FileReader(new File("src/test/resources/test.properties")));
        //Dont Change below line. Set this value in test.properties file incase you need to change it..
        System.setProperty("webdriver.chrome.driver",properties.getProperty("webdriver.chrome.driver") );
        driver = new ChromeDriver();
    }

    public void tearDown() throws Exception {
    	
        driver.quit();
    }

    @Test
    public void testSendEmail() throws Exception {
    	
        driver.get("https://mail.google.com/");
       
        WebElement userElement = driver.findElement(By.id("identifierId"));
        userElement.sendKeys(properties.getProperty("username"));

        driver.findElement(By.id("identifierNext")).click();

        Thread.sleep(3000);

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(properties.getProperty("password"));
        
        
        driver.findElement(By.id("passwordNext")).click();

        Thread.sleep(3000);

        // XPath address given in the task didn't work, so I changed it as below.
        WebElement composeElement = driver.findElement(By.cssSelector(".aic .z0 div"));
        composeElement.click();

        // Send command was wrongly placed before entering subject & body and labeling.
        // So I deleted these lines and added my "Recipient" code.
        driver.findElement(By.cssSelector(".oj div textarea")).sendKeys(String.format("%s@gmail.com", properties.getProperty("username")));
 		
        String emailSubject = properties.getProperty("email.subject");
        // After taking 'Subject' form properties, below code writes it to the respectful location.
        driver.findElement(By.cssSelector(".aoD.az6 input")).sendKeys(emailSubject);
        
        String emailBody = properties.getProperty("email.body");
        // After taking 'Body' form properties, below code writes it to the respectful location.
        driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys(emailBody);
        
        // After that point , requirements are "Label email", "Send email" and "Star email".
        // Since they aren't given, I am providing my own code down here.
        
        // Label email as "Social"
        driver.findElement(By.cssSelector("tr.btC td:nth-of-type(5) div div:nth-of-type(1) div")).click();
		driver.findElement(By.cssSelector(".J-Ph.Gi.J-N div")).click();
		driver.findElement(By.cssSelector(".J-M-Jz.aXjCH div:nth-of-type(1) div")).click();
		
		// Send email
		driver.findElement(By.cssSelector("tr.btC td:nth-of-type(1) div div:nth-of-type(2) div:nth-of-type(1)")).click();
		
		Thread.sleep(1000);
		
		// Add star to sent email
		driver.findElement(By.cssSelector("tr.aAA.J-KU-Jg.J-KU-Jg-K9 td:nth-of-type(2) div")).click();
		driver.findElement(By.xpath("//div[@class='Cq aqL']/div/div/div[1]/div/div[1]/div")).click();
		driver.findElement(By.xpath("//div[@class='Cq aqL']/div/div/div[8]/div/div[4]/div")).click();
		driver.findElement(By.xpath("//div[@class='Cq aqL']/div/div/div[7]/div/span")).click();
		driver.findElement(By.xpath("//div[@class='J-M aX0 aYO jQjAxd']/div/div[7]/div")).click();
		
		Thread.sleep(1000);
    }
}
