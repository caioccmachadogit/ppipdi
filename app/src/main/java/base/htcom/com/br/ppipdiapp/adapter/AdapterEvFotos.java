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
import base.htcom.com.br.ppipdiapp.model.Combo;

public class AdapterEvFotos extends ArrayAdapter<Combo>{
	
	private List<Combo> items;
	private int layoutResourceId;
	private Context context;

	
	public AdapterEvFotos(Context context, int layoutResourceId,List<Combo> items) {
		super(context, R.layout.lv_item_ev_fotos, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}
	
	public static class ViewHolder{
    	TextView titulo;
    	ImageView action;
    	Combo combo;
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ViewHolder();
		holder.combo = items.get(position);
		
		holder.titulo = (TextView)row.findViewById(R.id.tv_tit);
		holder.titulo.setText(holder.combo.getTITULO());
		
		holder.action = (ImageView)row.findViewById(R.id.img_acao);
		holder.action.setTag(holder.combo.getLINHA());
		
		row.setTag(holder);

		return row;
	}	
	
}
