package edu.cpp.cs580.thunderbird.tools;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

/**
 * 
 * @author nchantarutai
 * Get demo from Matyas Danter, https://github.com/mdanter/OAuth2v1
 * 
 * More info 
 * Another way to use oauth2 with spring boot
 * http://dba-presents.com/index.php/jvm/java/100-getting-started-with-google-sign-in-in-spring-boot-app
 * 
 */

public class GoogleOAuth {

	private static final String CLIENT_ID = "904127605699-qip5rbp5adqpe0bnh7o92sl1otua4e62.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "LpWQA9RuOIBQKoO8KbRYpEP8";

	//Redirect URI, after login sucessfully
	//Local
	private static final String REDIRECT_URI = "http://[::1]:8080/home";
	
	//AWS
	//private static final String REDIRECT_URI = "ec2-52-36-235-139.us-west-2.compute.amazonaws.com:8080/home";
	
	// start google authentication constants
	private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
	private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	// end google authentication constants
	
	private final GoogleAuthorizationCodeFlow flow;
	private String stateToken;
	
	/**
	 * Constructor initializes the Google Authorization Code Flow
	 */
	public GoogleOAuth(){
		flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPES).build();
		generateStateToken();
	}
	
	/**
	 * Generates a secure state token
	 */
	private void generateStateToken(){
		SecureRandom secureRand = new SecureRandom();
		stateToken = "google;" + secureRand.nextInt();
	}
	
	public String getStateToken(){
		return stateToken;
	}
	
	/**
	 * Login URL
	 */
	public String buildLoginUrl(){
		final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
		return url.setRedirectUri(REDIRECT_URI).setState(stateToken).build();
	}
	
	/**
	 * Expects an Authentication Code, and makes an authenticated request for the user's profile information
	 * @return JSON formatted user profile information
	 * @param authCode authentication code provided by google
	 */
	public String getUserInfoJson(final String authCode) throws IOException {

		final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(REDIRECT_URI).execute();
		final Credential credential = flow.createAndStoreCredential(response, null);
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
		// Make an authenticated request
		final GenericUrl url = new GenericUrl(USER_INFO_URL);
		final HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		final String jsonIdentity = request.execute().parseAsString();

		return jsonIdentity;

	}
	
}
