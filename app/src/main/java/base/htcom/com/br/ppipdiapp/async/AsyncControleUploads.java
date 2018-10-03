package base.htcom.com.br.ppipdiapp.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.main.ReenviarOSFragment;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.funcoes.FTPManager;
import base.htcom.com.br.ppipdiapp.padrao.utils.VarConfig;
import base.htcom.com.br.ppipdiapp.ws.WSControleUpload;

public class AsyncControleUploads extends AsyncTask<String, String, List<ControleUpload>>{
	private ProgressDialog pd;
	private Activity activity;
	private TarefaInterfaceReenv ti;
	private String host = VarConfig.Ftphost;
	private String user = VarConfig.Ftpuser;
	private String passw = VarConfig.Ftppassw;
	private String dir = VarConfig.Ftpdir;
	private final String TAG = getClass().getSimpleName();
	private List<ControleUpload> lstControleUploads;
	private WSControleUpload ws = new WSControleUpload();
	private Gson gson = new GsonBuilder().create();
	private FTPManager ftpManager = new FTPManager();
	private List<ControleUpload> controleUploadsOK = new ArrayList<>();
	
	public AsyncControleUploads(Activity activity, TarefaInterfaceReenv ti, List<ControleUpload> lstControleUploads){
		this.activity = activity;
		this.ti = ti;
		this.lstControleUploads = lstControleUploads;
	}

	@Override
	protected void onPreExecute() {
		pd = ProgressDialog.show(activity, "Envio ETP", "Enviando Imagem");
	}
	
	@Override
	protected List<ControleUpload> doInBackground(String... params) {
		List<ControleUpload> controleUploadsSucesso = new ArrayList<>();
		int tentativa = 0;

		Log.d(TAG,"lstControleUploads->"+lstControleUploads.size());
		do {
			Log.d(TAG,"controleUploadsOK->"+controleUploadsOK.size());
			Log.d(TAG,"tentativa->"+tentativa);
			uploadFtp(lstControleUploads);
			tentativa++;
		}
		while (controleUploadsOK.size()!=lstControleUploads.size());

		Log.d(TAG,"controleUploadsOK->"+controleUploadsOK.size());
		if(controleUploadsOK.size() > 0){
			for (int i=0;i<controleUploadsOK.size();i++){
				ControleUpload controleUpload = controleUploadsOK.get(i);
				if(ws.EnviarUploadArqPref(gson.toJson(controleUpload)).equals("true")) {
					publishProgress("Confirmando Imagens: "+i+1+"/"+controleUploadsOK.size());
				}
				controleUploadsSucesso.add(controleUpload);
			}
		}
		return controleUploadsSucesso;
	}

	private void uploadFtp(List<ControleUpload> controleUploadList) {
		try {
			for (int i=0; i < controleUploadList.size();i++){
				ControleUpload controleUpload = controleUploadList.get(i);
				if(verificaNaoUpload(controleUpload)){
					String arqOrigem = ((MainActivity)activity).externalFilesDir+"/"+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO();
					Log.d(TAG,"Foto->"+arqOrigem);
					if (new File(arqOrigem).exists()){
						if(ftpManager.conectar(host,user,passw,21)){
							if (ftpManager.upload(arqOrigem,
									controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO(),
									dir+"OV_"+controleUpload.getOV_CHAMADO_NUM()))
							{
								Log.d(TAG,"UploadsOK->"+arqOrigem);
								controleUploadsOK.add(controleUpload);
								publishProgress("Enviando Imagens: "+i+1+"/"+lstControleUploads.size());
							}
							ftpManager.desconectar();
						}
						else{
							Log.d(TAG,"FTP NAO CONECTADO");
							ftpManager.desconectar();
						}
					}
					else{
						Log.d(TAG,"FileNotFound->"+arqOrigem);
						controleUploadsOK.add(controleUpload);
					}
				}
			}
		}
		catch (Exception e) {
			Log.e(TAG,"ERROR->", e);
		}
		ftpManager.desconectar();
	}

	private boolean verificaNaoUpload(ControleUpload controleUpload) {
		boolean nUpload = true;
		for (ControleUpload c:controleUploadsOK) {
			if(controleUpload.getARQ_NOME_SIST().equals(c.getARQ_NOME_SIST())){
				nUpload = false;
				Log.d(TAG,"verificaNaoUpload UploadsOK->"+c.getARQ_NOME_SIST());
				break;
			}
		}
		return nUpload;
	}

	@Override
	protected void onProgressUpdate(String... values) {
		pd.setMessage(values[0]);
	}
	
	@Override
	protected void onPostExecute(List<ControleUpload> controleUploadsSucesso)
	{
		Log.d(TAG,"controleUploadsSucesso->"+controleUploadsSucesso.size());
		ti.respostaAsyncEnvioUpload(controleUploadsSucesso);
		this.pd.dismiss();
		
	}

}
