package com.Infy;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

	public static void getGIMLResponse(String query) throws JSONException, ParseException, IOException {
		
		String path = System.getProperty("user.dir");
		String question = query;
		System.out.println("Entered till token generation!!");
		String token = getAuthToken();
		String auth = authorization(token);
		System.out.println("token: "+auth);
		String userToken = auth.substring(246, 398);
		String sessionID = intialScreenSession(token, userToken);
		// String question = "branch";
		// System.out.println("sessionID :"+sessionID);
		String searchResults = AskQuestion(sessionID, token, userToken, question);
		// System.out.println("Search Results : "+searchResults);
		String fileName ="C:\\Service_Cloud\\Results.xml";
		convertToXML(searchResults, fileName);

		/*try {
			finalresults = (ArrayList<Results>) parseResultsXML(fileName);
		} catch (Exception e) {

			e.printStackTrace();
		}*/
		 
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

	public static List<Results> parseResultsXML(String fileName) {
		
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
			
			for (int i = 0; i < 4; i++) {
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
		Results rst = new Results();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			rst.setTitle(getTitle(fileName, i));
			rst.setGlobalAnswerId("https://citizens--tst1.custhelp.com/app/answers/answer_view/a_id/"
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

	private static String AskQuestion(String sessionID, String token, String userToken, String question) {
		// TODO Auto-generated method stub
		String results = "";
		String authtoken = token.split(":")[1].replace("\"", "").replace("}", "");
		try {
			HttpResponse<String> response = Unirest
					.post("https://citizens--tst1-qp.custhelp.com/srt/api/v1/search/question?question=" + question)
					.header("kmauthtoken",
							"{\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\""
									+ authtoken + "\",\"userToken\":\"" + userToken + "\"}")
					.header("Content-Type", "application/json").header("cache-control", "no-cache")
					.header("Postman-Token", "fd3b819b-6197-42ff-aeb6-84e601e908ff")
					.body("{\"session\" : \"16882011dc8788-fb1a-4b0b-8bfe-edbfb789a9e9\",\"locale\" : \"en-US\",\"resultLocales\": \"en-US\"}")
					.asString();
			results = response.getBody().toString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return results;
	}

	private static String intialScreenSession(String token, String userToken) {
		String sessionId = "";
		String authtoken = token.split(":")[1].replace("\"", "").replace("}", "");
		try {
			HttpResponse<String> response = Unirest
					.post("https://citizens--tst1-qp.custhelp.com/srt/api/latest/search/initialScreen")
					.header("kmauthtoken",
							"{\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\""
									+ authtoken + "\",\"userToken\":\"" + userToken + "\"}")
					.header("Content-Type", "application/json").header("cache-control", "no-cache")
					.body("{\"transactionId\" : 0,\"locale\" : \"en-US\",\"resultLocales\": \"en-US\",\"session\" : 1}")
					.asString();
			// System.out.println(response.getHeaders());
			sessionId = response.getBody().toString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionId;
	}

	public static String getAuthToken() {
		String jsonString = "";
		try {
			System.out.println("Get Auth Token called!!");
			HttpResponse<String> response = Unirest
					.post("https://citizens--tst1-irs.custhelp.com/km/api/latest/auth/integration/authorize")
					.header("Accept", "application/json")
					.header("kmauthtoken",
							"{\"login\":\"testapi\",\"password\":\"Test@123\",\"siteName\":\"citizens__tst1\"}")
					.header("Content-Type", "application/json").header("cache-control", "no-cache")
					.body("{\"login\":\"testapi\",\"password\":\"Test@123\",\"siteName\":\"citizens__tst1\"}")
					.asString();
			System.out.println("Stuck here!!");
			jsonString = response.getBody().toString();
			System.out.println("Unable to fetch Auth Token: "+jsonString);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString.toString();
	}

	public static String authorization(String authtoken) throws JSONException, ParseException, IOException {
		// System.out.println(authtoken);
		String token = authtoken.split(":")[1].replace("\"", "").replace("}", "");
		String responseString = "";
		try {
			HttpResponse<String> response = Unirest
					.post("https://citizens--tst1-irs.custhelp.com/km/api/latest/auth/authorize")
					.header("Accept", "application/json")
					.header("kmauthtoken",
							"{\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\",\"integrationUserToken\":\""
									+ token + "\"}")
					.header("Content-Type", "application/x-www-form-urlencoded").header("cache-control", "no-cache")
					.body("userName=N044796&password=Infy@123&siteName=citizens__tst1&userExternalType=CONTACT")
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

