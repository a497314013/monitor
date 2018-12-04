package com.rockwell.confirmdialog.component.i18n.captions;

import java.util.ListResourceBundle;

import com.rockwell.confirmdialog.ButtonType;


/**
 * I18n for the button captions. This class contains the translations for language code 'uk'.
 */
public class ButtonCaptions_uk extends ListResourceBundle {

    /**
     * See {@link ListResourceBundle#getContents()}
     */
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {ButtonType.OK.name(), "Гаразд"},
                {ButtonType.ABORT.name(), "Перервати"},
                {ButtonType.CANCEL.name(), "Скасувати"},
                {ButtonType.YES.name(), "Так"},
                {ButtonType.NO.name(), "Ні"},
                {ButtonType.CLOSE.name(), "Закрити"},
                {ButtonType.SAVE.name(), "Зберегти"},
                {ButtonType.RETRY.name(), "Спробувати ще раз"},
                {ButtonType.IGNORE.name(), "Ігнорувати"},
                {ButtonType.HELP.name(), "Довідка"},
        };
    }

}

