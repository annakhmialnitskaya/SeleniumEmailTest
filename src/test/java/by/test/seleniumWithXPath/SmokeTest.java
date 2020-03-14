package by.test.seleniumWithXPath;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SmokeTest {

	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	private static final String DRIVER_LOCATION = "C:/programs/selenium/chromedriver.exe";

	private WebDriver driver;

	@Before
	public void initBrowser() {
		System.setProperty(DRIVER_NAME, DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test
	public void sendMassageByMailRu() {
		// Open main page
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		// Open home page
		HomePage homePage = mainPage.getHomePage();
		// Click button "writeLetter"
		homePage.writeLetterElement();
		// Enter the recipient
		homePage.selectRecipientElement();
		// Enter the subject message
		homePage.selectSubjectMessageElement();
		// Enter message
		homePage.writeMessage();
		// Send message
		homePage.sendMessage();
		// Load page after sending
		homePage.loadPageAfterSending();
	}

	@After
	public void destroyBrowser() {
		driver.quit();
	}
}
