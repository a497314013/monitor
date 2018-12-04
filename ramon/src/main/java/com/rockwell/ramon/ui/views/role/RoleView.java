package com.rockwell.ramon.ui.views.role;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import com.querydsl.core.BooleanBuilder;
import com.rockwell.crudui.crud.CrudListener;
import com.rockwell.crudui.crud.CrudOperation;
import com.rockwell.crudui.crud.CrudOperationException;
import com.rockwell.crudui.crud.impl.GridCrud;
import com.rockwell.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import com.rockwell.ramon.dao.MenuDao;
import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.entity.Menu;
import com.rockwell.ramon.entity.QRole;
import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.service.RoleService;
import com.rockwell.ramon.ui.views.MainAppLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "role", layout = MainAppLayout.class)
public class RoleView extends VerticalLayout implements CrudListener<Role> { 

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3739330097897249619L;
	private VerticalLayout container = new VerticalLayout();
    private TextField nameFilter = new TextField();
    private ComboBox<Menu> menuFilter = new ComboBox<>();
    private RoleDao roleDao;
    private MenuDao menuDao;
    private RoleService roleService;
    
    
    public RoleView(RoleService roleService,MenuDao menuDao) {
    	this.roleService = roleService;
    	this.roleDao = roleService.getRoleDao();
    	this.menuDao = menuDao;
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
    	GridCrud<Role> crud = new GridCrud<>(Role.class, this);
    	nameFilter.setPlaceholder("filter by role name...");
    	nameFilter.getElement().setProperty("labelName", "Name");
        nameFilter.addValueChangeListener(e -> crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);

        menuFilter.setPlaceholder("filter by menu");
        menuFilter.setItems(menuDao.findAllByOrderByNameAndUrl());
        menuFilter.setItemLabelGenerator(menu->{
        	String menuName = menu.getName();
        	if(menu.getUrl() != null && !"".equals(menu.getUrl()))
        	{
        		menuName += " </"+menu.getUrl()+">";
        	}
        	return menuName;
        });
        
        menuFilter.addValueChangeListener(e -> crud.refreshGrid());
        menuFilter.getElement().setProperty("labelName", "Role");
        crud.getCrudLayout().addFilterComponent(menuFilter);
        
        Button queryButton = new Button("查询", VaadinIcon.SEARCH.create());
        queryButton.addClickListener(event -> crud.refreshGrid());
        queryButton.setIconAfterText(false);
        crud.getCrudLayout().addFilterButton(queryButton);
        
        Button clearFilters = new Button("重置", VaadinIcon.ERASER.create());
        clearFilters.setIconAfterText(false);
        crud.getCrudLayout().addFilterButton(clearFilters);
        clearFilters.addClickListener(event -> {
            nameFilter.setValue("");
            menuFilter.setValue(null);
        });
        
        Button modifyMenuButton = new Button("修改角色菜单", VaadinIcon.FORM.create());
        modifyMenuButton.setEnabled(false);
        modifyMenuButton.setIconAfterText(false);
        
        modifyMenuButton.addClickListener(event->{
        	Role role = crud.getGrid().asSingleSelect().getValue();
        	RoleMenuView roleMenuView = new RoleMenuView(roleDao,menuDao,role);
        	VerticalLayout dialogLayout = new VerticalLayout(roleMenuView);
        	
            dialogLayout.setWidth("100%");
            dialogLayout.setMargin(false);
            dialogLayout.setPadding(false);

            Dialog dialog = new Dialog(new H3("修改用户角色"), dialogLayout);
            roleMenuView.setCancelListener(cancelEvent -> dialog.close());
            roleMenuView.setSaveListener(saveEvent->{
            	roleMenuView.save();
            	Notification.show("save successfully!").setDuration(2000);
            	dialog.close();
            });
            dialog.setSizeFull();
            dialog.setWidth("650px");
            dialog.open();
        });
        
        crud.getCrudLayout().addFilterButton(modifyMenuButton);
        
        DefaultCrudFormFactory<Role> formFactory = new DefaultCrudFormFactory<>(Role.class);
        crud.setCrudFormFactory(formFactory);
        
        formFactory.setUseBeanValidation(true);

        /*formFactory.setErrorListener(e -> {
            Notification.show(e.getLocalizedMessage());
        });*/

        formFactory.setRequiredProperties(CrudOperation.ADD,"roleName","roleSign");
        formFactory.setRequiredProperties(CrudOperation.UPDATE,"roleName","roleSign");
	    formFactory.setVisibleProperties("roleName","roleSign");
	    formFactory.setDisabledProperties(CrudOperation.UPDATE, "roleName");
	    formFactory.setVisibleProperties(CrudOperation.DELETE, "id", "roleName");
	    crud.getGrid().addSelectionListener(event->{
	    	if(crud.getGrid().asSingleSelect().getValue() == null)
	    	{
	    		modifyMenuButton.setEnabled(false);
	    	}
	    	else
	    	{
	    		modifyMenuButton.setEnabled(true);
	    	}
	    });
	    crud.getGrid().getColumns().forEach(crud.getGrid()::removeColumn);
	    
	    crud.getGrid().addColumn(Role::getRoleName,"roleName").setHeader("Name");
	    crud.getGrid().addColumn(Role::getRoleSign,"roleSign").setHeader("Sign");
	    
	    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    crud.getGrid().addColumn(new TextRenderer<>(role ->	role.getCTime()==null ? "" : role.getCTime().format(pattern)))
	    .setHeader("CTime").setComparator((role1,role2)->{
	    	if(role1.getCTime() == null && role2.getCTime() != null)
	    	{
	    		return -1;
	    	}
	    	else if(role2.getCTime() == null && role1.getCTime() != null)
	    	{
	    		return 1;
	    	}
	    	else if(role2.getCTime() == null && role1.getCTime() == null)
	    	{
	    		return 0;
	    	}
	    	return role1.getCTime().compareTo(role2.getCTime());
    	});
	    
	    crud.getGrid().addColumn(new TextRenderer<>(role ->	role.getUTime()==null ? "" : role.getUTime().format(pattern)),"uTime")
	    .setHeader("UTime").setComparator((role1,role2)->{
	    	if(role1.getUTime() == null && role2.getUTime() != null)
	    	{
	    		return -1;
	    	}
	    	else if(role2.getUTime() == null && role1.getUTime() != null)
	    	{
	    		return 1;
	    	}
	    	else if(role2.getUTime() == null && role1.getUTime() == null)
	    	{
	    		return 0;
	    	}
	    	return role1.getUTime().compareTo(role2.getUTime());
    	});
	
	    crud.getGrid().setColumnReorderingAllowed(true);
	    crud.getGrid().getColumns().forEach(column->column.setResizable(true));
	    formFactory.setUseBeanValidation(CrudOperation.DELETE, false);
	    
        return crud;
    }

    
    
