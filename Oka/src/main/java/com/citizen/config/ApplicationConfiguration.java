package com.citizen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class ApplicationConfiguration {
	
	@Value("{app.ENV_URL}")
	private String ENV_URL;
	
	@Value("{app.API_USERID}")
	private String API_USERID;
	
	@Value("{app.API_PASSWORD}")
	private String API_PASSWORD;
	
	@Value("{app.siteName}")
	private String siteName;
	
	@Value("{app.AUTH_IRS_URL}")
	private String AUTH_IRS_URL;
	
	@Value("{app.CP_USERID}")
	private String CP_USERID;
	
	@Value("{app.CP_PASSWORD}")
	private String CP_PASSWORD;
	
	@Value("{app.ASK_QUESTION_URL}")
	private String ASK_QUESTION_URL;
	
	@Value("{app.userExternalType}")
	private String userExternalType;
	
	public String getUserExternalType() {
		return userExternalType;
	}
	public void setUserExternalType(String userExternalType) {
		this.userExternalType = userExternalType;
	}
	public String getENV_URL() {
		return ENV_URL;
	}
	public void setENV_URL(String eNV_URL) {
		ENV_URL = eNV_URL;
	}
	public String getAPI_USERID() {
		return API_USERID;
	}
	public void setAPI_USERID(String aPI_USERID) {
		API_USERID = aPI_USERID;
	}
	public String getAPI_PASSWORD() {
		return API_PASSWORD;
	}
	public void setAPI_PASSWORD(String aPI_PASSWORD) {
		API_PASSWORD = aPI_PASSWORD;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getAUTH_IRS_URL() {
		return AUTH_IRS_URL;
	}
	public void setAUTH_IRS_URL(String aUTH_IRS_URL) {
		AUTH_IRS_URL = aUTH_IRS_URL;
	}
	public String getCP_USERID() {
		return CP_USERID;
	}
	public void setCP_USERID(String cP_USERID) {
		CP_USERID = cP_USERID;
	}
	public String getCP_PASSWORD() {
		return CP_PASSWORD;
	}
	public void setCP_PASSWORD(String cP_PASSWORD) {
		CP_PASSWORD = cP_PASSWORD;
	}
	public String getASK_QUESTION_URL() {
		return ASK_QUESTION_URL;
	}
	public void setASK_QUESTION_URL(String aSK_QUESTION_URL) {
		ASK_QUESTION_URL = aSK_QUESTION_URL;
	}
}
