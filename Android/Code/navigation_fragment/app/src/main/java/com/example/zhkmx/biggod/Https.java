package com.example.zhkmx.biggod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class Https {
    /**
     * This is the https request function to the remote server, with the get method.
     * The parameter url means the url of the remote server.
     * This function can handle the certificate untrusted problem.
     * @param url
     * @return The String type of json.
     */
	public String GetHttps(String url){
        String https = url;
        StringBuffer sb = new StringBuffer();
        try{

            SSLContext sc = SSLContext.getInstance("TLS");  
            sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());  
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());  
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());  
            HttpsURLConnection conn = (HttpsURLConnection)new URL(https).openConnection();
            conn.setDoInput(true);
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;

            while ((line = br.readLine()) != null)   
                sb.append(line);
//                System.out.println("*********==============************");
            	//System.out.println(sb.toString());
           }catch(Exception e){
                e.printStackTrace();
           }
        return sb.toString();
    }

    /**
     * This is the https request function to the remote server, with the post method.
     * The parameter url means the url of the remote server.
     * The parameter data means the parameters of the post.
     * @param url
     * @param data demo:[data = "user_id=1&device_deviceId=74:d0:2b:05:8d:50";]
     * @return The String type of json array.
     */
    public String PostHttps(String url,String data){
        String https = url;
        StringBuffer sb = new StringBuffer();
        try{

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
            HttpsURLConnection conn = (HttpsURLConnection)new URL(https).openConnection();
            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            OutputStreamWriter writer  = new OutputStreamWriter(conn.getOutputStream());
            writer.write(data);
            writer.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;

            while ((line = br.readLine()) != null)
                sb.append(line);
            //System.out.println("*********==============************");
            //System.out.println(sb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
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

////@Test
class mainclass{
	public static void main(String[] args) {
		Https t = new Https();
		String result = t.GetHttps("https://120.27.44.239:32001/state/b8:27:eb:ba:d6:b9&1");
        System.out.println(result);

	}


}
