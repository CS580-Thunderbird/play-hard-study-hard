package edu.cpp.cs580.thunderbird.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.cpp.cs580.thunderbird.data.EventObject;
import edu.cpp.cs580.thunderbird.data.InternationalStudentEvent;

/**
 * Pull events from international student website
 * @author nchantarutai
 *
 */
public class GetInternationalStudentEvents {

	ArrayList<InternationalStudentEvent> resultEvent;
	
	public void parseEvents() throws IOException{
		resultEvent = new ArrayList<InternationalStudentEvent>();
		Document result = null;
		String webURL = "https://www.cpp.edu/~international/events/";
		//String csvFileName = "src/main/resources/static/data/intStdEvents.csv";
		//File csvFile = new File(csvFileName);
		
		//if(!csvFile.exists()) csvFile.createNewFile();
		
		//FileWriter writer = new FileWriter(csvFile);
		
		result = Jsoup.connect(webURL).get();
		
		Elements events;
		
		events = result.select("ul.events");
		
		//Need to fix data pattern
		for(Element event: events){
			InternationalStudentEvent intObj = new InternationalStudentEvent();		
			Element details = event.select("div.media-body").get(0);
			intObj.setTitle(details.getElementsByTag("a").text());
			intObj.setLink(details.getElementsByTag("a").attr("abs:href"));
			
			//////Time///////
			String rawTime = details.getElementsByTag("span").text();
			String tt[] = rawTime.split(" to ");
			
			for(String t: tt){
				
				System.out.println(t);
			}
			
			//////////////////
			
			
			intObj.setLocation(details.getElementsByTag("p").text());
			//System.out.println(details.getElementsByTag("a").attr("abs:href"));
			//System.out.println(details.getElementsByTag("span").text());
			//System.out.println(details.getElementsByTag("p").text());

		}
		
		
		//writer.flush();
		//writer.close();
		System.out.println("Completed");
	
	}

}
