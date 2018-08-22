package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import base.htcom.com.br.ppipdiapp.main.ReenviarOSFragment;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.funcoes.FTPManager;
import base.htcom.com.br.ppipdiapp.padrao.utils.VarConfig;
import base.htcom.com.br.ppipdiapp.ws.WSControleUpload;

public class AsyncReenviarUploadArqPref extends AsyncTask<String, String, String>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterfaceReenv ti;
	private String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PPI_PDI/Fotos/";
	private String host = VarConfig.Ftphost;
	private String user = VarConfig.Ftpuser;
	private String passw = VarConfig.Ftppassw;
	private String dir = VarConfig.Ftpdir;
	
	public AsyncReenviarUploadArqPref(Activity activity, TarefaInterfaceReenv ti){
		this.activity = activity;
		this.ti = ti;
	}
	
	@Override
	protected void onPreExecute() {
		//---Inicia a espera do progresso---
		ReenviarOSFragment.contaUpArqPref++;
		pd = ProgressDialog.show(activity, "Envio ETP", "Enviando Imagem: "+ReenviarOSFragment.contaUpArqPref);
	}
	
	@Override
	protected String doInBackground(String... params) {
		//---Executa a Requisi��o
		String json = "";
		try {
			String jsonEnvio = params[0];
			publishProgress("Enviando Imagem: "+ReenviarOSFragment.contaUpArqPref);
			//ENVIAR FOTO - FTP
			Gson gson = new GsonBuilder().create();
			ControleUpload controleUpload = gson.fromJson(jsonEnvio, ControleUpload.class);
			FTPManager ftpManager = new FTPManager();
			if(ftpManager.conectar(host,user,passw,21)){
				if (ftpManager.upload(PATH+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO(), 
						controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO(),
					dir+"OV_"+controleUpload.getOV_CHAMADO_NUM())) 
				{
					WSControleUpload ws = new WSControleUpload();
					json = ws.EnviarUploadArqPref(jsonEnvio);
				}
			}
		}
		catch (Exception e) {
			Log.e("ASYNCEnvioUpArqPref", e.getMessage());
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
		ReenviarOSFragment.cEnvioUpArqPref++;
		ti.respostaAsyncEnvioUpload(result);
		this.pd.dismiss();
		
	}

}
