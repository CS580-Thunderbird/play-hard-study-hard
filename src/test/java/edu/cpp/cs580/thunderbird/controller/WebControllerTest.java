package edu.cpp.cs580.thunderbird.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.cpp.cs580.thunderbird.tools.GoogleOAuth;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/***
 * Assignment#6 by Nanwarin Chantarutai
 * @author nchantarutai
 *
 */
public class WebControllerTest {
	
	GoogleOAuth mockedOAuth;
	
	@Before
	public void setUp(){
		mockedOAuth = mock(GoogleOAuth.class);
	}
	
	@Test
	public void loginGoogleTest() throws URISyntaxException{
		System.out.println("Perform login with Google Account Test");
		mockedOAuth = new GoogleOAuth();
        String mockedUrl = mockedOAuth.buildLoginUrl();
        URI mockddloginURI = new URI(mockedUrl);

        HttpHeaders mockedhttpHeaders = new HttpHeaders();
        mockedhttpHeaders.setLocation(mockddloginURI);
        
        assertNotNull(mockedhttpHeaders.getLocation());
       
       
	}
	
}
