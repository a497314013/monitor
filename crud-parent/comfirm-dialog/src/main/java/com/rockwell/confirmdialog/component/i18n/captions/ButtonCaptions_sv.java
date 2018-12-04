package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'sv'.
 */
public class ButtonCaptions_sv extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "Ok"},
                {ButtonType.ABORT.name(), "Avbryt"},
                {ButtonType.CANCEL.name(), "Avbryt"},
                {ButtonType.YES.name(), "Ja"},
                {ButtonType.NO.name(), "Nej"},
                {ButtonType.CLOSE.name(), "Stäng"},
                {ButtonType.SAVE.name(), "Spara"},
                {ButtonType.RETRY.name(), "Försök igen"},
                {ButtonType.IGNORE.name(), "Ignorera"},
                {ButtonType.HELP.name(), "Hjälp"},
        };
    }

}

