package base.htcom.com.br.ppipdiapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.BateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusBateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusCarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Bateria;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.StatusArqPref;
import base.htcom.com.br.ppipdiapp.model.StatusBateria;
import base.htcom.com.br.ppipdiapp.model.StatusCarregamento;
import base.htcom.com.br.ppipdiapp.model.StatusControleUpload;
import base.htcom.com.br.ppipdiapp.model.StatusOs;

public class AdapterOSFinalizada extends ArrayAdapter<Os>{
	private List<Os> items;
	private int layoutResourceId;
	private Context context;
	private StatusOsBLL statusOsBLL = new StatusOsBLL();
	private ArqPrefBLL arqPrefBLL = new ArqPrefBLL();
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	private BateriaBLL bateriaBLL = new BateriaBLL();
	private StatusArqPrefBLL statusArqPrefBLL = new StatusArqPrefBLL();
	private StatusCarregamentoBLL statusCarregamentoBLL = new StatusCarregamentoBLL();
	private StatusBateriaBLL statusBateriaBLL = new StatusBateriaBLL();
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	private StatusControleUploadBLL statusControleUploadBLL = new StatusControleUploadBLL();
	
	public AdapterOSFinalizada(Context context, int layoutResourceId,List<Os> items) {
		super(context, R.layout.lv_item_os_finalizada, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}
	static class ViewHolder{
    	TextView codigo;
    	TextView projeto;
    	TextView pre;
    	TextView codEntidade;
    	TextView pontaDist;
    	TextView dtFinalizada;
    	ImageView imagem;
    	TextView qtdImage;
    	ImageView info;
    	TextView qtdInfo;
    	ImageView car;
    	TextView qtdCar;
    	ImageView bat;
    	TextView qtdBat;
    	ImageView finalizado;
    	Os os;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if(row == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
		
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.codigo = (TextView)row.findViewById(R.id.tv_codigo);
			viewHolder.projeto = (TextView)row.findViewById(R.id.tv_projeto);
			viewHolder.pre = (TextView)row.findViewById(R.id.tv_pre);
			viewHolder.codEntidade = (TextView)row.findViewById(R.id.tv_siteA);
			viewHolder.pontaDist = (TextView)row.findViewById(R.id.tv_siteB);
			viewHolder.finalizado = (ImageView)row.findViewById(R.id.img_finalizado);
			viewHolder.info = (ImageView)row.findViewById(R.id.img_info);
			viewHolder.qtdInfo = (TextView)row.findViewById(R.id.tv_qtdInfo);
			viewHolder.car = (ImageView)row.findViewById(R.id.img_car);
			viewHolder.bat = (ImageView)row.findViewById(R.id.img_bat);
			viewHolder.imagem = (ImageView)row.findViewById(R.id.img_imagens);
			viewHolder.qtdCar = (TextView)row.findViewById(R.id.tv_qtdCar);
			viewHolder.qtdBat = (TextView)row.findViewById(R.id.tv_qtdBat);
			viewHolder.qtdImage = (TextView)row.findViewById(R.id.tv_qtdImagens);
			viewHolder.dtFinalizada = (TextView)row.findViewById(R.id.tv_dtFinalizacao);
			
			row.setTag(viewHolder);
		}
		
		ViewHolder holder = (ViewHolder) row.getTag();
		holder.os = items.get(position);
		holder.codigo.setText(holder.os.getCODIGO().replace("0",""));
		String projeto = (String) holder.os.getCODIGO().subSequence(8, 9);
		if(projeto.equals("1")){
	    	projeto = "MW-PPI";
	    }
	    else if (projeto.equals("2")) {
	    	projeto = "MW-PDI";
		}
	    else if (projeto.equals("3")) {
	    	projeto = "RF-PPI";
		}
	    else if (projeto.equals("4")) {
	    	projeto = "RF-PDI";
		}
		holder.projeto.setText(projeto);
		holder.pre.setText(holder.os.getPRE());
		holder.codEntidade.setText(holder.os.getCOD_ENTIDADE().replace("0",""));
		holder.pontaDist.setText(holder.os.getPONTA_DISTANTE().replace("0",""));
		holder.finalizado.setTag(holder.os.getLINHA());
		holder.info.setImageResource(R.mipmap.ic_cancelar);
		holder.car.setImageResource(R.mipmap.ic_cancelar);
		holder.bat.setImageResource(R.mipmap.ic_cancelar);
		holder.imagem.setImageResource(R.mipmap.ic_cancelar);
		
		//VERIFICA SE TODAS AS INFORMACOES E IMAGENS J� FORAM ENVIADAS
		try {
			StatusOs statusOs = statusOsBLL.listarByLinha(context, holder.os.getLINHA());
			holder.dtFinalizada.setText(statusOs.getDATA_FINALIZACAO());
			//==========VERIFICA ARQUIVOS PREFERENCIAIS REALIZADOS E COMPARA COM OS ENVIADOS
			List<ArqPref> lstArqPrefRealizados = arqPrefBLL.listarRealizadosByOvChamado(context, holder.os.getOV_CHAMADO_NUM());
			int contaArqPrefEnviado = 0;
			if(lstArqPrefRealizados != null){
				int totalArqPrefRealizado = lstArqPrefRealizados.size();
				for(int i=0; i < lstArqPrefRealizados.size();i++){
					StatusArqPref statusArqPref = statusArqPrefBLL.listarByLinha(context, lstArqPrefRealizados.get(i).getLINHA());
					if(statusArqPref != null){
						contaArqPrefEnviado++;
					}
				}
				if(contaArqPrefEnviado == totalArqPrefRealizado){
					holder.info.setImageResource(R.mipmap.ic_confirmar);
				}
				holder.qtdInfo.setText(totalArqPrefRealizado+" de "+contaArqPrefEnviado);
			}
			//==========VERIFICA ARQUIVOS PREFERENCIAIS REALIZADOS E COMPARA COM OS ENVIADOS
			
			//==========VERIFICA CARREGAMENTOS REALIZADOS E COMPARA COM OS ENVIADOS
			List<Carregamento> lstCarRealizados = carregamentoBLL.listarByCodigoAll(context, holder.os.getOV_CHAMADO_NUM());
			int contaCarEnviado = 0;
			if(lstCarRealizados != null){
				int totalCarRealizado = lstCarRealizados.size();
				for(int i=0; i < lstCarRealizados.size();i++){
					StatusCarregamento statusCarregamento = statusCarregamentoBLL.listarByLinha(context, lstCarRealizados.get(i).getLINHA());
					if(statusCarregamento != null){
						contaCarEnviado++;
					}
				}
				if(contaCarEnviado == totalCarRealizado){
					holder.car.setImageResource(R.mipmap.ic_confirmar);
				}
				holder.qtdCar.setText(totalCarRealizado+" de "+contaCarEnviado);
			}
			//==========VERIFICA CARREGAMENTOS REALIZADOS E COMPARA COM OS ENVIADOS
			
			//==========VERIFICA BATERIAS REALIZADAS E COMPARA COM AS ENVIADAS
			List<Bateria> lstBatRealizados = bateriaBLL.listarByOvChamado(context, holder.os.getOV_CHAMADO_NUM());
			int contaBatEnviado = 0;
			if(lstBatRealizados != null){
				int totalBatRealizado = lstBatRealizados.size();
				for(int i=0; i < lstBatRealizados.size();i++){
					StatusBateria statusBateria = statusBateriaBLL.listarByLinha(context, lstBatRealizados.get(i).getId());
					if(statusBateria != null){
						contaBatEnviado++;
					}
				}
				if(contaBatEnviado == totalBatRealizado){
					holder.bat.setImageResource(R.mipmap.ic_confirmar);
				}
				holder.qtdBat.setText(totalBatRealizado+" de "+contaBatEnviado);
			}
			//==========VERIFICA BATERIAS REALIZADAS E COMPARA COM AS ENVIADAS
			
			//==========VERIFICA UPLOADS REALIZADOS E COMPARA COM OS ENVIADOS
			List<ControleUpload> lstControleUploadRealizados = controleUploadBLL.listarRealizadosByOvChamado(context, holder.os.getOV_CHAMADO_NUM());
			int contaUpEnviado = 0;
			if(lstControleUploadRealizados != null){
				int totalUpRealizado = lstControleUploadRealizados.size();
				for(int i=0; i < lstControleUploadRealizados.size();i++){
					StatusControleUpload statusControleUpload = statusControleUploadBLL.listarByLinha(context, lstControleUploadRealizados.get(i).getLinha());
					if(statusControleUpload != null){
						contaUpEnviado++;
					}
				}
				if(contaUpEnviado == totalUpRealizado){
					holder.imagem.setImageResource(R.mipmap.ic_confirmar);
				}
				holder.qtdImage.setText(totalUpRealizado+" de "+contaUpEnviado);
			}
			//==========VERIFICA UPLOADS REALIZADOS E COMPARA COM OS ENVIADOS
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), " - ERROR ADAPTER OSFINALIZADA",context);
			e.printStackTrace();
		}
		return row;
	}

}
