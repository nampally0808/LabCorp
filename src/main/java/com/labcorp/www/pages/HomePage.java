package com.labcorp.www.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	private WebElement carrersLink() {
		return driver.findElement(By.linkText("Careers"));
	}
	
	private By closeIcon() {
		return By.xpath("//button[@aria-label='Close']");
	}
	
	public void navigateToCarrersPage() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(closeIcon()));
		driver.findElement(closeIcon()).click();
		carrersLink().click();
	}
	
	

}
