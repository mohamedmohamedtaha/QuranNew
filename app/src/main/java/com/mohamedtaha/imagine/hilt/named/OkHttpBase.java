package com.mohamedtaha.imagine.hilt.named;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OkHttpBase {
    String value()default "ok_http_base_url";
}
