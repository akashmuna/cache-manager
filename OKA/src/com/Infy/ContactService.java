package com.Infy;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Path("/contactService")
public class ContactService {

	@GET
	@Path("/retrieve/{contactid}")
	@Produces({MediaType.APPLICATION_JSON})
	public  String retriveContacts(@PathParam("contactid") int contactid) throws JSONException, ParseException, IOException {
		
		HttpResponse<String> response = null;
		try {
			response = Unirest.get("https://citizens--tst1.custhelp.com/services/rest/connect/v1.3/contacts/"+contactid)
					  .header("Authorization", "Basic U2h1YmhyYVM6VGVzdCMxMjM=")
					  .header("cache-control", "no-cache")
					  .asString();
			System.out.println(response.getBody().toString());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getBody();
		
	}
	
	
}
