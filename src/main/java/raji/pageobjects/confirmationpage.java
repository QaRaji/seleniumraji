package raji.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import raji.AbstractComponents.AbstactComponent;

public class confirmationpage extends AbstactComponent{

	WebDriver driver;
	public confirmationpage(WebDriver driver)
	{    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		//String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
		@FindBy(css=".hero-primary")
		WebElement confirmationtxt;
		
		public String confirmation()
		{
			return confirmationtxt.getText();
		}
		
	
}
