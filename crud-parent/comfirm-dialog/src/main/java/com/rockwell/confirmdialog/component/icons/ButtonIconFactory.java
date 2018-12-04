package com.rockwell.confirmdialog.component.icons;

import com.rockwell.confirmdialog.ButtonType;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.io.Serializable;


/**
 * This interface defines the essential methods for a ButtonIconFactory
 *
 * @author Dieter Steinwedel
 * @author Carlos Laspina
 */
public interface ButtonIconFactory extends Serializable {

    /**
     * Loads the resource for the given buttonType.
     *
     * @param buttonType The ButtonType
     * @return The resource
     */
    public VaadinIcon getIcon(ButtonType buttonType);

}