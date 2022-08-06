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

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class CustomerService {
	
	/*
	 * Assignments 3.Customer_Service_Options
	 *  ====================================
	 * 1. Launch Salesforce application https://login.salesforce.com/ 
	 * 2. Login with Provided Credentials 
	 * 3. Click on Learn More link in Mobile Publisher 
	 * 4. Clilck on Products and Mousehover on Service 
	 * 5. Click on Customer Services 
	 * 6. Get the names Of Services Available 
	 * 7. Verify the title
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
		
		//To Clilck on Products  
		
		Shadow dom = new Shadow(driver);
		
		dom.findElementByXPath("//span[text()='Products']").click();
		
		//To Mousehover on Service
		WebElement service = dom.findElementByXPath("//span[text()='Service']");
		
		Thread.sleep(3000);
		
		Actions builder = new Actions(driver);
		
		builder.moveToElement(service).perform();
		
		//To Click on Customer Services 
		dom.findElementByXPath("//a[text()='Customer Service']").click();
		
		//To Get the names Of Services Available 
		List<WebElement> servicesName = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-2']//a"));
		
		System.out.println("No. of Services : " + servicesName.size());
		
		System.out.println("Name of Services available are : ");
		
		for(WebElement element : servicesName)
		{
			System.out.println(element.getText());
		}
		
		//To Verify the title
		System.out.println("The title of the page is : " + driver.getTitle());
		
	}

}
