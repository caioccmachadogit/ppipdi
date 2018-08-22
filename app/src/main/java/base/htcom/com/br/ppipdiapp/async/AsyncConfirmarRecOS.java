package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.ws.WSOs;

public class AsyncConfirmarRecOS extends AsyncTask<Os, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterface ti;
	
	public AsyncConfirmarRecOS(Activity activity, TarefaInterface ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Recebimento ETP", "Confirmando...");
	}
	
	@Override
	protected String doInBackground(Os... params) {
		//---Executa a Requisi��o
		String json = "";
		try {
			publishProgress("Confirmando Recebimento...");
			Os os = params[0];
			WSOs wsOs = new WSOs();
			json = wsOs.ConfirmarRecOS(os.getOV_CHAMADO_NUM());
		}
		catch (Exception e) {
			Log.e("ASYNC_ConfirmaRecOs", e.getMessage());
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
		MainActivity.COUNTCAR = 0;
		MainActivity.COUNTCONFOS++;
		ti.respostaAsyncConfirmaRecOs(result);
		this.pd.dismiss();
		
	}

}
