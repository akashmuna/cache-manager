package com.Infosys;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestTemplateController rc = new RestTemplateController();
		ArrayList<Results> Resultslist = rc.getJsonResponse("credit cards");
		Resultslist.stream().forEach(item->{System.out.println(item.getTitle());});
		
	}

}
