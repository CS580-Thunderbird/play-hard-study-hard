package edu.cpp.cs580.thunderbird;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.cpp.cs580.thunderbird.data.GoogleUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoogleUserTest {

	private GoogleUser googleUser;
	
	@Before
	public void setup(){
		String id = "100019948849615901983";
		String email = "wiehsing@gmail.com";
		String name = "Wie Hsing Li";
		String pictureLink = "https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg";
		
		googleUser = new GoogleUser(id, email, name, pictureLink);
	}
	
	@Test
	public void testgetName(){
		String gName = googleUser.getName();
		assertEquals(gName, "Wie Hsing Li");
	}
	
	@Test
	public void testgetEmail(){
		String gmail = googleUser.getEmail();
		assertEquals(gmail, "wiehsing@gmail.com");
	}
	
	@Test
	public void testgetPicturelink(){
		String gPictureLink = googleUser.getPictureLink();
		assertEquals(gPictureLink, "https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg");
	}
}
