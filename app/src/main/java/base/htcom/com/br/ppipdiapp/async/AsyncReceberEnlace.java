package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.ws.WSEnlace;

public class AsyncReceberEnlace extends AsyncTask<Os, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterface ti;
	
	public AsyncReceberEnlace(Activity activity, TarefaInterface ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Recebimento OS", "Verificando Enlaces");
	}
	
	@Override
	protected String doInBackground(Os... params) {
		//---Executa a Requisi��o
		String json = "";
		try {
			Os os = params[0];
			publishProgress("Verificando Enlace (OS: "+ os.getCOD_ENTIDADE()+")");
			//Thread.sleep(500);
			WSEnlace wsEnlace = new WSEnlace();
			json = wsEnlace.receberEnlace(os.getCOD_ENTIDADE(), os.getOV_CHAMADO_NUM());
			
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
		MainActivity.COUNTOS++;
		ti.respostaAsync(result);
		this.pd.dismiss();
	}
}
