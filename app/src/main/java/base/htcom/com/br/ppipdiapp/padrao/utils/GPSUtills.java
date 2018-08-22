package base.htcom.com.br.ppipdiapp.padrao.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class GPSUtills {
	
	public Location getlocation;
	private LocationManager locationManager;
	public String provider;
	
	public LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {

			// CHAMADO QUANDO UM NOVO LOCAL � ENCONTRADO PELO LOCAL DE REDE
			//getlocation = location;
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};

	public LocationManager getLocationManager() {
		return locationManager;
	}

	public void setLocationManager(LocationManager locationManager) {
		this.locationManager = locationManager;
	}
	
	public void VerificarGPS(Context context){
		try {
			// LIGA O GPS CASO ESTEJA DESATIVADO
			if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
				ligaDesligaGPS(context);
			}
		}
		catch (Exception e) {
			Log.e("ERROR LIGARGPS" , e.getMessage());
		}
	}
	
	private void ligaDesligaGPS(Context context) throws Exception{
		try {
			Intent intent = new Intent();
			intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
			intent.setData(Uri.parse("3"));
			context.sendBroadcast(intent);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public boolean RegistrarGPSCriteria() {
		boolean resp = false;
		try {
			Criteria criteria = new Criteria ();
			provider = locationManager.getBestProvider(criteria, false);
			
			@SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(provider);//EM ALGUNS DISPOSITIVOS - PROBLEMAS NESSA LINHA
			if(location != null){
				Log.v("PROVIDER" , " Provider " + provider + " foi selecionado .");
				getlocation = location;
				resp = true;
			}
		}
		catch (Exception e) {
			Log.e("ERROR PROVIDER" , e.getMessage());	
		}
		return resp;
	}

	public void RemoveUpdates(){
		locationManager.removeUpdates(locationListener);
	}
	
	public static double CalcularDistanciaGPS_KM(double latitudeInicial,double longitudeInicial,double latitudeFinal,double longitudeFinal){  
		 double  d       = 0;
	        int raioTerra   = 6371;
	        double PI       = Math.PI;
	        int valorMetade = 90;
	        int valorInteiro= 180;
	        
	        double v1 = Math.cos(PI * (valorMetade  - latitudeFinal)           / 180);
	        double v2 = Math.cos((valorMetade       - latitudeInicial)    * PI / 180);
	        double v3 = Math.sin((valorMetade       - latitudeFinal)      * PI / 180);
	        double v4 = Math.sin((valorMetade       - latitudeInicial)    * PI / 180);
	        double v5 = Math.cos((longitudeInicial  - longitudeFinal)     * PI / 180);
	        
	        double result = raioTerra * Math.acos((v1 * v2) + (v3 * v4 * v5));
	        
	        return d = result;  
    }  
	
	public static Double CalcularDistanciaGPS_M(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
	    final int RADIUS_EARTH = 6371;

	    double dLat = getRad(latitude2 - latitude1);
	    double dLong = getRad(longitude2 - longitude1);

	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(getRad(latitude1)) * Math.cos(getRad(latitude2)) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return (RADIUS_EARTH * c) * 1000;
	    }

	    private static Double getRad(Double x) {
	    return x * Math.PI / 180;
	    }
}
