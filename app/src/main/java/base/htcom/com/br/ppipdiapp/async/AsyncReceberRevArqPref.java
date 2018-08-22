package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.ws.WSArqPref;

public class AsyncReceberRevArqPref extends AsyncTask<String, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterfaceRev ti;
	
	public AsyncReceberRevArqPref(Activity activity, TarefaInterfaceRev ti){
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
			String ovChamado = params[0];
			publishProgress("Verificando Arquivos Preferenciais Rev.");
			WSArqPref  wsArqPref = new WSArqPref();
			json = wsArqPref.ReceberRevArqPref(ovChamado);
		}
		catch (Exception e) {
			Log.e("ERROR ASYNCCARRev", e.getMessage());
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
		try {
			ti.respostaAsyncRecRevArqPref(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.pd.dismiss();
		
	}

}
