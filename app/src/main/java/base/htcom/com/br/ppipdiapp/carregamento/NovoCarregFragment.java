package base.htcom.com.br.ppipdiapp.carregamento;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoPlantaBLL;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.InsumosBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.CarregamentoPlanta;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Insumos;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.BitmapUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.CriarDirExterno;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSTracker;
import base.htcom.com.br.ppipdiapp.padrao.utils.GPSUtills;

public class NovoCarregFragment extends BaseFragment implements OnMenuItemClickListener{
	
	public static NovoCarregFragment newInstance(Bundle arguments){
		NovoCarregFragment f = new NovoCarregFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

	public static String tipoCarregamento;
	private String IDCAR;
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	private CarregamentoPlantaBLL carregamentoPlantaBLL = new CarregamentoPlantaBLL();
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	GPSUtills gpsUtills = new GPSUtills();
	private InsumosBLL insumosBLL;
	private ComboBLL comboBLL;
	private AlertDialog alerta;
	private GPSTracker GPS;
	private String LATITUDE = null;
	private String LONGITUDE = null;
	private Carregamento EV;
	private boolean checkedRbPlatSim = false;
	private boolean checkedRbPlatNao = false;
	private boolean checkedRbSecRet = false;
	private boolean checkedRbSecInc = false;
	private boolean checkedRbRefSim = false;
	private boolean checkedRbRefNao = false;
	private int camposF = 0;
	private int camposM = 0;
	private String IMG_DT = "";
	private String IMG_DM = "";
	private String CaptImg = "0";
	
	ArrayList<EditText> lstValidade = new ArrayList<EditText>();
	//==========SPINNER================================================
	private String[] comboFuncoes = new String[] { "MW", "RF", "ODU", "RRU", "GPS", "N/E" };
	private String[] comboFaceCanto = new String[] {"FACE-A",
			"FACE-B", "FACE-C", "FACE-D", "CANTO-A-B", "CANTO-B-C",
			"CANTO-C-D", "CANTO-C-A", "CANTO-D-A", "MASTRO-01", "MASTRO-02",
			"MASTRO-03", "MASTRO-04", "MASTRO-05", "MASTRO-06", "MASTRO-07",
			"MASTRO-08", "MASTRO-09", "MASTRO-10" };

	private String[] comboOperadoras = new String[] {"CLARO",
			"EMBRATEL", "NEXTEL", "OI", "QUATTRO", "TELEBRAS", "TELEFONICA",
			"TIM", "TRANSIT", "VIVO", "OUTRO N ESPEC", "N IDENTIFICADO" };

	private List<Insumos> lstInsumos;
	private String[] comboModAntena;
	
	private Spinner spinnerModAntena;
	private Spinner spinnerFuncoes;
	private Spinner spinnerFaceCanto;
	private Spinner spinnerOperadoras;
	private String modAntena;
	private String tipoElemento;
	private String funcoes;
	private String faceCanto;
	private String operadora;
	//==========SPINNER================================================
	
	//==========ELEMENTOSVIEW===============================================
	private RadioGroup rgAcao;
	private RadioButton rbExistente;
	private RadioButton rbRetirada;
	private RadioButton rbNova;
	private EditText edtEtiqueta;
	private CheckBox ckAtiva;
	private EditText edtPolariz;
	private EditText edtFreq;
	private EditText edtElemDim;
	private EditText edtElemDim2;
	private EditText edtElemPeso;
	private EditText edtAEV;
	private EditText edtCA;
	private EditText edtFT;
	private EditText edtAEVt;
	private EditText edtAltAzi;
	private EditText edtAZI;
	private EditText edtAltTilt;
	private EditText edtSupPeso;
	private EditText edtTipoFix;
	//================IMG EV
	private RadioGroup rgPlatTopo;
	private boolean rgPlatTopoEnabled = true;
	private RadioButton rbPlatSim;
	private RadioButton rbPlatNao;
	private ImageView ivDt;
	private ImageView ivDm;
	private RadioGroup rgSecao;
	private boolean rgSecaoEnabled = true;
	private RadioButton rbSecReta;
	private RadioButton rbSecInc;
	private RadioGroup rgReforco;
	private boolean rgReforcoEnabled = true;
	private RadioButton rbRefSim;
	private RadioButton rbRefNao;
	private EditText edtFa;
	private TextView tvFa;
	private EditText edtFb;
	private TextView tvFb;
	private EditText edtFc;
	private TextView tvFc;
	private EditText edtFd;
	private TextView tvFd;
	private EditText edtFe;
	private TextView tvFe;
	private EditText edtMa;
	private TextView tvMa;
	private EditText edtMb;
	private TextView tvMb;
	private EditText edtMc;
	private TextView tvMc;
	private EditText edtMd;
	private TextView tvMd;
	private EditText edtMe;
	private TextView tvMe;
	private EditText edtMf;
	private TextView tvMf;
	private EditText edtMg;
	private TextView tvMg;
	private EditText edtMh;
	private TextView tvMh;
	private EditText edtMi;
	private TextView tvMi;
	//================IMG EV
	private EditText edtCaboDim;
	private EditText edtCAx;
	private EditText edtQtd2;
	private EditText edtLocalizacaoEquipamento;
	private EditText edtObservacao;
	
	private Button btnFotoA;
	private Button btnFotoC;
	private Button btnFotoP;
	private Button btnFotoS;
	private Button btnGravar;
	//==========ELEMENTOSVIEW===============================================
	private Carregamento carregamento;
	private CarregamentoPlanta carregamentoPlanta = new CarregamentoPlanta();
	private String idCarregamentoRefPlanta = "0";
	private Carregamento _carregamentoRefPlanta = null;
	//==========CAPTURARFOTO===============================================
	private String photoFile = new String();
	CriarDirExterno criarDirExterno = new CriarDirExterno();
	private File file;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 1777;
	private String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PPI_PDI/Fotos/";
	private String[] nomeArquivo = new String[3];
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
	private String date = dateFormat.format(new Date());
	//==========CAPTURARFOTO===============================================
	//==========CALCULAR AEV==============================================
	String AEV = "";
	String AEVt = "";
	String CA = "";
	String AEV_FATOR = "";
	//==========CALCULAR AEV==============================================
	private final String TAG = NovoCarregFragment.class.getSimpleName();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.form_carreg_fragment, null);
		try {
			IDCAR = getArguments().getString(ListCarregFragment._ID);
			EV = carregamentoBLL.listarEv(getActivity(), OsMenuActitivity._OV_CHAMADO, "1A000");
			TextView tvTipoSite = (TextView) view.findViewById(R.id.tvTipoSite);
			tvTipoSite.setText("Tipo de Site: "+ EV.getINFO_41());
			
			//=======POPULAR SPINNER==========================================
			spinnerModAntena = (Spinner) view.findViewById(R.id.spinnerModAntena);
			spinnerFuncoes = (Spinner) view.findViewById(R.id.spinnerFuncoes);
			spinnerFaceCanto = (Spinner) view.findViewById(R.id.spinnerFaceCanto);
			spinnerOperadoras = (Spinner) view.findViewById(R.id.spinnerOperadora);
			PopularSpinners();
			//=======POPULAR SPINNER==========================================
			
			FindElementosView(view);
			DesabilitarRbConfigImagens();
			
			if(!IDCAR.equals("0")){
				CarregarForm();
			}
		}
		catch (Exception e) {
			Log.e(TAG, "ERROR ONCREATE FRAGMENT NOVOCARREGAMENTO",e);
			LogErrorBLL.LogError(e.getMessage(), "ERROR ONCREATE FRAGMENT NOVOCARREGAMENTO",getActivity());
		}
		setupBackButton();
		return view;
	}
	
	private void PopularSpinners() throws Exception {	
		insumosBLL = new InsumosBLL();
		lstInsumos = insumosBLL.listarAntena(getActivity());
		comboModAntena = new String [lstInsumos.size()];
		for(int i=0; i < lstInsumos.size();i++){
			comboModAntena[i] = lstInsumos.get(i).getINFO_02();
		}
		spinnerModAntena.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, comboModAntena));
		spinnerFuncoes.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, comboFuncoes));
		spinnerFaceCanto.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, comboFaceCanto));
		spinnerOperadoras.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, comboOperadoras));
		
		spinnerFaceCanto.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				faceCanto = spinnerFaceCanto.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		spinnerModAntena.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				
				modAntena = spinnerModAntena.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		spinnerFuncoes.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				funcoes = spinnerFuncoes.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		spinnerOperadoras.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				operadora = spinnerOperadoras.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}
	
	private void CarregarForm() throws Exception {
		if (tipoCarregamento.equals("NOVA")) {
			Carregamento carregamento = carregamentoBLL.listarById(getActivity(), IDCAR);
			
			if(carregamento.getINFO_17() != null){
				if(carregamento.getINFO_17().equals("e")){
					rbExistente.setChecked(true);
				}
				else if(carregamento.getINFO_17().equals("r")){
					rbRetirada.setChecked(true);
				}
				else if(carregamento.getINFO_17().equals("n")){
					rbNova.setChecked(true);
				}
			}
			edtEtiqueta.setText(carregamento.getINFO_20());
			if(carregamento.getINFO_19() != null){
				if(carregamento.getINFO_19().equals("S")){
					ckAtiva.setChecked(true);
				}
			}
			for (int i = 0; i < comboOperadoras.length; i++) {
				if (carregamento.getINFO_21() != null) {
					if (carregamento.getINFO_21().equals(comboOperadoras[i])){
						spinnerOperadoras.setSelection(i);
					}
				}
			}
			for (int i = 0; i < lstInsumos.size(); i++) {
				if (carregamento.getINFO_04() != null) {
					if (carregamento.getINFO_04().equals(lstInsumos.get(i).getINFO_02())){
						spinnerModAntena.setSelection(i);
					}
				}
			}
			for (int i = 0; i < comboFuncoes.length; i++) {
				if (carregamento.getINFO_18() != null) {
					if (carregamento.getINFO_18().equals(comboFuncoes[i])){
						spinnerFuncoes.setSelection(i);
					}
				}
			}
			edtPolariz.setText(carregamento.getINFO_15());
			edtFreq.setText(carregamento.getINFO_16());
			edtElemDim.setText(carregamento.getINFO_09().replace(",", "."));
			edtElemDim2.setText(carregamento.getINFO_10());
			edtElemPeso.setText(carregamento.getINFO_27());
			//==========AEV============================
			edtAEV.setText(carregamento.getINFO_12());
			edtCA.setText(carregamento.getINFO_13());
			edtFT.setText(carregamento.getINFO_26());
			edtAEVt.setText(carregamento.getINFO_14());
			//==========AEV============================
			edtAltAzi.setText(carregamento.getINFO_06());
			edtAZI.setText(carregamento.getINFO_05());
			edtAltTilt.setText(carregamento.getINFO_07());
			for (int i = 0; i < comboFaceCanto.length; i++) {
				if (carregamento.getINFO_02() != null) {
					if (carregamento.getINFO_02().equals(comboFaceCanto[i])){
						spinnerFaceCanto.setSelection(i);
					}
				}
			}
			edtTipoFix.setText(carregamento.getINFO_28());
			edtSupPeso.setText(carregamento.getINFO_29());
			//=======FIGURA RELACIONADA EV==================
			//RBPLATAFORMA
			if(carregamento.getINFO_49() != null && EV.getINFO_46() != null){
				if(carregamento.getINFO_49().equals("S") && EV.getINFO_46().equals("S")){
					rbPlatSim.setChecked(true);
					checkedRbPlatSim = true;
					
					rbSecInc.setEnabled(false);
					rbSecReta.setEnabled(false);
					rbRefSim.setEnabled(false);
					rbRefNao.setEnabled(false);
				}
				else if(carregamento.getINFO_49().equals("N") && EV.getINFO_46().equals("S")){
					rbPlatNao.setChecked(true);
					checkedRbPlatNao = true;
				}
			}
			//RBSECAO
			if(carregamento.getINFO_42() != null && EV.getINFO_42() != null && EV.getINFO_43() != null){
				if(carregamento.getINFO_42().equals("I") && EV.getINFO_42().equals("S") && EV.getINFO_43().equals("S")){
					rbSecInc.setChecked(true);
					checkedRbSecInc = true;
				}
				else if(carregamento.getINFO_42().equals("R") && EV.getINFO_42().equals("S") && EV.getINFO_43().equals("S")){
					rbSecReta.setChecked(true);
					checkedRbSecRet = true;
				}
			}
			//RBREFORCO
			if(carregamento.getINFO_45() != null && EV.getINFO_45() != null){
				if(carregamento.getINFO_45().equals("S") && EV.getINFO_45().equals("S")){
					rbRefSim.setChecked(true);
					checkedRbRefSim = true;
				}
				else if(carregamento.getINFO_45().equals("N") && EV.getINFO_45().equals("S")){
					rbRefNao.setChecked(true);
					checkedRbRefNao = true;
				}
			}
			EventConfigImagens();
			CarregarCamposDim(carregamento);
			//=======FIGURA RELACIONADA EV==================
			
			edtCaboDim.setText(carregamento.getINFO_23());
			edtCAx.setText(carregamento.getINFO_24());
			edtQtd2.setText(carregamento.getINFO_25());
			edtLocalizacaoEquipamento.setText(carregamento.getINFO_08());
			edtObservacao.setText(carregamento.getINFO_22());
		}

	}
	
	private void CarregarCamposDim(Carregamento carregamento) {
		switch (camposF) {
		case 3:
			edtFa.setText(carregamento.getINFO_41());
			edtFb.setText(carregamento.getINFO_43());
			edtFc.setText(carregamento.getINFO_44());
			break;
		case 4:
			edtFa.setText(carregamento.getINFO_41());
			edtFb.setText(carregamento.getINFO_43());
			edtFc.setText(carregamento.getINFO_44());
			edtFd.setText(carregamento.getINFO_46());
			break;
		case 5:
			edtFa.setText(carregamento.getINFO_41());
			edtFb.setText(carregamento.getINFO_43());
			edtFc.setText(carregamento.getINFO_44());
			edtFd.setText(carregamento.getINFO_46());
			edtFe.setText(carregamento.getINFO_48());
			break;
		default:
			break;
		}
		switch (camposM) {
		case 3:
			edtMa.setText(carregamento.getINFO_31());
			edtMb.setText(carregamento.getINFO_32());
			edtMc.setText(carregamento.getINFO_33());
			break;
		case 4:
			edtMa.setText(carregamento.getINFO_31());
			edtMb.setText(carregamento.getINFO_32());
			edtMc.setText(carregamento.getINFO_33());
			edtMd.setText(carregamento.getINFO_34());
			break;
		case 6:
			edtMa.setText(carregamento.getINFO_31());
			edtMb.setText(carregamento.getINFO_32());
			edtMc.setText(carregamento.getINFO_33());
			edtMd.setText(carregamento.getINFO_34());
			edtMe.setText(carregamento.getINFO_35());
			edtMf.setText(carregamento.getINFO_36());
			break;
		case 8:
			edtMa.setText(carregamento.getINFO_31());
			edtMb.setText(carregamento.getINFO_32());
			edtMc.setText(carregamento.getINFO_33());
			edtMd.setText(carregamento.getINFO_34());
			edtMe.setText(carregamento.getINFO_35());
			edtMf.setText(carregamento.getINFO_36());
			edtMg.setText(carregamento.getINFO_37());
			edtMh.setText(carregamento.getINFO_38());
			break;
		case 9:
			edtMa.setText(carregamento.getINFO_31());
			edtMb.setText(carregamento.getINFO_32());
			edtMc.setText(carregamento.getINFO_33());
			edtMd.setText(carregamento.getINFO_34());
			edtMe.setText(carregamento.getINFO_35());
			edtMf.setText(carregamento.getINFO_36());
			edtMg.setText(carregamento.getINFO_37());
			edtMh.setText(carregamento.getINFO_38());
			edtMi.setText(carregamento.getINFO_39());
			break;
		default:
			break;
		}
	}

	private void BtnGravar(boolean retornar) {
		try {
			if(validarEditTxt(lstValidade)){
				//=======FAZER CALCULO DE AEV=============
				requisitarCalcAEV();
				//=======FAZER CALCULO DE AEV=============
				
				if(tipoCarregamento.equals("NOVA")){
					//=======CRIA��O CARREGAMENTO===========
					if(IDCAR.equals("0")){
						carregamentoBLL = new CarregamentoBLL();
						IDCAR = String.valueOf(carregamentoBLL.Insert(getActivity(), PrepararCarregamento(IDCAR)));
						if(Long.valueOf(IDCAR) > 0){
							if(retornar){
								Toast.makeText(getActivity(), "Dados gravados com sucesso!", Toast.LENGTH_LONG).show();
								fragmentTransaction(ListCarregFragment.class.getSimpleName(), new ListCarregFragment(), false, 1);
							}
						}
						else {
							Toast.makeText(getActivity(),"Ocorreu um erro ao gravar essa antena!",Toast.LENGTH_LONG).show();
						}
					}
					//=======CRIA��O CARREGAMENTO===========
					
					//=======EDICAO CARREGAMENTO===========
					else {
						if(carregamentoBLL.update(getActivity(), PrepararCarregamento(IDCAR)) == 1){
							if(retornar){
								Toast.makeText(getActivity(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
								fragmentTransaction(ListCarregFragment.class.getSimpleName(), new ListCarregFragment(), false, 1);
							}
						}
						else {
							Toast.makeText(getActivity(),"Ocorreu um erro ao alterar essa antena!",Toast.LENGTH_LONG).show();
						}
					}
					//=======EDICAO CARREGAMENTO===========
				}
				else if (tipoCarregamento.equals("PLANTA")) {
					//VERIFICAR SE J� EXISTE REGISTRO NO CARREGAMENTO REFERENTE A PLANTA
					if(_carregamentoRefPlanta != null){
						//EXISTE CARREGAMENTO PARA O PLANTA - EDITAR
						if(carregamentoBLL.update(getActivity(), PrepararCarregamento(IDCAR)) == 1){
							if(retornar){
								Toast.makeText(getActivity(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
								fragmentTransaction(ListCarregPlantaFragment.class.getSimpleName(), new ListCarregPlantaFragment(), false, 1);
							}
						}
						else {
							Toast.makeText(getActivity(),"Ocorreu um erro ao alterar essa antena!",Toast.LENGTH_LONG).show();
						}
					}
					else {
						//� EXISTE CARREGAMENTO PARA O PLANTA - CRIAR
						carregamentoBLL = new CarregamentoBLL();
						long idCar = carregamentoBLL.Insert(getActivity(), PrepararCarregamento(IDCAR));
						if(idCar > 0){
							//REGISTRAR ID DO NOVO CARREGAMENTO NA TAB PLANTA
							carregamentoPlanta.setINFO_50(String.valueOf(idCar));
							if(carregamentoPlantaBLL.update(getActivity(), carregamentoPlanta) == 1){
								if(retornar){
									Toast.makeText(getActivity(), "Dados gravados com sucesso!", Toast.LENGTH_LONG).show();
									fragmentTransaction(ListCarregPlantaFragment.class.getSimpleName(), new ListCarregPlantaFragment(), false, 1);
								}
							}
							else {
								Toast.makeText(getActivity(),"Ocorreu um erro ao gravar essa antena!",Toast.LENGTH_LONG).show();
							}
						}
						else {
							Toast.makeText(getActivity(),"Ocorreu um erro ao gravar essa antena!",Toast.LENGTH_LONG).show();
						}
					}
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR GRAVAR CARREGAMENTO",getActivity());
			Toast.makeText(getActivity(), "Ação Não Realizada!", Toast.LENGTH_LONG).show();
		}
	}
	
	
	
	
	//=================CAPTURAR FOTO ============================================
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
			// Possible exceptions are NoSuchMethodError and NoSuchFieldError
			//
			// In either case, an exception indicates something is wrong with the reflection code, or the 
			// structure of the PopupMenu class or its dependencies has changed.
			//
			// These exceptions should never happen since we're shipping the AppCompat library in our own apk, 
			// but in the case that they do, we simply can't force icons to display, so log the error and
			// show the menu normally.
			Log.w("Error", "error forcing menu icons to show", e);
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
	        	BtnFoto();
	            retorno = true;
	            break;
	        case R.id.visu_foto:
	        	BtnViewFoto();
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
	
	
	
	private void BtnFoto() {
		try {
			
			if(validarEditTxt(lstValidade)){
				//=========VERIFICAR GPS==========
				GPS = new GPSTracker(getActivity());
				if(GPS.canGetLocation()){
		        	LATITUDE = GPS.getLatitude();
		        	LONGITUDE = GPS.getLongitude();
		        	
		        	btn_capturar();
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
			
		}
	}
	
	public void btn_capturar() {
		try {
			if(criarDirExterno.CriarDirDB("/PPI_PDI/Fotos/")){
				photoFile = GerarNomeFoto(PrepararCarregamento(IDCAR)) + ".jpg";
				file = new File(criarDirExterno.PATHDIR);
				File foto = new File(criarDirExterno.PATHDIR, photoFile);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
			}
			else {
				Toast.makeText(getActivity(), "Não habilitado para capturar foto, problemas com a memória Interna!", Toast.LENGTH_SHORT).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR BTN CAPTURAR",getActivity());
		}
	}
	
	private String GerarNomeFoto(Carregamento carregamento){
		nomeArquivo[0] = ("OV_"+carregamento.getCODIGO()+"_"+carregamento.getCOD_ENTIDADE()+"_"+date.substring(0, 6)+"_CARGA_"+"19"+carregamento.getCAMPO_TIPO().substring(2, 5)+CaptImg+"E_"+carregamento.getINFO_20()+"_"+modAntena).replace(".", "_");
		nomeArquivo[1] = ("OV_"+carregamento.getCODIGO()+"_"+carregamento.getCOD_ENTIDADE()+"_CARGA_"+"19"+carregamento.getCAMPO_TIPO().substring(2, 5)+CaptImg+"E_"+carregamento.getINFO_20()+"_"+modAntena).replace(".", "_");
		nomeArquivo[2] = ("CARGA_"+"19"+carregamento.getCAMPO_TIPO().substring(2, 5)+CaptImg+"E_"+carregamento.getINFO_20()+"_"+modAntena).replace(".", "_");
		return nomeArquivo[0];
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
				if (!file.exists()) {
					file.mkdirs();
				}
				File foto = new File(criarDirExterno.PATHDIR, photoFile);
				if (foto != null) 
				{
					Bitmap bitmap = BitmapUtills.salvarFotoPasta(foto); //METODO NOVO
					
					ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(), "19"+carregamento.getCAMPO_TIPO().substring(2, 5)+CaptImg, carregamento.getCODIGO());
					if(controleUpload != null){
						//JA EXISTE FOTO PARA O CAR, ENT�O DEVE EDITAR
						File file = new File(PATH+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
						if(file.exists()){
							if(file.delete()){}					
						}
						controleUploadBLL.update(getActivity(), PrepararControleUpload(bitmap, controleUpload.getLinha()));
					}
					else { //AINDA N�O EXISTE FOTO PARA O CAR, ENT�O DEVE CRIAR
						controleUploadBLL.Insert(getActivity(), PrepararControleUpload(bitmap, null));
					}
					//ARMAZENA CARREGAMENTO
					BtnGravar(false);
					new AlertaDialog(getActivity()).showDialogAviso(getResources().getString(R.string.geral_Atencao), getResources().getString(R.string.geral_RegistroSalvo));
					new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,int which) {
						
						}
					};
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR COMPRESS",getActivity());
		}
	}
	
	private ControleUpload PrepararControleUpload(Bitmap bitmap, String linhaControleUp) {
		ControleUpload controleUpload = new ControleUpload();
		try {
			if(linhaControleUp != null){
				controleUpload.setLinha(linhaControleUp);
			}
			
			OsBLL osBLL = new OsBLL();
			Os os = OsMenuActitivity._OS;

			controleUpload.setCODIGO("OV_"+carregamento.getCODIGO()+"_"+carregamento.getCOD_ENTIDADE());
			controleUpload.setCOD_ENTIDADE(carregamento.getCOD_ENTIDADE());
			controleUpload.setVALIDADE("T");
			controleUpload.setCLIENTE(os.getCLIENTE());
			controleUpload.setCONTRATO(os.getCONTRATO());
			controleUpload.setCONTRATO_FASE(os.getCONTRATO_FASE());
			controleUpload.setCOD_ENTIDADE(carregamento.getCOD_ENTIDADE());
			controleUpload.setLOTE_CONTROLE(os.getLOTE_CONTROLE());
			controleUpload.setOV_CHAMADO_NUM(carregamento.getCODIGO());
			controleUpload.setAPROVADO("N");
			controleUpload.setArquivo_Carregado("19"+carregamento.getCAMPO_TIPO().substring(2, 5)+CaptImg);
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

	//=================CAPTURAR FOTO ============================================
	
	private Carregamento PrepararCarregamento(String idLinha) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		carregamento = new Carregamento();

		if(!idLinha.equals("0")){
			//EDICAO CARREGAMENTO, NECESS�RIO PERMANECER COM ALGUNS VALORES
			Carregamento _carregamento = new Carregamento();
			if (tipoCarregamento.equals("NOVA")) { //IGNORAR ESSA CONDI��O *NOVA
				_carregamento = carregamentoBLL.listarById(getActivity(), idLinha);
				carregamento.setLINHA(idLinha);
				carregamento.setINFO_01(_carregamento.getINFO_01());
				carregamento.setCAMPO_TIPO(_carregamento.getCAMPO_TIPO());
				carregamento.setORDENADOR(_carregamento.getORDENADOR());
				carregamento.setINFO_58(_carregamento.getINFO_58());
			}
		}
		else {
			//NOVO CARREGAMENTO (SOMENTE PARA CARREGAMENTO) - COLUNAS PARA REGRA DE ORDENACAO
			String ordenador = buscarOrdemCarregamento(OsMenuActitivity._OV_CHAMADO);
			carregamento.setINFO_01(ordenador);
			String campoTipo;
			if(ordenador.length() == 1){
				campoTipo = "1C00"+ordenador;
			}
			else{
				campoTipo = "1C0"+ordenador;
			}
			carregamento.setCAMPO_TIPO(campoTipo);
			carregamento.setORDENADOR(OsMenuActitivity._OV_CHAMADO+"-"+campoTipo);
		}
		
		if (ckAtiva.isChecked()){
			carregamento.setINFO_19("S");	
		}
		
		switch (rgAcao.getCheckedRadioButtonId()) {
		case R.id.rbExistente:
			carregamento.setINFO_17("e");
			carregamento.setINFO_47("BRANCO");
			break;
		case R.id.rbRetirada:
			carregamento.setINFO_17("r");
			carregamento.setINFO_47("VERMELHO");
			break;
		case R.id.rbNova:
			carregamento.setINFO_17("n");
			carregamento.setINFO_47("LARANJA");
			break;
		default:
			break;
		}
		
		//===============CALCULO AEV =============================
		carregamento.setINFO_12(AEV);
		carregamento.setINFO_13(CA);
		carregamento.setINFO_14(AEVt);
		carregamento.setINFO_26(AEV_FATOR);
		//===============CALCULO AEV =============================
		
		carregamento.setCODIGO(OsMenuActitivity._OV_CHAMADO);
		carregamento.setVALIDADE("T");
		carregamento.setCOD_ENTIDADE(OsMenuActitivity._OS.getCOD_ENTIDADE());
		carregamento.setLOTE_CONTROLE(OsMenuActitivity._OS.getLOTE_CONTROLE());
		carregamento.setOS_INSERCAO(OsMenuActitivity._OV_CHAMADO);
		carregamento.setFLAG_CONDICAO("A"); //SEMPRE A
		carregamento.setESTRUT_ORDEM(OsMenuActitivity._OS.getESTRUT_ORDEM());
		carregamento.setINFO_02(faceCanto);
		carregamento.setINFO_03(tipoElemento);//TIPO DA ANTENA (tipo de unidade) preenchimento no c�digo
		carregamento.setINFO_04(modAntena);
		carregamento.setINFO_05(edtAZI.getText().toString());
		carregamento.setINFO_06(edtAltAzi.getText().toString());
		carregamento.setINFO_07(edtAltTilt.getText().toString());
		carregamento.setINFO_08(edtLocalizacaoEquipamento.getText().toString());
		carregamento.setINFO_09(edtElemDim.getText().toString().replace(".", ","));
		carregamento.setINFO_10(edtElemDim2.getText().toString());
		carregamento.setINFO_11("1"); //NOVA VERS�O REMOVEU ESTE CAMPO, REVER
		carregamento.setINFO_15(edtPolariz.getText().toString());
		carregamento.setINFO_16(edtFreq.getText().toString());
		carregamento.setINFO_18(funcoes);
		carregamento.setINFO_20(edtEtiqueta.getText().toString());
		carregamento.setINFO_21(operadora);
		carregamento.setINFO_22(edtObservacao.getText().toString());
		carregamento.setINFO_23(edtCaboDim.getText().toString());
		carregamento.setINFO_24(edtCAx.getText().toString());
		carregamento.setINFO_25(edtQtd2.getText().toString());
		carregamento.setINFO_27(edtElemPeso.getText().toString());
		carregamento.setINFO_28(edtTipoFix.getText().toString());
		carregamento.setINFO_29(edtSupPeso.getText().toString());
		//=====CAMPOS RELACIONADOS COM EV======================
		carregamento.setINFO_49(null);
		if(checkedRbPlatSim && rgPlatTopoEnabled){
			carregamento.setINFO_49("S");
		}
		else if(checkedRbPlatNao && rgPlatTopoEnabled){
			carregamento.setINFO_49("N");
		} 
		carregamento.setINFO_42(null);
		if(checkedRbSecRet && rgSecaoEnabled){
			carregamento.setINFO_42("R");
		}
		else if(checkedRbSecInc && rgSecaoEnabled){
			carregamento.setINFO_42("I");
		}
		carregamento.setINFO_45(null);
		if(checkedRbRefSim && rgReforcoEnabled){
			carregamento.setINFO_45("S");
		}
		else if(checkedRbRefNao && rgReforcoEnabled){
			carregamento.setINFO_45("N");
		}
		switch (camposF) {
		case 0:
			carregamento.setINFO_41(null);
			carregamento.setINFO_43(null);
			carregamento.setINFO_44(null);
			carregamento.setINFO_46(null);
			carregamento.setINFO_48(null);
			break;
		case 3:
			carregamento.setINFO_41(edtFa.getText().toString());
			carregamento.setINFO_43(edtFb.getText().toString());
			carregamento.setINFO_44(edtFc.getText().toString());
			carregamento.setINFO_46(null);
			carregamento.setINFO_48(null);
			break;
		case 4:
			carregamento.setINFO_41(edtFa.getText().toString());
			carregamento.setINFO_43(edtFb.getText().toString());
			carregamento.setINFO_44(edtFc.getText().toString());
			carregamento.setINFO_46(edtFd.getText().toString());
			carregamento.setINFO_48(null);
			break;
		case 5:
			carregamento.setINFO_41(edtFa.getText().toString());
			carregamento.setINFO_43(edtFb.getText().toString());
			carregamento.setINFO_44(edtFc.getText().toString());
			carregamento.setINFO_46(edtFd.getText().toString());
			carregamento.setINFO_48(edtFe.getText().toString());
			break;

		default:
			carregamento.setINFO_41(null);
			carregamento.setINFO_43(null);
			carregamento.setINFO_44(null);
			carregamento.setINFO_46(null);
			carregamento.setINFO_48(null);
			break;
		}
		switch (camposM) {
		case 0:
			carregamento.setINFO_31(null);
			carregamento.setINFO_32(null);
			carregamento.setINFO_33(null);
			carregamento.setINFO_34(null);
			carregamento.setINFO_35(null);
			carregamento.setINFO_36(null);
			carregamento.setINFO_37(null);
			carregamento.setINFO_38(null);
			carregamento.setINFO_39(null);
			break;
		case 3:
			carregamento.setINFO_31(edtMa.getText().toString());
			carregamento.setINFO_32(edtMb.getText().toString());
			carregamento.setINFO_33(edtMc.getText().toString());
			carregamento.setINFO_34(null);
			carregamento.setINFO_35(null);
			carregamento.setINFO_36(null);
			carregamento.setINFO_37(null);
			carregamento.setINFO_38(null);
			carregamento.setINFO_39(null);
			break;
		case 4:
			carregamento.setINFO_31(edtMa.getText().toString());
			carregamento.setINFO_32(edtMb.getText().toString());
			carregamento.setINFO_33(edtMc.getText().toString());
			carregamento.setINFO_34(edtMd.getText().toString());
			carregamento.setINFO_35(null);
			carregamento.setINFO_36(null);
			carregamento.setINFO_37(null);
			carregamento.setINFO_38(null);
			carregamento.setINFO_39(null);
			break;
		case 6:
			carregamento.setINFO_31(edtMa.getText().toString());
			carregamento.setINFO_32(edtMb.getText().toString());
			carregamento.setINFO_33(edtMc.getText().toString());
			carregamento.setINFO_34(edtMd.getText().toString());
			carregamento.setINFO_35(edtMe.getText().toString());
			carregamento.setINFO_36(edtMf.getText().toString());
			carregamento.setINFO_37(null);
			carregamento.setINFO_38(null);
			carregamento.setINFO_39(null);
			break;
		case 8:
			carregamento.setINFO_31(edtMa.getText().toString());
			carregamento.setINFO_32(edtMb.getText().toString());
			carregamento.setINFO_33(edtMc.getText().toString());
			carregamento.setINFO_34(edtMd.getText().toString());
			carregamento.setINFO_35(edtMe.getText().toString());
			carregamento.setINFO_36(edtMf.getText().toString());
			carregamento.setINFO_37(edtMg.getText().toString());
			carregamento.setINFO_38(edtMh.getText().toString());
			carregamento.setINFO_39(null);
			break;
		case 9:
			carregamento.setINFO_31(edtMa.getText().toString());
			carregamento.setINFO_32(edtMb.getText().toString());
			carregamento.setINFO_33(edtMc.getText().toString());
			carregamento.setINFO_34(edtMd.getText().toString());
			carregamento.setINFO_35(edtMe.getText().toString());
			carregamento.setINFO_36(edtMf.getText().toString());
			carregamento.setINFO_37(edtMg.getText().toString());
			carregamento.setINFO_38(edtMh.getText().toString());
			carregamento.setINFO_39(edtMi.getText().toString());
			break;

		default:
			carregamento.setINFO_31(null);
			carregamento.setINFO_32(null);
			carregamento.setINFO_33(null);
			carregamento.setINFO_34(null);
			carregamento.setINFO_35(null);
			carregamento.setINFO_36(null);
			carregamento.setINFO_37(null);
			carregamento.setINFO_38(null);
			carregamento.setINFO_39(null);
			break;
		}
		//=====CAMPOS RELACIONADOS COM EV======================
		carregamento.setATUALIZACAO_DATA(dateFormat.format(new Date()));
		carregamento.setINSERCAO_DATA(dateFormat.format(new Date()));
		carregamento.setINSERCAO_NOME("MOBILE");
		carregamento.setATUALIZACAO_NOME("MOBILE");
		carregamento.setINFO_53(dateFormat.format(new Date()));
		
		return carregamento;
	}
	
	private String buscarOrdemCarregamento(String codigo){
		int ordenador = 1;
		try {
			List<Carregamento> lst = carregamentoBLL.listarByCodigoAllNotEv(getActivity(), codigo, "1A000");
			if(lst != null){
				//EXISTE CARREGAMENTO PARA OS
				//VERIFICAR EM QUAL EST�
				ordenador = lst.size() + ordenador;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(ordenador);
	}
	
	//===============CALCULO AEV =============================	
	private void requisitarCalcAEV(){
		//=======FAZER CALCULO DE AEV=============
		try {
			comboBLL = new ComboBLL();
			Insumos insumosSelect = insumosBLL.listarByInfo02(getActivity(), modAntena);
			if(insumosSelect != null){
				Combo combo = comboBLL.listarBySigla(getActivity(), insumosSelect.getINFO_01());
				if(combo != null){
					tipoElemento = combo.getSIGLA();
					//CALCULAR AEV
					calcularAEV(combo);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//=======FAZER CALCULO DE AEV=============
	}
	
	private void calcularAEV(Combo combo){ //======REVER CALCULO======
		CA = combo.getCA();
		AEV_FATOR = combo.getAEV();
		DecimalFormat df = new DecimalFormat("#,###.000");
		if(!combo.getSIGLA().subSequence(0, 4).equals("TA-PV") && !combo.getSIGLA().subSequence(0, 4).equals("TA-PS")){
			if(combo.getCLASSIFICACAO().equals("RF") || combo.getCLASSIFICACAO().equals("ODU") 
					|| combo.getCLASSIFICACAO().equals("RRU") || combo.getCLASSIFICACAO().equals("GPS") ) {
				if(!edtElemDim.getText().equals("0") && !edtElemDim2.getText().equals("0")){
					AEV = df.format(Double.parseDouble(edtElemDim.getText().toString().trim())
							* Double.parseDouble(edtElemDim2.getText().toString().trim()));
				}
				else {
					//ERROR Dimens�es da Antena
					Toast.makeText(
							getActivity(),
							"Não foi possivel calcular o AEV devido algumas características do site não serem preenchidas!",
							Toast.LENGTH_LONG).show();
				}
			}
		}
		else {
			if(!edtElemDim.getText().toString().trim().equals("0") && edtElemDim2.getText().toString().trim().equals("0")){
				AEV = df.format((Math.pow(3.141592 * Double.parseDouble(edtElemDim.getText().toString()), 2) / 4));
			}
		}
		
		//quantidade sempre = 1
				AEVt = Double.toString(Double.parseDouble(CA.replace(",", "."))
					* Double.parseDouble(AEV_FATOR.replace(",", "."))
					* Double.parseDouble(AEV.replace(",", "."))
					* Double.parseDouble("1"));
				AEVt = AEVt.replace(".", ","); 
		
	}
	//===============CALCULO AEV =============================
	
	//===============VISUALIZAR FOTO =============================
	private void BtnViewFoto() {
		try {
			if(!IDCAR.equals("0")){
				String idCarr = "0";
				if(tipoCarregamento.equals("NOVA")){
					idCarr = IDCAR;
				}
				else if (tipoCarregamento.equals("PLANTA")) {
					idCarr = carregamentoPlanta.getINFO_50();
				}
				Carregamento carregamento = carregamentoBLL.listarById(getActivity(), idCarr);
				if(carregamento != null){
					ControleUpload controleUpload = controleUploadBLL.listarByArqCarregado(getActivity(), "19"+carregamento.getCAMPO_TIPO().substring(2, 5)+CaptImg, carregamento.getCODIGO());
					if(controleUpload != null){
						DialogImg(controleUpload);
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
	
	private void DialogImg(final ControleUpload controleUpload) {
		LayoutInflater li = getActivity().getLayoutInflater();
		
		View view = li.inflate(R.layout.dialog_img, null);
		File file = new File(PATH+controleUpload.getARQ_NOME_SIST()+"."+controleUpload.getARQ_TIPO());
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
	
	private boolean validarEditTxt(ArrayList<EditText> lstValidade){
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
	
	private void FindElementosView(View view) {
		rgAcao = (RadioGroup) view.findViewById(R.id.rgAcao);
		rbExistente = (RadioButton) view.findViewById(R.id.rbExistente);
		rbRetirada = (RadioButton) view.findViewById(R.id.rbRetirada);
		rbNova = (RadioButton) view.findViewById(R.id.rbNova);
		edtEtiqueta = (EditText) view.findViewById(R.id.edtEtiqueta);
		ckAtiva = (CheckBox) view.findViewById(R.id.ckAtiva);
		lstValidade.add(edtEtiqueta);
		
		edtPolariz = (EditText) view.findViewById(R.id.edtPolariz);
		lstValidade.add(edtPolariz);
		edtFreq = (EditText) view.findViewById(R.id.edtFreq);
		lstValidade.add(edtFreq);
		edtElemDim = (EditText) view.findViewById(R.id.edtElemDim);
		lstValidade.add(edtElemDim);
		edtElemDim2 = (EditText) view.findViewById(R.id.edtElemDim2);
		lstValidade.add(edtElemDim2);
		edtElemPeso = (EditText) view.findViewById(R.id.edtElemPeso);
		lstValidade.add(edtElemPeso);
		
		edtAEV = (EditText) view.findViewById(R.id.edtAEV);
		edtCA = (EditText) view.findViewById(R.id.edtCA);
		edtFT = (EditText) view.findViewById(R.id.edtFT);
		edtAEVt = (EditText) view.findViewById(R.id.edtAEVt);
		
		edtAZI = (EditText) view.findViewById(R.id.edtAZI);
		lstValidade.add(edtAZI);
		edtAltAzi = (EditText) view.findViewById(R.id.edtAltAzi);
		lstValidade.add(edtAltAzi);
		edtAltTilt = (EditText) view.findViewById(R.id.edtTilt);
		lstValidade.add(edtAltTilt);
		
		edtSupPeso = (EditText) view.findViewById(R.id.edtSupPeso);
		lstValidade.add(edtSupPeso);
		edtTipoFix = (EditText) view.findViewById(R.id.edtTipoFix);
		lstValidade.add(edtTipoFix);
		
		edtCaboDim = (EditText) view.findViewById(R.id.edtCaboDim);
		lstValidade.add(edtCaboDim);
		edtCAx = (EditText) view.findViewById(R.id.edtCAx);
		lstValidade.add(edtCAx);
		edtQtd2 = (EditText) view.findViewById(R.id.edtQtd2);
		lstValidade.add(edtQtd2);
		edtLocalizacaoEquipamento = (EditText) view.findViewById(R.id.edtLocalizacaoEquipamento);
		lstValidade.add(edtLocalizacaoEquipamento);
		
		edtObservacao = (EditText) view.findViewById(R.id.edtObservacao);
		

		rgPlatTopo = (RadioGroup) view.findViewById(R.id.rgPlatTopo);
		rbPlatSim = (RadioButton) view.findViewById(R.id.rbPlatSim);
		rbPlatSim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					checkedRbPlatSim = false;
					checkedRbPlatNao = false;
					switch (rgPlatTopo.getCheckedRadioButtonId()) {
					case R.id.rbPlatSim:
						checkedRbPlatSim = true;
						break;
					case R.id.rbPlatNao:
						checkedRbPlatNao = true;
						break;
					default:
						break;
					}
					rbSecInc.setEnabled(false);
					rbSecReta.setEnabled(false);
					rbRefSim.setEnabled(false);
					rbRefNao.setEnabled(false);
					DesabilitarRbConfigImagens();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rbPlatNao = (RadioButton) view.findViewById(R.id.rbPlatNao);
		rbPlatNao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					checkedRbPlatSim = false;
					checkedRbPlatNao = false;
					switch (rgPlatTopo.getCheckedRadioButtonId()) {
					case R.id.rbPlatSim:
						checkedRbPlatSim = true;
						break;
					case R.id.rbPlatNao:
						checkedRbPlatNao = true;
						break;
					default:
						break;
					}
					rbSecInc.setEnabled(true);
					rbSecReta.setEnabled(true);
					rbRefSim.setEnabled(true);
					rbRefNao.setEnabled(true);
					DesabilitarRbConfigImagens();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rgSecao = (RadioGroup) view.findViewById(R.id.rgSecaoCar);
		rbSecReta = (RadioButton) view.findViewById(R.id.rbSecReta);
		rbSecReta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					checkedRbSecRet = false;
					checkedRbSecInc = false;
					switch (rgSecao.getCheckedRadioButtonId()) {
					case R.id.rbSecReta:
						checkedRbSecRet = true;
						break;
					case R.id.rbSecInc:
						checkedRbSecInc = true;
						break;
					default:
						break;
					}
					DesabilitarRbConfigImagens();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rbSecInc = (RadioButton) view.findViewById(R.id.rbSecInc);
		rbSecInc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					checkedRbSecRet = false;
					checkedRbSecInc = false;
					switch (rgSecao.getCheckedRadioButtonId()) {
					case R.id.rbSecReta:
						checkedRbSecRet = true;
						break;
					case R.id.rbSecInc:
						checkedRbSecInc = true;
						break;
					default:
						break;
					}
					DesabilitarRbConfigImagens();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rgReforco = (RadioGroup) view.findViewById(R.id.rgReforco);
		rbRefSim = (RadioButton) view.findViewById(R.id.rbRefSim);
		rbRefSim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					checkedRbRefSim = false;
					checkedRbRefNao = false;
					switch (rgReforco.getCheckedRadioButtonId()) {
					case R.id.rbRefSim:
						checkedRbRefSim = true;
						break;
					case R.id.rbRefNao:
						checkedRbRefNao = true;
						break;
					default:
						break;
					}
					DesabilitarRbConfigImagens();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		rbRefNao = (RadioButton) view.findViewById(R.id.rbRefNao);
		rbRefNao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					checkedRbRefSim = false;
					checkedRbRefNao = false;
					switch (rgReforco.getCheckedRadioButtonId()) {
					case R.id.rbRefSim:
						checkedRbRefSim = true;
						break;
					case R.id.rbRefNao:
						checkedRbRefNao = true;
						break;
					default:
						break;
					}
					DesabilitarRbConfigImagens();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ivDt = (ImageView) view.findViewById(R.id.ivDt);
		ivDm = (ImageView) view.findViewById(R.id.ivDm);
		
		edtFa = (EditText) view.findViewById(R.id.edtFa);
		tvFa = (TextView) view.findViewById(R.id.tvFa);
		edtFb = (EditText) view.findViewById(R.id.edtFb);
		tvFb = (TextView) view.findViewById(R.id.tvFb);
		edtFc = (EditText) view.findViewById(R.id.edtFc);
		tvFc = (TextView) view.findViewById(R.id.tvFc);
		edtFd = (EditText) view.findViewById(R.id.edtFd);
		tvFd = (TextView) view.findViewById(R.id.tvFd);
		edtFe = (EditText) view.findViewById(R.id.edtFe);
		tvFe = (TextView) view.findViewById(R.id.tvFe);
		edtMa = (EditText) view.findViewById(R.id.edtMa);
		tvMa = (TextView) view.findViewById(R.id.tvMa);
		edtMb = (EditText) view.findViewById(R.id.edtMb);
		tvMb = (TextView) view.findViewById(R.id.tvMb);
		edtMc = (EditText) view.findViewById(R.id.edtMc);
		tvMc = (TextView) view.findViewById(R.id.tvMc);
		edtMd = (EditText) view.findViewById(R.id.edtMd);
		tvMd = (TextView) view.findViewById(R.id.tvMd);
		edtMe = (EditText) view.findViewById(R.id.edtMe);
		tvMe = (TextView) view.findViewById(R.id.tvMe);
		edtMf = (EditText) view.findViewById(R.id.edtMf);
		tvMf = (TextView) view.findViewById(R.id.tvMf);
		edtMg = (EditText) view.findViewById(R.id.edtMg);
		tvMg = (TextView) view.findViewById(R.id.tvMg);
		edtMh = (EditText) view.findViewById(R.id.edtMh);
		tvMh = (TextView) view.findViewById(R.id.tvMh);
		edtMi = (EditText) view.findViewById(R.id.edtMi);
		tvMi = (TextView) view.findViewById(R.id.tvMi);
		
		btnFotoA = (Button) view.findViewById(R.id.btnFotoA);
		btnFotoA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CaptImg = "a";
				showMenu(v);
			}
		});
		btnFotoC = (Button) view.findViewById(R.id.btnFotoC);
		btnFotoC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CaptImg = "c";
				showMenu(v);
			}
		});
		btnFotoP = (Button) view.findViewById(R.id.btnFotoP);
		btnFotoP.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CaptImg = "p";
				showMenu(v);
			}
		});
		btnFotoS = (Button) view.findViewById(R.id.btnFotoS);
		btnFotoS.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CaptImg = "s";
				showMenu(v);
			}
		});
		btnGravar = (Button) view.findViewById(R.id.btnGravar);
		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BtnGravar(true);
				
			}
		});
	}

	//--------------------------- config imagens
	
	private void DesabilitarRbConfigImagens() throws Exception{
		try {
			rgPlatTopoEnabled = true;
			rgSecaoEnabled = true;
			rgReforcoEnabled = true;
			//PLATAF TOPO
			if(EV.getINFO_46() == null){
				rbPlatSim.setEnabled(false);
				rbPlatNao.setEnabled(false);
				rgPlatTopoEnabled = false;
			}
			else if(!EV.getINFO_46().equals("S")){ 
				rbPlatSim.setEnabled(false);
				rbPlatNao.setEnabled(false);
				rgPlatTopoEnabled = false;
			}
			//SECAO
			if(EV.getINFO_42() == null || EV.getINFO_43() == null){
				rbSecInc.setEnabled(false);
				rbSecReta.setEnabled(false);
				rgSecaoEnabled = false;
			}
			else if(!EV.getINFO_42().equals("S") || !EV.getINFO_43().equals("S")){ 
				rbSecInc.setEnabled(false);
				rbSecReta.setEnabled(false);
				rgSecaoEnabled = false;
			}
			//REFORCO
			if(EV.getINFO_45() == null){
				rbRefSim.setEnabled(false);
				rbRefNao.setEnabled(false);
				rgReforcoEnabled = false;
			}
			else if(!EV.getINFO_45().equals("S")){ 
				rbRefSim.setEnabled(false);
				rbRefNao.setEnabled(false);
				rgReforcoEnabled = false;
			}
			EventConfigImagens();
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR Config Imagem",getActivity());
			throw e;
		}
	}
	
	private void EventConfigImagens() throws Exception {
		IMG_DT = "ze0_0";
		IMG_DM = "ze0_0";
		try {
			if(EV.getINFO_41().toLowerCase().equals("indoor")){
				IMG_DT = "in2_0";;
				IMG_DM = "in2_0";
			}
			else {
				if(checkedRbPlatSim){
					if(EV.getINFO_49().substring(1, 2).equals("6")){
						IMG_DT = "gc"+EV.getINFO_49().substring(1, 2)+"_0";
						IMG_DM = "gc"+EV.getINFO_49().substring(1, 2)+"_0";
					}
					else {
						IMG_DT = "gc"+EV.getINFO_49().substring(1, 2)+"_3";
						IMG_DM = "gc"+EV.getINFO_49().substring(1, 2)+"_3";
					}
					rgReforcoEnabled = false;
					rgSecaoEnabled = false;
				}
				else {
					//INFO_01() = combo.getSigla() (FS)
					if (EV.getINFO_01().equals("FS-CA") || EV.getINFO_01().equals("FS-CR")){
						IMG_DT = "cx0_0";
						IMG_DM = "cx0_0";
					}
					else if (EV.getINFO_01().equals("FS-PC") || EV.getINFO_01().equals("FS-PM")){
						IMG_DT = "pm4_4";
						IMG_DM = "qs3_3";
					}
					else if (EV.getINFO_01().equals("Roof-Top") || EV.getINFO_01().equals("FS-ZE")){
						IMG_DT = "";
						IMG_DM = "";
					}
					else if (EV.getINFO_01().equals("FS-R3") || EV.getINFO_01().equals("FS-RM") || EV.getINFO_01().equals("FS-MT")){
						IMG_DT = "pm4_4";
						IMG_DM = "qs3_3";
					}
					else if (EV.getINFO_01().equals("FS-SC")){
						IMG_DT = "sc2_0";
						IMG_DM = "sc2_0";
					}
					else if (EV.getINFO_01().equals("FS-TC")){
						IMG_DT = "tc2_0";
						IMG_DM = "tc2_4";
					}
					else if (EV.getINFO_01().equals("FS-CV") || EV.getINFO_01().equals("FS-RC") || EV.getINFO_01().equals("FS-RE") || EV.getINFO_01().equals("FS-TE")){
						IMG_DT = "tr2_3";
						IMG_DM = "DETERMINAR";
					}
					else if (EV.getINFO_01().equals("FS-RA") || EV.getINFO_01().equals("FS-TA")){
						if(EV.getINFO_42() != null && EV.getINFO_43() != null){
							if(EV.getINFO_42().equals("S") && EV.getINFO_43().equals("S")){
								if(checkedRbSecInc){
									IMG_DT = "ti2_5";
									IMG_DM = "DETERMINAR";
								}
								else if(checkedRbSecRet){
									IMG_DT = "tr2_3";
									IMG_DM = "DETERMINAR";
								}
							}
							else {
								if(EV.getINFO_42().equals("S") && EV.getINFO_43().equals("N")){
									IMG_DT = "ti2_5";
									IMG_DM = "DETERMINAR";
								}
								else if(EV.getINFO_42().equals("N") && EV.getINFO_43().equals("S")){
									IMG_DT = "tr2_3";
									IMG_DM = "DETERMINAR";
								}
								else {
									IMG_DT = "zi0_0";
									IMG_DM = "zi0_0";
								}
							}
						}
					}
					
					if(EV.getINFO_45() != null){
						if(EV.getINFO_45().equals("S") && !checkedRbRefSim && !checkedRbRefNao){
							IMG_DM = "zi0_0";
						}
						else {
							if(IMG_DM.equals("DETERMINAR")){
								IMG_DM ="";
								if(EV.getINFO_48().equals("T1")){
									if(checkedRbRefSim)
										IMG_DM = "tr1_8";
									else IMG_DM = "ts1_3";
								}
								else if(EV.getINFO_48().equals("T2")){
									if(checkedRbRefSim)
										IMG_DM = "tr2_9";
									else IMG_DM = "ts2_4";
								}
								else if(EV.getINFO_48().equals("T3") || EV.getINFO_48().equals("Q3"))
									IMG_DM = "qs3_3";
								else if(EV.getINFO_48().equals("T4")){
									if(checkedRbRefSim)
										IMG_DM = "tr4_9";
									else IMG_DM = "ts4_4";
								}
								else if(EV.getINFO_48().equals("T5"))
									IMG_DM = "ts5_6";
								else if(EV.getINFO_48().equals("T6") || EV.getINFO_48().equals("Q6"))
									IMG_DM = "sp2_0";
								else if(EV.getINFO_48().equals("Q1")){
									if(checkedRbRefSim)
										IMG_DM = "qr1_9";
									else IMG_DM = "qs1_3";
								}
							}
						}
					}
				}
			}
			Drawable res;
			int imageResource;
			
			IMG_DT = "dt_"+IMG_DT;
			String uriDt = IMG_DT;
			imageResource = getActivity().getResources().getIdentifier(uriDt, "drawable", getActivity().getPackageName());
			if(imageResource != 0){
				res = getActivity().getResources().getDrawable(imageResource);
				ivDt.setImageDrawable(res);
			}

			IMG_DM = "dm_"+IMG_DM;
			if(IMG_DM.equals("dm_DETERMINAR"))
				IMG_DM = "dm_ze0_0";
			String uriDm = IMG_DM;
			imageResource = getActivity().getResources().getIdentifier(uriDm, "drawable", getActivity().getPackageName());
			if(imageResource != 0){
				res = getActivity().getResources().getDrawable(imageResource);
				ivDm.setImageDrawable(res);
			}

			HabilitarCamposDim(IMG_DT, IMG_DM);
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR Config Imagem",getActivity());
			throw e;
		}
	}

	private void HabilitarCamposDim(String iMG_DT, String iMG_DM) {
		camposF = Integer.valueOf(iMG_DT.substring(iMG_DT.length()-1, iMG_DT.length()));
		camposM = Integer.valueOf(iMG_DM.substring(iMG_DM.length()-1, iMG_DM.length()));
		switch (camposF) {
		case 0:
			tvFa.setVisibility(View.GONE);
			edtFa.setVisibility(View.GONE);
			tvFb.setVisibility(View.GONE);
			edtFb.setVisibility(View.GONE);
			tvFc.setVisibility(View.GONE);
			edtFc.setVisibility(View.GONE);
			tvFd.setVisibility(View.GONE);
			edtFd.setVisibility(View.GONE);
			tvFe.setVisibility(View.GONE);
			edtFe.setVisibility(View.GONE);
			break;
		case 3:
			tvFa.setVisibility(View.VISIBLE);
			edtFa.setVisibility(View.VISIBLE);
			tvFb.setVisibility(View.VISIBLE);
			edtFb.setVisibility(View.VISIBLE);
			tvFc.setVisibility(View.VISIBLE);
			edtFc.setVisibility(View.VISIBLE);
			tvFd.setVisibility(View.GONE);
			edtFd.setVisibility(View.GONE);
			tvFe.setVisibility(View.GONE);
			edtFe.setVisibility(View.GONE);
			break;
		case 4:
			tvFa.setVisibility(View.VISIBLE);
			edtFa.setVisibility(View.VISIBLE);
			tvFb.setVisibility(View.VISIBLE);
			edtFb.setVisibility(View.VISIBLE);
			tvFc.setVisibility(View.VISIBLE);
			edtFc.setVisibility(View.VISIBLE);
			tvFd.setVisibility(View.VISIBLE);
			edtFd.setVisibility(View.VISIBLE);
			tvFe.setVisibility(View.GONE);
			edtFe.setVisibility(View.GONE);
			break;
		case 5:
			tvFa.setVisibility(View.VISIBLE);
			edtFa.setVisibility(View.VISIBLE);
			tvFb.setVisibility(View.VISIBLE);
			edtFb.setVisibility(View.VISIBLE);
			tvFc.setVisibility(View.VISIBLE);
			edtFc.setVisibility(View.VISIBLE);
			tvFd.setVisibility(View.VISIBLE);
			edtFd.setVisibility(View.VISIBLE);
			tvFe.setVisibility(View.VISIBLE);
			edtFe.setVisibility(View.VISIBLE);
			break;

		default:
			tvFa.setVisibility(View.GONE);
			edtFa.setVisibility(View.GONE);
			tvFb.setVisibility(View.GONE);
			edtFb.setVisibility(View.GONE);
			tvFc.setVisibility(View.GONE);
			edtFc.setVisibility(View.GONE);
			tvFd.setVisibility(View.GONE);
			edtFd.setVisibility(View.GONE);
			tvFe.setVisibility(View.GONE);
			edtFe.setVisibility(View.GONE);
			break;
		}
		
		switch (camposM) {
		case 0:
			tvMa.setVisibility(View.GONE);
			edtMa.setVisibility(View.GONE);
			tvMb.setVisibility(View.GONE);
			edtMb.setVisibility(View.GONE);
			tvMc.setVisibility(View.GONE);
			edtMc.setVisibility(View.GONE);
			tvMd.setVisibility(View.GONE);
			edtMd.setVisibility(View.GONE);
			tvMe.setVisibility(View.GONE);
			edtMe.setVisibility(View.GONE);
			tvMf.setVisibility(View.GONE);
			edtMf.setVisibility(View.GONE);
			tvMg.setVisibility(View.GONE);
			edtMg.setVisibility(View.GONE);
			tvMh.setVisibility(View.GONE);
			edtMh.setVisibility(View.GONE);
			tvMi.setVisibility(View.GONE);
			edtMi.setVisibility(View.GONE);
			break;
		case 3:
			tvMa.setVisibility(View.VISIBLE);
			edtMa.setVisibility(View.VISIBLE);
			tvMb.setVisibility(View.VISIBLE);
			edtMb.setVisibility(View.VISIBLE);
			tvMc.setVisibility(View.VISIBLE);
			edtMc.setVisibility(View.VISIBLE);
			tvMd.setVisibility(View.GONE);
			edtMd.setVisibility(View.GONE);
			tvMe.setVisibility(View.GONE);
			edtMe.setVisibility(View.GONE);
			tvMf.setVisibility(View.GONE);
			edtMf.setVisibility(View.GONE);
			tvMg.setVisibility(View.GONE);
			edtMg.setVisibility(View.GONE);
			tvMh.setVisibility(View.GONE);
			edtMh.setVisibility(View.GONE);
			tvMi.setVisibility(View.GONE);
			edtMi.setVisibility(View.GONE);
			break;
		case 4:
			tvMa.setVisibility(View.VISIBLE);
			edtMa.setVisibility(View.VISIBLE);
			tvMb.setVisibility(View.VISIBLE);
			edtMb.setVisibility(View.VISIBLE);
			tvMc.setVisibility(View.VISIBLE);
			edtMc.setVisibility(View.VISIBLE);
			tvMd.setVisibility(View.VISIBLE);
			edtMd.setVisibility(View.VISIBLE);
			tvMe.setVisibility(View.GONE);
			edtMe.setVisibility(View.GONE);
			tvMf.setVisibility(View.GONE);
			edtMf.setVisibility(View.GONE);
			tvMg.setVisibility(View.GONE);
			edtMg.setVisibility(View.GONE);
			tvMh.setVisibility(View.GONE);
			edtMh.setVisibility(View.GONE);
			tvMi.setVisibility(View.GONE);
			edtMi.setVisibility(View.GONE);
			break;
		case 6:
			tvMa.setVisibility(View.VISIBLE);
			edtMa.setVisibility(View.VISIBLE);
			tvMb.setVisibility(View.VISIBLE);
			edtMb.setVisibility(View.VISIBLE);
			tvMc.setVisibility(View.VISIBLE);
			edtMc.setVisibility(View.VISIBLE);
			tvMd.setVisibility(View.VISIBLE);
			edtMd.setVisibility(View.VISIBLE);
			tvMe.setVisibility(View.VISIBLE);
			edtMe.setVisibility(View.VISIBLE);
			tvMf.setVisibility(View.VISIBLE);
			edtMf.setVisibility(View.VISIBLE);
			tvMg.setVisibility(View.GONE);
			edtMg.setVisibility(View.GONE);
			tvMh.setVisibility(View.GONE);
			edtMh.setVisibility(View.GONE);
			tvMi.setVisibility(View.GONE);
			edtMi.setVisibility(View.GONE);
			break;
		case 8:
			tvMa.setVisibility(View.VISIBLE);
			edtMa.setVisibility(View.VISIBLE);
			tvMb.setVisibility(View.VISIBLE);
			edtMb.setVisibility(View.VISIBLE);
			tvMc.setVisibility(View.VISIBLE);
			edtMc.setVisibility(View.VISIBLE);
			tvMd.setVisibility(View.VISIBLE);
			edtMd.setVisibility(View.VISIBLE);
			tvMe.setVisibility(View.VISIBLE);
			edtMe.setVisibility(View.VISIBLE);
			tvMf.setVisibility(View.VISIBLE);
			edtMf.setVisibility(View.VISIBLE);
			tvMg.setVisibility(View.VISIBLE);
			edtMg.setVisibility(View.VISIBLE);
			tvMh.setVisibility(View.VISIBLE);
			edtMh.setVisibility(View.VISIBLE);
			tvMi.setVisibility(View.GONE);
			edtMi.setVisibility(View.GONE);
			break;
		case 9:
			tvMa.setVisibility(View.VISIBLE);
			edtMa.setVisibility(View.VISIBLE);
			tvMb.setVisibility(View.VISIBLE);
			edtMb.setVisibility(View.VISIBLE);
			tvMc.setVisibility(View.VISIBLE);
			edtMc.setVisibility(View.VISIBLE);
			tvMd.setVisibility(View.VISIBLE);
			edtMd.setVisibility(View.VISIBLE);
			tvMe.setVisibility(View.VISIBLE);
			edtMe.setVisibility(View.VISIBLE);
			tvMf.setVisibility(View.VISIBLE);
			edtMf.setVisibility(View.VISIBLE);
			tvMg.setVisibility(View.VISIBLE);
			edtMg.setVisibility(View.VISIBLE);
			tvMh.setVisibility(View.VISIBLE);
			edtMh.setVisibility(View.VISIBLE);
			tvMi.setVisibility(View.VISIBLE);
			edtMi.setVisibility(View.VISIBLE);
			break;

		default:
			tvMa.setVisibility(View.GONE);
			edtMa.setVisibility(View.GONE);
			tvMb.setVisibility(View.GONE);
			edtMb.setVisibility(View.GONE);
			tvMc.setVisibility(View.GONE);
			edtMc.setVisibility(View.GONE);
			tvMd.setVisibility(View.GONE);
			edtMd.setVisibility(View.GONE);
			tvMe.setVisibility(View.GONE);
			edtMe.setVisibility(View.GONE);
			tvMf.setVisibility(View.GONE);
			edtMf.setVisibility(View.GONE);
			tvMg.setVisibility(View.GONE);
			edtMg.setVisibility(View.GONE);
			tvMh.setVisibility(View.GONE);
			edtMh.setVisibility(View.GONE);
			tvMi.setVisibility(View.GONE);
			edtMi.setVisibility(View.GONE);
			break;
		}
	}
	
}

/*<RadioGroup
android:id="@+id/rgEdific"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:orientation="horizontal" >

<RadioButton
    android:id="@+id/rbE"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="e" />

<RadioButton
    android:id="@+id/rbR"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="r" />

<RadioButton
    android:id="@+id/rbN"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="15dp"
    android:text="n" />
</RadioGroup>*/

