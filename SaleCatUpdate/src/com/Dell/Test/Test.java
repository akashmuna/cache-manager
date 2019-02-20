package com.Dell.Test;

import java.util.ArrayList;
import java.util.Arrays;

import com.Dell.controller.UpdateCategory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpdateCategory uc = new UpdateCategory();
		// Update Old Taxonomy & Add New Taxonomy
		//uc.removeAndPublish("RUN160", "en-US","TEST_SPECIAL","ALL__PRODUCTS_ESUPRT_SER_STOR_NET_ESUPRT_POWERVAULT_DELL__DR4000");		
		
		/*ArrayList<String> remCatRefList = new ArrayList<>(Arrays.asList("ALL__PRODUCTS_ESUPRT_LAPTOP_ESUPRT_ALIENWARE_LAPTOPS_ALIENWARE__M15X", "ALL__PRODUCTS_ESUPRT_LAPTOP_ESUPRT_ALIENWARE_LAPTOPS_ALIENWARE__M14X","ALL__PRODUCTS_ESUPRT_DESKTOP_ESUPRT_ALIENWARE_DSK_ALIENWARE__AURORA__ALX"));
		ArrayList<String> newCatRefKeyList = new ArrayList<>(Arrays.asList("NOT_SEARCHABLE_IN_FAST", "REMOVE_DISCLAIMER__COPY_WRITER_REVIEW"));
		uc.removeAndPublish("HDSK111355", "en_US", remCatRefList, newCatRefKeyList);
*/
		//uc.delCategory("dee");
	}

}
