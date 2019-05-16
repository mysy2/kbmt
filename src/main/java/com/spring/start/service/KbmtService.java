package com.spring.start.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.start.util.Retrieve;

@Service
public class KbmtService {

	@Autowired
	private TestMapper mapper;
	
	@Autowired
	private Retrieve r;
	
	public String selectNow() {
		return mapper.selectNow();
	}
	
	public List<Map<String, String>> search() {
		return r.srch1("query", "sfield");
	}

	
	// HTTP POST request
	public static String sendPost(String ok, String api, String postBody) {
		// create json body
		URL obj;
		StringBuilder sb = new StringBuilder();

		try {
			obj = new URL(api);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());

			wr.write(postBody.toString().getBytes("UTF-8"));
			// System.out.println(postBody.toString().getBytes("UTF-8"));
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			try {
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				con.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String apiResponse = sb.toString();
		return apiResponse;
	}
	
}
