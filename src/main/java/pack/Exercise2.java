package pack;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

//Steps:
//1. Navigate to https://www.weightwatchers.com/us/
//2. Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
//3. On the right corner of the page, click on “Find a Studio”
//4. Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
//5. In the search field, search for meetings for zip code: 10011
//6. Print the title of the first result and the distance (located on the right of location title/name)
//7. Click on the first search result and then, verify displayed location name/title matches with the name of the first searched result that was clicked.
//8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)
//9. Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week
//e.g. printMeetings("Sun")
//Output should be:
//Person A  3
//Person B  1

public class Exercise2 {

	WebDriver driver;

	@Before
	public void openBasePage() {
		String path=System.getProperty("user.dir");
		System.out.println(path);
		System.setProperty("webdriver.chrome.driver", path+"\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.weightwatchers.com/us/");
		driver.manage().window().maximize();
		
	}

	@Test
	public void testGoogleSearch() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		Thread.sleep(5000);
		String title = driver.getTitle();
		//Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
		Assert.assertEquals(title, "WW (Weight Watchers): Weight Loss & Wellness Help | WW USA");
		WebElement findWorkshop = driver.findElement(By.xpath("//span[text()='Find a Workshop']"));
		findWorkshop.click();
		Thread.sleep(5000);
		title = driver.getTitle();
		Assert.assertEquals(title, "Find WW Studios & Meetings Near You | WW USA");

		WebElement searchBox = driver.findElement(By.id("meetingSearch"));
		searchBox.sendKeys("10011");
		WebElement button = driver.findElement(By.xpath("//button[@class='btn spice-translated']"));
		button.click();
		Thread.sleep(5000);

		String firstResult = driver.findElement(By.xpath("//span[@ng-if='!linkName']")).getText();
		String firstDistance = driver.findElement(By.xpath("//div[@class='location__distance']")).getText();

		System.out.println(firstResult);
		System.out.println(firstDistance);
		driver.findElement(By.xpath("//span[@ng-if='!linkName']")).click();
		String firstResultNextPage = driver.findElement(By.xpath("//div[@class='location__name']")).getText();

		Assert.assertEquals(firstResult, firstResultNextPage);
		printMeetings("Thu");
	}

	//to print the number of meetings of each person
	public void printMeetings(String day) {
		
		HashMap<String, Integer> hm =new HashMap<>();
		String xpath="//div[text()='"+day+"']//parent::div/div/div/div[2]";
		List<WebElement> ll=driver.findElements(By.xpath(xpath));
		
		for(WebElement element:ll){
			if(hm.containsKey(element.getText())){
				hm.put(element.getText(), hm.get(element.getText())+1);
			}
			else
				hm.put(element.getText(), 1);
		}
		
		for(java.util.HashMap.Entry<String, Integer> entry : hm.entrySet()){
			System.out.println(entry.getKey()+" "+entry.getValue());
		}


	}

	@After
	public void close() {
		 driver.quit();
	}

	

}
