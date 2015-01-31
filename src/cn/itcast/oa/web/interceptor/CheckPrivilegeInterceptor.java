package cn.itcast.oa.web.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
//或者实现interceptor方法
public class CheckPrivilegeInterceptor extends AbstractInterceptor {
	

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("------------CheckPrivilegeInterceptor------------");
		String namespace = ai.getProxy().getNamespace();
		String actionName = ai.getProxy().getActionName();
		String url=namespace+actionName;//   /user_login  处理addUI  ..UI
		
		if(url.endsWith("UI")){
			url=url.substring(0,url.length()-2);
		}
		
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		//如果没登录
		if(user==null){
			//登录功能，放行
			if("/user_login".equals(url)){
				return ai.invoke();
			}else{
				return "loginUI";
			}
			
		}else{
			List<String> allUrl=(List<String>)ServletActionContext.getServletContext().getAttribute("allUrl");
			if(!allUrl.contains(url)){
				return ai.invoke();
			}
			
			//已经登录，则进行权限 验证
			//如果 用户有权限 
			boolean b=user.hasPrivilegeUrl(url);
			if(b){
				return ai.invoke();
			}else{
				//如果用户没有权限 ,则跳转到没有权限 的提示页面
				return "noPrivilegeUI";
			}

		}
	}
}
