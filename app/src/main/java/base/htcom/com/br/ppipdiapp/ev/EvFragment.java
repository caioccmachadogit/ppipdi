package base.htcom.com.br.ppipdiapp.ev;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;

public class EvFragment extends Fragment {
	
	private ComboBLL comboBLL = new ComboBLL();
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	
	private Spinner spinnerTipoSite;
	private Spinner spinnerEstrutura;
	private String tipoSite;
	private String estrutura;
	private List<Combo> lstCombo;
	private String[] comboEstrutura;
	private String[] comboTipoSite;
	
	//==========ELEMENTOSVIEW===============================================
	private EditText edtAltEdif;
	private EditText edtEvAlt;
	private EditText edtAltTot;
	private EditText edtAltReta;
	private EditText edtObs;
	private Button btnGravar;
	
	private RadioGroup rgBase;
	private RadioButton rBtn_quad;
	private RadioButton rBtn_trian;
	
	private RadioGroup rgInclinado;
	private RadioButton rBtn_inc_sim;
	private RadioButton rBtn_inc_nao;
	
	private RadioGroup rgReto;
	private RadioButton rBtn_ret_sim;
	private RadioButton rBtn_ret_nao;
	
	private RadioGroup rgPoste;
	private RadioButton rBtn_poste_ret;
	private RadioButton rBtn_poste_con;
	
	private RadioGroup rgCantoneira;
	private RadioButton rBtn_cant_gr_1;
	private RadioButton rBtn_cant_gr_2;
	private RadioButton rBtn_cant_gr_3;
	private RadioButton rBtn_cant_gr_4;
	private RadioButton rBtn_cant_circ;
	private RadioButton rBtn_cant_int;
	private RadioButton rBtn_cant_ho_1;
	/*private RadioButton rBtn_cant_ho_2;*/
	
	private RadioGroup rgReforco;
	private RadioButton rBtn_reforco_sim;
	private RadioButton rBtn_reforco_nao;
	
	private RadioGroup rgPlataf;
	private RadioButton rBtn_plat_sim;
	private RadioButton rBtn_plat_nao;
	
	private LinearLayout boxTvAba;
	private LinearLayout boxRgAba;
	private RadioGroup rgAba;
	private RadioButton rBtn_aba_ps_1;
	private RadioButton rBtn_aba_ps_2;
	private RadioButton rBtn_aba_ps_3;
	private RadioButton rBtn_aba_ps_4;
	private RadioButton rBtn_aba_ps_5;
	
	//==========ELEMENTOSVIEW===============================================
	
