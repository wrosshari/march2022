package qaautomation.march2022;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseWebTestHeadless {
	// ini adalah command memanggil driver multi thread
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	// webdriver untuk wait juga menggunakan multithread
	ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<WebDriverWait>();

	@BeforeMethod
	public void setUp() {

		// karena sudah multithread driver mesti diganti menjadi driver.get()
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--windows-size=1920,1200");
		driver.set(new ChromeDriver(options));
		// explicitWait di call dengan menggunakan driver.get()
		explicitWait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(60)));
		driver.get().get("http://the-internet.herokuapp.com/secure");
		driver.get().manage().window().maximize();

	}

	@AfterMethod
	public void cleanUp() {
		driver.get().quit();
	}

}
