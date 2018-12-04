package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'sr'.
 */
public class ButtonCaptions_sr extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "У реду"},
                {ButtonType.ABORT.name(), "Обустави"},
                {ButtonType.CANCEL.name(), "Одустани"},
                {ButtonType.YES.name(), "Да"},
                {ButtonType.NO.name(), "Не"},
                {ButtonType.CLOSE.name(), "Затвори"},
                {ButtonType.SAVE.name(), "сачувај"},
                {ButtonType.RETRY.name(), "Покушај поново"},
                {ButtonType.IGNORE.name(), "Игнориши"},
                {ButtonType.HELP.name(), "Помоћ"},
        };
    }

}

