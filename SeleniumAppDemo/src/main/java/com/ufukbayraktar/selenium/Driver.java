package com.ufukbayraktar.selenium;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ufukbayraktar.selenium.model.Product;

public class Driver {

	private static Logger logger = Logger.getLogger(Driver.class);

	private WebDriver webDriver;
	private WebDriverWait webDriverWait;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriverWait getWebDriverWait() {
		return webDriverWait;
	}

	public void setWebDriverWait(WebDriverWait webDriverWait) {
		this.webDriverWait = webDriverWait;
	}

	public void setupDriver(String url) {
		try {
			if (webDriver != null)
				return;

			logger.info("Creating web driver for test.");
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Ben\\eclipse-workspace\\SeleniumAppDemo\\driver\\chromedriver.exe");
			webDriver = new ChromeDriver();
			logger.info("Driver: " + webDriver);
			logger.info("Redirecting to url address : https://www.gittigidiyor.com/");
			webDriver.get(url);
			webDriverWait = new WebDriverWait(webDriver, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void redirectUrl(String url) {
		logger.info("redirecting: " + url);
		webDriver.get(url);
	}

	public void closeDriver() {
		try {
			logger.info("Driver is closing. Driving " + webDriver);
			webDriver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setKeyById(String id, String value) {
		logger.info("Setting textboxt with info; id: " + id + " value: " + value);
		webDriver.findElement(By.id(id)).clear();
		webDriver.findElement(By.id(id)).sendKeys(value);
	}

	public void clickByXPath(String xpath) {
		logger.info("Clicking to button. Buttons xpath is: " + xpath);
		webDriver.findElement(By.xpath(xpath)).click();
	}

	public void clickById(String id) {
		logger.info("Clicking to button. Buttons id is: " + id);
		webDriver.findElement(By.id(id)).sendKeys(Keys.ENTER);
	}

	public void getUrl(String url) {
		logger.info("Page is redirecting: " + url);
		webDriver.get(url);
	}

	public void setByXPath(String xpath, String value) {
		logger.info("Setting to button. Buttons xpath is: " + xpath);
		webDriver.findElement(By.xpath(xpath)).sendKeys(value);
	}

	public List<WebElement> getSearchItemList() {
		logger.info("Trying to get product list.");
		return webDriver.findElements(By.cssSelector(".products-container > li"));
	}

	public void redirectSearcedPage(Long value) {
		logger.info("Redirecting to page of: " + value);
		webDriver.get("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=" + value);
	}

	public String getProductPrice() {
		logger.info("Getting price...");
		String lowPrice = webDriver.findElement(By.id("sp-price-lowPrice")).getText();
		String highPrice = webDriver.findElement(By.id("sp-price-highPrice")).getText();
		String discountPrice = webDriver.findElement(By.id("sp-price-discountPrice")).getText();
		if (!discountPrice.isEmpty()) {
			logger.info("Price is: " + discountPrice);
			return discountPrice;
		} else {
			if (!lowPrice.isEmpty()) {
				logger.info("Price is: " + lowPrice);
				return lowPrice;
			}else {
				logger.info("Price is: " + highPrice);
				return highPrice;
			}
				
		}
	}

	public String basketPrice() {
		logger.info("Trying to get basketPrice.");
		return webDriver.findElement(By.xpath("//*[@id=\"cart-price-container\"]/div[3]/p")).getText();
	}

	public void addToBasket() {
		clickById("add-to-basket");
		redirectUrl("https://www.gittigidiyor.com/sepetim");
	}

	public void changeAmountValue(By value) {
		logger.info("Trying to change amount value.");
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(value)).click();
	}

	public String getTotalProductText() {
		logger.info("Trying to get total product value.");
		By totalProductBy = By.cssSelector("li[class='clearfix total-price-sticky-container']>:nth-of-type(1)");
		return webDriver.findElement(totalProductBy).getText();

	}

	public void clearBasket() {
		logger.info("CreateBasket called.");
		By deleteProductBy = By.cssSelector("a[title='Sil']");
		clickByElement(deleteProductBy);
	}

	private void clickByElement(By byElement) {
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(byElement)).click();
	}

}
