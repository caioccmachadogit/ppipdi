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
import base.htcom.com.br.ppipdiapp.model.Carregamento;

public class AdapterCarregamento extends ArrayAdapter<Carregamento>{
	private List<Carregamento> items;
	private int layoutResourceId;
	private Context context;

	
	public AdapterCarregamento(Context context, int layoutResourceId,List<Carregamento> items) {
		super(context, R.layout.lv_item_carregamento, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}
	
	public static class ViewHolder{
    	TextView etiqueta;
    	ImageView editar;
    	Carregamento carregamento;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ViewHolder();
		holder.carregamento = items.get(position);
		
		holder.etiqueta = (TextView)row.findViewById(R.id.tv_etiqueta);
		holder.etiqueta.setText(holder.carregamento.getINFO_20());
		
		holder.editar = (ImageView)row.findViewById(R.id.imgBtnEditCar);
		holder.editar.setTag(holder.carregamento.getLINHA());

		row.setTag(holder);

		return row;
	}
	
}
