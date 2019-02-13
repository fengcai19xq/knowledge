package com.sky.knowledge.module.framework.server.web.interceptor;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sky.knowledge.module.framework.server.web.result.json.annotation.JSON;
import com.sky.knowledge.module.framework.server.web.validate.Validation;
import com.sky.knowledge.module.framework.server.web.validate.ValidationException;
import com.sky.knowledge.module.framework.server.web.validate.Validator;
import com.sky.knowledge.module.framework.server.web.validate.annotation.Validatable;
import com.sky.knowledge.module.framework.shared.define.Protocol;
/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:数据校验拦截器</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-3-8 周丹枫 新增
* </div>  
********************************************
 */
public class ValidationInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = 3266788864618662789L;

    private Log logger = LogFactory.getLog(ValidationInterceptor.class);
    
    private final static String INPUT = "jspError";
    
    private final static String JSONINPUT = "jsonError";

    /**
     * 完成对Action中属性的数据校验
     * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
     * doIntercept
     * @param invocation
     * @return
     * @throws Exception
     * @since:
     */
    @Override
    public String doIntercept(ActionInvocation invocation) throws Exception {
        Map<String, List<String>> validationError = new HashMap<String, List<String>>();
        if (logger.isDebugEnabled()) {
            logger.info("ValidationInterceptor start");
        }
        Object action = invocation.getAction();
        ServletContext sc = ServletActionContext.getServletContext();

        Class<?> clazz = action.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        boolean flag = false;
        for (PropertyDescriptor pd : pds) {
            Method writeMethod = pd.getWriteMethod();
            Method readMethod = pd.getReadMethod();
            if (writeMethod == null || readMethod == null)
            	continue;
            Annotation[] annotations = writeMethod.getAnnotations();
            if (annotations == null || annotations.length == 0) {
                continue;
            }
            Object[] objs = null;
            Object property = readMethod.invoke(action, objs);
            List<String> errorList = new ArrayList<String>();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getAnnotation(Validatable.class) == null) {
                    continue;
                }
                String str = annotation.annotationType().getName();
                String simpleName = str.substring(str.lastIndexOf('.'), str.length());
                str = str.replace("annotation.", "");
                String className = str.substring(0, str.lastIndexOf('.')) + simpleName + "Validator";
                Class<?> c = Class.forName(className);
                Validator validator = Validation.getValidation().get(simpleName + "Validator");
                if (null == validator) {
                    validator = (Validator) c.newInstance();
                    validator.initMessageSource(WebApplicationContextUtils.getRequiredWebApplicationContext(sc));
                    Validation.getValidation().put(simpleName + "Validator", validator);
                }
                try {
                    validator.validate(annotation, property);
                } catch (ValidationException e) {
                    errorList.add(e.getMessage());
                }
            }
            if (errorList.size() != 0) {
                validationError.put(pd.getName() + "Error", errorList);
                flag = true;
            }
        }

        if (flag) {
            return doResult(validationError,invocation);
        }
        return invocation.invoke();

    }

    /*
     * 把数据校验异常信息进行处理，如果要返回JSON的格式，就要返回jsonError字符串
     *                           如果要返回JSP的页面，就要返回jspError字符串
     */
    private String doResult(Map<String, List<String>> validationError,ActionInvocation invocation) throws IOException {
        HttpServletRequest req = ServletActionContext.getRequest();
        String contentType = req.getHeader("content-type");
        if (contentType != null) {
            int iSemicolonIdx;
            if ((iSemicolonIdx = contentType.indexOf(";")) != -1)
                contentType = contentType.substring(0, iSemicolonIdx);
        }
        Class<?> clazz = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(clazz, methodName);
        if (Protocol.JSON_CONTENT_TYPE.equals(contentType) || AnnotationUtils.isAnnotationDeclaredLocally(JSON.class, clazz)
        		|| (method != null && method.isAnnotationPresent(JSON.class))) {
        	Map<String, Object> errors = new HashMap<String, Object>();
        	errors.putAll(validationError);
        	errors.put("success", false);
        	errors.put("isException", true);
            req.setAttribute("ERROR", errors);
            return JSONINPUT;

        } else {
            for (Map.Entry<String, List<String>> entry : validationError.entrySet()) {
                String errorMessage = entry.getValue().toString();
            	req.setAttribute(entry.getKey(), errorMessage.substring(1, errorMessage.length() - 1));
            }
            String referer = req.getHeader("Referer");
            req.setAttribute("referer", referer);
        }
        return INPUT;
    }



}