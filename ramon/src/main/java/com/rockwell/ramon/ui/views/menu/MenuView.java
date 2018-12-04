package com.rockwell.ramon.ui.views.menu;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import com.querydsl.core.BooleanBuilder;
import com.rockwell.crudui.component.OtherField;
import com.rockwell.crudui.crud.CrudListener;
import com.rockwell.crudui.crud.CrudOperation;
import com.rockwell.crudui.crud.impl.GridCrud;
import com.rockwell.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import com.rockwell.ramon.constant.SaveOperation;
import com.rockwell.ramon.dao.MenuDao;
import com.rockwell.ramon.entity.Menu;
import com.rockwell.ramon.entity.QMenu;
import com.rockwell.ramon.service.MenuService;
import com.rockwell.ramon.service.RoleService;
import com.rockwell.ramon.ui.entities.MenuType;
import com.rockwell.ramon.ui.entities.MenuTypeEnum;
import com.rockwell.ramon.ui.views.MainAppLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "menu", layout = MainAppLayout.class)
public class MenuView extends VerticalLayout implements CrudListener<Menu> { 
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -1232551427208242343L;
	private VerticalLayout container = new VerticalLayout();
    private TextField nameFilter = new TextField();
    private TextField urlFilter = new TextField();
    private TextField permsFilter = new TextField();
    private ComboBox<String> typeField = new ComboBox<>();
    private ComboBox<Menu> parentField = new ComboBox<>();
    private ComboBox<Menu> afterField = new ComboBox<>();
    private ComboBox<String> typeFilter = new ComboBox<>();
    private MenuType menuType = new MenuType();
    private MenuDao menuDao;
    private MenuService menuService;
    
    public MenuView(RoleService roleService,MenuService menuService) {
    	this.menuService = menuService;
    	this.menuDao = menuService.getMenuDao();
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
    	GridCrud<Menu> crud = new GridCrud<>(Menu.class, this);
    	nameFilter.setPlaceholder("filter by menu name...");
    	nameFilter.getElement().setProperty("labelName", "Name");
        nameFilter.addValueChangeListener(e -> crud.refreshGrid());
        crud.getCrudLayout().addFilterComponent(nameFilter);

        urlFilter.setPlaceholder("filter by url...");
        urlFilter.addValueChangeListener(e -> crud.refreshGrid());
        urlFilter.getElement().setProperty("labelName", "URL");
        crud.getCrudLayout().addFilterComponent(urlFilter);
        
        permsFilter.setPlaceholder("filter by perms...");
        permsFilter.addValueChangeListener(e -> crud.refreshGrid());
        permsFilter.getElement().setProperty("labelName", "Perms");
        crud.getCrudLayout().addFilterComponent(permsFilter);
        
        typeFilter.setPlaceholder("filter by type...");
        typeFilter.addValueChangeListener(e -> crud.refreshGrid());
        typeFilter.getElement().setProperty("labelName", "type");
        typeFilter.setItems(menuType.getValues());
        crud.getCrudLayout().addFilterComponent(typeFilter);

        Button queryButton = new Button("查询", VaadinIcon.SEARCH.create());
        queryButton.addClickListener(event -> crud.refreshGrid());
        queryButton.setIconAfterText(false);
        crud.getCrudLayout().addFilterButton(queryButton);
        
        Button clearFilters = new Button("重置", VaadinIcon.ERASER.create());
        clearFilters.setIconAfterText(false);
        crud.getCrudLayout().addFilterButton(clearFilters);
        clearFilters.addClickListener(event -> {
            nameFilter.setValue("");
            urlFilter.setValue("");
            permsFilter.setValue("");
            typeFilter.setValue(null);
        });
        
        DefaultCrudFormFactory<Menu> formFactory = new DefaultCrudFormFactory<>(Menu.class);
        crud.setCrudFormFactory(formFactory);
        
        formFactory.setUseBeanValidation(true);

	    formFactory.setVisibleProperties("name","url","icon","perms");

    	typeField.setItems(menuType.getValues());
    	typeField.setLabel("Type");
    	
    	OtherField _typeFiled = new OtherField();
    	_typeFiled.setField(typeField);
    	typeField.setErrorMessage("Must select a type!");
    	typeField.setRequired(true);
    	typeField.setRequiredIndicatorVisible(true);
    	_typeFiled.setValidor(() -> {
    		return menuType.getValues().contains(typeField.getValue());
    		});
    	
	    formFactory.addOtherFields(CrudOperation.ADD, _typeFiled);
	    formFactory.addOtherFields(CrudOperation.UPDATE, _typeFiled);
	    
	    parentField.setLabel("Parent");
	    parentField.setItemLabelGenerator(Menu::getName);
	    formFactory.addOtherFields(CrudOperation.ADD, parentField);
	    formFactory.addOtherFields(CrudOperation.UPDATE, parentField);
	    
	    afterField.setLabel("After Menu");
	    afterField.setItemLabelGenerator(Menu::getName);
	    formFactory.addOtherFields(CrudOperation.ADD, afterField);
	    formFactory.addOtherFields(CrudOperation.UPDATE, afterField);
	    
	    typeField.addValueChangeListener(e->{
    		String type = typeField.getValue();
    		if(type != null && !menuType.getKey(1).equals(type))
    		{
    			parentField.setEnabled(false);
    			afterField.setEnabled(false);
    		}
    		else
    		{
    			parentField.setEnabled(true);
    			afterField.setEnabled(true);
    		}
    	});
	    
	    formFactory.setAfterAutoCreateFormEvents(CrudOperation.ADD,(form,domain) -> {
	    	if(typeField.getValue() != null)
	    	{
	    		typeField.setValue(null);
	    	}
	    	typeField.setValue(menuType.getKey(0));
	    	afterField.setValue(null);
	    	parentField.setValue(null);
	    	List<Menu> menus = menuDao.findAllByTypeOrderBySequence(MenuTypeEnum.菜单.ordinal());
	    	parentField.setItems(menus);
	    	afterField.setItems(menus);
	    });
	    
	    formFactory.setAfterAutoCreateFormEvents(CrudOperation.UPDATE, (form,domain) -> {
    		String type = typeField.getValue();
    		if(type != null && !menuType.getKey(1).equals(type))
    		{
    			afterField.setEnabled(false);
    			parentField.setEnabled(false);
    		}
    		else
    		{
    			afterField.setEnabled(true);
    			parentField.setEnabled(true);
    		}
    		List<Menu> menus = menuDao.findAllByTypeOrderBySequence(MenuTypeEnum.菜单.ordinal());
    		typeField.setValue(null);
    		typeField.setValue(menuType.getKey(domain.getType()));
    		parentField.setItems(menus);
    		afterField.setItems(menus);
    		if(domain.getParentId() != null)
    		{
    			parentField.setValue(menuDao.findById(domain.getParentId()).orElse(null));
    		}
    		List<Menu> filterMenus = menuDao.findBySequenceOrderByCtime(new Integer(domain.getSequence()-1));
    		afterField.setValue(filterMenus!=null&&filterMenus.size()>0?filterMenus.get(0):null);
    	});
	    
	    formFactory.setDisabledProperties(CrudOperation.UPDATE,"name");
	    formFactory.setRequiredProperties(CrudOperation.ADD,"name");
	    formFactory.setVisibleProperties(CrudOperation.DELETE, "id","parentId", "name","url");
	    
	    String manualColumns = "id,icon,parentId,type,ctime,utime";
	    crud.getGrid().getColumns().stream().filter(column->manualColumns.contains(column.getKey())).forEach(crud.getGrid()::removeColumn);
	    crud.getGrid().addColumn(menu->menuType.getKey(menu.getType()),"type").setHeader("type");
	    crud.getGrid().addColumn(Menu::getIcon,"icon").setHeader("Icon");
	    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    crud.getGrid().addColumn(new TextRenderer<>(menu ->	menu.getCtime()==null ? "" : menu.getCtime().format(pattern)))
	    .setHeader("CTime").setComparator((menu1,menu2)->{
	    	if(menu1.getCtime() == null && menu2.getCtime() != null)
	    	{
	    		return -1;
	    	}
	    	else if(menu2.getCtime() == null && menu1.getCtime() != null)
	    	{
	    		return 1;
	    	}
	    	else if(menu2.getCtime() == null && menu1.getCtime() == null)
	    	{
	    		return 0;
	    	}
	    	return menu1.getCtime().compareTo(menu2.getCtime());
    	});
	    
	    crud.getGrid().addColumn(new TextRenderer<>(menu ->	menu.getUtime()==null ? "" : menu.getUtime().format(pattern)),"utime")
	    .setHeader("UTime").setComparator((menu1,menu2)->{
	    	if(menu1.getUtime() == null && menu2.getUtime() != null)
	    	{
	    		return -1;
	    	}
	    	else if(menu2.getUtime() == null && menu1.getUtime() != null)
	    	{
	    		return 1;
	    	}
	    	else if(menu2.getUtime() == null && menu1.getUtime() == null)
	    	{
	    		return 0;
	    	}
	    	return menu1.getUtime().compareTo(menu2.getUtime());
    	});
	
	    crud.getGrid().setColumnReorderingAllowed(true);
	    crud.getGrid().getColumns().forEach(column->{
	    	column.setResizable(true);
	    });
	    
	    formFactory.setUseBeanValidation(CrudOperation.DELETE, false);
	    
        return crud;
    }
    
