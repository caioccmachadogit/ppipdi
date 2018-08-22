package base.htcom.com.br.ppipdiapp.main;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterOS;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.SiteBLL;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.Site;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSTracker;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class ListOSFragment extends Fragment{
	private ListView lv;
	private SiteBLL siteBLL;
	private OsBLL osBLL;
	private final double CERCAGPS_M = 600000000;//600000000 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_os_fragment, null);
		lv = (ListView) view.findViewById(R.id.lv_os);
		AtualizarListViewOS();
		//==============CLICK ITEM DO LISTVIEW=================
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try {
					siteBLL = new SiteBLL();
					osBLL = new OsBLL();
					long _id = arg3;
					
					/*Intent i = new Intent(getActivity(), OsMenuActitivity.class);
					i.putExtra("_LINHA", String.valueOf(_id));
					startActivity(i);*/
					
					//=========VERIFICAR GPS==========
					GPSTracker GPS = new GPSTracker(getActivity());
					if(GPS.canGetLocation()){
						Os os = osBLL.listarById(getActivity(), String.valueOf(_id)).get(0);
						Site site = siteBLL.listarByCodigo(getActivity(), os.getCOD_ENTIDADE());
						if( (site.getLATITUDE() != null && !site.getLATITUDE().equals(" ")) &&
								(site.getLONGITUDE() != null && !site.getLONGITUDE().equals(" ")) ){
							
							double LATITUDE = Double.valueOf(GPS.getLatitude()), LONGITUDE = Double.valueOf(GPS.getLongitude());
							double latitudeSite = Double.valueOf(site.getLATITUDE().replace(",", "."));
							double longitudeSite = Double.valueOf(site.getLONGITUDE().replace(",", "."));
							//double resultKM = GPSUtills.CalcularDistanciaGPS_KM(LATITUDE, LONGITUDE, latitudeSite, longitudeSite);
							double resultM = GPSUtills.CalcularDistanciaGPS_M(LATITUDE, LONGITUDE, latitudeSite, longitudeSite);
				        	if(resultM < CERCAGPS_M){
				        		Intent i = new Intent(getActivity(), OsMenuActitivity.class);
								i.putExtra("_LINHA", String.valueOf(_id));
								startActivity(i);
				        	}
							else {
								Toast.makeText(getActivity(), "ETP fora do alcance de "+CERCAGPS_M+" metros!", Toast.LENGTH_SHORT).show();
							}
						}
						else {
							Toast.makeText(getActivity(), "ETP com coordenada Inv�lida, reporte ao respons�vel!", Toast.LENGTH_SHORT).show();
						}
			        }else{
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	GPS.showSettingsAlert();
			        }
					//=========VERIFICAR GPS==========
					//-22.2270778, -45.9393716 - Pouso alegre
				} catch (Exception e) {
					LogErrorBLL.LogError(e.getMessage(), "Problemas com a Cerca Digital",getActivity());
					Toast.makeText(getActivity(), "Aguarde a inicializa��o do GPS...", Toast.LENGTH_SHORT).show();
				}
			}
			
		} );
		return view;
	}

	private void AtualizarListViewOS(){
		try {
			osBLL = new OsBLL();
			Cursor cursor = osBLL.listar(getActivity(), 
					SharedPreferencesUtills.loadSavedPreferencesString("USER", getActivity()),
					SharedPreferencesUtills.loadSavedPreferencesString("EMPRESA", getActivity())
					);
			AdapterOS adapterOS = new AdapterOS(getActivity(), cursor);
			lv.setAdapter(adapterOS);
			
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List OS",getActivity());
		}
	}

	public void imgBtn_selecionar(View v){
		String tag = (String)v.getTag();
		int id = Integer.valueOf(tag);
	}
}
