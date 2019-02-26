package com.dell;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SearchResult {
	
	private static final Pattern TAG_REGEX = Pattern.compile("<session>(.+?)</session>", Pattern.DOTALL);
	
	private static String getTagValues(final String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = TAG_REGEX.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	    }
	    return tagValues.get(0);
	}

	public static ArrayList<Results> getGIMLResponse(String query) throws JSONException, ParseException, IOException {
		
		ReadConfig properties = new ReadConfig();
		
		//String path = System.getProperty("user.dir");
		String AUTH_IRS_URL= properties.getPropValues("AUTH_IRS_URL");
		String api_user = properties.getPropValues("API_USERID");
		String api_password = properties.getPropValues("API_PASSWORD");
		String siteName = properties.getPropValues("siteName");
		String cp_user = properties.getPropValues("CP_USERID");
		String cp_password = properties.getPropValues("CP_PASSWORD");
		String askquestionURL = properties.getPropValues("ASK_QUESTION_URL");
		String question = query;
		String token = getAuthToken(AUTH_IRS_URL,api_user,api_password,siteName);
		
		String auth = authorization(token,AUTH_IRS_URL,siteName,cp_user,cp_password);
		System.out.println("token: "+token);
		System.out.println("auth:"+auth);
		String userToken = auth.substring(246, 398);
		String sessionID = intialScreenSession(token, userToken, askquestionURL, siteName);
		
		System.out.println("sessionID :" +sessionID);
		
		String searchResults = AskQuestion(sessionID, token, userToken, question,askquestionURL,siteName);
		
		String fileName =properties.getPropValues("XML_File_Path");
		convertToXML(searchResults, fileName);
		
		ArrayList<Results> finalresults = new ArrayList<>();

		try {
			finalresults = (ArrayList<Results>) parseResultsXML(fileName);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return finalresults;
	}

	private static String getTitle(String fileName, int i)
			throws ParserConfigurationException, SAXException, IOException {

		String title = "";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);

		// Create XPath

		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();

		XPathExpression expr;
		try {
			expr = xpath.compile("//ResultItem/title/snippets/Snippet/text/text()");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;

			title = nodes.item(i).getNodeValue();

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;

	}

	private static List<Results> parseResultsXML(String fileName) {
		
		File xmlFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		List<Results> resultList = new ArrayList<Results>();
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("ResultItem");
			// now XML is loaded as Document in memory, lets convert it to
			// Object List
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				resultList.add(getResultItem(nodeList.item(i), i, fileName));
			}
			// lets print Employee list information
			for (Results rst : resultList) {
				System.out.println("title:" + rst.getTitle());
				System.out.println("url:" + rst.getGlobalAnswerId());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	private static Results getResultItem(Node node, int i, String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		// XMLReaderDOM domReader = new XMLReaderDOM();
		ReadConfig properties = new ReadConfig();
		
		String env_url = properties.getPropValues("ENV_URL");
		
		Results rst = new Results();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			rst.setTitle(getTitle(fileName, i));
			rst.setGlobalAnswerId("https://"+env_url+"/app/answers/answer_view/a_id/"
					+ getTagValue("globalAnswerId", element));
			rst.setScore(getTagValue("score", element));
		}

		return rst;
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	private static void convertToXML(String searchResults, String fileName) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			// Use String reader
			Document document = builder.parse(new InputSource(new StringReader(searchResults)));
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();
			Source src = new DOMSource(document);
			Result dest = new StreamResult(new File(fileName));
			aTransformer.transform(src, dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String AskQuestion(String sessionID, String token, String userToken, String question, String askquestionURL, String siteName) {
		// TODO Auto-generated method stub
		String results = "";
		String authtoken = token.split(":")[1].replace("\"", "").replace("}", "");
		try {
			HttpResponse<String> response = Unirest
					.post("https://"+askquestionURL+"/srt/api/v1/search/question?question=" + question)
					.header("kmauthtoken",
							"{\"siteName\":\""+siteName+"\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\""
									+ authtoken + "\",\"userToken\":\"" + userToken + "\"}")
					.header("Content-Type", "application/json").header("cache-control", "no-cache")
					.body("{\"session\" : \""+sessionID+"\",\"locale\" : \"en-US\",\"resultLocales\": \"en-US\"}")
					.asString();
			results = response.getBody().toString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return results;
	}

	private static String intialScreenSession(String token, String userToken,String ASK_QUESTION_URL,String siteName) {
		String sessionId = "";
		String authtoken = token.split(":")[1].replace("\"", "").replace("}", "");
		try {
			HttpResponse<String> response = Unirest
					.post("https://"+ASK_QUESTION_URL+"/srt/api/latest/search/initialScreen")
					.header("kmauthtoken",
							"{\"siteName\":\""+siteName+"\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\""
									+ authtoken + "\",\"userToken\":\"" + userToken + "\"}")
					.header("Content-Type", "application/json").header("cache-control", "no-cache")
					.body("{\"transactionId\" : 0,\"locale\" : \"en-US\",\"resultLocales\": \"en-US\",\"session\" : 1}")
					.asString();
			// System.out.println(response.getHeaders());
			sessionId = response.getBody().toString();
			
			System.out.println(getTagValues(sessionId));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getTagValues(sessionId);
	}

	public static String getAuthToken(String AUTH_IRS_URL, String api_user, String api_password, String siteName) {
		String jsonString = "";
		try {
			HttpResponse<String> response = Unirest
					.post("https://"+AUTH_IRS_URL+"/km/api/latest/auth/integration/authorize")
					.header("Accept", "application/json")
					.header("kmauthtoken",
							"{\"login\":\""+api_user+"\",\"password\":\""+api_password+"\",\"siteName\":\""+siteName+"\"}")
					.header("Content-Type", "application/json").header("cache-control", "no-cache")
					.body("{\"login\":\""+api_user+"\",\"password\":\""+api_password+"\",\"siteName\":\""+siteName+"\"}")
					.asString();
			jsonString = response.getBody().toString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString.toString();
	}

	public static String authorization(String authtoken, String aUTH_IRS_URL, String siteName, String cp_user, String cp_password) throws JSONException, ParseException, IOException {
		
		String token = authtoken.split(":")[1].replace("\"", "").replace("}", "");
		String responseString = "";
		try {
			HttpResponse<String> response = Unirest
					.post("https://"+aUTH_IRS_URL+"/km/api/latest/auth/authorize")
					.header("Accept", "application/json")
					.header("kmauthtoken",
							"{\"siteName\":\""+siteName+"\",\"localeId\":\"en_US\",\"integrationUserToken\":\""
									+ token + "\"}")
					.header("Content-Type", "application/x-www-form-urlencoded").header("cache-control", "no-cache")
					.body("userName="+cp_user+"&password="+cp_password+"&siteName="+siteName+"&userExternalType=CONTACT")
					.asString();
			// System.out.println(response.getBody().toString());
			responseString = response.getBody().toString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseString;

	}

}
