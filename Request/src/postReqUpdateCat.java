import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class postReqUpdateCat {

	public void send_req(String docid, String locale, String remCatRef, String newCatRefKey, String url_to_hit, String instance) {
		OkHttpClient client = new OkHttpClient();
		OkHttpClient client1  = client.newBuilder().readTimeout(45,TimeUnit.SECONDS).build();
		
		System.out.println(docid);
		System.out.println(locale);
		System.out.println(remCatRef);
		System.out.println(newCatRefKey);
		System.out.println(url_to_hit);
		System.out.println(instance);
		RequestBody body = new FormBody.Builder()
		        .add("docid", docid)
		        .add("locale", locale)
		        .add("remCatRef", remCatRef)
		        .add("newCatRefKey", newCatRefKey)
		        .add("instanceNo", instance)
		        .build();
		Request request = new Request.Builder()
		  .url(url_to_hit)
		  .post(body)	
		  .build();
		
		try {
			Response response = client1.newCall(request).execute();
			System.out.println(response);
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
