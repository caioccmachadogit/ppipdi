package base.htcom.com.br.ppipdiapp.os;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.base.BaseActivity;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.ev.EvFragment;
import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Os;

public class OsMenuActitivity extends BaseActivity{

	public static String _LINHA;
	public static String _OV_CHAMADO;
	public static String _CODIGO;
	public static Os _OS;
	private List<Os> lstOS;
	private OsBLL osBLL = new OsBLL();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

		setmActivity(this);
		replaceFragment(new EvFragment());
		setTitleTela(MenuItemEnum.Est_Vert.getItem());
		setUpToolbar();
		setupNavDrawer(MenuItemEnum.Est_Vert, TipoMenu.OS);

    }

//    private void PreencherListMenu() {
//		// Add Drawer Item to dataList
//    	if(_OS.getOS_SITUACAO().equals("OS ABERTA")){
//    		dataList.add(new DrawerItem("1)Estrutura Vertical", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("2)Estrutura Vertical Fotos", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("3)Carregamento Novo", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("4)Carregamento Existente", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("5)Arquivos Preferenciais", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("6)Baterias", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("7)Notas", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("8)Finalizar ETP", R.mipmap.ic_action_good));
//    		dataList.add(new DrawerItem("Voltar", R.mipmap.ic_voltar));
//    	}
//    	else {
//    		dataList.add(new DrawerItem("1)Estrutura Vertical", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("2)Estrutura Vertical Fotos", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("3)Carregamento Revisão", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("4)Carregamento Existente", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("5)Arquivos Preferenciais", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("6)Baterias", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("7)Notas Revisão", R.mipmap.ic_compose_inverse));
//    		dataList.add(new DrawerItem("8)Finalizar ETP", R.mipmap.ic_action_good));
//    		dataList.add(new DrawerItem("Voltar", R.mipmap.ic_voltar));
//		}
//	}

	public boolean verificaOsAberta(){
		if(_OS.getOS_SITUACAO().equals("OS ABERTA")){
			return true;
		}
		else {
			Toast.makeText(this, "Não Habilitado para Revisão!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	public void finalizarOs(){
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
	}
}
