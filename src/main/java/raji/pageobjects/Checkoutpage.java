package raji.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import raji.AbstractComponents.AbstactComponent;

public class Checkoutpage extends AbstactComponent{
	
	
	WebDriver driver;
	public Checkoutpage(WebDriver driver)
	{    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement country=driver.findElement(By.cssSelector("input[placeholder*='Select Country']"));
	@FindBy(css="input[placeholder*='Select Country']")
	WebElement country;
	//driver.findElements(By.cssSelector("section[class*='ta-results'] button i "))
	
	@FindBy(css="section[class*='ta-results'] button i ")
	List<WebElement> countryselect;
	//driver.findElement(By.cssSelector(".action__submit"))
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void selectcountry()
	{
		visibilityofelements(country);
		Actions a=new Actions(driver);
		a.click(country).sendKeys("India").build().perform();
		
		countryselect.get(1).click();
	}

	public confirmationpage submitorder()
	{
		submit.click();
		confirmationpage confirmp=new confirmationpage(driver);
		return confirmp;
	}
}
