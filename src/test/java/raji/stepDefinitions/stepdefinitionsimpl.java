package raji.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import raji.pageobjects.CartPage;
import raji.pageobjects.Checkoutpage;
import raji.pageobjects.confirmationpage;
import raji.pageobjects.landingPage;
import raji.pageobjects.productCatalogue;
import raji.testcomponents.BaseTest;

public class stepdefinitionsimpl extends BaseTest {
	public landingPage landingpage;
	public productCatalogue pc;
	public confirmationpage confirmp;
	public CartPage cp;
	//public Checkoutpage checkpage;
	//Boolean match=null;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage=launchapplication();
	}
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password)
	{
		 pc=landingpage.logindetails(username,password);
	}
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String product) throws InterruptedException
	{
		pc.getproductslist();
		pc.getproductname1(product);
		
	}
	 @And("^checkout (.+)  and submit the order$")
	public void checkout_and_submit_the_order(String product) throws InterruptedException
	{
		 cp=pc.cartontheheader(); 
			
			Boolean match=cp.verifyerrorproduct(product);
			Assert.assertTrue(match);
		Checkoutpage checkpage=cp.checkout();
		checkpage.selectcountry();
	 confirmp=checkpage.submitorder();

	}
	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String string)
	{
		String text=confirmp.confirmation();
		Assert.assertTrue(text.equalsIgnoreCase(string));
		driver.quit();
		
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String stringarg) {
		Assert.assertEquals(stringarg,landingpage.errormsg());
		driver.quit();
	}
	

	
	

}