	private String carregarTipo = "0";
	private boolean desabilitarSelecEv = true;
	private List<EditText> lstEditValidade = new ArrayList<EditText>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	//==========RESPOSTAS===============================================
	private double ALTTOTAL = 0;
	private String respInc="";
	private String respRet="";
	private boolean firstComboSite = true;
	private boolean firstComboEv = true;
	private Carregamento EVEXISTENTE;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.form_ev_fragment, null);
		try {
			//=======POPULAR SPINNER==========================================
			spinnerTipoSite = (Spinner) view.findViewById(R.id.spinnerTipoSite);
			spinnerEstrutura = (Spinner) view.findViewById(R.id.spinnerEvTipo);
			
			//=======POPULAR SPINNER==========================================
			FindElementosView(view);
			
			//=======VERIFICAR SE JA EXISTE CADASTRO DE ESTRUTURA=============
			EVEXISTENTE = carregamentoBLL.listarEv(getActivity(), OsMenuActitivity._OV_CHAMADO, "1A000");
			CarregarComboSite();
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR ONCREATE FRAGMENT EV",getActivity());
		}
		return view;
	}
	
	private void SelecionarSpinnerSiteNExistente(int position) throws Exception{
		try {
			List<String> lstTipoGr = new ArrayList<String>();
			List<String> lstTipoHo = new ArrayList<String>();
			tipoSite = spinnerTipoSite.getItemAtPosition(position).toString();
			lstCombo = comboBLL.listarByFiltro(getActivity(), "FS", null);
			if(carregarTipo.equals("1")){
				//============MONTA LST COMBO ESTRUTURA============
				for(int i=0; i < lstCombo.size();i++){
					if(tipoSite.toLowerCase().equals("rooftop")){
						if(lstCombo.get(i).getTIPO().subSequence(0, 4).equals("Roof") || 
								lstCombo.get(i).getTIPO().subSequence(0, 3).equals("Sem") ||
								lstCombo.get(i).getTIPO().subSequence(0, 7).equals("Escolha")){
							lstTipoHo.add(lstCombo.get(i).getTIPO());
						}
					}
					else if(tipoSite.toLowerCase().equals("greenfield")){
						if(!lstCombo.get(i).getTIPO().subSequence(0, 4).equals("Roof")){
							lstTipoGr.add(lstCombo.get(i).getTIPO());
						}
					}
				}
			}
			else{//DEFAULT = 0 SEMPRE GREENFIELD
				for(int i=0; i < lstCombo.size();i++){
					if(tipoSite.toLowerCase().equals("greenfield")){
						if(!lstCombo.get(i).getTIPO().subSequence(0, 4).equals("Roof")){
							lstTipoGr.add(lstCombo.get(i).getTIPO());
						}
					}
				}
				carregarTipo = "1";
			}
			//============MONTA LST COMBO ESTRUTURA============
			Desabilitar();
			if(tipoSite.toLowerCase().equals("greenfield")){
				edtEvAlt.setEnabled(true);
				spinnerEstrutura.setEnabled(true);
				//selectedComboEv_Edicao = null;
				CarregarComboEstrutura(lstTipoGr);
			}
			else if(tipoSite.toLowerCase().equals("rooftop")){
				edtAltEdif.setEnabled(true);
				edtEvAlt.setEnabled(true);
				spinnerEstrutura.setEnabled(true);
				//selectedComboEv_Edicao = null;
				CarregarComboEstrutura(lstTipoHo);
			}
			else if(tipoSite.toLowerCase().equals("indoor")){
				spinnerEstrutura.setEnabled(false);
				edtEvAlt.setEnabled(false);
				edtEvAlt.setText(null);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	private void SelecionarSpinnerSiteExistente() throws Exception{
		try {
			//========SELECIONAR SPINNER TIPO SITE
			for (int i = 0; i < comboTipoSite.length; i++) {
				if (EVEXISTENTE.getINFO_41() != null) {
					if (EVEXISTENTE.getINFO_41().equals(comboTipoSite[i])){
						spinnerTipoSite.setSelection(i);
					}
				}
			}
			//========SELECIONAR SPINNER TIPO SITE
			
			//============MONTA LST COMBO ESTRUTURA============
			List<String> lstTipoGr = new ArrayList<String>();
			List<String> lstTipoHo = new ArrayList<String>();
			tipoSite = EVEXISTENTE.getINFO_41();
			lstCombo = comboBLL.listarByFiltro(getActivity(), "FS", null);
			for(int i=0; i < lstCombo.size();i++){
				if(tipoSite.toLowerCase().equals("rooftop")){
					if(lstCombo.get(i).getTIPO().subSequence(0, 4).equals("Roof") || 
							lstCombo.get(i).getTIPO().subSequence(0, 3).equals("Sem") ||
							lstCombo.get(i).getTIPO().subSequence(0, 7).equals("Escolha")){
						lstTipoHo.add(lstCombo.get(i).getTIPO());
					}
				}
				else if(tipoSite.toLowerCase().equals("greenfield")){
					if(!lstCombo.get(i).getTIPO().subSequence(0, 4).equals("Roof")){
						lstTipoGr.add(lstCombo.get(i).getTIPO());
					}
				}
			}
			//============MONTA LST COMBO ESTRUTURA============
			
			Desabilitar();
			if(tipoSite.toLowerCase().equals("greenfield")){
				edtEvAlt.setEnabled(true);
				spinnerEstrutura.setEnabled(true);
				CarregarComboEstrutura(lstTipoGr);
			}
			else if(tipoSite.toLowerCase().equals("rooftop")){
				edtAltEdif.setEnabled(true);
				edtEvAlt.setEnabled(true);
				spinnerEstrutura.setEnabled(true);
				CarregarComboEstrutura(lstTipoHo);
			}
			else if(tipoSite.toLowerCase().equals("indoor")){
				spinnerEstrutura.setEnabled(false);
				edtEvAlt.setEnabled(false);
				edtEvAlt.setText(null);
				//OBSERVACAO
				if(EVEXISTENTE.getINFO_23() != null && !EVEXISTENTE.getINFO_23().isEmpty()){
					edtObs.setText(EVEXISTENTE.getINFO_23());
				}
				EVEXISTENTE = null;
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	private void CarregarComboSite() throws Exception{
		
			//=========CARREGA COMBO TIPO SITE
			lstCombo = comboBLL.listarByFiltro(getActivity(), "LC1", null);
			comboTipoSite = new String [lstCombo.size()];
			for(int i=0; i < lstCombo.size();i++){
				comboTipoSite[i] = lstCombo.get(i).getTITULO();
			}
			spinnerTipoSite.setAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, comboTipoSite));
			//spinnerTipoSite.setSelection(Adapter.NO_SELECTION, false);
			//=========CARREGA COMBO TIPO SITE
			
			spinnerTipoSite.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent,
						View view, int position, long id) {
					try {
						/*if(firstComboSite){*/
							if(EVEXISTENTE == null){
								//ESTRUTURA � EXISTENTE
								SelecionarSpinnerSiteNExistente(position);
							}
							else {
								//ESTRUTURA EXISTENTE
								SelecionarSpinnerSiteExistente();
								firstComboSite = false;
								carregarTipo = "1";
							}
						/*}
						else firstComboSite = true;*/
					}
					catch (Exception e) {
					}
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
	}
	
	private void SelecionarSpinnerEstruturaNExistente(int position) throws Exception{
		try {
			estrutura = spinnerEstrutura.getItemAtPosition(position).toString();
			try {
				if(desabilitarSelecEv){
					Desabilitar();
				}
				RbPlat("desabilita");
				Combo combo = comboBLL.listarByTipo(getActivity(), estrutura);
				HabilitarCampos(combo);
			} catch (Exception e) {
				LogErrorBLL.LogError(e.getMessage(), "ERROR select Estrutura",getActivity());
				e.printStackTrace();
			}
			desabilitarSelecEv = true;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	private void SelecionarSpinnerEstruturaExistente() throws Exception{
		try {
			if(EVEXISTENTE != null){
				Combo combo = comboBLL.listarBySigla(getActivity(), EVEXISTENTE.getINFO_01());
				estrutura = combo.getTIPO();
				//========HABILITAR E EXIBIR CAMPOS PARA QUANDO ROOF E GREEN===================================
				if(!estrutura.toLowerCase().equals("indoor")){
					
					//========SELECIONAR SPINNER ESTRUTURA
					for (int i = 0; i < comboEstrutura.length; i++) {
						if (EVEXISTENTE.getINFO_01() != null) {
							if (estrutura.equals(comboEstrutura[i])){
								spinnerEstrutura.setSelection(i);
							}
						}
					}
					//========SELECIONAR SPINNER ESTRUTURA
					
					//===========HABILITAR CAMPOS
					RbPlat("desabilita");
					HabilitarCamposExibirValores(combo);
					//===========HABILITAR Alt edifica��o PARA ROOF
					if(combo != null){
						if(EVEXISTENTE.getINFO_41() != null){
							if(EVEXISTENTE.getINFO_41().toLowerCase().equals("rooftop")){
								edtAltEdif.setEnabled(true);
								edtAltEdif.setText(EVEXISTENTE.getINFO_03().replace(",", "."));
							}
						}
					}
				}
				//========HABILITAR E EXIBIR CAMPOS PARA QUANDO ROOF E GREEN===================================
				//OBSERVACAO
				if(EVEXISTENTE.getINFO_23() != null && !EVEXISTENTE.getINFO_23().isEmpty()){
					edtObs.setText(EVEXISTENTE.getINFO_23());
				}
			}
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	private void CarregarComboEstrutura(List<String> lstComboEv){
		
		//=========CARREGA COMBO ESTRUTURA
		comboEstrutura = new String[lstComboEv.size()];
		for(int i=0; i< lstComboEv.size();i++){
			comboEstrutura[i] = lstComboEv.get(i);
		}
		spinnerEstrutura.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, comboEstrutura));
		//=========CARREGA COMBO ESTRUTURA
		
		desabilitarSelecEv = false;
		spinnerEstrutura.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				try {
					if(firstComboEv){//NECESS�RIO, POIS NO INICIO DO FRAGMENT O FLUXO PASSA POR AQUI 2X (?)
						if(EVEXISTENTE == null){
							//ESTRUTURA � EXISTENTE
							SelecionarSpinnerEstruturaNExistente(position);
						}
						else {
							//ESTRUTURA EXISTENTE
							SelecionarSpinnerEstruturaExistente();
							firstComboEv = false;
							EVEXISTENTE = null;
						}
					}
					else firstComboEv = true;
				}
				catch (Exception e) {
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private void HabilitarCampos(Combo combo) {
		if(combo.getCONTROLE_MSG().equals("S")){
			rBtn_quad.setEnabled(true);
			rBtn_trian.setEnabled(true);
		}
		else {
			rBtn_quad.setEnabled(false);
			rBtn_trian.setEnabled(false);
		}
		
		if(combo.getAEV().equals("S")){
			rBtn_inc_sim.setEnabled(true);
			rBtn_inc_nao.setEnabled(true);
		}
		else {
			rBtn_inc_sim.setEnabled(false);
			rBtn_inc_nao.setEnabled(false);
		}
		
		if(combo.getCLASSIFICACAO().equals("S")){
			rBtn_ret_nao.setEnabled(true);
			rBtn_ret_sim.setEnabled(true);
		}
		else {
			rBtn_ret_nao.setEnabled(false);
			rBtn_ret_sim.setEnabled(false);
		}
		
		if(combo.getOBSERVACAO_MASTER().equals("S")){
			rBtn_poste_ret.setEnabled(true);
			rBtn_poste_con.setEnabled(true);
		}
		else {
			rBtn_poste_ret.setEnabled(false);
			rBtn_poste_con.setEnabled(false);
		}
		
		if(combo.getCA().equals("S")){
			rBtn_reforco_sim.setEnabled(true);
			rBtn_reforco_nao.setEnabled(true);
		}
		else {
			rBtn_reforco_sim.setEnabled(false);
			rBtn_reforco_nao.setEnabled(false);
		}
		
		if(combo.getCONTROLE_ROTINA().equals("S")){
			rBtn_plat_sim.setEnabled(true);
			rBtn_plat_nao.setEnabled(true);
		}
		else {
			rBtn_plat_sim.setEnabled(false);
			rBtn_plat_nao.setEnabled(false);
		}
	}
	
	private void HabilitarCamposExibirValores(Combo combo) {
		if(combo.getCONTROLE_MSG().equals("S")){
			rBtn_quad.setEnabled(true);
			rBtn_trian.setEnabled(true);
			if(EVEXISTENTE.getINFO_47().equals("Q")){
				rBtn_quad.setChecked(true);
				RbBase_valores("quad", EVEXISTENTE.getINFO_48());
			}
			else if(EVEXISTENTE.getINFO_47().equals("T")){
				rBtn_trian.setChecked(true);
				RbBase_valores("trian", EVEXISTENTE.getINFO_48());
			}
		}
		else {
			rBtn_quad.setEnabled(false);
			rBtn_trian.setEnabled(false);
		}
		
		edtEvAlt.setEnabled(true);
		edtEvAlt.setText(EVEXISTENTE.getINFO_02().replace(",", "."));
		
		if(combo.getAEV().equals("S")){
			rBtn_inc_sim.setEnabled(true);
			rBtn_inc_nao.setEnabled(true);
			if(EVEXISTENTE.getINFO_42().equals("S")){
				rBtn_inc_sim.setChecked(true);
				RbHabilitarAltReta("sim", "inc");
			}
			else if(EVEXISTENTE.getINFO_42().equals("N")){
				rBtn_inc_nao.setChecked(true);
			}
		}
		else {
			rBtn_inc_sim.setEnabled(false);
			rBtn_inc_nao.setEnabled(false);
		}
		
		if(combo.getCLASSIFICACAO().equals("S")){
			rBtn_ret_nao.setEnabled(true);
			rBtn_ret_sim.setEnabled(true);
			if(EVEXISTENTE.getINFO_43().equals("S")){
				rBtn_ret_sim.setChecked(true);
				RbHabilitarAltReta("sim", "ret");
			}
			else if(EVEXISTENTE.getINFO_43().equals("N")){
				rBtn_ret_nao.setChecked(true);
			}
		}
		else {
			rBtn_ret_nao.setEnabled(false);
			rBtn_ret_sim.setEnabled(false);
		}
		
		if(EVEXISTENTE.getINFO_42() != null && EVEXISTENTE.getINFO_43() != null)
		if(EVEXISTENTE.getINFO_42().equals("S") && EVEXISTENTE.getINFO_43().equals("S")){
			edtAltReta.setText(EVEXISTENTE.getINFO_05().replace(",", "."));
		}
		
		if(combo.getOBSERVACAO_MASTER().equals("S")){
			rBtn_poste_ret.setEnabled(true);
			rBtn_poste_con.setEnabled(true);
			if(EVEXISTENTE.getINFO_44().equals("R")){
				rBtn_poste_ret.setChecked(true);
			}
			else if(EVEXISTENTE.getINFO_44().equals("C")){
				rBtn_poste_con.setChecked(true);
			}
		}
		else {
			rBtn_poste_ret.setEnabled(false);
			rBtn_poste_con.setEnabled(false);
		}
		
		if(combo.getCA().equals("S")){
			rBtn_reforco_sim.setEnabled(true);
			rBtn_reforco_nao.setEnabled(true);
			if(EVEXISTENTE.getINFO_45().equals("S")){
				rBtn_reforco_sim.setChecked(true);
			}
			else if(EVEXISTENTE.getINFO_45().equals("N")){
				rBtn_reforco_nao.setChecked(true);
			}
		}
		else {
			rBtn_reforco_sim.setEnabled(false);
			rBtn_reforco_nao.setEnabled(false);
		}
		
		if(combo.getCONTROLE_ROTINA().equals("S")){
			rBtn_plat_sim.setEnabled(true);
			rBtn_plat_nao.setEnabled(true);
			if(EVEXISTENTE.getINFO_46().equals("S")){
				rBtn_plat_sim.setChecked(true);
				RbPlat_valores(EVEXISTENTE.getINFO_49());
			}
			else if(EVEXISTENTE.getINFO_46().equals("N")){
				rBtn_plat_nao.setChecked(true);
			}
		}
		else {
			rBtn_plat_sim.setEnabled(false);
			rBtn_plat_nao.setEnabled(false);
		}
	}

	private void FindElementosView(View view) {
		edtAltEdif = (EditText) view.findViewById(R.id.edtAltEdif);
		edtAltEdif.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					SomarAltTotal();
				}
			}
		});
		lstEditValidade.add(edtAltEdif);
		
		edtEvAlt = (EditText) view.findViewById(R.id.edtEvAlt);
		edtEvAlt.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					SomarAltTotal();
				}
			}
		});
		edtAltTot = (EditText) view.findViewById(R.id.edtAltTot);
		edtAltReta = (EditText) view.findViewById(R.id.edtAltReta);
		btnGravar = (Button) view.findViewById(R.id.btnGravarEv);
		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BtnGravar();
			}
		});
		
		rgBase = (RadioGroup) view.findViewById(R.id.rg_base);
		rBtn_quad = (RadioButton) view.findViewById(R.id.rb_quad);
		rBtn_quad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbBase("quad", v);
			}
		});
		rBtn_trian = (RadioButton) view.findViewById(R.id.rb_trian);
		rBtn_trian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbBase("tria", v);
			}
		});
		
		rgInclinado = (RadioGroup) view.findViewById(R.id.rg_inclinado);
		rBtn_inc_sim = (RadioButton) view.findViewById(R.id.rb_inc_sim);
		rBtn_inc_sim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbHabilitarAltReta("sim", "inc");
			}
		});
		rBtn_inc_nao = (RadioButton) view.findViewById(R.id.rb_inc_nao);
		rBtn_inc_nao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbHabilitarAltReta("nao", "inc");
			}
		});
		
		rgReto = (RadioGroup) view.findViewById(R.id.rg_reto);
		rBtn_ret_sim = (RadioButton) view.findViewById(R.id.rb_ret_sim);
		rBtn_ret_sim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbHabilitarAltReta("sim", "ret");
			}
		});
		rBtn_ret_nao = (RadioButton) view.findViewById(R.id.rb_ret_nao);
		rBtn_ret_nao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbHabilitarAltReta("nao", "ret");
			}
		});
		
		rgPoste = (RadioGroup) view.findViewById(R.id.rg_poste);
		rBtn_poste_ret = (RadioButton) view.findViewById(R.id.rb_poste_ret);
		rBtn_poste_con = (RadioButton) view.findViewById(R.id.rb_poste_con);
		
		rgCantoneira = (RadioGroup) view.findViewById(R.id.rg_canto);
		rBtn_cant_gr_1 = (RadioButton) view.findViewById(R.id.rb_cant_gr_1);
		rBtn_cant_gr_2 = (RadioButton) view.findViewById(R.id.rb_cant_gr_2);
		rBtn_cant_gr_3 = (RadioButton) view.findViewById(R.id.rb_cant_gr_3);
		rBtn_cant_gr_4 = (RadioButton) view.findViewById(R.id.rb_cant_gr_4);
		rBtn_cant_circ = (RadioButton) view.findViewById(R.id.rb_cant_circ);
		rBtn_cant_int = (RadioButton) view.findViewById(R.id.rb_cant_int);
		rBtn_cant_ho_1 = (RadioButton) view.findViewById(R.id.rb_cant_ho_1);
		/*rBtn_cant_ho_2 = (RadioButton) view.findViewById(R.id.rb_cant_ho_2);*/
		
		rgReforco = (RadioGroup) view.findViewById(R.id.rg_reforco);
		rBtn_reforco_sim = (RadioButton) view.findViewById(R.id.rb_reforco_sim);
		rBtn_reforco_nao = (RadioButton) view.findViewById(R.id.rb_reforco_nao);
		
		rgPlataf = (RadioGroup) view.findViewById(R.id.rg_plat);
		rBtn_plat_sim = (RadioButton) view.findViewById(R.id.rb_plat_sim);
		rBtn_plat_sim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbPlat("sim");
			}
		});
		rBtn_plat_nao = (RadioButton) view.findViewById(R.id.rb_plat_nao);
		rBtn_plat_nao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RbPlat("nao");
			}
		});
		
		boxTvAba = (LinearLayout) view.findViewById(R.id.ll_tv_aba);
		boxRgAba = (LinearLayout) view.findViewById(R.id.ll_rg_aba);
		rgAba = (RadioGroup) view.findViewById(R.id.rg_aba);
		rBtn_aba_ps_1 = (RadioButton) view.findViewById(R.id.rb_aba_ps_1);
		rBtn_aba_ps_2 = (RadioButton) view.findViewById(R.id.rb_aba_ps_2);
		rBtn_aba_ps_3 = (RadioButton) view.findViewById(R.id.rb_aba_ps_3);
		rBtn_aba_ps_4 = (RadioButton) view.findViewById(R.id.rb_aba_ps_4);
		rBtn_aba_ps_5 = (RadioButton) view.findViewById(R.id.rb_aba_ps_5);
		
		edtObs = (EditText) view.findViewById(R.id.edtObs);
		
		Desabilitar();
	}
	
	
	private void SomarAltTotal() {
		ALTTOTAL = 0;
		if(!edtAltEdif.getText().toString().equals("")){
			ALTTOTAL = ALTTOTAL + Double.valueOf(edtAltEdif.getText().toString()); 
		}
		if(!edtEvAlt.getText().toString().equals("")){
			ALTTOTAL = ALTTOTAL + Double.valueOf(edtEvAlt.getText().toString()); 
		}
		edtAltTot.setText(String.valueOf(ALTTOTAL));		
	}
	
	private void RbPlat(String resp) {
		if(resp.equals("sim")){
			boxTvAba.setVisibility(View.VISIBLE);
			boxRgAba.setVisibility(View.VISIBLE);
		}
		else if(resp.equals("nao")){
			boxTvAba.setVisibility(View.GONE);
			boxRgAba.setVisibility(View.GONE);
		}
		else {
			rgPlataf.clearCheck();
			boxTvAba.setVisibility(View.GONE);
			boxRgAba.setVisibility(View.GONE);
		}
	}
	
	private void RbPlat_valores(String imgPlat) {
		boxTvAba.setVisibility(View.VISIBLE);
		boxRgAba.setVisibility(View.VISIBLE);
		if(imgPlat.equals("P1")){
			rBtn_aba_ps_1.setChecked(true);
		}
		else if(imgPlat.equals("P2")){
			rBtn_aba_ps_2.setChecked(true);
		}
		else if(imgPlat.equals("P3")){
			rBtn_aba_ps_3.setChecked(true);
		}
		else if(imgPlat.equals("P4")){
			rBtn_aba_ps_4.setChecked(true);
		}
		else if(imgPlat.equals("P6")){
			rBtn_aba_ps_5.setChecked(true);
		}
	}
	
	@SuppressWarnings("static-access")
	private void RbBase(String check, View v) {
		rBtn_cant_circ.setVisibility(v.VISIBLE);
		rBtn_cant_circ.setEnabled(true);
		rBtn_cant_int.setVisibility(v.VISIBLE);
		rBtn_cant_int.setEnabled(true);
		if(check.equals("quad")){
			rBtn_cant_gr_1.setVisibility(v.GONE);
			rBtn_cant_gr_1.setEnabled(false);
			rBtn_cant_gr_2.setVisibility(v.GONE);
			rBtn_cant_gr_2.setEnabled(false);
			rBtn_cant_gr_3.setVisibility(v.GONE);
			rBtn_cant_gr_3.setEnabled(false);
			rBtn_cant_gr_4.setVisibility(v.GONE);
			rBtn_cant_gr_4.setEnabled(false);
			
			rBtn_cant_ho_1.setVisibility(v.VISIBLE);
			rBtn_cant_ho_1.setEnabled(true);
			/*rBtn_cant_ho_2.setVisibility(v.VISIBLE);
			rBtn_cant_ho_2.setEnabled(true);*/
		}
		else {
			rBtn_cant_ho_1.setVisibility(v.GONE);
			rBtn_cant_ho_1.setEnabled(false);
			/*rBtn_cant_ho_2.setVisibility(v.GONE);
			rBtn_cant_ho_2.setEnabled(false);*/
			
			rBtn_cant_gr_1.setVisibility(v.VISIBLE);
			rBtn_cant_gr_1.setEnabled(true);
			rBtn_cant_gr_2.setVisibility(v.VISIBLE);
			rBtn_cant_gr_2.setEnabled(true);
			rBtn_cant_gr_3.setVisibility(v.VISIBLE);
			rBtn_cant_gr_3.setEnabled(true);
			rBtn_cant_gr_4.setVisibility(v.VISIBLE);
			rBtn_cant_gr_4.setEnabled(true);
		}
	}
	
	private void RbBase_valores(String check, String imgCant) {
		rBtn_cant_circ.setVisibility(View.VISIBLE);
		rBtn_cant_circ.setEnabled(true);
		if(imgCant.equals("Q3") || imgCant.equals("T3")) rBtn_cant_circ.setChecked(true); 
		rBtn_cant_int.setVisibility(View.VISIBLE);
		rBtn_cant_int.setEnabled(true);
		if(imgCant.equals("Q6") || imgCant.equals("T6")) rBtn_cant_int.setChecked(true);
		if(check.equals("quad")){
			rBtn_cant_gr_1.setVisibility(View.GONE);
			rBtn_cant_gr_1.setEnabled(false);
			rBtn_cant_gr_2.setVisibility(View.GONE);
			rBtn_cant_gr_2.setEnabled(false);
			rBtn_cant_gr_3.setVisibility(View.GONE);
			rBtn_cant_gr_3.setEnabled(false);
			rBtn_cant_gr_4.setVisibility(View.GONE);
			rBtn_cant_gr_4.setEnabled(false);
			
			rBtn_cant_ho_1.setVisibility(View.VISIBLE);
			rBtn_cant_ho_1.setEnabled(true);
			if(imgCant.equals("Q1")) rBtn_cant_ho_1.setChecked(true);
		}
		else {
			rBtn_cant_ho_1.setVisibility(View.GONE);
			rBtn_cant_ho_1.setEnabled(false);
			
			rBtn_cant_gr_1.setVisibility(View.VISIBLE);
			rBtn_cant_gr_1.setEnabled(true);
			if(imgCant.equals("T1")) rBtn_cant_gr_1.setChecked(true);
			rBtn_cant_gr_2.setVisibility(View.VISIBLE);
			rBtn_cant_gr_2.setEnabled(true);
			if(imgCant.equals("T2")) rBtn_cant_gr_2.setChecked(true);
			rBtn_cant_gr_3.setVisibility(View.VISIBLE);
			rBtn_cant_gr_3.setEnabled(true);
			if(imgCant.equals("T4")) rBtn_cant_gr_3.setChecked(true);
			rBtn_cant_gr_4.setVisibility(View.VISIBLE);
			rBtn_cant_gr_4.setEnabled(true);
			if(imgCant.equals("T5")) rBtn_cant_gr_4.setChecked(true);
		}
	}
	
	private void RbHabilitarAltReta(String resp, String check) {
		if(resp.equals("sim")){
			if(check.equals("inc")){
				respInc = "S";
			}
			else {
				respRet = "S";
			}
		}
		else {
			if(check.equals("inc")){
				respInc = "N";
			}
			else {
				respRet = "N";
			}
		}
		if(!respInc.equals("") && respInc.equals("S") && !respRet.equals("") && respRet.equals("S")){
			edtAltReta.setEnabled(true);
		}
		else edtAltReta.setEnabled(false);
	}
	
	private void BtnGravar() {
		try {
			boolean validador = true;
			Carregamento carregamento = new Carregamento();
			carregamento.setINFO_41(tipoSite);
			if(tipoSite.toLowerCase().equals("rooftop") || tipoSite.toLowerCase().equals("greenfield")){
				if(tipoSite.toLowerCase().equals("rooftop")){
					if(edtAltEdif.getText().toString().isEmpty()){
						validador = false;
						edtAltEdif.setError("Informe o valor para o campo!");
					}
					else carregamento.setINFO_03(edtAltEdif.getText().toString().replace(".", ","));
						
					if(edtEvAlt.getText().toString().isEmpty()){
						validador = false;
						edtEvAlt.setError("Informe o valor para o campo!");
					}
					else carregamento.setINFO_02(edtEvAlt.getText().toString().replace(".", ","));
				}
				else if(tipoSite.toLowerCase().equals("greenfield")){
					if(edtEvAlt.getText().toString().isEmpty()){
						validador = false;
						edtEvAlt.setError("Informe o valor para o campo!");
					}
					else carregamento.setINFO_02(edtEvAlt.getText().toString().replace(".", ","));
				}
				if(estrutura.equals("Escolha uma Op��o")){
					validador = false;
					Toast.makeText(getActivity(), "Escolha uma op��o para Estrutura Tipo!", Toast.LENGTH_LONG).show();
				}
				else{
					Combo combo = comboBLL.listarByTipo(getActivity(), estrutura);
					if(combo != null)
					carregamento.setINFO_01(combo.getSIGLA());
					else carregamento.setINFO_01(null);
				}
				
				if(validador){
					ValidarCombos(carregamento);
				}
			}
			else if(tipoSite.toLowerCase().equals("indoor")){
				GravarIndoor(carregamento);
			}
		}
		catch (Exception e) {
			Toast.makeText(getActivity(), "Problemas ao Gravar Estrutura, verifique!", Toast.LENGTH_LONG).show();
		}
	}

	private void GravarIndoor(Carregamento _carregamento) throws Exception {
		try {
			if(!edtObs.getText().toString().isEmpty()){
				_carregamento.setINFO_23(edtObs.getText().toString());
			}
			//======== DADOS PADR�O DO CARREGAMENTO==================
			_carregamento.setCODIGO(OsMenuActitivity._OV_CHAMADO);
			_carregamento.setCAMPO_TIPO("1A000");
			_carregamento.setVALIDADE("T");
			_carregamento.setCOD_ENTIDADE(OsMenuActitivity._OS.getCOD_ENTIDADE());
			_carregamento.setLOTE_CONTROLE(OsMenuActitivity._OS.getLOTE_CONTROLE());
			_carregamento.setOS_INSERCAO(OsMenuActitivity._OV_CHAMADO);
			_carregamento.setFLAG_CONDICAO("A");
			_carregamento.setESTRUT_ORDEM(OsMenuActitivity._OS.getESTRUT_ORDEM());
			_carregamento.setORDENADOR(OsMenuActitivity._OV_CHAMADO+"-"+_carregamento.getCAMPO_TIPO());
			_carregamento.setATUALIZACAO_DATA(dateFormat.format(new Date()));
			_carregamento.setINSERCAO_DATA(dateFormat.format(new Date()));
			_carregamento.setINSERCAO_NOME("MOBILE");
			_carregamento.setATUALIZACAO_NOME("MOBILE");
			_carregamento.setINFO_53(dateFormat.format(new Date()));
			//======== DADOS PADR�O DO CARREGAMENTO==================
			
			Carregamento carregamento = carregamentoBLL.listarEv(getActivity(), OsMenuActitivity._OV_CHAMADO, "1A000");
			if(carregamento != null){
				// EDICAO
				_carregamento.setLINHA(carregamento.getLINHA());
				if(carregamentoBLL.update(getActivity(), _carregamento) == 1){
					Toast.makeText(getActivity(), "Estrutura Gravada com Sucesso!", Toast.LENGTH_LONG).show();
				}
				else Toast.makeText(getActivity(), "Problemas ao Gravar Estrutura!", Toast.LENGTH_LONG).show();
			}
			else {
				// NOVO
				if(carregamentoBLL.Insert(getActivity(), _carregamento) > 0){
					Toast.makeText(getActivity(), "Estrutura Gravada com Sucesso!", Toast.LENGTH_LONG).show();
				}
				else Toast.makeText(getActivity(), "Problemas ao Gravar Estrutura!", Toast.LENGTH_LONG).show();
			}
		}
		catch (Exception e) {
			throw e;
		}
		
	}

	private void ValidarCombos(Carregamento _carregamento) throws Exception {
		try {
			boolean validador = true;
			boolean trechoIncSim = false;
			boolean trechoRetoSim = false;
			Combo combo = comboBLL.listarByTipo(getActivity(), estrutura);
			if(combo.getCONTROLE_MSG().equals("S")){
				switch (rgBase.getCheckedRadioButtonId()) {
				case R.id.rb_quad:
					_carregamento.setINFO_47("Q");
					switch (rgCantoneira.getCheckedRadioButtonId()) {
					case R.id.rb_cant_ho_1:
						_carregamento.setINFO_48("Q1");
						break;
					/*case R.id.rb_cant_ho_2:
						
						break;*/
					case R.id.rb_cant_circ:
						_carregamento.setINFO_48("Q3");
						break;
					case R.id.rb_cant_int:
						_carregamento.setINFO_48("Q6");
						break;
					default:
						validador = false;
						Toast.makeText(getActivity(), "Selecione uma Cantoneira!", Toast.LENGTH_LONG).show();
						break;
					}
					break;
				case R.id.rb_trian:
					_carregamento.setINFO_47("T");
					switch (rgCantoneira.getCheckedRadioButtonId()) {
					case R.id.rb_cant_gr_1:
						_carregamento.setINFO_48("T1");
						break;
					case R.id.rb_cant_gr_2:
						_carregamento.setINFO_48("T2");
						break;
					case R.id.rb_cant_gr_3:
						_carregamento.setINFO_48("T4");
						break;
					case R.id.rb_cant_gr_4:
						_carregamento.setINFO_48("T5");
						break;
					case R.id.rb_cant_circ:
						_carregamento.setINFO_48("T3");
						break;
					case R.id.rb_cant_int:
						_carregamento.setINFO_48("T6");
						break;
					default:
						validador = false;
						Toast.makeText(getActivity(), "Selecione uma Cantoneira!", Toast.LENGTH_LONG).show();
						break;
					}
					break;
				default:
					validador = false;
					Toast.makeText(getActivity(), "Marque Estrutura Base!", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if(combo.getAEV().equals("S")){
				switch (rgInclinado.getCheckedRadioButtonId()) {
				case R.id.rb_inc_sim:
					_carregamento.setINFO_42("S");
					trechoIncSim = true;
					break;
				case R.id.rb_inc_nao:
					_carregamento.setINFO_42("N");
					break;
				default:
					validador = false;
					Toast.makeText(getActivity(), "Marque Trecho Inclinado!", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if(combo.getCLASSIFICACAO().equals("S")){
				switch (rgReto.getCheckedRadioButtonId()) {
				case R.id.rb_ret_sim:
					_carregamento.setINFO_43("S");
					trechoRetoSim = true;
					break;
				case R.id.rb_ret_nao:
					_carregamento.setINFO_43("N");
					break;
				default:
					validador = false;
					Toast.makeText(getActivity(), "Marque Trecho Reto!", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if(trechoIncSim && trechoRetoSim){
				if(edtAltReta.getText().toString().isEmpty()){
					validador = false;
					edtAltReta.setError("Informe o valor para o campo!");
				}
				else _carregamento.setINFO_05(edtAltReta.getText().toString().replace(".", ","));
			}
			if(combo.getOBSERVACAO_MASTER().equals("S")){
				switch (rgPoste.getCheckedRadioButtonId()) {
				case R.id.rb_poste_ret:
					_carregamento.setINFO_44("R");
					break;
				case R.id.rb_poste_con:
					_carregamento.setINFO_44("C");
					break;
				default:
					validador = false;
					Toast.makeText(getActivity(), "Marque Poste!", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if(combo.getCA().equals("S")){
				switch (rgReforco.getCheckedRadioButtonId()) {
				case R.id.rb_reforco_sim:
					_carregamento.setINFO_45("S");
					break;
				case R.id.rb_reforco_nao:
					_carregamento.setINFO_45("N");
					break;
				default:
					validador = false;
					Toast.makeText(getActivity(), "Marque Refor�o!", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if(combo.getCONTROLE_ROTINA().equals("S")){
				switch (rgPlataf.getCheckedRadioButtonId()) {
				case R.id.rb_plat_sim:
					_carregamento.setINFO_46("S");
					switch (rgAba.getCheckedRadioButtonId()) {
					case R.id.rb_aba_ps_1:
						_carregamento.setINFO_49("P1");
						break;
					case R.id.rb_aba_ps_2:
						_carregamento.setINFO_49("P2");
						break;
					case R.id.rb_aba_ps_3:
						_carregamento.setINFO_49("P3");
						break;
					case R.id.rb_aba_ps_4:
						_carregamento.setINFO_49("P4");
						break;
					case R.id.rb_aba_ps_5:
						_carregamento.setINFO_49("P6");
						break;
					default:
						validador = false;
						Toast.makeText(getActivity(), "Marque uma Aba para Plat. Topo!", Toast.LENGTH_LONG).show();
						break;
					}
					break;
				case R.id.rb_plat_nao:
					_carregamento.setINFO_46("N");
					break;
				default:
					validador = false;
					Toast.makeText(getActivity(), "Marque Plataforma de Topo!", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if(!edtObs.getText().toString().isEmpty()){
				_carregamento.setINFO_23(edtObs.getText().toString());
			}
			
			//======== DADOS PADR�O DO CARREGAMENTO==================
			_carregamento.setCODIGO(OsMenuActitivity._OV_CHAMADO);
			_carregamento.setCAMPO_TIPO("1A000");
			_carregamento.setVALIDADE("T");
			_carregamento.setCOD_ENTIDADE(OsMenuActitivity._OS.getCOD_ENTIDADE());
			_carregamento.setLOTE_CONTROLE(OsMenuActitivity._OS.getLOTE_CONTROLE());
			_carregamento.setOS_INSERCAO(OsMenuActitivity._OV_CHAMADO);
			_carregamento.setFLAG_CONDICAO("A");
			_carregamento.setESTRUT_ORDEM(OsMenuActitivity._OS.getESTRUT_ORDEM());
			_carregamento.setORDENADOR(OsMenuActitivity._OV_CHAMADO+"-"+_carregamento.getCAMPO_TIPO());
			_carregamento.setATUALIZACAO_DATA(dateFormat.format(new Date()));
			_carregamento.setINSERCAO_DATA(dateFormat.format(new Date()));
			_carregamento.setINSERCAO_NOME("MOBILE");
			_carregamento.setATUALIZACAO_NOME("MOBILE");
			_carregamento.setINFO_53(dateFormat.format(new Date()));
			//======== DADOS PADR�O DO CARREGAMENTO==================
			
			if(validador){
				Carregamento carregamento = carregamentoBLL.listarEv(getActivity(), OsMenuActitivity._OV_CHAMADO, "1A000");
				if(carregamento != null){
					// EDICAO
					_carregamento.setLINHA(carregamento.getLINHA());
					if(carregamentoBLL.update(getActivity(), _carregamento) == 1){
						Toast.makeText(getActivity(), "Estrutura Gravada com Sucesso!", Toast.LENGTH_LONG).show();
					}
					else Toast.makeText(getActivity(), "Problemas ao Gravar Estrutura!", Toast.LENGTH_LONG).show();
				}
				else {
					// NOVO
					if(carregamentoBLL.Insert(getActivity(), _carregamento) > 0){
						Toast.makeText(getActivity(), "Estrutura Gravada com Sucesso!", Toast.LENGTH_LONG).show();
					}
					else Toast.makeText(getActivity(), "Problemas ao Gravar Estrutura!", Toast.LENGTH_LONG).show();
				}
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR Gravar Estrutura",getActivity());
			throw e;
		}
	}
	

	private void Desabilitar() {
		if(tipoSite != null){
			if(!tipoSite.toLowerCase().equals("rooftop")){
				edtAltEdif.setText(null);
				edtAltEdif.setEnabled(false);
			}
		}
		
		edtAltTot.setEnabled(false);
		edtAltTot.setText(null);
		
		edtAltReta.setEnabled(false);
		edtAltReta.setText(null);
		
		rgBase.clearCheck();
		rBtn_quad.setEnabled(false);
		rBtn_trian.setEnabled(false);
		
		rgInclinado.clearCheck();
		rBtn_inc_sim.setEnabled(false);
		rBtn_inc_nao.setEnabled(false);
		
		rgReto.clearCheck();
		rBtn_ret_sim.setEnabled(false);
		rBtn_ret_nao.setEnabled(false);

		rgPoste.clearCheck();
		rBtn_poste_ret.setEnabled(false);
		rBtn_poste_con.setEnabled(false);

		DesabilitarCantoneiras();
		
		rgReforco.clearCheck();
		rBtn_reforco_sim.setEnabled(false);
		rBtn_reforco_nao.setEnabled(false);
		
		rgPlataf.clearCheck();
		rBtn_plat_sim.setEnabled(false);
		rBtn_plat_nao.setEnabled(false);
		
		boxRgAba.setVisibility(View.GONE);
		boxTvAba.setVisibility(View.GONE);
		rgAba.clearCheck();
	}
	
	
	private void DesabilitarCantoneiras(){
		rgCantoneira.clearCheck();
		rBtn_cant_gr_1.setVisibility(View.GONE);
		rBtn_cant_gr_1.setEnabled(false);
		rBtn_cant_gr_2.setVisibility(View.GONE);
		rBtn_cant_gr_2.setEnabled(false);
		rBtn_cant_gr_3.setVisibility(View.GONE);
		rBtn_cant_gr_3.setEnabled(false);
		rBtn_cant_gr_4.setVisibility(View.GONE);
		rBtn_cant_gr_4.setEnabled(false);
		rBtn_cant_circ.setVisibility(View.GONE);
		rBtn_cant_circ.setEnabled(false);
		rBtn_cant_int.setVisibility(View.GONE);
		rBtn_cant_int.setEnabled(false);
		rBtn_cant_ho_1.setVisibility(View.GONE);
		rBtn_cant_ho_1.setEnabled(false);
		/*rBtn_cant_ho_2.setVisibility(8);
		rBtn_cant_ho_2.setEnabled(false);*/
	}
	
}
