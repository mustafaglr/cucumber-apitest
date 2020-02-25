@tag 
Feature: Title of your feature



  Scenario: ApiTest
    Given I set headers
    	| Accept | application/json |
    	| Content-Type | application/json| 
    And I set body "src/test/java/cucumber/apitest/body.json"
    And I call uri "http://sot.cloudmbdev.vodafone.local/customer/verification" with type "POST"
    Then I get status
    
  Scenario: ApiTest-2
    Given I set headers
    	| Accept | application/json |
    	| Content-Type | application/json|
    	| Authorization | Bearer 5c438bff-4bb7-464d-95b7-433a4b7ec919 | 
    And I set parameters
    	| reqid | VODAFONE-2020-0061358016 |
    	| userName | gizemturkmen |
    	| shopCode | S90005 | 
    And I call uri "http://sot.cloudmbdev.vodafone.local/mnp-tracking/portin" with type "GET"
    Then I get status
      

    

    