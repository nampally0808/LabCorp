Feature: Validating Job search 
Scenario Outline: Validating Job search Functionality 

	Given Launch chrome browser and Navigate to Homepage
	And Click on Carrers link 
	When Search for Job Position "<Position Name>" 
	Then Validate job details "<Job Title>", "<Job Id>", "<Job Location>" and "<Job Description>" 
	And Select first job
	Then Validate job details in Job Details page
	When Click to Return to Job Search
	Then User should be navigated to Carrers page
	Then Close the browser
	
	Examples: 
		|Position Name|Job Title|Job Id|Job Location|Job Description|
		|Design Quality Manager|Design Quality Manager|2410323|Baltimore, Maryland, United States of America |At Labcorp, we believe in the power of science to change lives. We are a leading global life sciences company that delivers answers for crucial health questions|