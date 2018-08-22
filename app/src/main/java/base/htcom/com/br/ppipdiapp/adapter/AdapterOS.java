package base.htcom.com.br.ppipdiapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import base.htcom.com.br.ppipdiapp.R;

public class AdapterOS extends CursorAdapter{
	
	
	@SuppressWarnings("deprecation")
	public AdapterOS(Context context, Cursor cursor){
		super(context, cursor);
	}
	static class ViewHolder{
    	TextView codigo;
    	TextView projeto;
    	TextView pre;
    	TextView codEntidade;
    	TextView pontaDist;
    	TextView status;
    	ImageButton selecionar;
    }

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = new ViewHolder();
	    holder.codigo = (TextView)view.findViewById(R.id.tv_codigo);
	    holder.codigo.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))).replace("0",""));
	    String projeto = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
	    projeto = (String) projeto.subSequence(8, 9);
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
	    holder.projeto = (TextView)view.findViewById(R.id.tv_projeto);               
	    holder.projeto.setText(projeto);
	    holder.pre = (TextView)view.findViewById(R.id.tv_pre);               
	    holder.pre.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(7))));
	    holder.codEntidade = (TextView)view.findViewById(R.id.tv_siteA);               
	    holder.codEntidade.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(20))).replace("0",""));
	    holder.pontaDist = (TextView)view.findViewById(R.id.tv_siteB);               
	    holder.pontaDist.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(21))).replace("0",""));
	    holder.status = (TextView)view.findViewById(R.id.tv_status);
	    holder.status.setText("Nova");
	    if(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(16))).equals("CAMPO REVISAO")){
	    	holder.status.setText("Revis�o");
	    }
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
	    View v = inflater.inflate(R.layout.lv_item_os, parent, false);
        return v;
	}

}
