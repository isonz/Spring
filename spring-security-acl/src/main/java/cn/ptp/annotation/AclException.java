package cn.ptp.annotation;

public class AclException extends Exception
{
    private static final long serialVersionUID = 1364225358754655602L;

    public AclException() {
        super("权限不足");
    }

    public AclException(String message) {
        super(message);
    }
}
