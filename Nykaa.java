package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Nykaa {
	
	/*
	 * Assignment 4:*Nykaa* 
	 * =============
	 *  1) Go to https://www.nykaa.com/ 
	 *  2) Mouseover on Brands and Search L'Oreal Paris 
	 *  3) Click L'Oreal Paris
	 *  4) Check the title contains L'Oreal Paris(Hint-GetTitle) 
	 *  5) Click sort By and select customer top rated 
	 *  6) Click Category and click Hair->Click haircare->Shampoo
	 *  7) Click->Concern->Color Protection 
	 *  8) Check whether the Filter is applied with Shampoo 
	 *  9) Click on L'Oreal Paris Colour Protect Shampoo 
	 *  10) GO to the new window and select size as 175ml 
	 *  11) Print the MRP of the product 
	 *  12) Click on ADD to BAG 
	 *  13) Go to Shopping Bag 
	 *  14) Print the Grand Total amount
	 *  15) Click Proceed 
	 *  16) Click on Continue as Guest 
	 *  17) Check if this grand total is the same in step 14 
	 * 18) Close all windows
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// To Download and set the path 
		WebDriverManager.chromedriver().setup();
				
		// To Launch the chromebrowser
		ChromeDriver driver = new ChromeDriver();
				
		//To Launch the URL
		driver.get("https://www.nykaa.com/");
				
		//To Maximise the window
		driver.manage().window().maximize();
				
		//To add wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		//To Mouseover on Brands and Search L'Oreal Paris 
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
        WebElement ele = driver.findElement(By.linkText("L'Oreal Paris"));
        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();

		//To Check the title contains L'Oreal Paris(Hint-GetTitle) 
       if (driver.getTitle().contains("L'Oreal Paris"))
       {
    	   System.out.println("Nykaa page is showing L'Oreal Paris products");
       }
       else
       {
    	   System.out.println("Wrong page is displayed");
       }
		
       //Click sort By and select customer top rated 
       WebElement sort = driver.findElement(By.xpath("//span[@class='sort-name']"));
       wait.until(ExpectedConditions.elementToBeClickable(sort)).click();
       driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
       
       //Click Category and click Hair->Click haircare->Shampoo
       driver.findElement(By.xpath("//span[text()='Category']")).click();
       Thread.sleep(5000);
       driver.findElement(By.xpath("//span[text()='Hair']")).click();
       driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
       WebElement shampoo = driver.findElement(By.xpath("//span[text()='Shampoo']"));
       wait.until(ExpectedConditions.elementToBeClickable(shampoo)).click();
       
       //Click->Concern->Color Protection 
       Thread.sleep(5000);
       driver.findElement(By.xpath("//span[text()='Concern']")).click();
       driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
       
       //Check whether the Filter is applied with Shampoo 
       List<WebElement> filterValue = driver.findElements(By.xpath("//div[@class='css-1emjbq5']"));
       System.out.println("Filters applied are :");
       for(int i=0; i<filterValue.size(); i++)
       {
    	   String text = filterValue.get(i).getText();
    	   System.out.println(text);  
       }
       
        //Click on L'Oreal Paris Colour Protect Shampoo 
       driver.findElement(By.xpath("//div[@class='css-43m2vm']")).click();
       
       //GO to the new window
       Set<String> windowHandles = driver.getWindowHandles();
       List<String> windows = new ArrayList<String>(windowHandles);
       driver.switchTo().window(windows.get(1));
       
       //Select size as 175ml
       WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
       size.click();
       Select select = new Select(size);
       select.selectByVisibleText("175ml");
       
       //Print the MRP of the product 
       String text = driver.findElement(By.xpath("//span[@class='css-1jczs19']")).getText();
       System.out.println("MRP of the product is : " + text);
       
       //Click on ADD to BAG 
       driver.findElement(By.xpath("//button[@class=' css-12z4fj0']")).click();
       
       //Go to Shopping Bag 
       driver.findElement(By.xpath("//span[@class='cart-count']")).click();
       
       //To move to frame
       driver.switchTo().frame(0);
       
       //Print the Grand Total amount
       Thread.sleep(5000);
       String total = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText(); 
       System.out.println("The Grand Total Amount is : "+ total);
       
       //Click Proceed 
       driver.findElement(By.xpath("//span[text()='Proceed']")).click();
       
       //Click on Continue as Guest 
       driver.switchTo().window(windows.get(1));
       
       driver.findElement(By.xpath("//button[@class='btn full big']")).click();
       
       // Check if this grand total is the same in step 14 
       String text2 = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//div//span")).getText();
       if(total.contains(text2))
       {
    	 System.out.println("The Grand Total Values are same in both the places as "+ text2);  
       }
       else
       {
    	   System.out.println("Grand Total Values are different");
       }
       
       //Close all windows
       driver.quit();
	}

}
