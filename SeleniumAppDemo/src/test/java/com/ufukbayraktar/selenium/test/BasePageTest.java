package com.ufukbayraktar.selenium.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.ufukbayraktar.selenium.Driver;

public class BasePageTest {
	
	private static Logger logger = Logger.getLogger(BasePageTest.class);
	private static String url = "https://www.gittigidiyor.com/";
	
	@Test
	public void basePageRedirectingTest() {
		
		//Ana sayfa açýlmasý testi.
		logger.info("Running basePageRedirectingTest.");
		Driver driver = new Driver();
		driver.setupDriver(url);
		driver.closeDriver();
		
	}
	

}
