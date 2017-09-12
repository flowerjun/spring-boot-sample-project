package com.example.gnu.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.gnu.controller.FileController;
import com.example.gnu.exception.ExtensionNotMatchException;
import com.example.gnu.exception.InvalidHeaderException;

@Component
@Aspect
public class AopController {
	@Autowired
	FileController fileController;
	
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
	
	@Around(value = "execution(* com.example.gnu.controller.FileController.fileUploadHandler(..))")
	public Object fileValidator(ProceedingJoinPoint pjp) throws Throwable{
		MultipartFile file = (MultipartFile)pjp.getArgs()[0];
		try {
			if(!fileController.validator(file)){
				return "upload failed";
			} else {
				System.out.println("validate success");
				return pjp.proceed();
			}
		} catch(InvalidHeaderException e1){
			e1.printStackTrace();
			return "header not accepted";
		} catch(ExtensionNotMatchException e2){
			e2.printStackTrace();
			return "extension not allowed";
		}
		
	}
}
