package batchprocessor;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.google.gson.Gson;

public class BaseBatch {

	@SuppressWarnings("rawtypes")
	public static String login(Map<PARAM, String> args) {
		String host = args.get(PARAM.HOST);
		String port = args.get(PARAM.PORT);
		String user = args.get(PARAM.USER);
		String password = args.get(PARAM.PASSWORD);
		String response = HttpRequest.post("http://" + host + ":" + port
				+ "/RaiyunService/json/login", "", "{\"loginId\":\"" + user
				+ "\",\"password\":\"" + password + "\"}");
		Map json = new Gson().fromJson(response, Map.class);
		return (String) ((Map) json.get("content")).get("token");
	}

	public static String readFromFile(String inputFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(inputFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int s = 0;
			byte[] b = new byte[1024 * 4];
			while ((s = is.read(b)) != -1) {
				bos.write(b, 0, s);
			}
			return new String(bos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void writeToFile(String file, String content) {
		try {

			OutputStream os = new FileOutputStream(file, true);

			os.write(content.getBytes());

			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}