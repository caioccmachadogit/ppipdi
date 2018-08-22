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

public class AdapterSelectedArqPref extends ArrayAdapter<Combo>{
	
	private int layoutResourceId;
	private Context context;
	private List<Combo> items;
	
	public AdapterSelectedArqPref(Context context, int layoutResourceId,List<Combo> items) {
		super(context, R.layout.gv_item_arq_pref, items);
		this.context = context;
		this.items = items;
		this.layoutResourceId = layoutResourceId;
	}
	
	public static class ViewHolder{
    	TextView tvTitulo;
    	ImageView imgCheck;
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
		
		holder.tvTitulo = (TextView)row.findViewById(R.id.tvTitulo);
		holder.tvTitulo.setText(holder.combo.getTIPO_REL());
		
		holder.imgCheck = (ImageView)row.findViewById(R.id.imgCheck);
		holder.imgCheck.setTag(holder.combo.getTIPO_REL());
		
		row.setTag(holder);

		return row;
	}
	

}
