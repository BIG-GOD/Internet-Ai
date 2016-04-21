package com.example.zhkmx.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class https {
    /**
     * This is the https request function to the remote server, with the get method.
     * The parameter url means the url of the remote server.
     * This function can handle the certificate untrusted problem.
     * @param url
     */
	public void GetHttps(String url){
        String https = url;
        try{  
            SSLContext sc = SSLContext.getInstance("TLS");  
            sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());  
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());  
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());  
            HttpsURLConnection conn = (HttpsURLConnection)new URL(https).openConnection();  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.connect();  
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));   
            StringBuffer sb = new StringBuffer();   
            String line;   
            while ((line = br.readLine()) != null)   
                sb.append(line);                  
            	System.out.println(sb.toString());
           }catch(Exception e){  
                System.err.printf(this.getClass().getName(), e.getMessage());  
           }        
     }  
  
  
  
      private class MyHostnameVerifier implements HostnameVerifier{  
            @Override  
            public boolean verify(String hostname, SSLSession session) {  
                    // TODO Auto-generated method stub  
                    return true;  
            }  
  
       }  
  
       private class MyTrustManager implements X509TrustManager{  
            @Override  
            public void checkClientTrusted(X509Certificate[] chain, String authType)  
                            throws CertificateException {  
                    // TODO Auto-generated method stub    
            }  
            @Override  
            public void checkServerTrusted(X509Certificate[] chain, String authType)  
  
                            throws CertificateException {  
                    // TODO Auto-generated method stub      
            }  
            @Override  
            public X509Certificate[] getAcceptedIssuers() {  
                    // TODO Auto-generated method stub  
                    return null;  
            }          
  
      }     
       
     
}

//
//class mainclass{
//	public static void main(String[] args) {
//		test t = new test();
//		t.GetHttps();
//	}
//
//
//}
