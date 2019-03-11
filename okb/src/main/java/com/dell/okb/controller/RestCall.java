package com.dell.okb.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dell.okb.model.Request;
import com.dell.okb.model.RestStatus;
import com.dell.okb.util.ReadConfig;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestCall {

	private static final Logger logger = Logger.getLogger(RestCall.class);
	
	public void removePublish(ArrayList<Request> requestList,String instanceNo) {
		// TODO Auto-generated method stub
		for (Request req : requestList)
		{
			try {
				updateCat(req,instanceNo);
			} catch (UnirestException e) {
				logger.info("ERROR while hitting the Rest!! "+req.getDocid()+ " "+req.getLocaleid());
			}
		}
		
	}

	private void updateCat(Request req, String instanceNo) throws UnirestException {
		// TODO Auto-generated method stub
		ReadConfig properties = new ReadConfig();
		
		try {
				String end_point = properties.getPropValues("REST_API_ENDPOINT");
				String body = "docid="+req.getDocid()+"&locale="+req.getLocaleid()+"&remCatRef="+req.getRemRefCat()+"&newCatRefKey="+req.getNewRefCat()+"&instanceNo="+instanceNo;
				HttpResponse<RestStatus> response = Unirest.post(end_point).header("Content-Type", "application/x-www-form-urlencoded").header("cache-control", "no-cache").body(body).asObject(RestStatus.class);
				
				RestStatus restStatusObject = response.getBody();
				
				logger.info(restStatusObject.getListValue()+ "\t"+restStatusObject.getResult());
			}
		catch(IOException e)
		{
			logger.error("Unable to Read Property File");
		}
	}

}
