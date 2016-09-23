# Test the REST

Simple framework to test REST api's based on 

Rest Assured framework  - http://rest-assured.io/
Hamcrest - http://hamcrest.org/JavaHamcrest/
mustache - https://mustache.github.io/

Preferred framework for testing is TestNG but will work with JUnit also

I will use the example to test the 
simple rest service in https://github.com/arun2dot0/simple-rest-service

Why use this .. 

1. Set headers , base url and common expected reponse one time and reuse 
2. Setup templates to drive payloads and populate them using Java model 
3. Extend for different environments , add more test to the base test seamless

How to execute

1. Run the Rest API that you have to test.. example uses simple rest service
2. execute " mvn clean test "
