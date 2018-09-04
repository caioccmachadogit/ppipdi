package base.htcom.com.br.ppipdiapp.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.async.AsyncConfirmarEnvOS_Upload;
import base.htcom.com.br.ppipdiapp.async.AsyncConfirmarRecOS;
import base.htcom.com.br.ppipdiapp.async.AsyncEnviarUploadArqPref;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberCarPlanta;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberCombo;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberModeloAntena;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberOS;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberSite;
import base.htcom.com.br.ppipdiapp.async.TarefaInterface;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoPlantaBLL;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.InsumosBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.SiteBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.login.LogoutUtills;
import base.htcom.com.br.ppipdiapp.model.CarregamentoPlanta;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Insumos;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.Site;
import base.htcom.com.br.ppipdiapp.model.StatusControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.drawer.DrawerItem;
import base.htcom.com.br.ppipdiapp.padrao.drawer.MenuUtills;
import base.htcom.com.br.ppipdiapp.padrao.funcoes.ControleConexao;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class MainActivity extends AppCompatActivity implements TarefaInterface {

    MenuUtills menuUtills = new MenuUtills();
    List<DrawerItem> dataList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private int POSITION;
    public static int COUNTOS=0; //var de controle precisa inicializar
    public static int COUNTSITE=0; //var de controle precisa inicializar
    public static int COUNTCOMBO=0; //var de controle precisa inicializar
    public static int COUNTANTENA=0; //var de controle precisa inicializar
    public static int COUNTCAR=0; //var de controle precisa inicializar
    public static int COUNTCONFOS=0; //var de controle precisa inicializar
    private List<Os> listOsRec;
    private List<Os> listEnlacesRec;
    private List<Site> listSiteRec = new ArrayList<Site>(); //controle precisa inicializar
    private List<Combo> listComboRec;
    private List<Insumos> listInsumosRec;
    private List<CarregamentoPlanta> listCarPlntaRec;

    //=======ENVIO UPLOAD============================
    private List<Os> lstOsFinalizada;
    public static int cEnvioUpArqPref=0;
    public static int contaEnvUpArqPref=0;
    public static int contaUpArqPref=0;
    public static int contaConfEnvOs=0;
    public static int cEnvioConfEnvOs=0;
    private List<ControleUpload> lstUpArqPrefEnvio;
    public static String USER;
    public static String EMPRESA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view_main);
        TextView tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_titulo.setText("Usuário: "+ SharedPreferencesUtills.loadSavedPreferencesString("USER", this));
        USER = SharedPreferencesUtills.loadSavedPreferencesString("USER", this);
        EMPRESA = SharedPreferencesUtills.loadSavedPreferencesString("EMPRESA", this);
        ConfigurarMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash, menu);
        return true;
    }

    // ==========PADR�O PARA CRIAR MENU===============================

    @SuppressLint("NewApi")
    private void ConfigurarMenu() {
        PreencherListMenu();
        mTitle = mDrawerTitle = getTitle();
        menuUtills.Inicializar((DrawerLayout) findViewById(R.id.drawer_layout),
                (ListView) findViewById(R.id.left_drawer), this, dataList);
        menuUtills.mDrawerList
                .setOnItemClickListener(new DrawerItemClickListener());

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                menuUtills.mDrawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        menuUtills.mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void PreencherListMenu() {
        dataList = new ArrayList<DrawerItem>();
        // Add Drawer Item to dataList
        dataList.add(new DrawerItem("ETP", R.drawable.ic_registration_form_inverse));
        dataList.add(new DrawerItem("ETP Finalizada", R.drawable.ic_registration_form_inverse));
        dataList.add(new DrawerItem("Verificar ETP", R.drawable.ic_sincronizacao));
        dataList.add(new DrawerItem("Verificar Rev. ETP", R.drawable.ic_sincronizacao));
        dataList.add(new DrawerItem("Enviar ETP", R.drawable.ic_upload_inverse));
        dataList.add(new DrawerItem("Enviar Fotos", R.drawable.ic_upload_inverse));
        dataList.add(new DrawerItem("Sair", R.mipmap.ic_cancelar));
    }

    @SuppressLint("NewApi")
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
//        getActionBar().setTitle(mTitle);
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

    @Override
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

    // ==========PADR�O PARA CRIAR MENU===============================

    @SuppressLint("NewApi")
    public void SelectItem(int possition) {
        Fragment fragment = null;
        FragmentManager frgManager = getSupportFragmentManager();
        switch (possition) {
            case 0: // LISTAGEM OSs
                fragment = new ListOSFragment();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                break;
            case 1: // LISTAGEM OSs Finalizada
                fragment = new ListOSFinalizadaFragment();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                break;
            case 2:// RECEBIMENTO OSs
                if(!ControleConexao.checkNetworkInterface(this).equals("none")){
                    POSITION = 0;
                    Os pOs = new Os();
                    pOs.setSOLIC_OV_SERV_NOME(SharedPreferencesUtills
                            .loadSavedPreferencesString("USER", this));
                    pOs.setSOLIC_VISTORIA_EMPRESA_COD(SharedPreferencesUtills
                            .loadSavedPreferencesString("EMPRESA", this));
                    pOs.setOS_SITUACAO("OS ABERTA");
                    new AsyncReceberOS(this, this).execute(pOs);
                }
                else {
                    Toast.makeText(this, "Verifique sua Conexão com a Internet e tente novamente!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3: // RECEBIMENTO OSs - REVISAO
                if(!ControleConexao.checkNetworkInterface(this).equals("none")){
                    fragment = new ReceberOSRevFragment();
                    frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }
                else {
                    Toast.makeText(this, "Verifique sua Conexão com a Internet e tente novamente!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4: // ENVIO OSs
                if(!ControleConexao.checkNetworkInterface(this).equals("none")){
                    fragment = new EnviarOSFragment();
                    frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                }
                else {
                    Toast.makeText(this, "Verifique sua Conexão com a Internet e tente novamente!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5: // ENVIO Fotos
                if(!ControleConexao.checkNetworkInterface(this).equals("none")){
                    EnviarUpload();
                }
                else {
                    Toast.makeText(this, "Verifique sua Conexão com a Internet e tente novamente!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6: // SAIR
                startActivity(new Intent(this, LogoutUtills.class));
                break;
            default:
                break;
        }

        menuUtills.mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        menuUtills.mDrawerLayout.closeDrawer(menuUtills.mDrawerList);
    }

    //==================RECEBIMENTO OS=====================================================
    @Override
    public void respostaAsync(String json) {
        try {
            Gson gson = new Gson();
            GsonBuilder	builder = new GsonBuilder();
            switch (POSITION) {
                case 0: // RECEBIMENTO OSs
                    if(!json.equals("false") && !json.equals("x") && json != null){
                        Type listTypeOs = new TypeToken<List<Os>>(){}.getType();
                        listOsRec = gson.fromJson(json, listTypeOs);
                        //==========PARA CADA OS RECEBIDA VERIFICAR INFORMA��ES QUE COMP�E A OS=================

                        //====VERIFICAR SITE ================================
                        for(int j=0; j < listOsRec.size();j++){
                            POSITION = 2;
                            new AsyncReceberSite(this, this).execute(listOsRec.get(j).getCOD_ENTIDADE());
                        }
                    }
                    else {
                        if(json.equals("false")){
                            new AlertaDialog(this).showDialogAviso("Recebimento ETP","Nenhuma ETP Recebida!");
                        }
                        else
                            new AlertaDialog(this).showDialogAviso("Servidor Inacessível","Tente mais tarde!");
                    }
                    break;
                case 2: // RECEBIMENTO SITE
                    if(!json.equals("false") && !json.equals("x") && json != null){
                        gson    = builder.create();
                        Site site = gson.fromJson(json, Site.class);
                        if(site != null){
                            listSiteRec.add(site);
                        }
                    }
                    //====VERIFICAR COMBO ================================
                    if(COUNTSITE == listOsRec.size()){
                        POSITION = 3;
                        ComboBLL comboBLL = new ComboBLL();
                        List<Combo> comboConfig = comboBLL.listarByFiltro(this, "CONFCOMBO",null);
                        //FL1 = PPI MW
                        //FL2 = PDI MW
                        //FL3 = PPI RF
                        //FL4 = PDI RF
                        //LC1 = TIPO SITE
                        //FS = ESTRUTURA TIPO
                        //TA = TIPO ANTENA
                        if(comboConfig.size() == 1 ){ //JA EXISTE UM COD, NECESS�RIO VERICAR NA WEB
                            new AsyncReceberCombo(this, this).execute("FL1|FL2|FL3|FL4|LC1|FS|TA", comboConfig.get(0).getCOD());
                        }
                        //N�O EXISTE COD, NECESS�RIO PEGAR NA WEB
                        else new AsyncReceberCombo(this, this).execute("FL1|FL2|FL3|FL4|LC1|FS|TA", "00");
                    }
                    //====VERIFICAR COMBO ================================
                    break;
                case 3:// RECEBIMENTO COMBO
                    if(!json.equals("false") && !json.equals("x") && json != null){
                        if(!json.equals("false") && !json.equals("x") && json != null){
                            Type listTypeCombo = new TypeToken<List<Combo>>(){}.getType();
                            listComboRec = gson.fromJson(json, listTypeCombo);
                        }
                    }
                    //====VERIFICAR MODELO ANTENA ================================
                    if(COUNTCOMBO == 1){
                        POSITION = 5;
                        new AsyncReceberModeloAntena(this, this).execute("antenas");
                    }
                    //====VERIFICAR MODELO ANTENA ================================
                    break;
                case 5:// RECEBIMENTO MODELO ANTENA
                    if(!json.equals("false") && !json.equals("x") && json != null){
                        if(!json.equals("false") && !json.equals("x") && json != null){
                            Type listTypeInsumos = new TypeToken<List<Insumos>>(){}.getType();
                            listInsumosRec = gson.fromJson(json, listTypeInsumos);
                        }
                    }
                    //====VERIFICAR CARREGAMENTO PLANTA ================================
                    if(COUNTANTENA == 1){
                        POSITION = 6;
                        for(int i=0; i < listOsRec.size();i++){
                            POSITION = 6;
                            new AsyncReceberCarPlanta(this, this).execute(listOsRec.get(i).getCOD_ENTIDADE());
                        }
                    }
                    //====VERIFICAR CARREGAMENTO PLANTA ================================
                    break;
                case 6:// RECEBIMENTO CARREGAMENTO PLANTA
                    if(!json.equals("false") && !json.equals("x") && json != null){
                        if(!json.equals("false") && !json.equals("x") && json != null){
                            Type listTypeCarPlanta = new TypeToken<List<CarregamentoPlanta>>(){}.getType();
                            listCarPlntaRec = gson.fromJson(json, listTypeCarPlanta);
                        }
                    }
                    //====VERIFICAR SE EST� NO MOMENTO DE PERSISTIR O RECEBIMENTO================================
                    if(COUNTCAR == listOsRec.size()){
                        PersistirRecebimento();
                    }
                    //====VERIFICAR SE EST� NO MOMENTO DE PERSISTIR O RECEBIMENTO================================
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            COUNTOS = 0;
            COUNTSITE = 0;
            COUNTCOMBO = 0;
            COUNTCAR = 0;
            COUNTCONFOS = 0;
            COUNTANTENA = 0;
            listSiteRec = new ArrayList<Site>();
            LogErrorBLL.LogError(e.getMessage(), "ERROR SINCRONISMO REC",this);
        }
    }

    private void PersistirRecebimento() throws Exception{

        try {
            OsBLL osBLL = new OsBLL();
            SiteBLL siteBLL = new SiteBLL();
            ComboBLL comboBLL = new ComboBLL();
            InsumosBLL insumosBLL = new InsumosBLL();
            CarregamentoPlantaBLL carregamentoBLL = new CarregamentoPlantaBLL();
            long validar = 0;
            if(listOsRec != null){
                validar = osBLL.Insert(this, listOsRec);
            }
            if(listEnlacesRec != null){
                validar = osBLL.Insert(this, listEnlacesRec);
            }
            if(listSiteRec != null){
                validar = siteBLL.Insert(this, listSiteRec);
            }
            if(listComboRec != null){
                validar = comboBLL.Insert(this, listComboRec);
            }
            if(listInsumosRec != null){
                validar = insumosBLL.Insert(this, listInsumosRec);
            }
            if(listCarPlntaRec != null){
                validar = carregamentoBLL.Insert(this, listCarPlntaRec);
            }
            if(validar == -1){
                LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO RECEBIMENTO",this);
            }
            //====CONFIRMA REC DE OS ================================
            for(int i=0; i < listOsRec.size();i++){
                new AsyncConfirmarRecOS(this, this).execute(listOsRec.get(i));
            }
            //====CONFIRMA REC DE OS ================================
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void respostaAsyncConfirmaRecOs(String json) {
        if(COUNTCONFOS == listOsRec.size()){
            int cOS = 0, cEnl = 0, cSite = 0, cCombo = 0, cAntena = 0, cCarPlan = 0;
            if(listOsRec != null){
                cOS = listOsRec.size();
            }
			/*if(listEnlacesRec != null){
				cEnl = listEnlacesRec.size();
			}*/
            if(listSiteRec != null){
                cSite = listSiteRec.size();
            }
            if(listComboRec != null){
                cCombo = listComboRec.size();
            }
            if(listInsumosRec != null){
                cAntena = listInsumosRec.size();
            }
            if(listCarPlntaRec != null){
                cCarPlan = listCarPlntaRec.size();
            }
            new AlertaDialog(this).showDialogAviso("Recebimento", "ETP: "+cOS+" | Sites: "+cSite+" | Configurações: "+cCombo+" | Carregamento: "+cCarPlan+" | Antenas: "+cAntena+"");
            listSiteRec = new ArrayList<Site>();
            COUNTCONFOS = 0;
        }
    }
//==================RECEBIMENTO OS=====================================================


    //==================ENVIO UPLOAD=====================================================
    private void EnviarUpload() {
        //======ENVIO UPLOAD ARQ PREF============================
        StatusOsBLL statusOsBLL = new StatusOsBLL();
        ControleUploadBLL controleUploadBLL;
        Gson gson;
        try {
            lstOsFinalizada = statusOsBLL.listarFotos(this, USER, EMPRESA);
            List<ControleUpload> lstControleUploads = new ArrayList<ControleUpload>();
            if(lstOsFinalizada.size() > 0 ){
                lstUpArqPrefEnvio = new ArrayList<ControleUpload>();
                for(int i=0; i < lstOsFinalizada.size();i++){
                    controleUploadBLL = new ControleUploadBLL();
                    lstControleUploads = controleUploadBLL.listarByOvChamado(this, lstOsFinalizada.get(i).getOV_CHAMADO_NUM());
                    if(lstControleUploads != null){
                        //GUARDA AS LISTCONTROLEUPLOADS QUANDO POSSUI REGISTROS
                        lstUpArqPrefEnvio.addAll(lstControleUploads);
                    }
                    else {
                        //OS FINALIZADA MAIS N�O POSSUI UPLOADS,
                        //ATUALIZAR A COLUNA ENVIADO_FOTOS NA TAB OS_STATUS
                        statusOsBLL.updateStatus(this, lstOsFinalizada.get(i).getLINHA(), "imagem");
                        Toast.makeText(this, "Nenhuma Imagem para a ETP: "+lstOsFinalizada.get(i).getOV_CHAMADO_NUM()+"", Toast.LENGTH_SHORT).show();
                    }
                }
                if(lstUpArqPrefEnvio.size() > 0){
                    gson = new GsonBuilder().create();
                    JsonArray jsonArrayUpArq = gson.toJsonTree(lstUpArqPrefEnvio).getAsJsonArray();
                    for(int j=0;j< jsonArrayUpArq.size();j++){
                        JsonElement jsonUpArqPref = jsonArrayUpArq.get(j);
                        new AsyncEnviarUploadArqPref(this, this).execute(jsonUpArqPref.toString());
                    }
                }
            }
            else {
                Toast.makeText(this, "Nenhuma ETP Finalizada!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //======ENVIO UPLOAD ARQ PREF============================
    }

    @Override
    public void respostaAsyncEnvioUpload(String json) {
        try {
            if(json.equals("true")){
                //CONFIRMA ENVIO
                StatusControleUploadBLL statusControleUploadBLL = new StatusControleUploadBLL();
                StatusControleUpload statusControleUpload = new StatusControleUpload();
                statusControleUpload.setLINHA(lstUpArqPrefEnvio.get(cEnvioUpArqPref-1).getLinha());
                SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
                statusControleUpload.setDATA_ENVIO(format.format(new Date()));
                if(statusControleUploadBLL.Insert(this, statusControleUpload) != -1){
                    contaEnvUpArqPref++;
                }
            }
            if(lstUpArqPrefEnvio.size() == cEnvioUpArqPref){
                //FINALIZA ROTINA DE ENVIO UPLOAD ARQ PREF
                //INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA
                for(int i=0; i < lstOsFinalizada.size();i++){
                    new AsyncConfirmarEnvOS_Upload(this, this).execute(lstOsFinalizada.get(i));
                }
            }
            else {
                Toast.makeText(this, contaEnvUpArqPref+" imagem enviada", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            contaUpArqPref = 0;
            contaEnvUpArqPref = 0;
            cEnvioUpArqPref = 0;
            contaConfEnvOs = 0;
        }
    }

    @Override
    public void respostaAsyncConfirmaEnvOs(String json) {
        try {
            if(json.equals("true")){
                //CONFIRMA ENVIO
                contaConfEnvOs++;
            }
            if(cEnvioConfEnvOs == lstOsFinalizada.size()){
                StatusOsBLL statusOsBLL = new StatusOsBLL();
                if(contaEnvUpArqPref > 0){
                    for(int i=0; i < lstOsFinalizada.size();i++){
                        statusOsBLL.updateStatus(this, lstOsFinalizada.get(i).getLINHA(), "imagem");
                    }
                    new AlertaDialog(this).showDialogAviso("Confirmação Envio", "ETP Confirmada: "+contaConfEnvOs+" | Imagens: "+contaEnvUpArqPref+"");
                }
                ZerarCounts();
            }
        }
        catch (Exception e) {
            ZerarCounts();
        }
    }

    private void ZerarCounts(){
        contaConfEnvOs = 0;
        cEnvioConfEnvOs = 0;

        contaEnvUpArqPref = 0;
        cEnvioUpArqPref = 0;
        contaUpArqPref = 0;
    }
    //==================ENVIO UPLOAD=====================================================












}
