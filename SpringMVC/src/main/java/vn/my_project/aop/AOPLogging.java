package vn.my_project.aop;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLogging {

	private Logger log = Logger.getLogger(getClass().getName());

	// setup pointcut
	@Pointcut("execution(* vn.my_project.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("execution(* vn.my_project.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("forDaoPackage() || forServicePackage()")
	private void forAppPackage() {
	}

	// @Before advice
	@Before("forAppPackage()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		log.info("========> in @Before: calling method: " + methodName);
	}

	// @After returning
	@AfterReturning(pointcut = "forAppPackage()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().toShortString();
		log.info("========> in @AfterReturning: calling method: " + methodName);

		log.info("Result: " + result);
	}

	// @After throwing
	@AfterThrowing(pointcut = "forAppPackage()", throwing = "exeption")
	public void afterThrowing(JoinPoint joinPoint, Throwable exeption) {
		String methodName = joinPoint.getSignature().toShortString();
		log.info("========> in @AfterThrowing: calling method: " + methodName);

		log.info("Exception: " + exeption);
	}
}