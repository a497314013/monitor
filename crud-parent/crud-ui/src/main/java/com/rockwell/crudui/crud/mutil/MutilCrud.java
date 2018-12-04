package com.rockwell.crudui.crud.mutil;

import com.rockwell.crudui.crud.AddOperationListener;
import com.rockwell.crudui.crud.FindAllCrudOperationListener;
import com.rockwell.crudui.crud.UpdateOperationListener;
import com.rockwell.crudui.form.CrudFormFactory;
import com.rockwell.crudui.layout.CrudLayout;
import com.vaadin.flow.data.provider.DataProvider;

public interface MutilCrud<T> {

    void setAddOperationVisible(boolean visible);

    void setUpdateOperationVisible(boolean visible);

    void setDeleteOperationVisible(boolean visible);

    void setFindAllOperationVisible(boolean visible);

    CrudFormFactory<T> getCrudFormFactory();

    void setCrudFormFactory(CrudFormFactory<T> crudFormFactory);

    void setFindAllOperation(FindAllCrudOperationListener<T> findAllOperation);

    void setFindAllOperation(DataProvider<T, Void> dataProvider);

    void setAddOperation(AddOperationListener<T> addOperation);

    void setUpdateOperation(UpdateOperationListener<T> updateOperation);

    void setDeleteOperation(MutilDeleteOperationListener<T> deleteOperation);

    void setOperations(FindAllCrudOperationListener<T> findAllOperation, AddOperationListener<T> addOperation, UpdateOperationListener<T> updateOperation, MutilDeleteOperationListener<T> deleteOperation);

    void setCrudListener(MutilCrudListener<T> crudListener);

    CrudLayout getCrudLayout();

}
