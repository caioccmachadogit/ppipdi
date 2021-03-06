package base.htcom.com.br.ppipdiapp.arq_pref;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.LayoutInflater;
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
import base.htcom.com.br.ppipdiapp.base.ActivityResult;
import base.htcom.com.br.ppipdiapp.base.BaseActivity;
import base.htcom.com.br.ppipdiapp.base.Result;
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
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.FileUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSTracker;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSUtills;

public class ArqPrefActivity extends BaseActivity implements ActivityResult {
	private ListView lv;
	private AlertDialog alerta;
	GPSUtills gpsUtills = new GPSUtills();
	@SuppressLint("SimpleDateFormat") private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
	private String date = dateFormat.format(new Date());
	private static String photoFile = new String();
	private static ArqPref arqPref;
	private static String[] nomeArquivo = new String[3];
	private static GPSTracker GPS;
	private static String LATITUDE = null;
	private static String LONGITUDE = null;
	private String _TIPOREL;
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	private AdapterArqPref adapterArqPref;
	private List<Combo> lst;
	private final String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_TIPOREL = getIntent().getStringExtra("_TIPOREL");
		setContentView(R.layout.activity_main);

		setmActivity(this);
		setTAG(getClass().getSimpleName());
		setTitleTela("Categoria - "+_TIPOREL);
		setUpToolbar();
		setupNavDrawer(MenuItemEnum.Arq_Pref, TipoMenu.OS);
		setupBackButton();
		replaceLinear(R.layout.list_arq_pref);

		TextView tit = findViewById(R.id.tvTitSite);
		tit.setText("Site: "+ OsMenuActitivity._OS.getCOD_ENTIDADE().replace("0", "")+ " | ETP: "+OsMenuActitivity._CODIGO);
		lv = findViewById(R.id.lv_arq_pref);
		lv.setItemsCanFocus(true);

		atualizarListViewArqs();
	}

	private void atualizarListViewArqs() {
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
			lst = new ArrayList<>();
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
				adapterArqPref = new AdapterArqPref(this, R.layout.lv_item_arq_pref, lst);
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
						startCamera(this);
			        }else{
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	GPS.showSettingsAlert();
			        }
					//=========VERIFICAR GPS==========
				}
				else {
					Toast.makeText(ArqPrefActivity.this, "Somente para marcação OK!", Toast.LENGTH_SHORT).show();
				}
			}
			else {
				Toast.makeText(ArqPrefActivity.this, "Somente para marcação OK!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR IMGBTNCapturar",this);
			e.printStackTrace();
		}
	}

	private String gerarNomeFoto(ArqPref arqPref){
		Random random = new Random();
		String num = String.valueOf(random.nextInt(100000));
		nomeArquivo[0] = (arqPref.getCODIGO()+"_"+date.substring(0, 6)+"_PREFE_"+arqPref.getArquivo_Carregado()+"_D_"+"mobipref_"+num).replace(".", "_");
		nomeArquivo[1] = (arqPref.getCODIGO()+"_PREFE_"+arqPref.getArquivo_Carregado()+"_D_"+"mobipref_"+num).replace(".", "_");
		nomeArquivo[2] = ("PREFE_"+arqPref.getArquivo_Carregado()+"_D_"+"mobipref_"+num).replace(".", "_");
		Log.d(TAG,"gerarNomeFoto->"+nomeArquivo[0]);
		return nomeArquivo[0];
	}
	
	private ControleUpload prepararControleUpload(Bitmap bitmap, String linhaControleUp) {
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
				Toast.makeText(this, "Localização Recebida!", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(this, "Não foi possível receber a Localização!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
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
				dialogObs(Pref);
			}
			else {
				Toast.makeText(ArqPrefActivity.this, "Marque uma Opção!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR IMGBTNObs",this);
			e.printStackTrace();
		}
	}
	
	private void dialogObs(final ArqPref arqPref) {
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
						Toast.makeText(ArqPrefActivity.this, "Observação Inserida!", Toast.LENGTH_SHORT).show();
					}
					else {
						Toast.makeText(ArqPrefActivity.this, "Problemas ao inserir Observação!", Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e) {
					LogErrorBLL.LogError(e.getMessage(), "ERROR ao inserir Observação",ArqPrefActivity.this);
					alerta.dismiss();
					e.printStackTrace();
				}
				alerta.dismiss();
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Observação");
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
						dialogImg(controleUpload);
					}
					else {
						Toast.makeText(ArqPrefActivity.this, "Ainda não existe foto!", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					Toast.makeText(ArqPrefActivity.this, "Somente para marcação OK!", Toast.LENGTH_SHORT).show();
				}
			}
			else {
				Toast.makeText(ArqPrefActivity.this, "Somente para marcação OK!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR IMGBTNCapturar",this);
			e.printStackTrace();
		}
	}
	
	private void dialogImg(final ControleUpload controleUpload) {
		LayoutInflater li = getLayoutInflater();
		
		View view = li.inflate(R.layout.dialog_img, null);
		File file = new File(externalFilesDir+"/"+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
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
			inserirMarcacao(linhaCombo, "OK", null);
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR INSERIR RABDIOBUTTON",ArqPrefActivity.this);
			e.printStackTrace();
		}
	}

	public void rBtn_nt(View v) {
		String linhaCombo = (String) v.getTag();
		try {
			inserirMarcacao(linhaCombo, "NT", null);
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR INSERIR RABDIOBUTTON",ArqPrefActivity.this);
			e.printStackTrace();
		}
	}

	public void rBtn_na(View v) {
		String linhaCombo = (String) v.getTag();
		try {
			inserirMarcacao(linhaCombo, "NA", null);
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR INSERIR RABDIOBUTTON",ArqPrefActivity.this);
			e.printStackTrace();
		}
	}
	
	private void inserirMarcacao(String linhaCombo, String tipo, String obs) throws Exception{
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

	@Override
	public void onActivityResult(Result result) {
		String msgDialog = getResources().getString(R.string.geral_RegistroNSalvo);
		try {
			if (result.getRequestCode() == IMAGE_CAPTURE && result.getResultCode() == this.RESULT_OK) {
				Bitmap finalBitmap = savePicture(gerarNomeFoto(arqPref));
				if(finalBitmap != null){
					ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(this, arqPref.getArquivo_Carregado(), arqPref.getOV_CHAMADO_NUM());
					if(controleUpload != null){
						//JA EXISTE FOTO PARA O ARQPREF, ENT�O DEVE EDITAR
						FileUtills.deleteFile(externalFilesDir+"/"+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
						controleUploadBLL.update(this, prepararControleUpload(finalBitmap, controleUpload.getLinha()));
					}
					else { //AINDA N�O EXISTE FOTO PARA O ARQPREF, ENT�O DEVE CRIAR
						controleUploadBLL.insert(this, prepararControleUpload(finalBitmap, null));
					}
					msgDialog = getResources().getString(R.string.geral_RegistroSalvo);
					refreshAdapter();
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "onActivityResult",this);
		}
		showDialog(msgDialog);
	}

	@MainThread
	private void refreshAdapter(){
		adapterArqPref.notifyDataSetChanged();
	}

	private void showDialog(String msgDialog) {
		new AlertaDialog(this).showDialogAviso(getResources().getString(R.string.geral_Atencao), msgDialog);
		new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog,int which) {
				finish();
			}
		};
	}
}
