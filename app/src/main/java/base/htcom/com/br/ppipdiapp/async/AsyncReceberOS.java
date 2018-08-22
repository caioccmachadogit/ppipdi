package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.ws.WSOs;


public class AsyncReceberOS extends AsyncTask<Os, String, String>{

	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterface ti;
	
	public AsyncReceberOS(Activity activity, TarefaInterface ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Recebimento ETP", "Verificando...");
	}
	
	@Override
	protected String doInBackground(Os... params) {
		//---Executa a Requisi��o
		String jsonListOs = "";
		try {
			publishProgress("Verificando ETP");
			Os os = params[0];
			WSOs wsOs = new WSOs();
			jsonListOs = wsOs.receberOS(os.getSOLIC_OV_SERV_NOME(), os.getSOLIC_VISTORIA_EMPRESA_COD(), os.getOS_SITUACAO());
		}
		catch (Exception e) {
			Log.e("ERROR ASYNCLOGIN", e.getMessage());
			return null;
		}
		return jsonListOs;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		pd.setMessage(values[0]);
	}
	
	@Override
	protected void onPostExecute(String result) 
	{
		//---Executa a Pos-Requisi��o
		ti.respostaAsync(result);
		this.pd.dismiss();
		
	}
	
}

