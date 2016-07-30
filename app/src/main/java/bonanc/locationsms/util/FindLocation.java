package bonanc.locationsms.util;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Bonan Cao on 11/20/15.
 */
public class FindLocation extends Service implements LocationListener {
    private final Context context;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    Location location;
    double longitude;
    double latitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATE = 10;
    private static final long MIN_TIME_TW_UPDATE = 1000 * 60 * 1;
    protected LocationManager locationManager;

    public FindLocation(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        try {
            // Check permission
            if (Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission(context,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                throw new Exception("Permission denied.");
            }
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            if (locationManager == null) {
                throw new Exception("locationMananger is null.");
            }
            // Check location provider
            boolean isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isNetworkEnable && !isGPSEnable) {
                canGetLocation = false;
                throw new Exception("No location service.");
            } else {
                canGetLocation = true;
                if (isNetworkEnable) { // get from network
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_TW_UPDATE, MIN_DISTANCE_CHANGE_FOR_UPDATE, this);
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if (isGPSEnable) { // get from GPS
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            MIN_TIME_TW_UPDATE, MIN_DISTANCE_CHANGE_FOR_UPDATE, this);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
            return location;
        }
        catch (Exception e) {
            Log.e("EX",e.getMessage());
            return null;
        }
    }
    public double getLongitude(){
        if (location != null)
            return location.getLongitude();
        return 0;
    }
    public double getLatitude(){
        if (location != null)
            return location.getLatitude();
        return 0;
    }
    @Override
    public void onLocationChanged(Location location) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
