package qaautomation.march2022;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTestAfterRefactor2 extends BaseWebTest {

	// karena semua extends dari BaseWebTest maka semua driver mesti menggunakan
	// driver.get()
	@Test
	public void test() {
		String username = "tomsmith";
		String password = "SuperSecretPassword!";
		driver.get().findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.get().findElement(By.id("password")).sendKeys(password);
		driver.get().findElement(By.xpath("//button[@type='submit']")).click();
		String actualText = driver.get().findElement(By.xpath("//div[@id='flash']")).getText();
		String expectedText = "You logged into a secure area!";
		Assert.assertTrue(actualText.contains(expectedText));
	}

	@Test
	public void test2() {
		String username = "tomsmith";
		String password = "SuperSecretPassword!";
		driver.get().findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.get().findElement(By.id("password")).sendKeys(password);
		String actualText = driver.get().findElement(By.xpath("//h2[normalize-space()='Login Page']")).getText();
		String expectedText = "Login Page";
		Assert.assertTrue(actualText.contains(expectedText));
	}

	@Test
	public void test3() {
		String username = "tomsmith";
		String password = "SuperSecretPassword!";
		driver.get().findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.get().findElement(By.id("password")).sendKeys(password);
		String actualText = driver.get().findElement(By.xpath("//h2[normalize-space()='Login Page']")).getText();
		String expectedText = "Login Page";
		Assert.assertTrue(actualText.contains(expectedText));
	}
}
