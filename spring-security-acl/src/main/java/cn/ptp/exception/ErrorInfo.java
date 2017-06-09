package cn.ptp.exception;

public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100000;

    private Integer code = 100001;
    private String message = "Unknown error";
    private String url = "";
    private T data;


    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }
    
}