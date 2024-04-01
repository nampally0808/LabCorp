package com.labcorp.www.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class JobDetailsPage {
	WebDriver driver;
	public JobDetailsPage(WebDriver driver) {
		this.driver=driver;
	}
	private By jobTitle() {
		return By.xpath("//h1[@class='job-title']");
	}
	
	private By jobLocation() {
		return By.xpath("//span[contains(@class,'job-location')]");
	}
	
	private By jobId() {
		return By.xpath("//span[contains(@class,'jobId')]");
	}
	
	private By jobDescription() {
		return By.xpath("//div[@data-ph-at-id='jobdescription-text']//p/span");
	}
	
	private By jobSearchLink() {
		return By.xpath("//a[@aria-label='Job Map']");
	}
	
	public void validateJobDetails(Map<String,String> expectedResults) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jobTitle()));
		Assert.assertEquals("Incorrect job title",expectedResults.get("jobTitle"), driver.findElement(jobTitle()).getText());
		Assert.assertTrue("Incorrect job location", driver.findElement(jobLocation()).getText().contains(expectedResults.get("jobLocation")));
		Assert.assertTrue("Incorrect job id",driver.findElement(jobId()).getText().contains(expectedResults.get("jobId")));
		System.out.println(driver.findElement(jobDescription()).getText());
		System.out.println(expectedResults.get("jobDescription"));
		Assert.assertTrue("Incorrect job description",driver.findElement(jobDescription()).getText().contains(expectedResults.get("jobDescription")));		
	}
	
	public void navigateToJobSearch() {
		driver.findElement(jobSearchLink()).click();
	}

}
