package batchprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class HttpRequest {
    
    static String post(String url, String token, String jsonRequest){
        try {
            URL httpurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("token", token);
            conn.setRequestProperty("Accept-Language", "zh-CN");
            
            OutputStream os = conn.getOutputStream();
            os.write(jsonRequest.getBytes());
            os.flush();
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output).append("\n");
            }
            conn.disconnect();
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Failed:" + e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed:" + e.getMessage();
        }
    }
}
