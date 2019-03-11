package com.Infy;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import com.Infy.*;
import com.Infy.ReadConfig;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Path("/KASearch")
public class SearchService {
	
	@GET
	@Path("/question}")
	@Produces(MediaType.APPLICATION_XML)
	public String getSearchResponse(@QueryParam("qs") String query) throws JSONException, ParseException, IOException {
		query=query.replace(" ", "+");

		SearchResult.getGIMLResponse(query);
		
		return "success";
	}
	
	@GET
	@Path("/jsonresponse")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Results> getJsonResponse(@QueryParam("input_text") String query) throws JSONException, ParseException, IOException
	{
		ReadConfig properties = new ReadConfig();
		query=query.replace(" ", "+");
		
		String rest_url = properties.getPropValues("REST_URL"); 
		try {
			System.out.println("Started!!");
			HttpResponse<String> response = Unirest.get(rest_url+query).header("cache-control", "no-cache").asString();
			System.out.println("Response "+response.getCode());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileName ="C:\\Service_Cloud\\Results.xml";
		ArrayList<Results> list = new ArrayList<Results>();
		list = (ArrayList<Results>) SearchResult.parseResultsXML(fileName);
		
		return list;
		
	}

}
