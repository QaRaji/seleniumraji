package raji.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
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
import org.testng.annotations.Test;

import raji.pageobjects.CartPage;
import raji.pageobjects.Checkoutpage;
import raji.pageobjects.confirmationpage;
import raji.pageobjects.landingPage;
import raji.pageobjects.productCatalogue;
import raji.testcomponents.BaseTest;
import raji.testcomponents.retry;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"errorhandling"},retryAnalyzer=retry.class)
	public void loginerrorvalidation() throws IOException, InterruptedException
	{
	
		
		String[] list={"ZARA COAT 3","ADIDAS ORIGINAL"};
		List<String> productlist = Arrays.asList(list);
		Boolean a=null;
		productCatalogue pc=landingpage.logindetails("rajalakshmi271996@gmail.com","Aqws@1111l");
		//Assert.assertEquals("Incorrect email or password.",landingpage.errormsg());
		Assert.assertEquals("Incorrect email or password.",landingpage.errormsg());
        
	        
	
	}
	@Test
	public void productcatalogueerror() throws InterruptedException
	{     String[] list={"ZARA COAT 3","ADIDAS ORIGINAL"};
	      List<String> productlist = Arrays.asList(list);
	      Boolean a=null;
		productCatalogue pc=landingpage.logindetails("rajalakshmi271996@gmail.com","Qwer1234");
        pc.getproductslist();
        pc.getproductname(productlist);
     //System.out.println(toast.getText());
		CartPage cp=pc.cartontheheader(); 
		Boolean match=cp.verifyerrorproduct("ZARS");
		Assert.assertFalse(match);
	}

}