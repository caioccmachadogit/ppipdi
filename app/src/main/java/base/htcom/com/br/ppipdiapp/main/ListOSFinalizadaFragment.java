package base.htcom.com.br.ppipdiapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterOSFinalizada;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class ListOSFinalizadaFragment extends ListFragment{
	
	public static String _TAGLINHA = "TAGLINHA";
	private String _linhaOs = "0";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_os_finalizada_fragment, null);
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			AtualizarListViewOS();
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR ONCREATE FRAGMENT ListOSFinalizada",getActivity());
		}
	}
	
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
        registerForContextMenu(view);
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ImageView imgFinalizado = (ImageView) v.findViewById(R.id.img_finalizado);
		_linhaOs = (String) imgFinalizado.getTag();
		
		l.showContextMenuForChild(v);
		Log.d("Click OS", "LINHA: " + _linhaOs);
	}
	
	private void AtualizarListViewOS(){
		try {
			OsBLL osBLL = new OsBLL();
			List<Os> lstOsFinalizada = osBLL.listarFinalizada(getActivity(),
					SharedPreferencesUtills.loadSavedPreferencesString("USER", getActivity()),
					SharedPreferencesUtills.loadSavedPreferencesString("EMPRESA", getActivity())
					);
			AdapterOSFinalizada adapter = new AdapterOSFinalizada(getActivity(), R.layout.lv_item_os_finalizada, lstOsFinalizada);
			setListAdapter(adapter);
			
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar List OS Finalizada",getActivity());
		}
	}
	
	@Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    super.onCreateContextMenu(menu, v, menuInfo);  
        menu.setHeaderTitle("O que deseja fazer?");  
        menu.add(0, v.getId(), 0, "Consultar ETP");  
        menu.add(0, v.getId(), 0, "Reenviar ETP");  
    }
	
	@Override  
	public boolean onContextItemSelected(MenuItem item) {  
		if (item.getTitle() == "Consultar ETP") {
			
			Intent i = new Intent(getActivity(), OsMenuActitivity.class);
			i.putExtra("_LINHA", String.valueOf(_linhaOs));
			startActivity(i);
		}
		else if (item.getTitle() == "Reenviar ETP") {
			Bundle arguments = new Bundle();
	        arguments.putString(_TAGLINHA, _linhaOs);
	        ReenviarOSFragment reenviarOSFragment = ReenviarOSFragment.newInstance(arguments);
	        android.support.v4.app.FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, reenviarOSFragment).commit();
		}
		else {
			return false;
		}
		return true;  
	}  

}
