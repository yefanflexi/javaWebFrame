package com.flexi.javaWebFrame;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;

import com.flexi.javaWebFrame.bean.Data;
import com.flexi.javaWebFrame.bean.Handler;
import com.flexi.javaWebFrame.bean.Param;
import com.flexi.javaWebFrame.bean.View;
import com.flexi.javaWebFrame.utils.BeanHelper;
import com.flexi.javaWebFrame.utils.CodecUtil;
import com.flexi.javaWebFrame.utils.ConfigHelper;
import com.flexi.javaWebFrame.utils.ControllerHelper;
import com.flexi.javaWebFrame.utils.JsonUtil;
import com.flexi.javaWebFrame.utils.ReflectionUtil;
import com.flexi.javaWebFrame.utils.StreamUtil;
import com.flexi.javaWebFrame.utils.StringUtils;

/**
 * 
 * 请求转发器
 * @author vixtel
 *
 */
@WebServlet(urlPatterns = "/*",loadOnStartup=0)
public class DispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -907384650757836308L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求方法 路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		//获取action 处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler != null) {
			//获取controller类及其bean实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllrtBean = BeanHelper.getBean(controllerClass);
			//创建请求参数
			Map<String,Object> paramMap = new HashMap<String,Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtils.isNotEmpty(body)) {
				String[] params = org.apache.commons.lang3.StringUtils.split(body, "&");
				if(ArrayUtils.isNotEmpty(params)) {
					for (String param : params) {
						String[] array = org.apache.commons.lang3.StringUtils.split(param,"=");
						if(ArrayUtils.isNotEmpty(array)&&array.length == 2) {
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			//调用action方法
			Method methodAction = handler.getActionMethod();
			Object result =  ReflectionUtil.invokeMethod(controllrtBean, methodAction, param);
			//处理action返回值
			if(result instanceof View) {
				//返回jsp页面
				View view = (View)result;
				String path = view.getPath();
				if(StringUtils.isNotEmpty(path)) {
					if(path.startsWith("/")) {
						response.sendRedirect(request.getContextPath()+path);
					}else {
						Map<String,Object> model = view.getModel();
						for(Map.Entry<String, Object>entry:model.entrySet()) {
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
					}
				}
			}else if(result instanceof Data) {
				//返回json数据
				Data data = (Data) result;
				Object model = data.getModel();
				if(model != null) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
					
				}
			}
		}
		
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		//初始化helper类
		HelperLoader.init();
		//获取Servletcontext对象
		ServletContext servletContext = servletConfig.getServletContext();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		//注册处理静态资源默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
	}

	
}
