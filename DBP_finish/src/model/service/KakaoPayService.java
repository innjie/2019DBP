package model.service;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.ws.ServiceMode;

import model.KakaoPayApproval;
import model.KakaoPayReady;
import model.Reserve;





@ServiceMode
public class KakaoPayService {
	 
    private static final String HOST = "https://kapi.kakao.com";
    
    private KakaoPayReady kakaoPayReady;
    private KakaoPayApproval kakaoPayApproval;
    
    public String kakaoPayReady(Reserve reserve) throws Exception {

    	StringBuilder response;
    	String url = "https://kapi.kakao.com/v1/payment/ready";
    	
        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
 
        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Authorization", "KakaoAK " + "f012a4f7b8ffba856d5cb47c7d4e1e92");
        // httpClient.setRequestProperty("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        
        
        String urlParameters = 
        		"cid = TC0ONETIME %26 partner_order_id = 1004 %26 partner_user_id = " + reserve.getUser().getId() + "%26 item_name = 차량번호 " 
        		+ reserve.getCar().getCarNo() + " 예약  %26 quantity = 1 %26 total_amount = " + String.valueOf(reserve.getCar().getPrice()) 
        		+ " %26 tax_free_amount = 100 %26 approval_url = http://202.20.119.117/RentCarService/reserve/view %26 cancel_url = 202.20.119.117/RentCarService/car/modelView?modelNo= " + reserve.getModel().getModelNo() + "%26 fail_url = /202.20.119.117/RentCarService/car/modelView?modelNo= " + reserve.getModel().getModelNo();

        // Send post request
        httpClient.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();
        
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
/*
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }
 */
        try {
            	kakaoPayReady = (KakaoPayReady) httpClient.getContent();
            	return kakaoPayReady.getNext_redirect_pc_url();
 
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return "/pay";
        
    }
    
    
    public KakaoPayApproval kakaoPayInfo(String pg_token, Reserve reserve) throws Exception {
    	
    	StringBuilder response;
    	String url = "https://kapi.kakao.com/v1/payment/approve";
    	
        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Authorization", "KakaoAK " + "f012a4f7b8ffba856d5cb47c7d4e1e92");
       // httpClient.setRequestProperty("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        

        String urlParameters = 
        		"cid = TC0ONETIME %26 tid = " + kakaoPayReady.getTid() + "%26 partner_order_id = 1004 %26 partner_user_id = "
        				+ reserve.getUser().getId() + " %26 pg_token = " + pg_token + " %26 total_amount = " + String.valueOf(reserve.getCar().getPrice());

        // Send post request
        httpClient.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();
        
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
/*
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }
   */             
        try {
            kakaoPayApproval = (KakaoPayApproval) httpClient.getContent();  
            return kakaoPayApproval;
        
        } catch (Exception e) {          
            e.printStackTrace();
        }
        
        return null;
    }
    
}