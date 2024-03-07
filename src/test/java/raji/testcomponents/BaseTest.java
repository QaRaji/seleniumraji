package raji.testcomponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import raji.pageobjects.landingPage;

public class BaseTest {
	public WebDriver driver;
	public landingPage landingpage;
	public WebDriver initializedriver() throws IOException
	{
       Properties prop=new Properties();
      FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/raji/resources/globaldata.properties");
       prop.load(fis);
       //String browsername = prop.getProperty("browser");
       String browsername =System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
       //System.getProperty("browser")-->reads from terminal using cmd mvn test -Dbrowser=firefox
       //else it use from global properties
       
       if(browsername.contains("chrome"))
          {
    	ChromeOptions options=new ChromeOptions();
    	  if(browsername.contains("headless"))
    	{options.addArguments("headless");}
    	   driver=new ChromeDriver(options);
    	   driver.manage().window().setSize(new Dimension(1440,900));//full screen
		
    	   }
       else if(browsername.equalsIgnoreCase("firefox"))
       {
    	  
    	   
    	   driver=new FirefoxDriver();
    	   
       }
    	   else if(browsername.equalsIgnoreCase("edge"))
    	   {
    		   driver=new EdgeDriver();
    	   }
       driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   return driver;
	   
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException

	{
	//read json to string
		String Jsoncontent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	//string to hashmap use jacksondatabind
		ObjectMapper mapper=new ObjectMapper();
		//List<HashMap<String,String>> data=mapper.readValue(Jsoncontent,new TypeReference<List<HashMap<String,String>>>() {});
		List<HashMap<String, String>> data = mapper.readValue(Jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
		
	}
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException
	{
	TakesScreenshot scrn=(TakesScreenshot)driver;
	File source=scrn.getScreenshotAs(OutputType.FILE);
	File filepath=new File(System.getProperty("user.dir")+"reports"+ testcasename +".png");
	FileUtils.copyFile(source,filepath);
	return System.getProperty("user.dir")+"reports"+ testcasename +".png";
		
		
	}
	@BeforeMethod(alwaysRun=true)
	public landingPage launchapplication() throws IOException
	
	{
		driver=initializedriver();
		landingpage=new landingPage(driver);
	        landingpage.url();
	        return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.quit();
	}
	

}
