package abcpack;

import static org.testng.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenLinks {
	
	WebDriver d;
	@Test
	public void testLinks() 
	{
		// Load web page
		d.get("https://www.selenium.dev/");
		// Verify page title
		assertEquals(d.getTitle(),"Selenium");
		List<WebElement> l=d.findElements(By.className("nav-link"));
		System.out.println("No of links:"+l.size());
		for(WebElement e:l)
		{
			try
			{
				
				if(e.getAttribute("href")!=null)
				{
					URL u=new URL(e.getAttribute("href"));
					HttpURLConnection con=(HttpURLConnection)u.openConnection();
					con.setConnectTimeout(60000);
					con.connect();
					//System.out.println("Response code:"+u+"--->"+con.getResponseCode() );
					if(con.getResponseCode() == 200)
					{
						System.out.println("Link:"+u+"---> is working fine");
					}
					else
					{
						System.out.println("Link:"+u+"---> is not working");	
					}
				}
				
			}
			catch(Exception ie)
			{
				System.out.println(ie);
				Assert.fail();
			}
			
		}
		
	}
	@BeforeMethod
	public void setUp()
	{
		// Launch the browser
		d=new FirefoxDriver();
		//d=new ChromeDriver();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		d.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3));
		d.manage().deleteAllCookies();
		d.manage().window().maximize();
	}
	@AfterMethod
	public void tearDown()
	{
		// Close the browser
		d.quit();
	}

}
