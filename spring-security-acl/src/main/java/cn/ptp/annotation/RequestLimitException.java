package cn.ptp.annotation;

public class RequestLimitException extends Exception
{
    private static final long serialVersionUID = 1364225358754655602L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
