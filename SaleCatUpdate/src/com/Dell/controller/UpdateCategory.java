package com.Dell.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Dell.model.RestStatus;
import com.inquira.client.serviceclient.IQServiceClient;
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

	/**
	 * 
	 * <p> This Method Removes the Old Taxonomy and Adds the new Taxonomy in one Shot</P>
	 * 
	 * @param docid : DOCUMENTID to be used to update
	 * @param locale : Locale of the DOCUMENTID to be used
	 * @param remCatRef : ReferenceKey for Old Taxonomy tree
	 * @param newCatRefKey : ReferenceKey for New Integrated Taxonomy to be replace
	 * @return A RestStatus Object with a success/Failure message 
	 */
	public RestStatus removeAndPublish(String docid,String locale, String remCatRef,String newCatRefKey)
	   {
			RestStatus ps = new RestStatus();
		    IQServiceClient client = null;
			ReadConfig properties = new ReadConfig();
			String value = "failure";
			
	        try {
	        	String user = properties.getPropValues("IM_USER");
	    		String pwd = properties.getPropValues("IM_PASSWORD");
	    		String imUrl = properties.getPropValues("IM_URL");
	    		String repo = properties.getPropValues("REPO");
	    		String domain = properties.getPropValues("DOMAIN");
	    		
	    	System.out.println(docid);	
	    		
	        client = IQServiceClientManager.connect(user,pwd,domain,repo,imUrl,null, true);
	        
	        IQContentRecordRequest contentRecordRequest = client.getContentRecordRequest();
	        
	        ContentRecordITO content = contentRecordRequest.getLatestContentRecordByDocumentIDAndLocale(docid, locale);
	        
	        System.out.println(content.getPublished());
	        
	        if(content.getPublished())
	        {	
	        	List<CategoryKeyITO> categories = new ArrayList<CategoryKeyITO>();
	        	categories=content.getCategories();
	        	int i = 0,index = 0 ;
	        	for (CategoryKeyITO category: categories)
	        	{
	        		System.out.println("value : "+ category.getReferenceKey());
	        		if (category.getReferenceKey().equalsIgnoreCase(remCatRef))
	        			index = i;
	        		i++;
	        	}
	        	categories.remove(index);
	        	categories.add(client.getCategoryRequest().getCategoryKeyByReferenceKey(newCatRefKey));
	        	content.setCategories(categories);
	        	/*System.out.println(content.getUserName());
	        	System.out.println(content.getUserID());*/
	        	ContentRecordITO newContent=contentRecordRequest.modifyContent(content,true);
	        	
	        	if(newContent.getPublished())
	        		value = "Successfully Published!!";
	        }
	        else 
	        	value = "Doc ID Not found";
	        
	        } catch (Exception e) {
	            if ( client != null )System.out.println(client.getEWR().toString());
	                e.printStackTrace();
	            System.out.println("******* ERROR **********");
	            client.close();
	            value = "Connection issue or Doc ID Not found";
	            ps.setResult(value);
				return ps;
	        }
	        client.close();
	        ps.setResult(value);
			return ps;
	   }

	//Overloading the method incase a single article has more than one Category Changes
	public RestStatus removeAndPublish(String docid,String locale, ArrayList<String> remCatRefList,ArrayList<String> newCatRefKeyList)
	{
		RestStatus ps = new RestStatus();
	    IQServiceClient client = null;
		ReadConfig properties = new ReadConfig();
		String value = "failure";
		
        try {
        	String user = properties.getPropValues("IM_USER");
    		String pwd = properties.getPropValues("IM_PASSWORD");
    		String imUrl = properties.getPropValues("IM_URL");
    		String repo = properties.getPropValues("REPO");
    		String domain = properties.getPropValues("DOMAIN");
    		
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
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	if (client!=null)
        		client.close();
        	value = "Connection issue or DOC ID Not found";
        	ps.setResult(value);
        }
        client.close();
        ps.setResult(value);
		return ps;
		
	}
	
	
	@Path("/deleteCat")
	@POST 
    @Consumes("multipart/form-data") 
    @Produces({"application/xml","application/json"}) 
	public RestStatus delCategory(@FormDataParam("catRefList") String categoryList)
	{
		ArrayList<String> catRefList = new ArrayList<>(Arrays.asList(categoryList.split(",")));
		
		RestStatus rs = new RestStatus();
		IQServiceClient client = null;
		ReadConfig properties = new ReadConfig();
		String value = "failure";
		boolean result= false;
			
	    try {
	    		
	    		String user = properties.getPropValues("IM_USER");
	    		String pwd = properties.getPropValues("IM_PASSWORD");
	    		String imUrl = properties.getPropValues("IM_URL");
	    		String repo = properties.getPropValues("REPO");
	    		String domain = properties.getPropValues("DOMAIN");
	    		
		        client = IQServiceClientManager.connect(user,pwd,domain,repo,imUrl,null, true);
		        
		        IQCategoryRequest categoryRequest = client.getCategoryRequest();
		        
		        for (String catRef : catRefList)
		        {
		        	CategoryKeyITO category = client.getCategoryRequest().getCategoryKeyByReferenceKey(catRef); 
		        	result=categoryRequest.deleteCategory(category);
		        }
		        
		       /*  Adding catetgory to current branch
		        CategoryITOImpl category = new CategoryITOImpl();
		        
		        category.setReferenceKey("ALL__PRODUCTS_ESUPRT_CONVERGED_INFRASTRUCTURE_ESUPRT_CONVERGED_SYSTEMS_ESUPRT_CONVERGED_SYSTEMS_PIVOTAL");
		        category.setName("CONVERGED SYSTEMS PIVOTAL");
		        category.setParent(client.getCategoryRequest().getCategoryKeyByReferenceKey("ALL__PRODUCTS_ESUPRT_CONVERGED_INFRASTRUCTURE_ESUPRT_CONVERGED_SYSTEMS"));
		        categoryRequest.addCategory(category, "en_US");*/
	        }
		catch(Exception e)
		   {
		     	e.printStackTrace();
		        if (client!=null)
		        	client.close();
		        value = "Connection issue or Category Not found";
		   
		   }
		   client.close();
		   
		   if(result)
			   value="success";
		
		   rs.setResult(value);
		return rs;
	}
}
