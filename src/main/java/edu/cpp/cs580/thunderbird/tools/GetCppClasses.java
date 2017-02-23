package edu.cpp.cs580.thunderbird.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import edu.cpp.cs580.thunderbird.data.CppClassSchedule;
import edu.cpp.cs580.thunderbird.data.TimeObj;
import edu.cpp.cs580.thunderbird.data.provider.CppManager;
import edu.cpp.cs580.thunderbird.utils.ResourceResolver;


/**
 * A class to pull classes from http://schedule.cpp.edu/
 * @author nchantarutai
 *
 */
public class GetCppClasses {
		
		//private CppClassManager classManager;
		public CppManager cppManager;
		
		public GetCppClasses(CppManager cppManager) throws Exception{	
			this.cppManager = cppManager; //Autowired not work, need to recheck
			cppManager.deleteAllClasses();
			parseClasses();
		}
		
		public void parseClasses() throws Exception{
			File codeCsv = new File("src/main/resources/static/data/classCodes.csv");
			BufferedReader br = new BufferedReader(new FileReader(codeCsv));
			String input = br.readLine();
			String[] codes = input.split(",");
		
			
			for(String code:codes){
				Document result;
				result = parseClass(code);
				insertClassScheduleToDB(result);
			}
			
			//Temporary Testing
			//Document result = parseClass("CS");
			//insertClassScheduleToDB(result);
		}
		
		
		public static Document parseClass(String code) throws IOException{
			Document result = null;
			String cppClassURL = "http://schedule.cpp.edu/";
			Connection.Response cppClass = Jsoup.connect(cppClassURL).method(Connection.Method.GET).execute();
			
			Document cppClassDoc = cppClass.parse();
			
			//Hidden Inputs
	        Element viewState = cppClassDoc.select("input[name=__VIEWSTATE]").first();
	        Element eventTarget = cppClassDoc.select("input[name=__EVENTTARGET]").first();
	        Element eventArgument = cppClassDoc.select("input[name=__EVENTARGUMENT]").first();
	        
	        Element viewStateGenerator = cppClassDoc.select("input[name=__VIEWSTATEGENERATOR]").first();
	        Element viewStateEncrypted = cppClassDoc.select("input[name=__VIEWSTATEENCRYPTED]").first();
	        Element eventValidation = cppClassDoc.select("input[name=__EVENTVALIDATION]").first();
	     
			
			result = Jsoup.connect(cppClassURL + "index.aspx")
								.data("cookieexists", "false")
								.data("__VIEWSTATE", viewState.attr("value"))
								.data("__EVENTTARGET", eventTarget.attr("value"))
								.data("__EVENTARGUMENT", eventArgument.attr("value"))
								.data("__VIEWSTATEGENERATOR", viewStateGenerator.attr("value"))
								.data("__VIEWSTATEENCRYPTED", viewStateEncrypted.attr("value"))
								.data("__EVENTVALIDATION", eventValidation.attr("value"))
								.data("ctl00$ContentPlaceHolder1$TermDDL", "2171") //Term
								.data("ctl00$ContentPlaceHolder1$ClassSubject", code) // Class Subject
								.data("ctl00$ContentPlaceHolder1$CatalogNumber", "") // Catalog Number
								.data("ctl00$ContentPlaceHolder1$CatalogNumberCHK", "") // Checkbox --> need to review this again
								.data("ctl00$ContentPlaceHolder1$Description", "") //Title
								.data("ctl00$ContentPlaceHolder1$CourseComponentDDL", "Any Component") // Course Component
								.data("ctl00$ContentPlaceHolder1$CourseAttributeDDL", "Any Attribute") // Course Attribute
								.data("ctl00$ContentPlaceHolder1$CourseCareerDDL", "Any Career") //Course Career
								.data("ctl00$ContentPlaceHolder1$InstModesDDL", "Any Mode") // Instruction Mode
								.data("ctl00$ContentPlaceHolder1$SessionDDL", "Any Session") //Session
								.data("ctl00$ContentPlaceHolder1$ClassDays$0", "checked") //Mon Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$1", "checked") //Tue Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$2", "checked") //Wed Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$3", "checked") //Thur Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$4", "checked") //Fri Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$5", "checked") //Sat Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$6", "checked") //Sun Class
								.data("ctl00$ContentPlaceHolder1$ClassDays$7", "checked") //TBD Class
								.data("ctl00$ContentPlaceHolder1$StartTime", "ANY") // Class strat time
								.data("ctl00$ContentPlaceHolder1$EndTime", "ANY") // Class end time		
								.data("ctl00$ContentPlaceHolder1$Instructor", "") // Instructor
								.data("ctl00$ContentPlaceHolder1$InstructorCHK", "") // Match Instructor
								.data("ctl00$ContentPlaceHolder1$StaffCHK", "") //Staff only
								.data("ctl00$ContentPlaceHolder1$SearchButton", "Search") //Search button
								.cookies(cppClass.cookies())
								.followRedirects(true)
								.post();
		
			//System.out.println("Log -- document: " + document.toString());
			return result;
		}
		
