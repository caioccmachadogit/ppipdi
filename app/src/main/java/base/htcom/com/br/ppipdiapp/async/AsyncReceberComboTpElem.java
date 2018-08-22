package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.ws.WSCombo;

public class AsyncReceberComboTpElem extends AsyncTask<String, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterface ti;
	
	public AsyncReceberComboTpElem(Activity activity, TarefaInterface ti){
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
			String filtro = params[0];
			publishProgress("Verificando Combos");
			WSCombo  wsCombo = new WSCombo();
			json = wsCombo.ReceberCombo(filtro,"");
		}
		catch (Exception e) {
			Log.e("ERROR ASYNCCOMBO", e.getMessage());
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
		MainActivity.COUNTCOMBO = 0;
		//MainActivity.COUNTCOMBOTPELEMTO++;
		ti.respostaAsync(result);
		this.pd.dismiss();
		
	}

}
