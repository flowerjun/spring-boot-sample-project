package com.example.gnu.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopController {
	@Before(value = "execution(* com.example.gnu.DAO.*.*(..))")
	public void daoParamCheck(JoinPoint joinPoint) throws Throwable{
		for(Object obj : joinPoint.getArgs()){
			System.out.println(obj);
		}
	}
	@AfterReturning(value = "execution(* com.example.gnu.DAO.*.*(..))", returning="returnValue")
	public void daoReturnCheck(JoinPoint joinPoint, Object returnValue) throws Throwable{
		System.out.println(returnValue);
	}
}
