package base.htcom.com.br.ppipdiapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.async.AsyncConfirmarEnvOS;
import base.htcom.com.br.ppipdiapp.async.AsyncEnviarArqPref;
import base.htcom.com.br.ppipdiapp.async.AsyncEnviarBateria;
import base.htcom.com.br.ppipdiapp.async.AsyncEnviarCarregamento;
import base.htcom.com.br.ppipdiapp.async.TarefaInterfaceEnviar;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.BateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusBateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusCarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Bateria;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;

public class EnviarOSFragment extends BaseFragment {
	
	//=======ENVIO============================
		public static int cEnvioArqPref=0;
		public static int contaEnvArqPref=0;
		private List<ArqPref> lstArqPrefEnvio = new ArrayList<ArqPref>();
		public static int cEnvioCarregamento=0;
		public static int contaEnvCarregamento=0;
		private List<Carregamento> lstCarregamentoEnvio = new ArrayList<Carregamento>();
		public static int cEnvioBateria=0;
		public static int contaEnvBateria=0;
		private List<Bateria> lstBateriasEnvio = new ArrayList<Bateria>();
		private List<Os> lstOsFinalizada;
		public static int contaConfEnvOs=0;
		public static int cEnvioConfEnvOs=0;
		private boolean enviarConfirmacaoOS = true;
	//=======ENVIO============================	
		private TarefaInterfaceEnviar ti;
		private StatusOsBLL statusOsBLL = new StatusOsBLL();
		private ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
		private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
		private BateriaBLL bateriaBLL = new BateriaBLL();
		private StatusArqPrefBLL statusArqPrefBLL = new StatusArqPrefBLL();
		private StatusCarregamentoBLL statusCarregamentoBLL = new StatusCarregamentoBLL();
		private StatusBateriaBLL statusBateriaBLL = new  StatusBateriaBLL();
		private Gson gson;
		public static List<String> jsonArqPrefsEnviados;
		public static List<String> jsonCarregamentosEnviados;
		public static List<String> jsonBateriasEnviados;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		EnviarOS();
		return view;
	}
	
	
	//==================ENVIO OS=====================================================
		private void EnviarOS() {
			try {
				
				ti = new  TarefaInterfaceEnviar() {
					
					@Override
					public void respostaAsyncEnvioArqPref(String json) {
						try {
							if(cEnvioArqPref == lstArqPrefEnvio.size() && cEnvioCarregamento == lstCarregamentoEnvio.size() && cEnvioBateria == lstBateriasEnvio.size()){
								//ROTINA DE ENVIO NAS ASYNC DE CARR, ARQPREF, BAt FOI FINALIZADA
								
								//=======INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA============
								if(enviarConfirmacaoOS){
									enviarConfirmacaoOS = false;
									EnviarConfirmacaoOS();
								}
								//=======INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA============
							}
						}
						catch (Exception e) {
							ZerarCounts();
						}
					}
					
					@Override
					public void respostaAsyncEnvioCarregamento(String json) {
						try {
							if(cEnvioArqPref == lstArqPrefEnvio.size() && cEnvioCarregamento == lstCarregamentoEnvio.size() && cEnvioBateria == lstBateriasEnvio.size()){
								//ROTINA DE ENVIO NAS ASYNC DE CARR, ARQPREF, BAt FOI FINALIZADA
								
								//=======INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA============
								if(enviarConfirmacaoOS){
									enviarConfirmacaoOS = false;
									EnviarConfirmacaoOS();
								}
								//=======INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA============
							}
						}
						catch (Exception e) {
							ZerarCounts();
						}
					}
					
					@Override
					public void respostaAsyncEnvioBateria(String json) {
						try {
							if(cEnvioArqPref == lstArqPrefEnvio.size() && cEnvioCarregamento == lstCarregamentoEnvio.size() && cEnvioBateria == lstBateriasEnvio.size()){
								//ROTINA DE ENVIO NAS ASYNC DE CARR, ARQPREF, BAt FOI FINALIZADA
								
								//=======INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA============
								if(enviarConfirmacaoOS){
									enviarConfirmacaoOS = false;
									EnviarConfirmacaoOS();
								}
								//=======INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA============
							}
						}
						catch (Exception e) {
							ZerarCounts();
						}
					}
					
					@Override
					public void respostaAsyncConfirmaEnvOs(String json) {
						try {
							if(json.equals("true")){
								//CONFIRMA ENVIO
								contaConfEnvOs++;
							}
							if(cEnvioConfEnvOs == lstOsFinalizada.size()){
								if(cEnvioArqPref > 0 || cEnvioCarregamento > 0 || cEnvioBateria > 0){
									//ATUALIZA CAMPO ENVIADO = 1 NA TAB OS_STATUS
									for(int i=0; i < lstOsFinalizada.size();i++){
										statusOsBLL.updateStatus(getActivity(), lstOsFinalizada.get(i).getLINHA(), "arqpref");
									}
									new AlertaDialog(getActivity()).showDialogAviso("Confirmação Envio", "ETP Confirmada: "+contaConfEnvOs+" | Arquivos Preferenciais: "+contaEnvArqPref+" | Carregamentos: "+contaEnvCarregamento+" | Baterias: "+contaEnvBateria+" ");
								}
								ZerarCounts();
							}
						}
						catch (Exception e) {
							ZerarCounts();
						}
						
					}
				};
				
				lstOsFinalizada = statusOsBLL.listarRegistros(getActivity(),"MainActivity.USER","MainActivity.EMPRESA");
				if(lstOsFinalizada.size() > 0){
					for(int i=0; i < lstOsFinalizada.size();i++){
						//============ARQ PREFS =================================
						//VERIFICA SE TEM ARQ PREFS A SEREM ENVIADOS
						List<ArqPref> lstArqPrefs = new ArrayList<ArqPref>();
						lstArqPrefs = arqPrefBLL.listarByOvChamado(getActivity(), lstOsFinalizada.get(i).getOV_CHAMADO_NUM());
						if(lstArqPrefs != null){
							//GUARDA AS LISTARQPREFS QUANDO POSSUI REGISTROS
							lstArqPrefEnvio.addAll(lstArqPrefs);
						}
						else {
							Toast.makeText(getActivity(), "Nenhum Arquivo Preferencial para a ETP: "+lstOsFinalizada.get(i).getOV_CHAMADO_NUM()+"", Toast.LENGTH_SHORT).show();
						}
						//============ARQ PREFS =================================
						
						//============CARREGAMENTOS =================================
						//VERIFICA SE TEM CARREGAMENTOS A SEREM ENVIADOS
						List<Carregamento> lstCarregamentos = new ArrayList<Carregamento>();
						lstCarregamentos = carregamentoBLL.listarByCodigoAll(getActivity(), lstOsFinalizada.get(i).getOV_CHAMADO_NUM());
						if(lstCarregamentos != null){
							//GUARDA AS LISTCARREGAMENTOS QUANDO POSSUI REGISTROS
							lstCarregamentoEnvio.addAll(lstCarregamentos);
						}
						else {
							Toast.makeText(getActivity(), "Nenhum Carregamento para a ETP: "+lstOsFinalizada.get(i).getOV_CHAMADO_NUM()+"", Toast.LENGTH_SHORT).show();
						}
						//============CARREGAMENTOS =================================
						
						//============BATERIAS =================================
						//VERIFICA SE TEM BATERIAS A SEREM ENVIADAS
						List<Bateria> lstBaterias = new ArrayList<Bateria>();
						lstBaterias = bateriaBLL.listarByOvChamado(getActivity(), lstOsFinalizada.get(i).getOV_CHAMADO_NUM());
						if(lstBaterias != null){
							//GUARDA AS LISTBATERIAS QUANDO POSSUI REGISTROS
							lstBateriasEnvio.addAll(lstBaterias);
						}
						else {
							Toast.makeText(getActivity(), "Nenhuma Bateria para a ETP: "+lstOsFinalizada.get(i).getOV_CHAMADO_NUM()+"", Toast.LENGTH_SHORT).show();
						}
						//============BATERIAS =================================
					}
					
					//======ENVIO ARQ PREF============================
					jsonArqPrefsEnviados = new ArrayList<String>();
					if(lstArqPrefEnvio.size() > 0){
						gson = new GsonBuilder().create();
						JsonArray jsonArrayArq = gson.toJsonTree(lstArqPrefEnvio).getAsJsonArray();
						for(int y=0;y< jsonArrayArq.size();y++){
							JsonElement jsonArqPref = jsonArrayArq.get(y);
							new AsyncEnviarArqPref(getActivity(), ti).execute(jsonArqPref.toString());
						}
					}
					else Toast.makeText(getActivity(), "Nenhum Arquivo Preferencial a ser enviado!", Toast.LENGTH_SHORT).show();
					
					//======ENVIO CARREGAMENTOS============================
					jsonCarregamentosEnviados = new ArrayList<String>();
					if(lstCarregamentoEnvio.size() > 0){
						gson = new GsonBuilder().create();
						JsonArray jsonArrayCar = gson.toJsonTree(lstCarregamentoEnvio).getAsJsonArray();
						for(int y=0;y< jsonArrayCar.size();y++){
							JsonElement jsonCar = jsonArrayCar.get(y);
							new AsyncEnviarCarregamento(getActivity(), ti).execute(jsonCar.toString());
						}
					}
					else Toast.makeText(getActivity(), "Nenhum Carregamento a ser enviado!", Toast.LENGTH_SHORT).show();
					
					//======ENVIO BATERIAS============================
					jsonBateriasEnviados = new ArrayList<String>();
					if(lstBateriasEnvio.size() > 0){
						gson = new GsonBuilder().create();
						JsonArray jsonArrayBat = gson.toJsonTree(lstBateriasEnvio).getAsJsonArray();
						for(int y=0;y< jsonArrayBat.size();y++){
							JsonElement jsonBat = jsonArrayBat.get(y);
							new AsyncEnviarBateria(getActivity(), ti).execute(jsonBat.toString());
						}
					}
					else Toast.makeText(getActivity(), "Nenhuma Bateria a ser enviada!", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getActivity(), "Nenhuma ETP a ser Enviada!", Toast.LENGTH_SHORT).show();
				}
			}
			catch (Exception e) {
				LogErrorBLL.LogError(e.getMessage(), "ERROR ENVIO DE OS",getActivity());
				Toast.makeText(getActivity(), "Problemas ao Enviar ETP! Erro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		}
		
		private void EnviarConfirmacaoOS(){
			try {
				//======REGISTRA LISTA DE ENVIO PARA ARQPREF, CAR, BAT 
				for(int i=0;i < jsonArqPrefsEnviados.size();i++){
					gson = new GsonBuilder().create();
					ArqPref arqPref = gson.fromJson(jsonArqPrefsEnviados.get(i), ArqPref.class);
					if(statusArqPrefBLL.Insert(getActivity(), arqPref.getLINHA()) != -1){
						contaEnvArqPref++;
					}
				}
				for(int i=0; i < jsonCarregamentosEnviados.size();i++){
					gson = new GsonBuilder().create();
					Carregamento carregamento = gson.fromJson(jsonCarregamentosEnviados.get(i), Carregamento.class);
					if(statusCarregamentoBLL.Insert(getActivity(), carregamento.getLINHA()) != -1){
						contaEnvCarregamento++;
					}
				}
				for(int i=0; i < jsonBateriasEnviados.size();i++){
					gson = new GsonBuilder().create();
					Bateria bateria = gson.fromJson(jsonBateriasEnviados.get(i), Bateria.class);
					if(statusBateriaBLL.Insert(getActivity(), bateria.getId()) != -1){
						contaEnvBateria++;
					}
				}
				//======REGISTRA LISTA DE ENVIO PARA ARQPREF, CAR, BAT
				
				for(int i=0; i < lstOsFinalizada.size();i++){
					new AsyncConfirmarEnvOS(getActivity(), ti).execute(lstOsFinalizada.get(i));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void ZerarCounts(){
			cEnvioArqPref = 0;
			contaEnvArqPref = 0;
			cEnvioCarregamento = 0;
			contaEnvCarregamento = 0;
			cEnvioBateria = 0;
			contaEnvBateria = 0;
			cEnvioConfEnvOs = 0;
			contaConfEnvOs = 0;
			
			enviarConfirmacaoOS = true;
		}
	//==================ENVIO OS=====================================================
	

}
