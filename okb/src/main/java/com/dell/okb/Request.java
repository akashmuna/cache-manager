package com.dell.okb;

public class Request {

	private String docid;
	private String localeid;
	private String remRefCat;
	private String newRefCat;
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getLocaleid() {
		return localeid;
	}
	public void setLocaleid(String localeid) {
		this.localeid = localeid;
	}
	public String getRemRefCat() {
		return remRefCat;
	}
	public void setRemRefCat(String remRefCat) {
		this.remRefCat = remRefCat;
	}
	public String getNewRefCat() {
		return newRefCat;
	}
	public void setNewRefCat(String newRefCat) {
		this.newRefCat = newRefCat;
	}
}