		public void insertClassScheduleToDB(Document doc) throws Exception{
			
			Elements classes = doc.select("span.ClassTitle"); //<span class="ClassTitle" >
			Elements tables = doc.select("table");

			
			int index = 2; // Need to find better way
			for(Element className:classes){
				TimeObj timeObj = new TimeObj();
				
				String classCode = "", description = "", time ="", location = "", datePeriod = "", instructor = "", building = "", room = "", classNbr = "";
				
				Element table = tables.get(index);
				Elements rows = table.select("td");
			
				//System.out.println("ROWS: " + rows);
				
				classCode = className.text();		
				classNbr = rows.get(0).text();
				description = rows.get(2).text();
				
				time = rows.get(4).text();
				location = rows.get(5).text();
				
				//2017-01-03 to 2017-03-10 time 1:00 PM–1:50 PM   MWF // Format
				datePeriod = rows.get(6).text();
				//System.out.println("Date Period: " + datePeriod);
				String startDates[] = datePeriod.substring(0, 10).split("-");
				LocalDate startDate = LocalDate.of(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2]));
				String endDates[] = datePeriod.substring(14, datePeriod.length()).split("-");
				LocalDate endDate = LocalDate.of(Integer.parseInt(endDates[0]), Integer.parseInt(endDates[1]), Integer.parseInt(endDates[2]));
		
				timeObj.setStartDate(startDate);
				timeObj.setEndDate(startDate);
				
				//Time startTime = new Time();
				//Will come with better ideas later
				//Too much hard code
				//System.out.println("time: " + time);
				if(!time.contains("No time set.")){
					String startTimeString = time.substring(0, time.indexOf('–'));				
					String endTimeString = time.substring(time.indexOf('–')+1,time.indexOf('–') + 9);			
					String repeatDateString = time.substring(time.indexOf('–') + 9);
				
						
					LocalTime startTime = TimeConvertor.getLocalTime(startTimeString);
					timeObj.setStartTime(startTime);
					
					LocalTime endTime = TimeConvertor.getLocalTime(endTimeString);
					timeObj.setStartTime(endTime);
					
					String[] rs = repeatDateString.split("(?=\\p{Upper})");
					setRepeatDay(timeObj, rs);
		
					
				}
				else{
					
					timeObj.setStartTime(null);
					timeObj.setEndTime(null);
				
					
					
				}
			
				instructor = rows.get(8).text();
				
				CppClassSchedule newClass = new CppClassSchedule(classCode, description, timeObj, instructor, location, classNbr);
				cppManager.saveNewClass(newClass);

				index++;
			}
			
		}
		
		public void setRepeatDay(TimeObj timeObj, String[] rs){
			for(String r: rs){
				if(r.equals("M")) timeObj.setRepeatDay("MONDAY");
				else if(r.equals("Tu")) timeObj.setRepeatDay("TUESDAY");
				else if(r.equals("W")) timeObj.setRepeatDay("WEDNESDAY");
				else if(r.equals("Th")) timeObj.setRepeatDay("THURSDAY");
				else if(r.equals("F")) timeObj.setRepeatDay("FRIDAY");		
			}
		}
		

	}
	

