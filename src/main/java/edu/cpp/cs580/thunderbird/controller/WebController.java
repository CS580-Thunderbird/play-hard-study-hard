package edu.cpp.cs580.thunderbird.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cpp.cs580.thunderbird.tools.GetCppClasses;
import edu.cpp.cs580.thunderbird.tools.GoogleOAuth;

@RestController
public class WebController {
	
	
	/**
	 * This is Assignent #3
	 * Done by Nanwarin
	 */
	@RequestMapping("/Nanwarin")
	String helloNan(){
		return "Hello it's me";
	}
	
	/**
	 * Assignment 3 part 3
	 * @return
	 */
	@RequestMapping(value = "/Wiehsing", method = RequestMethod.GET)
	String wiehsingtest(){
		
		return "Wie Hsing Li testing";
	}
	

	@RequestMapping("/login")
	String getHome(){
		return "Welcome !!! !!";
	}
	
	@RequestMapping("/")
	public ResponseEntity<Object> loginGoogle() throws URISyntaxException
	{
		GoogleOAuth OAuth = new GoogleOAuth();
		String url = OAuth.buildLoginUrl();
		URI loginURI = new URI(url);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(loginURI);
		
		return new ResponseEntity<Object>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	//Temporary Usage, Just want to try it out
	@RequestMapping("/getCppClasses")
	String getCppClasses(){
		try {
			GetCppClasses cppClsss = new GetCppClasses();
		} catch (IOException e) {
			System.out.println("Error -- getCppClsses");
		}
		
		return "complete";
	}
	
}
