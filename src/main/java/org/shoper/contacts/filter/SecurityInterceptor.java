package org.shoper.contacts.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.shoper.commons.responseentity.BaseResponse;
import org.shoper.commons.responseentity.ResponseBuilder;
import org.shoper.contacts.Authrized;
import org.shoper.contacts.bean.User;
import org.shoper.contacts.bean.UserType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityInterceptor {

    @Order(0)
    @Around("within(org.shoper.contacts.web.api.*) && @annotation(authrized) && args(user,..)")
    public BaseResponse apply(ProceedingJoinPoint proceedingJoinPoint, User user, Authrized authrized)
            throws Throwable {
        boolean allowed = false;
        if (user != null) {
            for (UserType userType : authrized.value()) {
                if (user.getType() == userType) {
                    allowed = true;
                    break;
                }
            }
        }
        if (allowed) {
            return (BaseResponse) proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } else {
            return ResponseBuilder.custom().failed("Unauthorized...", 401).build();
        }
    }
}
