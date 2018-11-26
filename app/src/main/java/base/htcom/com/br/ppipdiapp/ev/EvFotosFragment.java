package base.htcom.com.br.ppipdiapp.ev;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterEvFotos;
import base.htcom.com.br.ppipdiapp.base.ActivityResult;
import base.htcom.com.br.ppipdiapp.base.BaseListFragment;
import base.htcom.com.br.ppipdiapp.base.Result;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.FileUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSTracker;

public class EvFotosFragment extends BaseListFragment implements OnMenuItemClickListener, ActivityResult {

	private List<Combo> lst;
	private String _ID = "0";
	private GPSTracker GPS;
	private String LATITUDE = null;
	private String LONGITUDE = null;
	private String photoFile = new String();
	private String[] nomeArquivo = new String[3];
	private AlertDialog alerta;
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	private AdapterEvFotos adapterEvFotos;
	//============OBJS VIEW ===========================================

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_ev_fotos, container, false);
		Carregamento EV;
		try {
			EV = carregamentoBLL.listarEv(getActivity(), OsMenuActitivity._OV_CHAMADO, "1A000");
			if(EV != null){
				TextView tvTipoSite = (TextView) rootView.findViewById(R.id.tvTipoSite);
				tvTipoSite.setText("Tipo de Site: "+ EV.getINFO_41());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			atualizarListView();
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR ONCREATE FRAGMENT EV Fotos",getActivity());
		}
	}
	
	 @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	        // remove the dividers from the ListView of the ListFragment
	        getListView().setDivider(null);
	    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.d("FragmentList", "Item clicked: " + id);
		ImageView imgAction = (ImageView) v.findViewById(R.id.img_acao);
		 _ID = (String) imgAction.getTag();
		showMenu(v);
	}
	
	public void showMenu(View v) {
	    PopupMenu popup = new PopupMenu(getActivity(), v);
	    // This activity implements OnMenuItemClickListener
	    popup.setOnMenuItemClickListener(this);
	    popup.inflate(R.menu.menu_camera);
	 //========== Force icons to show========
		Object menuHelper;
		Class[] argTypes;
		try {
			Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
			fMenuHelper.setAccessible(true);
			menuHelper = fMenuHelper.get(popup);
			argTypes = new Class[] { boolean.class };
			menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
		} catch (Exception e) {
			Log.d("Error", "error forcing menu icons to show", e);
			popup.show();
			return;
		}
		//========== Force icons to show========
	    popup.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		boolean retorno = false;
		try {
			switch (item.getItemId()) {
	        case R.id.nova_foto:
	        	//=========VERIFICAR GPS==========
				GPS = new GPSTracker(getActivity());
				if(GPS.canGetLocation()){
		        	LATITUDE = GPS.getLatitude();
		        	LONGITUDE = GPS.getLongitude();
		        	btnCapturar();
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	GPS.showSettingsAlert();
		        }
				//=========VERIFICAR GPS==========
	            retorno = true;
	            break;
	        case R.id.visu_foto:
	            btnImg();
	        	retorno = true;
	        	break;
	        default:
	        	break;
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "onMenuItemClick", getActivity());
		}
	    return retorno;
	}
	
	private void atualizarListView() {
		try {
			lst = new ArrayList<>();
			Combo foto1 = new Combo();
			foto1.setTITULO("1) Frente Site com EV");
			foto1.setLINHA("22001");
			foto1.setFoto(checkFoto(foto1.getLINHA()));
			lst.add(foto1);

			Combo foto2 = new Combo();
			foto2.setTITULO("2) EV Face 1");
			foto2.setLINHA("22002");
			foto2.setFoto(checkFoto(foto2.getLINHA()));
			lst.add(foto2);

			Combo foto3 = new Combo();
			foto3.setTITULO("3) EV Face 2");
			foto3.setLINHA("22003");
			foto3.setFoto(checkFoto(foto3.getLINHA()));
			lst.add(foto3);

			Combo foto4 = new Combo();
			foto4.setTITULO("4) EV Face 3");
			foto4.setLINHA("22004");
			foto4.setFoto(checkFoto(foto4.getLINHA()));
			lst.add(foto4);

			Combo foto5 = new Combo();
			foto5.setTITULO("5) EV Face 4");
			foto5.setLINHA("22005");
			foto5.setFoto(checkFoto(foto5.getLINHA()));
			lst.add(foto5);

			Combo foto6 = new Combo();
			foto6.setTITULO("6) EV Aterramento");
			foto6.setLINHA("22006");
			foto6.setFoto(checkFoto(foto6.getLINHA()));
			lst.add(foto6);

			Combo foto7 = new Combo();
			foto7.setTITULO("7) Placa identificação EV");
			foto7.setLINHA("22011");
			foto7.setFoto(checkFoto(foto7.getLINHA()));
			lst.add(foto7);

			Combo foto8 = new Combo();
			foto8.setTITULO("8) EV Esteira Vertical Foto 1");
			foto8.setLINHA("22012");
			foto8.setFoto(checkFoto(foto8.getLINHA()));
			lst.add(foto8);

			Combo foto9 = new Combo();
			foto9.setTITULO("9) EV Esteira Vertical Foto 2");
			foto9.setLINHA("22013");
			foto9.setFoto(checkFoto(foto9.getLINHA()));
			lst.add(foto9);

			Combo foto10 = new Combo();
			foto10.setTITULO("10) EV Esteira Horizontal Foto 1");
			foto10.setLINHA("22014");
			foto10.setFoto(checkFoto(foto10.getLINHA()));
			lst.add(foto10);

			Combo foto11 = new Combo();
			foto11.setTITULO("11) EV Esteira Horizontal Foto 2");
			foto11.setLINHA("22015");
			foto11.setFoto(checkFoto(foto11.getLINHA()));
			lst.add(foto11);

			Combo foto12 = new Combo();
			foto12.setTITULO("12) Placa Passagem Cabos");
			foto12.setLINHA("22016");
			foto12.setFoto(checkFoto(foto12.getLINHA()));
			lst.add(foto12);

			adapterEvFotos = new AdapterEvFotos(getActivity(), R.layout.lv_item_ev_fotos, lst);
			setListAdapter(adapterEvFotos);
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List Ev Fotos", getActivity());
		}
	}

	private boolean checkFoto(String linha) {
		ControleUpload controleUpload = null;
		try {
			controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(),linha , OsMenuActitivity._OV_CHAMADO);
			if(controleUpload != null)
				return true;
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}

	private void btnImg(){
		try {
			ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(), _ID, OsMenuActitivity._OV_CHAMADO);
			if(controleUpload != null){
				dialogImg(controleUpload);
			}
			else {
				Toast.makeText(getActivity(), "Ainda não existe foto!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "BtnImg", getActivity());
		}
	}
	
	private void dialogImg(final ControleUpload controleUpload) {
		LayoutInflater li = getActivity().getLayoutInflater();
		
		View view = li.inflate(R.layout.dialog_img, null);
		File file = new File(getExternalFilesDir(), controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
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
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Foto");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
	}
	
	private void btnCapturar(){
		startCamera(this);
	}

	private String gerarNomeFoto() {
		Random random = new Random();
		String num = String.valueOf(random.nextInt(1000000));
		String nome1 = ("OV_"+OsMenuActitivity._OV_CHAMADO+"."+OsMenuActitivity._OS.getCOD_ENTIDADE()+"."+num+".").replace(".", "_");
		String nome2 = ("OV_"+OsMenuActitivity._OV_CHAMADO+"."+OsMenuActitivity._OS.getCOD_ENTIDADE()+".").replace(".", "_");
		int id = Integer.parseInt(_ID);
		String nome;
		switch (id) {
		case 22001:
			nome = "ESTRU_220011_D_frente_site_c_ev";
			break;
		case 22002:
			nome = "ESTRU_220021_D_EV_face_1";
			break;
		case 22003:
			nome = "ESTRU_220031_D_EV_face_2";
			break;
		case 22004:
			nome = "ESTRU_220041_D_EV_face_3";
			break;
		case 22005:
			nome = "ESTRU_220051_D_EV_face_4";
			break;
		case 22006:
			nome = "ESTRU_220061_D_EV_aterramento";
			break;
		case 22011:
			nome = "ESTRU_220111_D_placa_id_ev";
			break;
		case 22012:
			nome = "ESTRU_220121_D_EV_esteira_vert_foto_1";
			break;
		case 22013:
			nome = "ESTRU_220131_D_EV_esteira_vert_foto_2";
			break;
		case 22014:
			nome = "ESTRU_220141_D_EV_esteira_horiz_foto_1";
			break;
		case 22015:
			nome = "ESTRU_220151_D_EV_esteira_horiz_foto_2";
			break;
		case 22016:
			nome = "ESTRU_220161_D_placa_passag_cabos";
			break;
		default:
			nome = "nao_encontrado";
			break;
		}
		nomeArquivo[0] = nome1 + nome;
		nomeArquivo[1] = nome2 + nome;
		nomeArquivo[2] = nome;
		return nomeArquivo[0];
	}

	private ControleUpload prepararControleUpload(Bitmap bitmap, String linhaControleUp) {
		ControleUpload controleUpload = new ControleUpload();
		try {
			if(linhaControleUp != null){
				controleUpload.setLinha(linhaControleUp);
			}
			
			controleUpload.setCODIGO("OV_"+OsMenuActitivity._OV_CHAMADO+"_"+OsMenuActitivity._OS.getCOD_ENTIDADE());
			controleUpload.setCOD_ENTIDADE(OsMenuActitivity._OS.getCOD_ENTIDADE());
			controleUpload.setVALIDADE("T");
			controleUpload.setCLIENTE(OsMenuActitivity._OS.getCLIENTE());
			controleUpload.setCONTRATO(OsMenuActitivity._OS.getCONTRATO());
			controleUpload.setCONTRATO_FASE(OsMenuActitivity._OS.getCONTRATO_FASE());
			controleUpload.setLOTE_CONTROLE(OsMenuActitivity._OS.getLOTE_CONTROLE());
			controleUpload.setOV_CHAMADO_NUM(OsMenuActitivity._OS.getOV_CHAMADO_NUM());
			controleUpload.setAPROVADO("N");
			controleUpload.setArquivo_Carregado(_ID);
			controleUpload.setDocumento_Numero("OV");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			controleUpload.setDATA_DOC(dateFormat.format(new Date()));
			dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			controleUpload.setDATA_DOC_INV(dateFormat.format(new Date()));
			controleUpload.setGRUPO(OsMenuActitivity._OS.getSOLIC_VISTORIA_EMPRESA_COD());
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
			controleUpload.setINSERCAO_NOME(OsMenuActitivity._OS.getSOLIC_OV_SERV_NOME());
			
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

	private void showDialog(String msg){
		new AlertaDialog(getActivity()).showDialogAviso(getResources().getString(R.string.geral_Atencao), msg);
		new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog,int which) {
				getActivity().finish();
			}
		};
	}

	@Override
	public void onActivityResult(Result result) {
		String msgDialog = getResources().getString(R.string.geral_RegistroNSalvo);
		try {
			if (result.getRequestCode() == getImageCapture() && result.getResultCode() == getActivity().RESULT_OK) {
				Bitmap finalBitmap = savePicture(gerarNomeFoto());
				if(finalBitmap != null){
					ControleUpload controleUpload;
					controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(), _ID, OsMenuActitivity._OV_CHAMADO);
					if(controleUpload != null){
						//JA EXISTE FOTO PARA O ARQPREF, ENT�O DEVE EDITAR
						FileUtills.deleteFile(getExternalFilesDir()+"/"+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
						controleUploadBLL.update(getActivity(), prepararControleUpload(finalBitmap, controleUpload.getLinha()));
					}
					else { //AINDA N�O EXISTE FOTO PARA O ARQPREF, ENT�O DEVE CRIAR
						controleUploadBLL.insert(getActivity(), prepararControleUpload(finalBitmap, null));
					}
					msgDialog = getResources().getString(R.string.geral_RegistroSalvo);

					refreshAdapter();
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "onActivityResult",getActivity());
		}
		showDialog(msgDialog);
	}

	@MainThread
    private void refreshAdapter() {
        setCheckFoto();
        adapterEvFotos.notifyDataSetChanged();
    }

    private void setCheckFoto() {
		for (Combo c:lst) {
			if(c.getLINHA().equals(_ID)){
				c.setFoto(true);
				break;
			}
		}
	}
}
