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
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Bateria;

public class AdapterBateria extends ArrayAdapter<Bateria>{
	private List<Bateria> items;
	private int layoutResourceId;
	private Context context;

	
	public AdapterBateria(Context context, int layoutResourceId,List<Bateria> items) {
		super(context, R.layout.lv_item_bateria, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}
	
	public static class ViewHolder{
    	TextView marca;
    	TextView modelo;
    	ImageView editar;
    	Bateria bateria;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		try {
			if(row == null){
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				row = inflater.inflate(layoutResourceId, parent, false);
				
				ViewHolder holder = new ViewHolder();
				holder = new ViewHolder();
				holder.marca = (TextView)row.findViewById(R.id.tv_marca);
				holder.modelo = (TextView)row.findViewById(R.id.tv_modelo);
				holder.editar = (ImageView)row.findViewById(R.id.imgEditBat);
				
				row.setTag(holder);
			}

			ViewHolder holder = (ViewHolder) row.getTag();
			holder.bateria = items.get(position);
			
			holder.marca.setText(holder.bateria.getMARCA());
			holder.modelo.setText(holder.bateria.getMODELO());
			holder.editar.setTag(holder.bateria.getId());
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), " - ERROR ADAPTER Bateria",context);
			e.printStackTrace();
		}
		return row;
	}
	
}
