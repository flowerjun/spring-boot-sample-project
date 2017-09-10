package com.example.gnu.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.gnu.DTO.MyUser;

@Component
@Aspect
public class AopController {
	private final Logger LOG = LogManager.getLogger();
	@Before(value = "execution(* com.example.gnu.DAO.*.*(..))")
	public void daoParamCheck(JoinPoint joinPoint) throws Throwable{
		for(Object obj : joinPoint.getArgs()){
			LOG.info("param : {}", obj);
		}
	}
	@AfterReturning(value = "execution(* com.example.gnu.DAO.*.*(..))", returning="returnValue")
	public void daoReturnCheck(JoinPoint joinPoint, Object returnValue) throws Throwable{
		LOG.info("return value : {}", returnValue);
	}
}
