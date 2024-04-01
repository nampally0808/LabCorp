package com.labcorp.www.stepdefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.labcorp.www.framework.BaseTest;
import com.labcorp.www.pages.CarrersPage;
import com.labcorp.www.pages.HomePage;
import com.labcorp.www.pages.JobDetailsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps{
	WebDriver driver;
	HomePage homePage;
	CarrersPage carrersPage;
	JobDetailsPage jobDetailsPage;
	@Given("Launch chrome browser and Navigate to Homepage")
	public void launch_chrome_browser_and_navigate_to_homepage() {
		System.setProperty("webdriver.chrome.driver", "C://users//NAMPA//Desktop//chromedriver-win64//chromedriver-win64//chromedriver.exe/");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.labcorp.com/");
	}
	
	@Given("Click on Carrers link")
	public void click_on_carrers_link() {
	  homePage=new HomePage(driver);
	  homePage.navigateToCarrersPage();
	}

	@When("Search for Job Position {string}")
	public void search_for_job_position(String role) {
	    carrersPage=new CarrersPage(driver);
	    carrersPage.searchRole(role);
	}

	@When("Select first job")
	public void select_first_job() {
		carrersPage.selectFirstJob();
	}
	Map<String,String> expectedJobDetails;
	@Then("Validate job details {string}, {string}, {string} and {string}")
	public void validate_job_details_and(String jobTitle, String jobId, String jobLocation, String jobDescription) {
		expectedJobDetails=carrersPage.validateJobDetails(jobTitle, jobId, jobLocation, jobDescription);
	}

	@Then("Validate job details in Job Details page")
	public void validate_job_details_in_apply_now_page() {
		jobDetailsPage=new JobDetailsPage(driver);
		jobDetailsPage.validateJobDetails(expectedJobDetails);
	}

	@When("Click to Return to Job Search")
	public void click_to_return_to_job_search() {
		jobDetailsPage.navigateToJobSearch();
	}

	@Then("User should be navigated to Carrers page")
	public void user_should_be_navigated_to_carrers_page() {
	    carrersPage.validateJobSearchTextBox();
	}
	
	@Then("Close the browser")
	public void close_the_browser() {
	    driver.quit();
	}
}
