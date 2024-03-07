package raji.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import raji.pageobjects.CartPage;
import raji.pageobjects.OrderPage;

public  class AbstactComponent {
	WebDriver driver;
	public AbstactComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
		
		// TODO Auto-generated constructor stub
		
	}
	//driver.findElement(By.cssSelector("[routerlink*='cart']"))
	@FindBy(css="[routerlink*='cart']")
	WebElement cartheader;
	//button[routerlink*='myorders']
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderheader;
	public void waitforelementstoappear(By findby) 
	
	{
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));	
	}
	public void visibilityofelements(WebElement element)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void invisibilityoftheelements(WebElement element) throws InterruptedException
	
	{
		Thread.sleep(2000);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10) );
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public CartPage cartontheheader()
	{
		cartheader.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}
	public OrderPage orderhistoryheader()
	{
		orderheader.click();
		OrderPage order=new OrderPage(driver);
		return order;
	}
	
		

	

}
