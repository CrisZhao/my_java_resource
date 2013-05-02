package batchprocessor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class DownloadAllProducts extends BaseBatch {

	private static final String HELP_MESSAGE = "DownloadAllProducts -host hostname -port portnumber -service servicename -user loginId -password password -file filename [-actiontype actiontype] [-datatype datatype] [-persisttype persisttype] [-skiponfail skiponfail]\n"
			+ "-host hostname: server name or ip address, such as 192.168.1.222\n"
			+ "-port portnumber: 8080\n"
			+ "-service servicename: JSON or SOAP\n"
			+ "-file filename: data file name to save\n"
			+ "-help | -?: print help messages";

	
	public static void main(String[] args) {
		Map<PARAM, String> maps = ParamParser.parse(args);
		System.out.println(maps);
		if (maps.containsKey(PARAM.HELP)) {
			System.out.println(HELP_MESSAGE);
			return;
		}
		writeToFile(getSaveFileName(maps), processResponse(sendBatch(login(maps), maps)));
	}

	private static String getSaveFileName(Map<PARAM, String> maps) {
		if (maps.get(PARAM.FILE)!=null) {
			return maps.get(PARAM.FILE);
		}
		return "MarketData_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
				+ ".csv";
	}

	public static String sendBatch(String token, Map<PARAM, String> args) {
		String service = args.get(PARAM.SERVICE);
		if (!"json".equalsIgnoreCase(service)) {
			throw new RuntimeException("Unsupported service :" + service);
		}
		return HttpRequest.post("http://" + args.get(PARAM.HOST) + ":" + args.get(PARAM.PORT)
				+ "/RaiyunService/json/product", token, buildParameters(args));
	}
	
	private static String buildParameters(Map<PARAM, String> args) {
		HashMap<String, Object> queryRequests_map = new HashMap<String, Object>();
		queryRequests_map.put("queryConditions", new ArrayList<Object>());
		queryRequests_map.put("dataSource", args.get(PARAM.DATASOURCE));
		queryRequests_map.put("orderObjects", new ArrayList<Object>());

		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("queryRequests", queryRequests_map);
		contentMap.put("queryResultAction", args.get(PARAM.DOWNLOADACTIONTYPE));
		contentMap.put("outputFormat", args.get(PARAM.DOWNLOADOUTPUT));

		return new Gson().toJson(contentMap);
	}

	private static String processResponse(String response) {
		return response.substring(response.indexOf("@template"),
				response.lastIndexOf("contentUrl") - 3);
	}


}