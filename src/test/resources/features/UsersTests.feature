Feature: Validating Users Data
Scenario: Validating user creation by providing one user

Given Create users requestBody
|id|username|firstName|lastName|email|password|phone|userStatus|
|01|TestUser|Test First|Test Second|test1@gmail.com|Testing@1|789394|0|
When Post the request and get the response
Then Status code should be 200
And Validate user details
|id|username|firstName|lastName|email|password|phone|userStatus|
|1|TestUser|Test First|Test Second|test1@gmail.com|Testing@1|789394|0|


Scenario: Validating user creation by providing multiple users

Given Create users requestBody
|id|username|firstName|lastName|email|password|phone|userStatus|
|01|TestUser|Test First|Test Second|test1@gmail.com|Testing@1|789394|0|
|02|TestUser2|Test First2|Test Second2|test2@gmail.com|Testing@1|789395|1|
When Post the request and get the response
Then Status code should be 200
And Validate user details
|id|username|firstName|lastName|email|password|phone|userStatus|
|1|TestUser|Test First|Test Second|test1@gmail.com|Testing@1|789394|0|
|2|TestUser2|Test First2|Test Second2|test2@gmail.com|Testing@1|789395|1|
