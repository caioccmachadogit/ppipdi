package base.htcom.com.br.ppipdiapp.main;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.async.AsyncConfirmarEnvOS_Upload;
import base.htcom.com.br.ppipdiapp.async.AsyncConfirmarRecOS;
import base.htcom.com.br.ppipdiapp.async.AsyncControleUploads;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberCarPlanta;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberCombo;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberModeloAntena;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberOS;
import base.htcom.com.br.ppipdiapp.async.AsyncReceberSite;
import base.htcom.com.br.ppipdiapp.async.CallBackGeneric;
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
import base.htcom.com.br.ppipdiapp.model.CarregamentoPlanta;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.model.Insumos;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.Site;
import base.htcom.com.br.ppipdiapp.model.StatusControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;
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
    private List<ControleUpload> controleUploadList;
    private List<ControleUpload> controleUploadsSucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setmActivity(this);
        setTAG(getClass().getSimpleName());
        setTitleTela("ETP");
        setUpToolbar();
        setupNavDrawer(MenuItemEnum.ETP, TipoMenu.PRINCIPAL);
        fragmentTransaction(ListOSFragment.class.getSimpleName(), new ListOSFragment(), false, 1);
    }

    @Override
    public void onBackPressed() {
        abrirAlertLogout();
    }

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
                        persistirRecebimento();
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

    private void persistirRecebimento() throws Exception{

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
        final StatusControleUploadBLL statusControleUploadBLL = new StatusControleUploadBLL();
        ControleUploadBLL controleUploadBLL;
        try {
            final CallBackGeneric callBackControleUpload = new CallBackGeneric() {
                @Override
                public void callBackSuccess(Object response) {
                    try {
                        controleUploadsSucesso = (List<ControleUpload>) response;
                        if(controleUploadsSucesso.size()>0){
                            //REGISTRA CONFIRMACAO ENVIO
                            for (ControleUpload c:controleUploadsSucesso) {
                                StatusControleUpload statusControleUpload = new StatusControleUpload();
                                statusControleUpload.setLINHA(c.getLinha());
                                SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
                                statusControleUpload.setDATA_ENVIO(format.format(new Date()));
                                statusControleUploadBLL.insert(getContext(), statusControleUpload);
                            }
                            //FINALIZA ROTINA DE ENVIO UPLOAD
                            //INICIA CONFIRMACAO DE ENVIO DE OS FINALIZADA
                            new AsyncConfirmarEnvOS_Upload(mActivity, callBackConfirmarEnvOS()).execute(lstOsFinalizada);
                        }
                    }
                    catch (Exception e) {
                    }

                }

                @Override
                public void callBackError(Object response) {

                }
            };

            lstOsFinalizada = statusOsBLL.listarFotos(this, USER, EMPRESA);
            List<ControleUpload> lstControleUploads = new ArrayList<ControleUpload>();
            if(lstOsFinalizada.size() > 0 ){
                controleUploadList = new ArrayList<ControleUpload>();
                for(int i=0; i < lstOsFinalizada.size();i++){
                    controleUploadBLL = new ControleUploadBLL();
                    lstControleUploads = controleUploadBLL.listarByOvChamado(this, lstOsFinalizada.get(i).getOV_CHAMADO_NUM());
                    if(lstControleUploads != null){
                        //GUARDA AS LISTCONTROLEUPLOADS QUANDO POSSUI REGISTROS
                        controleUploadList.addAll(lstControleUploads);
                    }
                    else {
                        //OS FINALIZADA MAIS N�O POSSUI UPLOADS,
                        //ATUALIZAR A COLUNA ENVIADO_FOTOS NA TAB OS_STATUS
                        statusOsBLL.updateStatus(this, lstOsFinalizada.get(i).getLINHA(), "imagem");
                        Toast.makeText(this, "Nenhuma Imagem para a ETP: "+lstOsFinalizada.get(i).getOV_CHAMADO_NUM()+"", Toast.LENGTH_SHORT).show();
                    }
                }
                if(controleUploadList.size() > 0){
                    new AsyncControleUploads(this, callBackControleUpload,controleUploadList).execute();
                }
            }
            else {
                Toast.makeText(this, "Nenhuma foto encontrada!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //======ENVIO UPLOAD ARQ PREF============================
    }

    public CallBackGeneric callBackConfirmarEnvOS(){
        return new CallBackGeneric() {
            @Override
            public void callBackSuccess(Object response) {
                try {
                    List<String> osLinha = (List<String>) response;

                    if(osLinha.size() > 0){
                        StatusOsBLL statusOsBLL = new StatusOsBLL();
                        for (String linha:osLinha) {
                            statusOsBLL.updateStatus(getContext(), linha, "imagem");
                        }
                        new AlertaDialog(getContext()).showDialogAviso(
                                "Confirmação Envio",
                                "ETP Confirmada: "+osLinha.size()+" | Imagens: "+controleUploadsSucesso.size()+"");

                        fragmentTransaction(ListOSFinalizadaFragment.class.getSimpleName(), new ListOSFinalizadaFragment(), false, 1);
                    }
                }
                catch (Exception e) {
                }
            }

            @Override
            public void callBackError(Object response) {

            }
        };
    }
    //==================ENVIO UPLOAD=====================================================












}
