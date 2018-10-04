package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.ws.WSOs;

public class AsyncConfirmarEnvOS_Upload extends AsyncTask<List<Os>, String, List<String>>{
	private ProgressDialog pd;
	private Activity activity;
	private CallBackGeneric callBackGeneric;
	private WSOs wsOs = new WSOs();
	private final String TAG = getClass().getSimpleName();
	
	public AsyncConfirmarEnvOS_Upload(Activity activity, CallBackGeneric callBackGeneric){
		this.activity = activity;
		this.callBackGeneric = callBackGeneric;
	}

	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Envio ETP", "ETP...");
	}

	@Override
	protected List<String> doInBackground(List<Os>... lists) {
		List<Os> osList = lists[0];
		List<String> osLinha = new ArrayList<>();
		try {
			publishProgress("Confirmando ETP...");
			for (int i=0;i<osList.size();i++){
				Os os = osList.get(i);
				if(wsOs.ConfirmarEnvOS(
						os.getOV_CHAMADO_NUM(),
						os.getCONC_OV_SERV_NOME(),
						os.getCONC_OV_SERV_DATA(),
						os.getOBSERVACAO_OS(),
						os.getANEXOS())
						.equals("true")){
					Log.d(TAG,"Confirma ETP OV_CHAMADO_NUM->"+os.getOV_CHAMADO_NUM());
					Log.d(TAG,"Confirma ETP LINHA->"+os.getLINHA());
					osLinha.add(os.getLINHA());
					publishProgress("Confirmando ETP: "+i+1+"/"+osList.size());
				}
			}
		}
		catch (Exception e) {
			Log.e(TAG,"ERROR->", e);
		}
		return osLinha;
	}
	
	@Override
	protected void onPostExecute(List<String> result)
	{
		Log.d(TAG,"Result Confirmacao->"+result.size());
		callBackGeneric.callBackSuccess(result);
		this.pd.dismiss();
		
	}

}
