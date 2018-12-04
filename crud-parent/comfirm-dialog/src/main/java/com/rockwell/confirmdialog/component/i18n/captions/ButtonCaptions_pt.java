package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'pt'.
 */
public class ButtonCaptions_pt extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "OK"},
                {ButtonType.ABORT.name(), "Interromper"},
                {ButtonType.CANCEL.name(), "Cancelar"},
                {ButtonType.YES.name(), "Sim"},
                {ButtonType.NO.name(), "Não"},
                {ButtonType.CLOSE.name(), "Fechar"},
                {ButtonType.SAVE.name(), "Gravar"},
                {ButtonType.RETRY.name(), "Repetir"},
                {ButtonType.IGNORE.name(), "Ignorar"},
                {ButtonType.HELP.name(), "Ajuda"},
        };
    }

}

