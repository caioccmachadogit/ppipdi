package base.htcom.com.br.ppipdiapp.menu;

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
        Sair("Sair", false, R.string.menu_sair);

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
            }
        }
    }
