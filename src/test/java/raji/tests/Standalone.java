package raji.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Standalone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		String[] list={"ZARA COAT 3","ADIDAS ORIGINAL"};
		List<String> productlist = Arrays.asList(list);
		
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("rajicse159@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aqws@1111");
		driver.findElement(By.id("login")).click();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement toast = driver.findElement(By.id("toast-container"));
		for(String p:productlist)
		{
		WebElement productf = products.stream().filter(product1->product1.findElement(By.cssSelector("b")).getText()
				.equals(p)).findFirst().orElse(null);
		if(p!=null)
		{
	productf.findElement(By.cssSelector(".card-body button:last-child")).click();
	
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	wait.until(ExpectedConditions.visibilityOf(toast));
		}
		}
	
	System.out.println(toast.getText());
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	List<WebElement> cart = driver.findElements(By.cssSelector("div[class='cart'] h3"));
	for(String pp:productlist)
	{
		Boolean b=cart.stream().anyMatch(c->c.getText().equalsIgnoreCase(pp));
		Assert.assertTrue(b);
	}
		
	driver.findElement(By.cssSelector("div[class*='ng-star-inserted'] button[class='btn btn-primary']")).click();
	WebElement country=driver.findElement(By.cssSelector("input[placeholder*='Select Country']"));
;	//WebElement country = driver.findElement(By.cssSelector("div[class='user__address'] div[class='form-group']"));
	wait.until(ExpectedConditions.visibilityOf(country));
	//country.sendKeys("I");
	Actions a=new Actions(driver);
	a.click(country).sendKeys("India").build().perform();
	driver.findElements(By.cssSelector("section[class*='ta-results'] button i "))
	.get(1).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}

}
