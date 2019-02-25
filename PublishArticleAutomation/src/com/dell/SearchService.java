package com.dell;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/SearchService") 
public class SearchService {
	
	@GET
	@Path("/question/{query}")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Results> getSearchResponse(@PathParam("query") String query) throws JSONException, ParseException, IOException {
		query=query.replace(" ", "+");
	    return SearchResult.getGIMLResponse(query);
	     
		
	}

}
