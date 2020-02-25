package cucumber.apitest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import okhttp3.RequestBody;

public class StepDefinitions {
	HttpHeaders header;
	RequestBody body;
	RestTemplate restTemplate;
	ResponseEntity<String> response;
	Map<String, String> params = null;
	HttpEntity<String> entity;
	UriComponentsBuilder builder;
	Boolean paramSet = false;
	String jsonStr=null;
	
	
	@Given("^I set headers$")
	public void addHeader(Map<String, String> dataMap) {
		header = new HttpHeaders();
		for (Map.Entry<String, String> item : dataMap.entrySet()) {
			try{ 
				header.set(item.getKey(),item.getValue());
			}catch(Exception e) {
				throw new RuntimeException("Exception",e);
				
			}
	    }
	    
	    
	}
	@And("^I set parameters$")
	public void addParameter(Map<String, String> dataMap) {
		paramSet = true;
		params = new HashMap<String, String>();
		for (Map.Entry<String, String> item : dataMap.entrySet()) {
			try{ 
				params.put(item.getKey(),item.getValue());
			}catch(Exception e) {
				throw new RuntimeException("Exception",e);
				
			}
	    }
	}
	
	public void addAuth() {
		
	}
	@And("^I set body \"(.*)\"$")
	public void addBody(String body) throws IOException {
		try {
		   	jsonStr = new String(Files.readAllBytes(Paths.get(body).toAbsolutePath()), StandardCharsets.UTF_8);
		}catch(Exception e) {
			throw new RuntimeException("Exception",e);
			
		}

	}
	
	@And("^I call uri \"(.*)\" with type \"(.*)\"$")
    public void sendRequest(String remoteAddress,String type){
		//DEFAULT
		
    	 try {
    		 restTemplate = new RestTemplate();
 
    	     if(paramSet) {
            	 builder = UriComponentsBuilder.fromHttpUrl(remoteAddress);
        	   	 for (Map.Entry<String, String> entry : params.entrySet()) {
        	   	     builder.queryParam(entry.getKey(), entry.getValue());
        	   	 }
    	     }

    	   	 HttpMethod httpMethod = null;
    	   	 if(type.contains("GET"))
    	   		httpMethod = HttpMethod.GET;
    	   	 else if(type.contains("POST"))
    	   		httpMethod = HttpMethod.POST; 
    	   	 else if(type.contains("PUT"))
    	   		httpMethod = HttpMethod.PUT; 
    	   	 else if(type.contains("HEAD"))
    	   		httpMethod = HttpMethod.HEAD; 
    	   	 else if(type.contains("DELETE"))
    	   		httpMethod = HttpMethod.DELETE; 
    	   	 else if(type.contains("OPTIONS"))
    	   		httpMethod = HttpMethod.OPTIONS; 
    	   	 else if(type.contains("PATCH"))
    	   		httpMethod = HttpMethod.PATCH; 
    	   	 
    	   	 
             entity = new HttpEntity<String>(jsonStr, header);
            
             if(!paramSet)
                 response = restTemplate.exchange(remoteAddress, httpMethod, entity, String.class);
             else
            	 response = restTemplate.exchange(builder.toUriString(), httpMethod, entity, String.class);
             paramSet = false;
             
    	 }catch(Exception e) {
    		 throw new RuntimeException("Exception",e);
    	 }

    }
	@Then("^I get status$")
    public void requestStatus() {

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received -> " + response.getStatusCode());
            System.out.println(response.getBody());
        } else {
            System.out.println("error occurred");
            System.out.println(response.getStatusCode());
        }
    }
	
}
