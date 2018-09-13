package base.htcom.com.br.ppipdiapp.main;

import android.os.Bundle;
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
import base.htcom.com.br.ppipdiapp.base.BaseActivity;
import base.htcom.com.br.ppipdiapp.bll.CarregamentoPlantaBLL;
import base.htcom.com.br.ppipdiapp.bll.ComboBLL;
import base.htcom.com.br.ppipdiapp.bll.ControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.InsumosBLL;
import base.htcom.com.br.ppipdiapp.bll.LogErrorBLL;
import base.htcom.com.br.ppipdiapp.bll.OsBLL;
import base.htcom.com.br.ppipdiapp.bll.SiteBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusControleUploadBLL;
import base.htcom.com.br.ppipdiapp.bll.StatusOsBLL;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;
import base.htcom.com.br.ppipdiapp.model.CarregamentoPlanta;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Insumos;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.Site;
import base.htcom.com.br.ppipdiapp.model.StatusControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.utils.AlertaDialog;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class MainActivity extends BaseActivity implements TarefaInterface {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setmActivity(this);
        replaceFragment(new ListOSFragment());
        setTitleTela("ETP");
        setUpToolbar();
        setupNavDrawer(MenuItemEnum.ETP, TipoMenu.PRINCIPAL);
    }

//    private void PreencherListMenu() {
//        dataList = new ArrayList<DrawerItem>();
//        // Add Drawer Item to dataList
//        dataList.add(new DrawerItem("ETP", R.drawable.ic_registration_form_inverse));
//        dataList.add(new DrawerItem("ETP Finalizada", R.drawable.ic_registration_form_inverse));
//        dataList.add(new DrawerItem("Verificar ETP", R.drawable.ic_sincronizacao));
//        dataList.add(new DrawerItem("Verificar Rev. ETP", R.drawable.ic_sincronizacao));
//        dataList.add(new DrawerItem("Enviar ETP", R.drawable.ic_upload_inverse));
//        dataList.add(new DrawerItem("Enviar Fotos", R.drawable.ic_upload_inverse));
//        dataList.add(new DrawerItem("Sair", R.mipmap.ic_cancelar));
//    }

    public void verificarOS(){
            POSITION = 0;
            Os pOs = new Os();
            pOs.setSOLIC_OV_SERV_NOME(SharedPreferencesUtills
                    .loadSavedPreferencesString("USER", this));
            pOs.setSOLIC_VISTORIA_EMPRESA_COD(SharedPreferencesUtills
                    .loadSavedPreferencesString("EMPRESA", this));
            pOs.setOS_SITUACAO("OS ABERTA");
            new AsyncReceberOS(this, this).execute(pOs);
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
    public void enviarUpload() {
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
