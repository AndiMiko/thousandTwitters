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

import static java.awt.SystemColor.info;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * Returns information about the given exception that may be printed by the view.
     * Catches all exceptions, but rethrows the exception if another exceptionHandler for
     * given exception does exists.
     *
     * @throws Exception rethrows the exception only if another more specific exceptionhandler will catch it.
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Map<String, Object> defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;
        return constructExceptionInfo(req, e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundDataAccessException.class})
    @ResponseBody
    public Map<String, Object> userNotFound(HttpServletRequest req, UserNotFoundDataAccessException e) {
        Map<String, Object> info = constructExceptionInfo(req, e);
        info.put("nestedException", e.getNestedException().getClass().getSimpleName());
        info.put("nestedExceptionMessage", e.getNestedException().getLocalizedMessage());
        return info;
    }

    private Map<String, Object> constructExceptionInfo(HttpServletRequest req, Exception e) {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("exception", e.getClass().getSimpleName());
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
