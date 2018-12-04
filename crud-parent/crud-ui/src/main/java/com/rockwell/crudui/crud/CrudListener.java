package com.rockwell.crudui.crud;

import java.io.Serializable;
import java.util.Collection;

public interface CrudListener<T> extends Serializable {

    Collection<T> findAll();

    T add(T domainObjectToAdd);

    T update(T domainObjectToUpdate);

    void delete(T domainObjectToDelete);

}
