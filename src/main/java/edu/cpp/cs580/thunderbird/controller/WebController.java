package edu.cpp.cs580.thunderbird.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cpp.cs580.thunderbird.data.UserRepository;
import edu.cpp.cs580.thunderbird.data.provider.UserManager;
import edu.cpp.cs580.thunderbird.tools.GetCppClasses;
import edu.cpp.cs580.thunderbird.tools.GoogleOAuth;

@RestController
public class WebController {
	
	@Autowired
	private UserManager userManager;
	
    GoogleOAuth OAuth; // May come up with better method

    /**
     * This is Assignent #3
     * Done by Nanwarin
     */
    @RequestMapping(value = "/Nanwarin")
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

    /**
     * Assignment 3 - Diana
     */
    @RequestMapping(value = "/Diana", method = RequestMethod.GET)
    String helloDiana() {
        return "Hello it's me, Diana";
    }

    /**
     * After user login with Google Account
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String getHome(@RequestParam("code") String code) throws IOException{
        userManager.updateUser(OAuth.getUserInfoJson(code));
        return "Welcome " + userManager.getUserName();
    }

    /**
     * Require User to authorize account with Google before they can use calendar
     * Will come up with better method later
     * @throws URISyntaxException
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> loginGoogle() throws URISyntaxException
    {
        OAuth = new GoogleOAuth();
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
