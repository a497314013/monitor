package com.rockwell.crudui.crud;

import java.io.Serializable;

/**
 * @author Alejandro Duarte.
 */
@FunctionalInterface
public interface AddOperationListener<T> extends Serializable {

    T perform(T domainObject);

}
