package com.ufukbayraktar.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ufukbayraktar.selenium.Driver;

public class SearchPageTest {
	
	private static Logger logger = Logger.getLogger(LoginPageTest.class);
	private static String url = "https://www.gittigidiyor.com/";
	private static String redirectLoginUrl = "https://www.gittigidiyor.com/uye-girisi";
	
	@Test
	public void searchPageTest() {
		
		List<WebElement> searcedItems = new ArrayList<WebElement>();
		logger.info("Running searchPageTest.");
		Driver driver = new Driver();
		driver.setupDriver(url);
		driver.redirectUrl(redirectLoginUrl);
		driver.setKeyById("L-UserNameField", "testertester@hotmail.com");
		driver.setKeyById("L-PasswordField", "testtest1");
		driver.clickById("gg-login-enter");
		driver.setByXPath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input", "bilgisayar");
		driver.clickByXPath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button");
		driver.redirectSearcedPage(Long.valueOf(2));
		searcedItems = driver.getSearchItemList();
		int randomProductIndex = new Random().nextInt(searcedItems.size());
        WebElement productElement = driver.getWebDriver().findElement(By.cssSelector(".products-container > li:nth-child(" + randomProductIndex + ")"));
        productElement.findElement(By.cssSelector("a")).click();
        String price = driver.getProductPrice();
        driver.addToBasket();
        String basketPrice = driver.basketPrice();
        assertEquals(price, basketPrice);
        By optionSelectedElement = By.cssSelector("option[value='2']");
        driver.changeAmountValue(optionSelectedElement);
        String totalProductTest = driver.getTotalProductText();
        assertTrue(totalProductTest.contains("2 Adet"));
        driver.clearBasket();
        String lastTotalProduct = driver.getTotalProductText();
        assertTrue(lastTotalProduct.contains("0 Adet"));
        
	}

}
