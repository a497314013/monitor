package com.rockwell.ramon.ui.views.user;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

import com.querydsl.core.BooleanBuilder;
import com.rockwell.crudui.crud.CrudOperation;
import com.rockwell.crudui.crud.CrudOperationException;
import com.rockwell.crudui.crud.impl.MutilGridCrud;
import com.rockwell.crudui.crud.mutil.MutilCrudListener;
import com.rockwell.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.dao.UserDao;
import com.rockwell.ramon.entity.QUser;
import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.entity.User;
import com.rockwell.ramon.service.UserService;
import com.rockwell.ramon.ui.views.MainAppLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "users", layout = MainAppLayout.class)
@PageTitle("User")
public class MutilUserView extends VerticalLayout implements MutilCrudListener<User> {// or implements LazyCrudListener<User> 
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5679762113767953209L;

    private VerticalLayout container = new VerticalLayout();
    private TextField nameFilter = new TextField();
    private TextField lnameFilter = new TextField();
    private ComboBox<Role> roleFilter = new ComboBox<>();
    private UserDao userDao;
    private RoleDao roleDao;
    private UserService userService;
    
    
    public MutilUserView(RoleDao roleDao,UserService userService) {
    	this.userDao = userService.getUserDao();
    	this.roleDao = roleDao;
    	this.userService = userService;
    	
        container.setSizeFull();
        container.setMargin(false);
        container.setPadding(false);
        add(container);
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        
        container.add(getDefaultCrud());
    }
    
