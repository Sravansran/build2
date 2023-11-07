package abcpack;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SliderDemo {
	
	WebDriver d;
	@Test
	public void testSlider() throws Exception
	{
		// Load web page
		d.get("https://jqueryui.com/slider/");
		// Verify page title
		assertEquals(d.getTitle(),"Slider | jQuery UI");
		// Switch driver focus to frame
		d.switchTo().frame(0);
		Actions a=new Actions(d);
		//a.dragAndDropBy(d.findElement(By.xpath("//div[@id='slider']/span")), 250, 0).perform();
		a.clickAndHold(d.findElement(By.xpath("//div[@id='slider']/span"))).moveByOffset(250, 0).release().build().perform();
		Thread.sleep(2000);
	}
	@BeforeMethod
	public void setUp()
	{
		// Launch the browser
		d=new FirefoxDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		d.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3));
	}
	@AfterMethod
	public void tearDown()
	{
		// Close the browser
	    d.quit();
	}

}
