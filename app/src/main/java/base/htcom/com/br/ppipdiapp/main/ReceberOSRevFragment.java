package base.htcom.com.br.ppipdiapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import base.htcom.com.br.ppipdiapp.async.AsyncConfirmarRecRevOS;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberRevArqPref;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberRevCarregamento;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberRevOS;
import base.htcom.com.br.ppipdiapp.async.TarefaInterfaceRev;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class ReceberOSRevFragment extends BaseFragment{
	
	private Gson gson = new Gson();
	
	private List<Os> listOsRecRev;
	private List<Carregamento> listCarRecRev;
	private List<ArqPref> listArqRecRev;
	
	private TarefaInterfaceRev ti;
	
	private ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
	private OsBLL osBLL = new OsBLL();
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	private StatusOsBLL statusOsBLL = new StatusOsBLL();
	
	
	//VAR DE CONTROLE PARA RECEBIMENTO=======================
	public static int COUNTOS=0; //var de controle precisa inicializar
	public static int COUNTCARinsert=0; //var de controle precisa inicializar
	public static int COUNTCAR=0; //var de controle precisa inicializar
	public static int COUNTARQinsert=0; //var de controle precisa inicializar
	public static int COUNTARQ=0; //var de controle precisa inicializar
	public static int COUNTCONFIR=0; //var de controle precisa inicializar
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		ReceberOSRev();
		return view;
	}

	private void ReceberOSRev() {
		try {
			ClearCounts();
			Os pOs = new Os();
			pOs.setSOLIC_OV_SERV_NOME(SharedPreferencesUtills
					.loadSavedPreferencesString("USER", getActivity()));
			pOs.setSOLIC_VISTORIA_EMPRESA_COD(SharedPreferencesUtills
					.loadSavedPreferencesString("EMPRESA", getActivity()));
			pOs.setOS_SITUACAO("CAMPO REVISAO");
			ti = new TarefaInterfaceRev() {
				
				@Override
				public void respostaAsync(String json) {
					try {
						if(!json.equals("false") && !json.equals("x") && json != null){
							Type listTypeOs = new TypeToken<List<Os>>(){}.getType();
							listOsRecRev = gson.fromJson(json, listTypeOs);
							//==========PARA CADA OS RECEBIDA VERIFICAR SE ELA J� FOI REGSITRA NO DISPOSITIVO=================
							VerificarLimpezaOS(listOsRecRev);
							//==========SE J� REGISTRA NO DISPOSITIVO � NECESS�RIO LIMPAR TODOS OS REGISTROS=================
							
							//=========ARMAZENA NOVA OS DE REV===============================================================
							if(osBLL.Insert(getActivity(), listOsRecRev) != -1){
								//==========PARA CADA OS RECEBIDA VERIFICAR INFORMA��ES-REV QUE COMP�E A OS=================
								for(int i=0;i< listOsRecRev.size();i++){
									//receber todos os registros em paralelo e j� armazenar na resp da async
									//inserir um count em cada async e contar at� o tamanho do listOsRev
									new AsyncReceberRevCarregamento(getActivity(), ti).execute(listOsRecRev.get(i).getOV_CHAMADO_NUM());
									new AsyncReceberRevArqPref(getActivity(), ti).execute(listOsRecRev.get(i).getOV_CHAMADO_NUM());
									
									new AsyncConfirmarRecRevOS(getActivity(), ti).execute(listOsRecRev.get(i));
								}	
							}
							else {
								new AlertaDialog(getActivity()).showDialogAviso("Problemas de Persistencia","ETP não registrada!");
								LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO RECEBIMENTO REV OS",getActivity());
							}
							//=========ARMAZENA NOVA OS DE REV===============================================================
						}
						else {
							if(json.equals("false")){
								new AlertaDialog(getActivity()).showDialogAviso("Recebimento ETP","Nenhuma ETP Recebida!");
							}
							else
								new AlertaDialog(getActivity()).showDialogAviso("Servidor Inacessível","Tente mais tarde!");
						}
					}
					catch (Exception e) {
						ClearCounts();
					}
				}
				
				@Override
				public void respostaAsyncRecRevCarregamento(String json) throws Exception {
					try {
						COUNTCAR++;
						if(!json.equals("false") && !json.equals("x") && json != null){
							Type listType = new TypeToken<List<Carregamento>>(){}.getType();
							listCarRecRev = gson.fromJson(json, listType);
							if(listCarRecRev != null){
								//ARMAZENA LIST NO DB
								if(carregamentoBLL.Insert(getActivity(), listCarRecRev) != -1){
									COUNTCARinsert++;
								}
								else {
									LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO RECEBIMENTO REV CAR",getActivity());
								}
							}
						}
					}
					catch (Exception e) {
						ClearCounts();
						throw e;
					}
				}
				
				@Override
				public void respostaAsyncRecRevArqPref(String json) throws Exception {
					try {
						COUNTARQ++;
						if(!json.equals("false") && !json.equals("x") && json != null){
							Type listType = new TypeToken<List<ArqPref>>(){}.getType();
							listArqRecRev = gson.fromJson(json, listType);
							if(listArqRecRev != null){
								//ARMAZENA LIST NO DB
								if(arqPrefBLL.Insert(getActivity(), listArqRecRev) != -1){
									COUNTARQinsert++;
								}
								else {
									LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO RECEBIMENTO REV ARQ PREF",getActivity());
								}
							}
						}
					}
					catch (Exception e) {
						ClearCounts();
						throw e;
					}
				}

				@Override
				public void respostaAsyncVerificarRev(String json) throws Exception {
					try {
						if(json.equals("OK")){
							Toast.makeText(getActivity(), "Confirmando Recebimento...", Toast.LENGTH_SHORT).show();
							//INICIA A CONFIRMACAO DE RECEBIMENTO
							//====CONFIRMA REC DE OS ================================
							for(int i=0; i < listOsRecRev.size();i++){
								new AsyncConfirmarRecRevOS(getActivity(), ti).execute(listOsRecRev.get(i));
							}
							//====CONFIRMA REC DE OS ================================
						}
						else {
							ClearCounts();
						}
					} catch (Exception e) {
						ClearCounts();
						throw e;
					}
				}
				
				@Override
				public void respostaAsyncConfirmaRecOs(String json) throws Exception {
					try {
						if(!json.equals("false") && !json.equals("x") && json != null){
							COUNTCONFIR++;
							Toast.makeText(getActivity(), COUNTCONFIR+" ETP Confirmada!", Toast.LENGTH_SHORT).show();
							if(listOsRecRev.size() == COUNTCONFIR){
								new AlertaDialog(getActivity()).showDialogAviso("ETP Rev. Recebida", "ETP Revisão: "+COUNTCONFIR+"");
								//ClearCounts();
							}
						}
					} catch (Exception e) {
						ClearCounts();
						throw e;
					}
				}
			};
			
			new AsyncReceberRevOS(getActivity(), ti).execute(pOs);
		}
		catch (Exception e) {
			ClearCounts();
		}
	}
	
	private void ClearCounts(){
		COUNTCAR=0;
		COUNTCARinsert=0;
		COUNTARQ=0;
		COUNTARQinsert=0;
		COUNTCONFIR=0;
	}
	
	private void VerificarLimpezaOS(List<Os> lstOS) throws Exception {
		try {//NAO REMOVE A IMAGEM
			for(int i=0; i < lstOS.size();i++){
				if(osBLL.delete(getActivity(), lstOS.get(i).getOV_CHAMADO_NUM()) > 0){
					arqPrefBLL.delete(getActivity(), lstOS.get(i).getOV_CHAMADO_NUM());
					statusOsBLL.delete(getActivity(), lstOS.get(i).getOV_CHAMADO_NUM());
					carregamentoBLL.delete(getActivity(), lstOS.get(i).getOV_CHAMADO_NUM());
					controleUploadBLL.delete(getActivity(), lstOS.get(i).getOV_CHAMADO_NUM());
				}
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

}
