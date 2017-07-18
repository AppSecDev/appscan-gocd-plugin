

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class gocloud {

	public static void main(String[] args) {
		try {

			//Bogus credentials if the user didn't specify any
			String apiId = "9d3e5ded-57cd-43dc-a56c-14fd7ac3a0tu";
			String apiSecret = "OHNSF7a1WsIzEyBGeoxZ9iIUsx8efupNCVkka4JGOcA=";
			
			if (args.length > 0) {
				apiId = args[0];
			} 
			if (args.length > 1) {
				apiSecret = args[1];
			}
			
			URL url = new URL("https://appscan.ibmcloud.com/api/v2/Account/ApiKeyLogin");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			//conn.setRequestProperty("charset", "utf-8");
			
			String input = "";
			input += "{";
			input += " \"KeyId\" : \"" + apiId + "\" ,";
			input += " \"KeySecret\" : \"" + apiSecret + "\" ";
			input += "}";
			
			DataOutputStream writer = new DataOutputStream(
					conn.getOutputStream());
			writer.writeBytes(input);
			writer.flush();
			writer.close();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				System.out.println("Request Failed.  Error Code : " + conn.getResponseCode());
				System.out.println("Response Body: \n" + conn.getResponseMessage());
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (Exception e) {
			  e.printStackTrace();
		}
	}
}