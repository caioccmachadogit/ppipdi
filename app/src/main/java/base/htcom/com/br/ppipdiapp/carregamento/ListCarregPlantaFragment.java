package base.htcom.com.br.ppipdiapp.carregamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterCarregamentoPlanta;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoPlantaBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.CarregamentoPlanta;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;

public class ListCarregPlantaFragment extends Fragment {
	private FragmentManager frgManager;
	Bundle arguments;
	private ListView lv;
	public static String _ID = "_ID";
	private CarregamentoPlantaBLL carregamentoPlantaBLL = new CarregamentoPlantaBLL();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_car_planta_view_fragment, container, false);
		
		lv = (ListView) view.findViewById(R.id.lv_carregamento_planta);
		AtualizarListViewCarregamento();
		//==============CLICK ITEM DO LISTVIEW=================
		lv.setOnItemClickListener(new ListView.OnItemClickListener(){
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageView edit = (ImageView) view.findViewById(R.id.imgBtnEditCar);				
				arguments = new Bundle();
		        arguments.putString(_ID, (String)edit.getTag());
		        NovoCarregFragment.tipoCarregamento = "PLANTA";
				NovoCarregFragment fragment = NovoCarregFragment.newInstance(arguments); 
				frgManager = getFragmentManager();
				// TODO: 22/08/2018 rever
//				frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
	        }
	    });
		return view;
	}
	
	private void AtualizarListViewCarregamento() {
		try {
			List<CarregamentoPlanta> lst = carregamentoPlantaBLL.listarByCodEntidade(getActivity(), OsMenuActitivity._OS.getCOD_ENTIDADE());
			if(lst != null){
				AdapterCarregamentoPlanta adapterCarregamentoPlanta = new AdapterCarregamentoPlanta(getActivity(), R.layout.lv_item_carregamento, lst);
				lv.setAdapter(adapterCarregamentoPlanta);
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List CarrPlanta", getActivity());
		}
		
	}
	

}
