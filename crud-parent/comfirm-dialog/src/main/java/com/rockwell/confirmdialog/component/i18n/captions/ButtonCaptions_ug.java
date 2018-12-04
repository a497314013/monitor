package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'ug'.
 */
public class ButtonCaptions_ug extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "تامام"},
                {ButtonType.ABORT.name(), "توختات"},
                {ButtonType.CANCEL.name(), "ئەمەلدىن قالدۇر"},
                {ButtonType.YES.name(), "ھەئە"},
                {ButtonType.NO.name(), "ياق"},
                {ButtonType.CLOSE.name(), "ياپ"},
                {ButtonType.SAVE.name(), "ساقلا"},
                {ButtonType.RETRY.name(), "قايتا سىنا"},
                {ButtonType.IGNORE.name(), "پەرۋا قىلما"},
                {ButtonType.HELP.name(), "ياردەم"},
        };
    }

}

