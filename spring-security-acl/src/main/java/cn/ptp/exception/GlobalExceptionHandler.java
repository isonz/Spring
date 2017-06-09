package cn.ptp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = WebException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, WebException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData(req.getQueryString());
        r.setUrl(req.getRequestURL().toString());

        ModelAndView mav = new ModelAndView();
        mav.addObject("err", r);
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, JsonException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData(req.getQueryString());
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}

