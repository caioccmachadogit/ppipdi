package base.htcom.com.br.ppipdiapp.arq_pref;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.adapter.AdapterSelectedArqPref;
import base.htcom.com.br.ppipdiapp.base.BaseFragment;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;

public class ArqPrefListFragment extends BaseFragment{
	
	private GridView gridView;
	private ComboBLL comboBLL = new ComboBLL();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.selected_arq_pref, null);
		gridView = (GridView) view.findViewById(R.id.gv_arqPref);
		AtualizarGridViewArqs();
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				ImageView imgAction = (ImageView) view.findViewById(R.id.imgCheck);
				String tipoRel = (String) imgAction.getTag();
				Intent i = new Intent(getActivity(), ArqPrefActivity.class);
				i.putExtra("_TIPOREL", String.valueOf(tipoRel));
				startActivity(i);
			}
			
		});
		return view;
	}
	private void AtualizarGridViewArqs() {
		try {
			String tipo =""; 
			if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("1")){
				tipo = "FL1";
			}
			else if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("2")){
				tipo = "FL2";
			}
			else if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("3")){
				tipo = "FL3";
			}
			else if(OsMenuActitivity._OV_CHAMADO.subSequence(8, 9).equals("4")){
				tipo = "FL4";
			}
			
			
			List<Combo> lst = new ArrayList<Combo>();
			lst = comboBLL.listarByFiltro_tipoRel(getActivity(), tipo);
			
			//CASO QUEIRA ELIMINAR OS TIPOS DE ARQ PREF DIRETO NA SELECAO DO ARQ
			//DEVERA REVER AS CONDICOES PARA QUANDO ABERTA/REVISAO
			/*if(OsMenuActitivity._OS.getOS_SITUACAO().equals("OS ABERTA")){
				lst = comboBLL.listarByFiltro_distinct(getActivity(), tipo);
			}
			else if(OsMenuActitivity._OS.getOS_SITUACAO().equals("CAMPO REVISAO")){
				
				List<ArqPref> lstArqPrefRev = arqPrefBLL.listarRealizadosByOvChamado(getActivity(), OsMenuActitivity._OV_CHAMADO);
				if(lstArqPrefRev != null){
					for(int i=0; i < lstArqPrefRev.size();i++){
						Combo comboRev = comboBLL.listarByOrdem(getActivity(), lstArqPrefRev.get(i).getArquivo_Carregado().substring(2, 5), tipo);
						if(comboRev != null){
							lst.add(comboRev);
						}
					}
				}
			}*/
			
			if(lst != null){
				AdapterSelectedArqPref adapter = new AdapterSelectedArqPref(getActivity(), R.layout.gv_item_arq_pref, lst);
				gridView.setAdapter(adapter);
			}
		}
		catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "Criar Grid ARQ",getActivity());
		}
		
	}

}
