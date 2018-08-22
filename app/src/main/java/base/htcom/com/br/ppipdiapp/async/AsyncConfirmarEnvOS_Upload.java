package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.MainActivity;

import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.ws.WSOs;

public class AsyncConfirmarEnvOS_Upload extends AsyncTask<Os, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterface ti;
	
	public AsyncConfirmarEnvOS_Upload(Activity activity, TarefaInterface ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Envio ETP", "ETP...");
	}
	
	@Override
	protected String doInBackground(Os... params) {
		//---Executa a Requisi��o
		String json = "";
		try {
			publishProgress("Confirmando ETP...");
			Os os = params[0];
			WSOs wsOs = new WSOs();
			json = wsOs.ConfirmarEnvOS(os.getOV_CHAMADO_NUM(), os.getCONC_OV_SERV_NOME(), os.getCONC_OV_SERV_DATA(), os.getOBSERVACAO_OS(), os.getANEXOS());
		}
		catch (Exception e) {
			Log.e("ASYNC_ConfirmaEnvOs", e.getMessage());
			return null;
		}
		return json;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		pd.setMessage(values[0]);
	}
	
	@Override
	protected void onPostExecute(String result) 
	{
		//---Executa a Pos-Requisi��o
		MainActivity.cEnvioConfEnvOs++;
		ti.respostaAsyncConfirmaEnvOs(result);
		this.pd.dismiss();
		
	}

}
