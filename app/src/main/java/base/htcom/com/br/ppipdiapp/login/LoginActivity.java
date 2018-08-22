package base.htcom.com.br.ppipdiapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.model.Colaborador;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.FormUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;
import base.htcom.com.br.ppipdiapp.splash.SplashActivity;

public class LoginActivity extends AppCompatActivity implements AsyncLogin.AsyncResponse {
	private EditText edtEmail;
	private EditText edtPassw;
	ArrayList<EditText> lstValidade = new ArrayList<EditText>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(SharedPreferencesUtills.loadSavedPreferencesString("USER", this) !="" && SharedPreferencesUtills.loadSavedPreferencesString("EMPRESA", this) !=""){
			startActivity(new Intent(this, MainActivity.class));
		}
		else {
			setContentView(R.layout.form_view_login);
			edtEmail = (EditText) findViewById(R.id.edtEmail);
			edtPassw = (EditText) findViewById(R.id.edtPassw);
			lstValidade.add(edtEmail);
			lstValidade.add(edtPassw);
		}
	}
	
	public void BtnEntrar(View view) {
		 if(FormUtills.validarEditTxt(lstValidade)){
			 
			 Colaborador colaborador = new Colaborador();
			 colaborador.setEmail(edtEmail.getText().toString());
			 colaborador.setSenha(edtPassw.getText().toString());
			 new AsyncLogin(this.findViewById(android.R.id.content).getRootView(), LoginActivity.this).execute(colaborador);
			 AsyncLogin.delegate = this;
		 }
	}
	
	@Override
	public void processFinish(String output) {
		// Retorno do AsyncLogin
		try {
			if(!output.equals("false") && !output.equals("x") && output != null){
				ConverterJson(output);
				finish();
			}
			else{
			//Problema de conex�o ou Usuario n�o valido
				if(output.equals("false")){
					new AlertaDialog(this).showDialogAviso("Aten��o!", "Usu�rio inv�lido, por favor verifique!");
				}
				else {
					new AlertaDialog(this).showDialogAviso("Aten��o!", "Tente novamente mais tarde! Se esta situa��o persistir, entre em contato com a HTCOM!");
				}
			}
		}
		catch (Exception e) {
			Log.e("ERROR PROCESSFINISH", e.getMessage());
		}
		
	}
	
	private void ConverterJson(String respWs) {
		try {
			GsonBuilder builder = new GsonBuilder();
			Gson gson    = builder.create();
			Colaborador colaborador = gson.fromJson(respWs, Colaborador.class);																								
			if (colaborador != null ){
				SharedPreferencesUtills.savePreferences("USER", colaborador.getUsuario(), this);
				SharedPreferencesUtills.savePreferences("EMPRESA", colaborador.getEmpresa(), this);
				startActivity(new Intent(this, MainActivity.class));
			}
			else{
				new AlertaDialog(this).showDialogAviso("Aten��o!", "Usu�rio inv�lido, por favor verifique! #cod1");
			}	
		}
		catch (Exception e) {
			Log.e("ERROR CONVERTERJSON", e.getMessage());
			new AlertaDialog(this).showDialogAviso("Aten��o!", "Usu�rio inv�lido, por favor verifique! #cod2");
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(this, SplashActivity.class));
		super.onBackPressed();
	}

}
