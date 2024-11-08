package com.mobile.productsale;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobile.productsale.model.PayOSPaymentRequestDTO;
import com.mobile.productsale.model.VietQrResponse;
import com.mobile.productsale.services.GoMapsService;
import com.mobile.productsale.services.PaymentService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in a specific location and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
    }

//    private void fetchLocation(String query) {
//        goMapsService.getLocation(query, new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    String content = response.body();
////                    Picasso.get().load(qrUrl).into(qrImageView);
//                    WebView webViewMaps = findViewById(R.id.webViewMaps);
//                    webViewMaps.getSettings().setJavaScriptEnabled(true);
//                    webViewMaps.loadUrl("https://maps.gomaps.pro/maps/api/js?key=AlzaSyJ-TujuvlBIoq23w5Gf1hpMTTz6k5NsZxV&libraries=geometry,places&callback=initMap");
//                } else {
//                    Toast.makeText(MapsActivity.this, "Failed to load GoMaps", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(MapsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}