package base.htcom.com.br.ppipdiapp.notas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;

public class NotasFragment extends Fragment{
	private FragmentManager frgManager;
	private OsBLL osBLL = new OsBLL();
	private Button btnGravar;
	private EditText edtNotas;
	private Os os = new Os();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.os_notas_fragment,container, false);
		
		edtNotas = (EditText) view.findViewById(R.id.edt_notas);
		CarregarNotas();
		btnGravar = (Button) view.findViewById(R.id.btnGravarNotas);
		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BtnGravarNotas();
			}
		});
		
		return view;
	}
	
	private void CarregarNotas() {
		try {
			os = osBLL.listarById(getActivity(), OsMenuActitivity._LINHA).get(0);
			if(!os.getOBSERVACAO_OS().isEmpty() && !os.getOBSERVACAO_OS().equals(" ")){
				edtNotas.setText(os.getOBSERVACAO_OS());
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR CARREGAR NOTAS",getActivity());
			Toast.makeText(getActivity(),"Notas n�o carregadas!",Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
	}

	private void BtnGravarNotas() {
		try {
			if(!edtNotas.getText().toString().isEmpty()){
				os.setOBSERVACAO_OS(edtNotas.getText().toString());
				if(osBLL.Update(getActivity(), os) == 1){
					Toast.makeText(getActivity(),"Notas Gravadas!",Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(getActivity(),"Notas n�o gravadas!",Toast.LENGTH_LONG).show();
				}
			}
			else {
				Toast.makeText(getActivity(),"Digite suas Notas!",Toast.LENGTH_LONG).show();
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR GRAVAR NOTAS",getActivity());
			Toast.makeText(getActivity(),"Notas n�o gravadas!",Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
	}

}
