package com.app01.ch01.request;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@BindingAnnotation
@Target(value = {FIELD, PARAMETER, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EdgeValue {
}
