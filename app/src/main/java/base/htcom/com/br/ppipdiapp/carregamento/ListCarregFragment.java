package base.htcom.com.br.ppipdiapp.carregamento;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterCarregamento;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;

public class ListCarregFragment extends BaseFragment {
	Bundle arguments;
	private ListView lv;
	public static String _ID = "_ID";
	private CarregamentoBLL carregamentoBLL = new CarregamentoBLL();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(getClass().getSimpleName(),"onCreateView");
		View view = inflater.inflate(R.layout.list_car_view_fragment, container, false);
		
		lv = (ListView) view.findViewById(R.id.lv_carregamento);
		AtualizarListViewCarregamento();
		//==============CLICK ITEM DO LISTVIEW=================
		lv.setOnItemClickListener(new ListView.OnItemClickListener(){
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageView edit = (ImageView) view.findViewById(R.id.imgBtnEditCar);				
				arguments = new Bundle();
		        arguments.putString(_ID, (String)edit.getTag());
		        NovoCarregFragment.tipoCarregamento = "NOVA";
				NovoCarregFragment fragment = NovoCarregFragment.newInstance(arguments);
				fragmentTransaction(NovoCarregFragment.class.getSimpleName(), fragment, true, 1);
	        }
	    });
		final Button btnNovo = (Button) view.findViewById(R.id.btn_novo_car);
		if(OsMenuActitivity._OS.getOS_SITUACAO().equals("OS ABERTA")){
			btnNovo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						if(carregamentoBLL.listarEv(getActivity(), OsMenuActitivity._OV_CHAMADO, "1A000") != null){
							arguments = new Bundle();
					        arguments.putString(_ID, "0");
					        NovoCarregFragment.tipoCarregamento = "NOVA";
					        NovoCarregFragment fragment = NovoCarregFragment.newInstance(arguments);
							fragmentTransaction(NovoCarregFragment.class.getSimpleName(), fragment, true, 1);
						}
						else {
							Toast.makeText(getActivity(), "Necessário cadastrar Estrutura Vertical!", Toast.LENGTH_LONG).show();
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else if (OsMenuActitivity._OS.getOS_SITUACAO().equals("CAMPO REVISAO")) {
			btnNovo.setEnabled(false);
		}

		setupNavDrawer();
		
		return view;
	}

	private void AtualizarListViewCarregamento() {
		try {
			List<Carregamento> lst = carregamentoBLL.listarByCodigo(getActivity(), OsMenuActitivity._OV_CHAMADO);
			if(lst != null){
				AdapterCarregamento adapterCarregamento = new AdapterCarregamento(getActivity(), R.layout.lv_item_carregamento, lst);
				lv.setAdapter(adapterCarregamento);
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List CArr", getActivity());
		}
		
	}
	

}
