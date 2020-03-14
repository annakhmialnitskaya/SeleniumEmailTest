package by.test.seleniumWithXPath;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {

	private final static String URL = "http://homepage.mail.ru";

	private static final String WRITE_LETTER_XPATH = ".//*[@id='b-toolbar__left']/div/div/div[2]/div/a/span";
	private static final String RECIPIENT_XPATH = ".//div[@id='b-compose']/div/div/form/div[1]/div/div[3]/div[1]/div/div/div[2]/div/div/div/textarea[2]";
	private static final String SUBJECT_MESSAGE_XPATH = ".//div[@id='b-compose']/div/div/form/div[1]/div/div[3]/div[4]/div/div/div[2]/div/input";
	private static final String TEXTAREA_XPATH = ".//*[@id='tinymce']";
	private static final String SEND_BUTTON_XPATH = "//div[@id='b-toolbar__right']/div[3]/div/div[2]/div/div/span";
	private static final String ELEMENT_AFTER_SENDING_XPATH = "//*[@id='b-compose__sent']/div/div[2]/div[1]/span";
	private static final String IFRAME_NAME = "iframe";

	private static final String RECIPIENT = "anna.khmialnitskaya@gmail.com";
	private static final String SUBJECT_MESSAGE = "Anna Khmialnitskaya: Sending email using Selenium";
	private static final String MESSAGE = "https://github.com/annakhmialnitskaya/SeleniumEmail";

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		getDriver().get(URL);
	}

	public void writeLetterElement() {
		WebElement writeLetterElement = (new WebDriverWait(driver, Duration.ofSeconds(10)))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(WRITE_LETTER_XPATH)));
		writeLetterElement.click();
	}

	public void selectRecipientElement() {
		WebElement recipientElement = driver.findElement(By.xpath(RECIPIENT_XPATH));
		recipientElement.sendKeys(RECIPIENT);
	}

	public void selectSubjectMessageElement() {
		WebElement subjectMessageElement = driver.findElement(By.xpath(SUBJECT_MESSAGE_XPATH));
		subjectMessageElement.sendKeys(SUBJECT_MESSAGE);
	}

	public void writeMessage() {
		driver.switchTo().defaultContent();
		List<WebElement> iframeList = driver.findElements(By.tagName(IFRAME_NAME));
		driver.switchTo().frame(iframeList.get(3));
		WebElement textareaElement = driver.findElement(By.xpath(TEXTAREA_XPATH));
		textareaElement.clear();
		textareaElement.click();
		textareaElement.sendKeys(MESSAGE);
		driver.switchTo().defaultContent();
	}

	public void sendMessage() {
		driver.findElement(By.xpath(SEND_BUTTON_XPATH)).click();
	}

	public void loadPageAfterSending() {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(1000));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(ELEMENT_AFTER_SENDING_XPATH))));
	}
}
