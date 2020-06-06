package com.example.giswisatacamping;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.net.sip.SipSession;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] nama,alamat,telpon,gambar,id;
    int jumData;
    private Double[] latitude,longitude;
    Boolean Marker0[];
    LatLng latLng[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getlokasi() {
        String url = "https://giswisataalam.000webhostapp.com/camping.php";
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jumData = response.length();
                        Log.d("DEBUG_", "PARSE JSON");
                        latLng = new LatLng[jumData];
                        nama = new String[jumData];
                        alamat = new String[jumData];
                        telpon = new String[jumData];
                        gambar = new String[jumData];
                        id = new String[jumData];
                        latitude = new Double[jumData];
                        longitude = new Double[jumData];

                        for (int i=0 ;i  <jumData ;i++>){
                            try {
                                JSONObject data = response.getJSONObject(i);
                                id[i] = data.getString("id");
                                latLng[i] = new LatLng(data.getDouble("latitude"), data.getDouble("longitude"));
                                nama[i] = data.getString("nama");
                                alamat[i] = data.getString("alamat");
                                telpon[i] = data.getString("telpon");
                                gambar[i] = data.getString("gambar");
                                latitude[i] = data.getDouble("latitude");
                                longitude[i] = data.getDouble("longitude");
                                Marker0[i] = false;
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng[i])
                                        .title(nama[i]));

                            } catch (JSONException je) {

                            }
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[i], 15.5f));

                        }
                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                        builder.setTitle("eror");
                        builder.setMessage("Conection failed");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getlokasi();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });

        Volley.newRequestQueue(this).add (request);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getlokasi();
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
