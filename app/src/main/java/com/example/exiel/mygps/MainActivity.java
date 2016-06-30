package com.example.exiel.mygps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView latitud, longitud, texto4, res;
    Location location;
    LocationManager locManager;
    boolean gpsActivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitud = (TextView)findViewById(R.id.texto);
        longitud= (TextView)findViewById(R.id.texto2);
        texto4 = (TextView) findViewById(R.id.msgtext);
        res = (TextView)findViewById(R.id.resp);


        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsActivo = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


            if (gpsActivo) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                latitud.setText("latitud: " + String.valueOf(location.getLatitude()));
                longitud.setText("longitud: " + String.valueOf(location.getLongitude()));
                String latitudP = "latitud=" + String.valueOf(location.getLatitude());
                String longitudP = "longitud=" + String.valueOf(location.getLongitude());

                HttpHandler handler = new HttpHandler();
                String txt = handler.post("http://190.141.120.200:8080/Rutas/ubicacion/guardar/123456?" + latitudP + "&" + longitudP);
                res.setText(txt);

            }else{
                latitud.setText("sin datos de latitud");
                longitud.setText("sin datos de longitud");

            }


       LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                latitud.setText("latitud: "+String.valueOf(location.getLatitude()));
                longitud.setText("longitud: "+String.valueOf(location.getLongitude()));





            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {


            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                texto4.setText("GPS Desactivado");
            }
        };

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*60, 5,locListener);
    }
/*/------------------------------------
public String post(String postUrl)
{

    try {

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(postUrl);


        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("latitud",String.valueOf(location.getLatitude())));

        params.add(new BasicNameValuePair("longitud",String.valueOf(location.getLongitude())));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse resp = httpclient.execute(httpPost);

        HttpEntity ent = resp.getEntity();

        String text = EntityUtils.toString(ent);
        return text;

    }

    catch(Exception e)
    {
        return  e.getMessage();
    }

}

    */


}

