package raji.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class submitordertest extends BaseTest
{
	//String[] list={"ZARA COAT 3","ADIDAS ORIGINAL"};
	//List<String> productlist = Arrays.asList(list);
	//Boolean a=null;
	@Test(dataProvider="getdata",groups= {"purchase"})
	//public void samplestandalonetest(String email,String password,String productname) throws IOException, InterruptedException
	
	public void samplestandalonetest(HashMap<String,String> input) throws IOException, InterruptedException
	
{
		//landing page
		//landingPage landingpage= launchapplication();because we use beforemethod on basetest class
		productCatalogue pc=landingpage.logindetails(input.get("email"),input.get("password"));
	        pc.getproductslist();
	        //pc.getproductname1(productname);
	        pc.getproductname1(input.get("productname"));
	     //System.out.println(toast.getText());
			CartPage cp=pc.cartontheheader(); 
			//Boolean match=cp.verifyerrorproduct(productname);
			Boolean match=cp.verifyerrorproduct(input.get("productname"));
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
		
		//Boolean match=order.verifyorder(productlist);
		//Assert.assertTrue(match);
		Boolean match1=order.verifyorder2("ZARA COAT 3");
		Assert.assertTrue(match1);
		
		
	}
	@DataProvider
	public Object[][] getdata() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java/raji//data/purchase.json");
		
		//static method to return data 
		//return new Object[][] {{data.get(0)},{data.get(1)}};
		//dynamic method to return data
		System.out.println(data.size());
		Object[][] results = new Object[data.size()][];
		
	
		

		//int i=0;
		int index = 0;

		for (Map<String, String> datatempmap : data) {

			results[index++] = new Object[] {datatempmap};

			}
	
		return results;
	}
	
	

//extent reports
}
	
	
	//using dataprovider
	//@DataProvider
	//public  Object[][] getdata()
	//{
		//return new Object[][] {{"rajicse159@gmail.com","Aqws@1111","ZARA COAT 3"},{"rajalakshmi271996@gmail.com","Qwer1234","ADIDAS ORIGINAL"}};
	
	//}
	//using dataprovider with hashmap
	//@DataProvider
	//public Object[][] getdata()
	//{
		//HashMap<String,String> map=new HashMap<String,String>();
		//map.put("email","rajicse159@gmail.com");
		//map.put("password","Aqws@1111");
		//map.put("productname","ZARA COAT 3");
		//HashMap<String,String> map1=new HashMap<String,String>();
		//map1.put("email","rajalakshmi271996@gmail.com");
		//map1.put("password","Qwer1234");
		//map1.put("productname","ADIDAS ORIGINAL");
		//return new Object[][] {{map},{map1}};//Object is a parent for all type of variable eg:int,string,float
		
	//}
	


