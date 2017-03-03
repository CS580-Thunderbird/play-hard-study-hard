package edu.cpp.cs580.thunderbird.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;

import edu.cpp.cs580.thunderbird.EventManagers;
import edu.cpp.cs580.thunderbird.data.EventOrganizer;
import edu.cpp.cs580.thunderbird.data.UserOrganizerSettingList;
import edu.cpp.cs580.thunderbird.data.UserSetting;
import edu.cpp.cs580.thunderbird.data.provider.CppManager;
import edu.cpp.cs580.thunderbird.data.provider.EventOrganizerManager;
import edu.cpp.cs580.thunderbird.data.provider.UserManager;
import edu.cpp.cs580.thunderbird.data.provider.UserSettingManager;
import edu.cpp.cs580.thunderbird.tools.GetCppClasses;
import edu.cpp.cs580.thunderbird.tools.GoogleOAuth;

@RestController
public class WebController {
	
	@Autowired private UserManager userManager;
	@Autowired CppManager cppManager;
	@Autowired EventOrganizerManager orgManager;
	@Autowired EventManagers eventManager;
	@Autowired UserSettingManager userSettingManager;
	//@Autowired UserPreferenceSettingManager userPreference;

    GoogleOAuth OAuth; // May come up with better method

    /**
     *  Nanwarin Testing
     */
    @RequestMapping(value = "/Nanwarin")
    String helloNan(){
    	//userManager.getJSONUser();
    	String result = "";
    	try {
    		result = orgManager.getJSonListOfOrganizer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;   
    }
    
    @RequestMapping(value = "/Nan")
    String nanTest() throws IOException{
    	//GetInternationalStudentEvents intEvent = new GetInternationalStudentEvents();
    	//intEvent.parseEvents();
    	eventManager.internationalStudentEvent.listEvents();
    	return "Complete";
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
     * Assignment 4 - Wie Hsing Li
     * 
     * Printed user email from json node using the Jackson dependency
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Wiehsing-A4", method = RequestMethod.GET)
    String pullUsers(){
    	
    	return userManager.readAllUsers();
    }	
    

    /**
     * Assignment 3 - Diana
     * Basic static calendar
     */
    @RequestMapping(value = "/Diana-A3", method = RequestMethod.GET)
    ModelAndView DianaAssignment3() {
        ModelAndView modelAndView = new ModelAndView("BasicCalendar.html");
        return modelAndView;
    }
    
    /**
     * Assignment 4 - Diana
     * Display International Student Club Events from intStdEvents.csv
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/Diana-A4", method = RequestMethod.GET)
    String DianaAssignment4() throws IOException {
        File file = new File("src/main/resources/static/data/intStdEvents.csv");
        List lines = FileUtils.readLines(file, "UTF-8");
        String eventList = "";
        for (Object event : lines) {
            eventList += event + "<br/>";
        }
        return eventList;
    }

    /*
     * Assignment #3
     * Author: Kushal Patel
     */
    @RequestMapping(value = "/Kushal", method = RequestMethod.GET)
    String KushalAssignment3() {
    	return "CS580 Assignment #3";
    }
    
    /*
     * Assignment #4
     * Author: Kushal Patel
     * Summery: Prints out information of all of the images on the given URL. (CS580 Teams Page)
     */
    @RequestMapping(value = "/Kushal-A4", method = RequestMethod.GET)
    void KushalAssignment4() throws IOException {
    	Document doc;
    	
        //get all images
        doc = Jsoup.connect("http://cs580.yusun.io/teams-winter2017/").get();
        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        for (Element image : images) {
            System.out.println("\nsrc : " + image.attr("src"));
            System.out.println("height : " + image.attr("height"));
            System.out.println("width : " + image.attr("width"));
            System.out.println("alt : " + image.attr("alt"));
        }
    }
    
    @RequestMapping(value = "/setting/add_class", method = RequestMethod.GET)
    void addClassToUserSetting(@RequestParam("code") String code){
    	System.out.println("Class code " + code);
    }
    
    @RequestMapping(value = "/setting/delete_class", method = RequestMethod.DELETE)
    void deleteClassToUserSetting(@RequestParam("classNbr") String code){
    	System.out.println("Class code " + code);
    }
    
    
    @RequestMapping(value = "/setting/add_org", method = RequestMethod.GET)
    void addOrgPreference(@RequestParam("id") String id){
    	System.out.println("Adding org preference id : " + id);
    }
    
    /**
     * After user login with Google Account
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    ModelAndView getHome(@RequestParam("code") String code) throws IOException{	
    	userManager.updateUser(OAuth.getUserInfoJson(code));
    	if(userSettingManager.isNewUser(userManager.getUserId())){
    		UserSetting user = new UserSetting();
    		user.setUserID(userManager.getUserId());
    		userSettingManager.saveNewUser(user);
    		System.out.println("Save new user into database id:" + user.getUserID());
    	}
    	//ModelAndView modelAndView = new ModelAndView("index.html");
    	ModelAndView modelAndView = new ModelAndView("redirect:/calendar");
        return modelAndView;

    }
    
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    ModelAndView getMain(){
    	ModelAndView modelAndView = new ModelAndView("index.html");
    	return modelAndView;
    }
    

    /**
     * Sign out
     */
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    String logOut() throws IOException{	
    	userManager.logOut();
        return "Bye Bye";
        
    }
    
    /**
     * Assignment#4 by Nanwarin, 
     * imported OAuth2 Library for logging in with Google Account
     * Require User to authorize account with Google before they can use calendar
     * Will come up with better method later
     * @throws URISyntaxException
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView welcomePage(){
    	ModelAndView modelAndView = new ModelAndView("pre-page.html");
		return modelAndView;
    }
    
    @RequestMapping(value = "/AutorizeWithGoogle", method = RequestMethod.GET)
    public ResponseEntity<Object> loginGoogle() throws URISyntaxException
    {
        OAuth = new GoogleOAuth();
        String url = OAuth.buildLoginUrl();
        URI loginURI = new URI(url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(loginURI);

        return new ResponseEntity<Object>(httpHeaders, HttpStatus.SEE_OTHER);
    }
    /**
     * User has too autorize google account, it not u will not get any info
     */
    @RequestMapping(value = "data/user", method = RequestMethod.GET)
    public String getUserJson(){
    	if(userManager.getJSONUser() == null) 
    		return "Please authorize user first http://localhost:8080/"; // Temporary usage
    	else 
    		return userManager.getJSONUser();
    }
    
    @RequestMapping(value = "data/organizers", method = RequestMethod.GET)
    public String getListOrganizer() throws FileNotFoundException, IOException{
    	//Temporary Usages before set DB and AWS
    	return orgManager.getHardCodeOrgList();
    	//return orgManager.getJSonListOfOrganizer();
    }
    
      @RequestMapping(value = "/data/events", method = RequestMethod.POST, consumes = "application/json")
      //@RequestMapping(value = "/data/events", method = RequestMethod.GET)
      @ResponseBody
      String getEventByOrgs(@RequestBody UserOrganizerSettingList preferSet) throws FileNotFoundException{
      //String getEventByOrgs() throws FileNotFoundException{
     //boolean addPreferenceOrganizer(@RequestBody List<String> lists){
     	
    	String dummy_event = new Scanner(new File("src/main/resources/static/data/sampleEvents.json")).useDelimiter("\\Z").next();

    	//"src/main/resources/static/data/sampleEvents.json"
    	
     	return dummy_event;
     }
    
    @RequestMapping(value = "data/classes", method = RequestMethod.GET)
    public String getListClasses() throws FileNotFoundException, IOException{
    	//Temporary Usages before set DB and AWS
    	return "[" + cppManager.listJSonClasses() + "]";
    	//return orgManager.getJSonListOfOrganizer();
    }
    
    @RequestMapping(value = "data/eventOrgList", method = RequestMethod.GET)
    public String getOrgPreferenceSetting() throws IOException{
    	return userSettingManager.getJsonOfPreferenceSetting(userManager.getUserId());
    }
    
    //Temporary Usage, Just want to try it out
    @RequestMapping("/getCppSchedule")
    String getCppClassesSchedule() throws Exception{
        try {
            GetCppClasses cppClsss = new GetCppClasses(cppManager);
            cppManager.listJSonClasses();
        } catch (IOException e) {
            System.out.println("Error -- getCppClsses"+ e);
        }

        return "complete";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    ModelAndView getAdminPage(){
    	ModelAndView modelAndView;
	
    	try{
    	if(userManager.getUserEmail().compareTo("nan2iz.ch@gmail.com") == 0)
    		modelAndView = new ModelAndView("adminPage.html");
    	else 
    		modelAndView = new ModelAndView("page_not_found.html");
    	}catch (NullPointerException e){
    		System.out.println("null");
    		modelAndView = new ModelAndView("page_not_found.html");
    	}
		return modelAndView;
    }
    
    @RequestMapping(value = "/classlist", method = RequestMethod.GET)
    ModelAndView getClasslist(){
    	ModelAndView modelAndView = new ModelAndView("classList.html");
    	return modelAndView;
    }
    
    
    @RequestMapping(value = "/admin/organizer/{orgID}", method = RequestMethod.POST)
    EventOrganizer addNewOrganizer(
    		@PathVariable("orgID") String id,
			@RequestParam("name") String name,
			@RequestParam("link") String link){
    	EventOrganizer organizer = new EventOrganizer(id,name,link);
    	System.out.println("Adding Organization: " + name);
    	orgManager.addNewEventOrganizer(organizer);
    	System.out.println("Done");
    	orgManager.listAllOrganizer();
    	System.out.println(new Date(System.currentTimeMillis()).toString());
    	return organizer;
    }
    
    @RequestMapping(value = "/setting/add_org", method = RequestMethod.POST, consumes = "application/json")
   // @RequestMapping(value = "/setting/add_org", method = RequestMethod.POST)
    @ResponseBody
    boolean addPreferenceOrganizer(@RequestBody UserOrganizerSettingList preferSet){
    //boolean addPreferenceOrganizer(@RequestBody List<String> lists){
    	userSettingManager.setEventPreference(preferSet.preferSet, userManager.getUserId());
    	return true;
    }
    
    @RequestMapping(value = "admin/organizer/list", method = RequestMethod.GET)
    List<EventOrganizer> listAllOrganizers(){
    	System.out.println("Listing All users" + new Date(System.currentTimeMillis()).toString());
    	
  
    	return orgManager.listAllOrganizer();
    }
    
}
