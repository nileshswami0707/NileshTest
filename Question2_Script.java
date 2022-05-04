package seleniumCode;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ThinkBridgeTest {
	WebDriver driver;
	private static String url = "https://jt-dev.azurewebsites.net/#/SignUp";
	private static String [] expected = {"English","Dutch"};
	private static String elementName1 = "name";
	private static String elementName2 = "companyName";
	private static String elementName3 = "email";
	private static String elementName4 = "termsCheckbox";
	private static String elementName5 = "getStartedButton";
	private static String xpathElement1 = "//input[@id='name']";
	private static String xpathElement2 = "//input[@id='orgName']";
	private static String xpathElement3 = "//input[@id='singUpEmail']";
	private static String xpathElement4 = "//span[contains(text(),'I agree to the')]";
	private static String xpathElement5 = "//button[contains(text(),'Get Started')]";
	private static String ValueElementName1 = "Nilesh Swami";
	private static String ValueElementName2 = "Fis,Pune";
	private static String ValueElementName3 = "nileshswami0707@gmail.com";
	private static String xpathdropDown = "//span[contains(text(),'English')]/parent::span";
	private static String browserType = "webdriver.chrome.driver";
	private static String browserPath = "D:\\chrome\\chromedriver.exe";
	
	@BeforeClass
	public void properties() {
		 System.setProperty(browserType,browserPath);  
		   driver=new ChromeDriver();
	}

  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	    driver.get(url);    
  }

  @AfterMethod
  public void afterMethod() {
	//Close current browser. 
	  driver.close();  
  }
  
  @Test
  public void test() throws InterruptedException {
	  //Validate that the dropdown has "English" and "Dutch"
	   List<WebElement> options = driver.findElements(By.xpath(xpathdropDown));
	  for(WebElement act:options) {
		  for(int i=0;i<expected.length;i++) {
			  if(act.getText().equals(expected[i])){
				  System.out.println("matched");
			  }
		  }
	  }
	  //Enetering the values name, Org name and email into textBox
	  inputValues(elementName1,xpathElement1,ValueElementName1);
	  inputValues(elementName2,xpathElement2,ValueElementName2);
	  inputValues(elementName3,xpathElement3,ValueElementName3);
	  
	//Clicking on 'Get Started' button after selecting checkbox
	  clickElements(elementName4,xpathElement4);
	  clickElements(elementName5,xpathElement5);
  }
  
  //Method for input the values
  public void waitTime(WebElement element) {
	   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5)); 
	   wait1.until(ExpectedConditions.visibilityOf(element));
  }
  
  //Method for clicking the button
  public void inputValues(String element,String locator, String value) {
	  WebElement element1 = driver.findElement(By.xpath(locator));
	  element1.sendKeys(value);
  }
 
  public void clickElements(String element,String locator) {
	  WebElement termsCheckbox = driver.findElement(By.xpath("//span[contains(text(),'I agree to the')]"));
	  termsCheckbox.click();
  }
}
