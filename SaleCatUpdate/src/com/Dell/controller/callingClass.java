package com.Dell.controller;

import java.util.ArrayList;
import java.util.Calendar;

import com.ibm.icu.text.SimpleDateFormat;

public class callingClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpdateCategory uc = new UpdateCategory();
		String systime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(systime);
		ArrayList <String> to_remove = new ArrayList <String>();
		to_remove.add("TEST_CATEGORY_2");
		to_remove.add("TEST_CATEGORY_3");
		to_remove.add("TEST_CATEGORY_4");
		ArrayList <String> to_add = new ArrayList <String>();
		to_add.add("TEST_CATEGORY_5");
		to_add.add("TEST_CATEGORY_6");
		to_add.add("TEST_CATEGORY_7");
		 
		uc.removeAndPublish("SLN316041", "en_US",to_remove,to_add);
		systime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(systime);

	}

}
