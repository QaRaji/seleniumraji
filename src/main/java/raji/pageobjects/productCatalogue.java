package raji.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import raji.AbstractComponents.AbstactComponent;

public class productCatalogue extends AbstactComponent {
	
	WebDriver driver;
	public productCatalogue(WebDriver driver)
	{    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products ;
	//WebElement toast = driver.findElement(By.id("toast-container"));
	@FindBy(id="toast-container")
	WebElement toast;
	//driver.findElement(By.cssSelector(".ng-animating")
	@FindBy(css=".ng-animating")
	WebElement animating;
	By ele=By.cssSelector(".mb-3");
	By add=By.cssSelector(".card-body button:last-child");
	
	public List<WebElement> getproductslist()
	
	{
		waitforelementstoappear(ele);
		
		return products;
	}
	public void getproductname(List<String> productlist) throws InterruptedException
	{
		for(String p:productlist)
		{
		WebElement productf = products.stream().filter(product1->product1.findElement(By.cssSelector("b")).getText()
				.equals(p)).findFirst().orElse(null);
		if(p!=null)
		{
	addtocart(productf);
	
		}
		}
		

	}
	public void getproductname1(String Productname) throws InterruptedException
	{
		WebElement productf = products.stream().filter(product1->product1.findElement(By.cssSelector("b")).getText()
				.equals(Productname)).findFirst().orElse(null);
		addtocart(productf);
	}
	public void addtocart(WebElement productf) throws InterruptedException
	
	{
		productf.findElement(add).click();
		visibilityofelements(toast);
		invisibilityoftheelements(animating);
		
		
		}
	

}
