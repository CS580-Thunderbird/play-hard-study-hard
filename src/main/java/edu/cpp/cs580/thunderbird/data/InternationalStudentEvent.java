package edu.cpp.cs580.thunderbird.data;

public class InternationalStudentEvent implements EventObject {
		String title;
		String link;
		String details;
		TimeObj timeObj;
		String location;
		
		public InternationalStudentEvent(){}
		
		public InternationalStudentEvent(String title, String link,TimeObj timeObj, String location){
			this.title = title;
			this.link = link;
			this.timeObj = timeObj;
			this.location = location;
		}
	
		@Override
		public void setTitle(String title) {
			this.title = title;
			
		}

		@Override
		public void setDetail(String details) {
			this.details = details;
			
		}

		@Override
		public void setTimeObj(TimeObj timeObj) {
			this.timeObj = timeObj;
			
		}

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return title;
		}

		@Override
		public String getDetail() {
			// TODO Auto-generated method stub
			return details;
		}

		@Override
		public TimeObj getTimeObj() {
			// TODO Auto-generated method stub
			return timeObj;
		}

		@Override
		public void setLocation(String location) {
			this.location = location;
			
		}

		@Override
		public String getLocation() {
			// TODO Auto-generated method stub
			return location;
		}

		@Override
		public void setLink(String link) {
			this.link = link;
			
		}

		@Override
		public String getLink() {
			// TODO Auto-generated method stub
			return link;
		}
}
