package com.assessment.checkout.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@Aspect
public class LoggingAspect {

	public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	void hasRequestMappingAnnotation() {
	}

	/**
	 * Logs request and response info for all methods annotated with
	 * {@link GetMapping} when the method completes.
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)",
			returning = "result")
	void afterGetRequestMappedMethodExecution(JoinPoint joinPoint, Object result) {

		StringBuilder info = getLogInfo(joinPoint);

		info.append("Response:").append(result);

		LOGGER.debug(info.toString());

	}

	/**
	 * Logs request and exception info for all methods annotated with
	 * {@link GetMapping} when the method throws an exception.
	 * 
	 * @param joinPoint
	 *            - Joinpoint
	 * @param exception
	 *            - Exception thrown by the method
	 */
	@AfterThrowing(pointcut = "@annotation(org.springframework.web.bind.annotation.GetMapping)",
			throwing = "exception")
	public void auditGetExceptionInfo(JoinPoint joinPoint, Throwable exception) {

		StringBuilder info = getLogInfo(joinPoint);

		LOGGER.error(info.toString(), exception);

	}

	/**
	 * Logs request and response info for all methods annotated with
	 * {@link PostMapping} when the method completes.
	 * 
	 * @param joinPoint
	 *            - JoinPoint
	 * @param result
	 *            - Result returned by the method
	 */
	@AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)",
			returning = "result")
	void afterPostRequestMappedMethodExecution(JoinPoint joinPoint, Object result) {

		StringBuilder info = getLogInfo(joinPoint);

		info.append("Response:").append(result);

		LOGGER.debug(info.toString());

	}

	/**
	 * Logs request and exception info for all methods annotated with
	 * {@link PostMapping} when the method throws an exception.
	 * 
	 * @param joinPoint
	 *            - JoinPoint
	 * @param exception
	 *            - Exception thrown by the method
	 */
	@AfterThrowing(pointcut = "@annotation(org.springframework.web.bind.annotation.PostMapping)",
			throwing = "exception")
	public void auditPostExceptionInfo(JoinPoint joinPoint, Throwable exception) {

		StringBuilder info = getLogInfo(joinPoint);

		LOGGER.error(info.toString(), exception);

	}

	private StringBuilder getLogInfo(JoinPoint joinPoint) {

		StringBuilder info = new StringBuilder();

		String method = joinPoint.getSignature().toShortString();
		Object[] args = joinPoint.getArgs();

		info.append("Method: ").append(method).append(LINE_SEPARATOR);
		if (null != args) {
			Arrays.asList(args).forEach((arg) -> {
				info.append(arg).append(LINE_SEPARATOR);
			});
		}
		return info;
	}

}
