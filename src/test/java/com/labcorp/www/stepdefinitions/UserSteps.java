package com.labcorp.www.stepdefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import pojos.User;

public class UserSteps {
	String requestBody;
	Response response;
	@Given("Create users requestBody")
	public void create_users_request_body(DataTable dataTable) throws JsonProcessingException {
		List<User> users=new ArrayList<>();
		for(Map<String,String> details:dataTable.asMaps()) {
			User user=new User();
			user.setId(Integer.valueOf(details.get("id")));
			user.setUserStatus(Integer.valueOf(details.get("userStatus")));
			user.setFirstName(details.get("firstName"));
			user.setLastName(details.get("lastName"));
			user.setUsername(details.get("username"));
			user.setPassword(details.get("password"));
			user.setPhone(details.get("phone"));
			user.setEmail(details.get("email"));
			users.add(user);
		}
		ObjectMapper objMapper=new ObjectMapper();
		requestBody=objMapper.writeValueAsString(users);	
		System.out.println(requestBody);
	}

	@When("Post the request and get the response")
	public void post_the_request_and_get_the_response() {
		Map<String,String> headers=new HashMap<>();
		headers.put("Content-Type", "application/json");
	    response=RestAssured.given().body(requestBody).headers(headers).post("https://petstore.swagger.io/v2/user/createWithArray");
	    System.out.println(response.getBody().asString());
	}
	
	@Then("Status code should be {int}")
	public void status_code_should_be(int expectedStatusCode) {
	    Assert.assertEquals(expectedStatusCode, response.getStatusCode());
	}


	@When("Get the users details")
	public void get_the_users_details() {
		 response=RestAssured.given().body(requestBody).get("https://petstore.swagger.io/v2/user/");
	}

	@Then("Validate user details")
	public void validate_user_details(io.cucumber.datatable.DataTable dataTable) {
		for(Map<String,String> details:dataTable.asMaps()) {
			String username=details.get("username");
			response=RestAssured.given().body(requestBody).get("https://petstore.swagger.io/v2/user/"+username);
			Assert.assertEquals(200, response.getStatusCode());
			 System.out.println(response.getBody().asString());
			 JSONObject jsonObject=new JSONObject(response.getBody().asString());
			 Assert.assertEquals("Incorrect Id",details.get("id"), String.valueOf(jsonObject.getInt("id")));
			 Assert.assertEquals("Incorrect username",details.get("username"), jsonObject.get("username"));
			 Assert.assertEquals("Incorrect firstName",details.get("firstName"), jsonObject.get("firstName"));
			 Assert.assertEquals("Incorrect lastName",details.get("lastName"), jsonObject.get("lastName"));
			 Assert.assertEquals("Incorrect email",details.get("email"), jsonObject.get("email"));
			 Assert.assertEquals("Incorrect password",details.get("password"), jsonObject.get("password"));
			 Assert.assertEquals("Incorrect phone",details.get("phone"), jsonObject.get("phone"));
			 Assert.assertEquals("Incorrect userStatus",details.get("userStatus"), String.valueOf(jsonObject.getInt("userStatus")));
		}
	}

}
