package raji.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import raji.AbstractComponents.AbstactComponent;

public class OrderPage extends AbstactComponent {
 
	WebDriver driver;
	Boolean a=null;

	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	//tr td:nth-child(3)
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordername;
public Boolean verifyorder(List<String> productlist)	
	
	{
	for(String pp:productlist)
	{

		 Boolean b=ordername.stream().anyMatch(c->c.getText().equalsIgnoreCase(pp));
		a=b;
		
	
	}
return a;

}
public Boolean verifyorder2(String String)	

{

	 Boolean b=ordername.stream().anyMatch(c->c.getText().equalsIgnoreCase(String));
	return b;
	
}
	
}