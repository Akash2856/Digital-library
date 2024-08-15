package org.elibrary.application;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomAspect {

    @Before("execution(* org.elibrary.application.controller.bookController.getBook(..))")
    public void emitBeforeLogs(JoinPoint joinPoint) {
        log.info("I am in emit logs before: "+ joinPoint.getSignature());
    }

    @After("execution(* org.elibrary.application.controller.bookController.getBook(..))")
    public void emitAfterLogs(JoinPoint joinPoint) {
        log.info("I am in emit logs after: "+ joinPoint.getSignature());
    }

    @Around("execution(* org.elibrary.application.Service.BookService.getBooks(..))")
    public Object emitAfterLogs(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("I am around in emit logs before: "+ proceedingJoinPoint.getSignature());
        Object response = proceedingJoinPoint.proceed();
//        List<Book> res = (java.util.List<Book>)response;
        log.info("response: {}", response);
        log.info("I am around in emit logs after: "+ proceedingJoinPoint.getSignature());
        return response;
    }


    @Around("@annotation(org.elibrary.application.annotations.LogAnnotation)")
    public Object emitLogsUsingAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("I am around annotation in emit logs before: "+ proceedingJoinPoint.getSignature());
        Object response = proceedingJoinPoint.proceed();
//        List<Book> res = (java.util.List<Book>)response;
        log.info("response annotation : {}", response);
        log.info("I am around annotation in emit logs after: "+ proceedingJoinPoint.getSignature());
        return response;
    }


}