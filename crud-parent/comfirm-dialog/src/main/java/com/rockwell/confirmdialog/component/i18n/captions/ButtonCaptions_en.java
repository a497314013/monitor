package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'en'.
 *
 * @author Carlos Laspina
 */
public class ButtonCaptions_en extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "OK"},
                {ButtonType.ABORT.name(), "Abort"},
                {ButtonType.CANCEL.name(), "Cancel"},
                {ButtonType.YES.name(), "Yes"},
                {ButtonType.NO.name(), "No"},
                {ButtonType.CLOSE.name(), "Close"},
                {ButtonType.SAVE.name(), "Save"},
                {ButtonType.RETRY.name(), "Retry"},
                {ButtonType.IGNORE.name(), "Ignore"},
                {ButtonType.HELP.name(), "Help"},
        };
    }

}

