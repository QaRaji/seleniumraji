package raji.tests;

import org.testng.annotations.DataProvider;
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
import raji.pageobjects.OrderPage;
import raji.pageobjects.confirmationpage;
import raji.pageobjects.landingPage;
import raji.pageobjects.productCatalogue;
import raji.testcomponents.BaseTest;

public class SampleStandalone extends BaseTest
{
	String[] list={"ZARA COAT 3","ADIDAS ORIGINAL"};
	List<String> productlist = Arrays.asList(list);
	Boolean a=null;
	@Test
	public void samplestandalonetest() throws IOException, InterruptedException
	{
		//landing page
		//landingPage landingpage= launchapplication();because we use beforemethod on basetest class
		productCatalogue pc=landingpage.logindetails("rajicse159@gmail.com","Aqws@1111");
	        pc.getproductslist();
	        pc.getproductname(productlist);
	     //System.out.println(toast.getText());
			CartPage cp=pc.cartontheheader(); 
			Boolean match=cp.verifyproduct(productlist);
			Assert.assertTrue(match);//all assertion in the test
			Checkoutpage checkpage=cp.checkout();
			checkpage.selectcountry();
			confirmationpage confirmp=checkpage.submitorder();
	String text=confirmp.confirmation();
	Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	@Test(dependsOnMethods = {"samplestandalonetest"})
	public void orderhistorytest()
	{
		productCatalogue pc=landingpage.logindetails("rajicse159@gmail.com","Aqws@1111");
		OrderPage order=pc.orderhistoryheader();
		
		Boolean match=order.verifyorder(productlist);
		Assert.assertTrue(match);
		Boolean match1=order.verifyorder2("ZARA COAT 3");
		Assert.assertTrue(match1);
		
		
	}
	

}
