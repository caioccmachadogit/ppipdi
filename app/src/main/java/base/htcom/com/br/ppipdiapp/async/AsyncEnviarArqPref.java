package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.EnviarOSFragment;
import base.htcom.com.br.ppipdiapp.ws.WSArqPref;

public class AsyncEnviarArqPref extends AsyncTask<String, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterfaceEnviar ti;
	
	public AsyncEnviarArqPref(Activity activity, TarefaInterfaceEnviar ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Envio ETP", "Enviando...");
	}

	@Override
	protected String doInBackground(String... params) {
		//---Executa a Requisi��o
		String json = "";
		try {
			String jsonEnvio = params[0];
			publishProgress("Enviando Arquivos Preferenciais");
			WSArqPref ws = new WSArqPref();
			json = ws.EnviarArqPref(jsonEnvio);
			if(json.equals("true")){
				EnviarOSFragment.jsonArqPrefsEnviados.add(jsonEnvio);
			}
		}
		catch (Exception e) {
			Log.e("ERROR ASYNCEnvioArqPref", e.getMessage());
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
		EnviarOSFragment.cEnvioArqPref++;
		ti.respostaAsyncEnvioArqPref(result);
		this.pd.dismiss();
	}

}
