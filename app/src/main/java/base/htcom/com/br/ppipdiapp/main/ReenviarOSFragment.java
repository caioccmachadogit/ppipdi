package base.htcom.com.br.ppipdiapp.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.async.AsyncReenviarArqPref;
import base.htcom.com.br.ppipdiapp.async.AsyncReenviarBateria;
import base.htcom.com.br.ppipdiapp.async.AsyncReenviarCarregamento;
import base.htcom.com.br.ppipdiapp.async.AsyncReenviarUploadArqPref;
import base.htcom.com.br.ppipdiapp.async.TarefaInterfaceReenv;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.BateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusBateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusCarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusControleUploadBLL;
import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Bateria;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.StatusControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.funcoes.ControleConexao;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;

public class ReenviarOSFragment extends Fragment{
	
	public static ReenviarOSFragment newInstance(Bundle arguments){
		ReenviarOSFragment f = new ReenviarOSFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }
	
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	private StatusCarregamentoBLL statusCarregamentoBLL = new StatusCarregamentoBLL();
	private List<Carregamento> lsCarregamentos = new ArrayList<Carregamento>();
	private BateriaBLL bateriaBLL = new BateriaBLL();
	private StatusBateriaBLL statusBateriaBLL = new StatusBateriaBLL();
	private List<Bateria> lsBaterias = new ArrayList<Bateria>();
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	private StatusControleUploadBLL statusControleUploadBLL = new StatusControleUploadBLL();
	private List<ControleUpload> lstControleUploads = new ArrayList<ControleUpload>();
	private ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
	private List<ArqPref> lsArqPrefs = new ArrayList<ArqPref>();
	private StatusArqPrefBLL statusArqPrefBLL = new StatusArqPrefBLL();
	private OsBLL osBLL = new OsBLL();
	private String IDLINHA;
	private Os os;
	private TarefaInterfaceReenv ti;
	private int countConfirmacao = 0;
	
