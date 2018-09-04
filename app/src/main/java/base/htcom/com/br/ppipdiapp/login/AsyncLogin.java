package base.htcom.com.br.ppipdiapp.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import base.htcom.com.br.ppipdiapp.model.Colaborador;

public class AsyncLogin extends AsyncTask<Colaborador, Void, String>{

	public static AsyncResponse delegate=null;
	private ProgressDialog pd;
	private Activity activity;
	private String TAG = getClass().getSimpleName();
	
	public AsyncLogin(View rootView, Activity activity){
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		pd = ProgressDialog.show(activity, "Executando", "Verificando...");
	}
	
	@Override
	protected String doInBackground(Colaborador... params) {
		//---Executa a Requisi��o
		String respWs;
		try {
			Colaborador colaborador = params[0];
			WSLogin wsLogin = new WSLogin();
			respWs = wsLogin.validarColaborador(colaborador.getEmail(), colaborador.getSenha());
		}
		catch (Exception e) {
			Log.e(TAG,"ERROR ASYNCLOGIN",e);
			return null;
		}
		return respWs;
	}
	
	protected void onPostExecute(String result) 
	{
		//---Executa a Pos-Requisi��o
		this.pd.dismiss();
		delegate.processFinish(result);
	}
	
	public interface AsyncResponse {
		void processFinish(String output);
	}

}
