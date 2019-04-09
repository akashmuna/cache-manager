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
import com.dell.okb.model.RestStatusList;
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
			logger.info("Doc ID: "+req.getDocid()+" Locale: "+ req.getLocaleid());
			try {
				updateCat(req,instanceNo);
			} catch (UnirestException e) {
				logger.info("ERROR while hitting the Rest!! "+req.getDocid()+ " "+req.getLocaleid());
			}
		}
		
	}
	
	public void removeCat(String categoryList,String instanceNo) {
		// TODO Auto-generated method stub
		ReadConfig properties = new ReadConfig();
		
		try {
			String end_point = properties.getPropValues("REST_DEL_API_ENDPOINT");
			
			String body = "categoryList="+categoryList+"&instanceNo="+instanceNo;
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(end_point).header("Content-Type", "application/x-www-form-urlencoded").header("cache-control", "no-cache").body(body).asString();
			
			RestStatusList restStatuslistObject = convertToClassforList(response.getBody().toString());
			
			ArrayList<RestStatus> categoryDelResults = new ArrayList<>();
			categoryDelResults = restStatuslistObject.getRestStatusList();
			
			categoryDelResults.forEach(item->{
				logger.info(item.getListValue()+ "\t"+item.getResult());
			});
			
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
			logger.info("Error Hitting the WebService!!");
		}
		catch(IOException e)
		{
			logger.error("Unable to Read Property File");
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
	
	private RestStatusList convertToClassforList(String xmlString)
	{
		JAXBContext jaxbContext;
		
		RestStatusList status = new RestStatusList();
		try
		{
		    jaxbContext = JAXBContext.newInstance(RestStatusList.class);             
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    status = (RestStatusList) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		     
		}
		catch (JAXBException e)
		{
		    e.printStackTrace();
		}
		return status;
		 
	}

}
