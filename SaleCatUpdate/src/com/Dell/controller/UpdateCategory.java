package com.Dell.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Dell.config.ReadConfig;
import com.Dell.model.RestStatus;
import com.inquira.client.serviceclient.IQServiceClient;
import com.inquira.client.serviceclient.IQServiceClientException;
import com.inquira.client.serviceclient.IQServiceClientManager;
import com.inquira.client.serviceclient.request.IQCategoryRequest;
import com.inquira.client.serviceclient.request.IQContentRecordRequest;
import com.inquira.client.serviceclient.request.helper.CategoryRequestHelper;
import com.inquira.im.ito.CategoryITO;
import com.inquira.im.ito.CategoryKeyITO;
import com.inquira.im.ito.ContentRecordITO;
import com.inquira.im.ito.impl.CategoryITOImpl;
import com.inquira.im.ito.impl.CategoryKeyITOImpl;
import com.sun.jersey.multipart.FormDataParam;


/**
 * @author Akash_Mohapatra
 */

@Path("/UpdateCat")
public class UpdateCategory {
	
	@GET 
	@Path("/test/{instanceNo}")
    @Produces(MediaType.APPLICATION_XML)
	public RestStatus test(@PathParam("instanceNo") String instanceNo)
	{
		RestStatus rs = new RestStatus();
		
	    IQServiceClient client = null;
		ReadConfig properties = new ReadConfig();
		String value = "";
		try {
			String user = null;
        	String pwd = null;
        	String imUrl = null;
        	String repo = null;
        	String domain = null;
			try {
				user = properties.getPropValues("IM_USER");
				pwd = properties.getPropValues("IM_PASSWORD");
				imUrl = properties.getPropValues("IM_URL_"+instanceNo);
	    		repo = properties.getPropValues("REPO");
	    		domain = properties.getPropValues("DOMAIN");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				value= "Unable to read Property File";
			}
			System.out.println(imUrl);
			System.out.println(repo);
			client = IQServiceClientManager.connect(user,pwd,domain,repo,imUrl,null, true);
			if (client.isValid())
				value = "Connection successfull!!";
		}
		catch (Exception e) {
			// TODO: handle exception
			value = "Connection Failure";
		}
		
		client.close();
		rs.setResult(value);
		rs.setListValue("IM Web Service");
		return rs;
	}
	/**
	 * 
	 * <p> This Method Removes the Old Taxonomy and Adds the new Taxonomy in one Shot</P>
	 * 
	 * @param docid : DOCUMENTID to be used to update
	 * @param locale : Locale of the DOCUMENTID to be used
	 * @param remCatRef : ReferenceKey for Old Taxonomy tree
	 * @param newCatRefKey : ReferenceKey for New Integrated Taxonomy to be replace
	 * @param instanceNo : instance Value for IMWS URL 
	 * @return A RestStatus Object with a success/Failure message 
	 */
	
