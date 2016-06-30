package com.example.exiel.mygps;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;



/**
 * Created by Exiel on 28/06/2016.
 */
public class HttpHandler {

    public String post(String postUrl)
    {
    MainActivity mainActivity = new MainActivity();

        try {

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(postUrl + "?latitud=enen&longitud=ned");


//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//            params.add(new BasicNameValuePair("latitud",String.valueOf(mainActivity.location.getLatitude())));
//
//            params.add(new BasicNameValuePair("longitud",String.valueOf(mainActivity.location.getLongitude())));

//            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse resp = httpclient.execute(httpPost);

            HttpEntity ent = resp.getEntity();

            String text = EntityUtils.toString(ent);
            return text;

        }

        catch(Exception e) {
            return e.getMessage();
        }


    }





}
