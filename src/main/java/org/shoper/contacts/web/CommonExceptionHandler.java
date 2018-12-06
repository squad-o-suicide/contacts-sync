package org.shoper.contacts.web;

import org.shoper.commons.responseentity.BaseResponse;
import org.shoper.commons.responseentity.ResponseBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 如果不在这里加@ResponseBody,则需要在类上加
     * @param e 异常
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public BaseResponse handle(Exception e) {
        return ResponseBuilder.custom().failed(e.getMessage(), 1).build();
    }
}