    private Component getDefaultCrud() 
    {
    	MutilGridCrud<User> crud = new MutilGridCrud<>(User.class, this);
    	nameFilter.setPlaceholder("filter by name...");
    	nameFilter.getElement().setProperty("labelName", "Name");
        nameFilter.addValueChangeListener(e -> crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);
       
        lnameFilter.setPlaceholder("filter by lname...");
        lnameFilter.getElement().setProperty("labelName", "LName");
        lnameFilter.addValueChangeListener(e -> crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(lnameFilter);

        roleFilter.setPlaceholder("Role");
        roleFilter.setItems(roleDao.findAllByOrderByRoleName());
        roleFilter.setItemLabelGenerator(Role::getRoleName);
        roleFilter.addValueChangeListener(e -> crud.refreshGrid());
        roleFilter.getElement().setProperty("labelName", "Role");
        crud.getCrudLayout().addFilterComponent(roleFilter);
        Button clearFilters = new Button("重置", VaadinIcon.ERASER.create());
        clearFilters.addClickListener(event -> {
            nameFilter.setValue("");
            lnameFilter.setValue("");
            roleFilter.clear();
        });

        Button queryButton = new Button("查询", VaadinIcon.SEARCH.create());
        queryButton.addClickListener(event -> crud.refreshGrid());
        queryButton.setIconAfterText(false);
        clearFilters.setIconAfterText(false);
        crud.getCrudLayout().addFilterButton(queryButton);
        crud.getCrudLayout().addFilterButton(clearFilters);
        
        Button modifyRoleButton = new Button("修改用户角色", VaadinIcon.GROUP.create());
        modifyRoleButton.setEnabled(false);
        queryButton.setIconAfterText(false);
        crud.getCrudLayout().addFilterButton(modifyRoleButton);
        modifyRoleButton.addClickListener(event->{
        	User user = crud.getFirstSelectedObj();
        	UserRoleView userRoleView = new UserRoleView(userDao,roleDao,user);
        	VerticalLayout dialogLayout = new VerticalLayout(userRoleView);
        	
            dialogLayout.setWidth("100%");
            dialogLayout.setMargin(false);
            dialogLayout.setPadding(false);

            Dialog dialog = new Dialog(new H3("修改用户角色"), dialogLayout);
            userRoleView.setCancelListener(cancelEvent -> dialog.close());
            userRoleView.setSaveListener(saveEvent->{
            	userRoleView.save();
            	Notification.show("save successfully!").setDuration(2000);
            	dialog.close();
            });
            dialog.setSizeFull();
            dialog.setWidth("650px");
            dialog.open();
        });
        
        DefaultCrudFormFactory<User> formFactory = new DefaultCrudFormFactory<>(User.class);
        crud.setCrudFormFactory(formFactory);
        
        formFactory.setUseBeanValidation(true);

        formFactory.setRequiredProperties(CrudOperation.ADD,"name","pwd");
        formFactory.setRequiredProperties(CrudOperation.UPDATE,"name","pwd");
        formFactory.setVisibleProperties("name","FName","LName","address","email","mobile","pwd","status");
	    formFactory.setVisibleProperties(CrudOperation.DELETE,"name","status", "FName","LName");
	    crud.getGrid().addSelectionListener(event->{
	    	if(crud.getFirstSelectedObj() == null)
	    	{
	    		modifyRoleButton.setEnabled(false);
	    	}
	    	else
	    	{
	    		modifyRoleButton.setEnabled(true);
	    	}
	    });
	    crud.getGrid().getColumns().forEach(crud.getGrid()::removeColumn);
	    crud.addGridRowIndex("Seq");
	    crud.getGrid().addColumn(User::getName,"name").setHeader("Name");
	    crud.getGrid().addColumn(User::getLName,"lName").setHeader("LName");
	    crud.getGrid().addColumn(User::getFName,"fName").setHeader("FName");
	    crud.getGrid().addColumn(User::getEmail,"email").setHeader("Email");
	    crud.getGrid().addColumn(User::getAddress,"address").setHeader("Address");
	    crud.getGrid().addColumn(User::getMobile,"mobile").setHeader("Mobile");
	    crud.getGrid().addColumn(User::getStatus,"status").setHeader("Status");
	    
	    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    crud.getGrid().addColumn(new TextRenderer<>(user ->	user.getCTime()==null ? "" : user.getCTime().format(pattern)))
	    .setHeader("CTime").setComparator((user1,user2)->{
	    	if(user1.getCTime() == null && user2.getCTime() != null)
	    	{
	    		return -1;
	    	}
	    	else if(user2.getCTime() == null && user1.getCTime() != null)
	    	{
	    		return 1;
	    	}
	    	else if(user2.getCTime() == null && user1.getCTime() == null)
	    	{
	    		return 0;
	    	}
	    	return user1.getCTime().compareTo(user2.getCTime());
    	});
	    
	    crud.getGrid().addColumn(new TextRenderer<>(user ->	user.getUTime()==null ? "" : user.getUTime().format(pattern)),"uTime")
	    .setHeader("UTime").setComparator((user1,user2)->{
	    	if(user1.getUTime() == null && user2.getUTime() != null)
	    	{
	    		return -1;
	    	}
	    	else if(user2.getUTime() == null && user1.getUTime() != null)
	    	{
	    		return 1;
	    	}
	    	else if(user2.getUTime() == null && user1.getUTime() == null)
	    	{
	    		return 0;
	    	}
	    	return user1.getUTime().compareTo(user2.getUTime());
    	});
	
	    crud.getGrid().setColumnReorderingAllowed(true);
	    
	    crud.getGrid().getColumns().forEach(column->column.setResizable(true));
	    
	    formFactory.setFieldType("pwd", PasswordField.class);
	    formFactory.setUseBeanValidation(CrudOperation.DELETE, false);
	    
        return crud;
    }

    
    
    @Override
    public User add(User user) 
    {
    	if(userDao.findByName(user.getName()) != null)
    	{
    		throw(new CrudOperationException("the user'name <"+user.getName()+"> has already exist in the system!"));
    	}
        return userService.save(user);
    }

    @Override
    public User update(User user) {
        return userService.save(user);
    }

    @Override
    public Collection<User> findAll()
    {
    	QUser qUser = QUser.user;
    	BooleanBuilder builder = new BooleanBuilder();
    	if(nameFilter.getValue() != null && !"".equals(nameFilter.getValue()))
    	{
    		builder.and(qUser.name.contains(nameFilter.getValue().trim()));
    	}
    	if(lnameFilter.getValue() != null && !"".equals(lnameFilter.getValue()))
    	{
    		builder.and(qUser.lName.contains(lnameFilter.getValue().trim()));
    	}
    	if(roleFilter.getValue() != null)
    	{
    		builder.and(qUser.roles.contains(roleFilter.getValue()));
    	}
    	
    	if(builder.getValue() == null)
    	{
    		return userDao.findAll();
    	}
    	Iterable<User> all = userDao.findAll(builder.getValue());
    	return (Collection<User>) all;
    }

	@Override
	public void delete(Collection<User> domainObjectToDelete)
	{
		userDao.deleteAll(domainObjectToDelete);
	}
}