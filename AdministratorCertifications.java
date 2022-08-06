package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;


import io.github.bonigarcia.wdm.WebDriverManager;

public class AdministratorCertifications {
	
	/*
	 * Assignments 1. Administrator Certifications ============================== 
	 * 1.Launch Salesforce application https://login.salesforce.com/ 
	 * 2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
	 * 3. Click on Learn More link in Mobile Publisher
	 * 4. Click confirm on Confirm redirect
	 * 5. Click Learning and mouse hover on Learning On Trailhead 
	 * 6.Clilck on Salesforce Certifications 
	 * 7. Click on Ceritification Administrator
	 * 8. Navigate to Certification - Administrator Overview window
	 * 9. Verify the Certifications available for Administrator Certifications should be displayed
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// To Download and set the path 
		WebDriverManager.chromedriver().setup();
				
		// To Launch the chromebrowser
		WebDriver driver = new ChromeDriver();
				
		//To Launch the URL
		driver.get("https://login.salesforce.com/");
				
		//To Maximise the window
		driver.manage().window().maximize();
				
		//To add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//To Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		
		//To  Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");
		
		//To click on the login button
		driver.findElement(By.id("Login")).click();
		
		//To click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//button[@title='Learn More']")).click();
		
		//To Switch to the next window using Windowhandles
		Set<String> windowHandles = driver.getWindowHandles();
		
		List<String> windows = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(windows.get(1));
		
		//To click on the confirm button in the redirecting page
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//To Click Learning on ShadowDom
		Shadow dom=new Shadow(driver); 
		
		dom.findElementByXPath("//span[text()='Learning']").click();
		
        //To mouse hover on Learning On Trailhead 
		Thread.sleep(3000);
		
		WebElement learningOnTrailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		
		Actions builder = new Actions(driver);
		
		builder.moveToElement(learningOnTrailHead).perform();
		
		builder.scrollToElement(learningOnTrailHead).perform();
		
		//To Click on Salesforce Certifications 
		WebElement salesForce = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		
		//driver.executeScript("arguments[0].click();", salesForce);
		
		salesForce.click();
		
		//To Click on Ceritification Administrator
		driver.findElement(By.linkText("Administrator")).click();
		
		//To Verify the Certifications available for Administrator Certifications should be displayed
		List<WebElement> certifications = driver.findElements(By.xpath("//div[@class='trailMix-card-body_title']//a"));
		
		System.out.println(certifications.size());
		
		for(WebElement webelement :certifications)
		{
			System.out.println(webelement.getText());
		}
	}
}
