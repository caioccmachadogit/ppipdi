package base.htcom.com.br.ppipdiapp.padrao.menu;

import base.htcom.com.br.ppipdiapp.R;

/**
 * Created by ccouto on 19/12/2017.
 */

    public enum MenuItemEnum {
        ETP("ETP", false, R.string.menu_etp),
        ETP_Finalizada("ETP Finalizada", false, R.string.menu_etp_finalizada),
        Verificar_ETP("Verificar ETP", false, R.string.menu_verifica_etp),
        Verificar_Rev_ETP("Verificar Rev. ETP", false, R.string.menu_verificar_rev),
        Enviar_ETP("Enviar ETP", false, R.string.menu_enviar_etp),
        Enviar_Fotos("Enviar Fotos", false, R.string.menu_enviar_fotos),
        Sair("Sair", false, R.string.menu_sair),

        Est_Vert("1)Estrutura Vertical", false, R.string.menu_est_vert),
        Est_Vert_Fotos("2)Estrutura Vertical Fotos", false, R.string.menu_est_vert_fotos),
        Carregamento("3)Carregamento", false, R.string.menu_carregamento),
        Carregamento_Existente("4)Carregamento Existente", false, R.string.menu_carreg_exist),
        Arq_Pref("5)Arquivos Preferenciais", false, R.string.menu_arq_pref),
        Baterias("6)Baterias", false, R.string.menu_baterias),
        Notas("7)Notas", false, R.string.menu_notas),
        Finalizar_ETP("8)Finalizar ETP", false, R.string.menu_finalizar),
        Voltar("Voltar", false, R.string.menu_voltar);

        private String mItem;
        private boolean isChecked;
        private int menuId;

        MenuItemEnum(String item, boolean isChecked, int menuId) {
            this.mItem = item;
            this.isChecked = isChecked;
            this.menuId = menuId;
        }

        public String getItem() {
            return mItem;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

    public int getMenuId() {
        return menuId;
    }

    public void itemIsChecked(MenuItemEnum itemChecked){
            ETP.setChecked(false);
            ETP_Finalizada.setChecked(false);
            Verificar_ETP.setChecked(false);
            Verificar_Rev_ETP.setChecked(false);
            Enviar_ETP.setChecked(false);
            Enviar_Fotos.setChecked(false);
            Sair.setChecked(false);
            Est_Vert.setChecked(false);
            Est_Vert_Fotos.setChecked(false);
            Carregamento.setChecked(false);
            Carregamento_Existente.setChecked(false);
            Arq_Pref.setChecked(false);
            Baterias.setChecked(false);
            Notas.setChecked(false);
            Finalizar_ETP.setChecked(false);
            Voltar.setChecked(false);
            switch (itemChecked){
                case ETP:
                    ETP.setChecked(true);
                    break;
                case ETP_Finalizada:
                    ETP_Finalizada.setChecked(true);
                    break;
                case Verificar_ETP:
                    Verificar_ETP.setChecked(true);
                    break;
                case Verificar_Rev_ETP:
                    Verificar_Rev_ETP.setChecked(true);
                    break;
                case Enviar_ETP:
                    Enviar_ETP.setChecked(true);
                    break;
                case Enviar_Fotos:
                    Enviar_Fotos.setChecked(true);
                    break;
                case Sair:
                    Sair.setChecked(true);
                    break;
                case Est_Vert:
                    Est_Vert.setChecked(true);
                    break;
                case Est_Vert_Fotos:
                    Est_Vert_Fotos.setChecked(true);
                    break;
                case Carregamento:
                    Carregamento.setChecked(true);
                    break;
                case Carregamento_Existente:
                    Carregamento_Existente.setChecked(true);
                    break;
                case Arq_Pref:
                    Arq_Pref.setChecked(true);
                    break;
                case Baterias:
                    Baterias.setChecked(true);
                    break;
                case Notas:
                    Notas.setChecked(true);
                    break;
                case Finalizar_ETP:
                    Finalizar_ETP.setChecked(true);
                    break;
                case Voltar:
                    Voltar.setChecked(true);
                    break;
            }
        }
    }
