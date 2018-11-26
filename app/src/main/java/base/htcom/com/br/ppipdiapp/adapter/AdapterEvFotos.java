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
		ImageView checkFoto;
    	Combo combo;
    }
	
	@Override
	public View getView(int position, View row, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder.combo = items.get(position);
		
		holder.titulo = row.findViewById(R.id.tv_tit);
		holder.titulo.setText(holder.combo.getTITULO());
		
		holder.action = row.findViewById(R.id.img_acao);
		holder.action.setTag(holder.combo.getLINHA());

		holder.checkFoto = row.findViewById(R.id.img_check);
		if(holder.combo.isFoto())
			holder.checkFoto.setVisibility(View.VISIBLE);
		else
			holder.checkFoto.setVisibility(View.INVISIBLE);

		row.setTag(holder);

		return row;
	}	
	
}
