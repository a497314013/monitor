package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;

/**
 * I18n for the button captions. This class contains the translations for language code 'eo'.
 */
public class ButtonCaptions_eo extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "Konfirmi"},
                {ButtonType.ABORT.name(), "Ĉesigi"},
                {ButtonType.CANCEL.name(), "Rezigni"},
                {ButtonType.YES.name(), "Jes"},
                {ButtonType.NO.name(), "Ne"},
                {ButtonType.CLOSE.name(), "Fermi"},
                {ButtonType.SAVE.name(), "Konsevi"},
                {ButtonType.RETRY.name(), "Provi denove"},
                {ButtonType.IGNORE.name(), "Malatenti"},
                {ButtonType.HELP.name(), "Helpo"},
        };
    }

}

