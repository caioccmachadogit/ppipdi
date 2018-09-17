package base.htcom.com.br.ppipdiapp.bateria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterBateria;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.bll.BateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Bateria;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;

public class ListBateriaFragment extends BaseFragment {
	private Bundle arguments;
	private NovaBateriaFragment fragment;
	private ListView lv;
	public static String _ID = "_ID";
	private BateriaBLL bateriaBLL = new BateriaBLL();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.list_bat_view_fragment, container, false);
		
		lv = (ListView) view.findViewById(R.id.lv_bateria);
		AtualizarListView();
		//==============CLICK ITEM DO LISTVIEW=================
		lv.setOnItemClickListener(new ListView.OnItemClickListener(){
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageView edit = (ImageView) view.findViewById(R.id.imgEditBat);				
				arguments = new Bundle();
		        arguments.putString(_ID, (String)edit.getTag());
				fragment = NovaBateriaFragment.newInstance(arguments);
                fragmentTransaction(NovaBateriaFragment.class.getSimpleName(), fragment, true, 1);
	        }
	    });
		
		Button btnNovo = (Button) view.findViewById(R.id.btn_novo_bat);
		btnNovo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					arguments = new Bundle();
			        arguments.putString(_ID, "0");
			        fragment = NovaBateriaFragment.newInstance(arguments);
					fragmentTransaction(NovaBateriaFragment.class.getSimpleName(), fragment, true, 1);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		setupNavDrawer();
		return view;
	}
	
	private void AtualizarListView(){
		try {
			List<Bateria> lst = bateriaBLL.listarByOvChamado(getActivity(), OsMenuActitivity._OV_CHAMADO);
			if(lst != null){
				AdapterBateria adapter = new AdapterBateria(getActivity(), R.layout.lv_item_bateria, lst);
				lv.setAdapter(adapter);
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List CArr", getActivity());
		}
		
	}

}
