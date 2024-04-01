package com.labcorp.www.pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class CarrersPage {
	WebDriver driver;
	public CarrersPage(WebDriver driver) {
		this.driver=driver;
	}
	private By searchBox() {
		return By.id("typehead");
	}
	
	private By searchButton() {
		return By.xpath("//button[@aria-label='Search']");
	}
	
	private By jobTitle() {
		return By.xpath("//div[@class='job-title']/span");
	}
	
	private By jobLocation() {
		return By.xpath("//span[contains(@class,'job-location')]");
	}
	
	private By jobId() {
		return By.xpath("//span[contains(text(),'Job Id')]//following-sibling::span");
	}
	
	private By jobDescription() {
		return By.xpath("//p[contains(@class,'job-description')]");
	}
	
	
	
	
	
	public void searchRole(String role) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchBox()));
		driver.findElement(searchBox()).sendKeys(role);
		driver.findElement(searchButton()).click();
	}
	
	public Map<String,String> validateJobDetails(String expectedTitle,String expectedJobId,String expectedJobLocation,String expectedJobDescription) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jobTitle()));
		Assert.assertEquals("Incorrect job title",expectedTitle, driver.findElement(jobTitle()).getText());
		Assert.assertTrue("Incorrect job location", driver.findElement(jobLocation()).getText().contains(expectedJobLocation));
		Assert.assertEquals("Incorrect job id",expectedJobId, driver.findElement(jobId()).getText());
		Assert.assertTrue("Incorrect job description",driver.findElement(jobDescription()).getText().contains(expectedJobDescription));
		Map<String,String> expectedResults=new HashMap<>();
		expectedResults.put("jobId", driver.findElement(jobId()).getText());
		expectedResults.put("jobTitle", driver.findElement(jobTitle()).getText());
		expectedResults.put("jobLocation", driver.findElement(jobLocation()).getText());
		expectedResults.put("jobDescription", expectedJobDescription);
		return expectedResults;
	}
	
	public void selectFirstJob() {
		driver.findElement(jobTitle()).click();
	}
	
	public void validateJobSearchTextBox() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchButton()));
		Assert.assertEquals("Search Button is displayed",true, driver.findElement(searchButton()).isDisplayed());
	}
	
	
	

}
