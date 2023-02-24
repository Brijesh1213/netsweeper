package utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class TestRailManager {
	
	
	public static String TEST_RUN_ID = "122";
	public static String TESTRAIL_USERNAME = "brijesh.trivedi@netsweeper.com";
	public static String TESTRAIL_PASSWORD = "Netsweeper123!";
	public static String RAILS_ENGINE_URL = "https://netsweeper.testrail.io/";
	public static final int TEST_CASE_PASSED_STATUS= 1;
	public static final int TEST_CASE_FAILED_STATUS = 5;
	
	public static void addResultsForTestcases(String testCaseID,int status, String error) throws IOException, IOException, APIException  {
	
		String testRunId = TEST_RUN_ID;
		APIClient client = new APIClient(RAILS_ENGINE_URL);
		client.setUser(TESTRAIL_USERNAME);
		client.setPassword(TESTRAIL_PASSWORD);
		Map<String, Comparable> data = new HashMap<String, Comparable>();
		data.put("status_id", status);
		data.put("comment", "Test Executed - status updated automatically");
		client.sendPost("add_result_for_case/"+testRunId+"/"+testCaseID+"", data);
	}

}
