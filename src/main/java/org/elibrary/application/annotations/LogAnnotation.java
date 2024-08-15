package org.elibrary.application.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//this annotation will be on field, method, class level
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
}
