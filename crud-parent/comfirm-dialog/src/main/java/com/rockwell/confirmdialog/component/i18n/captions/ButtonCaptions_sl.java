package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'sl'.
 */
public class ButtonCaptions_sl extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "V redu"},
                {ButtonType.ABORT.name(), "Prekini"},
                {ButtonType.CANCEL.name(), "Prekliči"},
                {ButtonType.YES.name(), "Da"},
                {ButtonType.NO.name(), "Ne"},
                {ButtonType.CLOSE.name(), "Zapri"},
                {ButtonType.SAVE.name(), "Shrani"},
                {ButtonType.RETRY.name(), "Poskusi znova"},
                {ButtonType.IGNORE.name(), "Prezri"},
                {ButtonType.HELP.name(), "Pomoč"},
        };
    }

}

