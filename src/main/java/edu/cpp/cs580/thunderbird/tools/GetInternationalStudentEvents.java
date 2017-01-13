package edu.cpp.cs580.thunderbird.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInternationalStudentEvents {
		
	public GetInternationalStudentEvents(){
		
	}
	
	public static void parseEvents() throws IOException{
		Document result = null;
		String webURL = "https://www.cpp.edu/~international/events/";
		String csvFileName = "src/main/resources/static/data/intStdEvents.csv";
		File csvFile = new File(csvFileName);
		
		if(!csvFile.exists()) csvFile.createNewFile();
		
		FileWriter writer = new FileWriter(csvFile);
		
		result = Jsoup.connect(webURL).get();
		
		Elements events;
		
		events = result.select("ul.events");
		
		//Need to fix data pattern
		for(Element event: events){
			Element details = event.select("div.media-body").get(0);
			writer.append(details.getElementsByTag("a").text());
			writer.append(",");
			writer.append(details.getElementsByTag("span").text());
			writer.append("\n");	
		}
		
		
		writer.flush();
		writer.close();
		System.out.println("Completed");
	
	}

}
