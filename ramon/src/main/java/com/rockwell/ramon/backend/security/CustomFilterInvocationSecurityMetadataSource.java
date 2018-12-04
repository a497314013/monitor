package com.rockwell.ramon.backend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.service.MenuService;

@Component("securityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource
{
    private Map<String, List<ConfigAttribute>> mp;
    
    @Autowired
    private MenuService menuService;
    

    /**
      * 构造每一种资源所需要的角色权限
     */
    public CustomFilterInvocationSecurityMetadataSource()
    {
        this.mp = new HashMap<String, List<ConfigAttribute>>();
        List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
        List<ConfigAttribute> list2 = new ArrayList<ConfigAttribute>();
        ConfigAttribute cb = new SecurityConfig("admin"); // 构造一个权限(角色)
        ConfigAttribute cbUser = new SecurityConfig("user"); // 构造一个权限(角色)
        ConfigAttribute cbManager = new SecurityConfig("root"); // 构造一个权限(角色)
        list.add(cb);
        list.add(cbUser);
        list2.add(cbManager);
        
        mp.put("/group", list);
        mp.put("/settings", list2);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    /**
     * 获取访问某一个url所需的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object arg0)
            throws IllegalArgumentException
    {
    	FilterInvocation filterInvocation = (FilterInvocation) arg0;
    	String requestUrl = filterInvocation.getRequestUrl();
    	if(requestUrl.startsWith("/"))
    	{
    		requestUrl = requestUrl.replaceFirst("/", "");
    	}
    	List<Role> roles = menuService.findRolesByMenuUrl(requestUrl);
    	List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
    	if(roles != null && roles.size() > 0)
    	{
    		roles.forEach(role -> {
    			result.add(new SecurityConfig(role.getRoleName()));
    		});
    	}
		// object 是一个URL，被用户请求的url。
		/*System.out.println(filterInvocation.getHttpRequest().getRequestURI());
		Iterator<String> ite = mp.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			 RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			    if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
				return mp.get(resURL);
			}
		}*/
 
		return result;
    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }

}