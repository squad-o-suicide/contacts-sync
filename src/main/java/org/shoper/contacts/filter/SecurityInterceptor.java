package org.shoper.contacts.filter;

import com.mxc.c2c.data.entity.UserType;
import com.mxc.c2c.extrenalApi.UserInfo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityInterceptor {

	@Order(0)
	@Around("within(com.mxc.c2c.web.controller.*) && @annotation(require) && args(userInfo,..)")
	public ResponseEntity apply(ProceedingJoinPoint proceedingJoinPoint, UserInfo userInfo, Require require)
			throws Throwable {
		boolean allowed = false;
		for (UserType userType : require.value()) {
			if (userInfo.getUserType() == userType) {
				allowed = true;
				break;
			}
		}
		if (allowed) {
			return (ResponseEntity) proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		} else {
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setCode(ResponseCode.Error);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
		}
	}
}
