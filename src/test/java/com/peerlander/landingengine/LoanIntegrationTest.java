package com.peerlander.landingengine;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.peerlander.landingengine.application.model.LoanApplicationDTO;
import com.peerlander.landingengine.application.model.LoanRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles(profiles = "test")
public class LoanIntegrationTest {
	
private static final String KARIM="jilani";
private static final Gson GSON= new Gson();

@Autowired
private TestRestTemplate restTemplate;
@LocalServerPort
private int serverPort;

@Test
public void givenLoanRequestIsMadeLoanApplicationGetsCreated() throws Exception{
	final String baseUrl="http://localhost:"+serverPort+"/loan/";
	HttpHeaders httpHeaders = getHttpHeaders();
	HttpEntity<LoanRequest> request=
			new HttpEntity<>(new LoanRequest(50,10,10),httpHeaders);
	
	restTemplate.postForEntity(baseUrl+"request", request, String.class);
	
	ResponseEntity<String> response= restTemplate
			.exchange(baseUrl+"/requests", HttpMethod.GET,
					new HttpEntity(null, getHttpHeaders()),String.class);
	
	List<LoanApplicationDTO> loanApplicationDTOS=
			GSON.fromJson(response.getBody(), new TypeToken<List<LoanApplicationDTO>>(){}.getType());
	assertEquals(1, loanApplicationDTOS.size());
	assertEquals(loanApplicationDTOS.get(0).getBorrower().getUsername(), KARIM);
}

private HttpHeaders getHttpHeaders() {
	HttpHeaders httpHeaders=new HttpHeaders();
	httpHeaders.add(httpHeaders.AUTHORIZATION, KARIM);
	return httpHeaders;
}

}
