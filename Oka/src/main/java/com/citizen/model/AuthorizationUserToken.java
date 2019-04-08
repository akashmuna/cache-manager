package com.citizen.model;

public class AuthorizationUserToken {

	private String localeId;
	private String knowledgeInteractionId;
	private String billableSessionId;
	private String appId;
	private String siteName;
	private String interfaceId;
	private String requiresBillable;
	private String captureAnalytics;
	private String integrationUserToken;
	private String userToken;
	private String referrer;
	private String querySource;
	
	public String getLocaleId() {
		return localeId;
	}
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}
	public String getKnowledgeInteractionId() {
		return knowledgeInteractionId;
	}
	public void setKnowledgeInteractionId(String knowledgeInteractionId) {
		this.knowledgeInteractionId = knowledgeInteractionId;
	}
	public String getBillableSessionId() {
		return billableSessionId;
	}
	public void setBillableSessionId(String billableSessionId) {
		this.billableSessionId = billableSessionId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
	public String getRequiresBillable() {
		return requiresBillable;
	}
	public void setRequiresBillable(String requiresBillable) {
		this.requiresBillable = requiresBillable;
	}
	public String getCaptureAnalytics() {
		return captureAnalytics;
	}
	public void setCaptureAnalytics(String captureAnalytics) {
		this.captureAnalytics = captureAnalytics;
	}
	public String getIntegrationUserToken() {
		return integrationUserToken;
	}
	public void setIntegrationUserToken(String integrationUserToken) {
		this.integrationUserToken = integrationUserToken;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getQuerySource() {
		return querySource;
	}
	public void setQuerySource(String querySource) {
		this.querySource = querySource;
	}

}
