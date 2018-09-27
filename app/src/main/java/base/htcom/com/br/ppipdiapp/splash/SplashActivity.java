package base.htcom.com.br.ppipdiapp.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.base.BaseActivity;
import base.htcom.com.br.ppipdiapp.bll.ArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.BateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoPlantaBLL;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.InsumosBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.SiteBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusArqPrefBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusBateriaBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusCarregamentoBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.login.LoginActivity;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;


public class SplashActivity extends BaseActivity {

	private Thread mSplashThread;

    private boolean mblnClicou = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.d("checkPermission", "PERMITIDO");
                startSplash();
            }
            else {
                Log.d("checkPermission", "NAO PERMITIDO");
                requestPermission();
            }
        }
        else {
            Log.d("checkPermission", "PERMISSAO NAO REQUERIDA. VERSAO ANDROID < 23");
            startSplash();
        }
    }

    private void startSplash() {
        toast("Dispositivo habilitado, carregando aguarde!");
        //thread para mostrar uma tela de Splash
        mSplashThread = new Thread() {
            @Override
            public void run() {
             try {
                    synchronized(this){
                        //Espera por 5 segundos or sai quando
                     //o usu�rio tocar na tela
                        wait(5000);
                        mblnClicou = true;
                    }
                }
                catch(InterruptedException ex){
                }

                if (mblnClicou){
                	try {
                		/*----------------------------------------------------------------------------------------------------------------------------------------*/
                		// **** CARREGA UM ARRAY DE QUERYS PARA EXECU��O NO MOMENTO DE CRIA��O DO BANCO DE DADOS
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(LogErrorBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(OsBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(SiteBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(ComboBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(CarregamentoPlantaBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(CarregamentoBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(ArqPrefBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(ControleUploadBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(StatusOsBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(StatusArqPrefBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(StatusCarregamentoBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(StatusControleUploadBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(InsumosBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(BateriaBLL.createTable);
                		GerenciadorDB.QUERY_CREATE_BANCO_DE_DADOS.add(StatusBateriaBLL.createTable);
                		// **** CRIA DIR EXTERNO E BANCO DE DADOS
                		GerenciadorDB ger = new GerenciadorDB();
                		ger.criarDB(SplashActivity.this);
                		/*----------------------------------------------------------------------------------------------------------------------------------------*/
                     //fechar a tela de Splash
                        finish();

                     //Carrega a Activity Principal
                     Intent i = new Intent();
                     i.setClass(SplashActivity.this, LoginActivity.class);
                     startActivity(i);
					}
                	catch (Exception e) {

					}

                }
            }
        };

        mSplashThread.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        //garante que quando o usu�rio clicar no bot�o
        //"Voltar" o sistema deve finalizar a thread
        if(mSplashThread != null)
            mSplashThread.interrupt();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_APP:
                if (grantResults.length > 0 && verifyGrantResults(grantResults)) {
                    Log.d("PermissionResult", "PERMISSAO CONCEDIDA");
                    startSplash();
                }
                else {
                    Log.d("PermissionResult", "PERMISSAO NAO CONCEDIDA");
                    toast("Permissão não concedida!");
                }
                break;
        }
    }
}
