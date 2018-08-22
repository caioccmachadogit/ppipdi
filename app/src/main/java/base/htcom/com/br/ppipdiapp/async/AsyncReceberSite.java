package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.ws.WSSite;

public class AsyncReceberSite extends AsyncTask<String, String, String>{

	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterface ti;
	
	public AsyncReceberSite(Activity activity, TarefaInterface ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Recebimento ETP", "Verificando...");
	}
	
	@Override
	protected String doInBackground(String... params) {
		//---Executa a Requisi��o
		String json = "";
		try {
			String codEntidade = params[0];
			publishProgress("Verificando Sites");
			WSSite wsSite = new WSSite();
			json = wsSite.receberSite(codEntidade);
			
		}
		catch (Exception e) {
			Log.e("ERROR ASYNCLOGIN", e.getMessage());
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
		MainActivity.COUNTOS = 0;
		MainActivity.COUNTSITE++;
		ti.respostaAsync(result);
		this.pd.dismiss();
		
	}
}
