@tag 
Feature: Title of your feature



  Scenario: ApiTest
    Given I set headers
    	| Accept | application/json |
    	| Content-Type | application/json| 
    And I set body "BODY.JSON PATH"
    And I call uri "URL" with type "POST"
    Then I get status
    
  Scenario: ApiTest-2
    Given I set headers
    	| Accept | application/json |
    	| Content-Type | application/json|
    	| Authorization | Bearer ****** | 
    And I set parameters
    	| ****** | ****** |
    	| ****** | ****** |
    	| ****** | ****** | 
    And I call uri "URL" with type "GET"
    Then I get status
      

    

    
