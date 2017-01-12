package edu.cpp.cs580.thunderbird.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cpp.cs580.thunderbird.utils.GetCppClasses;

@RestController
public class WebController {

	
	@RequestMapping("/")
	String home(){
		return "Hello ! This is home";
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
