package cn.ptp.annotation;

import cn.ptp.exception.JsonException;

public class AclJsonException extends JsonException
{
    public AclJsonException() {
        super("权限不足");
    }

    public AclJsonException(String message) {
        super(message);
    }
}
