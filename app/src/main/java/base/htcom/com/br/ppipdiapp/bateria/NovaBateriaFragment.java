package base.htcom.com.br.ppipdiapp.bateria;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.base.ActivityResult;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.base.Result;
import base.htcom.com.br.ppipdiapp.bll.BateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Bateria;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSTracker;

public class NovaBateriaFragment extends BaseFragment implements ActivityResult {
	public static NovaBateriaFragment newInstance(Bundle arguments){
		NovaBateriaFragment f = new NovaBateriaFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }
	
	private String ID;
	private EditText edtMarca;
	private EditText edtModelo;
	private EditText edtCapacidade;
	private EditText edtLocal;
	private Button btnFoto;
	private Button btnVisualizar;
	private Button btnGravar;
	private GPSTracker GPS;
	private String LATITUDE = null;
	private String LONGITUDE = null;
	private Bateria _bateria;
	private AlertDialog alerta;
	private String photoFile = new String();
	private String[] nomeArquivo = new String[3];
	@SuppressLint("SimpleDateFormat") private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	private String date = dateFormat.format(new Date());
	
	private ArrayList<EditText> lstValidade = new ArrayList<EditText>();
	private BateriaBLL bateriaBLL = new BateriaBLL();
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.form_bateria_fragment, container, false);
		try {
			ID = getArguments().getString(ListBateriaFragment._ID);

			findElementosView(view);
			if(!ID.equals("0")){
				carregarForm();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR ONCREATE FRAGMENT NovaBateria",getActivity());
		}
		setupBackButton();
		return view;
	}

	private void findElementosView(View view) {
		edtMarca = (EditText) view.findViewById(R.id.edtMarca);
		lstValidade.add(edtMarca);
		edtModelo = (EditText) view.findViewById(R.id.edtModelo);
		lstValidade.add(edtModelo);
		edtCapacidade = (EditText) view.findViewById(R.id.edtCapacidade);
		lstValidade.add(edtCapacidade);
		edtLocal = (EditText) view.findViewById(R.id.edtLocal);
		lstValidade.add(edtLocal);
		
		btnFoto = (Button) view.findViewById(R.id.btnFotoBat);
		btnFoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					btnFoto();
				}
				catch (Exception e) {
					LogErrorBLL.LogError(e.getMessage(), "ERROR BtnFoto",getActivity());
					e.printStackTrace();
				}
			}
		});
		btnVisualizar = (Button) view.findViewById(R.id.btnVisBat);
		btnVisualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnViewFoto();
			}
		});
		btnGravar = (Button) view.findViewById(R.id.btnGravarBat);
		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGravar();
			}
		});
		
	}

	private void carregarForm() throws Exception {
		try {
			Bateria bateria = bateriaBLL.listarById(getActivity(), Integer.valueOf(ID));
			if(bateria != null){
				edtMarca.setText(bateria.getMARCA());
				edtModelo.setText(bateria.getMODELO());
				edtCapacidade.setText(bateria.getCAPACIDADE());
				edtLocal.setText(bateria.getLOCAL());
			}
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	private void btnGravar() {
		try {
			Fragment fragment = null;
			if(validarEditTxt(lstValidade)){
				//=======CRIA��O ===========
				if(ID.equals("0")){
					ID = String.valueOf(bateriaBLL.Insert(getActivity(), preparar(ID)));
					if(Long.valueOf(ID) > 0){
						Toast.makeText(getActivity(), "Dados gravados com sucesso!", Toast.LENGTH_LONG).show();
						fragment = new ListBateriaFragment();
						fragmentTransaction(ListBateriaFragment.class.getSimpleName(), fragment, false, 1);
					}
					else {
						Toast.makeText(getActivity(),"Ocorreu um erro ao gravar o registro!",Toast.LENGTH_LONG).show();
					}
				}
				//=======EDICAO ===========
				else {
					if(bateriaBLL.Update(getActivity(), preparar(ID)) == 1){
						Toast.makeText(getActivity(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
						fragment = new ListBateriaFragment();
						fragmentTransaction(ListBateriaFragment.class.getSimpleName(), fragment, false, 1);
					}
					else {
						Toast.makeText(getActivity(),"Ocorreu um erro ao alterar o registro!",Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		catch (Exception e) {
			Log.e(getClass().getSimpleName(),"btnGravar",e);
			LogErrorBLL.LogError(e.getMessage(), "ERROR BtnGravar",getActivity());
			Toast.makeText(getActivity(),"Ocorreu um erro com o registro!",Toast.LENGTH_LONG).show();
		}
	}
	
	private Bateria preparar(String idLinha) throws Exception {
		_bateria = new Bateria();
		try {
			if(!idLinha.equals("0")){
				//=====EDICAO==========
				Bateria bateria = bateriaBLL.listarById(getActivity(), Integer.valueOf(idLinha));
				_bateria.setId(idLinha);
				_bateria.setORDEM(bateria.getORDEM());
			}
			else {
				_bateria.setORDEM(ordenador());
			}
			//===========NOVO===============
			_bateria.setOV_CHAMADO_NUM(OsMenuActitivity._OV_CHAMADO);
			_bateria.setMARCA(edtMarca.getText().toString());
			_bateria.setMODELO(edtModelo.getText().toString());
			_bateria.setCAPACIDADE(edtCapacidade.getText().toString());
			_bateria.setLOCAL(edtLocal.getText().toString());
		}
		catch (Exception e) {
			throw e;
		}
		return _bateria;
	}
	
	private void btnFoto(){
		try {
			if(validarEditTxt(lstValidade)){
				//=========VERIFICAR GPS==========
				GPS = new GPSTracker(getActivity());
				if(GPS.canGetLocation()){
		        	LATITUDE = GPS.getLatitude();
		        	LONGITUDE = GPS.getLongitude();
		        	startCamera(this);
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	GPS.showSettingsAlert();
		        }
				//=========VERIFICAR GPS==========
			}
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	private String gerarNomeFoto(Bateria bateria){
		nomeArquivo[0] = ("OV_"+bateria.getOV_CHAMADO_NUM()+"_"+OsMenuActitivity._OS.getCOD_ENTIDADE()+"_"+date.substring(8)+"_BATER_"+"21"+bateria.getORDEM()+"_E_"+bateria.getORDEM()+"_");
		nomeArquivo[1] = ("OV_"+bateria.getOV_CHAMADO_NUM()+"_"+OsMenuActitivity._OS.getCOD_ENTIDADE()+"_BATER_"+"21"+bateria.getORDEM()+"_E_"+bateria.getORDEM()+"_");
		nomeArquivo[2] = ("BATER_"+"21"+bateria.getORDEM()+"_E_"+bateria.getORDEM()+"_");
		return nomeArquivo[0];
	}
	
	private String ordenador(){
		int order = 1;
		try {
			List<Bateria> lst = bateriaBLL.listarByOvChamado(getActivity(), OsMenuActitivity._OV_CHAMADO);
			if(lst != null){
				order = lst.size()+order;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String format = String.valueOf(order);
		if(format.length() == 1){
			format = String.format("%03d", order);
		}
		else {
			format = String.format("%02d", order);
		}
		return format;
	}

	//===============VISUALIZAR FOTO =============================
		private void btnViewFoto() {
			try {
				if(!ID.equals("0")){
					
					Bateria bateria = bateriaBLL.listarById(getActivity(), Integer.valueOf(ID));
					if(bateria != null){
						ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(), "21"+bateria.getORDEM(), bateria.getOV_CHAMADO_NUM());
						if(controleUpload != null){
							dialogImg(controleUpload);
						}
						else {
							Toast.makeText(getActivity(), "Ainda não existe foto!", Toast.LENGTH_SHORT).show();
						}
					}
					else {
						Toast.makeText(getActivity(), "Ainda não existe foto!", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					Toast.makeText(getActivity(), "Ainda não existe foto!", Toast.LENGTH_SHORT).show();
				}
			}
			catch (Exception e) {
				LogErrorBLL.LogError(e.getMessage(), "ERROR BTN VISUALIZAR FOTO",getActivity());
			}
			
		}
		
		private void dialogImg(final ControleUpload controleUpload) {
			LayoutInflater li = getActivity().getLayoutInflater();
			
			View view = li.inflate(R.layout.dialog_img, null);
			File file = new File(getExternalFilesDir(),controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
		  	if (file.exists())
		  	{
				new BitmapFactory();
				Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
				ImageView img = (ImageView) view.findViewById(R.id.imgArqPref);
				img.setImageBitmap(bitmap);
			}
			view.findViewById(R.id.btOKImg).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					alerta.dismiss();
				}
			});
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Foto");
	        builder.setView(view);
	        alerta = builder.create();
	        alerta.show();
		}
		//===============VISUALIZAR FOTO =============================
	
	private ControleUpload prepararControleUpload(Bitmap bitmap, String linhaControleUp) {
		ControleUpload controleUpload = new ControleUpload();
		try {
			if(linhaControleUp != null){
				controleUpload.setLinha(linhaControleUp);
			}
			Os os = OsMenuActitivity._OS;

			controleUpload.setCODIGO("OV_"+_bateria.getOV_CHAMADO_NUM()+"_"+os.getCOD_ENTIDADE());
			controleUpload.setVALIDADE("T");
			controleUpload.setCLIENTE(os.getCLIENTE());
			controleUpload.setCONTRATO(os.getCONTRATO());
			controleUpload.setCONTRATO_FASE(os.getCONTRATO_FASE());
			controleUpload.setCOD_ENTIDADE(os.getCOD_ENTIDADE());
			controleUpload.setLOTE_CONTROLE(os.getLOTE_CONTROLE());
			controleUpload.setOV_CHAMADO_NUM(_bateria.getOV_CHAMADO_NUM());
			controleUpload.setAPROVADO("N");
			controleUpload.setArquivo_Carregado("21"+_bateria.getORDEM());
			controleUpload.setDocumento_Numero("OV");
			controleUpload.setDATA_DOC(dateFormat.format(new Date()));
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
				Toast.makeText(getActivity(), "Localização Recebida!", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(getActivity(), "Não foi possível receber a Localização!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return controleUpload;
	}

	@SuppressLint("NewApi") private boolean validarEditTxt(ArrayList<EditText> lstValidade){
		boolean validadeOk = true;
		for(int i=0; i < lstValidade.size();i++){
			if(lstValidade.get(i).getText().toString().isEmpty()){
				lstValidade.get(i).setError("Informe o valor para o campo!");
				validadeOk = false;
			}
			else{
				lstValidade.get(i).setError(null);
			}
		}
		return validadeOk;
	}

	@Override
	public void onActivityResult(Result result) {
		String msgDialog = getResources().getString(R.string.geral_RegistroNSalvo);
		try {
			if (result.getRequestCode() == getImageCapture() && result.getResultCode() == getActivity().RESULT_OK) {
				Bitmap finalBitmap = savePicture(gerarNomeFoto(preparar(ID)));
				if(finalBitmap != null){
					ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(), "21"+_bateria.getORDEM(), _bateria.getOV_CHAMADO_NUM());
					if(controleUpload != null){
						//JA EXISTE FOTO PARA O CAR, ENT�O DEVE EDITAR
//						FileUtills.deleteFile(getExternalFilesDir()+"/"+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
						controleUploadBLL.update(getActivity(), prepararControleUpload(finalBitmap, controleUpload.getLinha()));
					}
					else { //AINDA N�O EXISTE FOTO PARA O CAR, ENT�O DEVE CRIAR
						controleUploadBLL.insert(getActivity(), prepararControleUpload(finalBitmap, null));
					}
					msgDialog = getResources().getString(R.string.geral_RegistroSalvo);
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "onActivityResult",getActivity());
		}
		showDialog(msgDialog);
	}

	private void showDialog(String msgDialog) {
		new AlertaDialog(getActivity()).showDialogAviso(getResources().getString(R.string.geral_Atencao), msgDialog);
		new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog,int which) {

			}
		};
	}
}
