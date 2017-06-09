package cn.ptp.annotation;

import cn.ptp.exception.WebException;

public class AclWebException extends WebException
{
    public AclWebException() {
        super("权限不足");
    }

    public AclWebException(String message) {
        super(message);
    }
}


