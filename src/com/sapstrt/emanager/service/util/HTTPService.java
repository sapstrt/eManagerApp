package com.sapstrt.emanager.service.util;

import android.util.Log;

import com.sapstrt.emanager.domain.Expense;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pteltu on 10/24/13.
 */
public class HTTPService {

    private String TAG="com.sapstrt.resttokenmyprojectTag";
    private HttpClient httpClient=new DefaultHttpClient();;
    private HttpPost httpPost;
    private HttpResponse httpResponse;
    private String responseString = null;


    private static final HTTPService informationToSever=new HTTPService();

        private HTTPService(){

        }

        public static HTTPService getInstance(){
            return informationToSever;
        }



       public void sendTokenToServer( String token) throws IOException {

                this.httpPost = new HttpPost("http://10.209.32.27:9090/eManager-1.0-SNAPSHOT/app/user/create");
                try {
                        this.httpPost.addHeader("idToken",token);
                        this.httpResponse = this.httpClient.execute(this.httpPost);
                        String response=getResponseString();
                        if(response.equalsIgnoreCase("User Authenticated!")){


                         }
                        else{

                        }
                }
                catch (UnsupportedEncodingException e) {
                    Log.e(TAG, e.toString());
                }
                catch (ClientProtocolException e) {
                    Log.e(TAG, e.toString());
                }
                catch (IOException e) {
                    Log.e(TAG, e.toString());
                }

       }

    public void sendExpenseToServer(String token,Expense expense){

        this.httpPost = new HttpPost("http://10.209.32.27:9090/eManager-1.0-SNAPSHOT/app/"); ///url for sending 1 expense
        try {
            this.httpPost.addHeader("idToken",token);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("Expense","ExpenseName:"+expense.getExpenseName()+"Amount:"+expense.getAmount()+
                    "Date:"+expense.getDate()+"Location:"+expense.getLocation()+
                    "Mode"+expense.getMode()));

            this.httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
            this.httpResponse = this.httpClient.execute(this.httpPost);
        }
        catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
        }
        catch (ClientProtocolException e) {
            Log.e(TAG, e.toString());
        }
        catch (IOException e) {
            Log.e(TAG, e.toString());
        }

    }
    public void sendExpensesToServer(String token,List<Expense> expenseList){

        this.httpPost = new HttpPost("http://10.209.32.27:9090/eManager-1.0-SNAPSHOT/app/"); ///url for sendingall expenses
        try {
            this.httpPost.addHeader("idToken",token);
            List<NameValuePair> params = new ArrayList<>();
            for(Expense e:expenseList){
                params.add(new BasicNameValuePair(e.getExpenseId().toString(),
                        "ExpenseName:"+e.getExpenseName()+"Amount:"+e.getAmount()+
                                "Date:"+e.getDate()+"Location:"+e.getLocation()+
                                "Mode"+e.getMode()));
            }

            this.httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
            this.httpResponse = this.httpClient.execute(this.httpPost);
            String response=getResponseString();
            if(response.equalsIgnoreCase("All the expenses added!")){


            }
            else{

            }
        }
        catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
        }
        catch (ClientProtocolException e) {
            Log.e(TAG, e.toString());
        }
        catch (IOException e) {
            Log.e(TAG, e.toString());
        }

    }
     public String getResponseString(){
        if(this.httpResponse!=null){
            try {
                HttpEntity httpEntity = this.httpResponse.getEntity();
                InputStream is = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "utf-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                this.responseString = sb.toString();
                return this.responseString;
            } catch (Exception e) {
                Log.e(TAG + "Response string buffer error. ", e.getMessage());
            }
        }
        return null;
    }


     public Integer getGrpIdFromServer(String token){
         Integer grpId=null;
         try {

             String getURL = "";// get url for grpId
             this.httpPost = new HttpPost("");
             this.httpPost.addHeader("idToken",token);
             this.httpResponse = this.httpClient.execute(this.httpPost);
             grpId= Integer.valueOf(getResponseString());

         } catch (Exception e) {
             e.printStackTrace();
         }
             return grpId;

     }



}
