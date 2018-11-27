package base.htcom.com.br.ppipdiapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.List;
import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;

public class AdapterArqPref extends ArrayAdapter<Combo>{
	
	private List<Combo> items;
	private int layoutResourceId;
	private Context context;
	private final String TAG = getClass().getSimpleName();
	private ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
	private ArqPrefBLL arqPrefBLL = new ArqPrefBLL();

	
	public AdapterArqPref(Context context, int layoutResourceId,List<Combo> items) {
		super(context, R.layout.lv_item_arq_pref, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}
	
	public static class ViewHolder{
    	TextView titulo;
    	ImageButton capturar;
    	ImageButton foto;
    	ImageButton obs;
    	Combo combo;
    	RadioButton rbOk;
    	RadioButton rbNT;
    	RadioButton rbNA;
    }
	
	@Override
	public View getView(int position, View row, ViewGroup parent) {
		ViewHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ViewHolder();
		holder.combo = items.get(position);
		
		holder.titulo = row.findViewById(R.id.tv_tit);
		holder.titulo.setText(holder.combo.getTITULO());
		
		holder.capturar = row.findViewById(R.id.imgBtnCapturar);
		holder.capturar.setTag(holder.combo.getLINHA());
		
		holder.obs = row.findViewById(R.id.imgBtnObs);
		holder.obs.setTag(holder.combo.getLINHA());
		
		holder.foto = row.findViewById(R.id.imgBtnImg);
		holder.foto.setTag(holder.combo.getLINHA());
		
		holder.rbOk = row.findViewById(R.id.rb_ok);
		holder.rbOk.setTag(holder.combo.getLINHA());
		
		holder.rbNT = row.findViewById(R.id.rb_nt);
		holder.rbNT.setTag(holder.combo.getLINHA());
		
		holder.rbNA = row.findViewById(R.id.rb_na);
		holder.rbNA.setTag(holder.combo.getLINHA());
		
		ArqPref arqPref;
		try {
			arqPref = arqPrefBLL.listarByArqCarregado(context, "20"+holder.combo.getORDEM(), OsMenuActitivity._OV_CHAMADO);
			if(arqPref != null){
				Log.d(TAG, "isObs->"+arqPref.isObs());
				if(arqPref.isObs())
					holder.obs.setBackground(context.getResources().getDrawable(R.drawable.button_check_lay_main));

				String resp = arqPref.getOS_VERIF_20XXX();
				resp = resp.substring(0, 2);
				if(resp.equals("OK")){
					holder.rbOk.setChecked(true);
					if(controleUploadBLL.listarByArqCarregado(context, arqPref.getArquivo_Carregado(), arqPref.getOV_CHAMADO_NUM())
							!= null){
						holder.foto.setBackground(context.getResources().getDrawable(R.drawable.button_check_lay_main));
						Log.d(TAG, "isfoto->"+arqPref.isObs());
					}
				}
				else if(resp.equals("NT")) {
					holder.rbNT.setChecked(true);
				}
				else if(resp.equals("NA")) {
					holder.rbNA.setChecked(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		row.setTag(holder);

		return row;
	}	
	
}
