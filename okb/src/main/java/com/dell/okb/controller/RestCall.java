package com.dell.okb.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

	public void updateCat(Request req, String instanceNo) throws UnirestException {
		// TODO Auto-generated method stub
		ReadConfig properties = new ReadConfig();
		
		try {
				String end_point = properties.getPropValues("REST_API_ENDPOINT");
				
				String body = "docid="+req.getDocid()+"&locale="+req.getLocaleid()+"&remCatRef="+req.getRemRefCat()+"&newCatRefKey="+req.getNewRefCat()+"&instanceNo="+instanceNo;
				HttpResponse<String> response = Unirest.post(end_point).header("Content-Type", "application/x-www-form-urlencoded").header("cache-control", "no-cache").body(body).asString();
				
				RestStatus restStatusObject = convertToClass(response.getBody().toString());
				
				logger.info(restStatusObject.getListValue()+ "\t"+restStatusObject.getResult());
			}
		catch(IOException e)
		{
			logger.error("Unable to Read Property File");
		}
	}
	private RestStatus convertToClass(String xmlString)
	{
		JAXBContext jaxbContext;
		
		RestStatus status = new RestStatus();
		try
		{
		    jaxbContext = JAXBContext.newInstance(RestStatus.class);             
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    status = (RestStatus) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		     
		}
		catch (JAXBException e)
		{
		    e.printStackTrace();
		}
		return status;
		 
	}

}
