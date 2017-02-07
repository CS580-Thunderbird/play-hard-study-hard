package edu.cpp.cs580.thunderbird.data;

public class InternationalStudentEvent implements EventObject {
		String title;
		String detail;
		TimeObj timeObj;
		String location;
		
		public InternationalStudentEvent(String title, String detail,TimeObj timeObj, String location){
			this.title = title;
			this.detail = detail;
			this.timeObj = timeObj;
			this.location = location;
		}
	
		@Override
		public void setTitle(String title) {
			this.title = title;
			
		}

		@Override
		public void setDetail(String details) {
			this.detail = details;
			
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
			return detail;
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
}