    @Override
    public Menu add(Menu menu)
    {
        return save(menu,SaveOperation.ADD);
    }

    private Menu save(Menu menu,SaveOperation operation)
    {
    	menu.setType(menuType.getValue(typeField.getValue()));
    	menu.setParentId(parentField.getValue()==null?null:parentField.getValue().getId());
    	menu.setSequence(afterField.getValue()==null?0:afterField.getValue().getSequence()+1);
    	return menuService.save(menu,operation);
    }
    
    @Override
    public Menu update(Menu menu)
    {
    	return save(menu,SaveOperation.UPDATE);
    }

    @Override
    public void delete(Menu menu) 
    {
    	menuService.delete(menu);
    }

    @Override
    public Collection<Menu> findAll()
    {
    	QMenu qMenu = QMenu.menu;
    	BooleanBuilder builder = new BooleanBuilder();
    	if(nameFilter.getValue() != null && !"".equals(nameFilter.getValue()))
    	{
    		builder.and(qMenu.name.contains(nameFilter.getValue().trim()));
    	}
    	if(urlFilter.getValue() != null && !"".equals(urlFilter.getValue()))
    	{
    		builder.and(qMenu.url.contains(urlFilter.getValue()));
    	}
    	if(permsFilter.getValue() != null && !"".equals(permsFilter.getValue()))
    	{
    		builder.and(qMenu.perms.contains(permsFilter.getValue()));
    	}
    	if(typeFilter.getValue() != null)
    	{
    		builder.and(qMenu.type.eq(menuType.getValue(typeFilter.getValue())));
    	}
    	
    	if(builder.getValue() == null)
    	{
    		return menuDao.findAll();
    	}
    	Iterable<Menu> all = menuDao.findAll(builder.getValue());
    	return (Collection<Menu>) all;
    }
}