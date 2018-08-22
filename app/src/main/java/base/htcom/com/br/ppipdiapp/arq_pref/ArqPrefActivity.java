package base.htcom.com.br.ppipdiapp.arq_pref;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterArqPref;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.BitmapUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.CriarDirExterno;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSTracker;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSUtills;

@SuppressLint("NewApi") public class ArqPrefActivity extends Activity {
	private ListView lv;
	private AlertDialog alerta;
	GPSUtills gpsUtills = new GPSUtills();
	@SuppressLint("SimpleDateFormat") private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
	private String date = dateFormat.format(new Date());
	private static String photoFile = new String();
	CriarDirExterno criarDirExterno = new CriarDirExterno();
	private static File file;
	private static ArqPref arqPref;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 1777;
	private static String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PPI_PDI/Fotos/";
	private static String[] nomeArquivo = new String[3];
	private static GPSTracker GPS;
	private static String LATITUDE = null;
	private static String LONGITUDE = null;
	private String _TIPOREL;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_arq_pref);
		_TIPOREL = getIntent().getStringExtra("_TIPOREL");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		TextView titCat = (TextView) findViewById(R.id.tvTitCat);
		titCat.setText("Categoria - "+_TIPOREL);
		TextView tit = (TextView) findViewById(R.id.tvTitSite);
		tit.setText("Site: "+ OsMenuActitivity._OS.getCOD_ENTIDADE().replace("0", "")+ " | ETP: "+OsMenuActitivity._CODIGO);
		lv = (ListView) findViewById(R.id.lv_arq_pref);
		lv.setItemsCanFocus(true);
		AtualizarListViewArqs();
	}
	
	@Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	      switch (item.getItemId()) {
	         // Respond to the action bar's Up/Home button
	         case android.R.id.home:
				finish();
				break;
	      }
	      return true;
	   }
	
	

	private void AtualizarListViewArqs() {
		try {
			String tipo =""; 
			if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("1")){
				tipo = "FL1";
			}
			else if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("2")){
				tipo = "FL2";
			}
			else if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("3")){
				tipo = "FL3";
			}
			else if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("4")){
				tipo = "FL4";
			}
			
			ComboBLL comboBLL = new ComboBLL();
			List<Combo> lst = new ArrayList<Combo>();
			if(OsMenuActitivity._OS.getOS_SITUACAO().equals("OS ABERTA")){
				lst = comboBLL.listarByFiltro(this, tipo, _TIPOREL);
			}
			else if(OsMenuActitivity._OS.getOS_SITUACAO().equals("CAMPO REVISAO")){
				ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
				List<ArqPref> lstArqPrefRev = arqPrefBLL.listarRealizadosByOvChamado(this, OsMenuActitivity._OV_CHAMADO);
				if(lstArqPrefRev != null){
					for(int i=0; i < lstArqPrefRev.size();i++){
						Combo comboRev = comboBLL.listarByOrdem(this, lstArqPrefRev.get(i).getArquivo_Carregado().substring(2, 5), tipo, _TIPOREL);
						if(comboRev != null){
							lst.add(comboRev);
						}
					}
				}
			}
			if(lst != null){
				AdapterArqPref adapterArqPref = new AdapterArqPref(this, R.layout.lv_item_arq_pref, lst);
				lv.setAdapter(adapterArqPref);
			}
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List ARQ", this);
		}

	}

	public void imgBtn_capturar(View v) {
		String linhaCombo = (String) v.getTag();
		ComboBLL comboBLL = new ComboBLL();
		ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
		try {
			ArqPref Pref = arqPrefBLL.listarByArqCarregado(this, "20"+comboBLL.listarById(this, linhaCombo).getORDEM(), OsMenuActitivity._OV_CHAMADO);
			if(Pref != null){
				if(Pref.getOS_VERIF_20XXX().substring(0, 2).equals("OK")){
					//=========VERIFICAR GPS==========
					GPS = new GPSTracker(ArqPrefActivity.this);
					if(GPS.canGetLocation()){
			        	LATITUDE = GPS.getLatitude();
			        	LONGITUDE = GPS.getLongitude();
			        	
			        	arqPref = Pref;
						btn_capturar();
			        }else{
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	GPS.showSettingsAlert();
			        }
					//=========VERIFICAR GPS==========
				}
				else {
					Toast.makeText(ArqPrefActivity.this, "Somente para marca��o OK!", Toast.LENGTH_SHORT).show();
				}
			}
			else {
				Toast.makeText(ArqPrefActivity.this, "Somente para marca��o OK!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR IMGBTNCapturar",this);
			e.printStackTrace();
		}
	}
	
	public void btn_capturar() {
		if(criarDirExterno.CriarDirDB("/PPI_PDI/Fotos/")){
			photoFile = GerarNomeFoto(arqPref) + ".jpg";
			file = new File(criarDirExterno.PATHDIR);
			File foto = new File(criarDirExterno.PATHDIR, photoFile);
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto)); 
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
		}
		else {
			Toast.makeText(ArqPrefActivity.this, "N�o habilitado para capturar foto, problemas com a mem�ria Interna!", Toast.LENGTH_SHORT).show();
		}
	}
	
	private String GerarNomeFoto(ArqPref arqPref){
		Random random = new Random();
		String num = String.valueOf(random.nextInt(100000));
		nomeArquivo[0] = (arqPref.getCODIGO()+"_"+date.substring(0, 6)+"_PREFE_"+arqPref.getArquivo_Carregado()+"_D_"+"mobipref_"+num).replace(".", "_");
		nomeArquivo[1] = (arqPref.getCODIGO()+"_PREFE_"+arqPref.getArquivo_Carregado()+"_D_"+"mobipref_"+num).replace(".", "_");
		nomeArquivo[2] = ("PREFE_"+arqPref.getArquivo_Carregado()+"_D_"+"mobipref_"+num).replace(".", "_");
		return nomeArquivo[0];
	}

	@SuppressWarnings("static-access")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE && resultCode == this.RESULT_OK) {
				if (!file.exists()) {
					file.mkdirs();
				}
				File foto = new File(criarDirExterno.PATHDIR, photoFile);
				if (foto != null) 
				{
					Bitmap bitmap = BitmapUtills.salvarFotoPasta(foto); //METODO NOVO
					
					ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
					ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(this, arqPref.getArquivo_Carregado(), arqPref.getOV_CHAMADO_NUM());
					if(controleUpload != null){
						//JA EXISTE FOTO PARA O ARQPREF, ENT�O DEVE EDITAR
						File file = new File(PATH+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
						if(file.exists()){
							if(file.delete()){}					
						}
						controleUploadBLL.update(this, PrepararControleUpload(bitmap, controleUpload.getLinha()));
					}
					else { //AINDA N�O EXISTE FOTO PARA O ARQPREF, ENT�O DEVE CRIAR
						controleUploadBLL.Insert(this, PrepararControleUpload(bitmap, null));
					}
					new AlertaDialog(this).showDialogAviso(getResources().getString(R.string.geral_Atencao), getResources().getString(R.string.geral_RegistroSalvo));
					new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,int which) {
						finish();
						}
					};
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR COMPRESS",this);
		}
	}
	
	private ControleUpload PrepararControleUpload(Bitmap bitmap, String linhaControleUp) {
		ControleUpload controleUpload = new ControleUpload();
		try {
			if(linhaControleUp != null){
				controleUpload.setLinha(linhaControleUp);
			}
			
			OsBLL osBLL = new OsBLL();
			Os os = osBLL.listarById(this, OsMenuActitivity._LINHA).get(0);

			controleUpload.setCODIGO(arqPref.getCODIGO());
			controleUpload.setCOD_ENTIDADE(arqPref.getCOD_ENTIDADE());
			controleUpload.setVALIDADE("T");
			controleUpload.setCLIENTE(arqPref.getCLIENTE());
			controleUpload.setCONTRATO(arqPref.getCONTRATO());
			controleUpload.setCONTRATO_FASE(arqPref.getCONTRATO_FASE());
			controleUpload.setCOD_ENTIDADE(arqPref.getCOD_ENTIDADE());
			controleUpload.setLOTE_CONTROLE(os.getLOTE_CONTROLE());
			controleUpload.setOV_CHAMADO_NUM(arqPref.getOV_CHAMADO_NUM());
			controleUpload.setAPROVADO("N");
			controleUpload.setArquivo_Carregado(arqPref.getArquivo_Carregado());
			controleUpload.setDocumento_Numero("OV");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			controleUpload.setDATA_DOC(dateFormat.format(new Date()));
			dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			controleUpload.setDATA_DOC_INV(dateFormat.format(new Date()));
			controleUpload.setGRUPO(os.getSOLIC_VISTORIA_EMPRESA_COD());
			controleUpload.setAPLICACAO("MOBILE");
			controleUpload.setAcao("UPLOAD");
			controleUpload.setARQ_TIPO("jpg");
			controleUpload.setARQ_NOME_ORIGINAL("FOTO_MOBILE");
			controleUpload.setARQ_ORIGEM("TAG_MOBILE");
			controleUpload.setARQ_NOME_SIST(photoFile);
			controleUpload.setARQ_NOME_SIST(nomeArquivo[0]);
			controleUpload.setARQ_NOME_SIST_NM(nomeArquivo[1]);
			controleUpload.setARQ_DESTINO(nomeArquivo[2]);
			controleUpload.setARQ_TAM(String.valueOf(bitmap.getByteCount()));
			controleUpload.setARQ_DIM(String.valueOf(bitmap.getWidth())+" x "+String.valueOf(bitmap.getHeight()));
			controleUpload.setINSERCAO_Data_INV(dateFormat.format(new Date()));
			controleUpload.setINSERCAO_DATA(controleUpload.getDATA_DOC());
			controleUpload.setINSERCAO_NOME(os.getSOLIC_OV_SERV_NOME());
			
			//REGISTRA GEOLOCALIZA��O GPS
			if(LATITUDE != null && LONGITUDE != null){
				controleUpload.setCOLUNA_REF_latitude(LATITUDE.replace(".", ","));
				controleUpload.setCOLUNA_REF_longitude(LONGITUDE.replace(".", ","));
				Toast.makeText(this, "Localiza��o Recebida!", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(this, "N�o foi poss�vel receber a Localiza��o!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return controleUpload;
	}

	
	public void imgBtn_obs(View v) {
		String linhaCombo = (String) v.getTag();
		ComboBLL comboBLL = new ComboBLL();
		ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
		try {
			ArqPref Pref = arqPrefBLL.listarByArqCarregado(this, "20"+comboBLL.listarById(this, linhaCombo).getORDEM(), OsMenuActitivity._OV_CHAMADO);
			if(Pref != null){
				DialogObs(Pref);
			}
			else {
				Toast.makeText(ArqPrefActivity.this, "Marque uma Op��o!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR IMGBTNObs",this);
			e.printStackTrace();
		}
	}
	
	private void DialogObs(final ArqPref arqPref) {
		LayoutInflater li = getLayoutInflater();
		
		View view = li.inflate(R.layout.dialog_obs, null);
		final EditText edObs = (EditText) view.findViewById(R.id.edt_obs);
		String obs = arqPref.getOS_VERIF_20XXX().replace(arqPref.getOS_VERIF_20XXX().substring(0,10), "");
		edObs.setText(obs);
		view.findViewById(R.id.btSalvarObs).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
					arqPref.setOS_VERIF_20XXX(arqPref.getOS_VERIF_20XXX().substring(0,10)+edObs.getText());
					if(arqPrefBLL.update(ArqPrefActivity.this, arqPref) == 1 ){
						Toast.makeText(ArqPrefActivity.this, "Observa��o Inserida!", Toast.LENGTH_SHORT).show();
					}
					else {
						Toast.makeText(ArqPrefActivity.this, "Problemas ao inserir Observa��o!", Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e) {
					LogErrorBLL.LogError(e.getMessage(), "ERROR ao inserir Observa��o",ArqPrefActivity.this);
					alerta.dismiss();
					e.printStackTrace();
				}
				alerta.dismiss();
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Observa��o");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
	}
	
	public void imgBtn_img(View v) {
		String linhaCombo = (String) v.getTag();
		ComboBLL comboBLL = new ComboBLL();
		ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
		try {
			ArqPref Pref = arqPrefBLL.listarByArqCarregado(this, "20"+comboBLL.listarById(this, linhaCombo).getORDEM(), OsMenuActitivity._OV_CHAMADO);
			if(Pref != null){
				if(Pref.getOS_VERIF_20XXX().substring(0, 2).equals("OK")){
					ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
					ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(this, Pref.getArquivo_Carregado(), Pref.getOV_CHAMADO_NUM());
					if(controleUpload != null){
						DialogImg(controleUpload);
					}
					else {
						Toast.makeText(ArqPrefActivity.this, "Ainda n�o existe foto!", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					Toast.makeText(ArqPrefActivity.this, "Somente para marca��o OK!", Toast.LENGTH_SHORT).show();
				}
			}
			else {
				Toast.makeText(ArqPrefActivity.this, "Somente para marca��o OK!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR IMGBTNCapturar",this);
			e.printStackTrace();
		}
	}
	
	private void DialogImg(final ControleUpload controleUpload) {
		LayoutInflater li = getLayoutInflater();
		
		View view = li.inflate(R.layout.dialog_img, null);
		File file = new File(PATH+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
	  	if (file.exists())
	  	{
			new BitmapFactory();
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			ImageView img = (ImageView) view.findViewById(R.id.imgArqPref);
			img.setImageBitmap(bitmap);
		}
		view.findViewById(R.id.btOKImg).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alerta.dismiss();
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Foto");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
	}


	public void rBtn_ok(View v){
		String linhaCombo = (String) v.getTag();
		try {
			InserirMarcacao(linhaCombo, "OK", null);
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR INSERIR RABDIOBUTTON",ArqPrefActivity.this);
			e.printStackTrace();
		}
	}

	public void rBtn_nt(View v) {
		String linhaCombo = (String) v.getTag();
		try {
			InserirMarcacao(linhaCombo, "NT", null);
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR INSERIR RABDIOBUTTON",ArqPrefActivity.this);
			e.printStackTrace();
		}
	}

	public void rBtn_na(View v) {
		String linhaCombo = (String) v.getTag();
		try {
			InserirMarcacao(linhaCombo, "NA", null);
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR INSERIR RABDIOBUTTON",ArqPrefActivity.this);
			e.printStackTrace();
		}
	}
	
	private void InserirMarcacao(String linhaCombo, String tipo, String obs) throws Exception{
		String resp = null;
		ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
		ComboBLL comboBLL = new ComboBLL();
		Combo combo = comboBLL.listarById(ArqPrefActivity.this, linhaCombo);
		boolean alter = false;
		ArqPref arqPref = arqPrefBLL.listarByArqCarregado(ArqPrefActivity.this, "20"+combo.getORDEM(), OsMenuActitivity._OV_CHAMADO); 
		if(arqPref != null){
			alter = true;
		}
		
		if(tipo.equals("OK")){
			resp = "OK.MMM.TT.";
		}
		else if(tipo.equals("NT")){
			resp = "NT.MMM.TT.";
		}
		else if(tipo.equals("NA")){
			resp = "NA.MMM.TT.";
		}
		
		if(obs != null){
			resp = resp+obs;
		}
		
		if(alter){
			arqPref.setOS_VERIF_20XXX(resp);
			arqPrefBLL.update(ArqPrefActivity.this, arqPref);
		}
		else {
			arqPrefBLL.Insert(ArqPrefActivity.this, resp, linhaCombo, OsMenuActitivity._LINHA);
		}
		
		
	}
	

}
