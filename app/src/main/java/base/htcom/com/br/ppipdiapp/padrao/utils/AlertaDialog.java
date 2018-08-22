package base.htcom.com.br.ppipdiapp.padrao.utils;

import android.app.AlertDialog;
import android.content.Context;

import base.htcom.com.br.ppipdiapp.R;

public class AlertaDialog {
private Context ctx;
	
	public AlertaDialog(Context context) {
		this.ctx = context;
	}
	
	public void showDialogAviso (String titulo, String mensagem){
		AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(ctx);
		dialogBuilder.setMessage(mensagem);
		dialogBuilder.setNeutralButton(ctx.getResources().getString(R.string.geral_Ok), null);
		dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
		AlertDialog dialog = dialogBuilder.create();
		dialog.setTitle(titulo);
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		dialog.show();	
	}

}
