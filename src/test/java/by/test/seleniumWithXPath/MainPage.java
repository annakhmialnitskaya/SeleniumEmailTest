package by.test.seleniumWithXPath;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

	private static final String URL = "https://mail.ru/";
	private static final String LOGIN_XPATH = ".//*[@id='mailbox__login']";
	private static final String PASSWORD_XPATH = ".//*[@id='mailbox__password']";
	private static final String SIGN_IN_BUTTON_XPATH = ".//*[@id='mailbox__auth__button']";
	private static final String LOGIN = "anna_kh_test@mail.ru";
	private static final String PASSWORD = "Test123";

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		getDriver().get(URL);
	}

	public HomePage getHomePage() {

		WebElement emailElement = (new WebDriverWait(driver, Duration.ofSeconds(2)))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_XPATH)));
		emailElement.sendKeys(LOGIN);

		WebElement passElement = (new WebDriverWait(driver, Duration.ofSeconds(2)))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PASSWORD_XPATH)));
		passElement.sendKeys(PASSWORD);

		WebElement signInElement = (new WebDriverWait(driver, Duration.ofSeconds(2)))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SIGN_IN_BUTTON_XPATH)));
		signInElement.submit();

		return new HomePage(driver);
	}
}
