import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final String path = System.getProperty("user.dir");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + "/SIT1_DEL_CHILD2.csv"));
			String categoryList;
			while ((categoryList = reader.readLine())!=null) {
			//	System.out.println(categoryList);
				postReq pr = new postReq();
				pr.create_req(categoryList);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
