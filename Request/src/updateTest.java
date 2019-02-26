import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class updateTest extends Thread implements Runnable{
	public updateTest(String doc, String loc, String del, String new_c, String url_to_hit, String instance) {
	       // store parameter for later user, constructor to assign values
		this.doc = doc;
		this.loc = loc;
		this.del = del;
		this.new_c = new_c;
		this.url_to_hit = url_to_hit;
		this.instance = instance;
	 }
	String doc;
	String loc;
	String del;
	String new_c;
	String url_to_hit;
	String instance; 
	
	public void run() 
    { 
        try
        {  
           // System.out.println ("Thread " + Thread.currentThread().getId() + " is running"); 
            postReqUpdateCat pr = new postReqUpdateCat();
            //System.out.println(doc);
            /*System.out.println(loc);
            System.out.println(del);
            System.out.println(new_c);
            System.out.println(instance);
            System.out.println(url_to_hit);*/
			pr.send_req(doc,loc,del,new_c, url_to_hit, instance);
        } 
        catch (Exception e) 
        { 
            System.out.println ("Exception is caught"); 
        } 
    } 
	
	public static void main(String[] args) throws IOException  {
		final String path = System.getProperty("user.dir");
		getPropValues gpob = new getPropValues();
		// Input file should have same number of entries per line as num threads
		int num_threads = 2;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + "/test_two_threads_1000.txt"));
			String input;String[] arr_input;
			// URLS are in config.properties
			int  curr_url = 0; int num_url = 2; String instance; int num_instances = 2; int ins_num = 0;
			while ((input = reader.readLine())!=null) {
				// Documents+ parameters delimited by #
				String[] doc_params = input.split("#");
				// Start thread for every doc in current line 
				for (int i = 0; i < num_threads ; i++) {
						instance = Integer.toString(i+1);
						String url_to_hit = gpob.getProperties("IM_URL_"+Integer.toString((curr_url % num_url)+1));
						curr_url++;
						String temp = doc_params[i];
						arr_input = temp.split(","); 
						//parameters delimited by ,
						String doc = arr_input[0];
						String loc = arr_input[1];
						String del = arr_input[2];
						String new_c = arr_input[3];
						// Call run with input 
						Runnable r = new updateTest(doc,loc,del,new_c,url_to_hit, instance);
						new Thread(r).start();
				}	
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}