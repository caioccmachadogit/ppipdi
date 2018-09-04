package base.htcom.com.br.ppipdiapp.os;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.arq_pref.ArqPrefListFragment;
import base.htcom.com.br.ppipdiapp.bateria.ListBateriaFragment;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.carregamento.ListCarregFragment;
import base.htcom.com.br.ppipdiapp.carregamento.ListCarregPlantaFragment;
import base.htcom.com.br.ppipdiapp.ev.EvFotosFragment;
import base.htcom.com.br.ppipdiapp.ev.EvFragment;
import base.htcom.com.br.ppipdiapp.main.MainActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.notas.NotasFragment;
import base.htcom.com.br.ppipdiapp.padrao.drawer.DrawerItem;
import base.htcom.com.br.ppipdiapp.padrao.drawer.MenuUtills;

public class OsMenuActitivity extends AppCompatActivity{
	MenuUtills menuUtills = new MenuUtills();
	List<DrawerItem> dataList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	public static String _LINHA;
	public static String _OV_CHAMADO;
	public static String _CODIGO;
	public static Os _OS;
	private List<Os> lstOS;
	private OsBLL osBLL = new OsBLL();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view_os);
        _LINHA = getIntent().getStringExtra("_LINHA");
        try {
			lstOS = osBLL.listarById(this, _LINHA);
			_OS = lstOS.get(0);
			_OV_CHAMADO = lstOS.get(0).getOV_CHAMADO_NUM();
			_CODIGO = lstOS.get(0).getCODIGO();
		} catch (Exception e) {
			LogErrorBLL.LogError(e.getMessage(), "ERROR LISTARBYID_OS",this);
		}
        TextView tv_titulo = (TextView) findViewById(R.id.tv_titulo_os);
		tv_titulo.setText("Site: "+_OS.getCOD_ENTIDADE().replace("0", "")+" | ETP: "+lstOS.get(0).getCODIGO());
        ConfigurarMenu();
        /*if (savedInstanceState == null) {
			SelectItem(0);
		}*/
    }
	
	//==========PADR�O PARA CRIAR MENU===============================

    private void ConfigurarMenu(){
    	PreencherListMenu();
		mTitle = mDrawerTitle = getTitle();
		menuUtills.Inicializar((DrawerLayout) findViewById(R.id.drawer_layout),
				(ListView) findViewById(R.id.left_drawer), this, dataList);
        menuUtills.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setHomeButtonEnabled(true);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, menuUtills.mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
//				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
			public void onDrawerOpened(View drawerView) {
//				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		
		menuUtills.mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    
    private void PreencherListMenu() {
    	dataList = new ArrayList<DrawerItem>();
		// Add Drawer Item to dataList
    	if(_OS.getOS_SITUACAO().equals("OS ABERTA")){
    		dataList.add(new DrawerItem("1)Estrutura Vertical", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("2)Estrutura Vertical Fotos", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("3)Carregamento Novo", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("4)Carregamento Existente", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("5)Arquivos Preferenciais", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("6)Baterias", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("7)Notas", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("8)Finalizar ETP", R.mipmap.ic_action_good));
    		dataList.add(new DrawerItem("Voltar", R.mipmap.ic_voltar));
    	}
    	else {
    		dataList.add(new DrawerItem("1)Estrutura Vertical", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("2)Estrutura Vertical Fotos", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("3)Carregamento Revisão", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("4)Carregamento Existente", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("5)Arquivos Preferenciais", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("6)Baterias", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("7)Notas Revisão", R.mipmap.ic_compose_inverse));
    		dataList.add(new DrawerItem("8)Finalizar ETP", R.mipmap.ic_action_good));
    		dataList.add(new DrawerItem("Voltar", R.mipmap.ic_voltar));
		}
		
	}

	public void setTitle(CharSequence title) {
		mTitle = title;
//		getActionBar().setTitle(mTitle);
	}
    
    @Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return false;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			SelectItem(position);

		}
	}

    //==========PADR�O PARA CRIAR MENU===============================
	
	public void SelectItem(int possition) {
		Fragment fragment = null;
		FragmentManager frgManager = getSupportFragmentManager();
		switch (possition) {
			case 0:
				fragment = new EvFragment();
				frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				break;
			case 1:
				fragment = new EvFotosFragment();
				frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				break;
			case 2:
				fragment = new ListCarregFragment();
				frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				break;
			case 3:
				if(_OS.getOS_SITUACAO().equals("OS ABERTA")){
					fragment = new ListCarregPlantaFragment();
					frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				}
				else {
					Toast.makeText(this, "Não Habilitado para Revisão!", Toast.LENGTH_SHORT).show();
				}
				break;
			case 4:
				//startActivity(new Intent(this, ArqPrefActivity.class));
				fragment = new ArqPrefListFragment();
				frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				break;
			case 5:
				if(_OS.getOS_SITUACAO().equals("OS ABERTA")){
					fragment = new ListBateriaFragment();
					frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				}
				else {
					Toast.makeText(this, "Não Habilitado para Revisão!", Toast.LENGTH_SHORT).show();
				}
				break;
			case 6:
				fragment = new NotasFragment();
				frgManager.beginTransaction().replace(R.id.content_frame_os, fragment).commit();
				break;
			case 7:
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			     
				 alertDialogBuilder.setTitle("Finalizar ETP");
				 alertDialogBuilder.setMessage("Estará disponível somente para consulta, deseja finalizar?");
				 // set positive button: Yes message
				 alertDialogBuilder.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// Confirma exclus�o
							OsBLL osBLL = new OsBLL();
							StatusOsBLL statusOsBLL = new StatusOsBLL();
							try {
								Os osFinalizada = osBLL.listarById(getApplicationContext(), _LINHA).get(0);
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
								osFinalizada.setCONC_OV_SERV_NOME(osFinalizada.getSOLIC_OV_SERV_NOME());
								osFinalizada.setCONC_OV_SERV_DATA(dateFormat.format(new Date()));
								//CONTA ANEXOS PARA ETP
								ControleUploadBLL controleUploadBLL = new ControleUploadBLL();
								List<ControleUpload> lstControleUploadsRealizados = controleUploadBLL.listarRealizadosByOvChamado(getApplicationContext(), osFinalizada.getOV_CHAMADO_NUM());
								if(lstControleUploadsRealizados != null){
									osFinalizada.setANEXOS(String.valueOf(lstControleUploadsRealizados.size()));
								}
								if(osBLL.Update(getApplicationContext(), osFinalizada) == 1){
									if(statusOsBLL.Insert(getApplicationContext(), osBLL.listarById(getApplicationContext(), _LINHA).get(0)) != -1){
										Toast.makeText(getApplicationContext(), "ETP Finalizada pronta para envio!", Toast.LENGTH_SHORT).show();
									}
									else {
										Toast.makeText(getApplicationContext(), "Problemas, ETP Não Finalizada!", Toast.LENGTH_SHORT).show();
									}
								}
								else {
									Toast.makeText(getApplicationContext(), "Problemas, ETP Não Finalizada!", Toast.LENGTH_SHORT).show();
								}
							}
							catch (Exception e) {
								Toast.makeText(getApplicationContext(), "Problemas, ETP Não Finalizada!", Toast.LENGTH_SHORT).show();
								LogErrorBLL.LogError(e.getMessage(), "ERROR FINALIZACAO DE OS",getApplicationContext());
								e.printStackTrace();
							}
							startActivity(new Intent(getApplicationContext(), MainActivity.class));
							finish();
						}
					  });
				 // set negative button: No message
				 alertDialogBuilder.setNegativeButton("Não",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// cancel the alert box and put a Toast to the user
							dialog.cancel();
						}
					});
				  AlertDialog alertDialog = alertDialogBuilder.create();
				 // show alert
				 alertDialog.show();
				break;
			case 8:
				startActivity(new Intent(this, MainActivity.class));
				finish();
				break;
			default:
				break;
		}

		menuUtills.mDrawerList.setItemChecked(possition, true);
		setTitle(dataList.get(possition).getItemName());
		menuUtills.mDrawerLayout.closeDrawer(menuUtills.mDrawerList);
	}
	
	
	
}
