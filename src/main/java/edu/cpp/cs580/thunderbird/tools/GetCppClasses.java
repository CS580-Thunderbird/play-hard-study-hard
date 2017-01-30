package edu.cpp.cs580.thunderbird.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.cpp.cs580.thunderbird.data.CppClass;
import edu.cpp.cs580.thunderbird.data.TimeObj;
import edu.cpp.cs580.thunderbird.data.provider.CppManager;

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
			//classManager.deleteAllData();
			parseClasses();
		}
		
		public void parseClasses() throws Exception{
			File codeCsv = new File("src/main/resources/static/data/classCodes.csv");
			BufferedReader br = new BufferedReader(new FileReader(codeCsv));
			String input = br.readLine();
			String[] codes = input.split(",");
			
		/*	for(String code:codes){
				Document result;
				result = parseClass(code);
				insertClassScheduleToDB(result);
				//outputDocument(result);
			}*/
			
			Document result = parseClass("CS");
			insertClassScheduleToDB(result);
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
			
			TimeObj classTime = new TimeObj();		
			
			String classCode = "", description = "", time ="", location = "", datePeriod = "", instructor = "", building = "", room = "";
			
			Elements classes = doc.select("span.ClassTitle"); //<span class="ClassTitle" >
			Elements tables = doc.select("table");
			
			int index = 2; // Need to find better way
			for(Element className:classes){
				Element table = tables.get(index);
				Elements rows = table.select("td");
			
				
				classCode = className.text();
				description = rows.get(2).text();
				time = rows.get(4).text();
				location = rows.get(5).text();
				
				if(!location.isEmpty()){ 
					String input[] = location.split(" ");
					building = input[0];
					room = input[1];
				}
				
				//2017-01-03 to 2017-03-10 time 1:00 PM–1:50 PM   MWF // Format
				datePeriod = rows.get(6).text();
				String startDates[] = datePeriod.substring(0, 10).split("-");
				Date startDate = new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2]));
				String endDates[] = datePeriod.substring(14, datePeriod.length()).split("-");
				Date endDate = new Date(Integer.parseInt(endDates[0]), Integer.parseInt(endDates[1]), Integer.parseInt(endDates[2]));
		
				//Time startTime = new Time();
				
				
			//	System.out.println(datePeriod+ "time " + time);
				instructor = rows.get(8).text();
				
				CppClass newClass = new CppClass(classCode, new TimeObj(), building, room, description, instructor);
				cppManager.saveNewClass(newClass);

				
			}
			
		}
		

	}
	

