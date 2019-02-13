package com.sky.knowledge.module.framework.shared.exception;


/**
 * @Classname:RemoteGeneralException
 * @Description:远程服务访问通用异常类
 * @author sifer chuanguo.xie@achievo.com
 * @date 2011-4-2 下午02:13:17
 * 
 */
final public class GeneralServiceException extends GeneralException {
    
    private static final long serialVersionUID = 7317828017595658980L;
    
    public GeneralServiceException(String msg, String errorCode, Object... args) {
        super(msg);
        this.errCode = errorCode;
        this.setErrorArguments(args);
    }
    
}
