package com.ufukbayraktar.selenium.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.ufukbayraktar.selenium.Driver;

public class LoginPageTest {
	
	private static Logger logger = Logger.getLogger(LoginPageTest.class);
	private static String url = "https://www.gittigidiyor.com/";
	private static String redirectUrl = "https://www.gittigidiyor.com/uye-girisi";
	
	@Test
	public void loginPageTest() {
		
		//id ve password ile giriþ testi
		logger.info("Running loginPageTest.");
		Driver driver = new Driver();
		driver.setupDriver(url);
		driver.redirectUrl(redirectUrl);
		driver.setKeyById("L-UserNameField", "ccanozerr");
		driver.setKeyById("L-PasswordField", "Naptn3ah.cn");
		//driver.clickByXPath("//*[@id=\"gg-login-enter\"]");
		driver.clickById("gg-login-enter");
		
	}

}
