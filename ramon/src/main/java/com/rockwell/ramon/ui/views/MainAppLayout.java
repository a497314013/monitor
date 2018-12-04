package com.rockwell.ramon.ui.views;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.rockwell.ramon.backend.cashe.MenuCashe;
import com.rockwell.ramon.constant.ViewRouter;
import com.rockwell.ramon.entity.Menu;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 9032475985510733973L;
	private static final String VAADINICON_SPLIT = "::";
	private static final VaadinIcon DEFAULT_ICON = VaadinIcon.QUESTION;
	private static final Class<? extends Component> DEFAULT_VIEW = DefaultPage.class;
	private Behaviour variant;

	private DefaultNotificationHolder notifications;

//	private DefaultBadgeHolder badge;

	@Override
	public com.github.appreciated.app.layout.behaviour.AppLayout getAppLayout()
	{
		if (variant == null)
		{
			variant = Behaviour.LEFT_HYBRID;
			notifications = new DefaultNotificationHolder(newStatus -> {
			});
//			badge = new DefaultBadgeHolder();
		}
		AppLayoutBuilder appLayoutBuilder = AppLayoutBuilder.get(variant)
						.withTitle("App Layout")
						.withAppBar(AppBarBuilder.get().add(new AppBarNotificationButton(VaadinIcon.BELL, notifications)).build());
		
		LeftAppMenuBuilder leftAppMenuBuilder = LeftAppMenuBuilder.get();
		leftAppMenuBuilder.addToSection(new MenuHeaderComponent("Monitor", "Version 1.0.1","/frontend/images/logo.png"),HEADER);
		initLeftAppMenuFromDB(leftAppMenuBuilder);
		/*leftAppMenuBuilder.addToSection(new TopClickableComponent("Set Behaviour",
            VaadinIcon.COG.create(),
            clickEvent -> openModeSelector(variant)), FOOTER);*/
		appLayoutBuilder.withAppMenu(leftAppMenuBuilder.build());
		return appLayoutBuilder.build();
	}

	/*private void setDrawerVariant(Behaviour variant)
	{
		this.variant = variant;
		reloadConfiguration();
	}

	private void openModeSelector(Behaviour variant)
	{
		new BehaviourSelector(variant, this::setDrawerVariant).open();
	}*/

	LinkedList<SubMenuWrapper> beforeMenuButtons = null;
	private void initLeftAppMenuFromDB(LeftAppMenuBuilder leftAppMenuBuilder)
	{
		beforeMenuButtons = new LinkedList<>();
		MenuCashe menuCashe = MenuCashe.getInstance();
		if(menuCashe.getLeftMenuCashe() == null || menuCashe.getLeftMenuCashe().size() <= 0)
		{
			return;
		}
		List<Menu> leftMenuCashe = menuCashe.getLeftMenuCashe();
		if(leftMenuCashe == null || leftMenuCashe.size() <= 0)
		{
			return;
		}
		
		for(int i = 0; i < leftMenuCashe.size(); i ++)
		{
			Menu menu = leftMenuCashe.get(i);
			VaadinIcon vaadinIcon = null;
			String iconURL = menu.getIcon();
			if(iconURL != null)
			{
				if(iconURL.contains(VAADINICON_SPLIT))
				{
					try
					{
						vaadinIcon = VaadinIcon.valueOf(iconURL.replace("VaadinIcon"+VAADINICON_SPLIT, ""));
					}
					catch(Exception e)
					{
						System.err.println(e.getMessage());
					}
				}
				//other icon url
				else
				{
				}
			}
			
			//属于一级菜单且没有子菜单
			//
			
			//当前的parentID为0且当前的id没被下一个菜单的parentId引用
			if(i < leftMenuCashe.size()-1 
				&& !menu.getId().equals(leftMenuCashe.get(i+1).getParentId()))
			{
				addMenuButton(leftAppMenuBuilder,menu,vaadinIcon,false);
			}
			//属于一级菜单但是有子菜单：
			//当前parentID为0且当前ID被下一个菜单引用
			else if(i < leftMenuCashe.size()-1 
				&& menu.getId().equals(leftMenuCashe.get(i+1).getParentId()))
			{
				addMenuButton(leftAppMenuBuilder,menu,vaadinIcon,true);
			}
			//最后一个菜单
			else
			{
				addMenuButton(leftAppMenuBuilder,menu,vaadinIcon,false);
			}
		}
		
		//4. if the beforeMenuButtons has value
		//build each sub menu among beforeMenuButtons
		while(beforeMenuButtons.size() > 0)
		{
			SubMenuWrapper subMenu = beforeMenuButtons.removeLast();
			SubMenuWrapper subMenuParent = getParentMenu(subMenu.getParentId());
			if(subMenuParent == null)
			{
				leftAppMenuBuilder.add(subMenu.getLeftSubMenuBuilder().build());
			}
			else
			{
				subMenuParent.getLeftSubMenuBuilder().add(subMenu.getLeftSubMenuBuilder().build());
			}
		}
	}
	
	private SubMenuWrapper getParentMenu(String parentId)
	{
		for(int i = beforeMenuButtons.size()-1; i >= 0; i--)
		{
			if(beforeMenuButtons.get(i).getId().equals(parentId))
			{
				return beforeMenuButtons.get(i);
			}
		}
		
		return null;
	}
	
	private void addMenuButton(LeftAppMenuBuilder leftAppMenuBuilder,Menu menu,VaadinIcon vaadinIcon,
		boolean isSub)
	{
		LeftSubMenuBuilder leftSubMenuBuilder = null;
		LeftNavigationComponent menuButton = null;
		if(isSub)
		{
			leftSubMenuBuilder = LeftSubMenuBuilder.get(menu.getName()
				,vaadinIcon != null ? vaadinIcon.create() : DEFAULT_ICON.create());
			
		}
		else 
		{
			Class<? extends Component> viewClass = ViewRouter.getViewClass(menu.getUrl()!=null?menu.getUrl().trim():null);
			menuButton = new LeftNavigationComponent(menu.getName()
				,vaadinIcon != null ? vaadinIcon.create() : DEFAULT_ICON.create()
				,viewClass != null ? viewClass : DEFAULT_VIEW);
		}
		
		//1.if menu isn't sub menu and the beforeMenuButtons.size <= 0
		if(menuButton != null && beforeMenuButtons.size() <= 0)
		{
			leftAppMenuBuilder.add(menuButton);
		}
		
		//2. if menu isn't sub menu and the beforeMenuButtons.size > 0
		if(menuButton != null && beforeMenuButtons.size() > 0)
		{
			////在beforeMenuButtons中找到parentMenu的parent :BP
			SubMenuWrapper parentMenu = getParentMenu(menu.getParentId());
			//if the beforeMenuButtons's last != menu's parent
			
			SubMenuWrapper lastSub = beforeMenuButtons.get(beforeMenuButtons.size()-1);//A
			if(parentMenu != lastSub)
			{
				//AP
				SubMenuWrapper _parentMenu = getParentMenu(lastSub.getParentId());
				//build the same level sub menu
				if(_parentMenu == null)
				{
					leftAppMenuBuilder.add(lastSub.getLeftSubMenuBuilder().build());
				}
				else
				{
					_parentMenu.getLeftSubMenuBuilder().add(lastSub.getLeftSubMenuBuilder().build());
				}
				beforeMenuButtons.removeLast();
//				beforeMenuButtons.pop();
				
				if(parentMenu != null)
				{
					parentMenu.getLeftSubMenuBuilder().add(menuButton);
				}
				else
				{
					leftAppMenuBuilder.add(menuButton);
				}
			}
			else
			{
				parentMenu.getLeftSubMenuBuilder().add(menuButton);
			}
		}
		
		//3. if menu is sub menu
		if(leftSubMenuBuilder != null)
		{
			beforeMenuButtons.addLast(new SubMenuWrapper(menu.getId(), menu.getParentId(),leftSubMenuBuilder));
			if(beforeMenuButtons.size() >= 2)
			{
				SubMenuWrapper currentParent = getParentMenu(menu.getParentId());
				SubMenuWrapper previousParent = beforeMenuButtons.get(beforeMenuButtons.size()-2);
				if(previousParent != currentParent)
				{
					//build the same level sub menu
					SubMenuWrapper _previousParent = getParentMenu(previousParent.getParentId());
					if(_previousParent == null)
					{
						leftAppMenuBuilder.add(previousParent.getLeftSubMenuBuilder().build());
					}
					else
					{
						_previousParent.getLeftSubMenuBuilder().add(previousParent.getLeftSubMenuBuilder().build());
					}
					
					beforeMenuButtons.remove(previousParent);
				}
			}
			
		}
	}
	
	class SubMenuWrapper implements Serializable
	{
		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 6877780801628989980L;

		private String id;

		private String parentId;

		private LeftSubMenuBuilder leftSubMenuBuilder;

		public String getId()
		{
			return id;
		}

		public String getParentId()
		{
			return parentId;
		}

		public LeftSubMenuBuilder getLeftSubMenuBuilder()
		{
			return leftSubMenuBuilder;
		}

		public SubMenuWrapper(String id, String parentId, LeftSubMenuBuilder leftSubMenuBuilder)
		{
			this.id = id;
			this.parentId = parentId;
			this.leftSubMenuBuilder = leftSubMenuBuilder;
		}
	}
	
	class BehaviourSelector extends Dialog
	{
		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 783979609171043658L;

		BehaviourSelector(Behaviour current, Consumer<Behaviour> consumer)
		{
			VerticalLayout layout = new VerticalLayout();
			add(layout);
			RadioButtonGroup<Behaviour> group = new RadioButtonGroup<>();
			group.getElement().getStyle().set(
				"display",
				"flex");
			group.getElement().getStyle().set(
				"flexDirection",
				"column");
			group.setItems(
				Behaviour.LEFT,
				Behaviour.LEFT_OVERLAY,
				Behaviour.LEFT_RESPONSIVE,
				Behaviour.LEFT_HYBRID,
				Behaviour.LEFT_HYBRID_SMALL,
				Behaviour.LEFT_RESPONSIVE_HYBRID,
				Behaviour.LEFT_RESPONSIVE_HYBRID_NO_APP_BAR,
				Behaviour.LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR,
				Behaviour.LEFT_RESPONSIVE_OVERLAY,
				Behaviour.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR,
				Behaviour.LEFT_RESPONSIVE_SMALL,
				Behaviour.LEFT_RESPONSIVE_SMALL_NO_APP_BAR);
			group.setValue(current);
			layout.add(group);
			group.addValueChangeListener(
				singleSelectionEvent -> {
					consumer.accept(
						singleSelectionEvent.getValue());
					close();
				});
		}
	}
}
