package com.labcorp.www.framework;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
	public WebDriver driver;
	@Before
	public void openDriver(){
		driver=new ChromeDriver();
		
		driver.get("https://www.labcorp.com/");
	}

}
