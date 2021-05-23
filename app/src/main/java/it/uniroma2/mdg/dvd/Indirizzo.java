package it.uniroma2.mdg.dvd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.InfoWindow;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class Indirizzo extends Fragment implements OnMapReadyCallback, PermissionsListener {
    MapView mapView;
    private Indirizzo activity;
    public static ArrayList<PosizioneMarker> posizioneMarkers;
    private PermissionsManager permissionsManager;
    public static MapboxMap mapboxMap;
    LocationEngine locationEngine;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_indirizzo, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = getView().findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);





    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

// Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();





            // Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(getContext(), loadedMapStyle).build());

// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

// Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);


            Log.d("prova", "enableLocationComponent: latitudine "+locationComponent.getLastKnownLocation().getLatitude() + "longitudine " + locationComponent.getLastKnownLocation().getLongitude());
            String url;
            url= Caratteristiche.baseurl+"/Progeto/Caratteristiche?distanza=c&lat1=".concat(Double.toString(locationComponent.getLastKnownLocation().getLatitude())).concat("&lon1=").concat(Double.toString(locationComponent.getLastKnownLocation().getLongitude()));
            AsyncTask task1=new Task().execute(url,"9");
            //Marker marker = mapboxMap.addMarker(new MarkerOptions().position(new LatLng(42.4125105,12.12404671)).title("Ciao ciao ciao ciao"));
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(getActivity());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getContext(), "Permesso autorizzato", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(getContext(), "Permesso negato", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        Indirizzo.this.mapboxMap=mapboxMap;

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
                



            }
        });

        mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
            private int tenDp = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);

            @Nullable
            @Override
            public View getInfoWindow(@NonNull final Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.layout,null);
                TextView textView = v.findViewById(R.id.textViewnomescuola);
                TextView textView1 = v.findViewById(R.id.textViewindirizzoscuola);
                String [] parte = marker.getTitle().split(";");
                textView.setText(parte[0]);
                textView1.setText(parte[1]);
                //textView.setBackgroundColor(Color.WHITE);
                //textView.setPadding(tenDp, tenDp, tenDp, tenDp);
                Button button = v.findViewById(R.id.bottonescuola);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),Scuola.class);
                        intent.putExtra("codicescuola", marker.getSnippet());
                        startActivity(intent);
                    }
                });
                return v;
            }


        });
    }

    @Override
    @SuppressWarnings( {"MissingPermission"})
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public static final int MSG_UPDATE = 1;

    public static Handler Myhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==MSG_UPDATE){
                for(int c=0; c < posizioneMarkers.size(); c++){
                    Marker marker = mapboxMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(posizioneMarkers.get(c).getLatitudine()),Double.parseDouble(posizioneMarkers.get(c).getLongitudine()))).title(posizioneMarkers.get(c).getNomescuola() + ";" + posizioneMarkers.get(c).getIndirizzo()).snippet(posizioneMarkers.get(c).getCodicescuola()));
                }
            }
        }
    };

}

