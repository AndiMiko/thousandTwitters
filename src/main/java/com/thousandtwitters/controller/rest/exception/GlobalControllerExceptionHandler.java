package com.thousandtwitters.controller.rest.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;
        LinkedHashMap<String, Object> info = new LinkedHashMap<>();
        info.put("exception", e.getClass().getSimpleName());
        info.put("exceptionMessage", e.getLocalizedMessage());
        info.put("url", req.getRequestURL().toString() + "?" + req.getQueryString());
        info.put("parameters", req.getParameterMap());
        return info;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundDataAccessException.class})
    @ResponseBody
    public Map<String, Object> unexpectedResult(HttpServletRequest req, UserNotFoundDataAccessException e) {
        LinkedHashMap<String, Object> info = new LinkedHashMap<>();
        info.put("exception", e.getClass().getSimpleName());
        info.put("exceptionMessage", e.getLocalizedMessage());
        info.put("nestedException", e.getNestedException().getClass().getSimpleName());
        info.put("nestedExceptionMessage", e.getNestedException().getLocalizedMessage());
        info.put("exceptionMessage", e.getLocalizedMessage());
        info.put("url", req.getRequestURL().toString() + "?" + req.getQueryString());
        info.put("parameters", req.getParameterMap());
        return info;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidDAOParameterException.class})
    @ResponseBody
    public String invalidDAOParameter(InvalidDAOParameterException e) {
        return e.getLocalizedMessage();
    }

}
