package com.dell.okb.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "restStatuses")
@XmlAccessorType (XmlAccessType.FIELD)
public class RestStatusList {

		@XmlElement(name = "restStatus")
		private ArrayList<RestStatus> restStatusList;

		public ArrayList<RestStatus> getRestStatusList() {
			return restStatusList;
		}

		public void setRestStatusList(ArrayList<RestStatus> restStatusList) {
			this.restStatusList = restStatusList;
		}
}
