package com.example.exiel.mygps;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Exiel on 28/06/2016.
 */
public class HttpHandler {
    private RestOperation restOperation = null;



    public String post(String postUrl)
    {
        MainActivity ma = new MainActivity();

        try {

            restOperation = new RestOperation();
            restOperation.execute(postUrl);

            String text = restOperation.getStatus().toString();
            return text;

        }

        catch(Exception e) {
            return e.getMessage();
        }


    }


    private class RestOperation extends AsyncTask<String, Void, Void> {

        final HttpClient httpClient = new DefaultHttpClient();
        String content, error, data;
        JSONObject jsonResponse;
        Boolean islogin_message= false;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();


        }

        @Override
        protected Void  doInBackground(String... params){
            BufferedReader br = null;
            URL url;


            try {
                url = new URL(params[0]);
                URLConnection connection =  url.openConnection();
                connection.setDoOutput(true);
                br =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line =  null;
                while ((line = br.readLine()) != null){
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                    content =  sb.toString();

                }

            } catch (MalformedURLException e ){
                error = e.getMessage();
                e.printStackTrace();
            } catch (IOException e) {
                error = e.getMessage();
                e.printStackTrace();
            } finally {
                /*try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
                */
                if ((br != null)) {
                    try {
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            if (error!=null){

            }
            /*else {
                try {
                    jsonResponse =  new JSONObject(content);
                    //System.out.println("valor del json.. " + jsonResponse);
                    //String isLoginResponse = jsonResponse.getString("IsLogin");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
             */
        }
    }


}