	//=======ENVIO============================
		public static int cEnvioArqPref=0;
		public static int contaEnvArqPref=0;
		public static int cEnvioCarregamento=0;
		public static int contaEnvCarregamento=0;
		public static int cEnvioBateria=0;
		public static int contaEnvBateria=0;
		public static int cEnvioUpArqPref=0;
		public static int contaEnvUpArqPref=0;
		public static int contaUpArqPref=0;
		public static int contaConfEnvOs=0;
		public static int cEnvioConfEnvOs=0;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		IDLINHA = getArguments().getString(ListOSFinalizadaFragment._TAGLINHA);
		ReenviarOS(IDLINHA);
		return view;
	}

	private void ReenviarOS(String linhaOS) {
		try {
//=======INTERFACE DE RESPOSTA DOS ASYNC======================			
			ti = new TarefaInterfaceReenv() {
				
				@Override
				public void respostaAsyncEnvioArqPref(String json) {
					try {
						if(json.equals("true")){
							//REGISTRA CONFIRMACAO ENVIO
							if(statusArqPrefBLL.Insert(getActivity(), lsArqPrefs.get(cEnvioArqPref-1).getLINHA()) != -1){
								Toast.makeText(getActivity(), "Arq pref enviado: "+(cEnvioArqPref)+"", Toast.LENGTH_SHORT).show();
							}
						}
						else Toast.makeText(getActivity(), "Arq pref � enviado: "+(cEnvioArqPref)+"", Toast.LENGTH_SHORT).show();
					}
					catch (Exception e) {
						LogErrorBLL.LogError(e.getMessage(), "ERROR respostaAsyncEnvioArqPref Reenv", getActivity());
					}
					
				}
				
				@Override
				public void respostaAsyncEnvioCarregamento(String json) {
					try {
						if(json.equals("true")){
							//REGISTRA CONFIRMACAO ENVIO
							if(statusCarregamentoBLL.Insert(getActivity(), lsCarregamentos.get(cEnvioCarregamento-1).getLINHA()) != -1){
								Toast.makeText(getActivity(), "Carregamento enviado: "+(cEnvioCarregamento)+"", Toast.LENGTH_SHORT).show();
							}
						}
						else Toast.makeText(getActivity(), "Carregamento � enviado: "+(cEnvioCarregamento)+"", Toast.LENGTH_SHORT).show();
					}
					catch (Exception e) {
						LogErrorBLL.LogError(e.getMessage(), "ERROR respostaAsyncEnvioCarregamento Reenv", getActivity());
					}
					
				}
				
				@Override
				public void respostaAsyncEnvioBateria(String json) {
					try {
						if(json.equals("true")){
							//REGISTRA CONFIRMACAO ENVIO
							if(statusBateriaBLL.Insert(getActivity(), lsBaterias.get(cEnvioBateria-1).getId()) != -1){
								Toast.makeText(getActivity(), "Bateria enviada: "+(cEnvioBateria)+"", Toast.LENGTH_SHORT).show();
							}
						}
						else Toast.makeText(getActivity(), "Bateria � enviada: "+(cEnvioBateria)+"", Toast.LENGTH_SHORT).show();
					}
					catch (Exception e) {
						LogErrorBLL.LogError(e.getMessage(), "ERROR respostaAsyncEnvioBateria Reenv", getActivity());
					}
					
				}
				
				@Override
				public void respostaAsyncEnvioUpload(String json) {
					try {
						countConfirmacao++;
						if(json.equals("true")){
							//REGISTRA CONFIRMACAO ENVIO
							StatusControleUpload statusControleUpload = new StatusControleUpload();
							statusControleUpload.setLINHA(lstControleUploads.get(cEnvioUpArqPref-1).getLinha());
							SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
							statusControleUpload.setDATA_ENVIO(format.format(new Date()));
							if(statusControleUploadBLL.Insert(getActivity(), statusControleUpload) != -1){
								Toast.makeText(getActivity(), "Imagem enviada: "+(cEnvioUpArqPref)+"", Toast.LENGTH_SHORT).show();
							}
						}
						else Toast.makeText(getActivity(), "Imagem � enviada: "+(cEnvioUpArqPref)+"", Toast.LENGTH_SHORT).show();
						
						if(countConfirmacao == lstControleUploads.size())
							new AlertaDialog(getActivity()).showDialogAviso("Confirma��o", "ETP Reenviada!");
					}
					catch (Exception e) {
						LogErrorBLL.LogError(e.getMessage(), "ERROR respostaAsyncEnvioUpload Reenv", getActivity());
					}
					
				}
			};
//=======INTERFACE DE RESPOSTA DOS ASYNC======================			
			ClearCounts();
//=======OBTER LIST DE NAO ENVIADOS=====================
			os = osBLL.listarById(getActivity(), linhaOS).get(0);
			lsCarregamentos = carregamentoBLL.listarNaoEnviados(getActivity(), os.getOV_CHAMADO_NUM());
			lsBaterias = bateriaBLL.listarNaoEnviados(getActivity(), os.getOV_CHAMADO_NUM());
			lsArqPrefs = arqPrefBLL.listarByOvChamado(getActivity(), os.getOV_CHAMADO_NUM());
			lstControleUploads = controleUploadBLL.listarByOvChamado(getActivity(), os.getOV_CHAMADO_NUM());
//=======ENVIAR LIST DE NAO ENVIADOS=====================
			if(lsArqPrefs == null && lsCarregamentos == null && lsBaterias == null && lstControleUploads == null){
				Toast.makeText(getActivity(), "Nenhum registro a ser Reenviado!", Toast.LENGTH_SHORT).show();
			}
			else {
				if(!ControleConexao.checkNetworkInterface(getActivity()).equals("none")){
					//ARQUIVOS PREFERENCIAIS
					if(lsArqPrefs != null)
					if(lsArqPrefs.size() > 0){
						Gson gson = new GsonBuilder().create();
						JsonArray jsonArrayArq = gson.toJsonTree(lsArqPrefs).getAsJsonArray();
						for(int y=0;y< jsonArrayArq.size();y++){
							JsonElement jsonArqPref = jsonArrayArq.get(y);
							new AsyncReenviarArqPref(getActivity(), ti).execute(jsonArqPref.toString());
						}
					}
					//CARREGAMENTOS E EV
					if(lsCarregamentos != null)
					if(lsCarregamentos.size() > 0){
						Gson gson = new GsonBuilder().create();
						JsonArray jsonArrayCar = gson.toJsonTree(lsCarregamentos).getAsJsonArray();
						for(int c=0;c< jsonArrayCar.size();c++){
							JsonElement jsonCar = jsonArrayCar.get(c);
							new AsyncReenviarCarregamento(getActivity(), ti).execute(jsonCar.toString());
						}
					}
					//BATERIAS
					if(lsBaterias != null)
					if(lsBaterias.size() > 0){
						Gson gson = new GsonBuilder().create();
						JsonArray jsonArrayBat = gson.toJsonTree(lsBaterias).getAsJsonArray();
						for(int c=0;c< jsonArrayBat.size();c++){
							JsonElement jsonBat = jsonArrayBat.get(c);
							new AsyncReenviarBateria(getActivity(), ti).execute(jsonBat.toString());
						}
					}
					//CONTROLE UPLOADS
					if(lstControleUploads != null)
					if(lstControleUploads.size() > 0){
						Gson gson = new GsonBuilder().create();
						JsonArray jsonArrayUpArq = gson.toJsonTree(lstControleUploads).getAsJsonArray();
						for(int j=0;j< jsonArrayUpArq.size();j++){
							JsonElement jsonUpArqPref = jsonArrayUpArq.get(j);
							new AsyncReenviarUploadArqPref(getActivity(), ti).execute(jsonUpArqPref.toString());
						}
					}
				}
				else {
					Toast.makeText(getActivity(), "Verifique sua Conex�o com a Internet e tente novamente!", Toast.LENGTH_SHORT).show();
				}
			}
//=======ENVIAR LIST DE NAO ENVIADOS=====================
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR Reenvio", getActivity());
		}
		
	}
	
	private void ClearCounts(){
		cEnvioArqPref = 0;
		cEnvioCarregamento = 0;
		cEnvioBateria = 0;
		contaUpArqPref = 0;
		cEnvioUpArqPref = 0;
	}

}
