package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.ReenviarOSFragment;
import base.htcom.com.br.ppipdiapp.ws.WSBateria;

public class AsyncReenviarBateria extends AsyncTask<String, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterfaceReenv ti;
	
	public AsyncReenviarBateria(Activity activity, TarefaInterfaceReenv ti){
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
			publishProgress("Enviando Baterias");
			WSBateria ws = new WSBateria();
			json = ws.EnviarBateria(jsonEnvio);
		}
		catch (Exception e) {
			Log.e("ERROR ASYNCEnviaBateria", e.getMessage());
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
		ReenviarOSFragment.cEnvioBateria++;
		ti.respostaAsyncEnvioBateria(result);
		this.pd.dismiss();
	}

}
