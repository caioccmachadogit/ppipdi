package base.htcom.com.br.ppipdiapp.padrao.utils;

import android.annotation.SuppressLint;
import android.widget.EditText;

import java.util.ArrayList;

public class FormUtills {
	@SuppressLint("NewApi") public static boolean validarEditTxt(ArrayList<EditText> lstValidade){
		boolean validadeOk = true;
		for(int i=0; i < lstValidade.size();i++){
			if(lstValidade.get(i).getText().toString().isEmpty()){
				lstValidade.get(i).setError("Informe o valor para o campo!");
				validadeOk = false;
			}
			else{
				lstValidade.get(i).setError(null);
			}
		}
		return validadeOk;
	}
}
