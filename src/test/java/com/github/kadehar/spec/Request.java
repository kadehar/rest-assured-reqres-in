package com.github.kadehar.spec;

import com.github.kadehar.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.github.kadehar.filter.LogFilter.filters;

public class Request {
    private static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(Config.baseURL())
            .setBasePath(Config.basePath())
            .addFilter(filters().withCustomTemplates())
            .setContentType(ContentType.JSON)
            .build();

    public static RequestSpecification spec() {
        return spec;
    }
}
