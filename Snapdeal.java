package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	
	/*
	 * *SnapDeal* ============ 
	 * 1. Launch https://www.snapdeal.com/ 
	 * 2.Go to Mens Fashion 
	 * 3. Go to Sports Shoes 
	 * 4. Get the count of the sports shoes
	 * 5. Click Training shoes 
	 * 6. Sort by Low to High 
	 * 7. Check if the items displayed are sorted correctly
	 *  8.Select the price range (900-1200) 
	 *  9.Filter with color Navy 
	 *  10 verify the all applied filters 
	 *  11. Mouse Hover on firstresulting Training shoes 
	 * 12. click QuickView button
	 *  13. Print the cost and the discount percentage 
	 * 14. Take the snapshot of the shoes.
	 *  15. Close the current window 
	 *  16. Close the main window
	 */

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		// To Download and set the path 
		WebDriverManager.chromedriver().setup();
						
		// To Launch the chromebrowser
		ChromeDriver driver = new ChromeDriver();
						
		//To Launch the URL
		driver.get("https://www.snapdeal.com/");
						
		//To Maximise the window
		driver.manage().window().maximize();
						
		//To add wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		//Go to Mens Fashion 
		WebElement men = driver.findElement(By.xpath("(//span[@class='catText'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
		
		// Go to Sports Shoes 
		driver.findElement(By.xpath("//span[@class='linkTest']")).click();
		
		//Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//div[@class='sub-cat-count ']")).getText();
		System.out.println("Count of Men's Sport Shoes : "+ text);
		
		//Click Training shoes 
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		//Sort by Low to High
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-selected']"));
		wait.until(ExpectedConditions.elementToBeClickable(sort)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		
		//Check if the items displayed are sorted correctly
		Thread.sleep(5000);
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		System.out.println("Item Values are : " );
		for(WebElement webelement : price)
		{
			String value = webelement.getText();
			System.out.println(value);
		}
		
		//Select the price range (500-1200) 
		WebElement from = driver.findElement(By.xpath("//input[@name='fromVal']"));
		from.clear();
		from.sendKeys("500");
		
		WebElement to = driver.findElement(By.xpath("//input[@name='toVal']"));
		to.clear();
		to.sendKeys("1200");
		
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		
		//Filter with color Navy 
		WebElement color = driver.findElement(By.xpath("(//div[@class='filter-type-name lfloat'])[3]"));
		Thread.sleep(3000);
		builder.scrollToElement(color).perform();
		
		try
		{
		Thread.sleep(5000);
		WebElement navy = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
		navy.click();
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("ElementClickInterceptedException is handled");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//label[@for='Color_s-Navy']")))).click();
		}

		// verify all applied filters
		List<WebElement> filters = driver.findElements(By.xpath("//div[@class='filters']"));
		System.out.println("Applied Filters are : ");
		 for(int i=0; i<filters.size(); i++)
	       {
	    	   String text1 = filters.get(i).getText();
	    	   System.out.println(text1);  
	       }
		
		// Mouse Hover on first resulting Training shoes 
		WebElement result = driver.findElement(By.xpath("//img[@title='TUFF 5005 Navy Training Shoes']"));
		builder.moveToElement(result).perform();
		
		//Click QuickView button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();

		//Print the cost and the discount percentage
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Cost is : "+cost);
		
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount value is : "+discount);
		
		//Take the snapshot of the shoes
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snapdeal1.png");
		FileUtils.copyFile(source , dest);
		
		// Close the current window 
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		
		//Close the main window 
		driver.close();
	}

}
