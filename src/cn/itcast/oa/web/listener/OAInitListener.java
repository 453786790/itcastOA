package cn.itcast.oa.web.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.IPrivilegeService;
//项目 启动加载 项目菜单 数据
public class OAInitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("-----------------OAListenerStart加载菜单数据到application-------------------");
		//查询顶级菜单(从spring中获取service) 
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
		IPrivilegeService privilegeService=(IPrivilegeService)ctx.getBean("privilegeServiceImpl");
		
		List<Privilege> topList = privilegeService.findTopList();
		sc.getServletContext().setAttribute("topPrivilegeList", topList);
		
		//查询所有 权限 对应的url 
		List<String> allUrl=privilegeService.findAllUrl();	
		sc.getServletContext().setAttribute("allUrl", allUrl);
		
	}
	
	
	public void contextDestroyed(ServletContextEvent arg0) {}


}