	@Path("/removePublish")
	@POST 
    @Produces(MediaType.APPLICATION_XML)
	public RestStatus removeAndPublish(@FormParam("docid") String docid,@FormParam("locale") String locale,@FormParam("remCatRef") String remCatRef,@FormParam("newCatRefKey") String newCatRefKey,@FormParam("instanceNo") String instanceNo)
	{
		
		ArrayList<String> remCatRefList = new ArrayList<>(Arrays.asList(remCatRef.split(",")));
		ArrayList<String> newCatRefKeyList = new ArrayList<>(Arrays.asList(newCatRefKey.split(",")));
		
		RestStatus ps = new RestStatus();
	    IQServiceClient client = null;
		ReadConfig properties = new ReadConfig();
		String value = "failure";
		
        try {
        	String user = null;
        	String pwd = null;
        	String imUrl = null;
        	String repo = null;
        	String domain = null;
			try {
				user = properties.getPropValues("IM_USER");
				pwd = properties.getPropValues("IM_PASSWORD");
				imUrl = properties.getPropValues("IM_URL_"+instanceNo);
	    		repo = properties.getPropValues("REPO");
	    		domain = properties.getPropValues("DOMAIN");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				value= "Unable to read Property File";
			}
    		System.out.println(docid);	
    		
	        client = IQServiceClientManager.connect(user,pwd,domain,repo,imUrl,null, true);
	        
	        IQContentRecordRequest contentRecordRequest = client.getContentRecordRequest();
	        
	        ContentRecordITO content = contentRecordRequest.getPublishedContentRecordByDocumentIDAndLocale(docid, locale);
	        
	        if (content.getPublished())
	        {
	        	List<CategoryKeyITO> categories = new ArrayList<CategoryKeyITO>();
	        	categories=content.getCategories();
	        	ArrayList<String> listofCategories = new ArrayList<String>();
	        	for (CategoryKeyITO category: categories)
	        		listofCategories.add(category.getReferenceKey());
	        	listofCategories.removeAll(remCatRefList);
	        	listofCategories.addAll(newCatRefKeyList);
	        	
	        	List<CategoryKeyITO> newcategoriesList = new ArrayList<CategoryKeyITO>();
	        	for (String catRef:listofCategories)
	        	{
	        		System.out.println(catRef);
	        		newcategoriesList.add(client.getCategoryRequest().getCategoryKeyByReferenceKey(catRef));
	        	}
	        	content.setCategories(newcategoriesList);
	        	
	        	ContentRecordITO newContent=contentRecordRequest.modifyContent(content,true);
	        	
	        	if(newContent.getPublished())
	        		value = "Successfully Published!!";
	        	
	        }
	        else 
	        	value = "Unpublished document";
        }
        catch(IQServiceClientException e)
        {
        	e.printStackTrace();
        	if (client!=null)
        		client.close();
        	value = "Doc ID Not found";
        	ps.setResult(value);
        }
        client.close();
        ps.setListValue(docid+" : "+locale);
        ps.setResult(value);
		return ps;
		
	}
	
	
	@Path("/deleteCat")
	@POST 
    @Produces(MediaType.APPLICATION_XML) 
	public ArrayList<RestStatus> delCategory(@FormParam("categoryList") String categoryList,@FormParam("instanceNo") String instanceNo)
	{
		
		ArrayList<String> catRefList = new ArrayList<>(Arrays.asList(categoryList.split(",")));
		
		ArrayList<RestStatus> resultList = new ArrayList<>();
		
		//System.out.println(catRefList.size());
		
		IQServiceClient client = null;
		ReadConfig properties = new ReadConfig();
		String value = "failure";
		boolean result= false;
			
	    try {
	    		
	    		String user = properties.getPropValues("IM_USER");
	    		String pwd = properties.getPropValues("IM_PASSWORD");
	    		String imUrl = properties.getPropValues("IM_URL_"+instanceNo);
	    		String repo = properties.getPropValues("REPO");
	    		String domain = properties.getPropValues("DOMAIN");
	    		
		        client = IQServiceClientManager.connect(user,pwd,domain,repo,imUrl,null, true);
		        
		        IQCategoryRequest categoryRequest = client.getCategoryRequest();

		        for (String catRef : catRefList)
		        {
		        	RestStatus rs = new RestStatus();
		        	System.out.println(catRef);
		        	try {
						
		        		CategoryKeyITO category = client.getCategoryRequest().getCategoryKeyByReferenceKey(catRef); 
			        	result=categoryRequest.deleteCategory(category);
			        	if(result)
				 			   value="Category deleted in IM";
				        	
				        rs.setListValue(catRef);
				        rs.setResult(value);
				        	
				        resultList.add(rs);
				        	
					} catch (IQServiceClientException e) {
						e.printStackTrace();
						value = "Category Not Found in IM";
						rs.setListValue(catRef);
				        rs.setResult(value);
				        	
				        resultList.add(rs);
					}

		        }
		        
	        }
	    catch(IOException e)
	    {
	    	RestStatus rs = new RestStatus();
	    	value = "Unable to read property file";
	    	rs.setResult(value);
	    	resultList.add(rs);
	    }
	    
		   client.close();
		   
		   return resultList;
	}
}
