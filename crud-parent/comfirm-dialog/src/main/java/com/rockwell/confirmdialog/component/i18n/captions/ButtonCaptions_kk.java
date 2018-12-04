package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'kk'.
 */
public class ButtonCaptions_kk extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "ОК"},
                {ButtonType.ABORT.name(), "Доғару"},
                {ButtonType.CANCEL.name(), "Қайту"},
                {ButtonType.YES.name(), "Иә"},
                {ButtonType.NO.name(), "Жоқ"},
                {ButtonType.CLOSE.name(), "Жабу"},
                {ButtonType.SAVE.name(), "Сақтау"},
                {ButtonType.RETRY.name(), "Қайталау"},
                {ButtonType.IGNORE.name(), "Елемеу"},
                {ButtonType.HELP.name(), "Анықтама"},
        };
    }

}