    @Override
    public Role add(Role role) 
    {
    	if(roleDao.findByRoleName(role.getRoleName()) != null)
    	{
    		throw(new CrudOperationException("the role'name <"+role.getRoleName()+"> has already exist in the system!"));
    	}
    	role.setCTime(LocalDateTime.now());
    	role.setUTime(LocalDateTime.now());
        return roleDao.save(role);
    }

    @Override
    public Role update(Role role) 
    {
    	role.setUTime(LocalDateTime.now());
        return roleDao.save(role);
    }

    @Override
    public void delete(Role role) 
    {
    	roleService.delete(role);
    }

    @Override
    public Collection<Role> findAll()
    {
    	QRole qRole = QRole.role;
    	BooleanBuilder builder = new BooleanBuilder();
    	if(nameFilter.getValue() != null && !"".equals(nameFilter.getValue()))
    	{
    		builder.and(qRole.roleName.contains(nameFilter.getValue().trim()));
    	}
    	if(menuFilter.getValue() != null)
    	{
    		builder.and(qRole.menus.contains(menuFilter.getValue()));
    	}
    	
    	if(builder.getValue() == null)
    	{
    		return roleDao.findAll();
    	}
    	Iterable<Role> all = roleDao.findAll(builder.getValue());
    	return (Collection<Role>) all;
    }
}