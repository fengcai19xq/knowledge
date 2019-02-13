package com.sky.knowledge.module.framework.server.session;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class Session<V> implements ISession<V> {

	 
    private javax.servlet.http.HttpSession session;
    
    /**
     * 
     * @see com.deppon.foss.framework.server.web.session.ISession#setObject(java.lang.String, java.lang.Object)
     * setObject
     * @param k
     * @param v
     * @since:0.6
     */
    @Override
    public void setObject(String k, V v) {
    	//先验证设置的值是否被允许
        validateSessionValue(v);
        session.setAttribute(k, v);
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.server.web.session.ISession#getObject(java.lang.String)
     * getObject
     * @param k
     * @return
     * @since:0.6
     */
    @SuppressWarnings("unchecked")
    @Override
    public V getObject(String k) {
        Object value = null;
        value = session.getAttribute(k);
        return (V) value;
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.server.web.session.ISession#init(javax.servlet.http.HttpSession)
     * init
     * @param session
     * @since:0.6
     */
    @Override
    public void init(HttpSession session) {
        this.session = session;
    }
    
    /**
     * 验证设置属性是否合法
     * validateSessionValue
     * @param v
     * @return void
     * @since:0.6
     */
    @SuppressWarnings("unchecked")
    void validateSessionValue(V v) {
        if (null == v)
            return;
        if (java.lang.String.class == v.getClass())
            return;
        if (java.lang.Long.class == v.getClass())
            return;
        if (java.lang.Integer.class == v.getClass())
            return;
        if (java.util.Date.class == v.getClass())
            return;
        if(java.util.Locale.class == v.getClass()){
        	return;
        }
        //SessionValue注解标注的放过
        if (v.getClass().isAnnotationPresent(SessionValue.class))
            return;
        //对于Collection及Map的遍历里面所有元素进行判断
        if (Collection.class.isAssignableFrom(v.getClass())) {
            Collection<?> collect = (Collection<?>) v;
            for (Object t : collect) {
                validateSessionValue((V) t);
            }
        }
        if (Map.class.isAssignableFrom(v.getClass())) {
            Map<?, ?> map = (Map<?, ?>) v;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                validateSessionValue((V) entry.getValue());
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 
     * @see com.deppon.foss.framework.server.web.session.ISession#invalidate()
     * invalidate
     * @since:0.6
     */
	@Override
	public void invalidate() {
		session.invalidate();
		
	}
}
