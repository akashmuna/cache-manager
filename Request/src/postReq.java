import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class postReq {
	
	public void create_req(String categoryList) {
		OkHttpClient client = new OkHttpClient();
		//MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		//RequestBody body = RequestBody.create(mediaType, "categoryList=" + categoryList);
		RequestBody body = new FormBody.Builder()
		        .add("categoryList", categoryList)
		        .build();
		Request request = new Request.Builder()
		  .url("http://6260c2a1.ngrok.io/SaleCatUpdate/rest/UpdateCat/deleteCat")
		  .post(body)
		  .addHeader("content-type", "application/x-www-form-urlencoded")		
		  .build();
		
		try {
			Response response = client.newCall(request).execute();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
	}


/*
OkHttpClient client = new OkHttpClient();

RequestBody formBody = new FormBody.Builder()
        .add("message", "Your message")
        .build();
Request request = new Request.Builder()
        .url("http://www.foo.bar/index.php")
        .post(formBody)
        .build();

try {
    Response response = client.newCall(request).execute();

    // Do something with the response.
} catch (IOException e) {
    e.printStackTrace();
}		
	*/