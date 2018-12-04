package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;

/**
 * I18n for the button captions. This class contains the translations for language code 'el'.
 */
public class ButtonCaptions_el extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "Εντάξει"},
                {ButtonType.ABORT.name(), "Εγκατάλειψη"},
                {ButtonType.CANCEL.name(), "Ακύρωση"},
                {ButtonType.YES.name(), "Ναι"},
                {ButtonType.NO.name(), "Όχι"},
                {ButtonType.CLOSE.name(), "Κλείσιμο"},
                {ButtonType.SAVE.name(), "Αποθήκευση"},
                {ButtonType.RETRY.name(), "Προσπάθεια ξανά"},
                {ButtonType.IGNORE.name(), "Αγνόηση"},
                {ButtonType.HELP.name(), "Βοήθεια"},
        };
    }

}

