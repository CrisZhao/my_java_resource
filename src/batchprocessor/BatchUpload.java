package batchprocessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class BatchUpload extends BaseBatch {

	private static final String HELP_MESSAGE = "BatchUpload -host hostname -port portnumber -service servicename -user loginId -password password -file filename [-actiontype actiontype] [-datatype datatype] [-persisttype persisttype] [-skiponfail skiponfail]\n"
			+ "-host hostname: server name or ip address, such as 192.168.1.222\n"
			+ "-port portnumber: 8080\n"
			+ "-service servicename: JSON or SOAP\n"
			+ "-actiontype actiontype: Validation, Mapping, Calculation, Persist, default Persist\n"
			+ "-datatype datatype: IDList, CSV, XML, default CSV\n"
			+ "-persisttype persisttype: Add, Update, Delete, default Update\n"
			+ "-skiponfail skiponfail: true, false, default false\n"
			+ "-file filename: data file name\n"
			+ "-output filename: returns the result ( containing the error message ) in writing to the file\n"
			+ "-help | -?: print help messages";

	public static void main(String[] args) {
		Map<PARAM, String> maps = ParamParser.parse(args);
		if (maps.containsKey(PARAM.HELP)) {
			System.out.println(HELP_MESSAGE);
			return;
		}
		String token = login(maps);

		String response = sendBatch(token, maps);
		
		System.out.println(response);

		if (maps.containsKey(PARAM.OUTPUT)) {
			
			writeToFile(maps.get(PARAM.OUTPUT), processContent(response));
		}
	}

	private static String processContent(String content) {
		String start = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+"\n";
		if (content.endsWith("\r\n")) {
			return content.substring(0, content.length() - 2);
		} else if (content.endsWith("\n")) {
			return content.substring(0, content.length() - 1);
		}
		return start + content;
	}

	public static String sendBatch(String token, Map<PARAM, String> args) {
		String service = args.get(PARAM.SERVICE);
		if (!"json".equalsIgnoreCase(service)) {
			throw new RuntimeException("Unsupported service :" + service);
		}
		String host = args.get(PARAM.HOST);
		String port = args.get(PARAM.PORT);
		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("dataType", args.get(PARAM.DATATYPE).toUpperCase());
		contentMap.put("dataSource", "MARKET_DATA");
		contentMap.put("skipOnFfail", args.get(PARAM.SKIPONFAIL));
		contentMap.put("actionType", args.get(PARAM.ACTIONTYPE).toUpperCase());
		contentMap.put("persistType", args.get(PARAM.PERSISTYPE).toUpperCase());
		
		StringBuilder result = new StringBuilder();
		
		String files = args.get(PARAM.FILE);
		String[] split = files.split("\\|");
		for(String file : split){
		    String inputData = readFromFile(file);
	        contentMap.put("inputData", inputData);
	        String content = new Gson().toJson(contentMap);

	        String response = HttpRequest.post("http://" + host + ":" + port
	                + "/RaiyunService/json/product/process", token, content);
	        
	        result.append("上传文件:" + file + "\r\n").append(response).append("\r\n");
		}
		
		return result.toString();
	}

}