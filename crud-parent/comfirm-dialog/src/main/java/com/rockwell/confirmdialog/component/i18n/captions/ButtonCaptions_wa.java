package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'wa'.
 */
public class ButtonCaptions_wa extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "l' est Bon"},
                {ButtonType.ABORT.name(), "Abandner"},
                {ButtonType.CANCEL.name(), "Rinoncî"},
                {ButtonType.YES.name(), "Oyi"},
                {ButtonType.NO.name(), "Neni"},
                {ButtonType.CLOSE.name(), "Clôre"},
                {ButtonType.SAVE.name(), "Schaper"},
                {ButtonType.RETRY.name(), "Rissayî"},
                {ButtonType.IGNORE.name(), "Passer houte"},
                {ButtonType.HELP.name(), "Aidance"},
        };
    }

}

