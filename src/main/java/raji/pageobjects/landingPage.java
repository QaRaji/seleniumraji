package raji.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import raji.AbstractComponents.AbstactComponent;

public class landingPage extends AbstactComponent{
	
	 WebDriver driver;

	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	//driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement password;
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement login;
	//ng-tns-c4-2 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	public productCatalogue logindetails(String uname,String pwd)
	{
		userEmail.sendKeys(uname);
		password.sendKeys(pwd);
		login.click();
		productCatalogue pc=new productCatalogue(driver);
		return pc;
		
	}
	public void url()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
     public String errormsg()
     {   visibilityofelements(errormsg);
    	 return errormsg.getText();
     }

}
