package raji.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import raji.AbstractComponents.AbstactComponent;

public class CartPage extends AbstactComponent {
 
	WebDriver driver;

	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//List<WebElement> cart = driver.findElements(By.cssSelector("div[class='cart'] h3"));
	@FindBy(css="div[class='cart'] h3")
	List<WebElement> cart;
	//driver.findElement(By.cssSelector("div[class*='ng-star-inserted'] button[class='btn btn-primary']")).click();
	@FindBy(css="div[class*='ng-star-inserted'] button[class='btn btn-primary']")
	WebElement checkout;
	
	Boolean a=null;
	
	public Boolean verifyproduct(List<String> productlist)	
	
	{
	for(String pp:productlist)
	{

		 Boolean b=cart.stream().anyMatch(c->c.getText().equalsIgnoreCase(pp));
		a=b;
		
	
	}
return a;

}
	public Checkoutpage checkout()
	{
		checkout.click();
		Checkoutpage checkpage=new Checkoutpage(driver);
		return checkpage;
	}
	public Boolean verifyerrorproduct(String String)	
	{
		Boolean b=cart.stream().anyMatch(c->c.getText().equalsIgnoreCase(String));
		return b;
	}
}
 