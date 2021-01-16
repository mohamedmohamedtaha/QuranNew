package com.MohamedTaha.Imagine.New.dagger2.named;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;
import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlBase {
    String value() default "base_url";
}
